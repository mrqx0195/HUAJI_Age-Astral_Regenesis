package net.mrqx.huajiage.registy;

import net.minecraft.client.Minecraft;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.block.inventory.HuaJiBlenderMenu;
import net.mrqx.huajiage.block.inventory.HuaJiPolyfurnaceMenu;

import java.util.Objects;

public class HuaJiMenus {
    public static final DeferredRegister<MenuType<?>> CONTAINER_TYPES = DeferredRegister
            .create(ForgeRegistries.MENU_TYPES, HuaJiAgeMod.MODID);

    public static final RegistryObject<MenuType<HuaJiBlenderMenu>> HUAJI_BLENDER = CONTAINER_TYPES
            .register("huaji_blender", () -> new MenuType<>(HuaJiBlenderMenu::new, FeatureFlags.DEFAULT_FLAGS));

    public static final RegistryObject<MenuType<HuaJiPolyfurnaceMenu>> HUAJI_POLYFURNACE = CONTAINER_TYPES
            .register("huaji_polyfurnace", () -> IForgeMenuType.create((windowId, inv, data) -> new HuaJiPolyfurnaceMenu(windowId, inv, Objects.requireNonNull(Minecraft.getInstance().level), data.readBlockPos())));
}
