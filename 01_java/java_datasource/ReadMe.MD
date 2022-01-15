### MySQL

- https://www.baeldung.com/java-connect-mysql

### Logging이 잘 안되는 이유?

- https://stackoverflow.com/questions/54652836/slf4j-found-slf4j-api-dependency-but-no-providers-were-found

### Commons DBCP 2 이해하기 

***

- maxActive
- maxWaitMills
- removeAbandonedOnBorrow
- removeAbandonedOnMaintenance

***

- initialSize : BasicDataSource 클래스 생성 후 최초로 getConnection() 메서드를 호출할 때 커넥션 풀에 채워 넣을 커넥션 개수
- maxActive : 동시에 사용할 수 있는 최대 커넥션 개수(기본값: 8)
- maxIdle : 커넥션 풀에 반납할 때 최대로 유지될 수 있는 커넥션 개수(기본값: 8)
- minIdle : 최소한으로 유지할 커넥션 개수(기본값: 0)

- maxActive >= initialSize  
maxActive = 10이고 initialSize = 20이라고 가정하면 최초에 커넥션을 생성할 때 initialSize 값이 최대 커넥션 개수인 maxActive 값보다 커서 논리적으로 오류가 있는 설정이다.
- maxIdle >= minIdle  
maxIdle < minIdle로 설정할 수는 있지만 최솟값이 최댓값보다 커서 논리적으로 오류가 있는 설정이다.
- maxActive = maxIdle  
maxActive 값과 maxIdle 값이 같은 것이 바람직하다.
maxActive = 10이고 maxIdle = 5라고 가정해 보자. 
항상 커넥션을 동시에 5개는 사용하고 있는 상황에서 1개의 커넥션이 추가로 요청된다면 
maxActive = 10이므로 1개의 추가 커넥션을 데이터베이스에 연결한 후 풀은 비즈니스 로직으로 커넥션을 전달한다. 
이후 비즈니스 로직이 커넥션을 사용 후 풀에 반납할 경우, maxIdle=5에 영향을 받아 커넥션을 실제로 닫아버리므로, 
일부 커넥션을 매번 생성했다 닫는 비용이 발생할 수 있다

- testOnBorrow: 커넥션 풀에서 커넥션을 얻어올 때 테스트 실행(기본값: true)
- testOnReturn: 커넥션 풀로 커넥션을 반환할 때 테스트 실행(기본값: false)
- testWhileIdle: Evictor 스레드가 실행될 때 (timeBetweenEvictionRunMillis > 0) 커넥션 풀 안에 있는 유휴 상태의 커넥션을 대상으로 테스트 실행(기본값: false)

validationQuery 옵션에는 DBMS에 따라 다음과 같이 쿼리를 설정하기를 권장한다. 
실제 테이블에 있는 데이터를 조회하는 쿼리를 validationQuery 옵션에 설정했다가 
운영 서버에서 많은 데이터를 조회해 장애로 이어진 사례도 있다.

- removeAbandoned 옵션  
removeAbandoned 옵션은 false가 기본값이다. removeAbandoned 옵션은 오랫동안 열려만 있고 Connection.close() 메서드가 호출되지 않는 커넥션을 임의로 닫는 기능을 설정하는 옵션이다. removeAbandoned 옵션을 true로 설정하고 removeAbandonedTimeout 옵션에 허용할 최대 시간을 지정하면 Commons DBCP에서 자동으로 Connection.close() 메서드를 호출한다.
애플리케이션 개발자가 직접 JDBC API를 다루던 때는 Connection.close() 메서드 호출을 누락해서 전체 시스템의 자원을 고갈시키는 경우가 많았다. 근래에는 대부분의 애플리케이션이 Spring이나 MyBatis 등의 프레임워크를 사용하기 때문에 그럴 위험이 없다. 하지만 커넥션 자원이 제대로 반납되지 않는다는 의심이 있다면 Commons DBCP 수준에서 방어하기보다는 문제 지점을 찾아서 근본적으로 수정해야 애플리케이션을 더 안정적으로 만들 수 있다.
removeAbandoned 옵션을 true로 설정하면 실행 시간이 긴 쿼리의 커넥션을 의도하지 않게 닫는 부작용이 있다. removeAbandoned 옵션은 기본값이 false로 사용하고, 오래 걸리는 쿼리는 JDBC Statement의 쿼리 타임아웃(query timeout) 등 다른 속성으로 제어하는 편이 바람직하다.
