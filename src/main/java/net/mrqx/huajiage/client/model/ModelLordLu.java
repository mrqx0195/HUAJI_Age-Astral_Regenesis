package net.mrqx.huajiage.client.model;

import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelLordLu {
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition root = meshdefinition.getRoot();

        root.addOrReplaceChild("core",
                CubeListBuilder.create()
                        .texOffs(0, 27)
                        .addBox(-10.0F, -10.0F, 0.0F, 21, 21, 0),
                PartPose.offset(0.0F, -1.0F, 7.0F));

        PartDefinition wing = root.addOrReplaceChild("wing",
                CubeListBuilder.create(),
                PartPose.offset(0.4F, -1.0F, 8.0F));

        {
            PartDefinition wings1 = wing.addOrReplaceChild("wings1",
                    CubeListBuilder.create(),
                    PartPose.ZERO);

            wings1.addOrReplaceChild("wing1",
                    CubeListBuilder.create()
                            .texOffs(86, 2)
                            .addBox(-7.7855F, -2.0088F, 0.0F, 20, 5, 0),
                    PartPose.offsetAndRotation(-0.6145F, -14.9912F, 0.0F, 0.0F, 0.0F, -1.5708F));

            wings1.addOrReplaceChild("wing2",
                    CubeListBuilder.create()
                            .texOffs(86, 7)
                            .addBox(-10.7855F, -2.0088F, 0.0F, 20, 5, 0),
                    PartPose.offsetAndRotation(0.3855F, 18.0088F, 0.0F, 0.0F, 0.0F, 1.5708F));

            PartDefinition wings2 = wing.addOrReplaceChild("wings2",
                    CubeListBuilder.create(),
                    PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

            wings2.addOrReplaceChild("wing3",
                    CubeListBuilder.create()
                            .texOffs(86, 12)
                            .addBox(-7.7855F, -2.0088F, 0.0F, 20, 5, 0),
                    PartPose.offsetAndRotation(-0.6145F, -14.9912F, 0.0F, 0.0F, 0.0F, -1.5708F));

            wings2.addOrReplaceChild("wing4",
                    CubeListBuilder.create()
                            .texOffs(86, 17)
                            .addBox(-10.7855F, -2.0088F, 0.0F, 20, 5, 0),
                    PartPose.offsetAndRotation(0.3855F, 18.0088F, 0.0F, 0.0F, 0.0F, 1.5708F));

            PartDefinition wings3 = wing.addOrReplaceChild("wings3",
                    CubeListBuilder.create(),
                    PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.3562F));

            wings3.addOrReplaceChild("wing5",
                    CubeListBuilder.create()
                            .texOffs(86, 22)
                            .addBox(-7.7855F, -2.0088F, 0.0F, 20, 5, 0),
                    PartPose.offsetAndRotation(-0.6145F, -14.9912F, 0.0F, 0.0F, 0.0F, -1.5708F));

            wings3.addOrReplaceChild("wing6",
                    CubeListBuilder.create()
                            .texOffs(86, 27)
                            .addBox(-10.7855F, -2.0088F, 0.0F, 20, 5, 0),
                    PartPose.offsetAndRotation(0.3855F, 18.0088F, 0.0F, 0.0F, 0.0F, 1.5708F));

            PartDefinition wings4 = wing.addOrReplaceChild("wings4",
                    CubeListBuilder.create(),
                    PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

            wings4.addOrReplaceChild("wing7",
                    CubeListBuilder.create()
                            .texOffs(86, 32)
                            .addBox(-7.7855F, -2.0088F, 0.0F, 20, 5, 0),
                    PartPose.offsetAndRotation(-0.6145F, -14.9912F, 0.0F, 0.0F, 0.0F, -1.5708F));

            wings4.addOrReplaceChild("wing8",
                    CubeListBuilder.create()
                            .texOffs(86, 37)
                            .addBox(-10.7855F, -2.0088F, 0.0F, 20, 5, 0),
                    PartPose.offsetAndRotation(0.3855F, 18.0088F, 0.0F, 0.0F, 0.0F, 1.5708F));
        }

        PartDefinition wingBones = root.addOrReplaceChild("wingBones",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(0.4F, -1.0F, 8.0F, 0.0F, 0.0F, 0.3927F));

        {
            wingBones.addOrReplaceChild("wingBone1",
                    CubeListBuilder.create()
                            .texOffs(0, 52).addBox(-5.4F, -23.0F, 0.0F, 10, 1, 0)
                            .texOffs(3, 52).addBox(-4.4F, -20.0F, 0.0F, 8, 1, 0)
                            .texOffs(8, 52).addBox(-3.4F, -17.0F, 0.0F, 6, 1, 0),
                    PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.0F, 0.0F, -0.3927F));

            wingBones.addOrReplaceChild("wingBone2",
                    CubeListBuilder.create()
                            .texOffs(0, 52).addBox(-5.4F, -23.0F, 0.0F, 10, 1, 0)
                            .texOffs(3, 52).addBox(-4.4F, -20.0F, 0.0F, 8, 1, 0)
                            .texOffs(6, 55).addBox(-3.4F, -17.0F, 0.0F, 6, 1, 0),
                    PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.3927F));

            wingBones.addOrReplaceChild("wingBone3",
                    CubeListBuilder.create()
                            .texOffs(0, 55).addBox(-5.4F, -23.0F, 0.0F, 10, 1, 0)
                            .texOffs(3, 52).addBox(-4.4F, -20.0F, 0.0F, 8, 1, 0)
                            .texOffs(6, 55).addBox(-3.4F, -17.0F, 0.0F, 6, 1, 0),
                    PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.0F, 0.0F, 1.1781F));

            wingBones.addOrReplaceChild("wingBone4",
                    CubeListBuilder.create()
                            .texOffs(0, 52).addBox(-5.4F, -23.0F, 0.0F, 10, 1, 0)
                            .texOffs(3, 55).addBox(-4.4F, -20.0F, 0.0F, 8, 1, 0)
                            .texOffs(6, 55).addBox(-3.4F, -17.0F, 0.0F, 6, 1, 0),
                    PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.0F, 0.0F, 1.9635F));

            wingBones.addOrReplaceChild("wingBone5",
                    CubeListBuilder.create()
                            .texOffs(0, 55).addBox(-5.4F, -23.0F, 0.0F, 10, 1, 0)
                            .texOffs(3, 55).addBox(-4.4F, -20.0F, 0.0F, 8, 1, 0)
                            .texOffs(6, 55).addBox(-3.4F, -17.0F, 0.0F, 6, 1, 0),
                    PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.0F, 0.0F, 2.7489F));

            wingBones.addOrReplaceChild("wingBone6",
                    CubeListBuilder.create()
                            .texOffs(0, 55).addBox(-5.4F, -23.0F, 0.0F, 10, 1, 0)
                            .texOffs(3, 55).addBox(-4.4F, -20.0F, 0.0F, 8, 1, 0)
                            .texOffs(8, 52).addBox(-3.4F, -17.0F, 0.0F, 6, 1, 0),
                    PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.0F, 0.0F, -2.7489F));

            wingBones.addOrReplaceChild("wingBone7",
                    CubeListBuilder.create()
                            .texOffs(0, 52).addBox(-5.4F, -23.0F, 0.0F, 10, 1, 0)
                            .texOffs(3, 55).addBox(-4.4F, -20.0F, 0.0F, 8, 1, 0)
                            .texOffs(8, 52).addBox(-3.4F, -17.0F, 0.0F, 6, 1, 0),
                    PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.0F, 0.0F, -1.9635F));

            wingBones.addOrReplaceChild("wingBone8",
                    CubeListBuilder.create()
                            .texOffs(0, 55).addBox(-5.4F, -23.0F, 0.0F, 10, 1, 0)
                            .texOffs(3, 52).addBox(-4.4F, -20.0F, 0.0F, 8, 1, 0)
                            .texOffs(8, 52).addBox(-3.4F, -17.0F, 0.0F, 6, 1, 0),
                    PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.0F, 0.0F, -1.1781F));
        }

        return LayerDefinition.create(meshdefinition, 128, 128);
    }
}
