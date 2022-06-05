# Reactive Stream - BackPressure

Reactive Stream의 목적을 보면 bacpressure를 이용하여 비동기 스트림의 표준을 제공한다고 되어있습니다.
여기서 backpressure의 정의를 한번 더 알아보도록 하겠습니다. 리액티드 선언문의 용어집을 보면 아래와 같이 
BackPressure에 대해서 설명합니다.

> 한 컴포넌트가 부하를 이겨내기 힘들 때, 시스템 전체가 합리적인 방법으로 대응해야 합니다. 과부하 상태의 컴포넌트에서 치명적인 장애가 발생하거나 제어 없이 메시지를 유실해서는 안 됩니다. 컴포넌트가 대처할 수 없고 장애가 발생해선 안 되기 때문에 컴포넌트는 상류 컴포넌트들에 자신이 과부하 상태라는 것을 알려 부하를 줄이도록 해야 합니다. 이러한 배압은 시스템이 부하로 인해 무너지지 않고 정상적으로 응답할 수 있게 하는 중요한 피드백 방법입니다. 배압은 사용자에게까지 전달되어 응답성이 떨어질 수 있지만, 이 메커니즘은 부하에 대한 시스템의 복원력을 보장하고 시스템 자체가 부하를 분산할 다른 자원을 제공할 수 있는지 정보를 제공할 것입니다.

```java
@Slf4j
public class ReactiveStream_forBackPressure {

    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }

        Publisher publisher = subscriber -> {
            subscriber.onSubscribe(new Subscription() {
                @Override
                public void request(long n) {
                    log.info("최초 실행값 : {}", n);

                    if(n < 0){
                        subscriber.onError(new Exception("에러가 발생하였다."));
                    }

                    for (int i = 0; i < n; i++) {
                        if(stack.empty()){
                            subscriber.onComplete();
                        }

                        if(stack.isEmpty()){
                            break;
                        }else{
                            subscriber.onNext(stack.pop());
                        }
                    }
                }

                @Override
                public void cancel() {

                }
            });
        };

        Subscriber subscriber = new Subscriber() {

            Subscription subscription;

            int pressureCount = 0;

            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                subscription.request(3);
            }

            @Override
            public void onNext(Object o) {
                log.info(" On Next" + o);

                pressureCount ++;

                if(pressureCount == 3){
                    pressureCount = 0;
                    subscription.request(3);
                }
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onComplete() {
                log.info("On Complete");
            }
        };

        publisher.subscribe(subscriber);
    }

}
```

### 참조 

> [Reactive Stream](https://www.reactive-streams.org/)  
> [Reactive Menifesto](https://www.reactivemanifesto.org)  
> [Reactive Stream 에 대한 GitHub](https://github.com/reactive-streams/reactive-streams-jvm/tree/v1.0.3)