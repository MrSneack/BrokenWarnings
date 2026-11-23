package com.mrsneack.client.modules;

import net.minecraft.util.Formatting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DebugTraceManager {
    private static final Logger LOGGER = LoggerFactory.getLogger("BrokenWarnings");
    public DebugTraceManager() {

    }
    public void info(String message) {
        LOGGER.info(message);
    }
    public void warn(String message) {
        LOGGER.warn(message.formatted(Formatting.YELLOW));
    }
    public void error(String message) {
        LOGGER.error(message.formatted(Formatting.RED));
    }
}
