# Reactive Basic Flow : Publisher - Subscriber - Subscription


![](https://keepinmindsh.github.io/lines/assets/img/reactive_basic_flow.png)

- Publisher에 Subscriber가 구독(등록)되면, Publisher가 실행(subscribe)될 때 Publisher 통해서 데이터(스트림) 또는 시퀀스를 Subscriber로 전달하게됩니다.
- 이때 Publisher는 Subscriber에 정의된 OnSubscribe()를 호출하고, Subscriber는 request(n)를 호출하여 몇개의 데이터를 보내달라고 Publisher에게 Subscription을 통해서 요청하게 됩니다.
- Subscrition을 통해 정의된 요청 갯수에 의해서 request 메소드 내에서 Subscriber의 onNext, onError, OnComplete를 제어할 수 있습니다.
- Subscriber가 동작하던 도중에 장애/에러 발생으로 인하여 처리를 중단해야할 때 subscription 객체를 이용해서 cancel을 호출 하고 Flag를 관리한다면, 해당 Flow 전체를 중단할 수 있습니다.
