package bong.lines.producer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProducerDTO {
    @Setter
    @Getter
    @ToString
    public static class Request {
        private String requestKafkaMessage;
    }
}
