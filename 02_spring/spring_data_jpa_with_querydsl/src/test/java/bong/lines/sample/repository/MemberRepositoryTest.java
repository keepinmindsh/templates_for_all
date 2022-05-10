package bong.lines.sample.repository;

import bong.lines.sample.model.dto.MemberSearchCondition;
import bong.lines.sample.model.dto.MemberTeamDto;
import bong.lines.sample.model.entity.Member;
import bong.lines.sample.model.entity.QMember;
import bong.lines.sample.model.entity.Team;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static bong.lines.sample.model.entity.QMember.member;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired MemberRepository memberRepository;

    @BeforeEach
    public void before(){
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
    @DisplayName("Member Repository For JPA Repository")
    public void basicTest(){
        Member member = new Member("member1", 10);
        memberRepository.save(member);

        // Spring Data JPA가 모두 제공해주고 있음
        Member findMember = memberRepository.findById(member.getId()).get();
        assertThat(findMember).isEqualTo(member);

        // Spring Data JPA가 모두 제공해주고 있음
        List<Member> result1 = memberRepository.findAll();
        assertThat(result1).containsExactly(member);

        List<Member> result2 = memberRepository.findByUsername("member1");
        assertThat(result2).containsExactly(member);
    }

    @Test
    @DisplayName("동적 쿼리 - Where 사용")
    public void SearchWhereTest(){
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

        MemberSearchCondition memberSearchCondition = new MemberSearchCondition();
        memberSearchCondition.setAgeGoe(35);
        memberSearchCondition.setAgeLoe(40);
        memberSearchCondition.setTeamName("teamB");

        /**
         * Custom을 활용한 방식의 Repository Custom을 구성해야한다.
         */
        List<MemberTeamDto> memberTeamDtos = memberRepository.search(memberSearchCondition);

        assertThat(memberTeamDtos).extracting("username").containsExactly("member4");

    }
    
    @Test
    @DisplayName("")
    public void queryDslPredicateExecutor(){
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

        /**
         * Predicate를 바로 활용은 가능하지만, 조인이 되지 않음. 이로 인해서 Root Entity에 대해서만 처리해야함.
         * Client가 QueryDSL에 의존해야한다. 서비스 클래스가 QueryDSL에 구현 기술을 의존해야한다.
         * Repository의 추상화 단계를 깨는 QueryDSL을 사용하기 때문에 의존도가 올라간다.
         * 실무에서 권장하지 않음
         */
        Iterable<Member> findMember = memberRepository.findAll(member.age.between(10, 40).and(member.username.eq("member1")));

        for (Member memberItem : findMember) {
            System.out.println("member2 = " + memberItem);
        }

    }
}