package bong.lines.producer.messages;

import bong.lines.producer.code.TOPIC_VALUE;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Messages {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String msg) {
        kafkaTemplate.send(TOPIC_VALUE.BONG_LINES.getValue(), msg);
    }
}
