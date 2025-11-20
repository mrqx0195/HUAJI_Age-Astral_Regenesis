package net.mrqx.huajiage.data.patchouli.page;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.reimnop.pgen.data.page.PGenPage;
import net.minecraft.resources.ResourceLocation;
import net.mrqx.huajiage.HuaJiAgeMod;
import org.jetbrains.annotations.Nullable;

public class HuaJiBlenderPage extends PGenPage {
    public final ResourceLocation recipe;

    @Nullable
    public final ResourceLocation recipe2;

    public HuaJiBlenderPage(
            ResourceLocation recipe,
            @Nullable ResourceLocation recipe2,
            @Nullable ResourceLocation advancement,
            @Nullable String flag,
            @Nullable String anchor) {
        super(advancement, flag, anchor);
        this.recipe = recipe;
        this.recipe2 = recipe2;
    }

    @Override
    protected ResourceLocation getType() {
        return HuaJiAgeMod.prefix("blender");
    }

    @Override
    protected void writeToJson(JsonObject obj) {
        obj.add("r1", new JsonPrimitive(recipe.toString()));
        if (recipe2 != null) {
            obj.add("r2", new JsonPrimitive(recipe2.toString()));
        }
    }
}
