package com.mrsneack.client.modules;

import com.mrsneack.client.BrokenWarningsClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class NotificationsManager {
    public NotificationsManager() {}

    public void init() {
        ItemDamageManager itemDamageManager= new ItemDamageManager();
        int durabilityPercentage = BrokenWarningsClient.saveManager.load().durabilityPercentage;
        MinecraftClient client = MinecraftClient.getInstance();
        sendNotification(itemDamageManager, durabilityPercentage, client);
    }
    public void sendNotification(ItemDamageManager itemDamageManager, int durabilityPercentage, MinecraftClient client) {
        if(itemDamageManager.getDurabilityPercent() / durabilityPercentage <= durabilityPercentage && client.player != null && client.world != null){
            client.player.sendMessage(Text.translatable("text.bk.testmessage").append(String.valueOf(itemDamageManager.item)).append(Text.translatable("text.bk.testmessage_2")).append(String.valueOf(itemDamageManager.item.getDamage() | itemDamageManager.item.getMaxDamage()).formatted(Formatting.YELLOW)), true);
        }
    }
}
