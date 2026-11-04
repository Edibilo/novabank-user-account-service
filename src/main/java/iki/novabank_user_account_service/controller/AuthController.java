package iki.novabank_user_account_service.controller;

import iki.novabank_user_account_service.dto.JwtResponse;
import iki.novabank_user_account_service.dto.LoginDto;
import iki.novabank_user_account_service.dto.ResponseMessage;
import iki.novabank_user_account_service.dto.UserDto;
import iki.novabank_user_account_service.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("register")
    public ResponseEntity<ResponseMessage> register(@RequestBody @Valid UserDto userDto){
        ResponseMessage message=authService.register(userDto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody @Valid LoginDto loginDto){
        JwtResponse responseMessage=authService.login(loginDto);
        return new ResponseEntity<>(responseMessage,HttpStatus.CREATED);
    }
}
