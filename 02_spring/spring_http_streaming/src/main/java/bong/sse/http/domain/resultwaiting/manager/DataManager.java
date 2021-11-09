package bong.sse.http.domain.resultwaiting.manager;

import bong.sse.http.model.resultwaiting.ResultWaitingDTO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Optional;

public class DataManager {
    public static ResultWaitingDTO getResult(String key, RedisTemplate redisTemplate){

        final ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();

        ResultWaitingDTO resultWaitingDTO = ResultWaitingDTO.builder()
                .value(Optional.ofNullable(stringStringValueOperations.get(key)).orElse("Empty"))
                .build();

        stringStringValueOperations.getOperations().delete(key);

        return resultWaitingDTO;
    }
}
