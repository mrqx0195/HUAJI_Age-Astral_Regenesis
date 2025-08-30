package net.mrqx.huajiage.client.model.stand;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.mrqx.huajiage.capability.stand.StandDataCapabilityProvider;
import net.mrqx.huajiage.stand.Stand;

/**
 * Made with Blockbench 4.12.6
 */
public class ModelOrgaRequiem extends ModelStandBase {
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart extra;
    private final ModelPart hair_p_1;
    private final ModelPart hair_p_2;
    private final ModelPart hair_p_3;
    private final ModelPart hair_p_4;
    private final ModelPart hair_p_5;
    private final ModelPart hair_p_6;
    private final ModelPart hair_p_7;

    public ModelOrgaRequiem(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.leftArm = root.getChild("leftArm");
        this.rightArm = root.getChild("rightArm");
        this.leftLeg = root.getChild("leftLeg");
        this.rightLeg = root.getChild("rightLeg");
        this.extra = root.getChild("extra");
        this.hair_p_1 = this.extra.getChild("hair_p_1");
        this.hair_p_2 = this.extra.getChild("hair_p_2");
        this.hair_p_3 = this.extra.getChild("hair_p_3");
        this.hair_p_4 = this.extra.getChild("hair_p_4");
        this.hair_p_5 = this.extra.getChild("hair_p_5");
        this.hair_p_6 = this.extra.getChild("hair_p_6");
        this.hair_p_7 = this.extra.getChild("hair_p_7");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        head.addOrReplaceChild("hair1", CubeListBuilder.create().texOffs(11, 0).mirror().addBox(-1.0F, -6.0F, -4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        head.addOrReplaceChild("hair2", CubeListBuilder.create().texOffs(33, 8).mirror().addBox(-4.0F, -8.0F, -4.2F, 7.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        head.addOrReplaceChild("hair3", CubeListBuilder.create().texOffs(9, 1).mirror().addBox(-1.0F, -8.6333F, -4.8333F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0744F, 0.0F, 0.0F));

        head.addOrReplaceChild("hair4", CubeListBuilder.create().texOffs(10, 1).mirror().addBox(0.2F, -8.6F, -4.8F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0744F, -0.0372F, -0.5205F));

        head.addOrReplaceChild("hair5", CubeListBuilder.create().texOffs(11, 4).mirror().addBox(-3.2667F, -8.0F, -5.2667F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1487F, 0.0372F, 0.632F));

        head.addOrReplaceChild("hair6", CubeListBuilder.create().texOffs(0, 51).mirror().addBox(-3.0F, -8.6F, -2.0667F, 6.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1859F, 0.0F, 0.0F));

        head.addOrReplaceChild("hair7", CubeListBuilder.create().texOffs(31, 51).mirror().addBox(-2.0667F, -8.5333F, -1.4F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1487F, 0.1487F, 0.5205F));

        head.addOrReplaceChild("hair8", CubeListBuilder.create().texOffs(31, 51).mirror().addBox(-0.8667F, -8.5333F, -1.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1859F, -0.2603F, -0.409F));

        head.addOrReplaceChild("hair9", CubeListBuilder.create().texOffs(0, 40).mirror().addBox(-2.0333F, -8.6667F, -0.4667F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3346F, 0.0F, 0.0F));

        head.addOrReplaceChild("hair10", CubeListBuilder.create().texOffs(31, 40).mirror().addBox(-1.0F, -8.9F, 1.5333F, 2.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5577F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-3.3572F, -0.0668F, -1.2369F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, -0.6981F, 0.0F));

        PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.0F, 2.0F, -1.0F, 0.0F, 0.3491F, 0.5236F));

        leftArm.addOrReplaceChild("lb_1", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        leftArm.addOrReplaceChild("lb_2", CubeListBuilder.create().texOffs(48, 34).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-1.0F, 5.0F, 0.0F, 0.0F, 0.0F, -1.309F));

        PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm", CubeListBuilder.create(), PartPose.offset(5.0F, 3.0F, 3.0F));

        rightArm.addOrReplaceChild("rb_1", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -3.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.1745F, 0.0F));

        rightArm.addOrReplaceChild("rb_2", CubeListBuilder.create().texOffs(48, 34).mirror().addBox(-1.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, -0.1745F, -0.4363F, 0.0F));

        PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.9F, 11.0F, 0.0F, -0.6109F, -0.4363F, 0.0F));

        leftLeg.addOrReplaceChild("ll_1", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6109F, 0.0F, 0.0F));

        leftLeg.addOrReplaceChild("ll_2", CubeListBuilder.create().texOffs(48, 49).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.5F, -2.0F, 0.6981F, 0.0873F, 0.0F));

        PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(1.9F, 12.0F, 2.0F, 0.0873F, -1.1344F, 0.0F));

        rightLeg.addOrReplaceChild("rl_1", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        rightLeg.addOrReplaceChild("rl_2", CubeListBuilder.create().texOffs(48, 49).mirror().addBox(-2.0F, -2.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, 0.0F, 0.1745F, 0.0F));

        PartDefinition extra = partdefinition.addOrReplaceChild("extra", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, 0.0F, 0.0F, -0.8727F));

        extra.addOrReplaceChild("hair_p_1", CubeListBuilder.create().texOffs(41, 9).addBox(-2.5F, -3.0F, 0.0F, 5.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.5F, 1.0F, -11.0F, -0.9599F, 0.0873F, 0.0F));

        extra.addOrReplaceChild("hair_p_2", CubeListBuilder.create().texOffs(41, 9).addBox(-2.5F, -3.0F, 0.0F, 5.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 1.0F, -11.0F, -0.9599F, -0.3491F, 0.0F));

        extra.addOrReplaceChild("hair_p_3", CubeListBuilder.create().texOffs(41, 9).addBox(-2.5F, -3.0F, 0.0F, 5.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.5F, 2.0F, -7.0F, -0.9599F, -1.5708F, 0.0F));

        extra.addOrReplaceChild("hair_p_4", CubeListBuilder.create().texOffs(41, 9).addBox(-2.5F, -3.0F, 0.0F, 5.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.5F, 3.0F, 6.0F, -0.9599F, -2.618F, 0.0F));

        extra.addOrReplaceChild("hair_p_5", CubeListBuilder.create().texOffs(41, 9).addBox(-2.5F, -3.0F, 0.0F, 5.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 3.0F, 13.0F, -0.9599F, 2.1817F, 0.0F));

        extra.addOrReplaceChild("hair_p_6", CubeListBuilder.create().texOffs(41, 9).addBox(-2.5F, -3.0F, 0.0F, 5.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.5F, 3.0F, 9.0F, -0.9599F, 1.309F, 0.0F));

        extra.addOrReplaceChild("hair_p_7", CubeListBuilder.create().texOffs(41, 9).addBox(-2.5F, -3.0F, 0.0F, 5.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.5F, 3.0F, -3.0F, -0.9599F, 0.6109F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Entity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        float off = (float) Math.cos(0.1 * pAgeInTicks);

        this.resetPoses();

        head.y += off;
        leftArm.y += (float) (off * 0.8);
        rightArm.y += (float) (off * 0.8);
        body.y += off;
        leftLeg.y += off;
        rightLeg.y += off;
        hair_p_1.y += (float) (Math.sin(0.05 * pAgeInTicks) * 0.25);
        hair_p_2.y += (float) (Math.sin(0.05 * pAgeInTicks + 1 * Math.PI / 3) * 0.25);
        hair_p_3.y += (float) (Math.sin(0.05 * pAgeInTicks + 2 * Math.PI / 3) * 0.25);
        hair_p_4.y += (float) (Math.sin(0.05 * pAgeInTicks + 3 * Math.PI / 3) * 0.25);
        hair_p_5.y += (float) (Math.sin(0.05 * pAgeInTicks + 4 * Math.PI / 3) * 0.25);
        hair_p_6.y += (float) (Math.sin(0.05 * pAgeInTicks + 5 * Math.PI / 3) * 0.25);
        hair_p_7.y += (float) (Math.sin(0.05 * pAgeInTicks + 6 * Math.PI / 3) * 0.25);

        extra.yRot = pAgeInTicks / 2;

        head.xRot = pHeadPitch * 0.017453292F;
        head.yRot = pNetHeadYaw * 0.017453292F;

        pEntity.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
            Stand stand = Stand.getStand(data.getStand());
            if (stand != null && pEntity instanceof LivingEntity living) {
                extra.yRot = pAgeInTicks * stand.getSpeed(living, data) / 0.02F;
            }
        });
    }

    private void resetPoses() {
        head.resetPose();
        body.resetPose();
        leftArm.resetPose();
        rightArm.resetPose();
        body.resetPose();
        leftLeg.resetPose();
        rightLeg.resetPose();
        hair_p_1.resetPose();
        hair_p_2.resetPose();
        hair_p_3.resetPose();
        hair_p_4.resetPose();
        hair_p_5.resetPose();
        hair_p_6.resetPose();
        hair_p_7.resetPose();
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        extra.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
