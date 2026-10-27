package com.mrsneack.client.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;

public class ItemDamageManager {
    public ItemDamageManager() {}

    MinecraftClient client = MinecraftClient.getInstance();

    ItemStack item = client.player.getMainHandStack();

    public ItemStack init() {
        if(client.player != null && client.world != null) {
            if(!item.isEmpty() && item.isDamageable()) {
                return item;
            }
        }
        return null;
    }
    public int getDurabilityPercent() {
        item = init();
        return (int)(item.getMaxDamage() - item.getDamage()) * 100 / item.getDamage();
    }
}
