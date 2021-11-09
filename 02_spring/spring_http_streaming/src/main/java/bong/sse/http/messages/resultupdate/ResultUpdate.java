package bong.sse.http.messages.resultupdate;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/result")
@RequiredArgsConstructor
public class ResultUpdate {

    private final RedisTemplate redisTemplate;


    @GetMapping("/update/{key}")
    public void resultUpdate(@PathVariable("key") String key){

        final ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();

        stringStringValueOperations.set(key , "call");
    }

}
