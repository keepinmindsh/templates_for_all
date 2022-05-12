package bong.lines.sample;

import bong.lines.sample.model.dto.MemberDto;
import bong.lines.sample.model.dto.QMemberDto;
import bong.lines.sample.model.dto.UserDto;
import bong.lines.sample.model.entity.Member;
import bong.lines.sample.model.entity.QMember;
import bong.lines.sample.model.entity.Team;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.h2.engine.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static bong.lines.sample.model.entity.QMember.member;

@SpringBootTest
@Transactional
public class QueryDslAdvancedTest {

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
    public void simpleProjection(){
        List<String> strings = jpaQueryFactory
                .select(member.username)  // select 구문에 나열 되는 것을 Projection 이라고 함.
                .from(member)
                .fetch();

        System.out.println("strings = " + strings);

        for (String user:strings
             ) {
            System.out.println("user = " + user);
        }
    }

    @Test
    public void tupleProjection(){
        // Tuple은 Repository 계층 간에서만 사용할 것
        List<Tuple> tuples = jpaQueryFactory
                .select(member.username, member.age)
                .from(member)
                .fetch();

        for (Tuple item: tuples
             ) {
            String username = item.get(member.username);
            System.out.println("username = " + username);
            int age = item.get(member.age);
            System.out.println("age = " + age);
        }
    }

    @Test
    public void dtoProjection(){
        List<MemberDto> result =
                entityManager.createQuery("select new bong.lines.sample.model.dto.MemberDto(m.username, m.age)  from Member m",
                MemberDto.class).getResultList();

        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    public void dtoWithQueryDSLProjection(){

        List<MemberDto> memberDtos = jpaQueryFactory
                .select(Projections.bean(MemberDto.class,
                        member.username,
                        member.age
                ))
                .from(member)
                .fetch();

        for (MemberDto memberDto : memberDtos) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test // Field에 직접적으로 값을 바인딩하는 방식
    public void dtoWithQueryDSLProjectionWithField() {

        List<MemberDto> memberDtos = jpaQueryFactory
                .select(Projections.fields(MemberDto.class,
                        member.username,
                        member.age
                ))
                .from(member)
                .fetch();

        for (MemberDto memberDto : memberDtos) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test // 생성자를 이용하는 방식으로 실제 생성되는 생성자의 타입과 맞춰야함.
    public void dtoWithQueryDSLProjectionWithConstructor() {

        List<MemberDto> memberDtos = jpaQueryFactory
                .select(Projections.constructor(MemberDto.class,
                        member.username,
                        member.age
                ))
                .from(member)
                .fetch();

        for (MemberDto memberDto : memberDtos) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test // Field에 직접적으로 값을 바인딩하는 방식
    public void userDtoWithQueryDSLProjectionWithField() {

        List<UserDto> userDtos = jpaQueryFactory
                .select(Projections.fields(UserDto.class,
                        member.username.as("name"),
                        member.age
                ))
                .from(member)
                .fetch();

        for (UserDto memberDto : userDtos) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    @DisplayName("이름이 다를 때의 해결 방안")
    public void userDTOWithExpressions(){
        /**
         * elect member1.username as name, (select max(MemberSub.age)
         * from Member MemberSub) as age
         * from Member member1
         */
        QMember memberSub = new QMember("MemberSub");
        List<UserDto> resulst = jpaQueryFactory
                .select(Projections.fields(UserDto.class,
                        member.username.as("name"),
                        //SubQuery의 처리 방식
                        ExpressionUtils.as(JPAExpressions.select(
                                memberSub.age.max()
                                ).from(memberSub), "age")
                        ))
                .from(member)
                .fetch();

        for (UserDto userDto : resulst) {
            System.out.println("userDto = " + userDto.getName());
        }
    }

    @Test // 생성자를 이용하는 방식2
    public void dtoWithQueryDSLProjectionWithConstructor2() {


        List<UserDto> memberDtos = jpaQueryFactory
                .select(Projections.constructor(UserDto.class,
                        member.username,
                        member.age
                ))
                .from(member)
                .fetch();

        for (UserDto memberDto : memberDtos) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    @DisplayName("Qeury Projection을 활용하는 방식")
    public void QueryProjectionTest(){
        List<MemberDto> fetch = jpaQueryFactory
                // Q 타입의 매핑 DTO를 정의해주며, 각 생성자 인자에 맞는 값을 초기화 할 수 있음.
                // Compile 오류를 잡을 수 있음
                // 실제 호출하면 생성자도 그대로 호출이 되는 구조임.
                // 이슈 : Qfile을 생성해야하는 것, 의존관계의 문제 - 멤버 DTO가 QueryDSL의 의존성을 가지게 되는 이슈
                // 다중 레이어에서 DTO를 사용할 경우, 흘러가는 DTO 가 순수한 객체가 아닌 것으로 흘러가게 됨.
                .select(new QMemberDto(member.username, member.age))
                .from(member)
                .fetch();

        for (MemberDto memberDto : fetch) {
            System.out.println("memberDto = " + memberDto);
        }
    }
}
