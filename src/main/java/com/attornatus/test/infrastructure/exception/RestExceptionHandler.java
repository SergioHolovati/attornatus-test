package com.attornatus.test.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(HandlerException.class)
    public ResponseEntity<?> HandlerException(HandlerException handlerException){
       HandlerExceptionDetails handlerExceptionDetails = HandlerExceptionDetails.HandlerExceptionDetailsBuilder.aHandlerExceptionDetails()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Bad Request")
                .detail(handlerException.getMessage())
                .developerMessage(handlerException.getClass().getName()).build();

       return new ResponseEntity<>(handlerExceptionDetails,HttpStatus.BAD_REQUEST);
    }
}
