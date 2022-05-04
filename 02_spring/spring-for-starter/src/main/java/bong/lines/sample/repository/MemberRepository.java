package bong.lines.sample.repository;

import bong.lines.sample.entity.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Long save(Member member){
        entityManager.persist(member);
        return member.getId();
    }

    public Member find(Long id){
        Member member = entityManager.find(Member.class, id);
        return member;
    }
}
