package com.example.sakis.spring.rest.api.postgresql.advice;

import com.example.sakis.spring.rest.api.postgresql.exception.ErrorResponse;
import com.example.sakis.spring.rest.api.postgresql.exception.InvalidArgumentException;
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
        ErrorResponse errorResponse = new ErrorResponse("Not found - Sorry some other time ;) - " + ex.getLocalizedMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({IllegalArgumentException.class, InvalidArgumentException.class})
    public ResponseEntity<ErrorResponse> handleIllegalArgumentExceptions(Exception ex, WebRequest webRequest){
        ErrorResponse errorResponse = new ErrorResponse("Wrong argument - " + ex.getLocalizedMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleOtherExceptions(Exception ex, WebRequest webRequest){
        ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
