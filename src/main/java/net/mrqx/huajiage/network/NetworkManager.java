package net.mrqx.huajiage.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.mrqx.huajiage.HuaJiAgeMod;

public class NetworkManager {
    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(HuaJiAgeMod.MODID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals);

    @SuppressWarnings("all")
    public static void register() {
        int id = 0;
        INSTANCE.registerMessage(id++, HuaJiKeyMessage.class, HuaJiKeyMessage::encode, HuaJiKeyMessage::decode, HuaJiKeyMessage::handle);
        INSTANCE.registerMessage(id++, StandSyncMessage.class, StandSyncMessage::encode, StandSyncMessage::decode, StandSyncMessage::handle);
        INSTANCE.registerMessage(id++, HuaJiSoundMessage.class, HuaJiSoundMessage::encode, HuaJiSoundMessage::decode, HuaJiSoundMessage::handle);
        INSTANCE.registerMessage(id++, TimeStopEffectMessage.class, TimeStopEffectMessage::encode, TimeStopEffectMessage::decode, TimeStopEffectMessage::handle);
    }
}
