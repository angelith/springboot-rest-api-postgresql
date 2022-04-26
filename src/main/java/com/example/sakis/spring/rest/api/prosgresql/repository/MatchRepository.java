package com.example.sakis.spring.rest.api.prosgresql.repository;

import com.example.sakis.spring.rest.api.prosgresql.db.model.Match;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Long> {
}
