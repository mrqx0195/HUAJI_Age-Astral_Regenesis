package net.mrqx.huajiage.event.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.utils.HuajiSoundPlayer;

@Mod.EventBusSubscriber
@OnlyIn(Dist.CLIENT)
public class ClientTickHandler {
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onClientTickEvent(TickEvent.RenderTickEvent event) {
        HuajiSoundPlayer.clientSoundsTick();
    }
}
