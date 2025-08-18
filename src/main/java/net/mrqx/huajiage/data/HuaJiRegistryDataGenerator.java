package net.mrqx.huajiage.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.data.worldgen.HuaJiBiomeModifiers;
import net.mrqx.huajiage.data.worldgen.HuaJiConfiguredFeatures;
import net.mrqx.huajiage.data.worldgen.HuaJiPlacedFeatures;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class HuaJiRegistryDataGenerator extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DAMAGE_TYPE, HuaJiDamageTypes::register)
            .add(Registries.PLACED_FEATURE, HuaJiPlacedFeatures::register)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, HuaJiBiomeModifiers::register)
            .add(Registries.CONFIGURED_FEATURE, HuaJiConfiguredFeatures::register);

    public HuaJiRegistryDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of("minecraft", HuaJiAgeMod.MODID));
    }
}
