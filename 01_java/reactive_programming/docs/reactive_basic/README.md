# Reactive

***

- "외부의 어떤 이벤트나 데이터가 발생하면 거기에 대응하면 방식으로 동작하는 프로그램을 만드는 것"
- "데이터 플로우와 상태 변경을 전파한다는 생각에 근간을 둔 프로그래밍 패러다임"
- "막힘 없이 흘러다니는 데이터(이벤트)를 통해 사용자에게 자연스러운 응답을 주고, 규모 탄력적으로 리소스를 사용하며 실패에 있어서 유연하게 대처한다."

![](https://keepinmindsh.github.io/lines/assets/img/Reactive.png)

***

#### Responsive : 사용자에 대한 반응

**시스템이 적시에 응답합니다.**  
응답성은 사용성과 기능성의 기반인데, 그것보다 더 응답성은 문제에 대해서 빠르게 감지하는 것과 효율성을 다루는 것에 초점을 둡니다. 반응성(응답성)이 좋은 시스템은 속도와 일정한 응답성을 제공하고 , 신뢰할 수 있는 상향 경계를 수립하므로써, 일정한 품질의 서비스를 제공하는 것에 있습니다. 이 일관성있는 행동은 에러 처리를 간편화 하고, 사용자 신뢰를 구축하고, 앞선 상호작용을 장려합니다.

#### Scalable(Elastic) : 부하에 대한 반응

**시스템은 다양한 작업하에 응답성을 유지합니다.**  
Reactive System은 서비스에 제공되기 위한 입력을 할당한 자원을 증가시키거나 감소시키는 것으로써 입력 량의 변화에 응답할 수 있다. 이것은 경합 포인트나 병목현상을 가지지 않게 설계되었으며, 공유하고, 컴포넌트를 복제하고, 입력을 분산할 수 있도록 하는 결과를 의도합니다. Reactive System은 응답성 뿐만 아니라 예측 가능성을 지원하는데 이는 실시간 성능 측정을 제공하여 알고리즘을 조정할 수 있습니다. 상용 하드웨어 및 소프트웨어 플랫폼에서 비용 효율적인 방식으로 탄력성을 얻을 수 있습니다.

#### Resillent : 실패 상황에 대한 반응

**시스템은 장애가 발생하더라도 응답성을 유지합니다.**  
이는 고 가용성, Mission Critical 시스템에 적용됩니다. 탄력성은 복제, 유지, 격리 및 위임에 의해 획득할 수 있습니다. 장애는 각각의 컴포넌트에 포함되어 있기 때문에 각각으로 부터 컴포넌트가 고립하되는 것으로 시스템 전체에 영향일 미치지 않고, 시스템의 일부가 장애 및 복구되는 것을 보장할 수 있습니다. 각각의 컴포넌트의 복수는 새로운 컴포넌트에게 위임되고, 고가용성은 필요에 따라 복제 따라서 보장됩니다. 고객의 기능은 장애를 처리함으로써 부담을 받지 않아도 됩니다.

#### Event-Driven( Message-Driven ) : 이벤트에 대한 반응

**Reactive 시스템은 Location Transparency, Isolation, Loose Coupling을 보장하는 컴포넌트들 사이의 경계를 관리하기 위해서 비동기적인 Message 전달 (Asynchronouse message-passing)에 의존합니다.**  
이 경계는 메세지로서 장애를 위임하기 위한 의도를 제공합니다. 명시적인 Message 전달을 이용하면 부하관리, 탄력성, 흐름제어 및 시스템에서의 메세지 큐 모니터링, 필요에 따라 Back Pressure를 적용하는 것을 가능하게 합니다. 통신수단으로의 Location Transparent Message는 동일한 구조와 의미의 단일 호스트 또는 클러스터와 동작하기 위한 장애의 관리를 가능하게 합니다. Non-Blocking Communication은 수신자로 하여금 활성 상태에서만 자원을 소모할 수 있게 하여 시스템의 오버헤드를 줄일 수 있습니다.


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


# Reactive Stream 의 API Component

***

<https://github.com/reactive-streams/reactive-streams-jvm/blob/v1.0.0/README.md#specification>

Reactive Stream 에서 제공하는 기본 4가지 컴포넌트를 알아보고자합니다. 아래의 컴포넌트들은 초기에 Observer 패턴을 기반으로하여 Observer/Observable에서 부족한 부분을 보완한 컴포넌트입니다.

- Publisher
  - 구독을 위한 데이터를 생성하는 주체로 Subscriber 에서 데이터 처리량을 제어하기에 처리량을 초과하지 않음을 확신할 수 있다. 
- Subscriber
  - Subscriber 는 구독이 실행되면 Subscription 에 의해서 자신이 처리할 수 있는 용량 만큼을 받을 수 있다. 
  - Subscription::request(n)
- Subscription
  - Publisher 와 Subscriber 사이에서 데이터 생성을 제어하기 위한 기본적인 사항을 제공한다. 
- Processor

***

### Publisher
- Publisher는 Observable 입니다. Subscriber는 Publisher의 subscribe를 통해 등록합니다.

```java

public interface Publisher {
  public void subscribe(Subscriber<? super T> s);
}

```

***

### Subscriber
 - Subscriber 는 Observer 입니다.

![](https://keepinmindsh.github.io/lines/assets/img/subscriber_process.png)


```java

// 아래의 4개의 메소드는 반드시 implement 되어야 합니다.                                   
public interface Subscriber {
  public void onSubscribe(Subscription s);
  public void onNext(T t);
  public void onError(Throwable t);
  public void onComplete();
}   

```

- onSubscribe : 최초 호출되는 메소드, Subscriber를 사용할 때는 무조건 처음에 호출해야합니다.
- onNext : 기존의 Observer에서 update와 같은 역할을 합니다. 데이터를 받을 때 사용합니다.
- onComplete : 완료 되었을 때,
- onError : 에러가 발생했을 때,

***

### Subscription

```java

public interface Subscription {
  public void request(long n);
  public void cancel();
}

```

request는 long 타입의 파라미터를 받고 있는데 Subscriber가 이 메소드를 통해 요청을 하게 됩니다. 만약 5개의 데이터를 필요하다고 가정했을 때, reqeust 에 5를 넣어서 호출하면 Subscription은 5개를 호출하게 됩니다. 즉, 10개의 데이터가 있을 때, reqeust가 5를 받아 처리한다면 5개 -> 5개 를 보내줄 수 있게 처리합니다.
이는 publisher를 통해서 들어오는 데이터 스트림을 request를 이용해 subscriber에서 처리하는데 적절한 범위로 처리될 수 있게 제어를 할 수 있습니다. 이를 Reactive Stream에서 가장 중요한 Back-Pressure를 제어할 수 있는 방법입니다. 

# Reactive Basic Flow : Publisher - Subscriber - Subscription


![](https://keepinmindsh.github.io/lines/assets/img/reactive_basic_flow.png)

- Publisher에 Subscriber가 구독(등록)되면, Publisher가 실행(subscribe)될 때 Publisher 통해서 데이터(스트림) 또는 시퀀스를 Subscriber로 전달하게됩니다.
- 이때 Publisher는 Subscriber에 정의된 OnSubscribe()를 호출하고, Subscriber는 request(n)를 호출하여 몇개의 데이터를 보내달라고 Publisher에게 Subscription을 통해서 요청하게 됩니다.
- Subscrition을 통해 정의된 요청 갯수에 의해서 request 메소드 내에서 Subscriber의 onNext, onError, OnComplete를 제어할 수 있습니다.
- Subscriber가 동작하던 도중에 장애/에러 발생으로 인하여 처리를 중단해야할 때 subscription 객체를 이용해서 cancel을 호출 하고 Flag를 관리한다면, 해당 Flow 전체를 중단할 수 있습니다.

# Reactive Programming 을 위한 기본 코드 

```java
public class BasicCode {
    public static void main(String[] args) {
        
        // Publisher 객체 
        Publisher<Integer> publisher = new Publisher() {

            Stack<Integer> stack;

            @Override
            public void subscribe(Subscriber subscriber) {

                stack = new Stack<>();

                for (int i = 0; i < 10; i++) {
                    stack.push(i);
                }

                subscriber.onSubscribe(new Subscription() {
                    @Override
                    public void request(long n) {
                        System.out.println("request " + n);

                        if(n < 0){
                            subscriber.onError(new Exception(" 0 이상의 숫자를 넣어야 합니다. "));
                        }

                        for (int i = 0; i < n; i++) {
                            if(stack.isEmpty()){
                                subscriber.onComplete();
                            }

                            if(!stack.isEmpty()){
                                subscriber.onNext(stack.pop());
                            }
                        }
                    }

                    @Override
                    public void cancel() {

                    }
                });

            }
        };

        // Subscriber 객체 
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {

            Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;

                subscription.request(1);
            }

            @Override
            public void onNext(Integer o) {
                System.out.println(" onNext - " + o);

                subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(" onError - " + throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println(" onComplete");
            }
        };

        // Publisher 가 구독중인 Subscriber 에게 통지를 실행한다. 
        publisher.subscribe(subscriber);
    }
}
``` 

# BackPressure 를 관리하는 Reactive Programming 

