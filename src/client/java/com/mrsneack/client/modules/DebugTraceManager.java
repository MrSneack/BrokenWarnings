package com.mrsneack.client.modules;

public class DebugTraceManager {
    public DebugTraceManager() {

    }
    public void info(String message) {
        System.out.println("[BrokenWarnings] " + message);
    }
    public void error(String message) {
        System.out.println("[BrokenWarnings] ERROR:" + message);
    }
}
