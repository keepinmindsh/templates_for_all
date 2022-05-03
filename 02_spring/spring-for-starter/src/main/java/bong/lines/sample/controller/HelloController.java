package bong.lines.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/v1/hello/world2")
    public @ResponseBody String helloWorld(){
        return "Hello World";
    }

}
