package bong.lines.sample.study.basic;

import bong.lines.sample.model.entity.Member;
import bong.lines.sample.model.entity.Team;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

import static bong.lines.sample.model.entity.QMember.member;
import static bong.lines.sample.model.entity.QTeam.team;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Commit
@Transactional
public class QueryDslFetchJoinTest {

    JPAQueryFactory jpaQueryFactory;

    @Autowired
    EntityManager entityManager;

    @BeforeEach
    public void before(){

        // 이와 같은 방식도 동시성에 대한 문제는 없음.
        jpaQueryFactory = new JPAQueryFactory(entityManager);

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        entityManager.persist(teamA);
        entityManager.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);

        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        entityManager.persist(member1);
        entityManager.persist(member2);
        entityManager.persist(member3);
        entityManager.persist(member4);
    }
    @PersistenceUnit
    EntityManagerFactory emf;

    @Test
    @DisplayName("패치 조인 미적용 케이스")
    void nofetchJoin(){
        entityManager.flush();
        entityManager.clear();

        Member memberOne = jpaQueryFactory
                .selectFrom(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(memberOne.getTeam());

        assertThat(loaded).as("패치 조인 미적용").isFalse();
    }

    @Test
    @DisplayName("패치 조인 적용 케이스")
    void fetchJoin(){
        entityManager.flush();
        entityManager.clear();

        Member memberOne = jpaQueryFactory
                .selectFrom(member)
                // leftJoin, Join 뒤에 fetchJoin을 붙이면 fetchJoin이 적용된다.
                .join(member.team, team)
                .fetchJoin()
                .where(member.username.eq("member1"))
                .fetchOne();

        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(memberOne.getTeam());

        assertThat(loaded).as("패치 조인 미적용").isTrue();
    }

}
