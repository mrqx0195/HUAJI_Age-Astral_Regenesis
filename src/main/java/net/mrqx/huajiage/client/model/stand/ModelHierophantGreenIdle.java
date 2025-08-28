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
import net.mrqx.huajiage.stand.AbstractStand;
import org.jetbrains.annotations.NotNull;

/**
 * Made with Blockbench 4.12.6
 */
@SuppressWarnings("SpellCheckingInspection")
public class ModelHierophantGreenIdle extends ModelStandBase {
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart strips;
    private final ModelPart strip2;
    private final ModelPart strip4;
    private final ModelPart strip6;
    private final ModelPart strip8;
    private final ModelPart strip10;

    public ModelHierophantGreenIdle(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.strips = root.getChild("strips");
        ModelPart strip1 = this.strips.getChild("strip1");
        this.strip2 = strip1.getChild("strip2");
        ModelPart strip3 = this.strip2.getChild("strip3");
        this.strip4 = strip3.getChild("strip4");
        ModelPart strip5 = this.strip4.getChild("strip5");
        this.strip6 = strip5.getChild("strip6");
        ModelPart strip7 = this.strip6.getChild("strip7");
        this.strip8 = strip7.getChild("strip8");
        ModelPart strip9 = this.strip8.getChild("strip9");
        this.strip10 = strip9.getChild("strip10");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -5.8F, -2.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));

        PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(35, 114).addBox(-3.5F, -30.0F, -3.5F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(28, 0).addBox(-4.0F, -28.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(28, 0).mirror().addBox(3.0F, -28.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(28, 5).addBox(-1.5F, -30.5F, -2.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 1.0F));

        hat.addOrReplaceChild("hat_part_3", CubeListBuilder.create().texOffs(54, 107).addBox(-3.5F, 0.5F, 1.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -27.0F, -5.0F, -0.1745F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.3963F, 0.0F, 0.2618F));

        body.addOrReplaceChild("Shape1", CubeListBuilder.create().texOffs(35, 56).addBox(-1.5F, 4.0F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.3F));

        body.addOrReplaceChild("Shape2", CubeListBuilder.create().texOffs(54, 30).addBox(-1.5F, 4.0F, 0.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1307F, -2.2291F, -3.4244F, -0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("bodydown", CubeListBuilder.create().texOffs(19, 66).addBox(-3.5F, 6.0246F, -1.6873F, 7.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0175F, 0.0F, 0.0F));

        body.addOrReplaceChild("crotch", CubeListBuilder.create().texOffs(16, 82).addBox(-4.0F, 7.5F, -2.8764F, 8.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0349F, 0.0F, 0.0F));

        PartDefinition part1 = body.addOrReplaceChild("part1", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -0.5208F, -2.9544F, 0.0F, 0.0F, -0.7854F));

        part1.addOrReplaceChild("part1_1", CubeListBuilder.create().texOffs(48, 54).addBox(-0.5F, -3.0F, -3.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.9035F, -2.0107F, 2.8264F, 0.0F, 0.0F, -0.3491F));

        part1.addOrReplaceChild("part1_2", CubeListBuilder.create().texOffs(48, 54).addBox(-0.5F, -3.0F, -3.0F, 1.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2936F, -2.0295F, 2.5669F, 0.0F, 0.0F, -0.6109F));

        part1.addOrReplaceChild("part1_4", CubeListBuilder.create().texOffs(48, 69).addBox(-2.2784F, -2.1619F, -2.5456F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2071F, -2.7071F, 3.0F, 0.0F, 0.0F, -0.2618F));

        PartDefinition part2 = body.addOrReplaceChild("part2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -0.5208F, -2.9544F, 0.0F, 0.0F, 0.7854F));

        part2.addOrReplaceChild("part1_3", CubeListBuilder.create().texOffs(48, 54).addBox(-0.5F, -3.0F, -3.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.9035F, -2.0107F, 2.8264F, 0.0F, 0.0F, 0.3491F));

        part2.addOrReplaceChild("part1_5", CubeListBuilder.create().texOffs(48, 54).addBox(-0.5F, -3.0F, -3.0F, 1.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.2936F, -2.0295F, 2.5669F, 0.0F, 0.0F, 0.6109F));

        part2.addOrReplaceChild("part1_6", CubeListBuilder.create().texOffs(48, 69).addBox(1.2784F, -2.1619F, -2.5456F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.2071F, -2.7071F, 3.0F, 0.0F, 0.0F, 0.2618F));

        body.addOrReplaceChild("part3", CubeListBuilder.create().texOffs(35, 62).addBox(-3.5F, -2.0F, 4.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0872F, 4.8422F, -1.8075F));

        body.addOrReplaceChild("part4", CubeListBuilder.create().texOffs(48, 79).addBox(-2.5F, -1.0F, -3.5473F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9052F, 4.7968F, 2.3473F, 0.0F, 0.0F, -0.5236F));

        body.addOrReplaceChild("part5", CubeListBuilder.create().texOffs(48, 79).mirror().addBox(-1.5F, -1.0F, -3.5473F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.9052F, 4.7968F, 2.3473F, 0.0F, 0.0F, 0.5236F));

        PartDefinition part6 = body.addOrReplaceChild("part6", CubeListBuilder.create().texOffs(0, 64).addBox(-2.0F, 2.9696F, 1.6528F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.9696F, 0.3472F));

        part6.addOrReplaceChild("part6_1", CubeListBuilder.create().texOffs(0, 74).mirror().addBox(2.0F, -2.0F, 0.0F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.1129F, 4.4536F, -3.5367F, 0.0F, 0.0F, -0.7854F));

        part6.addOrReplaceChild("part6_2", CubeListBuilder.create().texOffs(0, 74).mirror().addBox(-3.0F, -2.0F, 0.0F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.1129F, 4.4536F, -3.5367F, 0.0F, 0.0F, 0.7854F));

        PartDefinition rightarm = body.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(40, 16).addBox(-2.9332F, -0.5783F, -1.8622F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0667F, 4.2177F, 1.4699F, 0.0F, -0.7854F, -1.6581F));

        rightarm.addOrReplaceChild("rightarmd", CubeListBuilder.create().texOffs(48, 4).addBox(-2.5F, 0.0F, -1.5F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0667F, 5.0783F, -0.1378F, 0.0F, -0.4363F, 0.2618F));

        PartDefinition leftarm = body.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0668F, -0.5783F, -1.8622F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0667F, 4.2177F, 1.4699F, 0.0F, 1.3963F, 1.6581F));

        leftarm.addOrReplaceChild("leftarmd", CubeListBuilder.create().texOffs(48, 4).mirror().addBox(-1.5F, 0.0F, -1.5F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0667F, 5.0783F, -0.1378F, 0.0F, 0.6981F, -0.3491F));

        PartDefinition leftleg = body.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(21, 100).mirror().addBox(-3.0F, 0.0F, -3.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 10.0F, 1.0F, 0.0F, 0.0F, -0.0873F));

        leftleg.addOrReplaceChild("legdownl", CubeListBuilder.create().texOffs(0, 100).mirror().addBox(-2.0F, -0.5F, -2.5F, 4.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 7.0F, -0.5F));

        PartDefinition rightleg = body.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(21, 100).addBox(-2.0F, 0.0F, -3.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 10.0F, 1.0F, -1.309F, 0.2618F, 0.0F));

        rightleg.addOrReplaceChild("legdownr", CubeListBuilder.create().texOffs(0, 100).addBox(-2.2F, -0.5F, -2.5F, 4.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, -0.5F, 2.1817F, 0.0F, 0.0F));

        PartDefinition strips = partdefinition.addOrReplaceChild("strips", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.0F, 5.0F, 25.0F, 0.0873F, 0.0F, 0.0F));

        PartDefinition strip1 = strips.addOrReplaceChild("strip1", CubeListBuilder.create().texOffs(44, 36).addBox(-3.0F, -3.0F, -1.0F, 3.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 1.0F, 1.0F));

        PartDefinition strip2 = strip1.addOrReplaceChild("strip2", CubeListBuilder.create().texOffs(2, 48).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -2.0F, 7.0F, 0.3491F, 0.5236F, -0.1745F));

        PartDefinition strip3 = strip2.addOrReplaceChild("strip3", CubeListBuilder.create().texOffs(2, 48).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 11.0F, 0.3491F, 0.8727F, 0.6109F));

        PartDefinition strip4 = strip3.addOrReplaceChild("strip4", CubeListBuilder.create().texOffs(2, 48).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3493F, -0.2779F, 10.1046F, 0.3491F, 1.4835F, 0.6981F));

        PartDefinition strip5 = strip4.addOrReplaceChild("strip5", CubeListBuilder.create().texOffs(2, 48).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1632F, 1.1236F, 11.0628F, 0.3491F, 1.4835F, 0.6981F));

        PartDefinition strip6 = strip5.addOrReplaceChild("strip6", CubeListBuilder.create().texOffs(2, 48).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.8064F, 0.502F, 11.423F, 0.3491F, 1.4835F, 0.6981F));

        PartDefinition strip7 = strip6.addOrReplaceChild("strip7", CubeListBuilder.create().texOffs(2, 48).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3493F, -0.2217F, 10.7624F, 0.3491F, -1.309F, 0.6981F));

        PartDefinition strip8 = strip7.addOrReplaceChild("strip8", CubeListBuilder.create().texOffs(2, 48).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2159F, 0.0822F, 11.4868F, -0.4363F, -0.6109F, 0.6981F));

        PartDefinition strip9 = strip8.addOrReplaceChild("strip9", CubeListBuilder.create().texOffs(2, 48).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5709F, 0.8538F, 10.9519F, -0.4363F, -0.6109F, 0.6981F));

        PartDefinition strip10 = strip9.addOrReplaceChild("strip10", CubeListBuilder.create().texOffs(2, 48).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2116F, -0.0739F, 11.2227F, -0.6981F, -0.7854F, -0.3491F));

        strip10.addOrReplaceChild("strip11", CubeListBuilder.create().texOffs(2, 48).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2968F, 1.3778F, 10.9551F, -0.6981F, -1.7453F, -2.0944F));

        return LayerDefinition.create(meshdefinition, 64, 128);
    }

    @Override
    public void setupAnim(@NotNull Entity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        float off = (float) Math.cos(0.1 * pAgeInTicks);

        this.resetPoses();

        head.y += off;
        body.y += off;
        strips.y += off;
        strip2.y += off * 0.5F;
        strip4.y -= off * 0.5F;
        strip6.y += off * 0.5F;
        strip8.y -= off * 0.5F;
        strip10.y += off * 0.5F;

        head.xRot = pHeadPitch * 0.017453292F;
        head.yRot = pNetHeadYaw * 0.017453292F;
    }

    private void resetPoses() {
        head.resetPose();
        body.resetPose();
        strips.resetPose();
        strip2.resetPose();
        strip4.resetPose();
        strip6.resetPose();
        strip8.resetPose();
        strip10.resetPose();
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack pPoseStack, @NotNull VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        head.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        body.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }

    @Override
    public void renderExtra(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, LivingEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch, IStandData data, AbstractStand stand) {
        strips.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucentCull(stand.getModelTextures().get(data.getState()))), 0xF000F0, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1F);
    }
}
