package bong.lines.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

    @GetMapping("/v1/hello/world1")
    public String helloWorld(){
        return "Hello World";
    }


}
