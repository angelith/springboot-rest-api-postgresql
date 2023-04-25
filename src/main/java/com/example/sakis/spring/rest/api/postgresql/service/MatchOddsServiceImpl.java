package com.example.sakis.spring.rest.api.postgresql.service;

import com.example.sakis.spring.rest.api.postgresql.data.MatchOddRequest;
import com.example.sakis.spring.rest.api.postgresql.db.model.MatchOdd;
import com.example.sakis.spring.rest.api.postgresql.exception.InvalidArgumentException;
import com.example.sakis.spring.rest.api.postgresql.repository.MatchOddsRepository;
import com.example.sakis.spring.rest.api.postgresql.repository.MatchRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
    @Cacheable("match-odds")
    public MatchOdd getMatchOdd(Long id) throws ResourceNotFoundException {
        return matchOddsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    @Cacheable("match-odds-collection")
    public Collection<MatchOdd> getAllMatchOdds() {
        return Streamable.of(matchOddsRepository.findAll()).toList();
    }

    @Override
    @CacheEvict(value = "match-odds-collection",allEntries = true)
    public MatchOdd create(MatchOddRequest matchOddRequest) {
        MatchOdd matchOdd = MatchOdd.builder()
                .odd(matchOddRequest.getOdd())
                .specifier(matchOddRequest.getSpecifier())
                .build();
       return matchRepository.findById(matchOddRequest.getMatchId())
                        .map(match -> {
                           if(matchOddsRepository.getMatchOddByMatchAndSpecifier(match, matchOddRequest.getSpecifier()).isPresent()){
                               try {
                                   throw new InvalidArgumentException("Error creating Odd for match " + match.getId().toString()+ ". Specifier already exists for that match");
                               } catch (InvalidArgumentException e) {
                                   throw new RuntimeException(e);
                               }
                           }
                            matchOdd.setMatch(match);
                            return matchOddsRepository.save(matchOdd);})
               .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    @Caching(put = @CachePut("match-odds"), evict = @CacheEvict(value = "match-odds-collection",allEntries = true))
    public void update(Long matchOddId, MatchOddRequest matchOddRequest) {
        matchOddsRepository.findById(matchOddId).orElseThrow(ResourceNotFoundException::new);
        matchOddsRepository.save(MatchOdd.builder()
                .odd(matchOddRequest.getOdd())
                .specifier(matchOddRequest.getSpecifier())
                .id(matchOddId)
                .build());
    }

    @Override
    @Caching(evict = {@CacheEvict("match-odds"), @CacheEvict(value = "match-odds-collection",allEntries = true)})
    public void deleteMatchOdd(Long matchId) {
        matchOddsRepository.findById(matchId).orElseThrow(ResourceNotFoundException::new);
        matchOddsRepository.deleteById(matchId);
    }
}
