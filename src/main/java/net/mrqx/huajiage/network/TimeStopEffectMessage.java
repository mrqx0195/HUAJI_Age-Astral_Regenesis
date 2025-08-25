package net.mrqx.huajiage.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.mrqx.huajiage.event.client.ShaderHandler;
import net.mrqx.huajiage.utils.QuadConsumer;

import java.util.function.Supplier;

public class TimeStopEffectMessage {
    public ResourceLocation level = Level.OVERWORLD.location();
    public long effectStartTick;
    public long effectDuration;
    public int entityId;

    public TimeStopEffectMessage() {
    }

    public static TimeStopEffectMessage decode(FriendlyByteBuf buf) {
        TimeStopEffectMessage timeStopEffectMessage = new TimeStopEffectMessage();
        timeStopEffectMessage.level = buf.readResourceLocation();
        timeStopEffectMessage.effectStartTick = buf.readLong();
        timeStopEffectMessage.effectDuration = buf.readLong();
        timeStopEffectMessage.entityId = buf.readInt();
        return timeStopEffectMessage;
    }

    public static void encode(TimeStopEffectMessage msg, FriendlyByteBuf buf) {
        buf.writeResourceLocation(msg.level);
        buf.writeLong(msg.effectStartTick);
        buf.writeLong(msg.effectDuration);
        buf.writeInt(msg.entityId);
    }

    public static void handle(TimeStopEffectMessage timeStopEffectMessage, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() != NetworkDirection.PLAY_TO_CLIENT) {
            return;
        }
        QuadConsumer<ResourceLocation, Long, Long, Integer> handler = DistExecutor.safeCallWhenOn(Dist.CLIENT, () -> ShaderHandler::setClientTimeStopEffect);

        if (handler != null) {
            ctx.get().enqueueWork(() -> handler.accept(timeStopEffectMessage.level, timeStopEffectMessage.effectStartTick, timeStopEffectMessage.effectDuration, timeStopEffectMessage.entityId));
        }

        ctx.get().setPacketHandled(true);
    }
}
