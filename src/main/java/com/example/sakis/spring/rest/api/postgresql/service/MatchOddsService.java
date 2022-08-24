package com.example.sakis.spring.rest.api.postgresql.service;

import com.example.sakis.spring.rest.api.postgresql.controller.MatchOddRequest;
import com.example.sakis.spring.rest.api.postgresql.db.model.MatchOdd;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.Collection;

public interface MatchOddsService {

    MatchOdd getMatchOdd(Long id) throws ResourceNotFoundException;

    Collection<MatchOdd> getAllMatchOdds();

    MatchOdd create(MatchOddRequest matchOdd);

    void update(Long matchOddId, MatchOddRequest matchOddRequest);

    void deleteMatchOdd(Long matchId);
}
