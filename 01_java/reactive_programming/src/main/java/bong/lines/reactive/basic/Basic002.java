package bong.lines.reactive.basic;

import bong.lines.stopwatch.StopWatchFactory;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.util.StopWatch;

import java.util.Stack;

@Slf4j
public class Basic002 {

    public static void main(String[] args) {

        Stack<Integer> stackParam = new Stack<>();

        for (int i = 0; i < 100; i++) {
            stackParam.push(i);
        }

        StopWatchFactory.execute((stack, stopWatch) -> {
            Publisher publisher = subscriber -> {
                subscriber.onSubscribe(new Subscription() {
                    @Override
                    public void request(long n) {

                        if (!stopWatch.isRunning()) {
                            stopWatch.start();
                        }

                        log.info("최초 실행값 : {}", n);

                        if(n < 0){
                            subscriber.onError(new Exception("에러가 발생하였다."));
                        }

                        for (int i = 0; i < n; i++) {
                            if(stack.empty()){
                                stopWatch.stop();
                                log.info("Second : {}", stopWatch.getTotalTimeSeconds());
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

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

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
        }, stackParam, new StopWatch());
    }
}
