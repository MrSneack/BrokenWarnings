package com.mrsneack.client;

import com.mrsneack.client.modules.DebugTraceManager;
import com.mrsneack.client.modules.SaveManager;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.item.Item;
import net.minecraft.client.MinecraftClient;

public class Broken_warningsClient implements ClientModInitializer {

    public Item itemInHand;
    public MinecraftClient client = MinecraftClient.getInstance();
    public static final SaveManager saveManager = new SaveManager();
    private final DebugTraceManager traceManager = new DebugTraceManager();

    public static SaveManager.BrokenWarningsData config = saveManager.load();

    @Override
    public void onInitializeClient() {
        traceManager.info("Initialize client");

        config = saveManager.load();
        traceManager.info("Loaded config");

        if(client.player != null && client.world != null) {
            itemInHand = client.player.getMainHandStack().getItem();
        }
    }
}
