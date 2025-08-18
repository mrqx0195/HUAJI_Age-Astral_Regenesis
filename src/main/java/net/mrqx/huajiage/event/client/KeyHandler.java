package net.mrqx.huajiage.event.client;

import net.minecraft.client.Minecraft;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.client.HuaJiKeyMappings;
import net.mrqx.huajiage.network.ChangeModeMessage;
import net.mrqx.huajiage.network.NetworkManager;

@Mod.EventBusSubscriber
public class KeyHandler {
    public static boolean changeModeIsDown = false;

    @SubscribeEvent
    public static void onClientTickEvent(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        if (changeModeIsDown != HuaJiKeyMappings.KEY_CHANGE_MODE.isDown() && HuaJiKeyMappings.KEY_CHANGE_MODE.isDown()) {
            ChangeModeMessage message = new ChangeModeMessage();
            message.shift = Minecraft.getInstance().options.keyShift.isDown();
            NetworkManager.INSTANCE.sendToServer(message);
        }
        changeModeIsDown = HuaJiKeyMappings.KEY_CHANGE_MODE.isDown();
    }
}
