package net.mrqx.huajiage.compat.avaritia;

import committee.nova.mods.avaritia.common.item.singularity.Singularity;
import net.minecraft.world.item.crafting.Ingredient;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.registy.HuaJiItems;

import java.util.List;

public class HuaJiSingularities {
    public static final Singularity HUAJI_INGOT = new Singularity(HuaJiAgeMod.prefix("huaji_ingot"),
            "singularity.huajiage.huaji_ingot", new int[]{0xFFFF00, 0xB99D00}, Ingredient.of(HuaJiItems.HUAJI_INGOT.get()));

    public static final Singularity BAKED_GLUTEN = new Singularity(HuaJiAgeMod.prefix("baked_gluten"),
            "singularity.huajiage.baked_gluten", new int[]{0xFDF83F, 0xFE9C02}, Ingredient.of(HuaJiItems.BAKED_GLUTEN.get()));

    public static final Singularity HOPE_FLOWER = new Singularity(HuaJiAgeMod.prefix("hope_flower"),
            "singularity.huajiage.hope_flower", new int[]{0xB9F4FF, 0x9A49FF}, Ingredient.of(HuaJiItems.HOPE_FLOWER.get()));

    public static final Singularity ETHER_CIRCUMFLUX_BOARD = new Singularity(HuaJiAgeMod.prefix("ether_circumflux_board"),
            "singularity.huajiage.ether_circumflux_board", new int[]{0xFF0300, 0xA90403}, Ingredient.of(HuaJiItems.ETHER_CIRCUMFLUX_BOARD.get()));

    public static List<Singularity> getHuaJiDefaultSingularities() {
        return List.of(HUAJI_INGOT, BAKED_GLUTEN, HOPE_FLOWER, ETHER_CIRCUMFLUX_BOARD);
    }
}
