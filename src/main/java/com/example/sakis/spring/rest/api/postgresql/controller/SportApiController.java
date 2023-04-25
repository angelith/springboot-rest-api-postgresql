package com.example.sakis.spring.rest.api.postgresql.controller;

import com.example.sakis.spring.rest.api.postgresql.data.MatchOddRequest;
import com.example.sakis.spring.rest.api.postgresql.data.MatchRequest;
import com.example.sakis.spring.rest.api.postgresql.db.model.Match;
import com.example.sakis.spring.rest.api.postgresql.db.model.MatchOdd;
import com.example.sakis.spring.rest.api.postgresql.service.MatchOddsService;
import com.example.sakis.spring.rest.api.postgresql.service.MatchService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path = "/api/sport/v1/")
public class SportApiController {

    private final MatchService matchService;
    private final MatchOddsService matchOddsService;

    public SportApiController(MatchService matchService, MatchOddsService matchOddsService){
        this.matchService = matchService;
        this.matchOddsService = matchOddsService;
    }

    @GetMapping(path = {"/matches"})
    public Collection<Match> getMatches() throws ResourceNotFoundException {
        return matchService.getAllMatches();
    }

    @GetMapping(path = {"/matches/{id}"})
    public Match getMatch(@PathVariable(name = "id") final Long id) {
        return matchService.getMatch(id);
    }


    @PostMapping(path = "/matches")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createMatch(@RequestBody@Valid final MatchRequest matchRequest) throws Throwable {
        Match match = matchService.create(matchRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(match.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(path = "/matches/{id}")
    public ResponseEntity<Void> updateMatch(@PathVariable(name = "id") final Long matchId, @RequestBody @Valid final MatchRequest matchRequest){
        matchService.update(matchId, matchRequest);
        return ResponseEntity.noContent().location(ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri()).build();
    }

    @DeleteMapping(path = "/matches/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable(name = "id") final Long matchId){
        matchService.deleteMatch(matchId);
        return ResponseEntity.ok().build();
    }


    @GetMapping(path = {"/matchodds"})
    public Collection<MatchOdd> getMatchOdds(){
        return matchOddsService.getAllMatchOdds();
    }

    @GetMapping(path = {"/matchodds/{id}"})
    public MatchOdd getMatchOdd(@PathVariable(name = "id") final Long id) {
        return matchOddsService.getMatchOdd(id);
    }

    @PostMapping(path = "/matchodds")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createMatchOdd(@RequestBody final MatchOddRequest matchOddRequest){
        MatchOdd matchOdd = matchOddsService.create(matchOddRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(matchOdd.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(path = "/matchodds/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> updateMatchOdd(@PathVariable(name = "id") final Long matchId, @RequestBody @Valid final MatchOddRequest matchOddRequest){
        matchOddsService.update(matchId, matchOddRequest);
        return ResponseEntity.noContent().location(ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri()).build();
    }

    @DeleteMapping(path = "/matchodds/{id}")
    public ResponseEntity<Void> deleteMatchOdd(@PathVariable(name = "id") final Long matchId){
        matchOddsService.deleteMatchOdd(matchId);
        return ResponseEntity.ok().build();
    }

}
