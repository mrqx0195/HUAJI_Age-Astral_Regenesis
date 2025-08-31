package net.mrqx.huajiage.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.mrqx.huajiage.entity.EntityOrgaHairKnife;
import net.mrqx.huajiage.utils.HuaJiSoundPlayer;

public class ItemOrgaHairKnife extends BaseItem {
    public ItemOrgaHairKnife() {
        super(new Item.Properties().rarity(Rarity.UNCOMMON));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        if (!pLevel.isClientSide) {
            HuaJiSoundPlayer.playMovingSoundToClient(pPlayer, SoundEvents.ARROW_SHOOT, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
            EntityOrgaHairKnife hair = new EntityOrgaHairKnife(pPlayer, pLevel);
            hair.setItem(itemstack);
            hair.setDamage(pPlayer.getMaxHealth() / 2);
            hair.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 0);
            pLevel.addFreshEntity(hair);
        }
        pPlayer.awardStat(Stats.ITEM_USED.get(this));
        if (!pPlayer.getAbilities().instabuild) {
            itemstack.shrink(1);
        }

        pPlayer.swing(pUsedHand, true);
        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }
}
