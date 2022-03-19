### QueryDSL with Lombok 

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