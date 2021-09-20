package bong.lines.real.controller;

import bong.lines.real.model.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RealController {

    @GetMapping("/hello")
    public String getHelloWorld(@RequestParam("value") String value){

        log.info("From GET Value : {}", value);

        return "Hello World" + value;
    }

    @PostMapping("/hello")
    public String postHelloWorld(@RequestParam("value") String value, PostVO.RequestVO requestVO){

        log.info("From POST Value : {}", value);
        log.info("From Request Body : {}", requestVO);

        return "Hello World" + value;
    }


    @PostMapping("/real_test")
    public String postRealTest(@RequestParam("value") String value, PostVO.RequestVO requestVO){

        log.info("From POST Value : {}", value);
        log.info("From Request Body : {}", requestVO);

        return "Hello World" + value;
    }
}
