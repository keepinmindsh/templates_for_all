# Spring Boot Test 

- [Spring Boot Test](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.testing)
  - JUnit 5: The de-facto standard for unit testing Java applications.
  - Spring Test & Spring Boot Test: Utilities and integration test support for Spring Boot applications.
  - AssertJ: A fluent assertion library.
  - Hamcrest: A library of matcher objects (also known as constraints or predicates).
  - Mockito: A Java mocking framework.
  - JSONassert: An assertion library for JSON.
  - JsonPath: XPath for JSON.

- Spring Boot Test Dependency 

```xml
<dependency> 
  <groupId>org.springframework.boot</groupId> 
  <artifactId>spring-boot-starter-test</artifactId> 
  <scope>test</scope>
</dependency>

```

```groovy

testImplementation 'org.springframework.boot:spring-boot-starter-test'

```

### @SpringBootTest

1) 개요
- 실제 운영 환경에서 사용될 클래스들을 통합하여 테스트 한다.
- 단위 테스트와 같이 기능 검증을 위한 것이 아니라 spring framework에서 전체적으로 플로우가 제대로 동작하는지 검증하기 위해 사용 한다.

2) 장점
- 애플리케이션의 설정, 모든 Bean을 모두 로드하기 때문에 운영환경과 가장 유사한 테스트가 가능하다.
- 전체적인 Flow를 쉽게 테스트 가능하다.

3) 단점
- 애플리케이션의 설정, 모든 Bean을 모두 로드하기 때문에 시간이 오래걸리고 무겁다.
- 테스트 단위가 크기 때문에 디버깅이 어려운 편이다


# Thymleaf 세팅하기 


#  Mac OS에서 port로 pid 찾기

```

sudo lsof -nP -iTCP:9090 | grep LISTEN

```

# devtools 세팅하기 [ Recompile / Refresh with restart ]

- gradle 세팅 

```groovy

implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'

implementation 'org.springframework.boot:spring-boot-devtools'

```

- build > Recompile : 재시작 없이 재 컴파일하여 반영할 수 있음. 

#H2 Database 설정

# JPA Log Query Parameter 보기 

- https://github.com/gavlyukovskiy/spring-boot-data-source-decorator
  - 운영 배포 시에는 반드시 고려해서 적용 필요

# 엔티티 클래스 개발
- getter를 용인하는 것은 상관이 없지만, setter는 꼭 필요한 경우만 사용하는 것을 추천함. 
- 값 타입은 Immutable한 객체로 생성하는 것이 최선임.

# 엔티티 설계시 주의점 
- 엔티티는 가급적 Setter를 사용하지 말자. 
- 모든 연관 관계는 지연 로딩으로 설정.
  - 연관된 Entity 함께 DB에서 조회해야하면 fetch join을 사용한다. 
- 컬렉션은 필드에서 초기화 하자. 
  - 컬렉션을 필드에서 바로 초기화하는 것이 안전하다.
    - 초기화에 대한 고민이 불필요함. 
- 테이블, 컬럼명 생성 전략  
  - SpringPhysicalNamingStrategy


> https://dololak.tistory.com/465
