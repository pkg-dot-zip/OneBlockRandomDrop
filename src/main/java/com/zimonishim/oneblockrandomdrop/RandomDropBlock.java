package com.zimonishim.oneblockrandomdrop;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class RandomDropBlock extends Block {

    private static final Set<Item> ILLEGAL_ITEMS = Set.of();

    public RandomDropBlock() {
        super(FabricBlockSettings.copyOf(Blocks.BARREL).breakByHand(true).breakByTool(FabricToolTags.AXES).strength(0.8f));
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

    private static ItemStack getRandomItemStack(World world){
        Item item = Registry.ITEM.getRandom(world.getRandom());

        if (ILLEGAL_ITEMS.contains(item)){
            return getRandomItemStack(world);
        }

        return new ItemStack(item);
    }

    @Override
    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
        ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), getRandomItemStack(world));
        world.spawnEntity(itemEntity);
    }
}