package com.example.sakis.spring.rest.api.postgresql;

import com.example.sakis.spring.rest.api.postgresql.db.model.Match;
import com.example.sakis.spring.rest.api.postgresql.repository.MatchRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.Date;

@SpringBootTest
@ActiveProfiles("h2")
class RestApiPostgresApplicationTests {

    @Autowired
    MatchRepository matchRepository;

    @Test
    void contextLoads() {
    }

    @Test
    @Transactional
    public void insertAndGetAll(){


        Match match = Match.builder()
                .teamA("OSFP")
                .teamB("PAS")
                .matchDate(new Date())
                .matchTime(new Date())
                .description("test game")
                .sport(Match.Sport.FOOTBALL)
                .build();

        matchRepository.save(match);

        Iterable<Match> matches = matchRepository.findAll();
        for(Match m: matches){
            System.out.println(m.getId().toString() + " - Date: " + m.getMatchDate() + " Time: " + m.getMatchTime());
        }
    }


}
