package com.zimonishim.oneblockrandomdrop.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class Blocks {

    public static final Block RANDOMDROP_BLOCK = new RandomDropBlock();
    public static final BlockItem RANDOMDROP_BLOCK_ITEM = new BlockItem(RANDOMDROP_BLOCK, new FabricItemSettings());

    public static void init() {
        //Register the block & the blockItem.
        Registry.register(Registries.BLOCK, new Identifier("obrd", "randomdrop_block"), RANDOMDROP_BLOCK);
        Registry.register(Registries.ITEM, new Identifier("obrd", "randomdrop_block"), RANDOMDROP_BLOCK_ITEM);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(content -> content.add(RANDOMDROP_BLOCK_ITEM));
    }
}
