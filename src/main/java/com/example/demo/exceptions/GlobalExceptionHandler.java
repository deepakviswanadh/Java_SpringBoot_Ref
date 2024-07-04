package com.example.demo.exceptions;

import com.example.demo.DTO.ResponseDTO;
import com.example.demo.exceptions.Types.FieldAlreadyExistsException;
import com.example.demo.exceptions.Types.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    //{} is to hold multiple exception classes,
    // if you want to use the same body for different exceptions
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ResponseDTO> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                       WebRequest webRequest) {
        ResponseDTO errorDetails = new ResponseDTO(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "RESOURCE_NOT_FOUND"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({FieldAlreadyExistsException.class})
    public ResponseEntity<ResponseDTO> handleFieldAlreadyExistsException(FieldAlreadyExistsException exception,
                                                                       WebRequest webRequest) {
        ResponseDTO errorDetails = new ResponseDTO(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "FIELD_ALREADY_EXISTS"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ResponseDTO> handleGeneralException(Exception exception,
                                                                         WebRequest webRequest) {
        ResponseDTO errorDetails = new ResponseDTO(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "INTERNAL_SERVER_ERROR"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

       Map<String, String> errorDetailsDescription= new HashMap<>();
       ex.getBindingResult().getAllErrors().stream().forEach(error->{
           String errorName = ((FieldError)error).getField();
           String errorMessage= error.getDefaultMessage();
           errorDetailsDescription.put(errorName,errorMessage);
       });

        ResponseDTO errorDetails = new ResponseDTO(
                LocalDateTime.now(),
                errorDetailsDescription,
                request.getDescription(false),
                "BAD_REQUEST"
        );
       return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

}
