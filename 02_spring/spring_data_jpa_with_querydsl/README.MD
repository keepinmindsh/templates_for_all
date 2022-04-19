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

### References 

> [Gradle Annotation Process with Query DSL - 버전별 처리](http://honeymon.io/tech/2020/07/09/gradle-annotation-processor-with-querydsl.html)