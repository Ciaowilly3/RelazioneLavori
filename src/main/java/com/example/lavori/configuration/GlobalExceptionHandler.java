package com.example.lavori.configuration;

import com.example.lavori.exceptions.InvalidSearchKeyException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

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
