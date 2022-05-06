package bong.lines.sample;

import bong.lines.sample.model.dto.MemberDto;
import bong.lines.sample.model.dto.UserDto;
import bong.lines.sample.model.entity.Member;
import bong.lines.sample.model.entity.Team;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
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
        List<MemberDto> result = entityManager.createQuery("select new bong.lines.sample.model.dto.MemberDto(m.username, m.age) from Member m",
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
}
