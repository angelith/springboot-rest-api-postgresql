package com.example.sakis.spring.rest.api.postgresql.service;

import com.example.sakis.spring.rest.api.postgresql.data.MatchRequest;
import com.example.sakis.spring.rest.api.postgresql.db.model.Match;
import com.example.sakis.spring.rest.api.postgresql.exception.InvalidArgumentException;
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
public class MatchServiceImpl implements MatchService {

    MatchRepository matchRepository;

    public MatchServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    @Cacheable("matches")
    public Match getMatch(Long id) throws ResourceNotFoundException {
        return matchRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    @Cacheable("match-collection")
    public Collection<Match> getAllMatches() {
        return Streamable.of(matchRepository.findAll()).toSet();
    }

    @Override
    @CacheEvict(value = "match-collection",allEntries = true)
    public Match create(MatchRequest matchRequest) throws Throwable {
        Match match;
        if (matchRepository.findByMatchDateAndMatchTime(matchRequest.getMatchDate(), matchRequest.getMatchTime()).isPresent()) {
            throw new InvalidArgumentException("Error creating, match already exists");
        }
        try {
            match = Match.builder()
                    .matchTime(matchRequest.getMatchTime())
                    .matchDate(matchRequest.getMatchDate())
                    .description(matchRequest.getDescription())
                    .sport(matchRequest.getSport())
                    .teamB(matchRequest.getTeamB())
                    .teamA(matchRequest.getTeamA())
                    .build();
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not create Match object from input MatchRequest.", e);
        }

        return matchRepository.save(match);
    }

    @Override
    @Caching(put = @CachePut("matches"), evict = @CacheEvict(value = "match-collection",allEntries = true))
    public void update(Long matchId, MatchRequest matchRequest) {
        matchRepository.findById(matchId).orElseThrow(ResourceNotFoundException::new);
        try {
            Match updateMatch = Match.builder()
                    .matchTime(matchRequest.getMatchTime())
                    .matchDate(matchRequest.getMatchDate())
                    .description(matchRequest.getDescription())
                    .sport(matchRequest.getSport())
                    .teamB(matchRequest.getTeamB())
                    .teamA(matchRequest.getTeamA())
                    .id(matchId)
                    .build();
            matchRepository.save(updateMatch);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not create Match object from input MatchRequest.", e);
        }
    }

    @Override
    @Caching(evict = {@CacheEvict("matches"), @CacheEvict(value = "match-collection",allEntries = true)})
    public void deleteMatch(Long matchId) {
        matchRepository.findById(matchId).orElseThrow(ResourceNotFoundException::new);
        matchRepository.deleteById(matchId);
    }
}
