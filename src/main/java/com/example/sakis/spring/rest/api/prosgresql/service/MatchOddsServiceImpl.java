package com.example.sakis.spring.rest.api.prosgresql.service;

import com.example.sakis.spring.rest.api.prosgresql.controller.MatchOddRequest;
import com.example.sakis.spring.rest.api.prosgresql.db.model.MatchOdd;
import com.example.sakis.spring.rest.api.prosgresql.repository.MatchOddsRepository;
import com.example.sakis.spring.rest.api.prosgresql.repository.MatchRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Primary
public class MatchOddsServiceImpl implements MatchOddsService {

    private final MatchOddsRepository matchOddsRepository;
    private final MatchRepository matchRepository;

    public MatchOddsServiceImpl(MatchOddsRepository matchOddsRepository, MatchRepository matchRepository){
        this.matchOddsRepository = matchOddsRepository;
        this.matchRepository = matchRepository;
    }

    @Override
    public MatchOdd getMatchOdd(Long id) throws ResourceNotFoundException {
        return matchOddsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Collection<MatchOdd> getAllMatchOdds() {
        return Streamable.of(matchOddsRepository.findAll()).toList();
    }

    @Override
    public MatchOdd create(MatchOddRequest matchOddRequest) {
        MatchOdd matchOdd = MatchOdd.builder()
                .odd(matchOddRequest.getOdd())
                .specifier(matchOddRequest.getSpecifier())
                .build();
       return matchRepository.findById(matchOddRequest.getMatchId())
                        .map(match -> {
                            matchOdd.setMatch(match);
                            return matchOddsRepository.save(matchOdd);})
               .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void update(Long matchOddId, MatchOddRequest matchOddRequest) {
        matchOddsRepository.findById(matchOddId).orElseThrow(ResourceNotFoundException::new);
        matchOddsRepository.save(MatchOdd.builder()
                .odd(matchOddRequest.getOdd())
                .specifier(matchOddRequest.getSpecifier())
                .id(matchOddId)
                .build());
    }

    @Override
    public void deleteMatchOdd(Long matchId) {
        matchOddsRepository.deleteById(matchId);
    }
}
