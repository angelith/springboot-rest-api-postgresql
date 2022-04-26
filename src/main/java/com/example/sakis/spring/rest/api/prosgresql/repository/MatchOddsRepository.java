package com.example.sakis.spring.rest.api.prosgresql.repository;

import com.example.sakis.spring.rest.api.prosgresql.db.model.MatchOdd;
import org.springframework.data.repository.CrudRepository;

public interface MatchOddsRepository extends CrudRepository<MatchOdd, Long> {
}
