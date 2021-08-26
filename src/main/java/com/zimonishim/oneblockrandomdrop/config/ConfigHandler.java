package com.zimonishim.oneblockrandomdrop.config;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zimonishim.oneblockrandomdrop.Oneblockrandomdrop;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.util.Map;

public class ConfigHandler {

    public static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();
    private static File configFile;
    private static final File CONFIG_DIRECTORY = FabricLoader.getInstance().getConfigDir().toFile();
    private static final String CONFIG_FILE_NAME = Oneblockrandomdrop.MOD_ID + ".json";

    public static final ChanceConfig CHANCE_CONFIG = new ChanceConfig();

    public static void init() {
        if (configFile != null) return; //If the config already exists we do not create a new one.
        configFile = new File(CONFIG_DIRECTORY, CONFIG_FILE_NAME);
    }

    public static void create() {
        try {
            save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save() throws IOException {
        init();
        new FileWriter(configFile).write(GSON.toJson(CHANCE_CONFIG.getCustomChangeMap()));
    }

    public static void load() {
        init();

        try {

            if (!configFile.exists()) {
                System.out.println("ConfigFile does NOT exist.");
                create();
            }

            if (configFile.exists()) {
                System.out.println("ConfigFile DOES exist.");

                BufferedReader bReader = new BufferedReader(new FileReader(configFile));

                Map<String, Double> savedMap = GSON.fromJson(bReader, Map.class);
                if (savedMap != null) {
                    ConfigHandler.CHANCE_CONFIG.init(savedMap);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}