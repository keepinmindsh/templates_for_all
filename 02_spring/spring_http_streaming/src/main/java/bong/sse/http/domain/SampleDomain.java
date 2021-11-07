package bong.sse.http.domain;

import bong.sse.http.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class SampleDomain {

    public Flux<User> get(long id) {
        return WebClient.create("https://jsonplaceholder.typicode.com")
                .get()
                .uri("/users/{id}", id)
                .retrieve()
                .bodyToFlux(User.class);
    }
}
