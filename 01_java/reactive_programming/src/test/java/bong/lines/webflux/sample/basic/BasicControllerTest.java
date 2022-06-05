package bong.lines.webflux.sample.basic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
class BasicControllerTest {

    @Test
    @DisplayName("ForTest")
    public void ForTest(){
        WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpec = WebClient.builder()
                .baseUrl("http://localhost:8080/hello")
                .build()
                .get();

        System.out.println("requestHeadersUriSpec.toString() = " + requestHeadersUriSpec.toString());
    }
}