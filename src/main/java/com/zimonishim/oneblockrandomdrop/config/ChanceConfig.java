package com.zimonishim.oneblockrandomdrop.config;

import java.util.HashMap;
import java.util.Map;

public class ChanceConfig {

    private final Map<String, Double> chanceMap;
    private final Map<String, Double> customChangeMap;

    public ChanceConfig() {
        this(new HashMap<>());
    }

    public ChanceConfig(Map<String, Double> chanceMap) {
        this.chanceMap = new HashMap<>();
        this.customChangeMap = new HashMap<>();
        init(chanceMap);
    }

    public void init(Map<String, Double> configChanceMap) {
        fillMap(configChanceMap);
    }

    private void fillMap(Map<String, Double> map) {
//        map.forEach((s, integer) -> System.out.println(s + " " + integer));
        this.chanceMap.putAll(map);
    }

    public Map<String, Double> getChanceMap() {
        return this.chanceMap;
    }

    public Map<String, Double> getCustomChangeMap() {
        return this.customChangeMap;
    }
}