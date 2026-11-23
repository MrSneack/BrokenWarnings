package com.mrsneack.client.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;

public class ItemDamageManager {
    public ItemStack getHeldDamageableItem(MinecraftClient client) {
        if (client.player == null || client.world == null) {
            return ItemStack.EMPTY;
        }

        ItemStack item = client.player.getMainHandStack();
        return item.isEmpty() || !item.isDamageable() ? ItemStack.EMPTY : item;
    }

    /**
     * Returns the remaining durability in the range 0..100, or -1 when the
     * supplied stack is not damageable.
     */
    public int getDurabilityPercent(ItemStack item) {
        if (item == null || item.isEmpty() || !item.isDamageable()) {
            return -1;
        }

        int maxDamage = item.getMaxDamage();
        if (maxDamage <= 0) {
            return -1;
        }

        long remainingDamage = (long) maxDamage - item.getDamage();
        long percentage = remainingDamage * 100L / maxDamage;
        return (int) Math.max(0L, Math.min(100L, percentage));
    }
}
