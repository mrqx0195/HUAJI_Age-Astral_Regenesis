package net.mrqx.huajiage.capability.stand;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.mrqx.huajiage.stand.Stand;
import net.mrqx.huajiage.utils.Scheduler;
import org.jetbrains.annotations.Nullable;

public interface IStandData {
    /**
     * 序列化数据至NBT
     *
     * @param data 替身数据
     * @return 序列化结果
     */
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

    /**
     * 反序列化NBT至数据
     *
     * @param nbt  NBT数据
     * @param data 替身数据
     */
    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    static void deserializeNBT(CompoundTag nbt, IStandData data) {
        data.setStand(ResourceLocation.tryParse(nbt.getString("stand")));
        data.setLevel(nbt.getInt("level"));
        data.setTriggered(nbt.getBoolean("isTriggered"));
        data.setState(nbt.getString("state"));
        data.setEnergy(nbt.getLong("energy"));
        data.setMaxEnergy(nbt.getLong("maxEnergy"));
    }

    /**
     * 设置替身并重置替身数据
     *
     * @param stand 替身实例
     */
    void setStandAndResetData(Stand stand);

    /**
     * 设置替身
     *
     * @param stand 替身的资源地址
     */
    void setStand(@Nullable ResourceLocation stand);

    /**
     * 获取替身
     *
     * @return 当前替身
     */
    @Nullable
    ResourceLocation getStand();

    /**
     * 获取调度器
     *
     * @return 一个调度器实例
     */
    Scheduler getScheduler();

    /**
     * 设置替身等级
     *
     * @param level 替身等级
     */
    void setLevel(int level);

    /**
     * 获取替身等级
     *
     * @return 替身等级
     */
    int getLevel();

    /**
     * 设置替身触发状态
     *
     * @param triggered 替身是否被触发
     */
    void setTriggered(boolean triggered);

    /**
     * 获取替身是否被触发
     *
     * @return 替身是否被触发
     */
    boolean isTriggered();

    /**
     * 设置替身状态
     *
     * @param state 替身状态
     * @see Stand#getStates()
     */
    void setState(String state);

    /**
     * 获取替身状态
     *
     * @return 当前替身状态
     * @see Stand#getStates()
     */
    String getState();

    /**
     * 设置当前精神力
     *
     * @param energy 精神力
     */
    void setEnergy(long energy);

    /**
     * 获取当前精神力
     *
     * @return 当前精神力
     */
    long getEnergy();

    /**
     * 设置替身最大精神力
     *
     * @param maxEnergy 最大精神力
     * @see Stand#getMaxEnergy(LivingEntity, IStandData)
     */
    void setMaxEnergy(long maxEnergy);

    /**
     * 获取替身最大精神力
     *
     * @return 最大精神力
     * @see Stand#getMaxEnergy(LivingEntity, IStandData)
     */
    long getMaxEnergy();
}
