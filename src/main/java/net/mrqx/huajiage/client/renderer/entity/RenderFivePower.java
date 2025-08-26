package net.mrqx.huajiage.client.renderer.entity;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.entity.EntityFivePower;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

public class RenderFivePower extends EntityRenderer<EntityFivePower> {
    private static final ResourceLocation TEXTURE_0 = HuaJiAgeMod.prefix("textures/entity/de_bullet_0.png");
    private static final ResourceLocation TEXTURE_1 = HuaJiAgeMod.prefix("textures/entity/de_bullet_1.png");

    public RenderFivePower(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull ResourceLocation getTextureLocation(@NotNull EntityFivePower pEntity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }

    @Override
    public void render(@NotNull EntityFivePower pEntity, float pEntityYaw, float pPartialTick, @NotNull PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity.tickCount >= 2 || this.entityRenderDispatcher.camera.getEntity().distanceToSqr(pEntity) >= 12.25) {
            pPoseStack.pushPose();
            pPoseStack.translate(0, pEntity.getBbHeight() / 2, 0);
            pPoseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
            pPoseStack.mulPose(Axis.ZP.rotation((float) Math.PI));
            pPoseStack.scale(1.5F, 1.5F, 1.5F);

            boolean depthTestEnabled = GL11.glIsEnabled(GL11.GL_DEPTH_TEST);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableDepthTest();

            TextureManager texturemanager = Minecraft.getInstance().getTextureManager();
            ResourceLocation texture = pEntity.isDe() ? TEXTURE_1 : TEXTURE_0;
            texturemanager.getTexture(texture).setFilter(false, false);
            RenderSystem.setShaderTexture(0, texture);

            drawTexturedQuad(pPoseStack, -0.5F, -0.5F, 0, 0, 1, 1, 0, 1);

            pPoseStack.popPose();
            if (depthTestEnabled) {
                RenderSystem.enableDepthTest();
            } else {
                RenderSystem.disableDepthTest();
            }
            super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
        }
    }


    public static void drawTexturedQuad(PoseStack poseStack, float x, float y, float u, float v, float width, float height, float zLevel, float f) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder wr = tesselator.getBuilder();
        wr.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        Matrix4f m = poseStack.last().pose();
        wr.vertex(m, (x), (y + height), zLevel).uv((u + 0.0F) * f, (v + height) * f).endVertex();
        wr.vertex(m, (x + width), (y + height), zLevel).uv((u + width) * f, (v + height) * f).endVertex();
        wr.vertex(m, (x + width), (y), zLevel).uv((u + width) * f, (v) * f).endVertex();
        wr.vertex(m, (x), (y), zLevel).uv(u * f, (v) * f).endVertex();
        BufferUploader.drawWithShader(wr.end());
    }
}
