package bong.lines.router;

import bong.lines.router.configuration.URIConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(URIConfiguration.class)
public class RouterApplication {
    public static void main(String[] args) {
        SpringApplication.run(RouterApplication.class , args);
    }
}
