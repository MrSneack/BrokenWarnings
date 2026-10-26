package com.mrsneack.client.modules;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class CommandManager {
    private CommandManager() {

    }
    private void initCommands() {
        ClientCommandRegistrationCallback.EVENT.register(((commandDispatcher, commandRegistryAccess) -> {
            commandDispatcher.register(register("brokenwarnings"));
            commandDispatcher.register(register("bw"));
        }));
    }
    public LiteralArgumentBuilder<FabricClientCommandSource> register(String command) {
        return ClientCommandManager.literal(command)
                .executes(context -> {
                    context.getSource().sendFeedback(Text.translatable("commands.brokenwarnings.testmessage").formatted(Formatting.GRAY).append(Text.translatable("commands.brokenwarnings.testmessage")));
                    return 1;
                });
    }
}
