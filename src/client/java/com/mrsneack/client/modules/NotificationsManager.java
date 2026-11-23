package com.mrsneack.client.modules;

import com.mrsneack.client.BrokenWarningsClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class NotificationsManager {
    private final ItemDamageManager itemDamageManager = new ItemDamageManager();

    private ItemStack trackedStack = ItemStack.EMPTY;
    private boolean warningSent;

    public void tick(MinecraftClient client) {
        if (client.player == null || client.world == null) {
            reset();
            return;
        }

        ItemStack item = itemDamageManager.getHeldDamageableItem(client);
        if (item.isEmpty()) {
            reset();
            return;
        }

        // A new stack should be eligible for its own warning.
        if (item != trackedStack) {
            trackedStack = item;
            warningSent = false;
        }

        int durabilityPercentage = itemDamageManager.getDurabilityPercent(item);
        int warningThreshold = BrokenWarningsClient.config.durabilityPercentage;

        if (durabilityPercentage >= 0 && durabilityPercentage <= warningThreshold && !warningSent) {
            Text message = Text.translatable(
                    "text.brokenwarnings.low_durability",
                    item.getName(),
                    durabilityPercentage + "%"
            ).formatted(Formatting.YELLOW);

            client.player.sendMessage(message, true);
            warningSent = true;
        } else if (durabilityPercentage > warningThreshold) {
            // Allow another warning if the item is repaired above the threshold.
            warningSent = false;
        }
    }

    private void reset() {
        trackedStack = ItemStack.EMPTY;
        warningSent = false;
    }
}
