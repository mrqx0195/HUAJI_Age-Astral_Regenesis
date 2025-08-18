package net.mrqx.huajiage.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.network.NetworkEvent;
import net.mrqx.huajiage.event.ChangeModeEvent;

import java.util.function.Supplier;

public class ChangeModeMessage {
    public boolean shift;

    public ChangeModeMessage() {
    }

    static public ChangeModeMessage decode(FriendlyByteBuf buf) {
        ChangeModeMessage msg = new ChangeModeMessage();
        msg.shift = buf.readBoolean();
        return msg;
    }

    static public void encode(ChangeModeMessage msg, FriendlyByteBuf buf) {
        buf.writeBoolean(msg.shift);
    }

    static public void handle(ChangeModeMessage msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ChangeModeEvent event = new ChangeModeEvent(ctx.get().getSender(), msg.shift);
            MinecraftForge.EVENT_BUS.post(event);
        });
        ctx.get().setPacketHandled(true);
    }
}
