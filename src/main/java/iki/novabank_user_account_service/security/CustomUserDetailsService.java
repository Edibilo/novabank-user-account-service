package iki.novabank_user_account_service.security;

import iki.novabank_user_account_service.entity.User;
import iki.novabank_user_account_service.exception.NotFoundException;
import iki.novabank_user_account_service.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(username).orElseThrow(
                () -> new NotFoundException("User Not Found !")
        );

        Set<GrantedAuthority> authorities= Collections.singleton(
                new SimpleGrantedAuthority(user.getRole().getName())
        );

        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                authorities
        );
    }
}
