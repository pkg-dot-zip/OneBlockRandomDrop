package com.zimonishim.oneblockrandomdrop;

import com.zimonishim.oneblockrandomdrop.block.Blocks;
import com.zimonishim.oneblockrandomdrop.block.RandomDropBlock;
import com.zimonishim.oneblockrandomdrop.config.ChanceConfigContainer;
import net.fabricmc.api.ModInitializer;

public class Oneblockrandomdrop implements ModInitializer {

    public static final String MOD_ID = "obrd";

    @Override
    public void onInitialize() {
        //Set up the chanceConfig.
        ChanceConfigContainer.load();
        RandomDropBlock.initValues();

        //Registering.
        Blocks.init();
    }
}