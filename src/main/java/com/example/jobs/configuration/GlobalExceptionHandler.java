package com.example.jobs.configuration;

import com.example.jobs.exceptions.InvalidSearchKeyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidSearchKeyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationException (InvalidSearchKeyException e){
        /*val errorMessage = Optional.of(ex)
                .map(BindException::getBindingResult)
                .map(Errors::getFieldError)
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse( "Generic error");*/
        log.error("Invalid Exeption erro:", e);
        return  ResponseEntity.badRequest().body(e.getMessage());
    }

}
