package com.mrsneack.client;

import com.mrsneack.client.modules.DebugTraceManager;
import com.mrsneack.client.modules.NotificationsManager;
import com.mrsneack.client.modules.SaveManager;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;

public class BrokenWarningsClient implements ClientModInitializer {

    public static final SaveManager saveManager = new SaveManager();
    private final DebugTraceManager traceManager = new DebugTraceManager();
    private final NotificationsManager notificationManager = new NotificationsManager();
    public static SaveManager.BrokenWarningsData config = saveManager.load();
    public static MinecraftClient client =  MinecraftClient.getInstance();

    @Override
    public void onInitializeClient() {
        if(saveManager.load().debug) {
            traceManager.info("DEBUG ENABLED");
        }

        traceManager.info("Initialize client");
        config = saveManager.load();
        traceManager.info("Loaded config");
        if(client.player != null & client.world != null) {
            notificationManager.init();
            traceManager.info("Loaded notifications");
        }
    }
}
