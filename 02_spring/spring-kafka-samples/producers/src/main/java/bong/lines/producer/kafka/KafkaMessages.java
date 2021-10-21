package bong.lines.producer.kafka;

import bong.lines.producer.code.TOPIC_VALUE;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaMessages {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String msg) {
        kafkaTemplate.send(TOPIC_VALUE.NEW_TOPICS.getValue(), msg);
    }
}
