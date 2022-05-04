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


> https://dololak.tistory.com/465
