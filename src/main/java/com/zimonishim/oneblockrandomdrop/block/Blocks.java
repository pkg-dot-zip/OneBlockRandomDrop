package com.zimonishim.oneblockrandomdrop.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Blocks {

    public static final Block RANDOMDROP_BLOCK = new RandomDropBlock();

    public static void init() {
        //Register the block & the blockItem.
        Registry.register(Registry.BLOCK, new Identifier("obrd", "randomdrop_block"), RANDOMDROP_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("obrd", "randomdrop_block"), new BlockItem(RANDOMDROP_BLOCK, new FabricItemSettings().group(ItemGroup.MISC)));
    }
}
