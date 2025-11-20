package net.mrqx.huajiage.client.model.armor;

import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelOrgaArmor {
    private static final float EXTEND_VALUE = -0.745F;

    public static MeshDefinition createBodyLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidArmorModel.createBodyLayer(deformation);
        PartDefinition root = meshdefinition.getRoot();

        PartDefinition head = root.addOrReplaceChild("head",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8),
                PartPose.ZERO);

        head.addOrReplaceChild("hair1",
                CubeListBuilder.create()
                        .texOffs(11, 0)
                        .addBox(-1.0F, -6.0F, -4.5F, 1, 1, 1),
                PartPose.ZERO);

        head.addOrReplaceChild("hair2",
                CubeListBuilder.create()
                        .texOffs(33, 0)
                        .addBox(-4.0F, -8.0F, -4.2F, 7, 6, 8),
                PartPose.ZERO);

        head.addOrReplaceChild("hair3",
                CubeListBuilder.create()
                        .texOffs(9, 1)
                        .addBox(-1.0F, -8.633333F, -4.833333F, 2, 4, 1),
                PartPose.rotation(-0.0743572F, 0.0F, 0.0F));

        head.addOrReplaceChild("hair4",
                CubeListBuilder.create()
                        .texOffs(10, 1)
                        .addBox(0.2F, -8.6F, -4.8F, 2, 4, 1),
                PartPose.rotation(-0.0743572F, -0.0371786F, -0.5205006F));

        head.addOrReplaceChild("hair5",
                CubeListBuilder.create()
                        .texOffs(11, 4)
                        .addBox(-3.266667F, -8.0F, -5.266667F, 2, 4, 1),
                PartPose.rotation(-0.1487144F, 0.0371786F, 0.6320364F));

        head.addOrReplaceChild("hair6",
                CubeListBuilder.create()
                        .texOffs(0, 51)
                        .addBox(-3.0F, -8.6F, -2.066667F, 6, 2, 7),
                PartPose.rotation(0.1858931F, 0.0F, 0.0F));

        head.addOrReplaceChild("hair7",
                CubeListBuilder.create()
                        .texOffs(31, 51)
                        .addBox(-2.066667F, -8.533334F, -1.4F, 2, 2, 6),
                PartPose.rotation(0.1487144F, 0.1487144F, 0.5205006F));

        head.addOrReplaceChild("hair8",
                CubeListBuilder.create()
                        .texOffs(31, 51)
                        .addBox(-0.8666667F, -8.533334F, -1.0F, 2, 2, 6),
                PartPose.rotation(0.1858931F, -0.2602503F, -0.4089647F));

        head.addOrReplaceChild("hair9",
                CubeListBuilder.create()
                        .texOffs(0, 40)
                        .addBox(-2.033333F, -8.666667F, -0.4666667F, 4, 3, 7),
                PartPose.rotation(0.3346075F, 0.0F, 0.0F));

        head.addOrReplaceChild("hair10",
                CubeListBuilder.create()
                        .texOffs(31, 40)
                        .addBox(-1.0F, -8.9F, 1.533333F, 2, 3, 5),
                PartPose.rotation(0.5576792F, 0.0F, 0.0F));

        root.addOrReplaceChild("body",
                CubeListBuilder.create()
                        .texOffs(16, 16)
                        .addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, deformation.extend(EXTEND_VALUE)),
                PartPose.ZERO);

        root.addOrReplaceChild("right_arm",
                CubeListBuilder.create()
                        .texOffs(40, 16)
                        .addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, deformation.extend(EXTEND_VALUE + 0.05F)),
                PartPose.offset(-5.0F, 2.0F, 0.0F));

        root.addOrReplaceChild("left_arm",
                CubeListBuilder.create()
                        .texOffs(40, 16)
                        .addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, deformation.extend(EXTEND_VALUE + 0.05F)),
                PartPose.offset(5.0F, 2.0F, 0.0F));

        root.addOrReplaceChild("right_leg",
                CubeListBuilder.create()
                        .texOffs(0, 16)
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.extend(EXTEND_VALUE)),
                PartPose.offset(-1.9F, 12.0F, 0.0F));

        root.addOrReplaceChild("left_leg",
                CubeListBuilder.create()
                        .texOffs(0, 16)
                        .mirror()
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.extend(EXTEND_VALUE)),
                PartPose.offset(1.9F, 12.0F, 0.0F));

        return meshdefinition;
    }
}
