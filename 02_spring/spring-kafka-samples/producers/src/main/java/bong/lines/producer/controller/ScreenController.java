package bong.lines.producer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScreenController {
    @GetMapping("/publisher/call/test")
    public String getPublisher(){
        return "publisher";
    }
}
