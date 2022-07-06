package com.zimonishim.oneblockrandomdrop.block;

import com.zimonishim.oneblockrandomdrop.config.ChanceConfigContainer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class RandomDropBlock extends Block {

    private static Map<String, Double> ITEMS_MAP;
    private static double TOTAL_CHANCE; //Should be the sum of all doubles of ITEMS_MAP.

    public RandomDropBlock() {
        super(FabricBlockSettings.copyOf(Blocks.BARREL).strength(0.8f));
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        player.incrementStat(Stats.MINED.getOrCreateStat(this));
        player.addExhaustion(0.005F);

        ItemStack randomItem = getRandomItemStack(world);

        if (!player.getInventory().insertStack(randomItem)) {
            player.dropItem(randomItem, false, false);
        }

        world.setBlockState(pos, state);
    }

    private static ItemStack getRandomItemStack(World world) {
        int randomInt = world.getRandom().nextInt((int) TOTAL_CHANCE);

        Item randomItem = null;
        int current = 0;

        for (Map.Entry<String, Double> entry : ITEMS_MAP.entrySet()) {
            current += entry.getValue();
            if (randomInt < current) {
                randomItem = Registry.ITEM.get(Identifier.tryParse(entry.getKey()));
                break;
            }
        }

        return new ItemStack(randomItem);
    }

    @Override
    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
        ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), getRandomItemStack(world));
        world.spawnEntity(itemEntity);
    }

    public static void initValues() {
        //These are temporary values we use to process the data.
        Map<String, Double> tempMap = new HashMap<>(ChanceConfigContainer.CHANCE_CONFIG.getChanceMap().size());
        double totalChance = 0;

        //First we filter out all the invalid data.
        for (Map.Entry<String, Double> entry : ChanceConfigContainer.CHANCE_CONFIG.getChanceMap().entrySet()) {
            String s = entry.getKey();
            Double aDouble = entry.getValue();

            if (aDouble <= 0D) continue;        //If chance is 0 or lower we do NOT add the item to the map.
            if (aDouble > 1000) aDouble = 1000.0; //If chance is higher than 1000 we set it to a 1000.
            totalChance += aDouble;             //Add the chance to the totalChance.
            tempMap.put(s, aDouble);
        }

        //Finally, we set the attributes to the new calculated ones.
        TOTAL_CHANCE = totalChance;
        ITEMS_MAP = tempMap;
    }
}