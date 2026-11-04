package iki.novabank_user_account_service.service;

import iki.novabank_user_account_service.dto.JwtResponse;
import iki.novabank_user_account_service.dto.LoginDto;
import iki.novabank_user_account_service.dto.ResponseMessage;
import iki.novabank_user_account_service.dto.UserDto;

public interface AuthService {
    ResponseMessage register(UserDto userDto);
    JwtResponse login(LoginDto loginDto);
}
