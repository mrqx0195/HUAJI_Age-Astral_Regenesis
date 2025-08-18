package net.mrqx.huajiage.registy;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.mrqx.huajiage.HuaJiAgeMod;

public class HuaJiCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HuaJiAgeMod.MODID);
    // TODO

    public static final RegistryObject<CreativeModeTab> HUAJI_GROUP = CREATIVE_MODE_TABS.register("huaji", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.huajiage")).icon(() -> new ItemStack(HuaJiItems.HUAJI.get()))
            .displayItems((features, output) -> HuaJiItems.ITEMS.getEntries().forEach(item -> output.accept(item.get()))).build());
}
