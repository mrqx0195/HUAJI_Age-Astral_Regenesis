package net.mrqx.huajiage.capability.stand;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;

public class StandDataCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static final Capability<IStandData> STAND_DATA = CapabilityManager.get(new CapabilityToken<>() {
    });

    protected final LazyOptional<IStandData> state = LazyOptional.of(StandData::new);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        return STAND_DATA.orEmpty(cap, state);
    }

    @Override
    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.put("temp", new CompoundTag());
        state.ifPresent(data -> tag.put("temp", IStandData.serializeNBT(data)));
        return tag.getCompound("temp");
    }

    @Override
    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    public void deserializeNBT(CompoundTag nbt) {
        state.ifPresent(data -> IStandData.deserializeNBT(nbt, data));
    }
}
