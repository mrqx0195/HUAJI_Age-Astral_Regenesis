package net.mrqx.huajiage.item.stand;

import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.mrqx.huajiage.capability.stand.StandDataCapabilityProvider;
import net.mrqx.huajiage.item.BaseItem;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.registy.HuaJiSoundEvents;
import net.mrqx.huajiage.utils.HuaJiSoundPlayer;

public class ItemArrowRequiem extends BaseItem {
    public ItemArrowRequiem() {
        super(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        pPlayer.startUsingItem(pUsedHand);
        if (!pPlayer.level().isClientSide) {
            pPlayer.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
                HuaJiSoundPlayer.playMovingSoundToClient(pPlayer, HuaJiSoundEvents.ORGA_REQUIEM_1.get());
                pPlayer.sendSystemMessage(Component.translatable("message.huajiage.orga.awake.1"));
                pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 6));
                pPlayer.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100));
                pPlayer.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 100));
                pPlayer.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100));
                pPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 7));
                pPlayer.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 6));
                pPlayer.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100));
                pPlayer.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 2));
                data.getScheduler().schedule("orgaRequiem", 100, (living, manager, gameTime) -> pPlayer.getInventory().add(HuaJiItems.ORGA_REQUIEM.get().getDefaultInstance()));
                pPlayer.awardStat(Stats.ITEM_USED.get(this));
                if (!pPlayer.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }
            });
        }
        return InteractionResultHolder.consume(itemStack);
    }
}
