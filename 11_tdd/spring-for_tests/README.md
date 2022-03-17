# TDD - Spring for Tests

### TDD

- TDD : Test Driven Development로 테스트가 개발을 주도한다.  
  - 보통 테스트의 경우 개발이 끝난 후에 하는 과정이라고 생각할 수 있는데, TDD의 경우 테스트를 먼저 만들고 테스트를 통과하기 위한 행들이 모두 개발을 주도하는 것을 목표로 한다. 

### BDD

- BDD : Behavior Driven Development 
  - BDD의 모든 근간은 TDD에서 착안되었기 때문에 TDD가 추가하는 가치와 크게 다르지 않다. 

### @WebMvcTest

@WebMvcTest를 이용한 테스트에서 사용할 수 있습니다.  

@WebMvcTest 는 Controller 를 테스트할 때 주로 이용되며, 단일 클래스의 테스트를 진행하므로 @MockBean 을 통해 
가짜 객체를 만들어 줍니다. => Controller 객체까지만 생성되고 Service 객체는 생성하지 않습니다.  
@MockBean 은 위와 같이 Bean 컨테이너에 객체(Service)가 있어야 다른 객체(Controller)와 협력할 수 있는데, 
객체를 만들 수 없는 경우(@WebMvcTest)에 사용할 수 있습니다.


### Hamcrest

- https://blog.daum.net/jungjin1980/578
- http://hamcrest.org/JavaHamcrest/index

### JsonPath 

- https://github.com/json-path/JsonPath 
- https://ykh6242.tistory.com/100

### 참조링크 

- https://velog.io/@sproutt/MockBean%EC%9D%84-%EC%82%AC%EC%9A%A9%ED%95%9C-%ED%86%B5%ED%95%A9Controller%ED%85%8C%EC%8A%A4%ED%8A%B8  
- https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/mock/mockito/MockBean.html    
- https://www.baeldung.com/java-spring-mockito-mock-mockbean
- https://frozenpond.tistory.com/83  
- https://velog.io/@max9106/Spring-Boot-%ED%85%8C%EC%8A%A4%ED%8A%B8-dek6b0a1zd     
- [Junit5 BDDMockito로 알아보는 TDD와 BDD의 차이 및 BDD 실습](https://wonit.tistory.com/493)