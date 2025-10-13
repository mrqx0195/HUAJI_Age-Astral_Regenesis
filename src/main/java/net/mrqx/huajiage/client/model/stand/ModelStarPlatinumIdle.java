package net.mrqx.huajiage.client.model.stand;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.mrqx.huajiage.capability.stand.IStandData;
import net.mrqx.huajiage.stand.Stand;

/**
 * Made with Blockbench 4.12.6
 */
@SuppressWarnings("SpellCheckingInspection")
public class ModelStarPlatinumIdle extends ModelStandBase {
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart leftleg;
    private final ModelPart rightleg;
    private final ModelPart hands;

    public ModelStarPlatinumIdle(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.leftleg = root.getChild("leftleg");
        this.rightleg = root.getChild("rightleg");
        this.hands = root.getChild("hands");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        body.addOrReplaceChild("Shape11", CubeListBuilder.create().texOffs(35, 56).addBox(-1.5F, 4.0F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.3F, -0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("bodydown", CubeListBuilder.create().texOffs(19, 66).addBox(-3.5F, 7.0F, -2.0F, 7.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0175F, 0.0F, 0.0F));

        body.addOrReplaceChild("cloth1", CubeListBuilder.create().texOffs(48, 67).addBox(-2.0F, -1.2338F, 0.4723F, 4.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.8157F, -4.3943F, -0.0614F, 0.0F, 0.0F));

        body.addOrReplaceChild("cloth2", CubeListBuilder.create().texOffs(48, 56).addBox(-2.5F, 0.0F, 1.0F, 5.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 10.9128F, 0.9962F, 0.4606F, 0.0F, 0.0F));

        body.addOrReplaceChild("crotch", CubeListBuilder.create().texOffs(16, 82).addBox(-4.0F, 10.0F, -3.5F, 8.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0349F, 0.0F, 0.0F));

        PartDefinition leftarm = body.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(46, 103).addBox(-1.0F, 2.0F, -1.5F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 3.0F, 0.0F, 0.2618F, -0.0873F, -0.4363F));

        leftarm.addOrReplaceChild("handl", CubeListBuilder.create().texOffs(48, 4).addBox(-2.0F, -2.0F, -4.4052F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2142F, 7.7366F, 0.4486F, -1.7453F, -0.2618F, 0.6981F));

        leftarm.addOrReplaceChild("armorl", CubeListBuilder.create().texOffs(0, 74).addBox(-2.0F, -0.5F, -3.0F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -2.5F, 0.9F));

        PartDefinition rightarm = body.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-3.0F, -2.0F, -2.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(46, 103).mirror().addBox(-2.5F, 2.0F, -1.5F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, 3.0F, 0.0F, 0.1745F, 0.4363F, 0.5236F));

        rightarm.addOrReplaceChild("handr", CubeListBuilder.create().texOffs(48, 4).mirror().addBox(-2.0F, -3.0864F, -1.6742F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.3226F, 5.0458F, 0.1748F, -1.4835F, 0.0873F, -0.5236F));

        rightarm.addOrReplaceChild("armorr", CubeListBuilder.create().texOffs(0, 74).mirror().addBox(-9.0F, -1.0F, -3.1F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, -2.0F, 1.0F));

        body.addOrReplaceChild("scarf", CubeListBuilder.create().texOffs(24, 35).addBox(-5.0F, -1.0F, -4.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5817F, 0.0F, 0.0F));

        body.addOrReplaceChild("scarf2", CubeListBuilder.create().texOffs(28, 35).addBox(-4.5F, -1.0F, -4.5F, 9.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2471F, 0.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -6.0F, -4.0F, 7.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        head.addOrReplaceChild("hair1", CubeListBuilder.create().texOffs(0, 35).addBox(-4.0F, -8.0F, 0.5F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6093F, -0.1487F, 0.0349F));

        head.addOrReplaceChild("hair2", CubeListBuilder.create().texOffs(0, 49).addBox(-3.9F, -8.3F, -2.5F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1267F, 0.0F, 0.0F));

        head.addOrReplaceChild("hair3", CubeListBuilder.create().texOffs(0, 35).addBox(-2.0F, -9.0F, 0.0F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5729F, 0.0F, 0.0F));

        head.addOrReplaceChild("hair4", CubeListBuilder.create().texOffs(0, 49).addBox(0.2F, -8.0F, 0.0F, 3.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 0.2269F, 0.2094F));

        head.addOrReplaceChild("hair5", CubeListBuilder.create().texOffs(0, 49).addBox(-3.2F, -8.0F, 0.0F, 2.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, -0.2269F, -0.2094F));

        head.addOrReplaceChild("hair6", CubeListBuilder.create().texOffs(0, 35).addBox(1.0F, -8.0F, 0.5F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6109F, 0.1396F, -0.0349F));

        head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 118).addBox(-4.0F, -6.5F, -4.5F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leftleg = partdefinition.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(21, 100).addBox(-2.0F, 0.0F, -3.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 12.0F, 1.0F, -0.0258F, -0.1374F, -0.1723F));

        leftleg.addOrReplaceChild("legdownl", CubeListBuilder.create().texOffs(0, 100).addBox(-2.5F, -0.5F, -2.5F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4313F, 5.9635F, -0.0644F, 0.5236F, 0.0F, 0.0F));

        PartDefinition rightleg = partdefinition.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(21, 100).mirror().addBox(-3.0F, 0.0F, -2.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 12.0F, 0.0F, -0.1017F, 0.1631F, 0.1609F));

        rightleg.addOrReplaceChild("legdownr", CubeListBuilder.create().texOffs(0, 100).mirror().addBox(-4.1154F, -1.9128F, -2.5F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.6154F, 6.7528F, 1.5678F, 0.9506F, 0.1601F, 0.1487F));

        partdefinition.addOrReplaceChild("hands", CubeListBuilder.create().texOffs(48, 4).mirror().addBox(-8.3226F, -19.0405F, -1.4994F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(48, 4).addBox(18.3226F, -12.0405F, -1.4994F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(48, 4).mirror().addBox(-13.3226F, -13.0405F, 6.5006F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(48, 4).addBox(24.3226F, -20.0405F, 4.5006F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(48, 4).mirror().addBox(-8.3226F, -19.0405F, 11.5006F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(48, 4).addBox(18.3226F, -17.0405F, 11.5006F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -4.0F, -8.0F, -1.5708F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 128);
    }

    @Override
    public void setupAnim(Entity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        float off = (float) Math.cos(0.1 * pAgeInTicks);

        this.resetPoses();

        head.y += off;
        body.y += off;
        leftleg.y += off;
        rightleg.y += off;
        hands.y += off;

        head.xRot = pHeadPitch * 0.017453292F;
        head.yRot = pNetHeadYaw * 0.017453292F;
    }

    private void resetPoses() {
        head.resetPose();
        body.resetPose();
        leftleg.resetPose();
        rightleg.resetPose();
        hands.resetPose();
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftleg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightleg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        hands.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void renderExtra(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, LivingEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch, IStandData data, Stand stand) {
        float off = (float) (Math.cos(0.15 * pAgeInTicks)) + 1;
        pPoseStack.pushPose();
        pPoseStack.scale(6F / 5, 6F / 5, 6F / 5);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityTranslucentCull(stand.getStandResource().getModelTextures().get(data.getState())));
        hands.render(pPoseStack, vertexconsumer, 0xF000F0, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.5F * off);
        pPoseStack.popPose();
    }
}
