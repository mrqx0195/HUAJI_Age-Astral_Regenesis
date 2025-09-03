package net.mrqx.huajiage.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrqx.huajiage.item.equipment.armor.ItemFiftyFiftyHelmet;
import net.mrqx.huajiage.mixin.AccessorModelPart;

@OnlyIn(Dist.CLIENT)
public class ModelFiftyFiftyHelmet extends HuaJiArmorModel {
    private final ItemStack itemStack;

    public ModelFiftyFiftyHelmet(ModelPart root, ItemStack itemStack) {
        super(root);
        this.itemStack = itemStack;
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        pPoseStack.pushPose();
        if (this.young) {
            float f = 1.5F / 2.0F;
            pPoseStack.scale(f, f, f);
            pPoseStack.translate(0.0F, 1.0f, 0.0F);
        }
        this.renderHelmet(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        pPoseStack.popPose();
    }

    private void renderHelmet(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        if (this.head.visible) {
            AccessorModelPart accessorModelPart = ((AccessorModelPart) (Object) this.head);
            if (!accessorModelPart.getCubes().isEmpty() || !accessorModelPart.getChildren().isEmpty()) {
                pPoseStack.pushPose();
                this.head.translateAndRotate(pPoseStack);
                if (!this.head.skipDraw) {
                    accessorModelPart.huaJiAgeInvokeCompile(pPoseStack.last(), pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
                }
                accessorModelPart.getChildren().forEach(((s, modelPart) -> {
                    if ("eyes".equals(s) || "tubes_b".equals(s)) {
                        if (ItemFiftyFiftyHelmet.Mode.getMode(itemStack).equals(ItemFiftyFiftyHelmet.Mode.ON)) {
                            modelPart.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
                        }
                    } else {
                        modelPart.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
                    }
                }));
                pPoseStack.popPose();
            }
        }
    }

    public static MeshDefinition createBodyLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(deformation, 0);
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-5.0F, -9.0F, -5.0F, 10, 4, 10, new CubeDeformation(0.0F)),
                PartPose.ZERO);

        head.addOrReplaceChild("base2",
                CubeListBuilder.create()
                        .texOffs(12, 5)
                        .addBox(-4.0F, -5.0F, 4.0F, 8, 3, 1, new CubeDeformation(0.0F)),
                PartPose.ZERO);

        head.addOrReplaceChild("base3",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-5.0F, -5.0F, 0.0F, 1, 5, 5, new CubeDeformation(0.0F)),
                PartPose.ZERO);

        head.addOrReplaceChild("base4",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(4.0F, -5.0F, 0.0F, 1, 5, 5, new CubeDeformation(0.0F)),
                PartPose.ZERO);

        head.addOrReplaceChild("p1",
                CubeListBuilder.create()
                        .texOffs(14, 28)
                        .addBox(4.4667F, -6.0F, 0.0F, 2, 2, 2, new CubeDeformation(0.0F)),
                PartPose.ZERO);

        head.addOrReplaceChild("p2",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-6.0F, -6.4667F, -6.0F, 1, 5, 6, new CubeDeformation(0.0F)),
                PartPose.ZERO);

        head.addOrReplaceChild("p3",
                CubeListBuilder.create()
                        .texOffs(0, 21)
                        .addBox(-5.0F, -7.0F, -6.0F, 10, 7, 1, new CubeDeformation(0.0F)),
                PartPose.ZERO);

        head.addOrReplaceChild("p4",
                CubeListBuilder.create()
                        .texOffs(0, 28)
                        .addBox(-6.4667F, -6.0F, 0.0F, 2, 2, 2, new CubeDeformation(0.0F)),
                PartPose.ZERO);

        head.addOrReplaceChild("p5",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(5.0F, -6.4667F, -6.0F, 1, 5, 6, new CubeDeformation(0.0F)),
                PartPose.ZERO);

        head.addOrReplaceChild("p6",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4.0F, -4.0F, -7.0F, 8, 1, 1, new CubeDeformation(0.0F)),
                PartPose.ZERO);

        head.addOrReplaceChild("p7",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4.0F, -6.0F, -7.0F, 8, 1, 1, new CubeDeformation(0.0F)),
                PartPose.ZERO);

        head.addOrReplaceChild("p8",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4.0F, -5.0F, -7.0F, 1, 1, 1, new CubeDeformation(0.0F)),
                PartPose.ZERO);

        head.addOrReplaceChild("p9",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(3.0F, -5.0F, -7.0F, 1, 1, 1, new CubeDeformation(0.0F)),
                PartPose.ZERO);

        head.addOrReplaceChild("eyes",
                CubeListBuilder.create()
                        .texOffs(25, 30)
                        .addBox(-3.5F, -5.5F, -6.2F, 7, 2, 0, new CubeDeformation(0.0F)),
                PartPose.ZERO);

        head.addOrReplaceChild("tubes_b",
                CubeListBuilder.create()
                        .texOffs(0, 37)
                        .addBox(-6.5F, -6.0F, -6.5F, 1, 1, 4, new CubeDeformation(0.0F))
                        .addBox(-6.5F, -4.0F, -6.5F, 1, 1, 4, new CubeDeformation(0.0F))
                        .addBox(5.5F, -6.0F, -6.5F, 1, 1, 4, new CubeDeformation(0.0F))
                        .addBox(5.5F, -4.0F, -6.5F, 1, 1, 4, new CubeDeformation(0.0F)),
                PartPose.ZERO);

        head.addOrReplaceChild("tubes_y",
                CubeListBuilder.create()
                        .texOffs(0, 43)
                        .addBox(4.5F, -6.0F, -1.0F, 1, 1, 8, new CubeDeformation(0.0F))
                        .texOffs(20, 43)
                        .addBox(4.5F, -7.0F, -1.0F, 1, 1, 8, new CubeDeformation(0.0F))
                        .texOffs(0, 43)
                        .addBox(-5.5F, -6.0F, -1.0F, 1, 1, 8, new CubeDeformation(0.0F))
                        .texOffs(20, 43)
                        .addBox(-5.5F, -7.0F, -1.0F, 1, 1, 8, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        return meshdefinition;
    }
}
