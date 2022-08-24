package com.example.sakis.spring.rest.api.postgresql.repository;

import com.example.sakis.spring.rest.api.postgresql.db.model.MatchOdd;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "match-odds", collectionResourceRel = "match-odds")
public interface RestMatchOddsRepository extends PagingAndSortingRepository<MatchOdd,Long> {
}
