package com.mrsneack.client.modules;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public final class CommandManager {
    private CommandManager() {
    }

    public static void initialize() {
        ClientCommandRegistrationCallback.EVENT.register((commandDispatcher, commandRegistryAccess) -> {
            commandDispatcher.register(register("brokenwarnings"));
            commandDispatcher.register(register("bw"));
        });
    }

    private static LiteralArgumentBuilder<FabricClientCommandSource> register(String command) {
        return ClientCommandManager.literal(command)
                .executes(context -> {
                    context.getSource().sendFeedback(
                            Text.translatable("commands.brokenwarnings.status").formatted(Formatting.GREEN)
                    );
                    return 1;
                });
    }
}
