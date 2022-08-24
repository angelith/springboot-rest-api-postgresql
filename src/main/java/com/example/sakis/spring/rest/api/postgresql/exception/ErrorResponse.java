package com.example.sakis.spring.rest.api.postgresql.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    private final String message;
}
