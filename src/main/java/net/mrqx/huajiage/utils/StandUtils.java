package net.mrqx.huajiage.utils;

import com.mega.endinglib.EndingLibrary;
import com.mega.endinglib.common.data.TimeStopSavedData;
import com.mega.endinglib.config.CommonConfig;
import com.mega.endinglib.network.PacketHandler;
import com.mega.endinglib.network.s2c.timestop.TSDimensionSynchedPacket;
import com.mega.endinglib.util.time.TimeStopEntityData;
import com.mega.endinglib.util.time.TimeStopUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.network.PacketDistributor;
import net.mrqx.huajiage.capability.stand.IStandData;
import net.mrqx.huajiage.data.HuaJiDamageTypes;
import net.mrqx.huajiage.entity.EntityRoadRoller;
import net.mrqx.huajiage.event.StandPunchEvent;
import net.mrqx.huajiage.network.NetworkManager;
import net.mrqx.huajiage.network.TimeStopEffectMessage;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.registy.HuaJiSoundEvents;
import net.mrqx.huajiage.stand.Stand;

import javax.annotation.Nullable;
import java.util.function.BiConsumer;

public class StandUtils {
    public static void castStandTimeStop(boolean isStop, LivingEntity source, IStandData data, boolean force, int time, int castTime) {
        if (castTime <= 0) {
            useStandTimeStop(isStop, source, force, time);
        } else {
            data.getScheduler().schedule("StandTimeStop", castTime, (living, manager, gameTime) -> useStandTimeStop(isStop, source, force, time));
        }
    }

    public static void useStandTimeStop(boolean isStop, LivingEntity source, boolean force, int time) {
        StandUtils.standTimeStop(isStop, source, force, time);
        MinecraftServer server = source.getServer();
        if (server != null) {
            TimeStopEffectMessage message = new TimeStopEffectMessage();
            message.level = source.level().dimension().location();
            message.effectStartTick = source.level().getGameTime();
            message.effectDuration = time;
            message.entityId = source.getId();
            server.getPlayerList().getPlayers().forEach(serverPlayer -> NetworkManager.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer), message));
        }
    }

    /**
     * @see TimeStopUtils#use(boolean, LivingEntity, boolean, int)
     */
    public static void standTimeStop(boolean isTimeStop, LivingEntity source, boolean force, int time) {
        if (isTimeStop && !CommonConfig.enableTS) {
            EndingLibrary.LOGGER.warn("Time Stop Settings is disabled in the common-config.");
        } else if (source.level().isClientSide) {
            throw new RuntimeException("time stop should be called on server side.");
        } else if (source.level() instanceof ServerLevel serverLevel) {
            if (!isTimeStop) {
                for (LivingEntity living : source.level().getEntitiesOfClass(LivingEntity.class, (new AABB(new BlockPos(0, 0, 0))).inflate(3.0E7F))) {
                    if (living != source && TimeStopEntityData.getTimeStopCount(living) > 0 && living.isAlive()) {
                        if (force) {
                            TimeStopEntityData.setTimeStopCount(source, 0);
                        }

                        return;
                    }
                }
            }

            TimeStopUtils.isTimeStop = isTimeStop;
            if (!TimeStopUtils.isTimeStop) {
                TimeStopSavedData.readOrCreate(serverLevel.getServer()).removeTsDimension(source.level().dimension());
            }

            if (TimeStopUtils.isTimeStop) {
                PacketHandler.sendToAll(new TSDimensionSynchedPacket(ResourceLocation.parse(""), source.level().dimension().location()));
            } else {
                PacketHandler.sendToAll(new TSDimensionSynchedPacket(source.level().dimension().location(), ResourceLocation.parse("")));
            }

            if (isTimeStop) {
                TimeStopSavedData.readOrCreate(serverLevel.getServer()).addTsDimension(source.level().dimension());
                TimeStopEntityData.setTimeStopCount(source, Math.max(TimeStopEntityData.getTimeStopCount(source), time));
            } else if (force) {
                TimeStopEntityData.setTimeStopCount(source, 0);
            }
        }
    }

    public static void standPunch(LivingEntity living, IStandData data, Stand stand,
                                  float distanceFactor, float angleThreshold,
                                  @Nullable BiConsumer<LivingEntity, Integer> onHitCallback) {
        StandPunchEvent event = new StandPunchEvent(living, data, stand, distanceFactor, angleThreshold);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled()) {
            return;
        }

        float finalDistanceFactor = event.getDistanceFactor();
        float finalAngleThreshold = event.getAngleThreshold();

        living.level().getEntitiesOfClass(Entity.class, living.getBoundingBox().inflate(stand.getDistance(living, data) / finalDistanceFactor))
                .forEach(entity -> {
                    if (HuaJiMathHelper.getDegreeXZ(living.getLookAngle(), HuaJiMathHelper.getVectorEntityEye(living, entity)) > finalAngleThreshold) {
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
                                living.level().levelEvent(2001, target.blockPosition().offset(0, (int) (target.getEyePosition(0).y - target.position().y), 0),
                                        Block.getId(Blocks.OBSIDIAN.defaultBlockState()));
                            }
                            if (HuaJiMathHelper.getVectorEntityEye(living, target).length() < stand.getDistance(living, data) / finalDistanceFactor) {
                                target.setDeltaMovement(back);
                            }

                            if (onHitCallback != null) {
                                onHitCallback.accept(living, data.getLevel());
                            }
                        }
                    } else if (!(entity instanceof ItemEntity || entity instanceof ExperienceOrb)) {
                        if (entity instanceof EntityRoadRoller roadRoller) {
                            roadRoller.hurt(damageSource, stand.getDamage(living, data) / 2);
                        } else {
                            if (entity instanceof OwnableEntity ownableEntity) {
                                if (!living.equals(ownableEntity.getOwner())) {
                                    entity.setDeltaMovement(back.scale(stand.getDamage(living, data) / 10));
                                }
                            } else if (entity instanceof TraceableEntity traceableEntity) {
                                if (!living.equals(traceableEntity.getOwner())) {
                                    entity.setDeltaMovement(back.scale(stand.getDamage(living, data) / 10));
                                }
                            } else {
                                entity.setDeltaMovement(back.scale(stand.getDamage(living, data) / 10));
                            }
                        }
                    }
                });
    }

    public static void foodTimeStop(LivingEntity living, IStandData data, int time) {
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
        castStandTimeStop(true, living, data, true, time, castTime);
        living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, time + 10, 4, false, false));
        living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, time + 10, 4, false, false));
        living.addEffect(new MobEffectInstance(MobEffects.JUMP, time + 10, 4, false, false));
        living.addEffect(new MobEffectInstance(MobEffects.REGENERATION, time + 10, 2, false, false));
        living.heal(5);
        living.sendSystemMessage(Component.translatable("stand.huajiage.the_world.skill").withStyle(ChatFormatting.GOLD));
        if (living instanceof Player player && player.level().random.nextDouble() < 0.3) {
            player.addItem(HuaJiItems.ROAD_ROLLER.get().getDefaultInstance());
        }
    }
}
