package net.mrqx.huajiage.compat.ltc2.spiceevent;

import com.doggystudio.chirencqr.ltc.server.spiceevent.AbstractSpiceEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.capability.stand.StandDataCapabilityProvider;
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
            StandUtils.foodTimeStop(living, data, time);
        });
    }

}
