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
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.client.HuaJiLayers;
import net.mrqx.huajiage.compat.HuaJiCompat;
import net.mrqx.huajiage.item.equipment.armor.ItemFiftyFiftyHelmet;
import net.mrqx.huajiage.registy.HuaJiItems;

@OnlyIn(Dist.CLIENT)
public class LayerLordLu<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation LORD_LU_TEXTURE = HuaJiAgeMod.prefix("textures/entity/lord_lu_power.png");
    private final ModelPart modelLordLu;
    private static final ResourceLocation LORD_LU_WING_TEXTURE = HuaJiAgeMod.prefix("textures/entity/lord_lu_wing.png");
    private final ModelPart modelLordLuWing;

    public LayerLordLu(RenderLayerParent<T, M> pRenderer, EntityModelSet pModelSet) {
        super(pRenderer);
        modelLordLu = pModelSet.bakeLayer(HuaJiLayers.LORD_LU);
        modelLordLuWing = pModelSet.bakeLayer(HuaJiLayers.LORD_LU_WING);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing,
                       float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        boolean active = ItemFiftyFiftyHelmet.isFiftyFiftyActive(pLivingEntity);
        boolean lord = ItemFiftyFiftyHelmet.isFiftyFiftyLord(pLivingEntity);
        if (active) {
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

            if (lord && ItemFiftyFiftyHelmet.Mode.getMode(pLivingEntity.getItemBySlot(EquipmentSlot.HEAD)).equals(ItemFiftyFiftyHelmet.Mode.ON)
                    && !pLivingEntity.isCrouching()) {
                VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityCutoutNoCull(LORD_LU_TEXTURE));
                pPoseStack.pushPose();
                pPoseStack.translate(0, 0, 0.5);

                modelLordLu.getChild("core")
                        .render(pPoseStack, vertexconsumer, 0xF000F0, OverlayTexture.NO_OVERLAY);

                ModelPart wing = modelLordLu.getChild("wing");
                wing.zRot = -pAgeInTicks / 20;
                wing.render(pPoseStack, vertexconsumer, 0xF000F0, OverlayTexture.NO_OVERLAY);

                ModelPart wingBones = modelLordLu.getChild("wingBones");
                wingBones.zRot = pAgeInTicks / 10;
                wingBones.render(pPoseStack, vertexconsumer, 0xF000F0, OverlayTexture.NO_OVERLAY);
                pPoseStack.popPose();
            }
        }
        ItemStack wing = pLivingEntity.getItemBySlot(EquipmentSlot.CHEST);
        if (wing.is(HuaJiItems.LORD_LU_WING.get())) {
            pPoseStack.pushPose();
            pPoseStack.mulPose(Axis.ZP.rotationDegrees(180));
            pPoseStack.mulPose(Axis.YP.rotationDegrees(90));
            pPoseStack.mulPose(Axis.XP.rotationDegrees(180));

            float off = (float) Math.cos(0.1 * pAgeInTicks);
            pPoseStack.translate(-0.6, -0.03 + off * 0.05, 0);

            VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityCutoutNoCull(LORD_LU_WING_TEXTURE));

            modelLordLuWing.getChild("wings")
                    .render(pPoseStack, vertexconsumer, 0xF000F0, OverlayTexture.NO_OVERLAY);

            if (active) {
                modelLordLuWing.getChild("ring")
                        .render(pPoseStack, vertexconsumer, 0xF000F0, OverlayTexture.NO_OVERLAY);
            }

            if (lord) {
                modelLordLuWing.getChild("sun")
                        .render(pPoseStack, vertexconsumer, 0xF000F0, OverlayTexture.NO_OVERLAY);
            }

            HuaJiCompat.Factories.renderLordLuWingGuns(pPoseStack, pBuffer, pLivingEntity, wing);
        }
    }

}
