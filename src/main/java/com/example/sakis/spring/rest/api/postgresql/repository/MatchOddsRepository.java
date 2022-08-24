package com.example.sakis.spring.rest.api.postgresql.repository;

import com.example.sakis.spring.rest.api.postgresql.db.model.MatchOdd;
import org.springframework.data.repository.CrudRepository;

public interface MatchOddsRepository extends CrudRepository<MatchOdd, Long> {
}
