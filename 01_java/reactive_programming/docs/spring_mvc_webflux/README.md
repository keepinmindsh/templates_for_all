# Spring MVC 

![Spring MVC](https://github.com/keepinmindsh/templates_for_all/blob/main/01_java/reactive_programming/docs/assets/spring_mvc.png)

해당 방식은 Tomcat에서 정의한 Thread 갯수 만큼의 요청에 대해서 처리할 수 있는 구조이다. 따라서 지정된 Thread Pool 에서의 수량까지는 동시처리가 되지만 
Max Thread Pool 수를 넘게 된다면 더이상 처리될 수 없기 때문에 큐에서 대기하는 구조이다. 

# Spring WebFlux 

![Spring Webflux](https://github.com/keepinmindsh/templates_for_all/blob/main/01_java/reactive_programming/docs/assets/spring_mvc.png)

해당 방식은 Tomcat / Netty 등의 Event Loop 기반하에 Main Thread와 Worker Thread의 역할을 분리하여 들어온 요청에 대해서 Spring MVC 보다 
적은 수의 Thread 로 요청을 처리할 수 있는 구조이다. 다만, 모든 요청이 Blocking I/O로 구성되어 있는 요청일 경우 Spring MVC와 마찬가지로 대기 해야한다. 
이렇게 Non Blocking 방식을 활용했을 때, MSA 기반의 네트워크 호출이 많은 경우, 적용하기에 좋다. 

> [https://honinbo-world.tistory.com/97](https://honinbo-world.tistory.com/97)