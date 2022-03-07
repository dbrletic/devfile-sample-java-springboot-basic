package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
 @RequestMapping("/rest-example")
public class DemoApplication {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
    
   @RequestMapping(value = "/{name}")
   String homeName(@PathVariable String name) {
        return "Hello " + name + "!";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
