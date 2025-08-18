package net.mrqx.huajiage.data.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.mrqx.huajiage.HuaJiAgeMod;

import java.util.List;

public class HuaJiPlacedFeatures {
    public static final ResourceKey<PlacedFeature> HUAJI_ORE = ResourceKey.create(Registries.PLACED_FEATURE, HuaJiAgeMod.prefix("huaji_ore"));

    public static void register(BootstapContext<PlacedFeature> ctx) {
        HolderGetter<ConfiguredFeature<?, ?>> featureLookup = ctx.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> huajiOre = featureLookup.getOrThrow(HuaJiConfiguredFeatures.HUAJI_ORE);

        PlacementUtils.register(ctx, HUAJI_ORE, huajiOre, List.of(
                CountPlacement.of(2),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(VerticalAnchor.absolute(1), VerticalAnchor.absolute(7))
        ));
    }
}
