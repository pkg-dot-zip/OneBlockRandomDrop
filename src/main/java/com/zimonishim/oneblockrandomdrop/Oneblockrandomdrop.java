package com.zimonishim.oneblockrandomdrop;

import com.zimonishim.oneblockrandomdrop.config.ChanceConfigContainer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Oneblockrandomdrop implements ModInitializer {

    public static final String MOD_ID = "obrd";

    public static final Block RANDOMDROP_BLOCK = new RandomDropBlock();

    @Override
    public void onInitialize() {
        //Set up the chanceConfig.
        ChanceConfigContainer.load();
        RandomDropBlock.initValues();

        //Chance callBack.
        RegistryEntryAddedCallback.event(Registry.ITEM).register((rawId, id, object) -> RandomDropBlock.addItem(id.toString()));

        //Register the block & the blockItem.
        Registry.register(Registry.BLOCK, new Identifier("obrd", "randomdrop_block"), RANDOMDROP_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("obrd", "randomdrop_block"), new BlockItem(RANDOMDROP_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
    }
}