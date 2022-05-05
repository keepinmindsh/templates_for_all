package bong.lines.sample;

import bong.lines.sample.model.entity.Member;
import bong.lines.sample.model.entity.Team;
import com.querydsl.core.Tuple;
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
}
