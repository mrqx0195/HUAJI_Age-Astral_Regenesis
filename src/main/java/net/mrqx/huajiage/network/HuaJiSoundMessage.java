package net.mrqx.huajiage.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.mrqx.huajiage.utils.HuajiSoundPlayer;
import net.mrqx.huajiage.utils.PentaConsumer;

import java.util.function.Supplier;

public class HuaJiSoundMessage {
    public ResourceLocation soundEvent = SoundEvents.EMPTY.getLocation();
    public SoundSource source = SoundSource.MUSIC;
    public int entityId;
    public float volume = 1;
    public float pitch = 1;

    public HuaJiSoundMessage() {
    }

    public static HuaJiSoundMessage decode(FriendlyByteBuf buf) {
        HuaJiSoundMessage huaJiSoundMessage = new HuaJiSoundMessage();
        huaJiSoundMessage.soundEvent = buf.readResourceLocation();
        huaJiSoundMessage.source = buf.readEnum(SoundSource.class);
        huaJiSoundMessage.entityId = buf.readInt();
        huaJiSoundMessage.volume = buf.readFloat();
        huaJiSoundMessage.pitch = buf.readFloat();
        return huaJiSoundMessage;
    }

    public static void encode(HuaJiSoundMessage msg, FriendlyByteBuf buf) {
        buf.writeResourceLocation(msg.soundEvent);
        buf.writeEnum(msg.source);
        buf.writeInt(msg.entityId);
        buf.writeFloat(msg.volume);
        buf.writeFloat(msg.pitch);
    }

    public static void handle(HuaJiSoundMessage huaJiSoundMessage, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() != NetworkDirection.PLAY_TO_CLIENT) {
            return;
        }
        PentaConsumer<ResourceLocation, SoundSource, Integer, Float, Float> handler = DistExecutor.safeCallWhenOn(Dist.CLIENT, () -> HuajiSoundPlayer::playMovingSoundClient);

        if (handler != null) {
            ctx.get().enqueueWork(() -> handler.accept(huaJiSoundMessage.soundEvent, huaJiSoundMessage.source, huaJiSoundMessage.entityId, huaJiSoundMessage.volume, huaJiSoundMessage.pitch));
        }

        ctx.get().setPacketHandled(true);
    }
}
