package net.mrqx.huajiage.client.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class HuaJiArmorModel extends HumanoidModel<LivingEntity> {
    public HuaJiArmorModel(ModelPart root) {
        super(root);
    }

    @Override
    public void setupAnim(@NotNull LivingEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entityIn instanceof ArmorStand entityarmorstand) {
            this.head.xRot = (float) (Math.PI / 180F) * entityarmorstand.getHeadPose().getX();
            this.head.yRot = (float) (Math.PI / 180F) * entityarmorstand.getHeadPose().getY();
            this.head.zRot = (float) (Math.PI / 180F) * entityarmorstand.getHeadPose().getZ();
            this.head.setPos(0.0F, 1.0F, 0.0F);
            this.body.xRot = (float) (Math.PI / 180F) * entityarmorstand.getBodyPose().getX();
            this.body.yRot = (float) (Math.PI / 180F) * entityarmorstand.getBodyPose().getY();
            this.body.zRot = (float) (Math.PI / 180F) * entityarmorstand.getBodyPose().getZ();
            this.leftArm.xRot = (float) (Math.PI / 180F) * entityarmorstand.getLeftArmPose().getX();
            this.leftArm.yRot = (float) (Math.PI / 180F) * entityarmorstand.getLeftArmPose().getY();
            this.leftArm.zRot = (float) (Math.PI / 180F) * entityarmorstand.getLeftArmPose().getZ();
            this.rightArm.xRot = (float) (Math.PI / 180F) * entityarmorstand.getRightArmPose().getX();
            this.rightArm.yRot = (float) (Math.PI / 180F) * entityarmorstand.getRightArmPose().getY();
            this.rightArm.zRot = (float) (Math.PI / 180F) * entityarmorstand.getRightArmPose().getZ();
            this.leftLeg.xRot = (float) (Math.PI / 180F) * entityarmorstand.getLeftLegPose().getX();
            this.leftLeg.yRot = (float) (Math.PI / 180F) * entityarmorstand.getLeftLegPose().getY();
            this.leftLeg.zRot = (float) (Math.PI / 180F) * entityarmorstand.getLeftLegPose().getZ();
            this.leftLeg.setPos(1.9F, 11.0F, 0.0F);
            this.rightLeg.xRot = (float) (Math.PI / 180F) * entityarmorstand.getRightLegPose().getX();
            this.rightLeg.yRot = (float) (Math.PI / 180F) * entityarmorstand.getRightLegPose().getY();
            this.rightLeg.zRot = (float) (Math.PI / 180F) * entityarmorstand.getRightLegPose().getZ();
            this.rightLeg.setPos(-1.9F, 11.0F, 0.0F);
            this.hat.copyFrom(this.head);
        } else {
            super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        }
    }
}
