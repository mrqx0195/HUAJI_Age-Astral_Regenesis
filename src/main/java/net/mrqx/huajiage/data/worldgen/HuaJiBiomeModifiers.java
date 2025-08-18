package net.mrqx.huajiage.data.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.mrqx.huajiage.HuaJiAgeMod;

public class HuaJiBiomeModifiers {
    public static final ResourceKey<BiomeModifier> HUAJI_ORE = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, HuaJiAgeMod.prefix("huaji_ore"));

    public static void register(BootstapContext<BiomeModifier> ctx) {
        HolderGetter<Biome> biomeLookup = ctx.lookup(Registries.BIOME);
        HolderSet<Biome> isOverworld = biomeLookup.getOrThrow(BiomeTags.IS_OVERWORLD);

        HolderGetter<PlacedFeature> featureLookup = ctx.lookup(Registries.PLACED_FEATURE);
        Holder<PlacedFeature> huajiOre = featureLookup.getOrThrow(HuaJiPlacedFeatures.HUAJI_ORE);

        ctx.register(HUAJI_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(isOverworld, HolderSet.direct(huajiOre), GenerationStep.Decoration.UNDERGROUND_ORES));
    }
}
