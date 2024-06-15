package com.example.demo.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String fieldName;
    Object fieldValue;

    public ResourceNotFoundException(String resourceName,String fieldName,Object fieldValue){
        super(String.format("%s not found with %s: %s",resourceName,fieldName,fieldValue));
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
    }

    //this will reduce the stack trace content being sent in the error response to the client

//    @Override
//    public Throwable fillInStackTrace() {
//        return this;
//    }

}
