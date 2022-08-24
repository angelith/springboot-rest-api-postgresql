package com.example.sakis.spring.rest.api.postgresql.service;

import com.example.sakis.spring.rest.api.postgresql.controller.MatchRequest;
import com.example.sakis.spring.rest.api.postgresql.db.model.Match;
import com.example.sakis.spring.rest.api.postgresql.repository.MatchRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Primary
public class MatchServiceImpl implements MatchService {

    MatchRepository matchRepository;

    public MatchServiceImpl(MatchRepository matchRepository){
        this.matchRepository = matchRepository;
    }

    @Override
    public Match getMatch(Long id) throws ResourceNotFoundException {
        return matchRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Collection<Match> getAllMatches() {
        return Streamable.of(matchRepository.findAll()).toSet();
    }

    @Override
    public Match create(MatchRequest matchRequest){
        Match match;
        try{
            match = Match.builder()
                    .matchTime(matchRequest.getMatchTime())
                    .matchDate(matchRequest.getMatchDate())
                    .description(matchRequest.getDescription())
                    .sport(matchRequest.getSport())
                    .teamB(matchRequest.getTeamB())
                    .teamA(matchRequest.getTeamA())
                    .build();
        }
        catch (Exception e){
            throw new IllegalArgumentException("Could not create Match object from input MatchRequest.", e);
        }
        return matchRepository.save(match);
    }

    @Override
    public void update(Long matchId, MatchRequest matchRequest) {
       matchRepository.findById(matchId).orElseThrow(ResourceNotFoundException::new);
       try{
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
       }
       catch (Exception e){
           throw new IllegalArgumentException("Could not create Match object from input MatchRequest.", e);
       }
    }

    @Override
    public void deleteMatch(Long matchId) {
        matchRepository.deleteById(matchId);
    }
}
