package com.mrsneack.client;

import com.mrsneack.client.modules.DebugTraceManager;
import com.mrsneack.client.modules.ItemDamageManager;
import com.mrsneack.client.modules.SaveManager;
import net.fabricmc.api.ClientModInitializer;

public class Broken_warningsClient implements ClientModInitializer {

    public static final SaveManager saveManager = new SaveManager();
    private final DebugTraceManager traceManager = new DebugTraceManager();
    private static ItemDamageManager itemDamageManager = new ItemDamageManager();

    public static SaveManager.BrokenWarningsData config = saveManager.load();

    @Override
    public void onInitializeClient() {
        traceManager.info("Initialize client");
        config = saveManager.load();
        traceManager.info("Loaded config");
    }
}
