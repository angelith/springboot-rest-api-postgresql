package com.example.sakis.spring.rest.api.postgresql.repository;

import com.example.sakis.spring.rest.api.postgresql.db.model.Match;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "matches", path = "matches")
public interface RestMatchRepository extends PagingAndSortingRepository<Match, Long> {
}
