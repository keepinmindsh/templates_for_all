# Join, Fetch Join 

### 일반 조인 ( Join )

- Fetch Join 과 달리 연관관계를 맺고 가져오는 Entity에 대해서 Join을 걸더라도 Select 시점의 영속화되는 Entity는 
  조회하는 대상이 되는 Entity에 대해서만 영속화 과장을 가지게 됨. 

```java

class NormalJoin{
    
    @Test
    @DisplayName("패치 조인 미적용 케이스")
    void nofetchJoin(){
        entityManager.flush();
        entityManager.clear();

        // 연관관계에 있는 Entity를 로딩하기 위해서 쿼리가 2번 실행됨. 
        Member memberOne = jpaQueryFactory
                .selectFrom(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(memberOne.getTeam());

        assertThat(loaded).as("패치 조인 미적용").isFalse();
    }
    
}

```

```shell

 /* select
        member1 
    from
        Member member1 
    where
        member1.username = ?1 */ 
select
    member0_.member_id as member_i1_1_,
    member0_.age as age2_1_,
    member0_.team_id as team_id4_1_,
    member0_.username as username3_1_ 
from
    member member0_ 
where
    member0_.username=?
    

select
      team0_.team_id as team_id1_2_0_,
      team0_.name as name2_2_0_ 
  from
      team team0_ 
  where
      team0_.team_id=?  

            
```

### 패치 조인 ( Fetch Join )

- 조회의 주체가 되는 Entity 이외에 Fetch Join이 걸린 연관 Entity도 함께 영속화한다. 
- 이 경우 만약 Entity의 연관관계 매핑시 FetchType을 Lazy인 경우에도 FetchJoin 에 의해서 영속화 되기 때문에 추가로 쿼리가 실행되지 않은채로 N+1 문제가 해결됨.

```java
class FetchJoin {
  @Test
  @DisplayName("패치 조인 적용 케이스")
  void fetchJoin(){
    entityManager.flush();
    entityManager.clear();

    Member memberOne = jpaQueryFactory
            .selectFrom(member)
            // leftJoin, Join 뒤에 fetchJoin을 붙이면 fetchJoin이 적용된다.
            .join(member.team, team)
            .fetchJoin()
            .where(member.username.eq("member1"))
            .fetchOne();

    boolean loaded = emf.getPersistenceUnitUtil().isLoaded(memberOne.getTeam());

    assertThat(loaded).as("패치 조인 적용").isTrue();
  }
}
```

```shell

/* select
        member1 
    from
        Member member1   
    inner join
        fetch member1.team as team 
    where
        member1.username = ?1 */ 
      
select
    member0_.member_id as member_i1_1_0_,
    team1_.team_id as team_id1_2_1_,
    member0_.age as age2_1_0_,
    member0_.team_id as team_id4_1_0_,
    member0_.username as username3_1_0_,
    team1_.name as name2_2_1_ 
from
    member member0_ 
inner join
    team team1_ 
        on member0_.team_id=team1_.team_id 
where
    member0_.username=?

```

### 데이터 로딩 전략 ( Lazy , EAGER) vs FetchJoin

데이터 조회 전략을 FetchType을 Lazy로 설정하더라도 연관 관계가 있는 레퍼런스를 참조하는 경우에 추가적인 조회 쿼리가 실행된다.
따라서 해당 부분은 데이터 로딩(조회) 전략을 이용해서 해결할 수 있는 문제는 아니다.

### 그럼 무엇을 사용해야 하는가?

Join, FetchJoin 에서 대상으로 도메인에 따른 요구사항에 따라 다를 수 있다!   
FetchJoin은 불필요한 영속성을 로드하는 것이 될 수 있음. 

따라서 Entity 의 데이터 조회 전략은 Lazy 로 하되 QueryDSL 를 도입하여 요구사항 및 프로세스에 따라 Fetch Join 을 적절히 활용하자. 