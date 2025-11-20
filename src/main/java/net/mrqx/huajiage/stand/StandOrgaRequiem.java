package net.mrqx.huajiage.stand;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.capability.stand.IStandData;
import net.mrqx.huajiage.capability.stand.StandDataCapabilityProvider;
import net.mrqx.huajiage.client.HuaJiLayers;
import net.mrqx.huajiage.client.model.stand.ModelOrgaRequiem;
import net.mrqx.huajiage.client.model.stand.ModelOrgaRequiemFly;
import net.mrqx.huajiage.client.model.stand.ModelStandBase;
import net.mrqx.huajiage.event.HuaJiCanFlyEvent;
import net.mrqx.huajiage.registy.HuaJiEffects;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.registy.HuaJiSoundEvents;
import net.mrqx.huajiage.utils.HuaJiSoundPlayer;
import net.mrqx.huajiage.utils.StandUtils;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Mod.EventBusSubscriber
public class StandOrgaRequiem extends Stand {
    public static final BiConsumer<LivingEntity, IStandData> ORGA_REQUIEM_TICK = (living, data) -> {
        Stand stand = Stand.getStand(data.getStand());
        if (stand != null && !living.level().isClientSide && STATE_FLY.equals(data.getState())) {
            StandUtils.standPunch(living, data, stand, 25, 45, null);
        }
    };

    public static final BiConsumer<LivingEntity, IStandData> ORGA_REQUIEM_DO_SKILL = (living, data) -> {
        Stand stand = Stand.getStand(data.getStand());
        if (living instanceof Player player) {
            ItemStack itemStack = HuaJiItems.ORGA_HAIR_KNIFE.get().getDefaultInstance();
            itemStack.setCount(16);
            player.addItem(itemStack);
            if (player.level().random.nextDouble() < 0.3) {
                player.addItem(HuaJiItems.BLACK_CAR.get().getDefaultInstance());
            }
        }
        if (stand != null) {
            living.addEffect(new MobEffectInstance(HuaJiEffects.REQUIEM.get(), stand.getDuration(living, data), data.getLevel()));
            living.addEffect(new MobEffectInstance(HuaJiEffects.STAND_POWER.get(), stand.getDuration(living, data), data.getLevel()));
        }
    };

    public StandOrgaRequiem() {
        super(ORGA_REQUIEM_TICK, ORGA_REQUIEM_DO_SKILL);
    }

    @Override
    public void onTriggered(LivingEntity livingEntity, IStandData data) {
        super.onTriggered(livingEntity, data);
        HuaJiSoundPlayer.playMovingSoundToClient(livingEntity, livingEntity.level().getRandom().nextBoolean() ? HuaJiSoundEvents.ORGA_REQUIEM_2.get() : HuaJiSoundEvents.ORGA_REQUIEM_3.get());
    }

    @Override
    public int skillEnergyDemand(LivingEntity livingEntity, IStandData data) {
        return 80000;
    }

    @Override
    public int getMaxLevel() {
        return 0;
    }

    @Override
    public float getDamage(LivingEntity livingEntity, IStandData data) {
        return livingEntity.getMaxHealth() / 2;
    }

    @Override
    public float getSpeed(LivingEntity livingEntity, IStandData data) {
        return (float) (livingEntity.getAttributeValue(Attributes.MOVEMENT_SPEED) / 2.5);
    }

    @Override
    public int getDuration(LivingEntity livingEntity, IStandData data) {
        return 650;
    }

    @Override
    public float getDistance(LivingEntity livingEntity, IStandData data) {
        return 50;
    }

    @Override
    public int chargePerTick(LivingEntity livingEntity, IStandData data) {
        return !data.isTriggered() ? 90 : data.getState().equals(STATE_DEFAULT) ? 70 : 0;
    }

    @Override
    public List<String> getStates() {
        return List.of(STATE_DEFAULT, STATE_FLY);
    }

    @SubscribeEvent
    public static void onHuaJiCanFlyEvent(HuaJiCanFlyEvent event) {
        event.getEntity().getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
            if (Stand.getStand(data.getStand()) instanceof StandOrgaRequiem && data.isTriggered() && data.getState().equals(STATE_FLY)) {
                event.setCanFly(true);
            }
        });
    }

    @Override
    public boolean shouldRenderHand(LivingEntity livingEntity, IStandData data) {
        return !(data.isTriggered() && data.getState().equals(STATE_FLY));
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
        public static final ModelLayerLocation DEFAULT_LAYER = HuaJiLayers.create("orga_requiem", STATE_DEFAULT);

        @OnlyIn(Dist.CLIENT)
        public static final ModelLayerLocation FLY_LAYER = HuaJiLayers.create("orga_requiem", STATE_FLY);

        @OnlyIn(Dist.CLIENT)
        public static final Map<String, ModelLayerLocation> MODEL_LAYER_MAP = Map.of(
                STATE_DEFAULT, DEFAULT_LAYER,
                STATE_FLY, FLY_LAYER
        );

        @OnlyIn(Dist.CLIENT)
        public static final Map<String, ResourceLocation> TEXTURE_MAP = Map.of(
                STATE_DEFAULT, HuaJiAgeMod.prefix("textures/entity/stand/orga_requiem_default.png"),
                STATE_FLY, HuaJiAgeMod.prefix("textures/entity/stand/orga_requiem_fly.png")
        );

        @OnlyIn(Dist.CLIENT)
        public static final Map<String, List<Double>> TRANSLATION_MAP = Map.of(
                STATE_DEFAULT, List.of(-0.5, -0.7, 0.75),
                STATE_FLY, List.of(0.0, -0.9, 0.0)
        );

        @OnlyIn(Dist.CLIENT)
        public static final Map<ModelLayerLocation, Supplier<LayerDefinition>> MODEL_MAP = Map.of(
                DEFAULT_LAYER, ModelOrgaRequiem::createBodyLayer,
                FLY_LAYER, ModelOrgaRequiemFly::createBodyLayer
        );

        @OnlyIn(Dist.CLIENT)
        public static final Map<ModelLayerLocation, Function<ModelPart, ModelStandBase>> MODEL_FUNCTION_MAP = Map.of(
                DEFAULT_LAYER, ModelOrgaRequiem::new,
                FLY_LAYER, ModelOrgaRequiemFly::new
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
