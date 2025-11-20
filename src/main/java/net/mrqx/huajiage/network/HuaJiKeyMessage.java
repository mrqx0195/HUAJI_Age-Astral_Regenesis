package net.mrqx.huajiage.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.network.NetworkEvent;
import net.mrqx.huajiage.event.KeyInputEvent;

import java.util.EnumSet;
import java.util.Objects;
import java.util.function.Supplier;

public class HuaJiKeyMessage {
    public EnumSet<Keys> oldCommand = EnumSet.noneOf(Keys.class);
    public EnumSet<Keys> currentCommand = EnumSet.noneOf(Keys.class);

    public HuaJiKeyMessage() {
    }

    public static HuaJiKeyMessage decode(FriendlyByteBuf buf) {
        HuaJiKeyMessage huaJiKeyMessage = new HuaJiKeyMessage();
        huaJiKeyMessage.oldCommand = buf.readEnumSet(Keys.class);
        huaJiKeyMessage.currentCommand = buf.readEnumSet(Keys.class);
        return huaJiKeyMessage;
    }

    public static void encode(HuaJiKeyMessage msg, FriendlyByteBuf buf) {
        buf.writeEnumSet(msg.oldCommand, Keys.class);
        buf.writeEnumSet(msg.currentCommand, Keys.class);
    }

    public static void handle(HuaJiKeyMessage huaJiKeyMessage, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> MinecraftForge.EVENT_BUS.post(new KeyInputEvent(Objects.requireNonNull(ctx.get().getSender()), huaJiKeyMessage.oldCommand, huaJiKeyMessage.currentCommand)));
        ctx.get().setPacketHandled(true);
    }

    public enum Keys {
        /**
         * @see net.mrqx.huajiage.client.HuaJiKeyMappings#KEY_CHANGE_MODE
         */
        CHANGE_MODE,
        /**
         * @see net.mrqx.huajiage.client.HuaJiKeyMappings#KEY_TRIGGER_STAND
         */
        TRIGGER_STAND,
        /**
         * @see net.mrqx.huajiage.client.HuaJiKeyMappings#KEY_STAND_SKILL
         */
        STAND_SKILL,
        /**
         * @see net.mrqx.huajiage.client.HuaJiKeyMappings#KEY_CHANGE_STAND_MODE
         */
        CHANGE_STAND_MODE
    }
}
