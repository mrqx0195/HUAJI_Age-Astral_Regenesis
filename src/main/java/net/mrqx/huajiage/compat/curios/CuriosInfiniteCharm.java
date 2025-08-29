package net.mrqx.huajiage.compat.curios;

import net.minecraft.world.item.ItemStack;
import net.mrqx.huajiage.item.ItemInfiniteCharm;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class CuriosInfiniteCharm extends ItemInfiniteCharm implements ICurioItem {
    public CuriosInfiniteCharm(Properties properties) {
        super(properties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        ICurioItem.super.curioTick(slotContext, stack);
        tick(stack, slotContext.entity());
    }
}
