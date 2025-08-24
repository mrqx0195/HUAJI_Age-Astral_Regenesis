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

import java.util.function.Supplier;

public class HuaJiSoundMessage {
    public ResourceLocation soundEvent = SoundEvents.EMPTY.getLocation();
    public SoundSource source = SoundSource.MUSIC;
    public int entityId;
    public float volume;

    public HuaJiSoundMessage() {
    }

    public static HuaJiSoundMessage decode(FriendlyByteBuf buf) {
        HuaJiSoundMessage huaJiSoundMessage = new HuaJiSoundMessage();
        huaJiSoundMessage.soundEvent = buf.readResourceLocation();
        huaJiSoundMessage.source = buf.readEnum(SoundSource.class);
        huaJiSoundMessage.entityId = buf.readInt();
        huaJiSoundMessage.volume = buf.readFloat();
        return huaJiSoundMessage;
    }

    public static void encode(HuaJiSoundMessage msg, FriendlyByteBuf buf) {
        buf.writeResourceLocation(msg.soundEvent);
        buf.writeEnum(msg.source);
        buf.writeInt(msg.entityId);
        buf.writeFloat(msg.volume);
    }

    public static void handle(HuaJiSoundMessage huaJiSoundMessage, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() != NetworkDirection.PLAY_TO_CLIENT) {
            return;
        }
        HuajiSoundPlayer.QuadConsumer<ResourceLocation, SoundSource, Integer, Float> handler = DistExecutor.safeCallWhenOn(Dist.CLIENT, () -> HuajiSoundPlayer::playMovingSoundClient);

        if (handler != null) {
            ctx.get().enqueueWork(() -> handler.accept(huaJiSoundMessage.soundEvent, huaJiSoundMessage.source, huaJiSoundMessage.entityId, huaJiSoundMessage.volume));
        }

        ctx.get().setPacketHandled(true);
    }
}
