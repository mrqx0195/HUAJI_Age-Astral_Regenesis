package net.mrqx.huajiage.utils;

import com.mega.endinglib.EndingLibrary;
import com.mega.endinglib.common.data.TimeStopSavedData;
import com.mega.endinglib.common.network.PacketHandler;
import com.mega.endinglib.common.network.s2c.timestop.TSDimensionSynchedPacket;
import com.mega.endinglib.config.CommonConfig;
import com.mega.endinglib.util.time.TimeStopEntityData;
import com.mega.endinglib.util.time.TimeStopUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.PacketDistributor;
import net.mrqx.huajiage.capability.stand.IStandData;
import net.mrqx.huajiage.network.NetworkManager;
import net.mrqx.huajiage.network.TimeStopEffectMessage;

public class StandUtils {
    public static void standTimeStop(boolean isStop, LivingEntity source, IStandData data, boolean force, int time, int castTime) {
        data.getScheduler().schedule("StandTimeStop", castTime, (living, manager, gameTime) -> {
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
        });
    }

    /**
     * @see TimeStopUtils#use(boolean, LivingEntity, boolean, int)
     */
    public static void standTimeStop(boolean z, LivingEntity source, boolean force, int time) {
        if (z && !CommonConfig.enableTS) {
            EndingLibrary.LOGGER.warn("Time Stop Settings is disabled in the common-config.");
        } else if (source.level().isClientSide) {
            throw new RuntimeException("time stop should be called on server side.");
        } else {
            if (!source.level().isClientSide) {
                if (!z) {
                    for (LivingEntity living : source.level().getEntitiesOfClass(LivingEntity.class, (new AABB(new BlockPos(0, 0, 0))).inflate(3.0E7F))) {
                        if (living != source && TimeStopEntityData.getTimeStopCount(living) > 0 && living.isAlive()) {
                            if (force) {
                                TimeStopEntityData.setTimeStopCount(source, 0);
                            }

                            return;
                        }
                    }
                }

                TimeStopUtils.isTimeStop = z;
                if (!TimeStopUtils.isTimeStop) {
                    TimeStopSavedData.readOrCreate(((ServerLevel) source.level()).getServer()).removeTsDimension(source.level().dimension());
                }

                if (TimeStopUtils.isTimeStop) {
                    PacketHandler.sendToAll(new TSDimensionSynchedPacket(new ResourceLocation(""), source.level().dimension().location()));
                } else {
                    PacketHandler.sendToAll(new TSDimensionSynchedPacket(source.level().dimension().location(), new ResourceLocation("")));
                }

                if (z) {
                    TimeStopSavedData.readOrCreate(((ServerLevel) source.level()).getServer()).addTsDimension(source.level().dimension());
                    TimeStopEntityData.setTimeStopCount(source, Math.max(TimeStopEntityData.getTimeStopCount(source), time));
                } else if (force) {
                    TimeStopEntityData.setTimeStopCount(source, 0);
                }
            }

        }
    }
}
