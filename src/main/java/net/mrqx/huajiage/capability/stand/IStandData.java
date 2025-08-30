package net.mrqx.huajiage.capability.stand;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.mrqx.huajiage.stand.Stand;
import net.mrqx.huajiage.utils.Scheduler;
import org.jetbrains.annotations.Nullable;

public interface IStandData {
    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    static CompoundTag serializeNBT(IStandData data) {
        CompoundTag tag = new CompoundTag();
        tag.putString("stand", String.valueOf(data.getStand()));
        tag.putInt("level", data.getLevel());
        tag.putBoolean("isTriggered", data.isTriggered());
        tag.putString("state", data.getState());
        tag.putLong("energy", data.getEnergy());
        tag.putLong("maxEnergy", data.getMaxEnergy());
        return tag;
    }

    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    static void deserializeNBT(CompoundTag nbt, IStandData data) {
        data.setStand(ResourceLocation.tryParse(nbt.getString("stand")));
        data.setLevel(nbt.getInt("level"));
        data.setTriggered(nbt.getBoolean("isTriggered"));
        data.setState(nbt.getString("state"));
        data.setEnergy(nbt.getLong("energy"));
        data.setMaxEnergy(nbt.getLong("maxEnergy"));
    }

    void setStand(Stand stand);

    void setStand(@Nullable ResourceLocation stand);

    @Nullable
    ResourceLocation getStand();

    Scheduler getScheduler();

    void setLevel(int level);

    int getLevel();

    void setTriggered(boolean triggered);

    boolean isTriggered();

    void setState(String state);

    String getState();

    void setEnergy(long energy);

    long getEnergy();

    void setMaxEnergy(long maxEnergy);

    long getMaxEnergy();
}
