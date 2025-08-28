package net.mrqx.huajiage.event.handler;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.capability.stand.StandDataCapabilityProvider;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.registy.HuaJiSoundEvents;
import net.mrqx.huajiage.utils.HuajiSoundPlayer;
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
                int castTime;
                SoundEvent soundEvent;
                switch (living.level().random.nextInt(4)) {
                    case 1 -> {
                        soundEvent = HuaJiSoundEvents.THE_WORLD_1.get();
                        castTime = 40;
                    }
                    case 2 -> {
                        soundEvent = HuaJiSoundEvents.THE_WORLD_2.get();
                        castTime = 80;
                    }
                    case 3 -> {
                        soundEvent = HuaJiSoundEvents.THE_WORLD_3.get();
                        castTime = 40;
                    }
                    default -> {
                        soundEvent = HuaJiSoundEvents.THE_WORLD.get();
                        castTime = 20;
                    }
                }
                HuajiSoundPlayer.playMovingSoundToClient(living, soundEvent, living.getSoundSource(), 2);
                StandUtils.standTimeStop(true, living, data, true, time, castTime);
                living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, time + 10, 4, false, false));
                living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, time + 10, 4, false, false));
                living.addEffect(new MobEffectInstance(MobEffects.JUMP, time + 10, 4, false, false));
                living.addEffect(new MobEffectInstance(MobEffects.REGENERATION, time + 10, 2, false, false));
                living.heal(5);
                living.sendSystemMessage(Component.translatable("stand.huajiage.the_world.skill").withStyle(ChatFormatting.GOLD));
                if (living instanceof Player player && player.level().random.nextDouble() < 0.3) {
                    player.addItem(HuaJiItems.ROAD_ROLLER.get().getDefaultInstance());
                }
            });
        } else if (event.getItem().is(HuaJiItems.REO_CHERRY.get())) {
            living.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> data.setEnergy(Math.min(data.getEnergy() + 20000, data.getMaxEnergy())));
            HuajiSoundPlayer.playMovingSoundToClient(living, HuaJiSoundEvents.REO_CHERRY.get(), living.getSoundSource());
            living.sendSystemMessage(Component.translatable("message.huajiage.reo_cherry").withStyle(ChatFormatting.GOLD));
        }
    }
}
