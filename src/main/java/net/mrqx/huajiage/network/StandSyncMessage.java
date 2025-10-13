package net.mrqx.huajiage.network;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.mrqx.huajiage.stand.Stand;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class StandSyncMessage {
    @Nullable
    public CompoundTag data;
    public int entityId;

    public StandSyncMessage() {
    }

    public static StandSyncMessage decode(FriendlyByteBuf buf) {
        StandSyncMessage standSyncMessage = new StandSyncMessage();
        standSyncMessage.data = Objects.requireNonNull(buf.readAnySizeNbt());
        standSyncMessage.entityId = buf.readInt();
        return standSyncMessage;
    }

    public static void encode(StandSyncMessage msg, FriendlyByteBuf buf) {
        buf.writeNbt(msg.data);
        buf.writeInt(msg.entityId);
    }

    public static void handle(StandSyncMessage standSyncMessage, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() != NetworkDirection.PLAY_TO_CLIENT) {
            return;
        }
        BiConsumer<CompoundTag, Integer> handler = DistExecutor.safeCallWhenOn(Dist.CLIENT, () -> Stand.AbstractStandResource::setClientTag);

        if (handler != null) {
            ctx.get().enqueueWork(() -> handler.accept(standSyncMessage.data, standSyncMessage.entityId));
        }

        ctx.get().setPacketHandled(true);
    }
}
