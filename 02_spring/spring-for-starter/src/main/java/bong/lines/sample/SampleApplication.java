package bong.lines.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@SpringBootApplication
public class SampleApplication {
    public static void main(String[] args) {
        //SpringApplication.run(SampleApplication.class, args);


        Person person = new Person();

        person.sayHello();

        person.name("Bong");

        person.myName();

    }
}

@RestController
class Controller {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}

class Person {
    private String name = "John";

    public void name(String name){
        this.name = name;
    }

    public void sayHello() {
        System.out.println("Hello!");
    }

    public void myName() {
        System.out.println("My Name is " + name);
    }
}
