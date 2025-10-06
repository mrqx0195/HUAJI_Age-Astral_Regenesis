package net.mrqx.huajiage.compat.ltc2.spiceevent;

import com.doggystudio.chirencqr.ltc.server.spiceevent.AbstractSpiceEvent;
import com.mega.endinglib.util.time.TimeStopUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.capability.stand.StandDataCapabilityProvider;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.registy.HuaJiSoundEvents;
import net.mrqx.huajiage.utils.HuaJiSoundPlayer;
import net.mrqx.huajiage.utils.StandUtils;

import java.util.List;

public class TimeStopSpiceEvent extends AbstractSpiceEvent {
    public TimeStopSpiceEvent() {
        super(HuaJiAgeMod.MODID, "time_stop");
    }

    @Override
    public void drive(ItemStack stack, Level level, LivingEntity living, List<MobEffect> effects, int latiaoGrade, int afterFlavouringEffectLevel) {
        living.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
            int time = 60 + latiaoGrade * 20;
            int castTime;
            if (TimeStopUtils.isTimeStop && TimeStopUtils.andSameDimension(living.level())) {
                castTime = 0;
            } else {
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
                HuaJiSoundPlayer.playMovingSoundToClient(living, soundEvent, 2);
            }
            StandUtils.castStandTimeStop(true, living, data, true, time, castTime);
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
    }
}
