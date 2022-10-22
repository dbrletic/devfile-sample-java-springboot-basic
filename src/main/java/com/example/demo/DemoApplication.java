package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.fluentd.logger.FluentLogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@SpringBootApplication
@RequestMapping("/rest-example")
public class DemoApplication {

	Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	
    @RequestMapping("/")
    String home() {
		
        for (int i=0; i<= 50000; i++) {
            logger.info("Hello i am here!");
            // do some execution
            logger.debug("this is a debug");
            logger.info("i am done");
        }
        return "Hello World! Logging information now";
    }
    
   @RequestMapping(value = "/{name}")
   String homeName(@PathVariable String name) {
        return "Hello " + name + "!";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
