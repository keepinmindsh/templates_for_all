package bong.lines.sample;

import bong.lines.sample.model.entity.Member;
import bong.lines.sample.model.entity.QMember;
import bong.lines.sample.model.entity.Team;
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

@SpringBootTest
@Transactional
public class QueryBulkTest {


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
    @DisplayName("벌크 연산에 대한 잘못된 예시")
//    @Commit
    public void bulkUpdatWrongCase(){
        // bulk 연산은 영속성 컨텍스트를 무시하고 바로 DB에 반영한다.
        long count = jpaQueryFactory
                .update(member)
                .set(member.username, "비회원")
                .where(member.age.lt(28))
                .execute();

        // JPA에서의 우선 순위는 영속성 컨텍스트임. DB에서 가져온 값을 조회되지 않음.
        // 해당 코드에서의 문제 점은 bulk 연산이 일어난 이후 select 쿼리는 영속성 컨텍스트의 캐쉬된 값을 조회함.
        List<Member> fetch = jpaQueryFactory.select(member)
                .from(member)
                .fetch();

        for (Member fetch1 : fetch) {
            System.out.println("fetch1.getUsername() = " + fetch1.getUsername());
        }
    }


    @Test
    @DisplayName("벌크 연산에 대한 잘된 예시")
//    @Commit
    public void bulkUpdatRightCase(){
        long count = jpaQueryFactory
                .update(member)
                .set(member.username, "비회원")
                .where(member.age.lt(28))
                .execute();

        // 벌크 연산 이후에는 반드시 영속성 컨텍스트를 초기화 해야한다.
        entityManager.flush();
        entityManager.clear();

        List<Member> fetch = jpaQueryFactory.select(member)
                .from(member)
                .fetch();

        for (Member fetch1 : fetch) {
            System.out.println("fetch1.getUsername() = " + fetch1.getUsername());
        }
    }

    @Test
    @DisplayName("추가, 곱하기 연산")
    public void bulkAdd(){
        jpaQueryFactory.update(member)
                .set(member.age, member.age.add(1))
                .execute();


        jpaQueryFactory.update(member)
                .set(member.age, member.age.multiply(1))
                .execute();
    }

    @Test
    @DisplayName("Bulk 삭제")
    public void bulkDelete(){
        jpaQueryFactory
                .delete(member)
                .where(member.age.gt(18))
                .execute();
    }
}
