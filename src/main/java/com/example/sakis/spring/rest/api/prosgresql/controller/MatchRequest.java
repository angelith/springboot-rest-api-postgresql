package com.example.sakis.spring.rest.api.prosgresql.controller;

import com.example.sakis.spring.rest.api.prosgresql.db.model.Match;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class MatchRequest {
    private String description;
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date matchDate;
    @JsonFormat(pattern="HH:mm")
    private Date matchTime;
    private String teamA;
    private String teamB;
    private Match.Sport sport;
}
