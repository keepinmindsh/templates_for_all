package bong.lines.sample.study.basic;

import bong.lines.sample.model.entity.Member;
import bong.lines.sample.model.entity.QMember;
import bong.lines.sample.model.entity.Team;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

import static bong.lines.sample.model.entity.QMember.member;
import static bong.lines.sample.model.entity.QTeam.team;
import static com.querydsl.jpa.JPAExpressions.select;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Commit
@Transactional
public class QueryDslSubQeuryTest {

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


    @Test
    @DisplayName("Sub Query 작성시 JPA Expression 활용 예제")
    void testSubQuery(){

        /**
         * select member1
         * from Member member1
         * where member1.age = (select max(MemberSub.age)
         * from Member MemberSub)
         */
        QMember memberSub = new QMember("MemberSub");

        List<Member> fetch = jpaQueryFactory
                .selectFrom(member)
                .where(member.age.eq(
                                select(memberSub.age.max())
                                        .from(memberSub)
                        )
                ).fetch();

        assertThat(fetch).extracting("age")
                .containsExactly(40);

        List<Member> fetchAVG = jpaQueryFactory
                .selectFrom(member)
                .where(member.age.goe(
                                select(memberSub.age.avg())
                                        .from(memberSub)
                        )
                ).fetch();

        assertThat(fetchAVG).extracting("age")
                .containsExactly(40);

        List<Member> fetchIn = jpaQueryFactory
                .selectFrom(member)
                .where(member.age.in(
                                select(memberSub.age)
                                        .from(memberSub)
                                        .where(memberSub.age.gt(10))
                        )
                ).fetch();

        assertThat(fetchIn).extracting("age")
                .containsExactly(20.30,40);
    }

    @Test
    @DisplayName("from 절의 한계 - JPQL의 한계")
    void testSelectSubQuery(){

        /**
         * JPA JPQL 서브 쿼리의 한계점으로 from 절의 서브 쿼리 (인라인 뷰)는 지원하지 않는다.
         * 딩연히 QueryDSL도 지원하지 않는다. 하이버네이트 구현체를 사용하면 select 절의 서브쿼리를 지원한다.
         * QueryDSL도 하이버네이트 구현체를 사용하면 select 절의 서브쿼리를 지원한다.
         *  - 서브쿼리를 join으로 변경한다.
         *  - 애플리케이션 쿼리를 2번 분리해서 실행한다.
         *  - nativeSQL 를 사용한다.
          */
        QMember memberSub = new QMember("MemberSub");

        List<Tuple> fetch = jpaQueryFactory
                .select(member.username,
                        select(memberSub.age.avg())
                                .from(memberSub)
                )
                .from(member)
                .fetch();

        for (Tuple tuple: fetch) {
            System.out.println("tuple = " + tuple) ;
        }
    }
}
