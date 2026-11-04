package iki.novabank_user_account_service.service.impl;

import iki.novabank_user_account_service.dto.JwtResponse;
import iki.novabank_user_account_service.dto.LoginDto;
import iki.novabank_user_account_service.dto.ResponseMessage;
import iki.novabank_user_account_service.dto.UserDto;
import iki.novabank_user_account_service.entity.Account;
import iki.novabank_user_account_service.entity.Role;
import iki.novabank_user_account_service.entity.User;
import iki.novabank_user_account_service.kafka.dto.UserRegistrationEvent;
import iki.novabank_user_account_service.kafka.service.AccountEventPublisher;
import iki.novabank_user_account_service.repository.AccountRepository;
import iki.novabank_user_account_service.repository.RoleRepository;
import iki.novabank_user_account_service.repository.UserRepository;
import iki.novabank_user_account_service.security.JwtUtilsProvider;
import iki.novabank_user_account_service.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtilsProvider jwtUtilsProvider;
    private  final AccountEventPublisher accountEventPublisher;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository, AccountRepository accountRepository,
                           AuthenticationManager authenticationManager, JwtUtilsProvider jwtUtilsProvider, AccountEventPublisher accountEventPublisher) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtilsProvider = jwtUtilsProvider;
        this.accountEventPublisher = accountEventPublisher;
    }

    //register user
    @Override
    public ResponseMessage register(UserDto userDto) {
        Role role = roleRepository.findByName("ROLE_CUSTOMER");
        //Create New User
        User user = new User();
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setLastName(userDto.getLastName());
        user.setFirstName(userDto.getFirstName());
        user.setEnabled(userDto.isEnabled());
        user.setEmail(userDto.getEmail());
        user.setCreatedAt(userDto.getCreatedAt());
        user.setRole(role);
        User savedUser = userRepository.save(user);

        //create Account For New user
        Account account = new Account();
        account.setBalance(BigDecimal.ZERO);
        account.setAccountNumber(generateUniqueAccountNumber());
        account.setUser(savedUser);
        Account savedAccount=accountRepository.save(account);

        //published event
        UserRegistrationEvent event=new UserRegistrationEvent();
        event.setEmail(savedUser.getEmail());
        event.setFirstName(savedUser.getFirstName());
        event.setLastName(savedUser.getLastName());
        event.setAccountNumber(savedAccount.getAccountNumber());
        event.setBankName("NOVA BANK");
        accountEventPublisher.publishedUserRegistrationEvent(event);

        ResponseMessage message = new ResponseMessage();
        message.setMessage("Registered Successfully !");
        return message;
    }

    //generate account Number
    private String generateUniqueAccountNumber() {
        String accountNumber;

        ThreadLocalRandom random = ThreadLocalRandom.current();

        do {
            //it will generate random 8 digits(between 0000000 to 99999999)
            int randomPart = random.nextInt(100_000_000);
            accountNumber = String.format("00%08d", randomPart);

        } while (accountRepository.existsByAccountNumber(accountNumber));

        return accountNumber;
    }

    //login
    @Override
    public JwtResponse login(LoginDto loginDto) {
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
       JwtResponse jwtResponse=new JwtResponse();
       jwtResponse.setToken(jwtUtilsProvider.generateToken(authentication));
        return jwtResponse;
    }
}
