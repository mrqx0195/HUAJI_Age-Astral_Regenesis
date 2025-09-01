package net.mrqx.huajiage.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.client.HuaJiLayers;
import net.mrqx.huajiage.client.model.entity.ModelMultiKnife;
import net.mrqx.huajiage.entity.EntityMultiKnife;

public class RenderMultiKnife extends EntityRenderer<EntityMultiKnife> {
    public static final ResourceLocation LOCATION = HuaJiAgeMod.prefix("textures/entity/multi_knife.png");
    public static final ResourceLocation LOCATION_SHINY = HuaJiAgeMod.prefix("textures/entity/multi_knife_shiny.png");
    private final ModelMultiKnife<EntityMultiKnife> model;
    private final ModelMultiKnife<EntityMultiKnife> modelShiny;

    public RenderMultiKnife(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.model = new ModelMultiKnife<>(pContext.bakeLayer(HuaJiLayers.MULTI_KNIFE));
        this.modelShiny = new ModelMultiKnife<>(pContext.bakeLayer(HuaJiLayers.MULTI_KNIFE_SHINY));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityMultiKnife pEntity) {
        return pEntity.isShiny() ? LOCATION_SHINY : LOCATION;
    }

    @Override
    public void render(EntityMultiKnife pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.translate(0, pEntity.getEyeHeight() * 1.5, 0);
        pPoseStack.mulPose(Axis.YP.rotationDegrees(pEntity.getYRot()));
        pPoseStack.mulPose(Axis.XP.rotationDegrees(90));
        pPoseStack.mulPose(Axis.XN.rotationDegrees(pEntity.getXRot()));
        pPoseStack.translate(0, -0.3, 0);
        pPoseStack.scale(1, -1, -1);

        if (pEntity.isShiny()) {
            modelShiny.renderToBuffer(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucentCull(this.getTextureLocation(pEntity))), 0xF000F0, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        } else {
            model.renderToBuffer(pPoseStack, pBuffer.getBuffer(RenderType.entityCutout(this.getTextureLocation(pEntity))), pPackedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        }

        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }
}
