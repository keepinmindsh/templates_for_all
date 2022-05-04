package bong.lines.sample;

import bong.lines.sample.model.entity.Member;
import bong.lines.sample.model.entity.QMember;
import bong.lines.sample.model.entity.QTeam;
import bong.lines.sample.model.entity.Team;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

import static bong.lines.sample.model.entity.QMember.*;
import static bong.lines.sample.model.entity.QMember.member;
import static bong.lines.sample.model.entity.QTeam.team;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class QueryDslBasicTest {

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
    public void startJPQL(){
        // member1 조회
        Member resultList = entityManager.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

        assertThat(resultList.getUsername()).isEqualTo("member1");
    }

    @Test
    public void startQueryDSL(){
        QMember qMember = new QMember("m");  // 어떤 QMember인지를 구분하는 m값 지정

        // prepare Statement에 파라미터 바인딩을 사용함.
        Member member = jpaQueryFactory
                .select(qMember)
                .from(qMember)
                .where(qMember.username.eq("member1"))
                .fetchOne();


        assert member != null;
        assertThat(member.getUsername()).isEqualTo("member1");
    }

    @Test
    public void testQType(){
        QMember member = QMember.member;

        Member findMember = jpaQueryFactory
                .select(member)
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        System.out.println("findMember.getUsername() = " + findMember.getUsername());

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void testQNamee(){
        QMember member = QMember.member;

        Member findMember = jpaQueryFactory
                .select(member)
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        System.out.println("findMember.getUsername() = " + findMember.getUsername());

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void testQAlias(){
        // Alias 를 직접 정의하는 경우, 같은 테이블의 조인해야하는 경우, 식별자를 통한 테이블 분리 가능함.
        QMember qMember = new QMember("memb");

        Member findMember = jpaQueryFactory.select(qMember)
                .from(qMember)
                .where(qMember.username.eq("member1"))
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    @DisplayName("Where 절을 정의하는 and에 의해 정의하는 방식")
    public void testSelectFrom(){

        Member findMember = jpaQueryFactory
                .selectFrom(member)
                .where(member.username.eq("member1").and(member.age.eq(10)))
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");

        /**
         * select member1
         * from Member member1
         * where member1.username = 'member1'1 and member1.age = 102
         */

    }

    @Test
    @DisplayName("Where 절을 정의하는 콤마의 의해 정의하는 방식")
    public void testSelectFromSecond(){

        Member findMember = jpaQueryFactory
                .selectFrom(member)
                .where(  //and 인 경우, 콤마로 대체 가능
                        member.username.eq("member1"),
                        (member.age.eq(10))
                )
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");

        /**
         * select member1
         * from Member member1
         * where member1.username = 'member1'1 and member1.age = 102
         */
    }

    @Test
    @DisplayName("Result를 fetch로 가져오는 방식")
    public void testResultWithFetchType(){

        List<Member> fetchListMember = jpaQueryFactory
                .selectFrom(member).fetch();

        fetchListMember.size();

        Member fetchOneMember = jpaQueryFactory.selectFrom(member)
                .fetchOne();

        Member fetchFirst = jpaQueryFactory.selectFrom(member).fetchFirst();

        QueryResults<Member> memberQueryResults = jpaQueryFactory.selectFrom(member).fetchResults();

        List<Member> results = memberQueryResults.getResults();

        memberQueryResults.getTotal();

        long fetchCount = jpaQueryFactory.selectFrom(member).fetchCount();

    }

    @Test
    @DisplayName("정렬하기")
    public void testResultWithOrder(){

        List<Member> fetch = jpaQueryFactory.selectFrom(member)
                .where(member.age.gt(1))
                .orderBy(member.age.desc(), member.username.asc().nullsLast())
                .fetch();

        Member member1 = fetch.get(0);
        Member member2 = fetch.get(1);
        Member member3 = fetch.get(2);

        assertThat(member1.getUsername()).isEqualTo("member1");
        assertThat(member2.getUsername()).isNull();
    }

    @Test
    @DisplayName("페이징")
    public void testPaging(){

        /**
         * offset - 시작지점
         * limit - 페이지 사이즈
         *
         * select p from Post p offset 3 limit 10
         * 이라고 한다면 3번째 로우에서부터 10개의 로우를 가져온다는 것이다.
         * 주의할점은 Querydsl 에서 offset 은 0부터 시작한다.
         */
        List<Member> fetch = jpaQueryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1)
                .limit(2)
                .fetch();

        assertThat(fetch.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("집합 처리")
    public void testAggregation(){
        List<Tuple> fetch = jpaQueryFactory
                .select(
                        member.count(),
                        member.age.sum(),
                        member.age.avg(),
                        member.age.max(),
                        member.age.min()
                ).from(member)
                .fetch();

        Tuple tuple = fetch.get(0);

        /***
         * select count(member1), sum(member1.age), avg(member1.age), max(member1.age), min(member1.age)
         * from Member member1
         */

        assertThat(tuple.get(member.count())).isEqualTo(4);
    }

    @Test
    @DisplayName("Group By - Aggregations")
    public void testGroupBy(){
        List<Tuple> fetch = jpaQueryFactory.select(team.name, member.age.avg())
                .from(member)
                .join(member.team, team)
                .groupBy(team.name)
                .fetch();


        List<Tuple> fetchMember = jpaQueryFactory.select(team.name, member.age.avg())
                .from(member)
                .join(member.team, team)
                .groupBy(team.name)
                .having(team.name.eq("Test!"))
                .fetch();

        Tuple teamA = fetch.get(0);
        Tuple teamB = fetch.get(1);

        /**
         * select team.name, avg(member1.age)
         * from Member member1
         *   inner join member1.team as team
         * group by team.name
         */

        assertThat(teamA.get(team.name)).isEqualTo("teamA");
        assertThat(teamA.get(member.age.avg())).isEqualTo(15);

        assertThat(teamB.get(team.name)).isEqualTo("teamB");
        assertThat(teamB.get(member.age.avg())).isEqualTo(35);
    }

    @Test
    @DisplayName("Join 테스트")
    public void testJoin(){
        // given
        List<Member> teamList = jpaQueryFactory
                .selectFrom(member)
                .join(member.team, team)
                .where(team.name.eq("teamA"))
                .fetch();

        /**
         * select member1
         * from Member member1
         *   inner join member1.team as team
         * where team.name = 'teamA'
         */

        List<Member> leftJoinList = jpaQueryFactory
                .selectFrom(member)
                .leftJoin(member.team, team)
                .where(team.name.eq("teamA"))
                .fetch();

        assertThat(teamList)
                .extracting("username")
                .containsExactly("member1", "member2");
    }

    @Test
    @DisplayName("세타 조인 - 연관관계를 가지지 않는 필드에 대해서도 조인을 걸 수 있다.")
    public void theta_join(){

        // member의 모든 행과 team의 모든 행에 대해서 조인을 한 뒤 조건을 검색함.

        /* select member1
             from Member member1, Team team
            where member1.username = team.name */

        List<Member> fetch = jpaQueryFactory.selectFrom(member)
                .select(member)
                .from(member, team)
                .where(member.username.eq(team.name))
                .fetch();

        assertThat(fetch)
                .extracting("username")
                .containsExactly("teamA", "teamB");
    }

}
