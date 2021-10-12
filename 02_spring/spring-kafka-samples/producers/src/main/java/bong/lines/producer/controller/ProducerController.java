package bong.lines.producer.controller;

import bong.lines.producer.messages.Messages;
import bong.lines.producer.model.HelloWorldDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ProducerController {

    private final Messages messages;

    @PostMapping("/hello")
    public String hello(@RequestBody HelloWorldDTO helloWorldDTO) throws Exception{

        log.info(helloWorldDTO.toString());

        ObjectMapper objectMapper = new ObjectMapper();

        String stringValue = objectMapper.writeValueAsString(helloWorldDTO);

        messages.sendMessage(stringValue);

        return "success";
    }
}
