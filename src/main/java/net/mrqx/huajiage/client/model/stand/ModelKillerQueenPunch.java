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
public class ModelKillerQueenPunch extends ModelStandBase {
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart leftleg;
    private final ModelPart rightleg;
    private final ModelPart hands_r;
    private final ModelPart r_hand1;
    private final ModelPart r_hand2;
    private final ModelPart r_hand3;
    private final ModelPart r_hand4;
    private final ModelPart r_hand5;
    private final ModelPart hands_l;
    private final ModelPart l_hand1;
    private final ModelPart l_hand2;
    private final ModelPart l_hand3;
    private final ModelPart l_hand4;
    private final ModelPart l_hand5;

    public ModelKillerQueenPunch(ModelPart root) {
        super();
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.leftleg = root.getChild("leftleg");
        this.rightleg = root.getChild("rightleg");
        this.hands_r = root.getChild("hands_r");
        this.r_hand1 = this.hands_r.getChild("r_hand1");
        this.r_hand2 = this.hands_r.getChild("r_hand2");
        this.r_hand3 = this.hands_r.getChild("r_hand3");
        this.r_hand4 = this.hands_r.getChild("r_hand4");
        this.r_hand5 = this.hands_r.getChild("r_hand5");
        this.hands_l = root.getChild("hands_l");
        this.l_hand1 = this.hands_l.getChild("l_hand1");
        this.l_hand2 = this.hands_l.getChild("l_hand2");
        this.l_hand3 = this.hands_l.getChild("l_hand3");
        this.l_hand4 = this.hands_l.getChild("l_hand4");
        this.l_hand5 = this.hands_l.getChild("l_hand5");
    }

    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 23).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

        body.addOrReplaceChild("Shape10", CubeListBuilder.create().texOffs(52, 36).addBox(-3.5F, 0.2F, -2.5F, 7.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("Shape11", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 4.0F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.3F, -0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("bodydown", CubeListBuilder.create().texOffs(16, 45).addBox(-3.5F, 7.0F, -2.0F, 7.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0175F, 0.0F, 0.0F));

        PartDefinition cloth = body.addOrReplaceChild("cloth", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        cloth.addOrReplaceChild("cloth1", CubeListBuilder.create().texOffs(0, 4).addBox(-1.0F, -2.5F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.2987F, -4.151F, -0.4978F, 0.0F, 0.0F));

        cloth.addOrReplaceChild("cloth2", CubeListBuilder.create().texOffs(42, 30).addBox(-4.5F, 0.0F, 1.0F, 9.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 10.9128F, 0.9962F, 0.6352F, 0.0F, 0.0F));

        cloth.addOrReplaceChild("cloth3", CubeListBuilder.create().texOffs(46, 49).addBox(-2.0F, -0.4128F, -4.0F, 1.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 10.9128F, 0.9962F, -0.063F, 0.0873F, 0.6109F));

        cloth.addOrReplaceChild("cloth4", CubeListBuilder.create().texOffs(32, 49).addBox(0.5F, 0.0F, -4.0F, 1.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 9.9128F, 0.9962F, -0.063F, -0.0873F, -0.7854F));

        cloth.addOrReplaceChild("cloth5", CubeListBuilder.create().texOffs(0, 57).addBox(-3.8146F, 0.0F, -4.0F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.8146F, 11.6044F, 0.3294F, -0.5866F, -0.0873F, -0.4363F));

        cloth.addOrReplaceChild("cloth6", CubeListBuilder.create().texOffs(40, 49).addBox(-0.5F, 0.0F, -4.0F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.6516F, 10.6473F, 0.8066F, -0.412F, 0.1746F, 0.4363F));

        cloth.addOrReplaceChild("crotch", CubeListBuilder.create().texOffs(0, 15).addBox(-4.0F, 10.0F, -3.5F, 8.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0349F, 0.0F, 0.0F));

        body.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(0, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

        body.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(47, 0).addBox(-2.0F, -2.0F, -2.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 2.0F, 0.5F, -0.6109F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -7.0F, -4.0F, 7.0F, 7.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(55, 55).addBox(2.5F, -9.0F, -3.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(22, 0).addBox(-2.5F, -8.0F, -3.5F, 5.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(54, 44).addBox(-3.5F, -9.0F, -3.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

        PartDefinition leftleg = partdefinition.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(37, 37).addBox(-2.0F, -2.0F, -3.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 11.0F, 3.0F, -0.2003F, -0.0502F, -0.2595F));

        leftleg.addOrReplaceChild("legdownl", CubeListBuilder.create().texOffs(34, 8).addBox(-1.6482F, -1.5F, -1.5F, 4.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1482F, 6.2009F, -0.5752F, 0.6109F, 0.0F, 0.0F));

        PartDefinition rightleg = partdefinition.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(0, 34).addBox(-3.0F, 0.0F, -3.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 10.0F, 4.0F, -0.538F, -0.0114F, 0.2482F));

        rightleg.addOrReplaceChild("legdownr", CubeListBuilder.create().texOffs(24, 27).addBox(-2.7077F, -1.5F, -7.5F, 4.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2077F, 3.6937F, 3.8524F, 0.7854F, 0.0F, 0.0F));

        PartDefinition hands_r = partdefinition.addOrReplaceChild("hands_r", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        hands_r.addOrReplaceChild("r_hand1", CubeListBuilder.create().texOffs(38, 23).addBox(-8.0F, 19.0F, -1.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 1.5708F, 0.0F));

        hands_r.addOrReplaceChild("r_hand2", CubeListBuilder.create().texOffs(38, 23).addBox(-3.0F, 12.0F, 5.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 1.5708F, 0.0F));

        hands_r.addOrReplaceChild("r_hand3", CubeListBuilder.create().texOffs(38, 23).addBox(0.0F, 12.0F, -8.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 1.5708F, 0.0F));

        hands_r.addOrReplaceChild("r_hand4", CubeListBuilder.create().texOffs(38, 23).addBox(4.0F, 10.0F, -8.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 6.0F, 7.0F, -1.5708F, 1.5708F, 0.0F));

        hands_r.addOrReplaceChild("r_hand5", CubeListBuilder.create().texOffs(38, 23).addBox(0.0F, 12.0F, -8.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 4.0F, -5.0F, -1.5708F, 1.5708F, 0.0F));

        PartDefinition hands_l = partdefinition.addOrReplaceChild("hands_l", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        hands_l.addOrReplaceChild("l_hand1", CubeListBuilder.create().texOffs(38, 23).mirror().addBox(-12.0F, 18.0F, -1.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, -1.5708F, 0.0F));

        hands_l.addOrReplaceChild("l_hand2", CubeListBuilder.create().texOffs(38, 23).mirror().addBox(-4.0F, 13.0F, 5.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, -1.5708F, 0.0F));

        hands_l.addOrReplaceChild("l_hand3", CubeListBuilder.create().texOffs(38, 23).mirror().addBox(0.0F, 13.0F, -8.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, -1.5708F, 0.0F));

        hands_l.addOrReplaceChild("l_hand4", CubeListBuilder.create().texOffs(38, 23).mirror().addBox(0.0F, 10.0F, -8.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 6.0F, 4.0F, -1.5708F, -1.5708F, 0.0F));

        hands_l.addOrReplaceChild("l_hand5", CubeListBuilder.create().texOffs(38, 23).mirror().addBox(0.0F, 13.0F, -8.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 3.0F, -10.0F, -1.5708F, -1.5708F, 0.0F));

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
        l_hand1.resetPose();
        l_hand2.resetPose();
        l_hand3.resetPose();
        l_hand4.resetPose();
        l_hand5.resetPose();
        r_hand1.resetPose();
        r_hand2.resetPose();
        r_hand3.resetPose();
        r_hand4.resetPose();
        r_hand5.resetPose();
        hands_l.resetPose();
        hands_r.resetPose();
    }

    protected void additionalAnim(float pAgeInTicks, float pNetHeadYaw, float pHeadPitch, float speed) {
        l_hand1.yRot = -45 + (float) (Math.cos(speed * pAgeInTicks) * 1.2 * 0.3) * 90;
        l_hand2.yRot = -45 + (float) (Math.cos(speed * pAgeInTicks + Math.PI / 3) * 1.4 * 0.3) * 90;
        l_hand3.yRot = -45 + (float) (Math.cos(speed * pAgeInTicks + 2 * Math.PI / 3) * 1.6 * 0.3) * 90;
        l_hand4.yRot = -45 + (float) (Math.cos(speed * pAgeInTicks + 2.5 * Math.PI / 3) * 1.3 * 0.3) * 90;
        l_hand5.yRot = -45 + (float) (Math.cos(speed * pAgeInTicks + 3 * Math.PI / 3) * 1.7 * 0.3) * 90;
        r_hand1.yRot = 45 - (float) (Math.cos(speed * pAgeInTicks + 3.5 * Math.PI / 3) * 1.2 * 0.3) * 90;
        r_hand2.yRot = 45 - (float) (Math.cos(speed * pAgeInTicks + 6 * Math.PI / 3) * 1.1 * 0.3) * 90;
        r_hand3.yRot = 45 - (float) (Math.cos(speed * pAgeInTicks + 8 * Math.PI / 3) * 1.4 * 0.3) * 90;
        r_hand4.yRot = 45 - (float) (Math.cos(speed * pAgeInTicks + 10 * Math.PI / 3) * 1.5 * 0.3) * 90;
        r_hand5.yRot = 45 - (float) (Math.cos(speed * pAgeInTicks + 1.5 * Math.PI / 3) * 1.6 * 0.3) * 90;

        hands_l.xRot = pHeadPitch * 0.017453292F;
        hands_l.yRot = pNetHeadYaw * 0.017453292F;
        hands_r.xRot = pHeadPitch * 0.017453292F;
        hands_r.yRot = pNetHeadYaw * 0.017453292F;

        float r = (float) Math.random();
        float offysin = (float) (Math.sin(r * speed * pAgeInTicks) * 0.3 - 0.25);
        float offycos = (float) (Math.cos(r * speed * pAgeInTicks) * 0.3 - 0.25);

        l_hand1.z += offysin;
        l_hand2.z += offycos;
        l_hand3.z += offysin;
        l_hand4.z += offycos;
        l_hand5.z += offysin;
        r_hand1.z += offycos;
        r_hand2.z += offysin;
        r_hand3.z += offycos;
        r_hand4.z += offysin;
        r_hand5.z += offycos;
        double v = r * Math.sin(r * speed * pAgeInTicks) * 0.3;
        float offxl = (float) (v + 0.2);
        float offxr = (float) (v - 0.2);
        hands_l.x += offxl;
        hands_r.x += offxr;
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        head.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        body.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        hands_l.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        hands_r.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        leftleg.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        rightleg.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }

    @Override
    public void renderExtra(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, LivingEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch, IStandData data, Stand stand) {
        pPoseStack.pushPose();
        pPoseStack.scale(1.3F, 1.3F, 1.3F);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityTranslucentCull(stand.getStandResource().getModelTextures().get(data.getState())));
        hands_l.render(pPoseStack, vertexconsumer, 0xF000F0, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.5F);
        hands_r.render(pPoseStack, vertexconsumer, 0xF000F0, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.5F);
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
        hands_l.render(poseStack, vertexConsumer, 0xF000F0, OverlayTexture.NO_OVERLAY, 1, 1, 1, alpha);
        hands_r.render(poseStack, vertexConsumer, 0xF000F0, OverlayTexture.NO_OVERLAY, 1, 1, 1, alpha);
    }
}
