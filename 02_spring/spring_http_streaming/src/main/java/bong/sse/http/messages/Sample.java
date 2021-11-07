package bong.sse.http.messages;


import bong.sse.http.domain.SampleDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import bong.sse.http.model.User;

import java.time.Duration;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class Sample {

    private final SampleDomain sampleDomain;


    // 1초마다 User 발생
    @GetMapping(produces = "application/stream+json")
    public Flux<User> users() {

        return Flux.interval(Duration.ofSeconds(1L))
                .take(3)
                .flatMap(number -> sampleDomain.get(number + 1L));
    }

}
