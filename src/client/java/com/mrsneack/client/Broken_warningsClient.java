package com.mrsneack.client;

import com.mrsneack.client.modules.CommandManager;
import com.mrsneack.client.modules.SaveManager;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.item.Item;
import net.minecraft.client.MinecraftClient;

public class Broken_warningsClient implements ClientModInitializer {

    public Item itemInHand;
    public MinecraftClient client = MinecraftClient.getInstance();
    public static final SaveManager saveManager = new SaveManager();

    public static SaveManager.BrokenWarningsData config = saveManager.load();

    @Override
    public void onInitializeClient() {
        System.out.println("[BrokenWarnings] Initializing Client");

        config = saveManager.load();
        System.out.println("[BrokenWarnings] Loaded config");

        if(client.player != null && client.world != null) {
            itemInHand = client.player.getMainHandStack().getItem();
        }
    }
}
