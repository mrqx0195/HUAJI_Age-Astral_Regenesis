package net.mrqx.huajiage.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

/**
 * Made with Blockbench 4.12.6
 */
public class ModelSheerHeartAttack<T extends Entity> extends EntityModel<T> {
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart w_1;
    private final ModelPart w_2;

    public ModelSheerHeartAttack(ModelPart root) {
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.w_1 = root.getChild("w_1");
        this.w_2 = root.getChild("w_2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(34, 0).addBox(-3.5F, -5.0F, -2.0F, 5.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 61).addBox(-3.0F, -4.1F, -1.8F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 21.0F, -5.0F));

        PartDefinition ear = head.addOrReplaceChild("ear", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, -5.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

        ear.addOrReplaceChild("ear_1", CubeListBuilder.create().texOffs(58, 5).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 0.0F, 0.0F, -0.1745F, 0.4363F, -0.5236F));

        ear.addOrReplaceChild("ear_2", CubeListBuilder.create().texOffs(58, 5).mirror().addBox(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.5F, 0.0F, 0.0F, -0.1745F, -0.4363F, 0.5236F));

        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -11.0F, -5.0F, 10.0F, 9.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(36, 27).addBox(-2.0F, -12.0F, 0.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(17, 42).addBox(-8.0F, -6.0F, -3.0F, 3.0F, 4.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(37, 12).addBox(5.0F, -6.0F, -3.0F, 3.0F, 4.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(18, 25).addBox(4.0F, -8.0F, -2.0F, 3.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 23).addBox(-7.0F, -8.0F, -2.0F, 3.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        partdefinition.addOrReplaceChild("w_1", CubeListBuilder.create().texOffs(37, 37).addBox(-2.0F, -3.0F, -4.0F, 3.0F, 5.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(-1.5F, -3.5F, -4.5F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 22.0F, 0.0F));

        partdefinition.addOrReplaceChild("w_2", CubeListBuilder.create().texOffs(0, 37).addBox(-1.0F, -3.0F, -4.0F, 3.0F, 5.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.5F, -3.5F, -4.5F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 22.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        head.xRot = headPitch * 0.017453292F;
        head.yRot = netHeadYaw * 0.017453292F;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        w_1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        w_2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
