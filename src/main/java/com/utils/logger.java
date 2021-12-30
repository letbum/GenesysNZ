package com.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.invoke.MethodHandles.lookup;

public class logger {
    public void logToFile(String log_text) {
        final Logger logger = LoggerFactory.getLogger(lookup().lookupClass());
        logger.info(log_text);
    }
}
