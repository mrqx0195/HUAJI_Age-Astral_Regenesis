package net.mrqx.huajiage.utils;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.concurrent.atomic.AtomicBoolean;

public class ItemUtils {
    public static InteractionResultHolder<ItemStack> swingAndShrinkItem(Player player, InteractionHand usedHand, ItemStack itemStack, AtomicBoolean flag) {
        player.swing(usedHand, true);
        if (flag.get()) {
            player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
            if (!player.getAbilities().instabuild) {
                itemStack.shrink(1);
            }
            return InteractionResultHolder.success(itemStack);
        }
        return InteractionResultHolder.consume(itemStack);
    }
}
