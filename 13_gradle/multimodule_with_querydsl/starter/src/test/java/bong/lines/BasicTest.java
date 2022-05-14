package bong.lines;

import bong.lines.entity.QTeam;
import bong.lines.entity.Team;
import bong.lines.jpa.EnableJPAConfiguration;
import bong.lines.starter.StarterApplication;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import java.util.List;

import static bong.lines.entity.QTeam.team;
import static org.assertj.core.api.Assertions.assertThat;

@EnableJPAConfiguration
@SpringBootTest( classes = StarterApplication.class)
public class BasicTest {

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Test Code")
    public void BasicQueryDSL(){

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        List<Team> fetch = jpaQueryFactory.selectFrom(team)
                .where(team.id.eq(1L))
                .fetch();

        assertThat(fetch).extracting("id").containsExactly(1L);
    }
}
