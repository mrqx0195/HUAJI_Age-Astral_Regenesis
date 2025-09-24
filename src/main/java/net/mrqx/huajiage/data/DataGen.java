package net.mrqx.huajiage.data;

import mods.flammpfeil.slashblade.registry.slashblade.SlashBladeDefinition;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.data.advancement.HuaJiAdvancementProvider;
import net.mrqx.huajiage.data.loot.HuaJiLootTables;
import net.mrqx.huajiage.data.model.HuaJiBlockStateGenerator;
import net.mrqx.huajiage.data.model.HuaJiItemModelGenerator;
import net.mrqx.huajiage.data.slashblade.HuaJiSlashBladeDefinitions;
import net.mrqx.huajiage.data.sound.HuaJiSoundDefinitions;
import net.mrqx.huajiage.data.tag.HuaJiBlockTagsProvider;
import net.mrqx.huajiage.data.tag.HuaJiDamageTypeTagGenerator;
import net.mrqx.huajiage.data.tag.HuaJiItemTagsProvider;
import net.mrqx.huajiage.data.tag.stand.StandTagsProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {
    @SubscribeEvent
    @SuppressWarnings("all")
    public static void dataGen(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        DatapackBuiltinEntriesProvider datapackProvider = new HuaJiRegistryDataGenerator(packOutput, lookupProvider);
        generator.addProvider(true, datapackProvider);
        lookupProvider = datapackProvider.getRegistryProvider();

        HuaJiBlockTagsProvider blockTagsProvider = new HuaJiBlockTagsProvider(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new HuaJiItemTagsProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));
        generator.addProvider(event.includeServer(), new HuaJiDamageTypeTagGenerator(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new StandTagsProvider(packOutput, lookupProvider, HuaJiAgeMod.MODID, existingFileHelper));
        generator.addProvider(event.includeServer(), new HuaJiAdvancementProvider(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new HuaJiLootTables(packOutput));
        generator.addProvider(event.includeServer(), new HuaJiRecipeGenerator(packOutput));

        RegistrySetBuilder bladeBuilder = (new RegistrySetBuilder()).add(SlashBladeDefinition.REGISTRY_KEY, HuaJiSlashBladeDefinitions::registerAll);
        generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(packOutput, lookupProvider, bladeBuilder, Set.of(HuaJiAgeMod.MODID)) {
            public String getName() {
                return "HuaJiAge SlashBlade Definition Registry";
            }
        });

        generator.addProvider(event.includeClient(), new HuaJiBlockStateGenerator(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new HuaJiItemModelGenerator(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new HuaJiSoundDefinitions(packOutput, existingFileHelper));
    }
}
