package com.example.demo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.HashMap;
import java.util.Map;
import org.fluentd.logger.FluentLogger;


@EnableScheduling
@Configuration
@ConditionalOnProperty(name = "spring.enable.scheduling")
public class ScheduleEnabling {
    
    String podName = System.getenv("HOSTNAME");
    private static final Logger FLUENTLOG = LoggerFactory.getLogger("fluent_transaction_log");

    //private static FluentLogger LOG = FluentLogger.getLogger("fluent_transaction_log", "localhost", 24224);
    @Scheduled(fixedRateString = "${sample.schedule.string}")
    public void scheduleTaskWithFixedRate() throws InterruptedException {
        sendLogsThroughLogger();
    }

    public void sendLogsThroughLogger() throws InterruptedException {

        System.out.println("Sending lods from " + podName + " at " +  Thread.currentThread());
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("from", "userA from pod: "+ podName);
        data.put("to", "userB");
       
        for(int i=0;i<5;i++){
            /*LOG.log("Trace", data);
            LOG.log("Debug", data);
            LOG.log("Info", data);
            LOG.log("Warn", data);
            LOG.log("Error", data);*/
            FLUENTLOG.trace("TRACE  with data Round " + i + " from pod: " + podName);
            FLUENTLOG.debug(" DEBUG with data Round " + i + " from pod: " + podName);
            FLUENTLOG.info(" INFO with data Round " + i + " from pod: " + podName);
            FLUENTLOG.warn( " WARN with data Round " + i + " from pod: " + podName);
            FLUENTLOG.error("ERROR with data Round " + i + " from pod: " + podName);

        }

    }


}
