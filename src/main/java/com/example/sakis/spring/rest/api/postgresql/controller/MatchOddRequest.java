package com.example.sakis.spring.rest.api.postgresql.controller;

import lombok.Data;

@Data
public class MatchOddRequest {
    String specifier;
    Float odd;
    Long matchId;
}
