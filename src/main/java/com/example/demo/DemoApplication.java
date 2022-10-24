package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.fluentd.logger.FluentLogger;

@RestController
@SpringBootApplication
@RequestMapping("/rest-example")
public class DemoApplication {

    Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	private static FluentLogger LOG = FluentLogger.getLogger("transaction_log.ng.double.encoded");
	
    @RequestMapping("/")
    String home() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("from", "userA");
        data.put("to", "userB");
        LOG.log("follow", data);
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
