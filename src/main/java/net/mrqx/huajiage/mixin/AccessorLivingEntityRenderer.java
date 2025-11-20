package net.mrqx.huajiage.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
@Mixin(LivingEntityRenderer.class)
public interface AccessorLivingEntityRenderer<T extends LivingEntity, M extends EntityModel<T>> {
    @Accessor("layers")
    List<RenderLayer<T, M>> getLayers();

    @Invoker("setupRotations")
    void huaJiAgeInvokeSetupRotations(T entityLiving, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks);

    @Invoker("scale")
    void huaJiAgeInvokeScale(T livingEntity, PoseStack poseStack, float partialTickTime);
}
