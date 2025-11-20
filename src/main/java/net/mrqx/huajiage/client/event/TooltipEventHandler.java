package net.mrqx.huajiage.client.event;

import com.mojang.datafixers.util.Either;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.item.equipment.armor.ItemLordLuWing;

@Mod.EventBusSubscriber
public class TooltipEventHandler {
    @SubscribeEvent
    public static void onGatherComponents(RenderTooltipEvent.GatherComponents event) {
        if (event.getItemStack().getItem() instanceof ItemLordLuWing wing) {
            wing.getItemComponent(event.getItemStack())
                    .ifPresent(component -> {
                        for (int i = 0; i < event.getTooltipElements().size(); i++) {
                            if (event.getTooltipElements().get(i).equals(Either.left(ItemLordLuWing.REMOVE_FLAG))) {
                                event.getTooltipElements().set(i, Either.right(component));
                            }
                        }
                    });
        }
    }
}
