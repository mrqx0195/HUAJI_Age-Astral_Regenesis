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
import net.mrqx.huajiage.data.avaritia.HuaJiSingularityProvider;
import net.mrqx.huajiage.data.loot.HuaJiLootTables;
import net.mrqx.huajiage.data.model.HuaJiBlockStateGenerator;
import net.mrqx.huajiage.data.model.HuaJiItemModelGenerator;
import net.mrqx.huajiage.data.patchouli.HuaJiPatchouliProvider;
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
        boolean isIncludedServer = event.includeServer();
        boolean isIncludedClient = event.includeClient();

        DatapackBuiltinEntriesProvider datapackProvider = new HuaJiRegistryDataGenerator(packOutput, lookupProvider);
        generator.addProvider(true, datapackProvider);
        lookupProvider = datapackProvider.getRegistryProvider();
        generator.addProvider(true, new HuaJiPatchouliProvider(lookupProvider, packOutput));

        HuaJiBlockTagsProvider blockTagsProvider = new HuaJiBlockTagsProvider(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(isIncludedServer, blockTagsProvider);
        generator.addProvider(isIncludedServer, new HuaJiItemTagsProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));
        generator.addProvider(isIncludedServer, new HuaJiDamageTypeTagGenerator(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(isIncludedServer, new StandTagsProvider(packOutput, lookupProvider, HuaJiAgeMod.MODID, existingFileHelper));
        generator.addProvider(isIncludedServer, new HuaJiAdvancementProvider(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(isIncludedServer, new HuaJiLootTables(packOutput));
        generator.addProvider(isIncludedServer, new HuaJiRecipeGenerator(packOutput));
        generator.addProvider(isIncludedServer, new HuaJiSingularityProvider(packOutput));

        RegistrySetBuilder bladeBuilder = (new RegistrySetBuilder()).add(SlashBladeDefinition.REGISTRY_KEY, HuaJiSlashBladeDefinitions::registerAll);
        generator.addProvider(isIncludedServer, new DatapackBuiltinEntriesProvider(packOutput, lookupProvider, bladeBuilder, Set.of(HuaJiAgeMod.MODID)) {
            public String getName() {
                return "HuaJiAge SlashBlade Definition Registry";
            }
        });

        generator.addProvider(isIncludedClient, new HuaJiBlockStateGenerator(packOutput, existingFileHelper));
        generator.addProvider(isIncludedClient, new HuaJiItemModelGenerator(packOutput, existingFileHelper));
        generator.addProvider(isIncludedClient, new HuaJiSoundDefinitions(packOutput, existingFileHelper));
    }
}
