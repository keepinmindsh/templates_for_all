# MySQL and JPA/MyBatis 

# 독커로 MySQL 설치 하기 

[참고링크](https://junghyungil.tistory.com/201)

```shell

docker run --platform linux/amd64 
-p 3306:3306 
--name [컨테이너 이름] 
-e MYSQL_ROOT_PASSWORD=[루트 유저 비밀번호] 
-e MYSQL_DATABASE=[데이터베이스 이름] 
-e MYSQL_PASSWORD=[비밀번호] 
-d mysql

$ docker run --platform linux/amd64 -p 3306:3306 --name mysql-dream -e MYSQL_ROOT_PASSWORD=dream -e MYSQL_DATABASE=dream -e MYSQL_PASSWORD=dream -d mysql

```

> [Docker Mybatis 세팅하기](https://junghyungil.tistory.com/201)  
> [Docker Mybatis Access Setting](https://csksoft.tistory.com/69)  
> [MyBatis 연동을 위한 Mapped SQL Statements 세팅(sqlmap 세팅)](https://mybatis.org/mybatis-3/getting-started.html)  
> [sqlmap xml Sample](https://mybatis.org/mybatis-3/sqlmap-xml.html)