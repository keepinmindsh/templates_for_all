package bong.sse.http.domain.resultwaiting;

import bong.sse.http.domain.resultwaiting.manager.DataManager;
import bong.sse.http.model.resultwaiting.ResultWaitingDTO;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Component
public class ResultWaitingDomain {

    public Flux<ResultWaitingDTO> getResult(String key) {
        return Flux.interval(Duration.ofMillis(1000), Schedulers.newSingle("resultWaiting"))
                .take(30)
                .flatMap(number -> DataManager.getResult(key))
                .cast(ResultWaitingDTO.class);
    }
}
