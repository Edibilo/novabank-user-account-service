package iki.novabank_user_account_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class AlreadyFound extends RuntimeException{
    public AlreadyFound(String message) {
        super(message);
    }
}
