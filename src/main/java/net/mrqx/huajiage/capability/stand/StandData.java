package net.mrqx.huajiage.capability.stand;

import net.minecraft.resources.ResourceLocation;
import net.mrqx.huajiage.registy.HuaJiStands;
import net.mrqx.huajiage.stand.AbstractStand;
import net.mrqx.huajiage.utils.Scheduler;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class StandData implements IStandData {
    @Nullable
    private ResourceLocation stand = null;
    private int level = 0;
    private boolean triggered = false;
    private String state = AbstractStand.STATE_DEFAULT;
    private long energy = 0;
    private long maxEnergy = 0;
    Scheduler scheduler = new Scheduler();

    @Override
    public void setStand(AbstractStand stand) {
        setStand(Objects.requireNonNull(HuaJiStands.REGISTRY.get().getKey(stand)));
        setState(AbstractStand.STATE_DEFAULT);
        setLevel(0);
        setTriggered(false);
        setMaxEnergy(stand.getDefaultMaxEnergy());
    }

    @Override
    public void setStand(@Nullable ResourceLocation stand) {
        this.stand = stand;
    }

    @Override
    @Nullable
    public ResourceLocation getStand() {
        return stand;
    }

    @Override
    public Scheduler getScheduler() {
        return scheduler;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setTriggered(boolean triggered) {
        this.triggered = triggered;
    }

    @Override
    public boolean isTriggered() {
        return triggered;
    }

    @Override
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void setEnergy(long energy) {
        this.energy = energy;
    }

    @Override
    public long getEnergy() {
        return energy;
    }

    @Override
    public void setMaxEnergy(long maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    @Override
    public long getMaxEnergy() {
        return maxEnergy;
    }
}
