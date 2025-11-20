package net.mrqx.huajiage.client.model.stand;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

/**
 * Made with Blockbench 4.12.6
 */
@SuppressWarnings({"SpellCheckingInspection", "DuplicatedCode"})
public class ModelKillerQueen extends ModelStandBase {
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart leftleg;
    private final ModelPart rightleg;

    public ModelKillerQueen(ModelPart root) {
        super();
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.leftleg = root.getChild("leftleg");
        this.rightleg = root.getChild("rightleg");
    }

    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 23).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, -0.1746F, 0.0F, 0.0F));

        body.addOrReplaceChild("Shape10", CubeListBuilder.create().texOffs(52, 36).addBox(-3.5F, 0.2F, -2.5F, 7.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("Shape11", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 4.0F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.3F, -0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("bodydown", CubeListBuilder.create().texOffs(16, 45).addBox(-3.5F, 7.0F, -2.0F, 7.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0175F, 0.0F, 0.0F));

        PartDefinition cloth = body.addOrReplaceChild("cloth", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        cloth.addOrReplaceChild("cloth1", CubeListBuilder.create().texOffs(0, 4).addBox(-1.0F, 11.9723F, -1.6662F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1487F, 0.0F, 0.0F));

        cloth.addOrReplaceChild("cloth2", CubeListBuilder.create().texOffs(42, 30).addBox(-4.5F, 0.0F, 1.0F, 9.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.9128F, 0.9962F, 0.5479F, 0.0F, 0.0F));

        cloth.addOrReplaceChild("cloth3", CubeListBuilder.create().texOffs(46, 49).addBox(-2.0F, -0.4128F, -4.0F, 1.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 10.9128F, 0.9962F, -0.063F, 0.0873F, 1.1345F));

        cloth.addOrReplaceChild("cloth4", CubeListBuilder.create().texOffs(32, 49).addBox(0.5F, 0.0F, -4.0F, 1.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 9.9128F, 0.9962F, -0.063F, -0.0873F, -0.7854F));

        cloth.addOrReplaceChild("cloth5", CubeListBuilder.create().texOffs(0, 57).addBox(-3.5F, 0.0F, -4.0F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 9.9128F, 0.9962F, -0.2375F, -0.0873F, -0.4363F));

        cloth.addOrReplaceChild("cloth6", CubeListBuilder.create().texOffs(40, 49).addBox(-1.5F, 0.0F, -4.0F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 9.9128F, 0.9962F, -0.2375F, 0.0873F, 0.6981F));

        cloth.addOrReplaceChild("crotch", CubeListBuilder.create().texOffs(0, 15).addBox(-4.0F, 10.0F, -3.5F, 8.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0349F, 0.0F, 0.0F));

        PartDefinition leftarm = body.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(0, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

        PartDefinition la_up = leftarm.addOrReplaceChild("la_up", CubeListBuilder.create().texOffs(14, 53).addBox(0.0F, 1.0F, -0.5F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6109F, 0.6109F, 0.2618F));

        la_up.addOrReplaceChild("la_down", CubeListBuilder.create().texOffs(20, 15).addBox(0.0F, -4.0F, -6.5F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, 1.5F, 0.1745F, 0.0873F, 0.6981F));

        PartDefinition rightarm = body.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(47, 0).addBox(-2.0F, -2.0F, -2.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 2.0F, 0.5F, -0.6109F, 0.0F, 0.0F));

        PartDefinition ra_up = rightarm.addOrReplaceChild("ra_up", CubeListBuilder.create().texOffs(52, 9).addBox(-2.0F, 1.0F, -1.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.0F, -0.5F, -0.2618F, -0.2618F, 0.1745F));

        ra_up.addOrReplaceChild("ra_down", CubeListBuilder.create().texOffs(38, 23).addBox(-1.0F, -3.0F, 0.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 5.0F, -1.0F, -0.9599F, -0.0873F, 0.0873F));

        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -7.0F, -4.0F, 7.0F, 7.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(55, 55).addBox(2.5F, -9.0F, -3.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(22, 0).addBox(-2.5F, -8.0F, -3.5F, 5.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(54, 44).addBox(-3.5F, -9.0F, -3.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

        PartDefinition leftleg = partdefinition.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(37, 37).addBox(-2.0F, 0.0F, -3.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 12.0F, -1.0F, -0.2876F, -0.3992F, -0.4341F));

        leftleg.addOrReplaceChild("legdownl", CubeListBuilder.create().texOffs(34, 8).addBox(-2.6629F, -4.5F, -2.1822F, 4.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1629F, 10.25F, 1.1822F, 0.5871F, -0.0501F, 0.0259F));

        PartDefinition rightleg = partdefinition.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(0, 34).addBox(-3.0F, 1.0F, -3.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 10.0F, 0.0F, -0.3635F, 0.3377F, 0.2482F));

        rightleg.addOrReplaceChild("legdownr", CubeListBuilder.create().texOffs(24, 27).addBox(-1.6913F, -4.3405F, -2.5427F, 4.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6913F, 9.3405F, 1.5427F, 0.9506F, 0.2473F, 0.0614F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(Entity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        float off = (float) Math.cos(0.1 * pAgeInTicks);

        this.resetPoses();

        head.y += off;
        body.y += off;
        leftleg.y += off;
        rightleg.y += off;

        head.xRot = pHeadPitch * 0.017453292F;
        head.yRot = pNetHeadYaw * 0.017453292F;
    }

    private void resetPoses() {
        head.resetPose();
        body.resetPose();
        leftleg.resetPose();
        rightleg.resetPose();
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftleg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightleg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
