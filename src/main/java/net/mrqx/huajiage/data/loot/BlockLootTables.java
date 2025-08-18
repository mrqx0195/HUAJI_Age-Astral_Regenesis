package net.mrqx.huajiage.data.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.registy.HuaJiBlocks;
import net.mrqx.huajiage.registy.HuaJiItems;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

public class BlockLootTables extends BlockLootSubProvider {
    protected BlockLootTables() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        add(HuaJiBlocks.HUAJI_ORE.get(), createOreDrop(HuaJiBlocks.HUAJI_ORE.get(), HuaJiItems.HUAJI_FRAGMENT.get()));
        dropSelf(HuaJiBlocks.HUAJI_STAR_BLOCK.get());
        dropSelf(HuaJiBlocks.AIRSPACE_STAR_BLOCK.get());
        dropSelf(HuaJiBlocks.HUAJI_BOMB.get());
        dropSelf(HuaJiBlocks.HUAJI_ANTIMATTER_BOMB.get());
        dropSelf(HuaJiBlocks.HUAJI_BLENDER.get());
        dropSelf(HuaJiBlocks.HUAJI_POLYFURNACE.get());
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ForgeRegistries.BLOCKS.getValues().stream()
                .filter(block -> HuaJiAgeMod.MODID.equals(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getNamespace()))
                .collect(Collectors.toSet());
    }
}
