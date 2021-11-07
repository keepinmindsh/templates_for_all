package bong.sse.http.domain.resultwaiting.manager;

import bong.sse.http.model.resultwaiting.ResultWaitingDTO;
import reactor.core.publisher.Flux;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class DataManager {

    private static final ConcurrentHashMap<String, ResultWaitingDTO> STRING_RESULT_WAITING_DTO_CONCURRENT_HASH_MAP = new ConcurrentHashMap<>();

    public static Flux<ResultWaitingDTO> getResult(String key){
        return Flux.just(Optional.ofNullable(STRING_RESULT_WAITING_DTO_CONCURRENT_HASH_MAP.get(key)).orElse(new ResultWaitingDTO()));
    }
}
