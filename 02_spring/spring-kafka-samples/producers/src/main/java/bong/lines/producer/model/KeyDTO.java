package bong.lines.producer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KeyDTO {
    @Setter
    @Getter
    @ToString
    public static class Request {
        private String requestkafkaMessage;
    }
}
