package net.mrqx.huajiage.stand;

import net.minecraft.ChatFormatting;
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
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class AbstractStand {
    public static final ResourceKey<Registry<AbstractStand>> REGISTRY_KEY = ResourceKey.createRegistryKey(HuaJiAgeMod.prefix("stands"));
    public static final String STATE_DEFAULT = HuaJiAgeMod.MODID + "." + "default";
    public static final String STATE_IDLE = HuaJiAgeMod.MODID + "." + "idle";
    public static final String STATE_FLY = HuaJiAgeMod.MODID + "." + "fly";

    protected final BiConsumer<LivingEntity, IStandData> tick;
    protected final BiConsumer<LivingEntity, IStandData> doSkill;

    protected AbstractStand(BiConsumer<LivingEntity, IStandData> tick, BiConsumer<LivingEntity, IStandData> doSkill) {
        this.tick = tick;
        this.doSkill = doSkill;
    }

    public String getDescriptionId() {
        return "stand.huajiage.null";
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
        livingEntity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 20, 24, false, false));
    }

    public int skillEnergyDemand(LivingEntity livingEntity, IStandData data) {
        return -1;
    }

    public int chargePerTick(LivingEntity livingEntity, IStandData data) {
        return 0;
    }

    public void onTriggered(LivingEntity livingEntity, IStandData data) {
        data.setState(AbstractStand.STATE_DEFAULT);
        livingEntity.sendSystemMessage(Component.translatable(this.getDescriptionId() + ".triggered"));
        float cost = (float) data.getEnergy() / HuaJiCommonConfig.STAND_TRIGGER_COST.get();
        if (cost > 1) {
            cost = 1;
        }
        data.setEnergy(data.getEnergy() - (long) (cost * HuaJiCommonConfig.STAND_TRIGGER_COST.get()));
        livingEntity.addEffect(new MobEffectInstance(HuaJiEffects.STAND_POWER.get(), (int) (this.getDuration(livingEntity, data) * cost), data.getLevel()));
    }

    public void onCancelTriggered(LivingEntity livingEntity, IStandData data) {
        MobEffectInstance mobEffectInstance = livingEntity.removeEffectNoUpdate(HuaJiEffects.STAND_POWER.get());
        if (mobEffectInstance != null) {
            data.setEnergy((long) (data.getEnergy() + (float) (mobEffectInstance.getDuration()) / this.getDuration(livingEntity, data) * HuaJiCommonConfig.STAND_TRIGGER_COST.get()));
        }
    }

    public int getMaxLevel() {
        return -1;
    }

    public abstract float getDamage(LivingEntity livingEntity, IStandData data);

    public abstract float getSpeed(LivingEntity livingEntity, IStandData data);

    public abstract int getDuration(LivingEntity livingEntity, IStandData data);

    public abstract float getDistance(LivingEntity livingEntity, IStandData data);

    public long getMaxEnergy(LivingEntity livingEntity, IStandData data) {
        return this.chargePerTick(livingEntity, data) * 1200L;
    }

    public abstract List<String> getStates();

    @OnlyIn(Dist.CLIENT)
    public abstract Map<String, ModelLayerLocation> getModelLocations();

    @OnlyIn(Dist.CLIENT)
    public abstract Map<String, ResourceLocation> getModelTextures();

    @OnlyIn(Dist.CLIENT)
    public abstract Map<String, List<Double>> getModelTranslations();

    @OnlyIn(Dist.CLIENT)
    public abstract Map<ModelLayerLocation, Supplier<LayerDefinition>> getModels();

    @OnlyIn(Dist.CLIENT)
    public abstract Map<ModelLayerLocation, Function<ModelPart, ModelStandBase>> getModelFunction();

    public boolean shouldRenderHand(LivingEntity livingEntity, IStandData data) {
        return true;
    }

    public boolean shouldRenderExtra(LivingEntity livingEntity, IStandData data) {
        return false;
    }

    @Nullable
    public static AbstractStand getStand(@Nullable ResourceLocation resourceLocation) {
        return resourceLocation == null ? null : HuaJiStands.REGISTRY.get().getValue(resourceLocation);
    }

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
