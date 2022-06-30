# Producer - Consumer 패턴 ( 생산자 - 소비자 패턴 )

어떤 데이터가 생성되면, 그 생성된 데이터를 받아 처리하는 상황에서 멀티 스레드를 활용하여 처리량과 속도를 늘리고자할 때 적용할 수 있는 패턴이다.

- 생산를 하는 주체 ( 프로듀서 )
- 소비를 하는 주체 ( 컨슈머 )

프로듀서와 컨슈머로 분리할 경우의 이점은 프로듀서와 컨슈머의 처리능력에 따라서 **조율**할 수 있다는 것이다.  
프로듀서의 갯수를 늘리면 컨슈머에게 많은 처리를 요청할 수 있으며, 컨슈머가 느릴 경우 Queue를 활용하여 처리량을 조절할 수 있다.

이 때 사용하게 되는 큐는 **적절한 특성을 가진 큐**를 사용해야 한다.

- 무제한 큐
    - 무제한의 큐는 실제 무제한의 리소스를 사용할 수 없기 때문에 메세지 손상이 온다.
- 크키가 제한된 드롭 큐
    - 메시지의 중요성이 낮을 경우 사용 가능하다. 메세지 손실이 발생한다.
- 크기가 제한된 블로킹 큐
    - 컨슈머의 처리 능력이 부족하다면, 시스템 전체의 처리량이 제한된다.

위의 항목에서 중요한 건은 **제어**의 방향이 컨슈머에 의한 것이 아닌 프로듀서에 의한 Push 모델 방식으로 사용하기 때문에 많은 부작용을 만들 수 있다.

#### Reactive Programming 은 시스템이 부하에 적절히 대응하는 방법, 즉 BackPressure 메커니즘의 중요성을 언급한다.

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