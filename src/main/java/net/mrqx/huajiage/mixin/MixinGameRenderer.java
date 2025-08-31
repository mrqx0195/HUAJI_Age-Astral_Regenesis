package net.mrqx.huajiage.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.GameRenderer;
import net.mrqx.huajiage.client.renderer.shader.HuaJiShaderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class MixinGameRenderer {
    @Inject(method = "renderLevel", at = @At("TAIL"))
    public void injectRenderLevel(float partialTicks, long finishTimeNano, PoseStack poseStack, CallbackInfo ci) {
        HuaJiShaderManager.renderShaders(partialTicks);
    }

    @Inject(method = "resize", at = @At("HEAD"))
    public void injectResize(int width, int height, CallbackInfo callbackInfo) {
        HuaJiShaderManager.POST_CHAINS.values().forEach(postChain -> postChain.resize(width, height));
    }
}
