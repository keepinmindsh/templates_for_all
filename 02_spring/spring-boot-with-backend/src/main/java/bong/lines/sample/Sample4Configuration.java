package bong.lines.sample;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class Sample4Configuration {

    @PostConstruct
    public void setUp(){
        System.out.println("true = " + true);
    }
}
