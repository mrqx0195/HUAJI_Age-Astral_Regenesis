package net.mrqx.huajiage.data.patchouli.page;

import com.reimnop.pgen.builder.page.PGenPageBuilder;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class HuaJiBlenderPageBuilder extends PGenPageBuilder<HuaJiBlenderPageBuilder> {
    public ResourceLocation recipe;

    @Nullable
    public ResourceLocation recipe2;

    public HuaJiBlenderPageBuilder(String modId, ResourceLocation recipe) {
        super(modId);
        this.recipe = recipe;
    }

    public HuaJiBlenderPageBuilder withRecipe(ResourceLocation recipe) {
        this.recipe = recipe;
        return this;
    }

    public HuaJiBlenderPageBuilder withRecipe(String recipe) {
        return withRecipe(ResourceLocation.fromNamespaceAndPath(modId, recipe));
    }

    public HuaJiBlenderPageBuilder withRecipe2(ResourceLocation recipe2) {
        this.recipe2 = recipe2;
        return this;
    }

    public HuaJiBlenderPageBuilder withRecipe2(String recipe2) {
        return withRecipe2(ResourceLocation.fromNamespaceAndPath(modId, recipe2));
    }

    public HuaJiBlenderPage build() {
        return new HuaJiBlenderPage(recipe, recipe2, advancement, flag, anchor);
    }
}
