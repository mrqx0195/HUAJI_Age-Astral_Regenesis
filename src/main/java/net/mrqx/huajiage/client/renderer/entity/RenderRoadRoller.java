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
import net.mrqx.huajiage.entity.EntityRoadRoller;
import org.jetbrains.annotations.NotNull;

public class RenderRoadRoller extends EntityRenderer<EntityRoadRoller> {
    private final ItemRenderer itemRenderer;

    public RenderRoadRoller(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.itemRenderer = pContext.getItemRenderer();
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull ResourceLocation getTextureLocation(@NotNull EntityRoadRoller pEntity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }

    @Override
    public void render(@NotNull EntityRoadRoller pEntity, float pEntityYaw, float pPartialTick, @NotNull PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.mulPose(Axis.YP.rotationDegrees(pEntity.getYRot()));
        pPoseStack.mulPose(Axis.XP.rotationDegrees(180F));
        pPoseStack.mulPose(Axis.YN.rotationDegrees(90F));
        pPoseStack.mulPose(Axis.ZN.rotationDegrees(Math.abs(pEntity.getXRot())));

        pPoseStack.scale(1F, -1F, -1F);

        this.itemRenderer.renderStatic(pEntity.getItem(), ItemDisplayContext.GROUND, pPackedLight, OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, pEntity.level(), pEntity.getId());
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }
}
