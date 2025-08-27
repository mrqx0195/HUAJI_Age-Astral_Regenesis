package net.mrqx.huajiage.utils;

import com.mega.endinglib.util.time.TimeStopUtils;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.PacketDistributor;
import net.mrqx.huajiage.capability.stand.IStandData;
import net.mrqx.huajiage.network.NetworkManager;
import net.mrqx.huajiage.network.TimeStopEffectMessage;

public class StandUtils {
    public static void standTimeStop(boolean isStop, LivingEntity source, IStandData data, boolean force, int time, int castTime) {
        data.getScheduler().schedule("StandTimeStop", source.level().getGameTime() + castTime, (living, manager, gameTime) -> {
            TimeStopUtils.use(isStop, source, force, time);
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
}
