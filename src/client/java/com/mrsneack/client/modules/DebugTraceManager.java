package com.mrsneack.client.modules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DebugTraceManager {
    private static final Logger LOGGER = LoggerFactory.getLogger("BrokenWarnings");

    public void info(String message) {
        LOGGER.info("{}", message);
    }

    public void warn(String message) {
        LOGGER.warn("{}", message);
    }

    public void error(String message) {
        LOGGER.error("{}", message);
    }
}
