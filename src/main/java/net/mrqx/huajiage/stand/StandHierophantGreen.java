package net.mrqx.huajiage.stand;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.capability.stand.IStandData;
import net.mrqx.huajiage.client.HuaJiLayers;
import net.mrqx.huajiage.client.model.stand.ModelHierophantGreen;
import net.mrqx.huajiage.client.model.stand.ModelHierophantGreenIdle;
import net.mrqx.huajiage.client.model.stand.ModelStandBase;
import net.mrqx.huajiage.entity.EntityItemBullet;
import net.mrqx.huajiage.registy.HuaJiSoundEvents;
import net.mrqx.huajiage.utils.HuaJiMathHelper;
import net.mrqx.huajiage.utils.HuaJiSoundPlayer;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class StandHierophantGreen extends Stand {
    public static final BiConsumer<LivingEntity, IStandData> HIEROPHANT_GREEN_TICK = (living, data) -> {
        if (living.level().getGameTime() % 5 == 0 && !living.level().isClientSide && STATE_DEFAULT.equals(data.getState())) {
            Stand stand = Stand.getStand(data.getStand());
            if (stand != null) {
                EntityItemBullet bullet = new EntityItemBullet(living, 0, 0, 0, living.level());
                bullet.setDelay(0L);
                Vec3 vec3 = HuaJiMathHelper.getPositionRelative2D(living, -0.55F, -0.6F);
                bullet.setItem(Items.EMERALD.getDefaultInstance());
                bullet.setDamage(stand.getDamage(living, data));
                bullet.setPos(living.position().add(vec3.x, 2.2F, vec3.z));
                bullet.shootFromRotation(living, living.getXRot(), living.getYRot(), 0, 1.5F, 0.2F);
                bullet.setPos(bullet.position().add(0, -0.4, 0));
                living.level().addFreshEntity(bullet);
            }
        }
    };

    public static final BiConsumer<LivingEntity, IStandData> HIEROPHANT_GREEN_DO_SKILL = (living, data) -> {
        Stand stand = Stand.getStand(data.getStand());
        if (stand != null && !living.level().isClientSide) {
            Vec3 look = living.getLookAngle();
            List<LivingEntity> entities = living.level().getEntitiesOfClass(LivingEntity.class, living.getBoundingBox().inflate(30));
            List<MutablePair<LivingEntity, Float>> lockedGroup = new ArrayList<>();
            boolean isLock = false;
            UUID uuid = null;
            Vec3 targetPos = living.position();
            if (!entities.isEmpty()) {
                for (LivingEntity i : entities) {
                    if (i != living) {
                        Vec3 vec = HuaJiMathHelper.getVectorEntityEye(living, i);
                        float degree1 = (float) HuaJiMathHelper.getDegreeXZ(look, vec);
                        float degree2 = (float) HuaJiMathHelper.getDegreeXY(look, vec);
                        float degree3 = (float) HuaJiMathHelper.getDegreeXZ(look, vec);
                        if (degree1 < 15 && degree2 < 15 && degree3 < 15) {
                            isLock = true;
                            float degrees = Math.abs(degree1) + Math.abs(degree2) + Math.abs(degree3);
                            lockedGroup.add(new MutablePair<>(i, degrees));
                            break;
                        }
                    }
                }
            }
            if (isLock) {
                for (int index = 0; index < lockedGroup.size(); index++) {
                    MutablePair<LivingEntity, Float> pair = lockedGroup.get(index);
                    if (pair.getLeft() != null) {
                        if (index > 0) {
                            if (pair.getRight() < lockedGroup.get(index - 1).getRight()) {
                                uuid = pair.getLeft().getUUID();
                            }
                        } else {
                            uuid = pair.getLeft().getUUID();
                        }
                    }
                }
                if (!entities.isEmpty()) {
                    for (LivingEntity entity : entities) {
                        if (entity.getUUID().equals(uuid)) {
                            targetPos = entity.position();
                        }
                    }
                }
            } else {
                Vec3 start = living.getEyePosition(1.0f);
                Vec3 end = start.add(living.getLookAngle().scale(30));
                HitResult result = living.level().clip(new ClipContext(start, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, living));
                targetPos = result.getLocation();
            }

            for (int i = 0; i < 128; i++) {
                float rx = (living.level().random.nextFloat() - 0.5F) * 40;
                float ry = living.level().random.nextFloat() * 19 + 1;
                float rz = (living.level().random.nextFloat() - 0.5F) * 40;
                Vec3 pos = new Vec3(targetPos.x + rx, targetPos.y + ry, targetPos.z + rz);
                Vec3 finalTargetPos = targetPos;
                EntityItemBullet bullet = new EntityItemBullet(living, 0, 0, 0, living.level(), bullet1 -> {
                    double pOffsetX = finalTargetPos.x - bullet1.getX();
                    double pOffsetY = finalTargetPos.y - bullet1.getY();
                    double pOffsetZ = finalTargetPos.z - bullet1.getZ();
                    double d3 = Math.sqrt(pOffsetX * pOffsetX + pOffsetY * pOffsetY + pOffsetZ * pOffsetZ);
                    if (d3 != 0) {
                        bullet1.xPower = pOffsetX / d3 * 0.1;
                        bullet1.yPower = pOffsetY / d3 * 0.1;
                        bullet1.zPower = pOffsetZ / d3 * 0.1;
                    }
                });
                bullet.setItem(Items.EMERALD.getDefaultInstance());
                bullet.setDamage(stand.getDamage(living, data));
                bullet.setPos(pos);
                bullet.setSplashHuge(true);
                bullet.setDelay(50);
                bullet.setXRot(360 * rx / 20);
                bullet.setYRot(360 * ry / 20);
                living.level().addFreshEntity(bullet);
            }
            HuaJiSoundPlayer.playMovingSoundToClient(living, HuaJiSoundEvents.STAND_HIEROPHANT_GREEN_EMERALD_SPLASH.get(), living.getSoundSource());
        }
    };

    public StandHierophantGreen() {
        super(HIEROPHANT_GREEN_TICK, HIEROPHANT_GREEN_DO_SKILL);
    }

    @Override
    public String getDescriptionId() {
        return "stand.huajiage.hierophant_green";
    }

    @Override
    public void onTriggered(LivingEntity livingEntity, IStandData data) {
        super.onTriggered(livingEntity, data);
        HuaJiSoundPlayer.playMovingSoundToClient(livingEntity, livingEntity.level().getRandom().nextBoolean() ? HuaJiSoundEvents.STAND_HIEROPHANT_GREEN_SHOOT_1.get() : HuaJiSoundEvents.STAND_HIEROPHANT_GREEN_SHOOT_2.get(), livingEntity.getSoundSource());
    }

    @Override
    public int skillEnergyDemand(LivingEntity livingEntity, IStandData data) {
        return data.getLevel() > 0 ? 70000 : -1;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public float getDamage(LivingEntity livingEntity, IStandData data) {
        return data.getLevel() > 0 ? 7 : 5;
    }

    @Override
    public float getSpeed(LivingEntity livingEntity, IStandData data) {
        return 1;
    }

    @Override
    public int getDuration(LivingEntity livingEntity, IStandData data) {
        return 200;
    }

    @Override
    public float getDistance(LivingEntity livingEntity, IStandData data) {
        return 20;
    }

    @Override
    public int chargePerTick(LivingEntity livingEntity, IStandData data) {
        return data.getState().equals(STATE_IDLE) ? 160 : 80;
    }

    @Override
    public List<String> getStates() {
        return List.of(STATE_DEFAULT, STATE_IDLE);
    }

    @OnlyIn(Dist.CLIENT)
    public static final ModelLayerLocation DEFAULT_LAYER = HuaJiLayers.create("hierophant_green", STATE_DEFAULT);

    @OnlyIn(Dist.CLIENT)
    public static final ModelLayerLocation IDLE_LAYER = HuaJiLayers.create("hierophant_green", STATE_IDLE);

    @OnlyIn(Dist.CLIENT)
    public static final Map<String, ModelLayerLocation> MODEL_LAYER_MAP = Map.of(
            STATE_DEFAULT, DEFAULT_LAYER,
            STATE_IDLE, IDLE_LAYER
    );

    @OnlyIn(Dist.CLIENT)
    public static final Map<String, ResourceLocation> TEXTURE_MAP = Map.of(
            STATE_DEFAULT, HuaJiAgeMod.prefix("textures/entity/entity_hierophant_green_default.png"),
            STATE_IDLE, HuaJiAgeMod.prefix("textures/entity/entity_hierophant_green_idle.png")
    );

    @OnlyIn(Dist.CLIENT)
    public static final Map<String, List<Double>> TRANSLATION_MAP = Map.of(
            STATE_DEFAULT, List.of(0.5, -1.0, 0.75),
            STATE_IDLE, List.of(0.8, -0.75, -0.7)
    );

    @OnlyIn(Dist.CLIENT)
    public static final Map<ModelLayerLocation, Supplier<LayerDefinition>> MODEL_MAP = Map.of(
            DEFAULT_LAYER, ModelHierophantGreen::createBodyLayer,
            IDLE_LAYER, ModelHierophantGreenIdle::createBodyLayer
    );

    @OnlyIn(Dist.CLIENT)
    public static final Map<ModelLayerLocation, Function<ModelPart, ModelStandBase>> MODEL_FUNCTION_MAP = Map.of(
            DEFAULT_LAYER, ModelHierophantGreen::new,
            IDLE_LAYER, ModelHierophantGreenIdle::new
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
    public boolean shouldRenderExtra(LivingEntity livingEntity, IStandData data) {
        return data.getLevel() >= 1;
    }
}
