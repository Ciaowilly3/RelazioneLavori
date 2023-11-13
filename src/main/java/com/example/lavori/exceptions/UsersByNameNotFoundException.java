package com.example.lavori.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsersByNameNotFoundException extends RuntimeException {
    public UsersByNameNotFoundException(String message){
        super(message);
    }
}
