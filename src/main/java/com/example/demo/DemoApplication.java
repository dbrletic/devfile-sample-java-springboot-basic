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
public class DemoApplication {

    Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	private static FluentLogger LOG = FluentLogger.getLogger("transaction_log.ng.double.encoded");
    private static FluentLogger LOGMANUAL = FluentLogger.getLogger("transaction_log.ng.double.encoded", "cloudwatch-openshift-logforwarding-cloudwatch.openshift-logging.svc", 24224);
    private static FluentLogger LOGLOCAL = FluentLogger.getLogger("transaction_log.ng.double.encoded", "localhost", 24224);
        
    @RequestMapping("/")
    String home() {
        System.out.println("Starting FluentLogger through logback");
        logger.info("Starting the default logger");
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("from", "userA");
        data.put("to", "userB");
        LOG.log("follow", data);
        return "Hello World! Logging information now through logback.xml to remote server";
    }
    
    @RequestMapping("/manual")
    String manual(){
        System.out.println("Starting FluentLogger through manaul connection to remote server");
        logger.info("Starting the manual logger");
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("from", "userA");
        data.put("to", "userB");
        LOGMANUAL.log("follow", data);
        LOGMANUAL.flush();
        return "Hello World! Logging information now through manual connection";
    }

    @RequestMapping("/local")
    String local(){
        //Just to see if the java code will bomb out with a connection not running
        System.out.println("Starting FluentLogger through local host");
        logger.info("Starting the local logger");
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("from", "userA");
        data.put("to", "userB");
        LOGLOCAL.log("follow", data);
        LOGLOCAL.flush();
        return "Hello World! Logging information now through local host";
    }

   @RequestMapping(value = "/{name}")
   String homeName(@PathVariable String name) {
        return "Hello " + name + "!";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
