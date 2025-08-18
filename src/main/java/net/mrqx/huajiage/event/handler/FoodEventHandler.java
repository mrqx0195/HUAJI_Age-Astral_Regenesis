package net.mrqx.huajiage.event.handler;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.registy.HuaJiItems;

@Mod.EventBusSubscriber
public class FoodEventHandler {
    @SubscribeEvent
    public static void onLivingEntityUseItemEventFinish(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity().level().isClientSide) {
            return;
        }
        if (event.getItem().is(HuaJiItems.EGG_RICE.get())) {
            LivingEntity entity = event.getEntity();
            entity.heal(10);
            entity.sendSystemMessage(Component.translatable("message.huajiage.egg_rice").withStyle(ChatFormatting.GOLD));
        } else if (event.getItem().is(HuaJiItems.ULTIMATE_EGG_RICE.get())) {
            LivingEntity entity = event.getEntity();
            entity.setHealth(entity.getHealth() + 22);
            entity.sendSystemMessage(Component.translatable("message.huajiage.egg_rice").withStyle(ChatFormatting.GOLD));
        }
    }
}
