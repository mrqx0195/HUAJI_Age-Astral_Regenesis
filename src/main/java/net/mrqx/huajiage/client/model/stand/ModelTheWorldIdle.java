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
public class ModelTheWorldIdle extends ModelStandBase {
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leftleg;
    private final ModelPart rightleg;
    private final ModelPart gears;
    private final ModelPart gear1;
    private final ModelPart gear2;

    public ModelTheWorldIdle(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.leftleg = root.getChild("leftleg");
        this.rightleg = root.getChild("rightleg");
        this.gears = root.getChild("gears");
        this.gear1 = this.gears.getChild("gear1");
        this.gear2 = this.gears.getChild("gear2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -6.0F, -4.0F, 7.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        head.addOrReplaceChild("Shape1", CubeListBuilder.create().texOffs(0, 35).addBox(-4.5F, -8.0F, -1.0F, 9.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4014F, 0.0F, 0.0F));

        head.addOrReplaceChild("Shape2", CubeListBuilder.create().texOffs(0, 49).addBox(-4.5F, -7.2F, -3.0F, 9.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0524F, 0.0F, 0.0F));

        PartDefinition glass = head.addOrReplaceChild("glass", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -0.5F));

        glass.addOrReplaceChild("Shape3", CubeListBuilder.create().texOffs(0, 63).addBox(2.8F, -7.0F, -3.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.0F, 1.0996F, 0.0F));

        glass.addOrReplaceChild("Shape4", CubeListBuilder.create().texOffs(0, 63).mirror().addBox(-5.8F, -7.0F, -3.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.0F, -1.0996F, 0.0F));

        head.addOrReplaceChild("Shape5", CubeListBuilder.create().texOffs(0, 75).addBox(-4.5F, -4.0F, 1.0F, 9.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1505F, 0.0F, 0.0F));

        head.addOrReplaceChild("Shape6", CubeListBuilder.create().texOffs(0, 30).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

        head.addOrReplaceChild("Shape7", CubeListBuilder.create().texOffs(0, 30).addBox(0.0F, -1.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1746F, -0.3491F, -0.3491F));

        body.addOrReplaceChild("bodydown", CubeListBuilder.create().texOffs(19, 66).addBox(-3.5F, 7.0F, -2.0F, 7.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("Shape8", CubeListBuilder.create().texOffs(37, 30).addBox(1.0F, -1.0F, -3.0F, 2.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("Shape9", CubeListBuilder.create().texOffs(37, 30).mirror().addBox(-3.0F, -1.0F, -3.0F, 2.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("Shape10", CubeListBuilder.create().texOffs(35, 49).addBox(-3.5F, 0.2F, -2.5F, 7.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("Shape11", CubeListBuilder.create().texOffs(35, 56).addBox(-1.5F, 4.2F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.3F, 0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("shape12", CubeListBuilder.create().texOffs(16, 82).addBox(-4.0F, 10.0F, -3.5F, 8.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("Shape13", CubeListBuilder.create().texOffs(0, 30).addBox(7.0F, 8.0F, -3.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.7854F));

        body.addOrReplaceChild("Shape14", CubeListBuilder.create().texOffs(0, 30).addBox(8.0F, 7.0F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.7854F));

        body.addOrReplaceChild("back1", CubeListBuilder.create().texOffs(0, 83).addBox(0.5F, 0.0F, 3.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("back2", CubeListBuilder.create().texOffs(0, 83).mirror().addBox(-2.5F, 0.0F, 3.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        PartDefinition leftarm = body.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(49, 27).addBox(-1.0F, 2.0F, -1.5F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, 0.6981F, 0.5236F, -0.5236F));

        leftarm.addOrReplaceChild("handl", CubeListBuilder.create().texOffs(48, 4).addBox(-3.2F, -1.0F, -4.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 7.0F, 2.0F, -1.2218F, 0.0F, 0.0F));

        PartDefinition rightarm = body.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-3.0F, -2.0F, -2.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(49, 27).mirror().addBox(-2.5F, 2.0F, -1.5F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, 2.0F, 0.0F, -1.2218F, 0.1745F, 1.0472F));

        rightarm.addOrReplaceChild("handr", CubeListBuilder.create().texOffs(48, 4).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 6.0F, 1.0F, -0.4364F, -0.2618F, 0.0873F));

        PartDefinition leftleg = partdefinition.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(21, 100).addBox(-2.0F, 0.0F, -3.0F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 11.0F, 5.0F, -0.6981F, -0.6981F, -0.8203F));

        leftleg.addOrReplaceChild("legdownl", CubeListBuilder.create().texOffs(0, 100).addBox(-2.2773F, -1.4722F, -3.5434F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1276F, 3.9224F, 0.6341F, 2.6086F, 0.0F, 0.1745F));

        PartDefinition rightleg = partdefinition.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(21, 100).mirror().addBox(-3.0F, 0.0F, -3.0F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 11.0F, 2.0F, 0.2618F, -0.2618F, -0.576F));

        rightleg.addOrReplaceChild("legdownr", CubeListBuilder.create().texOffs(0, 100).addBox(-2.5F, -1.5F, -1.5F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4521F, 7.6393F, 0.1241F, 0.427F, 0.0872F, 0.0F));

        partdefinition.addOrReplaceChild("right_hands", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 2.0F));

        PartDefinition gears = partdefinition.addOrReplaceChild("gears", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        gears.addOrReplaceChild("gear1", CubeListBuilder.create().texOffs(48, 79).addBox(-2.0F, -0.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(48, 88).addBox(-1.0F, -0.5F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(48, 88).addBox(-1.0F, -0.5F, -3.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(58, 87).addBox(-3.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(58, 87).addBox(2.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9073F, -21.3328F, 3.0076F, 0.0F, 0.0873F, -0.8727F));

        gears.addOrReplaceChild("gear2", CubeListBuilder.create().texOffs(48, 79).addBox(-2.0F, -0.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(48, 88).addBox(-1.0F, -0.5F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(48, 88).addBox(-1.0F, -0.5F, -3.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(58, 87).addBox(-3.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(58, 87).addBox(2.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.9703F, -25.7181F, 4.6108F, 0.0F, 0.0F, 0.8727F));

        return LayerDefinition.create(meshdefinition, 64, 128);
    }

    @Override
    public void setupAnim(@NotNull Entity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        float off = (float) Math.cos(0.1 * pAgeInTicks);

        head.resetPose();
        body.resetPose();
        leftleg.resetPose();
        rightleg.resetPose();
        gears.resetPose();

        head.y += off;
        body.y += off;
        leftleg.y += off;
        rightleg.y += off;
        gears.y += 20;
        gears.z -= 8;
        gears.xScale = gears.yScale = gears.zScale = 2;
        gear1.yRot = pAgeInTicks / 10;
        gear2.yRot = pAgeInTicks / 15;

        head.xRot = pHeadPitch * 0.017453292F;
        head.yRot = pNetHeadYaw * 0.017453292F;
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack pPoseStack, @NotNull VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        head.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        body.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        leftleg.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        rightleg.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }

    @Override
    public void renderExtra(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, LivingEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch, IStandData data, AbstractStand stand) {
        gears.render(pPoseStack, pBuffer.getBuffer(RenderType.entityTranslucentCull(stand.getModelTextures().get(data.getState()))), 0xF000F0, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1F);
    }
}
