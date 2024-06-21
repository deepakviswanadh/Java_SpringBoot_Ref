package com.example.demo.exceptions;

import com.example.demo.DTO.ResponseDTO;
import com.example.demo.exceptions.Types.FieldAlreadyExistsException;
import com.example.demo.exceptions.Types.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    //{} is to hold multiple exception classes, if you want to use the same body for different exceptions
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ResponseDTO> handleResourceNotFoundException(Exception exception,
                                                                       WebRequest webRequest) {
        ResponseDTO errorDetails = new ResponseDTO(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "USER_NOT_FOUND"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({FieldAlreadyExistsException.class})
    public ResponseEntity<ResponseDTO> handleFieldAlreadyExistsException(Exception exception,
                                                                       WebRequest webRequest) {
        ResponseDTO errorDetails = new ResponseDTO(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "FIELD_ALREADY_EXISTS"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
