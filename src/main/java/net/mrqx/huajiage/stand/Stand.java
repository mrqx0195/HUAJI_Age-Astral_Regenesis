package net.mrqx.huajiage.stand;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.capability.stand.IStandData;
import net.mrqx.huajiage.capability.stand.StandDataCapabilityProvider;
import net.mrqx.huajiage.client.model.stand.ModelStandBase;
import net.mrqx.huajiage.config.HuaJiCommonConfig;
import net.mrqx.huajiage.registy.HuaJiEffects;
import net.mrqx.huajiage.registy.HuaJiStands;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Stand {
    public static final ResourceKey<Registry<Stand>> REGISTRY_KEY = ResourceKey.createRegistryKey(HuaJiAgeMod.prefix("stands"));
    public static final String STATE_DEFAULT = HuaJiAgeMod.MODID + "." + "default";
    public static final String STATE_IDLE = HuaJiAgeMod.MODID + "." + "idle";
    public static final String STATE_FLY = HuaJiAgeMod.MODID + "." + "fly";
    public static final String STATE_PUNCH = HuaJiAgeMod.MODID + "." + "punch";

    protected final BiConsumer<LivingEntity, IStandData> tick;
    protected final BiConsumer<LivingEntity, IStandData> doSkill;
    @Nullable
    private String descriptionId;

    protected Stand(BiConsumer<LivingEntity, IStandData> tick, BiConsumer<LivingEntity, IStandData> doSkill) {
        this.tick = tick;
        this.doSkill = doSkill;
    }

    public Component getDescription() {
        return Component.translatable(this.getDescriptionId());
    }

    @Override
    public String toString() {
        return Objects.requireNonNull(HuaJiStands.REGISTRY.get().getKey(this)).getPath();
    }

    protected String getOrCreateDescriptionId() {
        if (this.descriptionId == null) {
            this.descriptionId = Util.makeDescriptionId("stand", HuaJiStands.REGISTRY.get().getKey(this));
        }

        return this.descriptionId;
    }

    public String getDescriptionId() {
        return this.getOrCreateDescriptionId();
    }

    public void tick(LivingEntity livingEntity, IStandData data) {
        if (data.isTriggered()) {
            this.tick.accept(livingEntity, data);
            if (!livingEntity.hasEffect(HuaJiEffects.STAND_POWER.get())) {
                this.timeoutPenalty(livingEntity, data);
            }
        }
        data.setEnergy(Math.min(data.getEnergy() + this.chargePerTick(livingEntity, data), data.getMaxEnergy()));
    }

    @CanIgnoreReturnValue
    public boolean trySkill(LivingEntity livingEntity, IStandData data) {
        int energyDemand = this.skillEnergyDemand(livingEntity, data);
        if (energyDemand >= 0 && data.getEnergy() >= energyDemand) {
            data.setEnergy(data.getEnergy() - energyDemand);
            this.doSkill(livingEntity, data);
            return true;
        } else if (energyDemand >= 0) {
            livingEntity.sendSystemMessage(Component.translatable("message.huajiage.stand.trigger_failed").withStyle(ChatFormatting.GRAY));
        }
        return false;
    }

    protected void doSkill(LivingEntity livingEntity, IStandData data) {
        this.doSkill.accept(livingEntity, data);
        livingEntity.sendSystemMessage(Component.translatable(this.getDescriptionId() + ".skill"));
    }

    protected void timeoutPenalty(LivingEntity livingEntity, IStandData data) {
        if (!livingEntity.level().isClientSide) {
            if (data.getLevel() >= this.getMaxLevel() && data.getEnergy() >= HuaJiCommonConfig.STAND_TRIGGER_COST.get()) {
                costAndTrigger(livingEntity, data);
            } else {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 20, 24, false, false));
            }
        }
    }

    public int skillEnergyDemand(LivingEntity livingEntity, IStandData data) {
        return -1;
    }

    public int chargePerTick(LivingEntity livingEntity, IStandData data) {
        return 0;
    }

    public void onTriggered(LivingEntity livingEntity, IStandData data) {
        data.setState(Stand.STATE_DEFAULT);
        livingEntity.sendSystemMessage(Component.translatable(this.getDescriptionId() + ".triggered"));
        costAndTrigger(livingEntity, data);
    }

    private void costAndTrigger(LivingEntity livingEntity, IStandData data) {
        float cost = (float) data.getEnergy() / HuaJiCommonConfig.STAND_TRIGGER_COST.get();
        if (cost > 1) {
            cost = 1;
        }
        data.setEnergy(data.getEnergy() - (long) (cost * HuaJiCommonConfig.STAND_TRIGGER_COST.get()));
        livingEntity.addEffect(new MobEffectInstance(HuaJiEffects.STAND_POWER.get(), (int) (this.getDuration(livingEntity, data) * cost), data.getLevel()));
    }

    public void onCancelTriggered(LivingEntity livingEntity, IStandData data) {
        MobEffectInstance mobEffectInstance = livingEntity.getEffect(HuaJiEffects.STAND_POWER.get());
        if (mobEffectInstance != null) {
            data.setEnergy((long) (data.getEnergy() + (float) (mobEffectInstance.getDuration()) / this.getDuration(livingEntity, data) * HuaJiCommonConfig.STAND_TRIGGER_COST.get()));
            livingEntity.removeEffect(HuaJiEffects.STAND_POWER.get());
        }
    }

    public int getMaxLevel() {
        return -1;
    }

    /**
     * 获取替身的基础伤害，对应原作的“破坏力”属性
     *
     * @param livingEntity 正在使用替身的生物
     * @param data         替身数据
     * @return 替身的基础伤害
     */
    public abstract float getDamage(LivingEntity livingEntity, IStandData data);

    /**
     * 获取替身的基础速度，对应原作的“速度”属性
     *
     * @param livingEntity 正在使用替身的生物
     * @param data         替身数据
     * @return 替身的基础速度
     */
    public abstract float getSpeed(LivingEntity livingEntity, IStandData data);

    /**
     * 获取替身的基础持续时间，对应原作的“持续力”属性
     *
     * @param livingEntity 正在使用替身的生物
     * @param data         替身数据
     * @return 替身的基础持续力
     */
    public abstract int getDuration(LivingEntity livingEntity, IStandData data);

    /**
     * 获取替身的基础射程距离，对应原作的“射程距离”属性
     *
     * @param livingEntity 正在使用替身的生物
     * @param data         替身数据
     * @return 替身的基础射程距离
     */
    public abstract float getDistance(LivingEntity livingEntity, IStandData data);

    public long getMaxEnergy(LivingEntity livingEntity, IStandData data) {
        return this.chargePerTick(livingEntity, data) * 1200L;
    }

    /**
     * 该替身可用的状态
     *
     * @return 状态列表
     */
    public abstract List<String> getStates();

    /**
     * 获取用于存储替身客户端资源的类
     *
     * @return 一个AbstractStandResource实例
     */
    @OnlyIn(Dist.CLIENT)
    public abstract AbstractStandResource getStandResource();

    public boolean shouldRenderHand(LivingEntity livingEntity, IStandData data) {
        return true;
    }

    public boolean shouldRenderExtra(LivingEntity livingEntity, IStandData data) {
        return false;
    }

    @Nullable
    public static Stand getStand(@Nullable ResourceLocation resourceLocation) {
        return resourceLocation == null ? null : HuaJiStands.REGISTRY.get().getValue(resourceLocation);
    }

    @OnlyIn(Dist.CLIENT)
    public abstract static class AbstractStandResource {
        /**
         * 替身状态与{@link ModelLayerLocation}的映射表
         *
         * @return 一个映射表实例
         */
        @OnlyIn(Dist.CLIENT)
        public abstract Map<String, ModelLayerLocation> getModelLocations();

        /**
         * 替身状态与{@link ResourceLocation}的映射表
         *
         * @return 一个映射表实例
         */
        @OnlyIn(Dist.CLIENT)
        public abstract Map<String, ResourceLocation> getModelTextures();

        /**
         * 替身状态与该状态下替身模型基础位移的映射表
         *
         * @return 一个映射表实例
         */
        @OnlyIn(Dist.CLIENT)
        public abstract Map<String, List<Double>> getModelTranslations();

        /**
         * {@link ModelLayerLocation}与其对应的{@link LayerDefinition}的映射表
         *
         * @return 一个映射表实例
         */
        @OnlyIn(Dist.CLIENT)
        public abstract Map<ModelLayerLocation, Supplier<LayerDefinition>> getModels();

        /**
         * {@link ModelLayerLocation}与其对应的{@link ModelStandBase#ModelStandBase()}的映射表
         *
         * @return 一个映射表实例
         */
        @OnlyIn(Dist.CLIENT)
        public abstract Map<ModelLayerLocation, Function<ModelPart, ModelStandBase>> getModelFunction();

        @OnlyIn(Dist.CLIENT)
        public static BiConsumer<CompoundTag, Integer> setClientTag() {
            return (tag, id) -> {
                if (Minecraft.getInstance().level != null) {
                    Entity entity = Minecraft.getInstance().level.getEntity(id);
                    if (entity != null) {
                        entity.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> IStandData.deserializeNBT(tag, data));
                    }
                }
            };
        }
    }
}
