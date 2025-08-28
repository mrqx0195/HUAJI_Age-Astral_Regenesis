package net.mrqx.huajiage.client.layer;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderHandEvent;
import net.mrqx.huajiage.capability.stand.StandDataCapabilityProvider;
import net.mrqx.huajiage.client.model.stand.ModelStandBase;
import net.mrqx.huajiage.registy.HuaJiStands;
import net.mrqx.huajiage.stand.AbstractStand;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class LayerStand<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    public static final Map<String, LayerStand<?, ?>> PLAYER_LAYERS_MAP = new HashMap<>();
    public final Map<ModelLayerLocation, ModelStandBase> modelMap;

    public LayerStand(RenderLayerParent<T, M> pRenderer, EntityModelSet pModelSet) {
        super(pRenderer);
        Map<ModelLayerLocation, ModelStandBase> map = new HashMap<>();
        HuaJiStands.REGISTRY.get().forEach(stand -> stand.getModelFunction().forEach((modelLayerLocation, modelSupplier) -> map.put(modelLayerLocation, modelSupplier.apply(pModelSet.bakeLayer(modelLayerLocation)))));
        modelMap = ImmutableMap.copyOf(map);
    }

    @Override
    public void render(@NotNull PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight, @NotNull T pLivingEntity, float pLimbSwing,
                       float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (Minecraft.getInstance().player != null) {
            Minecraft.getInstance().player.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(playerData -> {
                if (AbstractStand.getStand(playerData.getStand()) != null) {
                    pLivingEntity.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
                        AbstractStand stand = AbstractStand.getStand(data.getStand());
                        if (stand != null && data.isTriggered()) {
                            VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityTranslucentCull(stand.getModelTextures().get(data.getState())));
                            ModelStandBase model = modelMap.get(stand.getModelLocations().get(data.getState()));
                            List<Double> translations = stand.getModelTranslations().get(data.getState());

                            pPoseStack.pushPose();
                            RenderSystem.enableBlend();

                            pPoseStack.translate(translations.get(0), translations.get(1), translations.get(2));
                            model.setupAnim(pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
                            model.renderToBuffer(pPoseStack, vertexconsumer, 0xF000F0, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
                            if (stand.shouldRenderExtra(pLivingEntity, data)) {
                                model.renderExtra(pPoseStack, pBuffer, pPackedLight, pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTick, pAgeInTicks, pNetHeadYaw, pHeadPitch, data, stand);
                            }

                            RenderSystem.disableBlend();
                            pPoseStack.popPose();
                        }
                    });
                }
            });
        }
    }

    public void renderHand(RenderHandEvent event, LocalPlayer player) {
        player.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
            AbstractStand stand = AbstractStand.getStand(data.getStand());
            if (stand != null && data.isTriggered()) {
                PoseStack poseStack = event.getPoseStack();
                ModelStandBase model = modelMap.get(stand.getModelLocations().get(data.getState()));
                poseStack.pushPose();
                RenderSystem.enableBlend();
                poseStack.translate(0, 0.8, 0.3);
                model.renderHand(event, player, stand, data);
                RenderSystem.disableBlend();
                poseStack.popPose();
                if (!stand.shouldRenderHand(player, data)) {
                    event.setCanceled(true);
                }
            }
        });
    }
}
