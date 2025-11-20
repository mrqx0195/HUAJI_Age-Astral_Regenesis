package net.mrqx.huajiage.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.client.HuaJiKeyMappings;
import net.mrqx.huajiage.network.FivePowerMessage;
import net.mrqx.huajiage.network.HuaJiKeyMessage;
import net.mrqx.huajiage.network.NetworkManager;

import java.util.EnumSet;

@Mod.EventBusSubscriber(Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class KeyHandler {
    private static final EnumSet<HuaJiKeyMessage.Keys> KEY_SET = EnumSet.noneOf(HuaJiKeyMessage.Keys.class);

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onClientTickEvent(TickEvent.RenderTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
            return;
        }
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) {
            return;
        }

        EnumSet<HuaJiKeyMessage.Keys> newKeySet = EnumSet.noneOf(HuaJiKeyMessage.Keys.class);

        if (HuaJiKeyMappings.KEY_CHANGE_MODE.isDown()) {
            newKeySet.add(HuaJiKeyMessage.Keys.CHANGE_MODE);
        }

        if (HuaJiKeyMappings.KEY_TRIGGER_STAND.isDown()) {
            newKeySet.add(HuaJiKeyMessage.Keys.TRIGGER_STAND);
        }

        if (HuaJiKeyMappings.KEY_CHANGE_STAND_MODE.isDown()) {
            newKeySet.add(HuaJiKeyMessage.Keys.CHANGE_STAND_MODE);
        }

        if (HuaJiKeyMappings.KEY_STAND_SKILL.isDown()) {
            newKeySet.add(HuaJiKeyMessage.Keys.STAND_SKILL);
        }

        if (!KEY_SET.equals(newKeySet)) {
            HuaJiKeyMessage message = new HuaJiKeyMessage();
            message.oldCommand = KEY_SET.clone();
            message.currentCommand = newKeySet.clone();
            NetworkManager.INSTANCE.sendToServer(message);

            KEY_SET.clear();
            KEY_SET.addAll(newKeySet);
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    @SuppressWarnings("InstantiationOfUtilityClass")
    public static void onLeftClick(PlayerInteractEvent event) {
        if (event instanceof PlayerInteractEvent.LeftClickBlock
                || event instanceof PlayerInteractEvent.LeftClickEmpty) {
            if (event.getEntity().getMainHandItem().isEmpty()) {
                NetworkManager.INSTANCE.sendToServer(new FivePowerMessage());
            }
        }
    }
}
