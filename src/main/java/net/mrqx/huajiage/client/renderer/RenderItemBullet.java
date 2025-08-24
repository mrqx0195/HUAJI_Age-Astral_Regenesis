package net.mrqx.huajiage.client.renderer;

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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.entity.EntityItemBullet;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class RenderItemBullet extends EntityRenderer<EntityItemBullet> {
    private static final ResourceLocation TEXTURE = HuaJiAgeMod.prefix("");
    private final ItemRenderer itemRenderer;

    public RenderItemBullet(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.itemRenderer = pContext.getItemRenderer();
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull ResourceLocation getTextureLocation(@NotNull EntityItemBullet pEntity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }

    @Override
    public void render(@NotNull EntityItemBullet pEntity, float pEntityYaw, float pPartialTick, @NotNull PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity.tickCount >= 2 || this.entityRenderDispatcher.camera.getEntity().distanceToSqr(pEntity) >= 12.25) {
            pPoseStack.pushPose();
            Random random = new Random(pEntity.randomSeed);
            pPoseStack.translate(0, 0.4, 0);
            pPoseStack.mulPose(Axis.XP.rotationDegrees(random.nextFloat() * 90));
            pPoseStack.mulPose(Axis.YP.rotationDegrees(random.nextFloat() * 90));
            pPoseStack.mulPose(Axis.ZP.rotationDegrees(random.nextFloat() * 90));
            this.itemRenderer.renderStatic(pEntity.getItem(), ItemDisplayContext.GROUND, pPackedLight, OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, pEntity.level(), pEntity.getId());
            pPoseStack.popPose();
            super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
        }
    }
}
