package com.zimonishim.oneblockrandomdrop.config;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zimonishim.oneblockrandomdrop.Oneblockrandomdrop;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;

public class ConfigHandler {

    static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
    static final File CONFIG_DIRECTORY = FabricLoader.getInstance().getConfigDir().toFile();
    static final String DROP_CHANCE_CONFIG_NAME = Oneblockrandomdrop.MOD_ID + "_dropChance" + ".json";
    static final String SETTINGS_CONFIG_NAME = Oneblockrandomdrop.MOD_ID + "_settings" + ".json"; //TODO: Create settings config with standardChance value.

    public static final double STANDARD_CHANCE = 0D;
}