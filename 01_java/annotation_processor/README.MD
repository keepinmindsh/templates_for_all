# Custom Annotation Process 만들기 

- https://medium.com/@emmasuzuki/annotation-processor-101-your-first-custom-annotation-a3db9ae48046

# Annotation Processor 생성시 Meta Inf 파일에 대한 자동 생성 처리 

Auto Service의 역할에 주목할 것

```groovy

dependencies {

    annotationProcessor 'com.google.auto.service:auto-service:1.0.1'

    // https://mvnrepository.com/artifact/com.google.auto.service/auto-service
    implementation 'com.google.auto.service:auto-service:1.0.1'


    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
}

```

# Java Service Provider 

확장 포인트를 제공한다. 

- https://itnext.io/java-service-provider-interface-understanding-it-via-code-30e1dd45a091
