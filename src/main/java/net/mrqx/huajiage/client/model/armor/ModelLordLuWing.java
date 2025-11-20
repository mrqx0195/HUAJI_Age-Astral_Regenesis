package net.mrqx.huajiage.client.model.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelLordLuWing {
    /**
     * @author XiaoTin
     */
    public static MeshDefinition createBodyLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(deformation, 0);
        PartDefinition partdefinition = meshdefinition.getRoot();

        wings(partdefinition);
        ring(partdefinition);
        sun(partdefinition);

        return meshdefinition;
    }

    private static void wings(PartDefinition partdefinition) {
        PartDefinition wings = partdefinition.addOrReplaceChild("wings", CubeListBuilder.create(), PartPose.offset(3.725F, 1.3407F, -20.1802F));

        CubeDeformation cubeDeformation = new CubeDeformation(-0.49F, 0, 0);

        PartDefinition bone17 = wings.addOrReplaceChild("bone17", CubeListBuilder.create().texOffs(106, 9).addBox(-0.5F, 1.2993F, -4.5713F, 1.0F, 1.0F, 7.0F, cubeDeformation)
                .texOffs(90, 122).addBox(-0.5F, 0.2993F, -1.9313F, 1.0F, 2.0F, 5.0F, cubeDeformation)
                .texOffs(132, 0).addBox(-0.5F, 1.2993F, -3.3063F, 1.0F, 1.0F, 3.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, -7.1364F, 33.1784F, -3.0107F, 0.0F, 3.1416F));

        bone17.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(114, 74).addBox(-0.725F, -0.725F, -0.175F, 1.0F, 1.0F, 7.0F, cubeDeformation), PartPose.offsetAndRotation(0.225F, 2.203F, 2.8213F, 1.5359F, 0.0F, 0.0F));

        bone17.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(72, 124).addBox(-1.0F, -1.9F, 3.3F, 1.0F, 2.0F, 5.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 3.3888F, -6.1631F, 0.3927F, 0.0F, 0.0F));

        bone17.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(90, 114).addBox(-1.0F, -0.9F, 3.3F, 1.0F, 2.0F, 6.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 2.0888F, -5.8631F, 0.3927F, 0.0F, 0.0F));

        bone17.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(30, 60).addBox(-1.0F, 0.1F, 6.6F, 1.0F, 1.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 2.0888F, -9.0631F, 0.3927F, 0.0F, 0.0F));

        bone17.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(84, 124).addBox(-1.0F, 0.2F, 4.95F, 1.0F, 2.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 2.0888F, -4.2631F, 0.3927F, 0.0F, 0.0F));

        bone17.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(60, 101).addBox(-0.5F, -2.831F, -0.8625F, 1.0F, 4.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, -1.3249F, 0.9753F, -0.4363F, 0.0F, 0.0F));

        bone17.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(96, 84).addBox(-1.0F, -0.25F, 5.05F, 1.0F, 2.0F, 3.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 0.6888F, -5.7631F, 0.3927F, 0.0F, 0.0F));

        bone17.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(24, 89).addBox(-1.0F, -1.0F, -0.55F, 1.0F, 1.0F, 10.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.9348F, -4.1593F, 0.7243F, 0.0F, 0.0F));

        PartDefinition bone14 = wings.addOrReplaceChild("bone14", CubeListBuilder.create().texOffs(126, 102).addBox(-0.5F, 0.5199F, -5.3992F, 1.0F, 1.0F, 5.0F, cubeDeformation)
                .texOffs(60, 129).addBox(-0.5F, -0.4801F, -3.2124F, 1.0F, 2.0F, 4.0F, cubeDeformation)
                .texOffs(36, 60).addBox(-0.5F, 0.5199F, -4.4251F, 1.0F, 1.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.115F, 1.4807F, 11.3204F, -1.1781F, 0.0F, 3.1416F));

        bone14.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(132, 12).addBox(-0.7883F, -0.7883F, -0.1348F, 1.0F, 1.0F, 3.0F, cubeDeformation), PartPose.offsetAndRotation(0.2883F, 0.5926F, 0.7274F, 0.3229F, 0.0F, 0.0F));

        bone14.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(28, 128).addBox(-0.7883F, -0.7882F, -0.1348F, 1.0F, 1.0F, 5.0F, cubeDeformation), PartPose.offsetAndRotation(0.2883F, 1.2856F, 0.7274F, 0.3229F, 0.0F, 0.0F));

        bone14.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(130, 31).addBox(-1.0F, -0.923F, 2.541F, 1.0F, 1.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 2.2049F, -6.3939F, 0.3927F, 0.0F, 0.0F));

        bone14.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(78, 131).addBox(-1.0F, -1.153F, 2.541F, 1.0F, 2.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 0.9579F, -3.6839F, 0.3927F, 0.0F, 0.0F));

        bone14.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(84, 124).addBox(-1.0F, -0.306F, 3.8115F, 1.0F, 2.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.3579F, -5.0079F, 0.3927F, 0.0F, 0.0F));

        bone14.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(104, 114).addBox(-1.0F, -1.0F, 2.6565F, 1.0F, 1.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, -0.7085F, 0.5122F, -0.1134F, 0.0F, 0.0F));

        bone14.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(40, 128).addBox(-1.0F, -1.0F, -0.4235F, 1.0F, 1.0F, 5.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, -0.2681F, -0.0834F, -0.1134F, 0.0F, 0.0F));

        bone14.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(126, 108).addBox(-1.0F, -1.0F, -0.4235F, 1.0F, 1.0F, 5.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, -0.7301F, 0.3166F, -0.1134F, 0.0F, 0.0F));

        bone14.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(60, 115).addBox(-0.615F, -0.615F, -3.08F, 1.0F, 1.0F, 6.0F, cubeDeformation), PartPose.offsetAndRotation(0.115F, -0.0144F, -2.701F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone13 = wings.addOrReplaceChild("bone13", CubeListBuilder.create().texOffs(106, 9).addBox(-0.5F, 0.4522F, -4.767F, 1.0F, 1.0F, 7.0F, cubeDeformation)
                .texOffs(90, 122).addBox(-0.5F, -0.5478F, -1.927F, 1.0F, 2.0F, 5.0F, cubeDeformation)
                .texOffs(132, 0).addBox(-0.5F, 0.4522F, -3.502F, 1.0F, 1.0F, 3.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, -23.2773F, -2.8472F, 0.6109F, 0.0F, 3.1416F));

        bone13.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(130, 36).addBox(-0.725F, -0.725F, -0.175F, 1.0F, 1.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(0.225F, 0.2478F, 3.1897F, 0.3229F, 0.0F, 0.0F));

        bone13.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(110, 101).addBox(-0.725F, -0.725F, -0.175F, 1.0F, 1.0F, 7.0F, cubeDeformation), PartPose.offsetAndRotation(0.225F, 1.1478F, 3.1897F, 0.3229F, 0.0F, 0.0F));

        bone13.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(126, 63).addBox(-1.0F, -0.9F, 3.3F, 1.0F, 1.0F, 5.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 2.3417F, -6.0589F, 0.3927F, 0.0F, 0.0F));

        bone13.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(104, 129).addBox(-1.0F, -0.9F, 3.3F, 1.0F, 2.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.2417F, -3.0589F, 0.3927F, 0.0F, 0.0F));

        bone13.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(84, 124).addBox(-1.0F, 0.2F, 4.95F, 1.0F, 2.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.2417F, -4.2589F, 0.3927F, 0.0F, 0.0F));

        bone13.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(132, 4).addBox(-1.0F, -1.0F, 3.45F, 1.0F, 1.0F, 3.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, -1.4419F, 2.9102F, -0.1134F, 0.0F, 0.0F));

        bone13.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(14, 115).addBox(-1.0F, -1.0F, -0.55F, 1.0F, 1.0F, 6.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, -0.87F, 2.6561F, -0.1134F, 0.0F, 0.0F));

        bone13.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(110, 109).addBox(-1.0F, -1.0F, -0.55F, 1.0F, 1.0F, 7.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, -1.47F, 2.6561F, -0.1134F, 0.0F, 0.0F));

        bone13.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(96, 75).addBox(-0.5F, -0.5F, -4.0F, 1.0F, 1.0F, 8.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, -0.5405F, -1.2628F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone6 = wings.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(90, 122).addBox(-0.5F, -0.2011F, -3.1548F, 1.0F, 2.0F, 5.0F, cubeDeformation)
                .texOffs(126, 57).addBox(-0.5F, 1.0989F, -6.5298F, 1.0F, 1.0F, 5.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.829F, 0.0F, 3.1416F));

        bone6.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(132, 8).addBox(-0.725F, -0.725F, 1.825F, 1.0F, 1.0F, 3.0F, cubeDeformation), PartPose.offsetAndRotation(0.225F, 1.7027F, 1.5978F, 1.117F, 0.0F, 0.0F));

        bone6.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(126, 69).addBox(-0.725F, -0.725F, 0.825F, 1.0F, 1.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(0.225F, 2.4037F, 1.2803F, 1.117F, 0.0F, 0.0F));

        bone6.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(126, 63).addBox(-1.0F, -0.9F, 3.3F, 1.0F, 1.0F, 5.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 2.6884F, -7.0867F, 0.3927F, 0.0F, 0.0F));

        bone6.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(72, 124).addBox(-1.0F, -0.9F, 3.3F, 1.0F, 2.0F, 5.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.5884F, -5.0867F, 0.3927F, 0.0F, 0.0F));

        bone6.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(30, 60).addBox(-1.0F, 0.1F, 6.6F, 1.0F, 1.0F, 2.0F, cubeDeformation)
                .texOffs(104, 93).addBox(-1.0F, 0.2F, 4.95F, 1.0F, 2.0F, 2.0F, cubeDeformation)
                .texOffs(0, 49).addBox(-1.0F, -1.0F, -4.55F, 1.0F, 1.0F, 14.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.5884F, -5.2867F, 0.3927F, 0.0F, 0.0F));

        bone6.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(100, 9).addBox(-1.0F, -0.25F, 6.05F, 1.0F, 2.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.5884F, -5.6867F, 0.3927F, 0.0F, 0.0F));

        bone6.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(0, 78).addBox(-1.0F, -1.0F, -10.55F, 1.0F, 1.0F, 11.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.9454F, 0.9435F, 0.1396F, 0.0F, 0.0F));

        bone6.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(0, 78).addBox(-1.0F, -1.0F, -10.55F, 1.0F, 1.0F, 11.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.8454F, 1.4435F, 0.1396F, 0.0F, 0.0F));

        PartDefinition bone4 = wings.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(48, 25).addBox(-0.5F, 1.9236F, -15.8957F, 1.0F, 1.0F, 19.0F, cubeDeformation)
                .texOffs(84, 75).addBox(-0.5F, 1.9236F, -16.2957F, 1.0F, 1.0F, 1.0F, cubeDeformation)
                .texOffs(50, 0).addBox(-0.5F, -0.0764F, -10.0957F, 1.0F, 3.0F, 13.0F, cubeDeformation)
                .texOffs(66, 89).addBox(-0.5F, 0.9236F, -13.1957F, 1.0F, 2.0F, 9.0F, cubeDeformation), PartPose.offsetAndRotation(-0.225F, -9.2643F, -1.9241F, -0.7418F, 0.0F, 0.0F));

        bone4.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(60, 122).addBox(-0.5F, -1.5F, 3.5F, 1.0F, 2.0F, 5.0F, cubeDeformation)
                .texOffs(50, 16).addBox(-0.5F, -0.5F, 1.5F, 1.0F, 1.0F, 8.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, 4.0232F, 1.9682F, 1.117F, 0.0F, 0.0F));

        bone4.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(30, 63).addBox(-0.5F, -1.5F, -5.5F, 1.0F, 3.0F, 11.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, -0.6327F, -1.0036F, 0.3054F, 0.0F, 0.0F));

        bone4.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(96, 84).addBox(-1.0F, 0.0F, 12.0F, 1.0F, 2.0F, 3.0F, cubeDeformation)
                .texOffs(126, 49).addBox(-1.0F, 0.0F, 9.0F, 1.0F, 4.0F, 4.0F, cubeDeformation)
                .texOffs(60, 107).addBox(-1.0F, -1.0F, 11.0F, 1.0F, 6.0F, 1.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 2.5409F, -9.9718F, 0.3927F, 0.0F, 0.0F));

        bone4.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.3148F, -22.1535F, 1.0F, 1.0F, 24.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, -4.3944F, 4.615F, 0.3054F, 0.0F, 0.0F));

        PartDefinition bone15 = wings.addOrReplaceChild("bone15", CubeListBuilder.create().texOffs(48, 45).addBox(-0.3866F, 2.5863F, -13.3658F, 1.0F, 1.0F, 17.0F, cubeDeformation)
                .texOffs(78, 0).addBox(-0.3866F, 0.5863F, -6.6076F, 1.0F, 3.0F, 10.0F, cubeDeformation)
                .texOffs(78, 0).addBox(-0.3866F, 0.0863F, -6.6076F, 1.0F, 3.0F, 10.0F, cubeDeformation)
                .texOffs(128, 114).addBox(-0.3866F, 0.5863F, -8.8044F, 1.0F, 3.0F, 4.0F, cubeDeformation)
                .texOffs(124, 129).addBox(-0.3866F, 1.5863F, -11.3274F, 1.0F, 2.0F, 4.0F, cubeDeformation)
                .texOffs(130, 85).addBox(-0.3866F, 1.1513F, -9.5874F, 1.0F, 2.0F, 3.0F, cubeDeformation)
                .texOffs(130, 41).addBox(-0.3866F, 0.4983F, -7.0644F, 1.0F, 1.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(-0.225F, -12.2642F, 44.0759F, 2.5307F, 0.0F, -3.1416F));

        bone15.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(114, 129).addBox(-0.3866F, -2.2566F, 5.1634F, 1.0F, 2.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, 3.9783F, 3.9803F, 1.117F, 0.0F, 0.0F));

        bone15.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(114, 33).addBox(-0.3866F, -0.3867F, 1.6835F, 1.0F, 1.0F, 7.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, 2.5133F, 3.0493F, 1.117F, 0.0F, 0.0F));

        bone15.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(104, 84).addBox(-0.3866F, -1.3867F, 1.6835F, 1.0F, 2.0F, 7.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, 3.0133F, 3.0493F, 1.117F, 0.0F, 0.0F));

        bone15.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(102, 64).addBox(-0.3866F, -2.3866F, 1.6835F, 1.0F, 3.0F, 7.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, 4.4133F, 2.6493F, 1.117F, 0.0F, 0.0F));

        bone15.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(88, 28).addBox(-0.3866F, -0.3867F, 1.84F, 1.0F, 1.0F, 10.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, 4.9713F, 2.6763F, 1.117F, 0.0F, 0.0F));

        bone15.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(54, 63).addBox(-1.0F, -0.5466F, 7.3602F, 1.0F, 3.0F, 11.0F, cubeDeformation)
                .texOffs(130, 79).addBox(-1.0F, -0.5466F, 14.7204F, 1.0F, 3.0F, 3.0F, cubeDeformation)
                .texOffs(78, 114).addBox(-1.0F, -0.0932F, 11.0403F, 1.0F, 5.0F, 5.0F, cubeDeformation)
                .texOffs(18, 90).addBox(-1.0F, -0.8665F, 13.4937F, 1.0F, 7.0F, 1.0F, cubeDeformation)
                .texOffs(0, 25).addBox(-1.0F, -1.0F, -1.2267F, 1.0F, 1.0F, 23.0F, cubeDeformation), PartPose.offsetAndRotation(0.6133F, 3.1169F, -12.2325F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone12 = wings.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(106, 9).addBox(-0.5F, 0.7989F, -5.7948F, 1.0F, 1.0F, 7.0F, cubeDeformation)
                .texOffs(90, 122).addBox(-0.5F, -0.2011F, -2.9548F, 1.0F, 2.0F, 5.0F, cubeDeformation)
                .texOffs(132, 0).addBox(-0.5F, 0.7989F, -4.5298F, 1.0F, 1.0F, 3.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, -12.6F, 6.0F, 2.0944F, 0.0F, 3.1416F));

        bone12.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(84, 13).addBox(-0.725F, -0.725F, 1.825F, 1.0F, 1.0F, 1.0F, cubeDeformation), PartPose.offsetAndRotation(0.225F, 1.7027F, 1.5978F, 1.6406F, 0.0F, 0.0F));

        bone12.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(126, 63).addBox(-1.0F, -0.9F, 3.3F, 1.0F, 1.0F, 5.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 2.6884F, -7.0867F, 0.3927F, 0.0F, 0.0F));

        bone12.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(94, 129).addBox(-1.0F, -0.9F, 3.3F, 1.0F, 2.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.5884F, -5.0867F, 0.3927F, 0.0F, 0.0F));

        bone12.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(84, 124).addBox(-1.0F, 0.2F, 4.95F, 1.0F, 2.0F, 2.0F, cubeDeformation)
                .texOffs(96, 75).addBox(-1.0F, -1.0F, -0.55F, 1.0F, 1.0F, 8.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.5884F, -5.2867F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone11 = wings.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(0, 115).addBox(-0.59F, 0.4751F, -4.7517F, 1.0F, 1.0F, 6.0F, cubeDeformation)
                .texOffs(60, 129).addBox(-0.59F, -0.5249F, -2.5869F, 1.0F, 2.0F, 4.0F, cubeDeformation)
                .texOffs(36, 60).addBox(-0.59F, 0.4751F, -3.7145F, 1.0F, 1.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, -3.9F, 6.0F, 2.4871F, 0.0F, 3.1416F));

        bone11.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(42, 60).addBox(-0.7745F, -0.7745F, 1.4965F, 1.0F, 1.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.1845F, 1.3962F, 1.3102F, 1.117F, 0.0F, 0.0F));

        bone11.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(0, 132).addBox(-0.7745F, -0.7745F, 0.6765F, 1.0F, 1.0F, 3.0F, cubeDeformation), PartPose.offsetAndRotation(0.1845F, 1.971F, 1.0499F, 1.117F, 0.0F, 0.0F));

        bone11.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(130, 31).addBox(-1.0F, -0.918F, 2.706F, 1.0F, 1.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(0.41F, 2.2045F, -5.8111F, 0.3927F, 0.0F, 0.0F));

        bone11.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(84, 129).addBox(-1.0F, -1.098F, 2.706F, 1.0F, 2.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(0.41F, 1.3025F, -4.1711F, 0.3927F, 0.0F, 0.0F));

        bone11.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(84, 124).addBox(-1.0F, -0.196F, 4.059F, 1.0F, 2.0F, 2.0F, cubeDeformation)
                .texOffs(68, 16).addBox(-1.0F, -1.0F, -0.451F, 1.0F, 1.0F, 8.0F, cubeDeformation), PartPose.offsetAndRotation(0.41F, 1.3025F, -4.3351F, 0.3927F, 0.0F, 0.0F));

        bone11.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(100, 9).addBox(-1.0F, -0.565F, 4.961F, 1.0F, 2.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.41F, 1.3025F, -4.6631F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone16 = wings.addOrReplaceChild("bone16", CubeListBuilder.create().texOffs(106, 9).addBox(-0.5F, 0.7989F, -5.7948F, 1.0F, 1.0F, 7.0F, cubeDeformation)
                .texOffs(90, 122).addBox(-0.5F, -0.2011F, -3.1548F, 1.0F, 2.0F, 5.0F, cubeDeformation)
                .texOffs(132, 0).addBox(-0.5F, 0.7989F, -4.5298F, 1.0F, 1.0F, 3.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, 10.0F, 43.0F, -2.7489F, 0.0F, 3.1416F));

        bone16.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(132, 8).addBox(-0.725F, -0.725F, 1.825F, 1.0F, 1.0F, 3.0F, cubeDeformation), PartPose.offsetAndRotation(0.225F, 1.7027F, 1.5978F, 1.117F, 0.0F, 0.0F));

        bone16.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(126, 69).addBox(-0.725F, -0.725F, 0.825F, 1.0F, 1.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(0.225F, 2.4037F, 1.2803F, 1.117F, 0.0F, 0.0F));

        bone16.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(126, 63).addBox(-1.0F, -0.9F, 3.3F, 1.0F, 1.0F, 5.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 2.6884F, -7.0867F, 0.3927F, 0.0F, 0.0F));

        bone16.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(72, 124).addBox(-1.0F, -0.9F, 3.3F, 1.0F, 2.0F, 5.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.5884F, -5.0867F, 0.3927F, 0.0F, 0.0F));

        bone16.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(30, 60).addBox(-1.0F, 0.1F, 6.6F, 1.0F, 1.0F, 2.0F, cubeDeformation)
                .texOffs(84, 124).addBox(-1.0F, 0.2F, 4.95F, 1.0F, 2.0F, 2.0F, cubeDeformation)
                .texOffs(24, 89).addBox(-1.0F, -1.0F, -0.55F, 1.0F, 1.0F, 10.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.5884F, -5.2867F, 0.3927F, 0.0F, 0.0F));

        bone16.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(100, 9).addBox(-1.0F, -0.25F, 6.05F, 1.0F, 2.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.5884F, -5.6867F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone19 = wings.addOrReplaceChild("bone19", CubeListBuilder.create().texOffs(0, 115).addBox(-0.5F, 0.4956F, -4.5305F, 1.0F, 1.0F, 6.0F, cubeDeformation)
                .texOffs(60, 129).addBox(-0.5F, -0.5044F, -2.3657F, 1.0F, 2.0F, 4.0F, cubeDeformation)
                .texOffs(36, 60).addBox(-0.5F, 0.4956F, -3.4932F, 1.0F, 1.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.09F, -0.1018F, 40.5929F, 0.7418F, 0.0F, 0.0F));

        bone19.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(42, 60).addBox(-0.7745F, -0.7745F, 1.4965F, 1.0F, 1.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.2745F, 1.4167F, 1.5314F, 1.117F, 0.0F, 0.0F));

        bone19.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(0, 132).addBox(-0.7745F, -0.7745F, 0.6765F, 1.0F, 1.0F, 3.0F, cubeDeformation), PartPose.offsetAndRotation(0.2745F, 1.9915F, 1.2711F, 1.117F, 0.0F, 0.0F));

        bone19.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(130, 31).addBox(-1.0F, -0.918F, 2.706F, 1.0F, 1.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 2.225F, -5.5898F, 0.3927F, 0.0F, 0.0F));

        bone19.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(84, 129).addBox(-1.0F, -1.098F, 2.706F, 1.0F, 2.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.323F, -3.9498F, 0.3927F, 0.0F, 0.0F));

        bone19.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(84, 124).addBox(-1.0F, -0.196F, 4.059F, 1.0F, 2.0F, 2.0F, cubeDeformation)
                .texOffs(68, 16).addBox(-1.0F, -1.0F, -0.451F, 1.0F, 1.0F, 8.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.323F, -4.1138F, 0.3927F, 0.0F, 0.0F));

        bone19.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(100, 9).addBox(-1.0F, -0.565F, 4.961F, 1.0F, 2.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.323F, -4.4418F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone20 = wings.addOrReplaceChild("bone20", CubeListBuilder.create().texOffs(0, 115).addBox(-0.5F, 0.4956F, -4.5305F, 1.0F, 1.0F, 6.0F, cubeDeformation)
                .texOffs(60, 129).addBox(-0.5F, -0.5044F, -2.3657F, 1.0F, 2.0F, 4.0F, cubeDeformation)
                .texOffs(36, 60).addBox(-0.5F, 0.4956F, -3.4932F, 1.0F, 1.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.09F, -16.1018F, 31.5929F, -0.4363F, 0.0F, 0.0F));

        bone20.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(42, 60).addBox(-0.7745F, -0.7745F, 1.4965F, 1.0F, 1.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.2745F, 1.4167F, 1.5314F, 1.117F, 0.0F, 0.0F));

        bone20.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(0, 132).addBox(-0.7745F, -0.7745F, 0.6765F, 1.0F, 1.0F, 3.0F, cubeDeformation), PartPose.offsetAndRotation(0.2745F, 1.9915F, 1.2711F, 1.117F, 0.0F, 0.0F));

        bone20.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(130, 31).addBox(-1.0F, -0.918F, 2.706F, 1.0F, 1.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 2.225F, -5.5898F, 0.3927F, 0.0F, 0.0F));

        bone20.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(84, 129).addBox(-1.0F, -1.098F, 2.706F, 1.0F, 2.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.323F, -3.9498F, 0.3927F, 0.0F, 0.0F));

        bone20.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(84, 124).addBox(-1.0F, -0.196F, 4.059F, 1.0F, 2.0F, 2.0F, cubeDeformation)
                .texOffs(68, 16).addBox(-1.0F, -1.0F, -0.451F, 1.0F, 1.0F, 8.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.323F, -4.1138F, 0.3927F, 0.0F, 0.0F));

        bone20.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(100, 9).addBox(-1.0F, -0.565F, 4.961F, 1.0F, 2.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.323F, -4.4418F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone18 = wings.addOrReplaceChild("bone18", CubeListBuilder.create().texOffs(0, 115).addBox(-0.5F, 0.4956F, -4.5305F, 1.0F, 1.0F, 6.0F, cubeDeformation)
                .texOffs(60, 129).addBox(-0.5F, -0.5044F, -2.3657F, 1.0F, 2.0F, 4.0F, cubeDeformation)
                .texOffs(36, 60).addBox(-0.5F, 0.4956F, -3.4932F, 1.0F, 1.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(-0.01F, 8.5795F, 43.6213F, 3.1416F, 0.0F, 3.1416F));

        bone18.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(42, 60).addBox(-0.7745F, -0.7745F, 1.4965F, 1.0F, 1.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.2745F, 1.4167F, 1.5314F, 1.117F, 0.0F, 0.0F));

        bone18.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(0, 132).addBox(-0.7745F, -0.7745F, 0.6765F, 1.0F, 1.0F, 3.0F, cubeDeformation), PartPose.offsetAndRotation(0.2745F, 1.9915F, 1.2711F, 1.117F, 0.0F, 0.0F));

        bone18.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(130, 31).addBox(-1.0F, -0.918F, 2.706F, 1.0F, 1.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 2.225F, -5.5898F, 0.3927F, 0.0F, 0.0F));

        bone18.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(84, 129).addBox(-1.0F, -1.098F, 2.706F, 1.0F, 2.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.323F, -3.9498F, 0.3927F, 0.0F, 0.0F));

        bone18.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(84, 124).addBox(-1.0F, -0.196F, 4.059F, 1.0F, 2.0F, 2.0F, cubeDeformation)
                .texOffs(68, 16).addBox(-1.0F, -1.0F, -0.451F, 1.0F, 1.0F, 8.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.323F, -4.1138F, 0.3927F, 0.0F, 0.0F));

        bone18.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(100, 9).addBox(-1.0F, -0.565F, 4.961F, 1.0F, 2.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.323F, -4.4418F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone22 = wings.addOrReplaceChild("bone22", CubeListBuilder.create().texOffs(88, 39).addBox(-0.59F, 0.2264F, -3.715F, 1.0F, 1.0F, 5.0F, cubeDeformation)
                .texOffs(132, 16).addBox(-0.59F, -0.7736F, -0.5399F, 1.0F, 2.0F, 2.0F, cubeDeformation)
                .texOffs(36, 60).addBox(-0.59F, 0.2264F, -2.8644F, 1.0F, 1.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.09F, 1.5795F, 48.3213F, -2.618F, 0.0F, 3.1416F));

        bone22.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(42, 60).addBox(-0.8151F, -0.8151F, 1.2271F, 1.0F, 1.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.2251F, 1.1617F, 1.2558F, 1.117F, 0.0F, 0.0F));

        bone22.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(78, 13).addBox(-0.8151F, -0.8151F, 0.5547F, 1.0F, 1.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.2251F, 1.633F, 1.0423F, 1.117F, 0.0F, 0.0F));

        bone22.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(8, 132).addBox(-1.0F, -0.9328F, 2.2189F, 1.0F, 1.0F, 3.0F, cubeDeformation), PartPose.offsetAndRotation(0.41F, 1.8245F, -4.5837F, 0.3927F, 0.0F, 0.0F));

        bone22.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(70, 131).addBox(-1.0F, -1.2604F, 2.2189F, 1.0F, 2.0F, 3.0F, cubeDeformation), PartPose.offsetAndRotation(0.41F, 1.3849F, -3.2389F, 0.3927F, 0.0F, 0.0F));

        bone22.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(84, 124).addBox(-1.0F, -0.5207F, 3.3284F, 1.0F, 2.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.41F, 1.0848F, -3.7733F, 0.3927F, 0.0F, 0.0F));

        bone22.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(114, 41).addBox(-1.0F, -1.0F, -0.3698F, 1.0F, 1.0F, 7.0F, cubeDeformation), PartPose.offsetAndRotation(0.41F, 1.0848F, -3.3733F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone21 = wings.addOrReplaceChild("bone21", CubeListBuilder.create().texOffs(0, 115).addBox(-0.59F, 0.4751F, -4.7517F, 1.0F, 1.0F, 6.0F, cubeDeformation)
                .texOffs(60, 129).addBox(-0.59F, -0.5249F, -2.5869F, 1.0F, 2.0F, 4.0F, cubeDeformation)
                .texOffs(36, 60).addBox(-0.59F, 0.4751F, -3.7144F, 1.0F, 1.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, -19.9F, 45.0F, 2.0944F, 0.0F, 3.1416F));

        bone21.addOrReplaceChild("cube_r85", CubeListBuilder.create().texOffs(42, 60).addBox(-0.7745F, -0.7745F, 1.4965F, 1.0F, 1.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.1845F, 1.3962F, 1.3102F, 1.117F, 0.0F, 0.0F));

        bone21.addOrReplaceChild("cube_r86", CubeListBuilder.create().texOffs(0, 132).addBox(-0.7745F, -0.7745F, 0.6765F, 1.0F, 1.0F, 3.0F, cubeDeformation), PartPose.offsetAndRotation(0.1845F, 1.971F, 1.0499F, 1.117F, 0.0F, 0.0F));

        bone21.addOrReplaceChild("cube_r87", CubeListBuilder.create().texOffs(130, 31).addBox(-1.0F, -0.918F, 2.706F, 1.0F, 1.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(0.41F, 2.2045F, -5.8111F, 0.3927F, 0.0F, 0.0F));

        bone21.addOrReplaceChild("cube_r88", CubeListBuilder.create().texOffs(84, 129).addBox(-1.0F, -1.098F, 2.706F, 1.0F, 2.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(0.41F, 1.3025F, -4.1711F, 0.3927F, 0.0F, 0.0F));

        bone21.addOrReplaceChild("cube_r89", CubeListBuilder.create().texOffs(84, 124).addBox(-1.0F, -0.196F, 4.059F, 1.0F, 2.0F, 2.0F, cubeDeformation)
                .texOffs(68, 16).addBox(-1.0F, -1.0F, -0.451F, 1.0F, 1.0F, 8.0F, cubeDeformation), PartPose.offsetAndRotation(0.41F, 1.3025F, -4.3351F, 0.3927F, 0.0F, 0.0F));

        bone21.addOrReplaceChild("cube_r90", CubeListBuilder.create().texOffs(100, 9).addBox(-1.0F, -0.565F, 4.961F, 1.0F, 2.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.41F, 1.3025F, -4.6631F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone24 = wings.addOrReplaceChild("bone24", CubeListBuilder.create().texOffs(88, 39).addBox(-0.5F, 0.2754F, -3.5851F, 1.0F, 1.0F, 5.0F, cubeDeformation)
                .texOffs(132, 16).addBox(-0.5F, -0.7246F, -0.4099F, 1.0F, 2.0F, 2.0F, cubeDeformation)
                .texOffs(36, 60).addBox(-0.5F, 0.2754F, -2.7345F, 1.0F, 1.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, 10.1721F, -0.4668F, 0.8727F, 0.0F, 0.0F));

        bone24.addOrReplaceChild("cube_r91", CubeListBuilder.create().texOffs(130, 74).addBox(-0.8151F, -0.8151F, 0.5547F, 1.0F, 1.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(0.3151F, 1.6237F, 1.1204F, 0.9599F, 0.0F, 0.0F));

        bone24.addOrReplaceChild("cube_r92", CubeListBuilder.create().texOffs(8, 132).addBox(-1.0F, -0.9328F, 2.2189F, 1.0F, 1.0F, 3.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.8735F, -4.4537F, 0.3927F, 0.0F, 0.0F));

        bone24.addOrReplaceChild("cube_r93", CubeListBuilder.create().texOffs(70, 131).addBox(-1.0F, -1.2604F, 2.2189F, 1.0F, 2.0F, 3.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.2339F, -2.7089F, 0.3927F, 0.0F, 0.0F));

        bone24.addOrReplaceChild("cube_r94", CubeListBuilder.create().texOffs(84, 124).addBox(-1.0F, -0.5207F, 3.3284F, 1.0F, 2.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.1339F, -3.6434F, 0.3927F, 0.0F, 0.0F));

        bone24.addOrReplaceChild("cube_r95", CubeListBuilder.create().texOffs(100, 0).addBox(-1.0F, -1.0F, -0.3698F, 1.0F, 1.0F, 8.0F, cubeDeformation), PartPose.offsetAndRotation(0.5F, 1.1339F, -3.2434F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone23 = wings.addOrReplaceChild("bone23", CubeListBuilder.create().texOffs(88, 39).addBox(-0.59F, 0.2264F, -3.715F, 1.0F, 1.0F, 5.0F, cubeDeformation)
                .texOffs(132, 16).addBox(-0.59F, -0.7736F, -0.5399F, 1.0F, 2.0F, 2.0F, cubeDeformation)
                .texOffs(36, 60).addBox(-0.59F, 0.2264F, -2.8644F, 1.0F, 1.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.09F, -2.7205F, -7.3788F, -0.5236F, 0.0F, 0.0F));

        bone23.addOrReplaceChild("cube_r96", CubeListBuilder.create().texOffs(130, 74).addBox(-0.8151F, -0.8151F, 0.5547F, 1.0F, 1.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(0.2251F, 1.5747F, 0.9905F, 0.9599F, 0.0F, 0.0F));

        bone23.addOrReplaceChild("cube_r97", CubeListBuilder.create().texOffs(8, 132).addBox(-1.0F, -0.9328F, 2.2189F, 1.0F, 1.0F, 3.0F, cubeDeformation), PartPose.offsetAndRotation(0.41F, 1.8245F, -4.5837F, 0.3927F, 0.0F, 0.0F));

        bone23.addOrReplaceChild("cube_r98", CubeListBuilder.create().texOffs(70, 131).addBox(-1.0F, -1.2604F, 2.2189F, 1.0F, 2.0F, 3.0F, cubeDeformation), PartPose.offsetAndRotation(0.41F, 1.1849F, -2.8389F, 0.3927F, 0.0F, 0.0F));

        bone23.addOrReplaceChild("cube_r99", CubeListBuilder.create().texOffs(84, 124).addBox(-1.0F, -0.5207F, 3.3284F, 1.0F, 2.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(0.41F, 1.0848F, -3.7733F, 0.3927F, 0.0F, 0.0F));

        bone23.addOrReplaceChild("cube_r100", CubeListBuilder.create().texOffs(100, 0).addBox(-1.0F, -1.0F, -0.3698F, 1.0F, 1.0F, 8.0F, cubeDeformation), PartPose.offsetAndRotation(0.41F, 1.0848F, -3.3733F, 0.3927F, 0.0F, 0.0F));
    }

    private static void ring(PartDefinition partdefinition) {
        PartDefinition ring = partdefinition.addOrReplaceChild("ring", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition hexadecagon = ring.addOrReplaceChild("hexadecagon", CubeListBuilder.create(), PartPose.offset(-0.5F, -24.2311F, 0.2296F));

        PartDefinition bone2 = hexadecagon.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.9599F, 0.0F, 0.0F));

        CubeDeformation cubeDeformation = new CubeDeformation(0);

        bone2.addOrReplaceChild("hexadecagon_r1", CubeListBuilder.create().texOffs(0, 90).addBox(77.9616F, 29.3005F, -4.0326F, 1.0F, 2.0F, 8.0F, cubeDeformation)
                .texOffs(78, 63).addBox(77.9616F, 23.3005F, -1.0326F, 1.0F, 11.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(-79.7916F, -0.0299F, 0.0347F, -0.3927F, 0.0F, 0.0F));

        PartDefinition bone = hexadecagon.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

        bone.addOrReplaceChild("hexadecagon_r2", CubeListBuilder.create().texOffs(86, 89).addBox(77.9616F, 29.3005F, -4.0326F, 1.0F, 2.0F, 8.0F, cubeDeformation)
                .texOffs(24, 64).addBox(77.9616F, 23.3005F, -1.0326F, 1.0F, 11.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(-79.7916F, -0.0299F, 0.0347F, -0.3927F, 0.0F, 0.0F));

        PartDefinition bone5 = hexadecagon.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(110, 93).addBox(-1.423F, 13.1803F, -3.0196F, 2.0F, 2.0F, 6.0F, cubeDeformation), PartPose.offset(-0.8916F, -0.0299F, 0.0347F));

        bone5.addOrReplaceChild("hexadecagon_r3", CubeListBuilder.create().texOffs(128, 121).addBox(45.377F, -2.9805F, -15.1803F, 2.0F, 6.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(-46.8F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

        bone5.addOrReplaceChild("hexadecagon_r4", CubeListBuilder.create().texOffs(110, 25).addBox(45.377F, 13.1803F, -3.0196F, 2.0F, 2.0F, 6.0F, cubeDeformation), PartPose.offsetAndRotation(-46.8F, 0.015F, 0.0361F, -0.3927F, 0.0F, 0.0F));

        bone5.addOrReplaceChild("hexadecagon_r5", CubeListBuilder.create().texOffs(106, 17).addBox(45.377F, 13.1803F, -3.0195F, 2.0F, 2.0F, 6.0F, cubeDeformation), PartPose.offsetAndRotation(-46.8F, 0.0F, -0.0391F, 0.3927F, 0.0F, 0.0F));

        bone5.addOrReplaceChild("hexadecagon_r6", CubeListBuilder.create().texOffs(52, 128).addBox(45.377F, -2.9804F, 13.3082F, 2.0F, 6.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(-46.8F, -0.0755F, -0.1656F, -0.7854F, 0.0F, 0.0F));

        PartDefinition bone3 = hexadecagon.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(44, 116).addBox(-2.83F, -30.0971F, -24.9049F, 2.0F, 10.0F, 2.0F, cubeDeformation)
                .texOffs(48, 77).addBox(-2.83F, -1.9604F, -4.7683F, 2.0F, 2.0F, 10.0F, cubeDeformation)
                .texOffs(118, 49).addBox(-2.83F, -30.0971F, 23.3684F, 2.0F, 10.0F, 2.0F, cubeDeformation), PartPose.offset(0.5F, 24.2311F, -0.2296F));

        bone3.addOrReplaceChild("hexadecagon_r7", CubeListBuilder.create().texOffs(112, 117).addBox(76.9616F, -4.9674F, 22.1803F, 2.0F, 10.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(-79.7916F, -25.4932F, 1.1029F, 0.3927F, 0.0F, 0.0F));

        bone3.addOrReplaceChild("hexadecagon_r8", CubeListBuilder.create().texOffs(24, 77).addBox(76.9616F, 22.3005F, -5.0326F, 2.0F, 2.0F, 10.0F, cubeDeformation), PartPose.offsetAndRotation(-79.7916F, -24.337F, 0.5818F, 0.3927F, 0.0F, 0.0F));

        bone3.addOrReplaceChild("hexadecagon_r9", CubeListBuilder.create().texOffs(28, 115).addBox(76.9616F, -4.9674F, -24.3005F, 2.0F, 10.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(-79.7916F, -24.5289F, -0.3826F, 0.7854F, 0.0F, 0.0F));

        bone3.addOrReplaceChild("hexadecagon_r10", CubeListBuilder.create().texOffs(120, 117).addBox(76.9616F, -4.9674F, 22.1803F, 2.0F, 10.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(-79.7916F, -24.4439F, 0.9311F, -0.7854F, 0.0F, 0.0F));

        bone3.addOrReplaceChild("hexadecagon_r11", CubeListBuilder.create().texOffs(72, 77).addBox(76.9616F, 22.3005F, -5.0326F, 2.0F, 2.0F, 10.0F, cubeDeformation), PartPose.offsetAndRotation(-79.7916F, -24.3121F, -0.0582F, -0.3927F, 0.0F, 0.0F));

        bone3.addOrReplaceChild("hexadecagon_r12", CubeListBuilder.create().texOffs(118, 61).addBox(76.9616F, -4.9674F, 22.1803F, 2.0F, 10.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(-79.7916F, -24.7612F, 1.1278F, -0.3927F, 0.0F, 0.0F));

        bone3.addOrReplaceChild("hexadecagon_r13", CubeListBuilder.create().texOffs(36, 116).addBox(76.9616F, -4.9674F, -24.3005F, 2.0F, 10.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(-79.7916F, -24.8072F, -0.5533F, 0.3927F, 0.0F, 0.0F));

        bone3.addOrReplaceChild("hexadecagon_r14", CubeListBuilder.create().texOffs(104, 117).addBox(76.9616F, -4.9674F, -24.3005F, 2.0F, 10.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(-79.7916F, -25.7114F, -0.3365F, -0.7854F, 0.0F, 0.0F));

        bone3.addOrReplaceChild("hexadecagon_r15", CubeListBuilder.create().texOffs(52, 116).addBox(76.9616F, -4.9674F, -24.3005F, 2.0F, 10.0F, 2.0F, cubeDeformation), PartPose.offsetAndRotation(-79.7916F, -25.4472F, -0.5283F, -0.3927F, 0.0F, 0.0F));

        PartDefinition hexadecagon3 = ring.addOrReplaceChild("hexadecagon3", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.4F, -24.2311F, 0.2296F, 0.0F, 3.1416F, 0.0F));

        PartDefinition bone25 = hexadecagon3.addOrReplaceChild("bone25", CubeListBuilder.create().texOffs(122, 10).addBox(0.5077F, 9.3587F, -2.6572F, 0.0F, 5.0F, 5.0F, cubeDeformation), PartPose.offset(-0.8846F, -0.0263F, 0.0306F));

        bone25.addOrReplaceChild("hexadecagon_r16", CubeListBuilder.create().texOffs(10, 122).addBox(41.6917F, -2.3428F, -14.3587F, 0.0F, 5.0F, 5.0F, cubeDeformation), PartPose.offsetAndRotation(-41.184F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

        bone25.addOrReplaceChild("hexadecagon_r17", CubeListBuilder.create().texOffs(122, 0).addBox(41.6917F, 9.3587F, -2.6572F, 0.0F, 5.0F, 5.0F, cubeDeformation), PartPose.offsetAndRotation(-41.184F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

        bone25.addOrReplaceChild("hexadecagon_r18", CubeListBuilder.create().texOffs(0, 122).addBox(41.6917F, 9.3587F, -2.6572F, 0.0F, 5.0F, 5.0F, cubeDeformation), PartPose.offsetAndRotation(-41.184F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

        bone25.addOrReplaceChild("hexadecagon_r19", CubeListBuilder.create().texOffs(120, 82).addBox(41.6917F, -2.3428F, 9.7112F, 0.0F, 5.0F, 5.0F, cubeDeformation), PartPose.offsetAndRotation(-41.184F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition bone26 = hexadecagon3.addOrReplaceChild("bone26", CubeListBuilder.create().texOffs(98, 99).addBox(-0.7304F, -25.9209F, -23.0318F, 0.0F, 9.0F, 6.0F, cubeDeformation)
                .texOffs(84, 60).addBox(-0.7304F, -4.0852F, -4.1961F, 0.0F, 6.0F, 9.0F, cubeDeformation)
                .texOffs(102, 39).addBox(-0.7304F, -25.9209F, 17.7513F, 0.0F, 9.0F, 6.0F, cubeDeformation), PartPose.offset(0.34F, 21.3233F, -0.2021F));

        bone26.addOrReplaceChild("hexadecagon_r20", CubeListBuilder.create().texOffs(48, 101).addBox(69.4862F, -4.5713F, 17.5187F, 0.0F, 9.0F, 6.0F, cubeDeformation)
                .texOffs(88, 13).addBox(69.4862F, 17.2644F, -4.4287F, 0.0F, 6.0F, 9.0F, cubeDeformation)
                .texOffs(12, 100).addBox(69.4862F, -4.5713F, -23.2644F, 0.0F, 9.0F, 6.0F, cubeDeformation), PartPose.offsetAndRotation(-70.2166F, -21.3496F, 0.2326F, 0.3927F, 0.0F, 0.0F));

        bone26.addOrReplaceChild("hexadecagon_r21", CubeListBuilder.create().texOffs(36, 101).addBox(69.4862F, -4.5713F, -23.2644F, 0.0F, 9.0F, 6.0F, cubeDeformation), PartPose.offsetAndRotation(-70.2166F, -21.3496F, 0.2326F, 0.7854F, 0.0F, 0.0F));

        bone26.addOrReplaceChild("hexadecagon_r22", CubeListBuilder.create().texOffs(66, 100).addBox(69.4862F, -4.5713F, 17.5187F, 0.0F, 9.0F, 6.0F, cubeDeformation), PartPose.offsetAndRotation(-70.2166F, -21.3496F, 0.2326F, -0.7854F, 0.0F, 0.0F));

        bone26.addOrReplaceChild("hexadecagon_r23", CubeListBuilder.create().texOffs(84, 45).addBox(69.4862F, 17.2644F, -4.4287F, 0.0F, 6.0F, 9.0F, cubeDeformation)
                .texOffs(24, 100).addBox(69.4862F, -4.5713F, 17.5187F, 0.0F, 9.0F, 6.0F, cubeDeformation)
                .texOffs(86, 99).addBox(69.4862F, -4.5713F, -23.2644F, 0.0F, 9.0F, 6.0F, cubeDeformation), PartPose.offsetAndRotation(-70.2166F, -21.3496F, 0.2326F, -0.3927F, 0.0F, 0.0F));

        bone26.addOrReplaceChild("hexadecagon_r24", CubeListBuilder.create().texOffs(0, 100).addBox(69.4862F, -4.5713F, -23.2644F, 0.0F, 9.0F, 6.0F, cubeDeformation), PartPose.offsetAndRotation(-70.2166F, -21.3496F, 0.2326F, -2.3562F, 0.0F, 0.0F));

        PartDefinition hexadecagon2 = ring.addOrReplaceChild("hexadecagon2", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.3F, -24.2311F, 0.2296F, 0.0F, 3.1416F, 0.0F));

        PartDefinition bone7 = hexadecagon2.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(122, 10).addBox(0.5077F, 9.3587F, -2.6572F, 0.0F, 5.0F, 5.0F, cubeDeformation), PartPose.offset(-0.8846F, -0.0263F, 0.0306F));

        bone7.addOrReplaceChild("hexadecagon_r25", CubeListBuilder.create().texOffs(10, 122).addBox(41.6917F, -2.3428F, -14.3587F, 0.0F, 5.0F, 5.0F, cubeDeformation), PartPose.offsetAndRotation(-41.184F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

        bone7.addOrReplaceChild("hexadecagon_r26", CubeListBuilder.create().texOffs(122, 0).addBox(41.6917F, 9.3587F, -2.6572F, 0.0F, 5.0F, 5.0F, cubeDeformation), PartPose.offsetAndRotation(-41.184F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

        bone7.addOrReplaceChild("hexadecagon_r27", CubeListBuilder.create().texOffs(0, 122).addBox(41.6917F, 9.3587F, -2.6572F, 0.0F, 5.0F, 5.0F, cubeDeformation), PartPose.offsetAndRotation(-41.184F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

        bone7.addOrReplaceChild("hexadecagon_r28", CubeListBuilder.create().texOffs(120, 82).addBox(41.6917F, -2.3428F, 9.7112F, 0.0F, 5.0F, 5.0F, cubeDeformation), PartPose.offsetAndRotation(-41.184F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition bone8 = hexadecagon2.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(98, 99).addBox(-0.7304F, -25.9209F, -23.0318F, 0.0F, 9.0F, 6.0F, cubeDeformation)
                .texOffs(84, 60).addBox(-0.7304F, -4.0852F, -4.1961F, 0.0F, 6.0F, 9.0F, cubeDeformation)
                .texOffs(102, 39).addBox(-0.7304F, -25.9209F, 17.7513F, 0.0F, 9.0F, 6.0F, cubeDeformation), PartPose.offset(0.34F, 21.3233F, -0.2021F));

        bone8.addOrReplaceChild("hexadecagon_r29", CubeListBuilder.create().texOffs(48, 101).addBox(69.4862F, -4.5713F, 17.5187F, 0.0F, 9.0F, 6.0F, cubeDeformation)
                .texOffs(88, 13).addBox(69.4862F, 17.2644F, -4.4287F, 0.0F, 6.0F, 9.0F, cubeDeformation)
                .texOffs(12, 100).addBox(69.4862F, -4.5713F, -23.2644F, 0.0F, 9.0F, 6.0F, cubeDeformation), PartPose.offsetAndRotation(-70.2166F, -21.3496F, 0.2326F, 0.3927F, 0.0F, 0.0F));

        bone8.addOrReplaceChild("hexadecagon_r30", CubeListBuilder.create().texOffs(36, 101).addBox(69.4862F, -4.5713F, -23.2644F, 0.0F, 9.0F, 6.0F, cubeDeformation), PartPose.offsetAndRotation(-70.2166F, -21.3496F, 0.2326F, 0.7854F, 0.0F, 0.0F));

        bone8.addOrReplaceChild("hexadecagon_r31", CubeListBuilder.create().texOffs(66, 100).addBox(69.4862F, -4.5713F, 17.5187F, 0.0F, 9.0F, 6.0F, cubeDeformation)
                .texOffs(0, 100).addBox(69.4862F, -4.5713F, -23.2644F, 0.0F, 9.0F, 6.0F, cubeDeformation), PartPose.offsetAndRotation(-70.2166F, -21.3496F, 0.2326F, -0.7854F, 0.0F, 0.0F));

        bone8.addOrReplaceChild("hexadecagon_r32", CubeListBuilder.create().texOffs(84, 45).addBox(69.4862F, 17.2644F, -4.4287F, 0.0F, 6.0F, 9.0F, cubeDeformation)
                .texOffs(24, 100).addBox(69.4862F, -4.5713F, 17.5187F, 0.0F, 9.0F, 6.0F, cubeDeformation)
                .texOffs(86, 99).addBox(69.4862F, -4.5713F, -23.2644F, 0.0F, 9.0F, 6.0F, cubeDeformation), PartPose.offsetAndRotation(-70.2166F, -21.3496F, 0.2326F, -0.3927F, 0.0F, 0.0F));
    }

    private static void sun(PartDefinition partdefinition) {
        CubeDeformation cubeDeformation = new CubeDeformation(-0.4F, 0, 0);

        PartDefinition sun = partdefinition.addOrReplaceChild("sun", CubeListBuilder.create().texOffs(47, 90).addBox(-1.5F, -1.5082F, -7.5F, 1.0F, 3.0F, 8.0F, cubeDeformation)
                .texOffs(101, 53).addBox(-1.5F, -1.5082F, -0.5F, 1.0F, 3.0F, 8.0F, cubeDeformation)
                .texOffs(126, 20).addBox(-1.5F, -0.5F, -1.4918F, 1.0F, 8.0F, 3.0F, cubeDeformation)
                .texOffs(78, 100).addBox(-1.5F, -7.5F, -1.4918F, 1.0F, 8.0F, 3.0F, cubeDeformation), PartPose.offset(-0.8F, -25.0F, 0.5F));

        sun.addOrReplaceChild("hexadecagon_r33", CubeListBuilder.create().texOffs(20, 122).addBox(21.0F, -7.5F, -1.4918F, 1.0F, 8.0F, 3.0F, cubeDeformation)
                .texOffs(126, 20).addBox(21.0F, -0.5F, -1.4918F, 1.0F, 8.0F, 3.0F, cubeDeformation)
                .texOffs(47, 90).addBox(21.0F, -1.5082F, -0.5F, 1.0F, 3.0F, 8.0F, cubeDeformation)
                .texOffs(47, 90).addBox(21.0F, -1.5082F, -7.5F, 1.0F, 3.0F, 8.0F, cubeDeformation), PartPose.offsetAndRotation(-22.5F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

        sun.addOrReplaceChild("hexadecagon_r34", CubeListBuilder.create().texOffs(78, 100).addBox(21.0F, -7.5F, -1.4918F, 1.0F, 8.0F, 3.0F, cubeDeformation)
                .texOffs(126, 92).addBox(21.0F, -0.5F, -1.4918F, 1.0F, 8.0F, 3.0F, cubeDeformation)
                .texOffs(101, 53).addBox(21.0F, -1.5082F, -0.5F, 1.0F, 3.0F, 8.0F, cubeDeformation)
                .texOffs(30, 49).addBox(21.0F, -1.5082F, -7.5F, 1.0F, 3.0F, 8.0F, cubeDeformation), PartPose.offsetAndRotation(-22.5F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

        sun.addOrReplaceChild("hexadecagon_r35", CubeListBuilder.create().texOffs(30, 49).addBox(21.0F, -1.5082F, -0.5F, 1.0F, 3.0F, 8.0F, cubeDeformation)
                .texOffs(3, 67).addBox(21.0F, -1.5082F, -7.5F, 1.0F, 3.0F, 8.0F, cubeDeformation), PartPose.offsetAndRotation(-22.5F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

        sun.addOrReplaceChild("hexadecagon_r36", CubeListBuilder.create().texOffs(47, 90).addBox(21.0F, -1.5082F, -0.5F, 1.0F, 3.0F, 8.0F, cubeDeformation)
                .texOffs(30, 49).addBox(21.0F, -1.5082F, -7.5F, 1.0F, 3.0F, 8.0F, cubeDeformation), PartPose.offsetAndRotation(-22.5F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));
    }
}
