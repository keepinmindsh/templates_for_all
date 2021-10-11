package bong.lines.producer.controller;

import bong.lines.producer.messages.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {

    private final Messages messages;

    @GetMapping("/hello")
    public String hello(){

        messages.sendMessage("Hello World");

        return "success";
    }
}
