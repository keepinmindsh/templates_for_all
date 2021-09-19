package bong.lines.real.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RealController {

    @GetMapping("/hello")
    public String getHelloWorld(@RequestParam("value") String value){

        log.info(value);

        return "Hello World" + value;
    }
}
