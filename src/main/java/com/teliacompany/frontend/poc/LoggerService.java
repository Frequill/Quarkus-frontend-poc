package com.teliacompany.frontend.poc;

import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@ApplicationScoped
public class LoggerService {

    public static final Logger LOG = Logger.getLogger("EventbusLogger");

    @ConsumeEvent("myLogger")
    public void ReadLogData(String data) {
        LOG.info("LoggerService got data: " + data);
    }
}
