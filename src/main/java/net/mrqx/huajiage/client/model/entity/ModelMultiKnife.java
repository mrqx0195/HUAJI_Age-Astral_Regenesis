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
public class ModelMultiKnife<T extends Entity> extends EntityModel<T> {
    private final ModelPart Shape1;
    private final ModelPart Shape2;
    private final ModelPart Shape3;
    private final ModelPart Shape4;
    private final ModelPart Shape5;
    private final ModelPart Shape6;
    private final ModelPart Shape7;
    private final ModelPart Shape8;

    public ModelMultiKnife(ModelPart root) {
        this.Shape1 = root.getChild("Shape1");
        this.Shape2 = root.getChild("Shape2");
        this.Shape3 = root.getChild("Shape3");
        this.Shape4 = root.getChild("Shape4");
        this.Shape5 = root.getChild("Shape5");
        this.Shape6 = root.getChild("Shape6");
        this.Shape7 = root.getChild("Shape7");
        this.Shape8 = root.getChild("Shape8");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("Shape1", CubeListBuilder.create().texOffs(0, 27).mirror().addBox(-4.6F, 0.0F, -3.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, -1, 0.0F, 0.8179F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("Shape2", CubeListBuilder.create().texOffs(0, 27).addBox(-0.3F, 0.0F, -3.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1, 0.0F, 0.8179F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("Shape3", CubeListBuilder.create().texOffs(0, 17).mirror().addBox(0.0F, 0.0F, 0.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, -1.6667F, -1.6F));

        partdefinition.addOrReplaceChild("Shape4", CubeListBuilder.create().texOffs(12, 23).mirror().addBox(-1.0F, 0.0F, 0.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0, -1.0F));

        partdefinition.addOrReplaceChild("Shape5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0.0F, 0.0F, 1.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -13.1334F, -1.5F));

        partdefinition.addOrReplaceChild("Shape6", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, 0.0F, -1.0F, 1.0F, 11.0F, 3.0F, new CubeDeformation(-0.001F)).mirror(false), PartPose.offsetAndRotation(-0.5F, -10, -2.5F, 0.1487F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("Shape7", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -0.7667F, 0.3F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0005F)).mirror(false), PartPose.offsetAndRotation(-0.5F, -13.6F, -2.0F, -0.4461F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("Shape8", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -0.1333F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.5F, -14.6667F, -0.8667F, -0.8179F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Shape1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        Shape2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        Shape3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        Shape4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        Shape5.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        Shape6.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        Shape7.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        Shape8.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
