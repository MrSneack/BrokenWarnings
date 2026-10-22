package com.mrsneack.client.modules;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class CommandManager {
    private CommandManager() {

    }
    private void initCommands() {
        CommandRegistrationCallback.EVENT.register(((commandDispatcher, commandRegistryAccess, registrationEnvironment) -> {
            commandDispatcher.register(register("brokenwarnings"));
            commandDispatcher.register(register("bw"));
        }));
    }
    public LiteralArgumentBuilder<ServerCommandSource> register(String command) {
        return ClientCommandManager.literal(command)
                .executes(context -> {
                    context.getSource().sendFeedback(Text.translatable("commands.brokenwarnings.defaultConclusion").formatted(Formatting.GRAY).append());
                })
    }
}
