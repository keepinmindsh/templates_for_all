package bong.lines.sample.study.dynamiccondition;

import bong.lines.sample.model.entity.Member;
import bong.lines.sample.model.entity.Team;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static bong.lines.sample.model.entity.QMember.member;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class QueryDslDynamicConditionTest {

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
    @DisplayName("Dynamic Query Boolean Builder")
    public void dynamicQueryBooleanBuilder(){
        String userName = "member1";
        Integer age = 10;

        // Boolean Builder 를 활용한 Dynamic Query 적용
        List<Member> result = searchMemberWithBooleanBuilder(userName, age);

        assertThat(result.size()).isEqualTo(1);
    }

    private List<Member> searchMemberWithBooleanBuilder(String userName, Integer age) {

        /**
         * select member1
         *   from Member member1
         *  where member1.username = 'member1'1 and member1.age = 102
         */
        BooleanBuilder builder = new BooleanBuilder();

        if(userName != null){
            builder.and(member.username.eq(userName));
        }

        if(age != null){
            builder.and(member.age.eq(age));
        }

        return jpaQueryFactory.selectFrom(member)
                .where(builder)
                .fetch();
    }

    @Test
    @DisplayName("Dynamic Query with where null or value by function condition")
    public void dynamicQueryWithWhere(){
        String userName = "member1";
        Integer age = null;

        List<Member> result1 = searchMember2(userName, age);

        assertThat(result1.size()).isEqualTo(1);

        List<Member> result2 = searchMember3(userName, age);
        assertThat(result2.size()).isEqualTo(1);
    }

    private List<Member> searchMember2(String userName, Integer age) {
        return jpaQueryFactory
                .selectFrom(member)
                .where(usernameEq(userName),ageEq(age))
                .fetch();
    }

    private List<Member> searchMember3(String userName, Integer age) {
        return jpaQueryFactory
                .selectFrom(member)
                .where(allEq(userName, age)) // 이와 같은 방식으로 작성할 수 있음
                .fetch();
    }

    private BooleanExpression ageEq(Integer age) {
        return age == null ? null : member.age.eq(age);
    }

    private BooleanExpression usernameEq(String userName) {
        if(userName == null){
            return null;
        }

        return member.username.eq(userName);
    }

    /**
     * 여러가지 값의 조합으로 비교 메소드들을 우리가 원하는 방식으로 작성 가능.
     * 어떤 조건, 어떤 조건일 경우 이것이다 라는 비교 메서드 작성
     * 또는 메소드 자체에 대한 재활용이 가능함.
     * 자유로운 컴포지션이 가능하다.
     * @param usernameCond
     * @param ageCond
     * @return
     */
    private Predicate allEq(String usernameCond, Integer ageCond){
        return usernameEq(usernameCond).and(ageEq(ageCond));
    }
}
