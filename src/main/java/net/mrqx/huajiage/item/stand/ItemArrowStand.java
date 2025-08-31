package net.mrqx.huajiage.item.stand;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
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
import net.mrqx.huajiage.config.HuaJiCommonConfig;
import net.mrqx.huajiage.item.BaseItem;
import net.mrqx.huajiage.registy.HuaJiStands;
import net.mrqx.huajiage.stand.Stand;
import net.mrqx.huajiage.utils.HuaJiSoundPlayer;

public class ItemArrowStand extends BaseItem {
    public ItemArrowStand() {
        super(new Item.Properties().rarity(Rarity.RARE).stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        pPlayer.startUsingItem(pUsedHand);
        if (!pPlayer.level().isClientSide) {
            pPlayer.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
                Stand stand = Stand.getStand(data.getStand());
                if (stand == null) {
                    if (pLevel.random.nextDouble() < HuaJiCommonConfig.ARROW_STAND_CHANCE.get()) {
                        String stand1 = HuaJiCommonConfig.ARROW_STAND_RANGE_MAP.get(pLevel.random.nextDouble() * HuaJiCommonConfig.arrowStandTotalRange);
                        if (stand1 != null) {
                            Stand stand2 = HuaJiStands.REGISTRY.get().getValue(new ResourceLocation(stand1));
                            if (stand2 != null) {
                                data.setStand(stand2);
                                data.setLevel(0);
                                data.setMaxEnergy(stand2.getMaxEnergy(pPlayer, data));
                                pPlayer.sendSystemMessage(Component.translatable("message.huajiage.stand.gain", Component.translatable(stand2.getDescriptionId())).withStyle(ChatFormatting.AQUA, ChatFormatting.BOLD));
                            } else {
                                throw new IllegalArgumentException("Unknown registry key of stand: " + stand1);
                            }
                        }
                    } else {
                        pPlayer.addEffect(new MobEffectInstance(MobEffects.POISON, 500, 6));
                        pPlayer.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 500));
                        pPlayer.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 500));
                        pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 500, 3));
                        pPlayer.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 500, 6));
                        pPlayer.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 500));
                        pPlayer.addEffect(new MobEffectInstance(MobEffects.WITHER, 500, 2));
                        HuaJiSoundPlayer.playMovingSoundToClient(pPlayer, SoundEvents.WITHER_HURT);
                        pPlayer.sendSystemMessage(Component.translatable("message.huajiage.stand.fail").withStyle(ChatFormatting.RED, ChatFormatting.ITALIC));
                    }
                    pPlayer.awardStat(Stats.ITEM_USED.get(this));
                    if (!pPlayer.getAbilities().instabuild) {
                        itemStack.shrink(1);
                    }
                } else {
                    pPlayer.sendSystemMessage(Component.translatable("message.huajiage.tarot.stand.failed").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
                }
            });
        }
        return InteractionResultHolder.consume(itemStack);
    }
}
