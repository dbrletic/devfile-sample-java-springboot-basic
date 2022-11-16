package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PathVariable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import org.fluentd.logger.FluentLogger;


@RestController
@SpringBootApplication
public class DemoApplication {

    private static final Logger LOG = LoggerFactory.getLogger(DemoApplication.class);
   //
    private static FluentLogger LOCALLOG = FluentLogger.getLogger("FLUENCY_SYNC");
    
    private static final Logger FLUENTLOG = LoggerFactory.getLogger("fluent_transaction_log");

    @RequestMapping("/")
    String home() {

        LOG.trace("This will be printed on trace");
        LOG.debug("This will be printed on debug");
        LOG.info("This will be printed on info");
        LOG.warn("This will be printed on warn");
        LOG.error("This will be printed on error");
        return "Hello World! Logging information now through logback.xml to remote servergi";
    }
    @RequestMapping(value = "/{name}")
    String homeName(@PathVariable String name) {
        LOG.trace("This will be printed on trace for " + name);
        LOG.debug("This will be printed on debug for " + name);
        LOG.info("This will be printed on info for " + name);
        LOG.warn("This will be printed on warn for " + name);
        LOG.error("This will be printed on error for " + name);
        return "Hello " + name + "! Log files have been printed for you";
    }
    /* 
    @RequestMapping(value = "/transaction/{name}")
    String transactionName(@PathVariable String name) {
        transactionLogger.trace("This will be printed on trace for " + name + " in the transaction.log");
        transactionLogger.debug("This will be printed on debug for " + name + " in the transaction.log");
        transactionLogger.info("This will be printed on info for " + name + " in the transaction.log");
        transactionLogger.warn("This will be printed on warn for " + name + " in the transaction.log");
        transactionLogger.error("This will be printed on error for " + name + " in the transaction.log");
        return "Hello " + name + "! Transaction Log files have been printed for you";
    } */
    @RequestMapping(value = "/localhost/{name}")
    String localHostName(@PathVariable String name) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("from", "userA");
        data.put("to", name);

        LOG.info(FLUENTLOG.toString());
     
        FLUENTLOG.info("NAME INFO", data);
        //LOCALLOG.log("NAME INFO", data);

        return "Hello " + name + "! Logs sent straight for FluentD  have been printed for you";
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
