package net.mrqx.huajiage.data.patchouli.page;

import com.reimnop.pgen.builder.page.PGenPageBuilder;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class HuaJiPolyFurnacePageBuilder extends PGenPageBuilder<HuaJiPolyFurnacePageBuilder> {
    public ResourceLocation recipe;

    @Nullable
    public ResourceLocation recipe2;

    public HuaJiPolyFurnacePageBuilder(String modId, ResourceLocation recipe) {
        super(modId);
        this.recipe = recipe;
    }

    public HuaJiPolyFurnacePageBuilder withRecipe(ResourceLocation recipe) {
        this.recipe = recipe;
        return this;
    }

    public HuaJiPolyFurnacePageBuilder withRecipe(String recipe) {
        return withRecipe(ResourceLocation.fromNamespaceAndPath(modId, recipe));
    }

    public HuaJiPolyFurnacePageBuilder withRecipe2(ResourceLocation recipe2) {
        this.recipe2 = recipe2;
        return this;
    }

    public HuaJiPolyFurnacePageBuilder withRecipe2(String recipe2) {
        return withRecipe2(ResourceLocation.fromNamespaceAndPath(modId, recipe2));
    }

    public HuaJiPolyFurnacePage build() {
        return new HuaJiPolyFurnacePage(recipe, recipe2, advancement, flag, anchor);
    }
}
