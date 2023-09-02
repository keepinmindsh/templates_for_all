package bong.lines;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

    @GetMapping("/name")
    public String Name(){
        return "bongbong";
    }
}
