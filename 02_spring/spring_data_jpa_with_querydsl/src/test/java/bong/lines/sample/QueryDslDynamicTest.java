package bong.lines.sample;

import bong.lines.sample.model.dto.MemberDto;
import bong.lines.sample.model.dto.QMemberDto;
import bong.lines.sample.model.dto.UserDto;
import bong.lines.sample.model.entity.Member;
import bong.lines.sample.model.entity.QMember;
import bong.lines.sample.model.entity.Team;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Assertions;
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
public class QueryDslDynamicTest {

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
        String usernameparameter = "member1";
        Integer age = 10;

        List<Member> result = searchMember1(usernameparameter, age);

        assertThat(result.size()).isEqualTo(1);
    }

    private List<Member> searchMember1(String usernameparameter, Integer age) {

        /**
         * select member1
         *   from Member member1
         *  where member1.username = 'member1'1 and member1.age = 102
         */
        BooleanBuilder builder = new BooleanBuilder();

        if(usernameparameter != null){
            builder.and(member.username.eq(usernameparameter));
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
        String usernameparameter = "member1";
        Integer age = 10;

        // 실무에서 좀더 이해도 있게 사용할 수 있는 방식
        List<Member> result = searchMember2(usernameparameter, age);

        assertThat(result.size()).isEqualTo(1);
    }

    private List<Member> searchMember2(String usernameparameter, Integer age) {
        return jpaQueryFactory
                .selectFrom(member)
                .where(usernameEq(usernameparameter), ageEq(age))
                .fetch();
    }

    private List<Member> searchMember3(String usernameparameter, Integer age) {
        return jpaQueryFactory
                .selectFrom(member)
                .where(allEq(usernameparameter, age)) // 이와 같은 방식으로 작성할 수 있음
                .fetch();
    }

    private BooleanExpression ageEq(Integer age) {
        return age == null ? null : member.age.eq(age);
    }

    private BooleanExpression usernameEq(String usernameparameter) {
        if(usernameparameter == null){
            return null;
        }

        return member.username.eq(usernameparameter);
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
