package bong.lines.shortcut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ShortCutApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShortCutApplication.class, args);
    }

    @GetMapping("/hello")
    public String helloWorld(){
        return "hello world";
    }
}
