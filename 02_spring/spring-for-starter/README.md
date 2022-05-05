### Thymleaf 세팅하기 


###  Mac OS에서 port로 pid 찾기

```

sudo lsof -nP -iTCP:9090 | grep LISTEN

```

### devtools 세팅하기 [ Recompile / Refresh with restart ]

- gradle 세팅 

```groovy

implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'

implementation 'org.springframework.boot:spring-boot-devtools'

```

- build > Recompile : 재시작 없이 재 컴파일하여 반영할 수 있음. 

### H2 Database 설정

### JPA Log Query Parameter 보기 

- https://github.com/gavlyukovskiy/spring-boot-data-source-decorator
  - 운영 배포 시에는 반드시 고려해서 적용 필요

### 엔티티 클래스 개발
- getter를 용인하는 것은 상관이 없지만, setter는 꼭 필요한 경우만 사용하는 것을 추천함. 
- 값 타입은 Immutable한 객체로 생성하는 것이 최선임.

### 엔티티 설계시 주의점 
- 엔티티는 가급적 Setter를 사용하지 말자. 
- 모든 연관 관계는 지연 로딩으로 설정.
  - 연관된 Entity 함께 DB에서 조회해야하면 fetch join을 사용한다. 
- 컬렉션은 필드에서 초기화 하자. 
  - 컬렉션을 필드에서 바로 초기화하는 것이 안전하다.
    - 초기화에 대한 고민이 불필요함. 
- 테이블, 컬럼명 생성 전략  
  - SpringPhysicalNamingStrategy


> https://dololak.tistory.com/465
