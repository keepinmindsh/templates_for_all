# JPQL vs QueryDSL

 - QueryDSL 이점
   - PrepareStatement 에 파라미터 바인딩 방식을 사용 
   - 컴파일 타임에 오류를 잡아준다.

```java
class QueryDSL{
  @Test
  public void startJPQL(){
    // member1 조회
    Member resultList = entityManager.createQuery("select m from Member m where m.username = :username", Member.class)
            .setParameter("username", "member1")
            .getSingleResult();

    assertThat(resultList.getUsername()).isEqualTo("member1");
  }

  @Test
  public void startQueryDSL(){
    // member1 조회
    JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

    QMember qMember = new QMember("m");  // 어떤 QMember인지를 구분하는 m값 지정

    // prepare Statement에 파라미터 바인딩을 사용함. 
    Member member = jpaQueryFactory
            .select(qMember)
            .from(qMember)
            .where(qMember.username.eq("member1"))  // 파라미터 바인딩 처리 
            .fetchOne();


    assert member != null;
    assertThat(member.getUsername()).isEqualTo("member1");
  }
}
```

# 도메인 모델 세팅하기 

- ToString 정의시 주의 할 점 
  - 연관관계로 세팅된 값에 대해서 ToString을 걸 경우 무한 Loop에 빠질 수 있다. 


# Query DSL Setting 하기 

##### h2 최신 버전에서는 JPA의 일부 기능이 동작하지 않는 것이 존재함. 

```groovy
implementation 'com.h2database:h2:1.4.200'
```

###Logging

##### 라이브러리 추가 방식 
  
```groovy

// Query를 쉽게 볼 수 있게 해주는 Util
// https://mvnrepository.com/artifact/com.github.gavlyukovskiy/p6spy-spring-boot-starter
implementation 'com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.8.0'

```

```shell

2022-04-16 19:59:59.228  INFO 19240 --- [           main] p6spy                                    : #1650106799228 | took 0ms | statement | connection 3| url jdbc:h2:tcp://localhost/~/querydsl
insert into item (id) values (?)
insert into item (id) values (1);

```

##### org.hibernate.type 설정 방식 

```yaml
logging:
  level:
    org.hibernate.SQL: debug
    # org.hibernate.type: trace - 이 값을 사용하면 바인딩 되는 파라미터 확인 가능

```

다만 운영상에 Query를 남기는 경우에는 성능 테스트는 반드시 필요함. 

# QueryDSL with Lombok 

> [QueryDSL with Lombok](https://jaime-note.tistory.com/67)

- Query DSL Compile 

compileQueryDSL task를 통해서 Class Build 처리 

```shell

./gradlew compileQuerydsl

```

- Lombok, QueryDSL를 같이 사용할 수 있는 코드 세팅 

```groovy

buildscript {
    ext {
        queryDslVersion = "5.0.0"
    }
}

plugins {
    id 'org.springframework.boot' version '2.6.4'  // 2022-03-19 12:22 기준 Spring Version
    id 'io.spring.dependency-management' version '1.0.11.RELEASE'
    id "com.ewerk.gradle.plugins.querydsl" version "1.0.10"  // Lombok with QueryDSL
    id 'java'
}

group = 'bong.lines'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '11'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation "com.querydsl:querydsl-jpa:${queryDslVersion}" // Lombok with QueryDSL
    implementation "com.querydsl:querydsl-apt:${queryDslVersion}" // Lombok with QueryDSL
    compileOnly 'org.projectlombok:lombok' // Lombok with QueryDSL

    runtimeOnly 'com.h2database:h2'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

// Lombok with QueryDSL - START

def querydslDir = "$buildDir/generated/querydsl"

querydsl {
    jpa = true
    querydslSourcesDir = querydslDir
}
sourceSets {
    main.java.srcDir querydslDir
}
compileQuerydsl{
    options.annotationProcessorPath = configurations.querydsl
}
configurations {
    compileOnly {
        extendsFrom annotationProcessor
    }
    querydsl.extendsFrom compileClasspath
}
// Lombok with QueryDSL - END

test {
    useJUnitPlatform()
}

```

### QueryDSL - Select Query 

```java
class Sample {
    public static void main(String[] args) {
        member.username.isNotNull();

        member.age.in(10, 20);
        member.age.notIn(10, 20);
        member.age.between(10, 20);

        member.age.goe(30); // age >= 30 
        member.age.gt(20);  // age > 20
        member.age.loe(20); // age <= 2ß0
        member.age.lt(20);  // age < 20

        member.username.like("kim%"); // like 검색 
        member.username.contains("member"); // like '%member%' 검색 
        member.username.statwith("member"); // like 'member%' 검색 
                
    }
}
```

### QueryDSL - Fetch

- fetch()
- fetchOne()
- fetchFirst() 
- fetchResults() : deprecated
  - [QueryDSL에서 fetchResults()가 Deprecated 된 이유 ](https://velog.io/@nestour95/QueryDsl-fetchResults%EA%B0%80-deprecated-%EB%90%9C-%EC%9D%B4%EC%9C%A0)

### QueryDSL - Join 

조인의 기본 문법은 첫번째 파라미의 조인 대상을 지원하고, 두번째 파라미터에 별칭(alias)으로 사용할 Q 타입을 지정하면 된다. 
    
### References 

> [Gradle Annotation Process with Query DSL - 버전별 처리](http://honeymon.io/tech/2020/07/09/gradle-annotation-processor-with-querydsl.html)    
> 
> [Join, Index의 역할](https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=jyc8618&logNo=220214553092)  
> - http://haisins.epac.to/wordpress/?p=716     
> - https://gent.tistory.com/306  
