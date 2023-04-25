package com.example.sakis.spring.rest.api.postgresql.repository;

import com.example.sakis.spring.rest.api.postgresql.db.model.Match;
import com.example.sakis.spring.rest.api.postgresql.db.model.MatchOdd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchOddsRepository extends JpaRepository<MatchOdd, Long> {
    Optional<MatchOdd> getMatchOddByMatchAndSpecifier(Match match, String specifier);
}
