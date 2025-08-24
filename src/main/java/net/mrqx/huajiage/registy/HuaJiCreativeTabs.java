package net.mrqx.huajiage.registy;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.item.ItemDisc;

@SuppressWarnings("all")
public class HuaJiCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HuaJiAgeMod.MODID);

    public static final RegistryObject<CreativeModeTab> HUAJI_GROUP = CREATIVE_MODE_TABS.register("huaji", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.huajiage")).icon(() -> HuaJiItems.HUAJI.get().getDefaultInstance())
            .displayItems((features, output) -> HuaJiItems.ITEMS.getEntries().forEach(item -> output.accept(item.get()))).build());

    public static final RegistryObject<CreativeModeTab> STAND_GROUP = CREATIVE_MODE_TABS.register("stand", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.huajiage")).icon(() -> HuaJiItems.DISC.get().getDefaultInstance())
            .displayItems((features, output) -> {
                HuaJiStands.REGISTRY.get().getKeys().forEach(resourceLocation -> {
                    ItemStack stack = HuaJiItems.DISC.get().getDefaultInstance();
                    stack.getOrCreateTag().putString(ItemDisc.DISC_STAND_KEY, resourceLocation.toString());
                    stack.getOrCreateTag().putInt(ItemDisc.DISC_STAND_LEVEL_KEY, 0);
                    output.accept(stack);
                });
            }).build());
}
