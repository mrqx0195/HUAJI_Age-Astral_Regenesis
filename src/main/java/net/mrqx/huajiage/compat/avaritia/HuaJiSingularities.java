package net.mrqx.huajiage.compat.avaritia;

import committee.nova.mods.avaritia.core.singularity.Singularity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.RegistryObject;
import net.mrqx.huajiage.registy.HuaJiItems;

import java.util.List;

@SuppressWarnings("DataFlowIssue")
public class HuaJiSingularities {
    public static final Singularity HUAJI_INGOT = createSingularity(HuaJiItems.HUAJI_INGOT, 0xFFFF00, 0xB99D00);

    public static final Singularity BAKED_GLUTEN = createSingularity(HuaJiItems.BAKED_GLUTEN, 0xFDF83F, 0xFE9C02);

    public static final Singularity HOPE_FLOWER = createSingularity(HuaJiItems.HOPE_FLOWER, 0xB9F4FF, 0x9A49FF);

    public static final Singularity ETHER_CIRCUMFLUX_BOARD = createSingularity(HuaJiItems.ETHER_CIRCUMFLUX_BOARD, 0xFF0300, 0xA90403);

    public static List<Singularity> getHuaJiDefaultSingularities() {
        return List.of(HUAJI_INGOT, BAKED_GLUTEN, HOPE_FLOWER, ETHER_CIRCUMFLUX_BOARD);
    }

    private static Singularity createSingularity(RegistryObject<Item> item, int overlayColor, int underlayColor) {
        return Singularity.create(item.getId(),
                "singularity.huajiage." + item.getId().getPath(),
                new int[]{overlayColor, underlayColor},
                Ingredient.of(item.get()));
    }
}
