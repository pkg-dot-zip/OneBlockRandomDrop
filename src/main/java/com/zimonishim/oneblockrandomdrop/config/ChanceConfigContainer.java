package com.zimonishim.oneblockrandomdrop.config;

import java.io.*;
import java.util.Map;

import static com.zimonishim.oneblockrandomdrop.config.ConfigHandler.*;

public class ChanceConfigContainer {

    private static File configFile;
    public static final ChanceConfig CHANCE_CONFIG = new ChanceConfig();

    public static void init() {
        if (configFile != null) return; //If the config already exists we do not create a new one.
        configFile = new File(CONFIG_DIRECTORY, DROP_CHANCE_CONFIG_NAME);
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
                create();
            }

            if (configFile.exists()) {
                BufferedReader bReader = new BufferedReader(new FileReader(configFile));

                Map<String, Double> savedMap = GSON.fromJson(bReader, Map.class);
                if (savedMap != null) {
                    ChanceConfigContainer.CHANCE_CONFIG.init(savedMap);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}