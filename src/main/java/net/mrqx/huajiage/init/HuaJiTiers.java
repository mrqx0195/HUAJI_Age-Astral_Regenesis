package net.mrqx.huajiage.init;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;
import net.mrqx.huajiage.registy.HuaJiItems;

public enum HuaJiTiers {
    EXGLUTENBUR(new ForgeTier(3, 6250, 6, 9, 18,
            Tags.Blocks.NEEDS_NETHERITE_TOOL, () -> Ingredient.of(HuaJiItems.FLASH_FLOUR.get())));

    private final ForgeTier tier;

    HuaJiTiers(ForgeTier tier) {
        this.tier = tier;
    }

    public ForgeTier get() {
        return tier;
    }
}
