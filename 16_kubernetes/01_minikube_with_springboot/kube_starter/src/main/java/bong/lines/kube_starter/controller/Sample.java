package bong.lines.kube_starter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Sample {

    @GetMapping("/hello")
    public String getHello(){
        return "Hello World";
    }
}
