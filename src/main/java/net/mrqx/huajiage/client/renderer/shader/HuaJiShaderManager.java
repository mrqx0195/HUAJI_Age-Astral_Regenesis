package net.mrqx.huajiage.client.renderer.shader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.HashMap;

@OnlyIn(Dist.CLIENT)
public class HuaJiShaderManager {
    public static HashMap<ResourceLocation, PostChain> postChains = new HashMap<>();

    public static void renderShaders(float partialTicks) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.getProfiler().push("huaji_shaders");
        postChains.values().forEach(postChain -> {
            postChain.resize(minecraft.getWindow().getWidth(), minecraft.getWindow().getHeight());
            postChain.process(partialTicks);
            minecraft.getMainRenderTarget().bindWrite(false);
        });
        minecraft.getProfiler().pop();
    }

    public static void removeShader(ResourceLocation resourceLocation) {
        if (postChains.containsKey(resourceLocation)) {
            PostChain remove = postChains.remove(resourceLocation);
            remove.close();
        }
    }
}
