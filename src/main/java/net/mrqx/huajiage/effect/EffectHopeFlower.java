package net.mrqx.huajiage.effect;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class EffectHopeFlower extends MobEffect {
    public EffectHopeFlower() {
        super(MobEffectCategory.BENEFICIAL, 0x0E30D0);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return ImmutableList.of();
    }
}
