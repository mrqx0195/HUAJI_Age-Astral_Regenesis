package net.mrqx.huajiage.capability.stand;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StandDataCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static final Capability<IStandData> STAND_DATA = CapabilityManager.get(new CapabilityToken<>() {
    });

    protected final LazyOptional<IStandData> state = LazyOptional.of(StandData::new);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return STAND_DATA.orEmpty(cap, state);
    }

    @Override
    @SuppressWarnings("all")
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        state.ifPresent(data -> {
            tag.putString("stand", data.getStand().toString());
            tag.putInt("level", data.getLevel());
            tag.putBoolean("isTriggered", data.isTriggered());
            tag.putString("state", data.getState());
            tag.putLong("energy", data.getEnergy());
            tag.putLong("maxEnergy", data.getMaxEnergy());
        });
        return tag;
    }

    @Override
    @SuppressWarnings("all")
    public void deserializeNBT(CompoundTag nbt) {
        state.ifPresent(data -> {
            data.setStand(ResourceLocation.tryParse(nbt.getString("stand")));
            data.setLevel(nbt.getInt("level"));
            data.setTriggered(nbt.getBoolean("isTriggered"));
            data.setState(nbt.getString("state"));
            data.setEnergy(nbt.getLong("energy"));
            data.setMaxEnergy(nbt.getLong("maxEnergy"));
        });
    }
}
