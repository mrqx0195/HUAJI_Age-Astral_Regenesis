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

    public static void register() {
        int id = 0;
        INSTANCE.registerMessage(id, ChangeModeMessage.class, ChangeModeMessage::encode, ChangeModeMessage::decode,
                ChangeModeMessage::handle);
    }
}
