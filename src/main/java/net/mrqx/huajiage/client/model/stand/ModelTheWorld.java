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
import net.mrqx.huajiage.stand.AbstractStand;
import org.jetbrains.annotations.NotNull;

/**
 * Made with Blockbench 4.12.6
 */
@SuppressWarnings("SpellCheckingInspection")
public class ModelTheWorld extends ModelStandBase {
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leftleg;
    private final ModelPart rightleg;
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

    public ModelTheWorld(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.leftleg = root.getChild("leftleg");
        this.rightleg = root.getChild("rightleg");
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
    }

    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
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

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("bodydown", CubeListBuilder.create().texOffs(19, 66).addBox(-3.5F, 7.0F, -2.0F, 7.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("Shape8", CubeListBuilder.create().texOffs(37, 30).addBox(1.0F, -1.0F, -3.0F, 2.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("Shape9", CubeListBuilder.create().texOffs(37, 30).mirror().addBox(-3.0F, -1.0F, -3.0F, 2.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("Shape10", CubeListBuilder.create().texOffs(35, 49).addBox(-3.5F, 0.2F, -2.5F, 7.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("Shape11", CubeListBuilder.create().texOffs(35, 56).addBox(-1.5F, 4.2F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.3F, 0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("Shape12", CubeListBuilder.create().texOffs(16, 82).addBox(-4.0F, 10.0F, -3.5F, 8.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("Shape13", CubeListBuilder.create().texOffs(0, 30).addBox(7.0F, 8.0F, -3.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.7854F));

        body.addOrReplaceChild("Shape14", CubeListBuilder.create().texOffs(0, 30).addBox(8.0F, 7.0F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.7854F));

        body.addOrReplaceChild("back1", CubeListBuilder.create().texOffs(0, 83).addBox(0.5F, 0.0F, 3.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("back2", CubeListBuilder.create().texOffs(0, 83).mirror().addBox(-2.5F, 0.0F, 3.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-3.0F, -2.0F, -2.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, 2.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        body.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition leftleg = partdefinition.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(21, 100).addBox(-2.0F, 0.0F, -3.0F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 11.0F, 0.0F, -0.6981F, -0.4363F, -0.4712F));

        leftleg.addOrReplaceChild("legdownl", CubeListBuilder.create().texOffs(0, 100).addBox(-2.2773F, -3.4722F, -4.5434F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1276F, 4.9224F, 4.6341F, 1.2124F, 0.0F, 0.2618F));

        PartDefinition rightleg = partdefinition.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(21, 100).mirror().addBox(-3.0F, 0.0F, -3.0F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 11.0F, 0.0F, -0.6981F, 0.4363F, 0.4712F));

        rightleg.addOrReplaceChild("legdownr", CubeListBuilder.create().texOffs(0, 100).addBox(-2.5F, -4.5F, -2.5F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0181F, 7.2866F, 3.8689F, 1.2124F, -0.0873F, -0.3491F));

        PartDefinition left_hands = partdefinition.addOrReplaceChild("left_hands", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 2.0F));

        left_hands.addOrReplaceChild("handl1", CubeListBuilder.create().texOffs(48, 4).addBox(4.0F, 1.0F, -11.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, 2.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        left_hands.addOrReplaceChild("handl2", CubeListBuilder.create().texOffs(48, 4).addBox(14.0F, 8.0F, -6.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        left_hands.addOrReplaceChild("handl3", CubeListBuilder.create().texOffs(48, 4).addBox(8.0F, -6.0F, -5.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        left_hands.addOrReplaceChild("handl4", CubeListBuilder.create().texOffs(48, 4).addBox(10.0F, 5.0F, 1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        left_hands.addOrReplaceChild("handl5", CubeListBuilder.create().texOffs(48, 4).addBox(12.0F, 1.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        PartDefinition right_hands = partdefinition.addOrReplaceChild("right_hands", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 2.0F));

        right_hands.addOrReplaceChild("handr1", CubeListBuilder.create().texOffs(48, 4).mirror().addBox(-19.0F, 3.0F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 2.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        right_hands.addOrReplaceChild("handr2", CubeListBuilder.create().texOffs(48, 4).mirror().addBox(-13.0F, -1.0F, -9.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        right_hands.addOrReplaceChild("handr3", CubeListBuilder.create().texOffs(48, 4).mirror().addBox(-13.0F, -6.0F, -3.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        right_hands.addOrReplaceChild("handr4", CubeListBuilder.create().texOffs(48, 4).mirror().addBox(-19.0F, 8.0F, -3.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        right_hands.addOrReplaceChild("handr5", CubeListBuilder.create().texOffs(48, 4).mirror().addBox(-17.0F, 3.0F, -6.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

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

        head.xRot = pHeadPitch * 0.017453292F;
        head.yRot = pNetHeadYaw * 0.017453292F;

        pEntity.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
            AbstractStand stand = AbstractStand.getStand(data.getStand());
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
        leftleg.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        rightleg.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        left_hands.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        right_hands.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }

    @Override
    public void renderExtra(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, LivingEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch, IStandData data, AbstractStand stand) {
        pPoseStack.pushPose();
        pPoseStack.scale(1.3F, 1.3F, 1.3F);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityTranslucentCull(stand.getModelTextures().get(data.getState())));
        left_hands.render(pPoseStack, vertexconsumer, 0xF000F0, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.5F);
        right_hands.render(pPoseStack, vertexconsumer, 0xF000F0, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.5F);
        pPoseStack.popPose();
    }

    @Override
    public void renderHand(RenderHandEvent event, LocalPlayer player, AbstractStand stand, IStandData data) {
        PoseStack poseStack = event.getPoseStack();
        VertexConsumer vertexConsumer = event.getMultiBufferSource().getBuffer(RenderType.entityTranslucentCull(stand.getModelTextures().get(data.getState())));
        poseStack.translate(0, -1, -0.75);
        float alpha = player.hasEffect(HuaJiEffects.STAND_POWER.get()) ? 0.6F : 0.3F;
        float speed = stand.getSpeed(player, data);
        this.resetPoses();
        additionalAnim(player.tickCount, 0, -1, speed * 4 / 3);
        left_hands.render(poseStack, vertexConsumer, 0xF000F0, OverlayTexture.NO_OVERLAY, 1, 1, 1, alpha);
        right_hands.render(poseStack, vertexConsumer, 0xF000F0, OverlayTexture.NO_OVERLAY, 1, 1, 1, alpha);
    }
}
