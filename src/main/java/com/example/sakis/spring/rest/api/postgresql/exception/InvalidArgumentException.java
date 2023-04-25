package com.example.sakis.spring.rest.api.postgresql.exception;

public class InvalidArgumentException extends Exception{
    public InvalidArgumentException(String s) {
        super(s);
    }
}
