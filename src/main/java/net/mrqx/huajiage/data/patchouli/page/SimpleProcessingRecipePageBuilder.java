package net.mrqx.huajiage.data.patchouli.page;

import com.reimnop.pgen.builder.page.PGenSmeltingPageBuilder;
import net.minecraft.resources.ResourceLocation;

public class SimpleProcessingRecipePageBuilder extends PGenSmeltingPageBuilder {
    private final String type;

    public SimpleProcessingRecipePageBuilder(String type, String modId, ResourceLocation recipe) {
        super(modId, recipe);
        this.type = type;
    }

    @Override
    public SimpleProcessingRecipePage build() {
        return new SimpleProcessingRecipePage(type, recipe, recipe2, text, title, advancement, flag, anchor);
    }
}
