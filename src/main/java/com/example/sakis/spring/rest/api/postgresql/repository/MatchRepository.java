package com.example.sakis.spring.rest.api.postgresql.repository;

import com.example.sakis.spring.rest.api.postgresql.db.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {
    Optional<Match> findByMatchDateAndMatchTime(Date matchDate, Date matchTime);
}