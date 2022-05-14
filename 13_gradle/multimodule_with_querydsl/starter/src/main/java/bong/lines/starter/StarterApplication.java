package bong.lines.starter;

import bong.lines.jpa.EnableJPAConfiguration;
import bong.lines.register.EnableRegisterConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableJPAConfiguration
@EnableRegisterConfiguration
@SpringBootApplication
public class StarterApplication {
    public static void main(String[] args) {
        SpringApplication.run(StarterApplication.class, args);
    }
}
