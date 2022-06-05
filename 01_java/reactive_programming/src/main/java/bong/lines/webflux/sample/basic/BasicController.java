package bong.lines.webflux.sample.basic;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class BasicController {

    @GetMapping(value = "/hello/{id}", produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Integer> hello(@PathVariable("id") int count){

        return Mono
                .just(count)
                .doOnRequest(request -> {
                    log.info("request : {}", request);
                })
                .doOnNext(item -> {
                    log.debug("Value : {}", item);
                });
    }
}
