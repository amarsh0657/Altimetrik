package org.altimetrik.automotiveims.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @Autowired
    private  ApiException apiException;


    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public ApiException handleBadRequestException( BadRequestException e){
        apiException.setErrorMessage(e.toString());
        apiException.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiException.setZonedDateTime(ZonedDateTime.now());
        return  apiException;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ApiException handleResourceNotFoundException( ResourceNotFoundException e){
        apiException.setErrorMessage(e.toString());
        apiException.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiException.setZonedDateTime(ZonedDateTime.now());
        return  apiException;
    }



}
