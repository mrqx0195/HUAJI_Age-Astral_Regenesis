package net.mrqx.huajiage.event.handler;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.capability.stand.StandDataCapabilityProvider;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.registy.HuaJiSoundEvents;
import net.mrqx.huajiage.utils.HuaJiSoundPlayer;
import net.mrqx.huajiage.utils.StandUtils;

@Mod.EventBusSubscriber
public class FoodEventHandler {
    @SubscribeEvent
    public static void onLivingEntityUseItemEventFinish(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity().level().isClientSide) {
            return;
        }
        LivingEntity living = event.getEntity();
        if (event.getItem().is(HuaJiItems.EGG_RICE.get())) {
            living.heal(10);
            living.sendSystemMessage(Component.translatable("message.huajiage.egg_rice").withStyle(ChatFormatting.GOLD));
        } else if (event.getItem().is(HuaJiItems.ULTIMATE_EGG_RICE.get())) {
            living.setHealth(living.getHealth() + 22);
            living.sendSystemMessage(Component.translatable("message.huajiage.egg_rice").withStyle(ChatFormatting.GOLD));
        } else if (event.getItem().is(HuaJiItems.DIO_BREAD.get())) {
            living.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
                int time = 100;
                StandUtils.foodTimeStop(living, data, time);
            });
        } else if (event.getItem().is(HuaJiItems.REO_CHERRY.get())) {
            living.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> data.setEnergy(Math.min(data.getEnergy() + 20000, data.getMaxEnergy())));
            HuaJiSoundPlayer.playMovingSoundToClient(living, HuaJiSoundEvents.REO_CHERRY.get());
            living.sendSystemMessage(Component.translatable("message.huajiage.reo_cherry").withStyle(ChatFormatting.GOLD));
        }
    }
}
