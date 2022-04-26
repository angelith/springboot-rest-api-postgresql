package com.example.sakis.spring.rest.api.prosgresql.service;

import com.example.sakis.spring.rest.api.prosgresql.controller.MatchRequest;
import com.example.sakis.spring.rest.api.prosgresql.db.model.Match;

import java.util.Collection;


public interface MatchService {

    Match getMatch(Long id);

    Collection<Match> getAllMatches();

    Match create(MatchRequest matchRequest);

    void update(Long matchId, MatchRequest matchRequest);

    void deleteMatch(Long matchId);
}
