package com.example.sakis.spring.rest.api.prosgresql.advice;

import com.example.sakis.spring.rest.api.prosgresql.exception.ErrorResponse;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundExceptions(ResourceNotFoundException ex, WebRequest webRequest){
        ErrorResponse errorResponse = new ErrorResponse("Sorry some other time ;) - " + ex.getLocalizedMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundExceptions(IllegalArgumentException ex, WebRequest webRequest){
        ErrorResponse errorResponse = new ErrorResponse("Sorry some other time ;) - " + ex.getLocalizedMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
