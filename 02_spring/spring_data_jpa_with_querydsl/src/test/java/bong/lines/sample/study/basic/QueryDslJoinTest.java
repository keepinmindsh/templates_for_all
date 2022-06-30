package bong.lines.sample.study.basic;

import bong.lines.sample.model.entity.Member;
import bong.lines.sample.model.entity.Team;
import com.querydsl.core.Tuple;
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

import static bong.lines.sample.model.entity.QMember.member;
import static bong.lines.sample.model.entity.QTeam.team;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Commit
@Transactional
public class QueryDslJoinTest {

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


    /**
     * @apiNote
     *  - https://superman28.tistory.com/23
     */
    @Test
    @DisplayName("Join 테스트")
    void testJoin(){
        /**
         * select member1
         *   from Member member1
         *  inner join member1.team as team
         *  where team.name = 'teamA'
         */
        List<Member> teamList = jpaQueryFactory
                .selectFrom(member)
                .join(member.team, team)
                .where(team.name.eq("teamA"))
                .fetch();

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
    void theta_join(){

        // member의 모든 행과 team의 모든 행에 대해서 조인을 한 뒤 조건을 검색함.

        /* select member1
             from Member member1, Team team
            where member1.username = team.name
         */

        /*
        * Optimizer
        *   select -> table full access scan -> 시스템의 성능 저하
        * primary - index
        * constraint , table key, index
        * index -> 스터디
         */

        List<Member> fetch = jpaQueryFactory.selectFrom(member)
                .select(member)
                .from(member, team)
                .where(member.username.eq(team.name))
                .fetch();

        assertThat(fetch)
                .extracting("username")
                .containsExactly("teamA", "teamB");
    }

    @Test
    @DisplayName("Join 에 대해서 On 사용")
    void testJoinOn(){
        /**
         * select
         *         member1,
         *         team
         *     from
         *         Member member1
         *     left join
         *         member1.team as team with team.name = ?1
         */
        List<Tuple> teamA = jpaQueryFactory
                .select(member, team)
                .from(member)
                .leftJoin(member.team, team)
                .on(team.name.eq("teamA"))
                .fetch();

        /**
         * select member1, team
         *   from Member member
         *   left join member1.team as team with team.name = 'teamA'1
         */
        for (Tuple value: teamA) {
            System.out.println("value = " + value);
        }

        // Join 절의 경우, join , where를 사용하는 방식과 동일함.
        // leftOutJoin이 아닌 경우에는 아래의 두가지 모두 사용이 가능하다.
        List<Tuple> teamJoin = jpaQueryFactory
                .select(member, team)
                .from(member)
                .join(member.team, team)
                .on(team.name.eq("teamA"))
                .fetch();

        List<Tuple> teamWhereLikeJoinOn = jpaQueryFactory
                .select(member, team)
                .from(member)
                .join(member.team, team)
                .where(team.name.eq("teamA"))
                .fetch();
    }

    @Test
    @DisplayName("연관 관계가 없는 Field에 대한 조건절 조인 처리")
    void testJoinOnNoRelations(){
        List<Tuple> fetch = jpaQueryFactory
                .select(member, team)
                .from(member)
                .leftJoin(member.team, team)
                .on(member.username.eq(team.name))
                .fetch();

        /**
         * select
         *         member1,
         *         team
         *     from
         *         Member member1
         *     left join
         *         Team team with member1.username = team.name
         */
        for (Tuple m :
                fetch) {
            System.out.println("m = " + m);
        }
    }

    @Test
    @DisplayName("Case When Statement - Way 1")
    void testCaseWhenOtherwise(){

        /* select case when member1.age = 101 then '열살'2
                       when member1.age = 203 then '스무살'4
                       else '기타' end
         */

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
}
