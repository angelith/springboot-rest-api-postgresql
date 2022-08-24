package com.example.sakis.spring.rest.api.postgresql.db.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "match")
    @JsonManagedReference
    private final Set<MatchOdd> matchOdd = new HashSet<>();

    private String description;

    @Column(name = "match_date")
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date matchDate;

    @JsonFormat(pattern="HH:mm")
    @Column(name = "match_time")
    private Date matchTime;

    @Column(name = "team_a")
    private String teamA;

    @Column(name = "team_b")
    private String teamB;

    @Enumerated(EnumType.ORDINAL)
    private Sport sport;

    public enum Sport{
        FOOTBALL,
        BASKETBALL;
    }
}
