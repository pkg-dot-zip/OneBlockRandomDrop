package com.zimonishim.oneblockrandomdrop;

import com.zimonishim.oneblockrandomdrop.config.ChanceConfigContainer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.lwjgl.glfw.GLFW;

public class Oneblockrandomdrop implements ModInitializer {

    public static final String MOD_ID = "obrd";

    public static final Block RANDOMDROP_BLOCK = new RandomDropBlock();

    //Debug part.
    private static boolean debugDone = false;
    private static KeyBinding printItemMapKeyBinding;

    @Override
    public void onInitialize() {
        //Set up the chanceConfig.
        ChanceConfigContainer.load();
        RandomDropBlock.initValues();

        //Chance callBack.
        RegistryEntryAddedCallback.event(Registry.ITEM).register((rawId, id, object) -> RandomDropBlock.addItem(id.toString()));

        //Register the block & the blockItem.
        Registry.register(Registry.BLOCK, new Identifier("obrd", "randomdrop_block"), RANDOMDROP_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("obrd", "randomdrop_block"), new BlockItem(RANDOMDROP_BLOCK, new FabricItemSettings().group(ItemGroup.MISC)));

        //Debug part.
        printItemMapKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.obrd.printMap", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_COMMA, // The keycode of the key
                "debug.obrd.main" // The translation key of the keybinding's category.
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (printItemMapKeyBinding.wasPressed()) {
                client.player.sendMessage(new LiteralText("Key 1 was pressed!"), false);
                if (!debugDone){
                    debugDone = true;
                    RandomDropBlock.printItemMap();
                }
            }
        });
    }
}