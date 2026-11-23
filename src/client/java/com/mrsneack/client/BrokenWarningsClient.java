package com.mrsneack.client;

import com.mrsneack.client.modules.CommandManager;
import com.mrsneack.client.modules.DebugTraceManager;
import com.mrsneack.client.modules.NotificationsManager;
import com.mrsneack.client.modules.SaveManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public final class BrokenWarningsClient implements ClientModInitializer {

    public static final SaveManager saveManager = new SaveManager();
    public static final SaveManager.BrokenWarningsData config = saveManager.load();

    private final DebugTraceManager traceManager = new DebugTraceManager();
    private final NotificationsManager notificationManager = new NotificationsManager();

    @Override
    public void onInitializeClient() {
        if (config.debug) {
            traceManager.info("DEBUG ENABLED");
        }

        traceManager.info("Initialize client");

        CommandManager.initialize();
        traceManager.info("Registered client commands");

        // Register during initialization; player/world become available later.
        ClientTickEvents.END_CLIENT_TICK.register(notificationManager::tick);
        traceManager.info("Registered durability notifications");
    }
}
