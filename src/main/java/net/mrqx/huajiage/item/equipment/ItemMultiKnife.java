package net.mrqx.huajiage.item.equipment;

import com.mega.endinglib.util.time.TimeStopUtils;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.mrqx.huajiage.entity.EntityMultiKnife;
import net.mrqx.huajiage.item.BaseItem;
import net.mrqx.huajiage.registy.HuaJiItems;

public class ItemMultiKnife extends BaseItem {
    public ItemMultiKnife(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        pPlayer.startUsingItem(pUsedHand);
        if (!pLevel.isClientSide) {
            EntityMultiKnife multiKnife = new EntityMultiKnife(pPlayer, pLevel);
            multiKnife.setPos(pPlayer.getEyePosition());
            multiKnife.setShiny(itemStack.is(HuaJiItems.MULTI_KNIFE_SHINY.get()));
            multiKnife.setBaseDamage((double) (5
                    + (itemStack.is(HuaJiItems.MULTI_KNIFE_SHINY.get()) ? 3 : 0)
                    + (TimeStopUtils.isTimeStop && TimeStopUtils.andSameDimension(pLevel) ? 8 : 0)) / 2);
            multiKnife.setCritArrow(itemStack.is(HuaJiItems.MULTI_KNIFE_SHINY.get()));
            multiKnife.setKnockback(0);
            multiKnife.setPierceLevel((byte) (itemStack.is(HuaJiItems.MULTI_KNIFE_SHINY.get()) ? 3 : 0));
            multiKnife.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 2, 0);
            pLevel.addFreshEntity(multiKnife);
        }
        pPlayer.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
        if (!pPlayer.getAbilities().instabuild) {
            itemStack.hurtAndBreak(8, pPlayer, player -> player.broadcastBreakEvent(pUsedHand));
        }

        pPlayer.swing(pUsedHand, true);
        return InteractionResultHolder.sidedSuccess(itemStack, pLevel.isClientSide());
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.is(HuaJiItems.HUAJI_INGOT.get());
    }
}
