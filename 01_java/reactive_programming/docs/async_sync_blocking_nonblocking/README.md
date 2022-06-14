# Async/Sync

### Async ( 비동기 )

요청과 응답이 동시에 일어나지 않음. 요청에 대한 응답을 받는 시간 동안 Thread는 다른 업무를 수행할 수 있음. 

### Sync ( 동기 )

요청과 응답이 동시에 일어남. 요청에 대한 응답을 받는 시간 동알 Thread는 다른 업무를 수행할 수 없음. 

# Blocking/NonBlocking

### Blocking ( 블로킹 ) 

Thread가 Blocking 되는 시점에 Blocking이 풀리기 전까지 아무일도 할 수 없음

### NonBlocking ( 논블로킹 )

Main Thread 가 Blocking을 바탕으로 처리하는 업무를 다른 Thread( Worker Thread) 에게 전달해주고, Main Thread는 다른 일을 하다가 Worker Thread가 업무를 
완료하면 해당 Main Thread로 통지를 하여 해당 업무를 마무리하는 것! 

- 제어권이 분리됨
  - 관리 ( Main Thread) : 업무 지시 
  - 처리 ( Worker Thread ) : 업무 처리 - 업무 처리 내역은 Blocking 업무이든 그외의 다른 업무이든 상관 없음. 

##### **Non Blocking I/O** 는 Blocking에 대한 업무를 역할을 분리하여 처리할 수 있게 해주는 것! 