package net.mrqx.huajiage.registy;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.item.ItemDisc;

@SuppressWarnings("unused")
public class HuaJiCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HuaJiAgeMod.MODID);

    public static final RegistryObject<CreativeModeTab> HUAJI_GROUP = CREATIVE_MODE_TABS.register("huaji", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.huajiage")).icon(() -> HuaJiItems.HUAJI.get().getDefaultInstance())
            .displayItems((features, output) -> {
                HuaJiItems.ITEMS.getEntries().forEach(item -> output.accept(item.get()));
            }).build());

    public static final RegistryObject<CreativeModeTab> STAND_GROUP = CREATIVE_MODE_TABS.register("stand", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.stand")).icon(() -> HuaJiItems.SINGULARITY.get().getDefaultInstance())
            .displayItems((features, output) -> {
                HuaJiStands.REGISTRY.get().getEntries().forEach(resourceLocation -> {
                    for (int i = 0; i <= resourceLocation.getValue().getMaxLevel(); i++) {
                        ItemStack stack = HuaJiItems.DISC.get().getDefaultInstance();
                        stack.getOrCreateTag().putString(ItemDisc.DISC_STAND_KEY, resourceLocation.getKey().location().toString());
                        stack.getOrCreateTag().putInt(ItemDisc.DISC_STAND_LEVEL_KEY, i);
                        output.accept(stack);
                    }
                });
            }).build());
}
