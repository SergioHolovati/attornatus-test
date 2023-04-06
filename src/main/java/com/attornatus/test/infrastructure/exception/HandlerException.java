package com.attornatus.test.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class HandlerException extends RuntimeException {

    public HandlerException(String message){
        super(message);
    }
}
