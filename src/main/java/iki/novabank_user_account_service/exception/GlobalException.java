package iki.novabank_user_account_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorEntity> notFound(NotFoundException exception, WebRequest request){
        ErrorEntity errorEntity=new ErrorEntity();
        errorEntity.setMessage(exception.getMessage());
        errorEntity.setErrorCode(request.getDescription(false));
        errorEntity.setLocalDateTime(LocalDateTime.now());
        return ResponseEntity.ok(errorEntity);
    }

    @ExceptionHandler(AlreadyFound.class)
    public ResponseEntity<ErrorEntity> alreadyFound(AlreadyFound exception, WebRequest request){
        ErrorEntity errorEntity=new ErrorEntity();
        errorEntity.setMessage(exception.getMessage());
        errorEntity.setErrorCode(request.getDescription(false));
        errorEntity.setLocalDateTime(LocalDateTime.now());
        return ResponseEntity.ok(errorEntity);
    }
}
