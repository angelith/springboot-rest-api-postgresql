package com.example.sakis.spring.rest.api.prosgresql.controller;

import lombok.Data;

import javax.persistence.Column;

@Data
public class MatchOddRequest {
    String specifier;
    Float odd;
    Long matchId;
}
