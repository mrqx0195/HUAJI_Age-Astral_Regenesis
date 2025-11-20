package net.mrqx.huajiage.compat.tacz;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrqx.huajiage.item.equipment.armor.ItemLordLuWing;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class LordLuWingGunsRenderer {
    public static <T extends LivingEntity> void renderLordLuWingGuns(PoseStack pPoseStack, MultiBufferSource pBuffer, T pLivingEntity, ItemStack wing) {
        pPoseStack.scale(0.7F, 0.7F, 0.7F);
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(90));
        pPoseStack.mulPose(Axis.XP.rotationDegrees(90));

        List<ItemStack> guns = ItemLordLuWing.getGunsFromWing(wing);

        pPoseStack.translate(-0.5, 0, 0);
        for (int i = 0; i < 2; i++) {
            pPoseStack.pushPose();
            pPoseStack.translate(0, -0.2, 0);
            pPoseStack.mulPose(Axis.ZP.rotationDegrees(47.5F));
            pPoseStack.translate(-1.8, 0.1, 0.16);

            Minecraft.getInstance().getItemRenderer().renderStatic(pLivingEntity, guns.get(i),
                    ItemDisplayContext.FIXED, false, pPoseStack, pBuffer, pLivingEntity.level(),
                    0xF000F0, OverlayTexture.NO_OVERLAY, pLivingEntity.getId());

            pPoseStack.mulPose(Axis.ZP.rotationDegrees(20));
            pPoseStack.translate(-0.3, -1.05, 0);

            Minecraft.getInstance().getItemRenderer().renderStatic(pLivingEntity, guns.get(2 + i),
                    ItemDisplayContext.FIXED, false, pPoseStack, pBuffer, pLivingEntity.level(),
                    0xF000F0, OverlayTexture.NO_OVERLAY, pLivingEntity.getId());

            pPoseStack.mulPose(Axis.ZP.rotationDegrees(20));
            pPoseStack.translate(-0.3, -1.05, 0);

            Minecraft.getInstance().getItemRenderer().renderStatic(pLivingEntity, guns.get(4 + i),
                    ItemDisplayContext.FIXED, false, pPoseStack, pBuffer, pLivingEntity.level(),
                    0xF000F0, OverlayTexture.NO_OVERLAY, pLivingEntity.getId());

            pPoseStack.mulPose(Axis.ZP.rotationDegrees(35));
            pPoseStack.translate(-0.55, -1.35, 0);

            Minecraft.getInstance().getItemRenderer().renderStatic(pLivingEntity, guns.get(6 + i),
                    ItemDisplayContext.FIXED, false, pPoseStack, pBuffer, pLivingEntity.level(),
                    0xF000F0, OverlayTexture.NO_OVERLAY, pLivingEntity.getId());
            pPoseStack.popPose();
            pPoseStack.mulPose(Axis.XP.rotationDegrees(180));
        }

        pPoseStack.popPose();
    }
}
