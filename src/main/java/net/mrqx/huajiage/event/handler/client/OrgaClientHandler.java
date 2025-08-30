package net.mrqx.huajiage.event.handler.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.capability.stand.StandDataCapabilityProvider;
import net.mrqx.huajiage.item.equipment.armor.ItemOrgaArmor;
import net.mrqx.huajiage.item.stand.ItemOrgaRequiem;
import net.mrqx.huajiage.registy.HuaJiEffects;
import net.mrqx.huajiage.registy.HuaJiSoundEvents;
import net.mrqx.huajiage.stand.Stand;
import net.mrqx.huajiage.stand.StandOrgaRequiem;
import net.mrqx.huajiage.utils.HuaJiSoundPlayer;

/**
 * @see net.mrqx.huajiage.event.handler.OrgaEventHandler
 */
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber
public class OrgaClientHandler {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onLivingTickEvent(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        startRequiemMusic(entity);
        stopRequiemMusic(entity);
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onRenderPlayerEvent(RenderPlayerEvent.Pre event) {
        event.getEntity().getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
            Stand stand = Stand.getStand(data.getStand());
            if (stand instanceof StandOrgaRequiem && data.isTriggered() && data.getState().equals(Stand.STATE_FLY)) {
                event.setCanceled(true);
            }
        });
    }

    private static void startRequiemMusic(LivingEntity living) {
        if (living.hasEffect(HuaJiEffects.REQUIEM.get())) {
            MobEffectInstance effect = living.getEffect(HuaJiEffects.REQUIEM.get());
            if (effect != null) {
                int requiemDuration = effect.getDuration();
                if (requiemDuration == 30 * 20 - 1) {
                    if (living.level().isClientSide) {
                        Minecraft.getInstance().getSoundManager().stop();
                        HuaJiSoundPlayer.playMusic(HuaJiSoundEvents.ORGA_REQUIEM_GOLD.get());
                    }
                }
            }
        }
    }

    private static void stopRequiemMusic(LivingEntity target) {
        if (target instanceof Player player) {
            boolean hasOrga = ItemOrgaArmor.hasAllOrgaArmor(player)
                    || player.getCapability(StandDataCapabilityProvider.STAND_DATA)
                    .map(data -> Stand.getStand(data.getStand()) instanceof StandOrgaRequiem).orElse(false);

            if (hasOrga && player.hasEffect(HuaJiEffects.REQUIEM.get())) {
                boolean hasRequiemItem = ItemOrgaRequiem.hasOrgaRequiem(player);
                boolean hasStandPotion = player.hasEffect(HuaJiEffects.STAND_POWER.get());

                if (!hasRequiemItem && !hasStandPotion) {
                    Minecraft.getInstance().getSoundManager().stop();
                }
            }
        }
    }
}
