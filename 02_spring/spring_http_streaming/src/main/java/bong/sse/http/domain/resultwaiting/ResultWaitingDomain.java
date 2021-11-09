package bong.sse.http.domain.resultwaiting;

import bong.sse.http.domain.resultwaiting.manager.DataManager;
import bong.sse.http.model.resultwaiting.ResultWaitingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class ResultWaitingDomain {

    final RedisTemplate redisTemplate;

    public Mono<ResultWaitingDTO> getResult(String key) {
        CompletableFuture<ResultWaitingDTO> future = CompletableFuture
                .supplyAsync(() -> {

                    ResultWaitingDTO resultWaitingDTO;

                    while (true){
                        resultWaitingDTO = DataManager.getResult(key, redisTemplate);

                        if("call".equals(resultWaitingDTO.getValue())) {
                            break;
                        }
                    }

                return resultWaitingDTO;
        });

        Mono<ResultWaitingDTO> monoFromFuture = Mono.fromFuture(future);

        return monoFromFuture;
    }
}
