package bong.lines;

import bong.lines.sample.Sample4Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration(proxyBeanMethods = false)
@EnableAutoConfiguration
@Import({Sample4Configuration.class})
public class Sample4Application {
    public static void main(String[] args) {
        SpringApplication.run(Sample4Application.class, args);
    }
}
