package bong.lines.producer.runner;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class LinesCustomRunner {

    public ApplicationRunner runner(KafkaTemplate<String, String> template){
        return args -> {
            template.send("topic1", "test");
        };
    }
}
