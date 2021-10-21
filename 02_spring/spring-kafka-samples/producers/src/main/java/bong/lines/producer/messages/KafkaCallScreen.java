package bong.lines.producer.messages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KafkaCallScreen {
    @GetMapping("/kafka/caller")
    public String getPublisher(){
        return "publisher";
    }
}
