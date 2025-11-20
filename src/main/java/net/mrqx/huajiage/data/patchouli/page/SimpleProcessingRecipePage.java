package net.mrqx.huajiage.data.patchouli.page;

import com.reimnop.pgen.data.page.PGenSmeltingPage;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class SimpleProcessingRecipePage extends PGenSmeltingPage {
    private final String type;

    public SimpleProcessingRecipePage(String type,
                                      ResourceLocation recipe,
                                      @Nullable ResourceLocation recipe2,
                                      @Nullable String text,
                                      @Nullable String title,
                                      @Nullable ResourceLocation advancement,
                                      @Nullable String flag,
                                      @Nullable String anchor) {
        super(recipe, recipe2, text, title, advancement, flag, anchor);
        this.type = type;
    }

    @Override
    protected ResourceLocation getType() {
        return patchouliPageType(type);
    }
}
