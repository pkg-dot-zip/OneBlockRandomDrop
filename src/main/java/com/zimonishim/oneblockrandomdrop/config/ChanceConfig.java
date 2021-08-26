package com.zimonishim.oneblockrandomdrop.config;

import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

public class ChanceConfig {

    private final Map<String, Double> chanceMap;
    private final Map<String, Double> customChangeMap;

    public static final double STANDARD_CHANCE = 1D;

    public ChanceConfig(){
        this(new HashMap<>());
    }

    public ChanceConfig(Map<String, Double> chanceMap){
        this.chanceMap = new HashMap<>();
        this.customChangeMap = new HashMap<>();
        init(chanceMap);
    }

    public void init(Map<String, Double> configChanceMap){
        for (Item item : Registry.ITEM) {
            System.out.println("Putting " + Registry.ITEM.getId(item) + " with a chance of " + 50);
            this.chanceMap.put(Registry.ITEM.getId(item).toString(), STANDARD_CHANCE);
        }

        fillMap(configChanceMap);
    }

    private void fillMap(Map<String, Double> map){
        map.forEach((s, integer) -> System.out.println(s + " " + integer));
        this.chanceMap.putAll(map);
    }

    public Map<String, Double> getChanceMap() {
        return this.chanceMap;
    }

    public Map<String, Double> getCustomChangeMap() {
        return this.customChangeMap;
    }
}