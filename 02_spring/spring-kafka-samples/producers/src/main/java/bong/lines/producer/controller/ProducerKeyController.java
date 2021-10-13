package bong.lines.producer.controller;

import bong.lines.producer.messages.Messages;
import bong.lines.producer.model.HelloWorldDTO;
import bong.lines.producer.model.KeyDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ProducerKeyController {

    private final Messages messages;

    @PostMapping("/key")
    public String hello(@RequestBody KeyDTO.Request keyDTO) throws Exception{

        log.info(keyDTO.toString());

        ObjectMapper objectMapper = new ObjectMapper();

        String stringValue = objectMapper.writeValueAsString(keyDTO);

        messages.sendMessage(stringValue);

        return "success";
    }
}
