package net.mrqx.huajiage.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;
import java.util.Map;

@Mixin(ModelPart.class)
public interface AccessorModelPart {
    @Accessor("children")
    Map<String, ModelPart> getChildren();

    @Accessor("cubes")
    List<ModelPart.Cube> getCubes();

    @Invoker("compile")
    void huaJiAgeInvokeCompile(PoseStack.Pose pPose, VertexConsumer pVertexConsumer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha);
}
