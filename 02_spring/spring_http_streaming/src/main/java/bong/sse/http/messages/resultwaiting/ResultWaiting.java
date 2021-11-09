package bong.sse.http.messages.resultwaiting;

import bong.sse.http.domain.resultwaiting.ResultWaitingDomain;
import bong.sse.http.model.resultwaiting.ResultWaitingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/result/waiting")
@RequiredArgsConstructor
public class ResultWaiting {

    private final ResultWaitingDomain resultWaitingDomain;

    @GetMapping(value = "/{key}", produces = "application/stream+json")
    public Mono<ResultWaitingDTO> resultWaiting(@PathVariable("key") String key) {
        return resultWaitingDomain.getResult(key);
    }
}
