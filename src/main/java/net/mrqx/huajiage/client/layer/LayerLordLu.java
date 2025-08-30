package net.mrqx.huajiage.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.client.HuaJiLayers;
import net.mrqx.huajiage.item.equipment.armor.ItemFiftyFiftyHelmet;
import net.mrqx.huajiage.registy.HuaJiItems;

@OnlyIn(Dist.CLIENT)
public class LayerLordLu<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation TEXTURE = HuaJiAgeMod.prefix("textures/entity/lord_lu_power.png");
    private final ModelPart model;

    public LayerLordLu(RenderLayerParent<T, M> pRenderer, EntityModelSet pModelSet) {
        super(pRenderer);
        model = pModelSet.bakeLayer(HuaJiLayers.LORD_LU);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing,
                       float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (ItemFiftyFiftyHelmet.isFiftyFiftyActive(pLivingEntity)) {
            pPoseStack.pushPose();
            pPoseStack.mulPose(Axis.ZP.rotationDegrees(180));
            pPoseStack.mulPose(Axis.XP.rotationDegrees(180));
            pPoseStack.translate(0, 0.4, -0.25);

            if (pLivingEntity.isCrouching()) {
                pPoseStack.mulPose(Axis.XP.rotation(-0.5f));
            }
            Minecraft.getInstance().getItemRenderer().renderStatic(pLivingEntity, HuaJiItems.LORD_CORE.get().getDefaultInstance(),
                    ItemDisplayContext.FIXED, false, pPoseStack, pBuffer, pLivingEntity.level(), pPackedLight, OverlayTexture.NO_OVERLAY, pLivingEntity.getId());
            pPoseStack.popPose();

            if (ItemFiftyFiftyHelmet.isFiftyFiftyLord(pLivingEntity)
                    && ItemFiftyFiftyHelmet.Mode.getMode(pLivingEntity.getItemBySlot(EquipmentSlot.HEAD)).equals(ItemFiftyFiftyHelmet.Mode.ON)
                    && !pLivingEntity.isCrouching()) {
                VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
                pPoseStack.pushPose();
                pPoseStack.translate(0, 0, 0.5);

                model.getChild("core").render(pPoseStack, vertexconsumer, pPackedLight, LivingEntityRenderer.getOverlayCoords(pLivingEntity, 0.0F));

                ModelPart wing = model.getChild("wing");
                wing.zRot = -pAgeInTicks / 20;
                wing.render(pPoseStack, vertexconsumer, pPackedLight, LivingEntityRenderer.getOverlayCoords(pLivingEntity, 0.0F));

                ModelPart wingBones = model.getChild("wingBones");
                wingBones.zRot = pAgeInTicks / 10;
                wingBones.render(pPoseStack, vertexconsumer, pPackedLight, LivingEntityRenderer.getOverlayCoords(pLivingEntity, 0.0F));
                pPoseStack.popPose();
            }
        }
    }
}
