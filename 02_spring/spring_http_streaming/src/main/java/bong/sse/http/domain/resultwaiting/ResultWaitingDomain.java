package bong.sse.http.domain.resultwaiting;

import bong.sse.http.domain.resultwaiting.manager.DataManager;
import bong.sse.http.model.resultwaiting.ResultWaitingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class ResultWaitingDomain {

    final RedisTemplate redisTemplate;

    public Flux<ResultWaitingDTO> getResult(String key) {
        return Flux.interval(Duration.ofMillis(1000), Schedulers.newSingle("resultWaiting"))
                .take(30)
                .flatMap(
                        number ->DataManager.getResult(key, redisTemplate).filter(value -> value.getValue() != null)
                )
                .cast(ResultWaitingDTO.class);
    }
}
