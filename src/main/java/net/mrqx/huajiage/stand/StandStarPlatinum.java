package net.mrqx.huajiage.stand;

import com.mega.endinglib.util.time.TimeStopUtils;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.capability.stand.IStandData;
import net.mrqx.huajiage.client.HuaJiLayers;
import net.mrqx.huajiage.client.model.stand.ModelStandBase;
import net.mrqx.huajiage.client.model.stand.ModelStarPlatinum;
import net.mrqx.huajiage.client.model.stand.ModelStarPlatinumIdle;
import net.mrqx.huajiage.data.HuaJiDamageTypes;
import net.mrqx.huajiage.entity.EntityRoadRoller;
import net.mrqx.huajiage.registy.HuaJiSoundEvents;
import net.mrqx.huajiage.utils.HuaJiDamageSources;
import net.mrqx.huajiage.utils.HuaJiMathHelper;
import net.mrqx.huajiage.utils.HuaJiSoundPlayer;
import net.mrqx.huajiage.utils.StandUtils;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class StandStarPlatinum extends Stand {
    public static final BiConsumer<LivingEntity, IStandData> STAR_PLATINUM_TICK = (living, data) -> {
        Stand stand = Stand.getStand(data.getStand());
        if (stand instanceof StandStarPlatinum starPlatinum && !living.level().isClientSide && STATE_DEFAULT.equals(data.getState())) {
            living.level().getEntitiesOfClass(Entity.class, living.getBoundingBox().inflate(stand.getDistance(living, data))).forEach(entity -> {
                if (HuaJiMathHelper.getDegreeXZ(living.getLookAngle(), HuaJiMathHelper.getVectorEntityEye(living, entity)) > 120) {
                    return;
                }
                Vec3 back = HuaJiMathHelper.getVectorEntityEye(living, entity);
                DamageSource damageSource = HuaJiDamageSources.simple(living, HuaJiDamageTypes.STAND_HIT);

                if (entity instanceof EnderDragonPart enderDragonPart) {
                    enderDragonPart.parentMob.hurt(enderDragonPart.parentMob.head, damageSource, stand.getDamage(living, data));
                } else if (entity instanceof LivingEntity target && !target.equals(living)) {
                    boolean isTimeStopping = TimeStopUtils.isTimeStop && TimeStopUtils.andSameDimension(living.level());
                    if (living.level().getGameTime() % 4 == 0 || isTimeStopping) {
                        target.invulnerableTime = 0;
                        target.hurt(damageSource, stand.getDamage(living, data) / 2);
                        target.invulnerableTime = 0;
                        if (!isTimeStopping) {
                            living.level().levelEvent(2001, target.blockPosition().offset(0, (int) (target.getEyePosition(0).y - target.position().y), 0), Block.getId(Blocks.OBSIDIAN.defaultBlockState()));
                        }
                        if (HuaJiMathHelper.getVectorEntityEye(living, target).length() < stand.getDistance(living, data)) {
                            target.setDeltaMovement(back);
                        }
                        starPlatinum.counter += isTimeStopping ? 1 : 5;
                        if (data.getLevel() > 0 && starPlatinum.counter > 4) {
                            starPlatinum.counter = 0;
                            HuaJiSoundPlayer.playMovingSoundToClient(living, SoundEvents.GENERIC_EXPLODE, 0.25F);
                            HuaJiSoundPlayer.playMovingSoundToClient(living, isTimeStopping ? HuaJiSoundEvents.STAND_STAR_PLATINUM_REPEAT_1.get() : HuaJiSoundEvents.STAND_STAR_PLATINUM_5.get(), isTimeStopping ? 1 : 0.3F);
                        }
                    }
                } else if (!(entity instanceof ItemEntity || entity instanceof ExperienceOrb)) {
                    if (entity instanceof EntityRoadRoller roadRoller) {
                        roadRoller.hurt(damageSource, stand.getDamage(living, data) / 2);
                    } else {
                        entity.setDeltaMovement(back.scale(stand.getDamage(living, data) / 10));
                    }
                }
            });
        }
    };

    public static final BiConsumer<LivingEntity, IStandData> STAR_PLATINUM_DO_SKILL = (living, data) -> {
        int time = (data.getLevel() > 1 ? 5 : 2) * 20;
        int castTime;
        if (TimeStopUtils.isTimeStop && TimeStopUtils.andSameDimension(living.level())) {
            castTime = 0;
        } else {
            castTime = 40;
            HuaJiSoundPlayer.playMovingSoundToClient(living, HuaJiSoundEvents.STAR_PLATINUM_THE_WORLD_1.get(), 5);
        }
        StandUtils.castStandTimeStop(true, living, data, true, time, castTime);
        living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, time + castTime, 1, false, false));
        living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, time + castTime, 2, false, false));
        living.addEffect(new MobEffectInstance(MobEffects.JUMP, time + castTime, 1, false, false));
    };

    public int counter = 0;

    public StandStarPlatinum() {
        super(STAR_PLATINUM_TICK, STAR_PLATINUM_DO_SKILL);
    }

    @Override
    public String getDescriptionId() {
        return "stand.huajiage.star_platinum";
    }

    @Override
    public void onTriggered(LivingEntity livingEntity, IStandData data) {
        super.onTriggered(livingEntity, data);
        SoundEvent soundEvent = switch (livingEntity.level().getRandom().nextInt(5)) {
            case 1 -> HuaJiSoundEvents.STAND_STAR_PLATINUM_2.get();
            case 2 -> HuaJiSoundEvents.STAND_STAR_PLATINUM_3.get();
            case 3 -> HuaJiSoundEvents.STAND_STAR_PLATINUM_4.get();
            default -> HuaJiSoundEvents.STAND_STAR_PLATINUM_1.get();
        };
        HuaJiSoundPlayer.playMovingSoundToClient(livingEntity, soundEvent);
    }

    @Override
    public float getDamage(LivingEntity livingEntity, IStandData data) {
        return 15;
    }

    @Override
    public float getSpeed(LivingEntity livingEntity, IStandData data) {
        return 1.5F;
    }

    @Override
    public int getDuration(LivingEntity livingEntity, IStandData data) {
        return 275;
    }

    @Override
    public float getDistance(LivingEntity livingEntity, IStandData data) {
        return 2 + data.getLevel();
    }

    @Override
    public int chargePerTick(LivingEntity livingEntity, IStandData data) {
        return data.isTriggered() && data.getState().equals(STATE_IDLE) ? 190 : 95;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public int skillEnergyDemand(LivingEntity livingEntity, IStandData data) {
        return data.getLevel() > 0 ? 50000 : -1;
    }

    @Override
    public List<String> getStates() {
        return List.of(STATE_DEFAULT, STATE_IDLE);
    }

    @OnlyIn(Dist.CLIENT)
    public static final ModelLayerLocation DEFAULT_LAYER = HuaJiLayers.create("star_platinum", STATE_DEFAULT);

    @OnlyIn(Dist.CLIENT)
    public static final ModelLayerLocation IDLE_LAYER = HuaJiLayers.create("star_platinum", STATE_IDLE);

    @OnlyIn(Dist.CLIENT)
    public static final Map<String, ModelLayerLocation> MODEL_LAYER_MAP = Map.of(
            STATE_DEFAULT, DEFAULT_LAYER,
            STATE_IDLE, IDLE_LAYER
    );

    @OnlyIn(Dist.CLIENT)
    public static final Map<String, ResourceLocation> TEXTURE_MAP = Map.of(
            STATE_DEFAULT, HuaJiAgeMod.prefix("textures/entity/entity_star_platinum_default.png"),
            STATE_IDLE, HuaJiAgeMod.prefix("textures/entity/entity_star_platinum_idle.png")
    );

    @OnlyIn(Dist.CLIENT)
    public static final Map<String, List<Double>> TRANSLATION_MAP = Map.of(
            STATE_DEFAULT, List.of(0.0, -0.2, -0.75),
            STATE_IDLE, List.of(0.0, -0.7, 0.5)
    );

    @OnlyIn(Dist.CLIENT)
    public static final Map<ModelLayerLocation, Supplier<LayerDefinition>> MODEL_MAP = Map.of(
            DEFAULT_LAYER, ModelStarPlatinum::createBodyLayer,
            IDLE_LAYER, ModelStarPlatinumIdle::createBodyLayer
    );

    @OnlyIn(Dist.CLIENT)
    public static final Map<ModelLayerLocation, Function<ModelPart, ModelStandBase>> MODEL_FUNCTION_MAP = Map.of(
            DEFAULT_LAYER, ModelStarPlatinum::new,
            IDLE_LAYER, ModelStarPlatinumIdle::new
    );

    @Override
    @OnlyIn(Dist.CLIENT)
    public Map<String, ModelLayerLocation> getModelLocations() {
        return MODEL_LAYER_MAP;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public Map<String, ResourceLocation> getModelTextures() {
        return TEXTURE_MAP;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public Map<String, List<Double>> getModelTranslations() {
        return TRANSLATION_MAP;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public Map<ModelLayerLocation, Supplier<LayerDefinition>> getModels() {
        return MODEL_MAP;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public Map<ModelLayerLocation, Function<ModelPart, ModelStandBase>> getModelFunction() {
        return MODEL_FUNCTION_MAP;
    }

    @Override
    public boolean shouldRenderHand(LivingEntity livingEntity, IStandData data) {
        return !(data.isTriggered() && data.getState().equals(STATE_DEFAULT));
    }

    @Override
    public boolean shouldRenderExtra(LivingEntity livingEntity, IStandData data) {
        return data.getLevel() >= 1;
    }
}
