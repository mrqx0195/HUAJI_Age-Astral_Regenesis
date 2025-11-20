package net.mrqx.huajiage.client.model.stand;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.client.event.RenderHandEvent;
import net.mrqx.huajiage.capability.stand.IStandData;
import net.mrqx.huajiage.capability.stand.StandDataCapabilityProvider;
import net.mrqx.huajiage.registy.HuaJiEffects;
import net.mrqx.huajiage.stand.Stand;

/**
 * Made with Blockbench 4.12.6
 */
@SuppressWarnings({"SpellCheckingInspection", "DuplicatedCode"})
public class ModelStarPlatinum extends ModelStandBase {
    private final ModelPart body;
    private final ModelPart left_hands;
    private final ModelPart handl1;
    private final ModelPart handl2;
    private final ModelPart handl3;
    private final ModelPart handl4;
    private final ModelPart handl5;
    private final ModelPart right_hands;
    private final ModelPart handr1;
    private final ModelPart handr2;
    private final ModelPart handr3;
    private final ModelPart handr4;
    private final ModelPart handr5;
    private final ModelPart head;
    private final ModelPart leftleg;
    private final ModelPart rightleg;

    public ModelStarPlatinum(ModelPart root) {
        super();
        this.body = root.getChild("body");
        this.left_hands = root.getChild("left_hands");
        this.handl1 = this.left_hands.getChild("handl1");
        this.handl2 = this.left_hands.getChild("handl2");
        this.handl3 = this.left_hands.getChild("handl3");
        this.handl4 = this.left_hands.getChild("handl4");
        this.handl5 = this.left_hands.getChild("handl5");
        this.right_hands = root.getChild("right_hands");
        this.handr1 = this.right_hands.getChild("handr1");
        this.handr2 = this.right_hands.getChild("handr2");
        this.handr3 = this.right_hands.getChild("handr3");
        this.handr4 = this.right_hands.getChild("handr4");
        this.handr5 = this.right_hands.getChild("handr5");
        this.head = root.getChild("head");
        this.leftleg = root.getChild("leftleg");
        this.rightleg = root.getChild("rightleg");
    }

    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("Shape10", CubeListBuilder.create().texOffs(35, 49).addBox(-3.5F, 0.2F, -2.5F, 7.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("Shape11", CubeListBuilder.create().texOffs(35, 56).addBox(-1.5F, 4.0F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.3F, -0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("armorl", CubeListBuilder.create().texOffs(0, 74).addBox(5.0F, -1.0F, -3.0F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        body.addOrReplaceChild("armorr", CubeListBuilder.create().texOffs(0, 74).mirror().addBox(-9.0F, -1.0F, -3.0F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        body.addOrReplaceChild("bodydown", CubeListBuilder.create().texOffs(19, 66).addBox(-3.5F, 7.0F, -2.0F, 7.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0175F, 0.0F, 0.0F));

        body.addOrReplaceChild("cloth1", CubeListBuilder.create().texOffs(48, 67).addBox(-2.0F, 11.0F, -1.9F, 4.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1487F, 0.0F, 0.0F));

        body.addOrReplaceChild("cloth2", CubeListBuilder.create().texOffs(48, 56).addBox(-2.5F, 0.0F, 1.0F, 5.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 10.9128F, 0.9962F, 0.2861F, 0.0F, 0.0F));

        body.addOrReplaceChild("crotch", CubeListBuilder.create().texOffs(16, 82).addBox(-4.0F, 10.0F, -3.5F, 8.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0349F, 0.0F, 0.0F));

        body.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        body.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-3.0F, -2.0F, -2.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, 2.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        body.addOrReplaceChild("scarf", CubeListBuilder.create().texOffs(24, 35).addBox(-5.0F, -1.0F, -4.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4072F, 0.0F, 0.0F));

        body.addOrReplaceChild("scarf2", CubeListBuilder.create().texOffs(28, 35).addBox(-4.5F, -1.0F, -4.5F, 9.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0726F, 0.0F, 0.0F));

        PartDefinition left_hands = partdefinition.addOrReplaceChild("left_hands", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        left_hands.addOrReplaceChild("handl1", CubeListBuilder.create().texOffs(48, 4).addBox(8.0F, 10.0F, 3.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        left_hands.addOrReplaceChild("handl2", CubeListBuilder.create().texOffs(48, 4).addBox(9.0F, 3.0F, 0.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        left_hands.addOrReplaceChild("handl3", CubeListBuilder.create().texOffs(48, 4).addBox(7.0F, -5.0F, 4.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        left_hands.addOrReplaceChild("handl4", CubeListBuilder.create().texOffs(48, 4).addBox(15.0F, -11.0F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        left_hands.addOrReplaceChild("handl5", CubeListBuilder.create().texOffs(48, 4).addBox(11.0F, -3.0F, -4.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        PartDefinition right_hands = partdefinition.addOrReplaceChild("right_hands", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        right_hands.addOrReplaceChild("handr1", CubeListBuilder.create().texOffs(48, 4).mirror().addBox(-14.0F, 11.0F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        right_hands.addOrReplaceChild("handr2", CubeListBuilder.create().texOffs(48, 4).mirror().addBox(-14.0F, -5.0F, -4.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        right_hands.addOrReplaceChild("handr3", CubeListBuilder.create().texOffs(48, 4).mirror().addBox(-17.0F, 8.0F, 1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        right_hands.addOrReplaceChild("handr4", CubeListBuilder.create().texOffs(48, 4).mirror().addBox(-20.0F, -10.0F, 2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        right_hands.addOrReplaceChild("handr5", CubeListBuilder.create().texOffs(48, 4).mirror().addBox(-13.0F, 2.0F, 6.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -6.0F, -4.0F, 7.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        head.addOrReplaceChild("hair1", CubeListBuilder.create().texOffs(0, 35).addBox(-4.0F, -8.0F, 0.5F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6093F, -0.1487F, 0.0349F));

        head.addOrReplaceChild("hair2", CubeListBuilder.create().texOffs(0, 49).addBox(-3.9F, -8.3F, -2.5F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1267F, 0.0F, 0.0F));

        head.addOrReplaceChild("hair3", CubeListBuilder.create().texOffs(0, 35).addBox(-2.0F, -9.0F, 0.0F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5729F, 0.0F, 0.0F));

        head.addOrReplaceChild("hair4", CubeListBuilder.create().texOffs(0, 49).addBox(0.2F, -8.0F, 0.0F, 3.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 0.2269F, 0.2094F));

        head.addOrReplaceChild("hair5", CubeListBuilder.create().texOffs(0, 49).addBox(-3.2F, -8.0F, 0.0F, 2.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, -0.2269F, -0.2094F));

        head.addOrReplaceChild("hair6", CubeListBuilder.create().texOffs(0, 35).addBox(1.0F, -8.0F, 0.5F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6109F, 0.1396F, -0.0349F));

        head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 118).addBox(-4.0F, -6.5F, -4.5F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leftleg = partdefinition.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(21, 100).addBox(-2.0F, 0.0F, -3.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 11.0F, 0.0F, -0.5494F, -0.3992F, -0.4341F));

        leftleg.addOrReplaceChild("legdownl", CubeListBuilder.create().texOffs(0, 100).addBox(-0.5F, 3.5F, -7.0F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8371F, -0.75F, -1.3178F, 1.1107F, -0.1374F, 0.2004F));

        PartDefinition rightleg = partdefinition.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(21, 100).mirror().addBox(-3.0F, 0.0F, -3.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 11.0F, 0.0F, -0.3635F, 0.2504F, 0.2482F));

        rightleg.addOrReplaceChild("legdownr", CubeListBuilder.create().texOffs(0, 100).mirror().addBox(-2.5F, 5.5F, -6.0F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.6913F, -0.6595F, 1.0427F, 0.427F, 0.0728F, 0.0614F));

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

        head.xRot = pHeadPitch * 0.017453292F;
        head.yRot = pNetHeadYaw * 0.017453292F;

        pEntity.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
            Stand stand = Stand.getStand(data.getStand());
            if (stand != null && pEntity instanceof LivingEntity living) {
                float speed = stand.getSpeed(living, data);
                additionalAnim(pAgeInTicks, pNetHeadYaw, pHeadPitch, speed * 4 / 3);
            }
        });
    }

    private void resetPoses() {
        head.resetPose();
        body.resetPose();
        leftleg.resetPose();
        rightleg.resetPose();
        handl1.resetPose();
        handl2.resetPose();
        handl3.resetPose();
        handl4.resetPose();
        handl5.resetPose();
        handr1.resetPose();
        handr2.resetPose();
        handr3.resetPose();
        handr4.resetPose();
        handr5.resetPose();
        left_hands.resetPose();
        right_hands.resetPose();
    }

    protected void additionalAnim(float pAgeInTicks, float pNetHeadYaw, float pHeadPitch, float speed) {
        handl1.yRot = (float) (Math.cos(speed * pAgeInTicks) * 1.2 * 0.3) * 90;
        handl2.yRot = (float) (Math.cos(speed * pAgeInTicks + Math.PI / 3) * 1.4 * 0.3) * 90;
        handl3.yRot = (float) (Math.cos(speed * pAgeInTicks + 2 * Math.PI / 3) * 1.6 * 0.3) * 90;
        handl4.yRot = (float) (Math.cos(speed * pAgeInTicks + 2.5 * Math.PI / 3) * 1.3 * 0.3) * 90;
        handl5.yRot = (float) (Math.cos(speed * pAgeInTicks + 3 * Math.PI / 3) * 1.7 * 0.3) * 90;
        handr1.yRot = (float) (Math.cos(speed * pAgeInTicks + 3.5 * Math.PI / 3) * 1.2 * 0.3) * 90;
        handr2.yRot = (float) (Math.cos(speed * pAgeInTicks + 6 * Math.PI / 3) * 1.1 * 0.3) * 90;
        handr3.yRot = (float) (Math.cos(speed * pAgeInTicks + 8 * Math.PI / 3) * 1.4 * 0.3) * 90;
        handr4.yRot = (float) (Math.cos(speed * pAgeInTicks + 10 * Math.PI / 3) * 1.5 * 0.3) * 90;
        handr5.yRot = (float) (Math.cos(speed * pAgeInTicks + 1.5 * Math.PI / 3) * 1.6 * 0.3) * 90;

        left_hands.xRot = pHeadPitch * 0.017453292F;
        left_hands.yRot = pNetHeadYaw * 0.017453292F;
        right_hands.xRot = pHeadPitch * 0.017453292F;
        right_hands.yRot = pNetHeadYaw * 0.017453292F;

        float r = (float) Math.random();
        float offysin = (float) (Math.sin(r * speed * pAgeInTicks) * 0.3 - 0.25);
        float offycos = (float) (Math.cos(r * speed * pAgeInTicks) * 0.3 - 0.25);

        handl1.z += offysin;
        handl2.z += offycos;
        handl3.z += offysin;
        handl4.z += offycos;
        handl5.z += offysin;
        handr1.z += offycos;
        handr2.z += offysin;
        handr3.z += offycos;
        handr4.z += offysin;
        handr5.z += offycos;
        double v = r * Math.sin(r * speed * pAgeInTicks) * 0.3;
        float offxl = (float) (v + 0.2);
        float offxr = (float) (v - 0.2);
        left_hands.x += offxl;
        right_hands.x += offxr;
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        head.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        body.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        left_hands.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        right_hands.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        leftleg.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        rightleg.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }

    @Override
    public void renderExtra(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, LivingEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch, IStandData data, Stand stand) {
        pPoseStack.pushPose();
        pPoseStack.scale(1.3F, 1.3F, 1.3F);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityTranslucentCull(stand.getStandResource().getModelTextures().get(data.getState())));
        left_hands.render(pPoseStack, vertexconsumer, 0xF000F0, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.5F);
        right_hands.render(pPoseStack, vertexconsumer, 0xF000F0, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.5F);
        pPoseStack.popPose();
    }

    @Override
    public void renderHand(RenderHandEvent event, LocalPlayer player, Stand stand, IStandData data) {
        PoseStack poseStack = event.getPoseStack();
        VertexConsumer vertexConsumer = event.getMultiBufferSource().getBuffer(RenderType.entityTranslucentCull(stand.getStandResource().getModelTextures().get(data.getState())));
        poseStack.translate(0, -1, -0.75);
        float alpha = player.hasEffect(HuaJiEffects.STAND_POWER.get()) ? 0.6F : 0.3F;
        float speed = stand.getSpeed(player, data);
        this.resetPoses();
        additionalAnim(player.tickCount, 0, -1, speed * 4 / 3);
        left_hands.render(poseStack, vertexConsumer, 0xF000F0, OverlayTexture.NO_OVERLAY, 1, 1, 1, alpha);
        right_hands.render(poseStack, vertexConsumer, 0xF000F0, OverlayTexture.NO_OVERLAY, 1, 1, 1, alpha);
    }
}
