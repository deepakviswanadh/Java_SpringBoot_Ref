package com.example.demo.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseException extends RuntimeException{
    String resourceName;
    String fieldName;
    Object fieldValue;


    public BaseException(String message,String resourceName,String fieldName,Object fieldValue){
        super(message);
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
    }

    //reduce stack trace
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
