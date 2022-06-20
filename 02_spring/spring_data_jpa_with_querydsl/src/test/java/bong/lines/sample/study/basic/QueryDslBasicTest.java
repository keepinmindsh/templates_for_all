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
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
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
    @DisplayName("JPQL을 활용한 Query 작성")
    public void startJPQL(){
        // member1 조회
        Member resultList = entityManager
                .createQuery(
                        "select m from Member m where m.username = :username",
                        Member.class
                )
                .setParameter("username", "member1")
                .getSingleResult();

        assertThat(resultList.getUsername()).isEqualTo("member1");
    }

    /**
     * @apiNote
     * 식별자를 m으로 표시함. QMember 생성시에 Table의 식별자를 설정할 수 있음.
     * select m
     *   from Member m
     *  where m.username = 'member'1
     */
    @Test
    @DisplayName("QType 에 대한 소개 및 Table 식별자 지정하기")
    void startQueryDSL(){
        QMember qMember = new QMember("m");  // 어떤 QMember 인지를 구분하는 m값 지정

        // Prepare Statement 파라미터 바인딩을 사용함.
        Member member = jpaQueryFactory
                .select(qMember)
                .from(qMember)
                .where(qMember.username.eq("member1"))
                .fetchOne();

        assert member != null;
        assertThat(member.getUsername()).isEqualTo("member1");
    }

    @Test
    @DisplayName("QType 내의 Entity 객체를 사용할 경우, 임의의 식별자가 정의됨.")
    void testQType() {

        QMember member = QMember.member;

        Member findMember = jpaQueryFactory
                .select(member)
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        assert findMember != null; // https://offbyone.tistory.com/294
        System.out.println("findMember.getUsername() = " + findMember.getUsername());

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    @DisplayName("Alias 를 직접 정의하는 경우, 같은 테이블의 조인해야하는 경우, 식별자를 통한 테이블 분리 가능함.")
    void testQAlias(){
        QMember qMember = new QMember("memb");

        Member findMember = jpaQueryFactory.select(qMember)
                .from(qMember)
                .where(qMember.username.eq("member1"))
                .fetchOne();

        assertThat(Objects.requireNonNull(findMember).getUsername()).isEqualTo("member1");
    }

    @Test
    @DisplayName("Where 절을 정의하는 and 에 의해 정의하는 방식")
    void testSelectFrom(){

        Member findMember = jpaQueryFactory
                .selectFrom(member)
                .where(member.username.eq("member1").and(member.age.eq(10)))
                .fetchOne();

        /**
         * select *
         *   from MEMBER
         *  where username = 'member1'
         *    and age = 10
         */

        assertThat(findMember.getUsername()).isEqualTo("member1");

        /**
         * select member1
         *   from Member member1
         *  where member1.username = 'member1'1 and member1.age = 102
         */
    }

    @Test
    @DisplayName("Where 절을 정의하는 콤마의 의해 정의하는 방식")
    void testSelectFromSecond(){

        Member findMember = jpaQueryFactory
                .select(member)
                .from(member)
                .where(  //and 인 경우, 콤마로 대체 가능
                        member.username.eq("member1"),
                        member.age.eq(10)
                )
                .fetchOne();

        assertThat(Objects.requireNonNull(findMember).getUsername()).isEqualTo("member1");

        /**
         * select member1
         *   from Member member1
         *  where member1.username = 'member1'
         *    and member1.age = 102
         */
    }

    @Test
    @DisplayName("Result 를 fetch 를 통해서 가져오는 방식")
    void testResultWithFetchType(){


        List<Member> fetchListMember =
                jpaQueryFactory
                        .selectFrom(member)
                        .fetch();

        int rowCount = fetchListMember.size();

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
    void testResultWithOrder(){

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

    /**
     * @apiNote
     * offset - 시작지점
     * limit - 페이지 사이즈
     *
     * select p from Post p offset 3 limit 10
     * 이라고 한다면 3번째 로우에서부터 10개의 로우를 가져온다는 것이다.
     * 주의할점은 Querydsl 에서 offset 은 0부터 시작한다.
     */
    @Test
    @DisplayName("페이징")
    void testPaging(){
        List<Member> fetch = jpaQueryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1)
                .limit(2)
                .fetch();

        assertThat(fetch).hasSize(2);
    }

    @Test
    @DisplayName("집합 처리")
    void testAggregation(){
        List<Tuple> fetch = jpaQueryFactory
                .select(member.count(),
                        member.age.sum(),
                        member.age.avg(),
                        member.age.max(),
                        member.age.min()
                ).from(member)
                .fetch();

        Tuple tuple = fetch.get(0);

        /***
         * select count(member1),
         *        sum(member1.age),
         *        avg(member1.age),
         *        max(member1.age),
         *        min(member1.age)
         *   from Member member1
         */

        assertThat(tuple.get(member.count())).isEqualTo(4);
    }

    @Test
    @DisplayName("Group By - Aggregations")
    void testGroupBy(){
        List<Tuple> fetch = jpaQueryFactory
                .select(team.name,
                        member.age.avg())
                .from(member)
                .join(member.team, team)
                .groupBy(team.name)
                .fetch();


        List<Tuple> fetchMember = jpaQueryFactory
                .select(team.name, member.age.avg())
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
    @DisplayName("Case When Statement - Way 1")
    void testCaseWhenOtherwise(){
        List<String> fetch = jpaQueryFactory.select(member
                        .age
                        .when(10).then("열살")
                        .when(20).then("스무살")
                        .otherwise("기타"))
                .from(member)
                .fetch();

        for (String value: fetch) {
            System.out.println("value = " + value);
        }
    }

    @Test
    @DisplayName("Case When Statement - Way 2")
    void complexCase(){
        List<String> values = jpaQueryFactory
                .select(
                        new CaseBuilder()
                                .when(member.age.between(0, 20)).then("0~20살")
                                .when(member.age.between(21, 30)).then("21~30살")
                                .otherwise("기타"))
                .from(member)
                .fetch();

        for (String value : values) {
            System.out.println("value = " + value);
        }
    }

    @Test
    @DisplayName("Select 내에 임의의 상수 지정하기")
    void testPlusValue(){
        List<Tuple> fetch = jpaQueryFactory
                .select(member.username, Expressions.constant("A"))
                .from(member)
                .fetch();

        for (Tuple value: fetch) {
            System.out.println("value = " + value);
        }
    }

    @Test
    @DisplayName("문자열 합치기")
    void testConcat(){
        List<String> fetch = jpaQueryFactory
                .select(member.username.concat("_").concat(member.age.stringValue()))
                .from(member)
                .where(member.username.eq("member1"))
                .fetch();

        for (String value :
                fetch) {
            System.out.println("value = " + value);
        }
    }
}
