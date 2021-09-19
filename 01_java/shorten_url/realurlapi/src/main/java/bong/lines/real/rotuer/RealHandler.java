package bong.lines.real.rotuer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class RealHandler {
    public Mono<ServerResponse> getRealData(ServerRequest request) {

        log.info(request.toString());

        log.info(request.path());

        log.info(request.pathVariables().get("test"));

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new RealData("Hello, Spring!")));
    }
}
