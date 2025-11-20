package net.mrqx.huajiage.compat.avaritia;

import committee.nova.mods.avaritia.core.singularity.Singularity;
import committee.nova.mods.avaritia.core.singularity.SingularityBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.registy.HuaJiItems;

import java.util.List;

public class HuaJiSingularities {
    public static final Singularity HUAJI_INGOT = new SingularityBuilder(HuaJiAgeMod.MODID, "huaji_ingot")
            .colors(0xFFFF00, 0xB99D00)
            .ingredient(Ingredient.of(HuaJiItems.HUAJI_INGOT.get()))
            .build();

    public static final Singularity BAKED_GLUTEN = new SingularityBuilder(HuaJiAgeMod.MODID, "baked_gluten")
            .colors(0xFDF83F, 0xFE9C02)
            .ingredient(Ingredient.of(HuaJiItems.BAKED_GLUTEN.get()))
            .build();

    public static final Singularity HOPE_FLOWER = new SingularityBuilder(HuaJiAgeMod.MODID, "hope_flower")
            .colors(0xB9F4FF, 0x9A49FF)
            .ingredient(Ingredient.of(HuaJiItems.HOPE_FLOWER.get()))
            .build();

    public static final Singularity ETHER_CIRCUMFLUX_BOARD = new SingularityBuilder(HuaJiAgeMod.MODID, "ether_circumflux_board")
            .colors(0xFF0300, 0xA90403)
            .ingredient(Ingredient.of(HuaJiItems.ETHER_CIRCUMFLUX_BOARD.get()))
            .build();

    public static List<Singularity> getHuaJiDefaultSingularities() {
        return List.of(HUAJI_INGOT, BAKED_GLUTEN, HOPE_FLOWER, ETHER_CIRCUMFLUX_BOARD);
    }
}
