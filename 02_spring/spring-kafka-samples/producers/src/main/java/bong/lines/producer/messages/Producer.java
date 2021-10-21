package bong.lines.producer.messages;

import bong.lines.producer.kafka.KafkaMessages;
import bong.lines.producer.model.ProducerDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class Producer {

    private final KafkaMessages kafkaMessages;

    @PostMapping("/publisher/call")
    public String hello(@RequestBody ProducerDTO.Request keyDTO) throws Exception{

        log.info(keyDTO.toString());

        ObjectMapper objectMapper = new ObjectMapper();

        String stringValue = objectMapper.writeValueAsString(keyDTO);

        kafkaMessages.sendMessage(stringValue);

        return "success";
    }
}
