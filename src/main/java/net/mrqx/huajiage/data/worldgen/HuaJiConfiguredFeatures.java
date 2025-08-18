package net.mrqx.huajiage.data.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.registy.HuaJiBlocks;

import java.util.List;

public class HuaJiConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUAJI_ORE = ResourceKey.create(Registries.CONFIGURED_FEATURE, HuaJiAgeMod.prefix("huaji_ore"));
    public static final List<OreConfiguration.TargetBlockState> HUAJI_TARGET_STATES = List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), HuaJiBlocks.HUAJI_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), HuaJiBlocks.HUAJI_ORE.get().defaultBlockState())
    );
    public static final OreConfiguration HUAJI_ORE_CONFIGURATION = new OreConfiguration(HUAJI_TARGET_STATES, 8);

    public static void register(BootstapContext<ConfiguredFeature<?, ?>> ctx) {
        FeatureUtils.register(ctx, HUAJI_ORE, Feature.ORE, HUAJI_ORE_CONFIGURATION);
    }
}
