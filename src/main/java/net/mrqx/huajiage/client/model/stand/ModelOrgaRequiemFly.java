package net.mrqx.huajiage.client.model.stand;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.player.LocalPlayer;
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
public class ModelOrgaRequiemFly extends ModelStandBase {
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart extra;

    public ModelOrgaRequiemFly(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.extra = root.getChild("extra");
    }

    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 22.0F, 0.0F, 1.4835F, 0.0F, -0.3491F));

        head.addOrReplaceChild("hair1", CubeListBuilder.create().texOffs(11, 0).mirror().addBox(-1.0F, -6.0F, -4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        head.addOrReplaceChild("hair2", CubeListBuilder.create().texOffs(33, 8).mirror().addBox(-4.0F, -8.0F, -4.2F, 7.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        head.addOrReplaceChild("hair3", CubeListBuilder.create().texOffs(9, 1).mirror().addBox(-1.0F, -8.6333F, -4.8333F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0744F, 0.0F, 0.0F));

        head.addOrReplaceChild("hair4", CubeListBuilder.create().texOffs(10, 1).mirror().addBox(0.2F, -8.6F, -4.8F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0744F, -0.0372F, -0.5205F));

        head.addOrReplaceChild("hair5", CubeListBuilder.create().texOffs(11, 4).mirror().addBox(-3.2667F, -8.0F, -5.2667F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1487F, 0.0372F, 0.632F));

        head.addOrReplaceChild("hair6", CubeListBuilder.create().texOffs(0, 51).mirror().addBox(-3.0F, -8.6F, -2.0667F, 6.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1859F, 0.0F, 0.0F));

        head.addOrReplaceChild("hair7", CubeListBuilder.create().texOffs(31, 51).mirror().addBox(-2.0667F, -8.5333F, -1.4F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1487F, 0.1487F, 0.5205F));

        head.addOrReplaceChild("hair8", CubeListBuilder.create().texOffs(31, 51).mirror().addBox(-0.8667F, -8.5333F, -1.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1859F, -0.2603F, -0.409F));

        head.addOrReplaceChild("hair9", CubeListBuilder.create().texOffs(0, 40).mirror().addBox(-2.0333F, -8.6667F, -0.4667F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3346F, 0.0F, 0.0F));

        head.addOrReplaceChild("hair10", CubeListBuilder.create().texOffs(31, 40).mirror().addBox(-1.0F, -8.9F, 1.5333F, 2.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5577F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-3.3572F, -0.0668F, -1.2369F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 22.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition leftArm = body.addOrReplaceChild("leftArm", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.8727F));

        PartDefinition lb_1 = leftArm.addOrReplaceChild("lb_1", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

        lb_1.addOrReplaceChild("lb_2", CubeListBuilder.create().texOffs(48, 34).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 5.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

        PartDefinition rightArm = body.addOrReplaceChild("rightArm", CubeListBuilder.create(), PartPose.offset(5.0F, 3.0F, 3.0F));

        PartDefinition rb_1 = rightArm.addOrReplaceChild("rb_1", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.5F, -3.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -3.0F, -2.0F, 3.1416F, -0.0873F, 1.0472F));

        rb_1.addOrReplaceChild("rb_2", CubeListBuilder.create().texOffs(48, 34).mirror().addBox(-1.5F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 2.0F, 0.0F, 0.6109F, -1.658F, 0.4363F));

        rb_1.addOrReplaceChild("rb_3", CubeListBuilder.create().texOffs(58, 27).mirror().addBox(-0.5F, 7.0F, 0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 2.0F, 0.0F, 0.6109F, -1.658F, 0.4363F));

        PartDefinition leftLeg = body.addOrReplaceChild("leftLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.9F, 11.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition ll_1 = leftLeg.addOrReplaceChild("ll_1", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 1.6581F, 1.0472F));

        ll_1.addOrReplaceChild("ll_2", CubeListBuilder.create().texOffs(48, 49).addBox(-2.1F, -5.5F, 1.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.5F, 0.0F, 0.5236F, -0.2618F, 0.0F));

        PartDefinition rightLeg = body.addOrReplaceChild("rightLeg", CubeListBuilder.create(), PartPose.offset(2.9F, 11.0F, 0.0F));

        PartDefinition rl_1 = rightLeg.addOrReplaceChild("rl_1", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        rl_1.addOrReplaceChild("rl_2", CubeListBuilder.create().texOffs(48, 49).mirror().addBox(-1.9F, -1.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

        PartDefinition extra = partdefinition.addOrReplaceChild("extra", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 22.0F, -14.0F, 1.5708F, 0.0F, 0.0F));

        extra.addOrReplaceChild("hair_p_1", CubeListBuilder.create().texOffs(41, 9).addBox(-2.5F, -3.0F, 0.0F, 5.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.5F, 1.0F, -11.0F, -0.9599F, 0.0873F, 0.0F));

        extra.addOrReplaceChild("hair_p_2", CubeListBuilder.create().texOffs(41, 9).addBox(-2.5F, -3.0F, 0.0F, 5.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 1.0F, -11.0F, -0.9599F, -0.3491F, 0.0F));

        extra.addOrReplaceChild("hair_p_3", CubeListBuilder.create().texOffs(41, 9).addBox(-2.5F, -3.0F, 0.0F, 5.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.5F, 2.0F, -7.0F, -0.9599F, -1.5708F, 0.0F));

        extra.addOrReplaceChild("hair_p_4", CubeListBuilder.create().texOffs(41, 9).addBox(-2.5F, -3.0F, 0.0F, 5.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.5F, 3.0F, 6.0F, -0.9599F, -2.618F, 0.0F));

        extra.addOrReplaceChild("hair_p_5", CubeListBuilder.create().texOffs(41, 9).addBox(-2.5F, -3.0F, 0.0F, 5.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 3.0F, 13.0F, -0.9599F, 2.1817F, 0.0F));

        extra.addOrReplaceChild("hair_p_6", CubeListBuilder.create().texOffs(41, 9).addBox(-2.5F, -3.0F, 0.0F, 5.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.5F, 3.0F, 9.0F, -0.9599F, 1.309F, 0.0F));

        extra.addOrReplaceChild("hair_p_7", CubeListBuilder.create().texOffs(41, 9).addBox(-2.5F, -3.0F, 0.0F, 5.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.5F, 3.0F, -3.0F, -0.9599F, 0.6109F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Entity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        float off = (float) Math.cos(0.1 * pAgeInTicks);

        this.resetPoses();

        head.y += off;
        body.y += off;
        extra.y += off;

        extra.zRot = pAgeInTicks / 2;
        pEntity.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
            Stand stand = Stand.getStand(data.getStand());
            if (stand != null && pEntity instanceof LivingEntity living) {
                extra.zRot = pAgeInTicks * stand.getSpeed(living, data) / 0.08F;
            }
        });
    }

    private void resetPoses() {
        head.resetPose();
        body.resetPose();
        extra.resetPose();
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        extra.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void renderHand(RenderHandEvent event, LocalPlayer player, Stand stand, IStandData data) {
        PoseStack poseStack = event.getPoseStack();
        VertexConsumer vertexConsumer = event.getMultiBufferSource().getBuffer(RenderType.entityTranslucentCull(stand.getStandResource().getModelTextures().get(data.getState())));
        float alpha = player.hasEffect(HuaJiEffects.STAND_POWER.get()) ? 0.6F : 0.3F;
        poseStack.mulPose(Axis.ZP.rotation(179.1F));
        poseStack.translate(0, 0.2, -2);
        poseStack.mulPose(Axis.XP.rotation(81.6F));
        this.resetPoses();
        extra.zRot = player.tickCount * stand.getSpeed(player, data) / 0.08F;
        head.render(poseStack, vertexConsumer, 0xF000F0, OverlayTexture.NO_OVERLAY, 1, 1, 1, alpha);
        body.render(poseStack, vertexConsumer, 0xF000F0, OverlayTexture.NO_OVERLAY, 1, 1, 1, alpha);
        extra.render(poseStack, vertexConsumer, 0xF000F0, OverlayTexture.NO_OVERLAY, 1, 1, 1, alpha);
    }
}
