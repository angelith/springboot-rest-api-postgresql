package com.example.sakis.spring.rest.api.postgresql.service;

import com.example.sakis.spring.rest.api.postgresql.data.MatchRequest;
import com.example.sakis.spring.rest.api.postgresql.db.model.Match;

import java.util.Collection;


public interface MatchService {

    Match getMatch(Long id);

    Collection<Match> getAllMatches();

    Match create(MatchRequest matchRequest) throws Throwable;

    void update(Long matchId, MatchRequest matchRequest);

    void deleteMatch(Long matchId);
}
