package com.zimonishim.oneblockrandomdrop.client;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;

public class Commands {

    private static final List<LiteralArgumentBuilder<FabricClientCommandSource>> commandList = new ArrayList<>();

    public static void registerAll(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        init();
        commandList.forEach(dispatcher::register);
    }

    private static void init() {
        commandList.addAll(List.of(
                ClientCommandManager.literal("printRegisteredItems").then(argument("value", IntegerArgumentType.integer(0, 1000)).executes(printAllRegisteredItems()))
        ));
    }

    public static Command<FabricClientCommandSource> printAllRegisteredItems() {
        return context -> {
            File f = new File("mapLog.txt");
            FileWriter fileWriter = null;

            try {
                fileWriter = new FileWriter(f);
                fileWriter.write("\nLog from " + LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE) + ".\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (Item item : Registry.ITEM) {
                String s = item.getTranslationKey();
                Integer anInt = context.getArgument("value", Integer.class);

                try {
                    fileWriter.write("\"" + s + "\": " + anInt + "," + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return 0;
        };
    }
}
