package net.mrqx.huajiage.event.handler;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.capability.stand.StandDataCapabilityProvider;
import net.mrqx.huajiage.event.KeyInputEvent;
import net.mrqx.huajiage.network.HuaJiKeyMessage;
import net.mrqx.huajiage.stand.AbstractStand;

@Mod.EventBusSubscriber
public class StandHandler {
    @SubscribeEvent
    public static void onKeyInputEvent(KeyInputEvent event) {
        event.getEntity().getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
            AbstractStand stand = AbstractStand.getStand(data.getStand());
            if (stand != null) {
                if (!event.oldCommand.contains(HuaJiKeyMessage.Keys.TRIGGER_STAND) && event.currentCommand.contains(HuaJiKeyMessage.Keys.TRIGGER_STAND)) {
                    if (data.isTriggered()) {
                        data.setTriggered(false);
                        data.setState(AbstractStand.STATE_DEFAULT);
                    } else {
                        data.setTriggered(true);
                        stand.onTriggered(event.getEntity(), data);
                    }
                }
                if (!event.oldCommand.contains(HuaJiKeyMessage.Keys.CHANGE_STAND_MODE) && event.currentCommand.contains(HuaJiKeyMessage.Keys.CHANGE_STAND_MODE)) {
                    if (data.isTriggered()) {
                        int i = stand.getStates().indexOf(data.getState());
                        if (i == -1) {
                            data.setState(AbstractStand.STATE_DEFAULT);
                        } else if (stand.getStates().size() <= i + 1) {
                            data.setState(stand.getStates().get(0));
                        } else {
                            data.setState(stand.getStates().get(i + 1));
                        }
                    }
                }
                if (!event.oldCommand.contains(HuaJiKeyMessage.Keys.STAND_SKILL) && event.currentCommand.contains(HuaJiKeyMessage.Keys.STAND_SKILL)) {
                    stand.trySkill(event.getEntity(), data);
                }
            }
        });
    }
}
