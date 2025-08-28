package net.mrqx.huajiage.client.model.stand;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

/**
 * Made with Blockbench 4.12.6
 */
@SuppressWarnings("SpellCheckingInspection")
public class ModelHierophantGreen extends ModelStandBase {
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leftarm;
    private final ModelPart rightarm;
    private final ModelPart leftleg;
    private final ModelPart rightleg;
    private final ModelPart extra;

    public ModelHierophantGreen(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.leftarm = root.getChild("leftarm");
        this.rightarm = root.getChild("rightarm");
        this.leftleg = root.getChild("leftleg");
        this.rightleg = root.getChild("rightleg");
        this.extra = root.getChild("extra");
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

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.1745F, 0.0F));

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

        PartDefinition leftarm = partdefinition.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(40, 16).addBox(3.0F, 1.0872F, -1.9962F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.0F, 0.0F, -0.0873F, 0.0F, -0.9599F));

        leftarm.addOrReplaceChild("leftarmd", CubeListBuilder.create().texOffs(48, 4).addBox(-2.0F, -3.5F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6753F, 5.2814F, -3.4392F, 2.0071F, 3.0543F, -1.6581F));

        PartDefinition rightarm = partdefinition.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-4.1335F, 1.8434F, 1.2756F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, -0.7854F, 0.0F, 1.309F));

        rightarm.addOrReplaceChild("rightarmd", CubeListBuilder.create().texOffs(48, 4).mirror().addBox(-2.3983F, -2.8966F, -1.1802F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.6845F, 7.7366F, 2.1856F, -2.0944F, 0.2618F, -0.2618F));

        PartDefinition leftleg = partdefinition.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(21, 100).addBox(-2.0F, 0.0F, -3.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 10.0F, 0.0F, -0.5494F, -0.3992F, -0.4341F));

        leftleg.addOrReplaceChild("legdownl", CubeListBuilder.create().texOffs(0, 100).addBox(-4.5885F, -4.6434F, -2.9332F, 4.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.8266F, 3.8994F, 3.2559F, 1.8088F, -0.1374F, -0.4105F));

        PartDefinition rightleg = partdefinition.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(21, 100).mirror().addBox(-3.0F, 0.0F, -3.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 10.0F, 2.0F, -0.2762F, 0.2504F, 0.1609F));

        rightleg.addOrReplaceChild("legdownr", CubeListBuilder.create().texOffs(0, 100).mirror().addBox(-1.5F, -4.5F, -2.5F, 4.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.9457F, 10.0163F, 1.7692F, 0.6015F, 0.0728F, 0.0614F));

        partdefinition.addOrReplaceChild("extra", CubeListBuilder.create().texOffs(0, 36).addBox(-5.2887F, -1.5893F, -1.4862F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 44).addBox(-4.7113F, -0.4107F, -0.5139F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.6176F, 4.3949F, -4.7861F, 0.4363F, 0.0F, 0.349F));

        return LayerDefinition.create(meshdefinition, 64, 128);
    }

    @Override
    public void setupAnim(@NotNull Entity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        float off = (float) Math.cos(0.1 * pAgeInTicks);

        this.resetPoses();

        head.y += off;
        body.y += off;
        leftleg.y += off;
        rightleg.y += off;
        leftarm.y += off;
        rightarm.y += off;
        extra.y += off;

        head.xRot = pHeadPitch * 0.017453292F;
        head.yRot = pNetHeadYaw * 0.017453292F;

        extra.xRot = pAgeInTicks * 2;
    }

    private void resetPoses() {
        head.resetPose();
        body.resetPose();
        leftleg.resetPose();
        rightleg.resetPose();
        leftarm.resetPose();
        rightarm.resetPose();
        extra.resetPose();
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack pPoseStack, @NotNull VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        head.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        body.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        leftarm.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        rightarm.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        leftleg.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        rightleg.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        extra.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }
}
