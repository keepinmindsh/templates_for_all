package bong.sse.http.domain.sample;

import bong.sse.http.model.sample.SampleDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class SampleDomain {

    public Flux<SampleDTO> get(long id) {
        return WebClient.create("https://jsonplaceholder.typicode.com")
                .get()
                .uri("/users/{id}", id)
                .retrieve()
                .bodyToFlux(SampleDTO.class);
    }
}
