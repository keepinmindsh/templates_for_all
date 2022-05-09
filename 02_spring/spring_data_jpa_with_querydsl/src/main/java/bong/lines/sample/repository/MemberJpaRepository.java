package bong.lines.sample.repository;

import bong.lines.sample.model.dto.MemberSearchCondition;
import bong.lines.sample.model.dto.MemberTeamDto;
import bong.lines.sample.model.dto.QMemberTeamDto;
import bong.lines.sample.model.entity.Member;
import bong.lines.sample.model.entity.QMember;
import bong.lines.sample.model.entity.QTeam;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static bong.lines.sample.model.entity.QMember.member;
import static bong.lines.sample.model.entity.QTeam.team;
import static org.springframework.util.StringUtils.*;

@Repository
public class MemberJpaRepository {

    // EntityManager와 JPAQueryFactory의 동시성은 모두 EntityManager에 의해서 동작하는데,
    // Transaction 단위로 Thread를 분리해서 사용할 수 있게 되어 있다.
    private final EntityManager entityManager;
    private final JPAQueryFactory jpaQueryFactory;

    public MemberJpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    public void save(Member member){
        entityManager.persist(member);
    }

    public Optional<Member> findById(Long id){
        Member member = entityManager.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public List<Member> findAll(){
        return entityManager.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findAll_QeuryDSL(){
        return jpaQueryFactory
                .selectFrom(member)
                .fetch();
    }

    public List<Member> findByUsername(String username){
        return entityManager.createQuery("select m from Member m where m.username= :username", Member.class)
                .setParameter("username", username)
                .getResultList();
    }

    // 컴파일 시점에 에러가 나기 때문에 사전 코드 체크가 가능함.
    public List<Member> findByUsername_Querydsl(String username){
        return jpaQueryFactory
                .selectFrom(member)
                .where(member.username.eq(username))
                .fetch();
    }

    public List<MemberTeamDto> searchByBuilder(MemberSearchCondition condition) {

        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(condition.getUsername())){
            builder.and(member.username.eq(condition.getUsername()));
        }

        if(hasText(condition.getTeamName())){
            builder.and(team.name.eq(condition.getTeamName()));
        }

        if(condition.getAgeGoe()  != null){
            builder.and(member.age.goe(condition.getAgeGoe()));
        }

        if(condition.getAgeLoe() != null){
            builder.and(member.age.loe(condition.getAgeLoe()));
        }

        /**
         * 조건문이 모두 빠지는 경우에는 전체 데이터를 다 가져오기 때문에 필수값에 대한 유효성 검사를 반드시 체크해야한다.
         * - 페이징 쿼리 적용
         */

        return jpaQueryFactory
                .select(new QMemberTeamDto(
                        member.id.as("memberId"),
                        member.username,
                        member.age,
                        team.id.as("teamId"),
                        team.name.as("teamName")))
                .from(member)
                .leftJoin(member.team, team)
                .where(builder)
                .fetch();
    }

    public List<MemberTeamDto> searchWithWhere(MemberSearchCondition condition){

        /**
         * Boolean Builder에 비해서 훨씬 깔끔한 쿼리가 작성이 가능함.
         *  - 메소드에 대한 재사용이 가능함.
         */
        return jpaQueryFactory
                .select(new QMemberTeamDto(
                        member.id.as("memberId"),
                        member.username,
                        member.age,
                        team.id.as("teamId"),
                        team.name.as("teamName")))
                .from(member)
                .leftJoin(member.team, team)
                .where(
                        userNameEq(condition.getUsername()),
                        teamNameEq(condition.getTeamName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe())
                )
                .fetch();
    }

    private BooleanExpression ageLoe(Integer ageLoe) {
        return ageLoe != null ? member.age.loe(ageLoe) : null;
    }

    private BooleanExpression ageGoe(Integer ageGoe) {
        return ageGoe != null ? member.age.goe(ageGoe) : null;
    }

    private BooleanExpression teamNameEq(String teamName) {
        return hasText(teamName) ? team.name.eq(teamName) : null;
    }

    private BooleanExpression userNameEq(String username) {
        return hasText(username) ? member.username.eq(username) : null;
    }
}

