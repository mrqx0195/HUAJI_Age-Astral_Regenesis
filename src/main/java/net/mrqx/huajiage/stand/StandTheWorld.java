package net.mrqx.huajiage.stand;

import com.mega.endinglib.util.time.TimeStopUtils;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.capability.stand.IStandData;
import net.mrqx.huajiage.client.HuaJiLayers;
import net.mrqx.huajiage.client.model.stand.ModelStandBase;
import net.mrqx.huajiage.client.model.stand.ModelTheWorld;
import net.mrqx.huajiage.client.model.stand.ModelTheWorldIdle;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.registy.HuaJiSoundEvents;
import net.mrqx.huajiage.utils.HuaJiSoundPlayer;
import net.mrqx.huajiage.utils.StandUtils;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class StandTheWorld extends Stand {
    public static final BiConsumer<LivingEntity, IStandData> THE_WORLD_TICK = (living, data) -> {
        Stand stand = Stand.getStand(data.getStand());
        if (stand instanceof StandTheWorld theWorld && !living.level().isClientSide && STATE_DEFAULT.equals(data.getState())) {
            StandUtils.standPunch(living, data, theWorld, 1, 90, (living1, integer) -> {
                boolean isTimeStopping = TimeStopUtils.isTimeStop && TimeStopUtils.andSameDimension(living1.level());
                theWorld.counter += isTimeStopping ? 1 : 5;
                if (data.getLevel() > 0 && theWorld.counter > 4) {
                    theWorld.counter = 0;
                    HuaJiSoundPlayer.playMovingSoundToClient(living1, SoundEvents.GENERIC_EXPLODE, 0.25F);
                    HuaJiSoundPlayer.playMovingSoundToClient(living1, HuaJiSoundEvents.DIO_HIT.get(), 0.75F);
                }
            });
        }
    };

    public static final BiConsumer<LivingEntity, IStandData> THE_WORLD_DO_SKILL = (living, data) -> {
        int time = (data.getLevel() > 1 ? 9 : 5) * 20;
        int castTime;
        if (TimeStopUtils.isTimeStop && TimeStopUtils.andSameDimension(living.level())) {
            castTime = 0;
        } else {
            SoundEvent soundEvent;
            switch (living.level().random.nextInt(4)) {
                case 1 -> {
                    soundEvent = HuaJiSoundEvents.THE_WORLD_1.get();
                    castTime = 40;
                }
                case 2 -> {
                    soundEvent = HuaJiSoundEvents.THE_WORLD_2.get();
                    castTime = 80;
                }
                case 3 -> {
                    soundEvent = HuaJiSoundEvents.THE_WORLD_3.get();
                    castTime = 40;
                }
                default -> {
                    soundEvent = HuaJiSoundEvents.THE_WORLD.get();
                    castTime = 20;
                }
            }
            HuaJiSoundPlayer.playMovingSoundToClient(living, soundEvent, 2);
        }
        StandUtils.castStandTimeStop(true, living, data, true, time, castTime);
        living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, time + castTime, 4, false, false));
        living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, time + castTime, 4, false, false));
        living.addEffect(new MobEffectInstance(MobEffects.JUMP, time + castTime, 4, false, false));
        living.addEffect(new MobEffectInstance(MobEffects.REGENERATION, time + castTime, 2, false, false));
        living.heal(5);
        if (living instanceof Player player && player.level().random.nextDouble() < 0.3) {
            player.addItem(HuaJiItems.ROAD_ROLLER.get().getDefaultInstance());
        }
    };

    public int counter = 0;

    public StandTheWorld() {
        super(THE_WORLD_TICK, THE_WORLD_DO_SKILL);
    }

    @Override
    public void onTriggered(LivingEntity livingEntity, IStandData data) {
        super.onTriggered(livingEntity, data);
        HuaJiSoundPlayer.playMovingSoundToClient(livingEntity, livingEntity.level().getRandom().nextBoolean() ? HuaJiSoundEvents.STAND_THE_WORLD_HIT_1.get() : HuaJiSoundEvents.STAND_THE_WORLD_HIT_2.get());
    }

    @Override
    public float getDamage(LivingEntity livingEntity, IStandData data) {
        return 10;
    }

    @Override
    public float getSpeed(LivingEntity livingEntity, IStandData data) {
        return 1.2F;
    }

    @Override
    public int getDuration(LivingEntity livingEntity, IStandData data) {
        return 200;
    }

    @Override
    public float getDistance(LivingEntity livingEntity, IStandData data) {
        return 2 + data.getLevel();
    }

    @Override
    public int chargePerTick(LivingEntity livingEntity, IStandData data) {
        return !data.isTriggered() ? 150 : data.getState().equals(STATE_IDLE) ? 75 : 0;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public int skillEnergyDemand(LivingEntity livingEntity, IStandData data) {
        return data.getLevel() > 0 ? 60000 : -1;
    }

    @Override
    public List<String> getStates() {
        return List.of(STATE_DEFAULT, STATE_IDLE);
    }

    @Override
    public boolean shouldRenderHand(LivingEntity livingEntity, IStandData data) {
        return !(data.isTriggered() && data.getState().equals(STATE_DEFAULT));
    }

    @Override
    public boolean shouldRenderExtra(LivingEntity livingEntity, IStandData data) {
        return data.getLevel() >= 1;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public AbstractStandResource getStandResource() {
        return StandResource.INSTANCE;
    }

    @OnlyIn(Dist.CLIENT)
    public static class StandResource extends AbstractStandResource {
        @OnlyIn(Dist.CLIENT)
        public static final StandResource INSTANCE = new StandResource();

        @OnlyIn(Dist.CLIENT)
        public static final ModelLayerLocation DEFAULT_LAYER = HuaJiLayers.create("the_world", STATE_DEFAULT);

        @OnlyIn(Dist.CLIENT)
        public static final ModelLayerLocation IDLE_LAYER = HuaJiLayers.create("the_world", STATE_IDLE);

        @OnlyIn(Dist.CLIENT)
        public static final Map<String, ModelLayerLocation> MODEL_LAYER_MAP = Map.of(
                STATE_DEFAULT, DEFAULT_LAYER,
                STATE_IDLE, IDLE_LAYER
        );

        @OnlyIn(Dist.CLIENT)
        public static final Map<String, ResourceLocation> TEXTURE_MAP = Map.of(
                STATE_DEFAULT, HuaJiAgeMod.prefix("textures/entity/stand/the_world_default.png"),
                STATE_IDLE, HuaJiAgeMod.prefix("textures/entity/stand/the_world_idle.png")
        );

        @OnlyIn(Dist.CLIENT)
        public static final Map<String, List<Double>> TRANSLATION_MAP = Map.of(
                STATE_DEFAULT, List.of(0.0, -0.2, -0.75),
                STATE_IDLE, List.of(-0.45, -0.2, 0.45)
        );

        @OnlyIn(Dist.CLIENT)
        public static final Map<ModelLayerLocation, Supplier<LayerDefinition>> MODEL_MAP = Map.of(
                DEFAULT_LAYER, ModelTheWorld::createBodyLayer,
                IDLE_LAYER, ModelTheWorldIdle::createBodyLayer
        );

        @OnlyIn(Dist.CLIENT)
        public static final Map<ModelLayerLocation, Function<ModelPart, ModelStandBase>> MODEL_FUNCTION_MAP = Map.of(
                DEFAULT_LAYER, ModelTheWorld::new,
                IDLE_LAYER, ModelTheWorldIdle::new
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
    }
}
