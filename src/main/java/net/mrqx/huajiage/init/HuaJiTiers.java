package net.mrqx.huajiage.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;
import net.mrqx.huajiage.compat.HuaJiCompat;
import net.mrqx.huajiage.registy.HuaJiItems;

public enum HuaJiTiers {
    /**
     * @see net.mrqx.huajiage.item.equipment.ItemHuaJiSword
     */
    HUAJI(new ForgeTier(3, 1200, 16, 4, 20,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(HuaJiItems.HUAJI.get()))),
    /**
     * @see net.mrqx.huajiage.item.equipment.ItemHuajiStarSword
     */
    HUAJI_STAR(new ForgeTier(5, 5400, 20, 20, 20,
            Tags.Blocks.NEEDS_NETHERITE_TOOL, () -> Ingredient.of(HuaJiItems.NEUTRON_STAR_FRAGMENT.get()))),
    /**
     * @see net.mrqx.huajiage.item.equipment.ItemHuajiLaTiaoSword
     */
    HUAJI_LATIAO(new ForgeTier(5, 3998, 20, 20, 20,
            Tags.Blocks.NEEDS_NETHERITE_TOOL, () -> Ingredient.of(HuaJiCompat.LATIAO_TAG))),
    /**
     * @see net.mrqx.huajiage.item.equipment.ItemExglutenbur
     */
    EXGLUTENBUR(new ForgeTier(5, 6250, 25, 17, 30,
            Tags.Blocks.NEEDS_NETHERITE_TOOL, () -> Ingredient.of(HuaJiItems.FLASH_FLOUR.get())));

    private final ForgeTier tier;

    HuaJiTiers(ForgeTier tier) {
        this.tier = tier;
    }

    public ForgeTier get() {
        return tier;
    }
}
