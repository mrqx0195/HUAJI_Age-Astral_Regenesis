package net.mrqx.huajiage.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
public class HuaJiKeyMappings {
    public static final KeyMapping KEY_CHANGE_MODE = new KeyMapping("key.huajiage.change_mode",
            KeyConflictContext.IN_GAME, KeyModifier.NONE, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_K,
            "key.category.huajiage");
}
