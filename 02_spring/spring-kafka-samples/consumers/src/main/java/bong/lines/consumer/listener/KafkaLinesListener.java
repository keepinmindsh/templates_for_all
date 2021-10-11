package bong.lines.consumer.listener;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaLinesListener {

    @KafkaListener(id = "myId", topics = "bong-lines")
    public void listen(String in){
        System.out.println(in);
    }
}
