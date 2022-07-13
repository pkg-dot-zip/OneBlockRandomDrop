package com.zimonishim.oneblockrandomdrop.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;

public class OneblockrandomdropClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Commands.registerAll(ClientCommandManager.DISPATCHER);
    }
}
