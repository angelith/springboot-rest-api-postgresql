package com.example.sakis.spring.rest.api.postgresql.db.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "MatchOdds")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchOdd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "specifier")
    String specifier;

    @Column(name = "odd")
    Float odd;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "match_id")
    @JsonBackReference
    private Match match;

}
