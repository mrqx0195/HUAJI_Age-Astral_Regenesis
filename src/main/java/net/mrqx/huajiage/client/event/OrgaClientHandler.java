package net.mrqx.huajiage.client.event;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.capability.stand.StandDataCapabilityProvider;
import net.mrqx.huajiage.client.layer.LayerStand;
import net.mrqx.huajiage.event.handler.OrgaEventHandler;
import net.mrqx.huajiage.item.equipment.armor.ItemOrgaArmor;
import net.mrqx.huajiage.item.stand.ItemOrgaRequiem;
import net.mrqx.huajiage.mixin.AccessorLivingEntityRenderer;
import net.mrqx.huajiage.registy.HuaJiEffects;
import net.mrqx.huajiage.registy.HuaJiSoundEvents;
import net.mrqx.huajiage.stand.Stand;
import net.mrqx.huajiage.stand.StandOrgaRequiem;
import net.mrqx.huajiage.utils.HuaJiSoundPlayer;

/**
 * @see net.mrqx.huajiage.event.handler.OrgaEventHandler
 */
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class OrgaClientHandler {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onLivingTickEvent(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        startRequiemMusic(entity);
        stopRequiemMusic(entity);
    }

    @SuppressWarnings("unchecked")
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static <T extends LivingEntity, M extends EntityModel<T>> void onRenderPlayerEvent(RenderLivingEvent.Pre<T, M> event) {
        T entity = (T) event.getEntity();
        entity.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
            Stand stand = Stand.getStand(data.getStand());
            if (stand instanceof StandOrgaRequiem && data.isTriggered() && data.getState().equals(Stand.STATE_FLY)) {
                event.setCanceled(true);
                AccessorLivingEntityRenderer<T, M> accessorLivingEntityRenderer = (AccessorLivingEntityRenderer<T, M>) event.getRenderer();
                accessorLivingEntityRenderer.getLayers().forEach(layer -> {
                    if (layer instanceof LayerStand layerStand) {
                        // 我TM在写啥
                        PoseStack poseStack = event.getPoseStack();
                        poseStack.pushPose();
                        boolean shouldSit = entity.isPassenger() && (entity.getVehicle() != null && entity.getVehicle().shouldRiderSit());
                        float partialTicks = event.getPartialTick();
                        float f = Mth.rotLerp(partialTicks, entity.yBodyRotO, entity.yBodyRot);
                        float f1 = Mth.rotLerp(partialTicks, entity.yHeadRotO, entity.yHeadRot);
                        float f2 = f1 - f;
                        if (shouldSit && entity.getVehicle() instanceof LivingEntity livingentity) {
                            f = Mth.rotLerp(partialTicks, livingentity.yBodyRotO, livingentity.yBodyRot);
                            f2 = f1 - f;
                            float f3 = Mth.wrapDegrees(f2);
                            if (f3 < -85.0F) {
                                f3 = -85.0F;
                            }
                            if (f3 >= 85.0F) {
                                f3 = 85.0F;
                            }
                            f = f1 - f3;
                            if (f3 * f3 > 2500.0F) {
                                f += f3 * 0.2F;
                            }
                            f2 = f1 - f;
                        }
                        float f6 = Mth.lerp(partialTicks, entity.xRotO, entity.getXRot());
                        if (LivingEntityRenderer.isEntityUpsideDown(entity)) {
                            f6 *= -1.0F;
                            f2 *= -1.0F;
                        }
                        float f8 = 0.0F;
                        float f5 = 0.0F;
                        if (!shouldSit && entity.isAlive()) {
                            f8 = entity.walkAnimation.speed(partialTicks);
                            f5 = entity.walkAnimation.position(partialTicks);
                            if (entity.isBaby()) {
                                f5 *= 3.0F;
                            }

                            if (f8 > 1.0F) {
                                f8 = 1.0F;
                            }
                        }
                        float f7 = entity.tickCount + partialTicks;
                        accessorLivingEntityRenderer.huaJiAgeInvokeSetupRotations(entity, poseStack, f7, f, partialTicks);
                        poseStack.scale(-1.0F, -1.0F, 1.0F);
                        accessorLivingEntityRenderer.huaJiAgeInvokeScale(entity, poseStack, partialTicks);
                        poseStack.translate(0.0F, -1.501F, 0.0F);
                        layerStand.render(poseStack, event.getMultiBufferSource(), event.getPackedLight(), entity, f5, f8, partialTicks, f7, f2, f6);
                        poseStack.popPose();
                    }
                });
            }
        });
    }

    private static void startRequiemMusic(LivingEntity living) {
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
        MobEffectInstance effectHopeFlower = living.getEffect(HuaJiEffects.HOPE_FLOWER.get());
        if (effectHopeFlower != null) {
            if (effectHopeFlower.getDuration() == OrgaEventHandler.HOPE_FLOWER_TIME) {
                Minecraft.getInstance().getSoundManager().stop();
                HuaJiSoundPlayer.playMusic(HuaJiSoundEvents.ORGA_FLOWER.get());
            }
        }
    }

    private static void stopRequiemMusic(LivingEntity target) {
        if (target instanceof Player player) {
            boolean hasOrga = ItemOrgaArmor.hasAllOrgaArmor(player)
                    || player.getCapability(StandDataCapabilityProvider.STAND_DATA)
                    .map(data -> Stand.getStand(data.getStand()) instanceof StandOrgaRequiem).orElse(false);

            if (hasOrga && player.hasEffect(HuaJiEffects.REQUIEM.get())) {
                boolean hasRequiemItem = ItemOrgaRequiem.hasValidOrgaRequiem(player);
                boolean hasStandPotion = player.hasEffect(HuaJiEffects.STAND_POWER.get());

                if (!hasRequiemItem && !hasStandPotion) {
                    Minecraft.getInstance().getSoundManager().stop();
                }
            }
        }
    }
}
