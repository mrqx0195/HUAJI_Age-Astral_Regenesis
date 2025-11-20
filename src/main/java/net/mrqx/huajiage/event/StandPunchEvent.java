package net.mrqx.huajiage.event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import net.mrqx.huajiage.capability.stand.IStandData;
import net.mrqx.huajiage.stand.Stand;

@Cancelable
public class StandPunchEvent extends LivingEvent {
    private final IStandData data;
    private final Stand stand;
    private float distanceFactor;
    private float angleThreshold;

    public StandPunchEvent(LivingEntity entity, IStandData data, Stand stand,
                           float distanceFactor, float angleThreshold) {
        super(entity);
        this.data = data;
        this.stand = stand;
        this.distanceFactor = distanceFactor;
        this.angleThreshold = angleThreshold;
    }

    public void setDistanceFactor(float distanceFactor) {
        this.distanceFactor = distanceFactor;
    }

    public void setAngleThreshold(float angleThreshold) {
        this.angleThreshold = angleThreshold;
    }

    public float getAngleThreshold() {
        return angleThreshold;
    }

    public float getDistanceFactor() {
        return distanceFactor;
    }

    public Stand getStand() {
        return stand;
    }

    public IStandData getData() {
        return data;
    }
}
