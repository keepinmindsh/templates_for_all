package bong.lines.sample.repository;

import bong.lines.sample.model.entity.Member;
import bong.lines.sample.model.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

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
                .selectFrom(QMember.member)
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
                .selectFrom(QMember.member)
                .where(QMember.member.username.eq(username))
                .fetch();
    }
}

