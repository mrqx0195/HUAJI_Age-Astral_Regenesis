package net.mrqx.huajiage.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.mrqx.huajiage.entity.EntityOrgaHairKnife;

public class RenderOrgaHairKnife extends EntityRenderer<EntityOrgaHairKnife> {
    private final ItemRenderer itemRenderer;

    public RenderOrgaHairKnife(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.itemRenderer = pContext.getItemRenderer();
    }

    @Override
    @SuppressWarnings("deprecation")
    public ResourceLocation getTextureLocation(EntityOrgaHairKnife pEntity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }

    @Override
    public void render(EntityOrgaHairKnife pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();

        if (pEntity.getDeltaMovement().x() == 0 && pEntity.getDeltaMovement().y() == 0 && pEntity.getDeltaMovement().z() == 0) {
            pPoseStack.mulPose(Axis.YP.rotationDegrees(pEntity.getXRot()));
        } else {
            pPoseStack.mulPose(Axis.YP.rotationDegrees(pEntity.getYRot()));
        }

        pPoseStack.mulPose(Axis.XP.rotationDegrees(180F));
        pPoseStack.mulPose(Axis.YN.rotationDegrees(90F));
        pPoseStack.mulPose(Axis.XP.rotationDegrees(pEntity.getRotationRandom()));
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(pEntity.tickCount * 40));

        if (pEntity.getDeltaMovement().x() == 0 && pEntity.getDeltaMovement().y() == 0 && pEntity.getDeltaMovement().z() == 0) {
            pPoseStack.mulPose(Axis.ZP.rotationDegrees(pEntity.getYRot()));
        } else {
            pPoseStack.mulPose(Axis.ZP.rotationDegrees(pEntity.getXRot()));
        }

        pPoseStack.scale(1F, -1F, -1F);

        this.itemRenderer.renderStatic(pEntity.getItem(), ItemDisplayContext.GROUND, pPackedLight, OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, pEntity.level(), pEntity.getId());
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }
}
