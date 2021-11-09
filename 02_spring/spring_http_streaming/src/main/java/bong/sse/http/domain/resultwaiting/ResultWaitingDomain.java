package bong.sse.http.domain.resultwaiting;

import bong.sse.http.domain.resultwaiting.manager.DataManager;
import bong.sse.http.model.resultwaiting.ResultWaitingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@RequiredArgsConstructor
public class ResultWaitingDomain {

    final RedisTemplate redisTemplate;

    public Mono<ResultWaitingDTO> getResult(String key) {
        CompletableFuture<ResultWaitingDTO> future = CompletableFuture
                .supplyAsync(() -> {

                    ResultWaitingDTO resultWaitingDTO = null;

                    boolean isPass = false;

                    for (int i = 0; i < 5; i++) {
                        try{
                            resultWaitingDTO = DataManager.getResult(key, redisTemplate);

                            if("call".equals(resultWaitingDTO.getValue())) {
                                isPass = true;
                                break;
                            }

                            TimeUnit.SECONDS.sleep(1);
                        }catch (Exception exception){
                            exception.printStackTrace();
                        }
                    }

                return isPass ? resultWaitingDTO : ResultWaitingDTO.builder().value("Timeout!").build();
        });

        Mono<ResultWaitingDTO> monoFromFuture = Mono.fromFuture(future);

        return monoFromFuture;
    }
}
