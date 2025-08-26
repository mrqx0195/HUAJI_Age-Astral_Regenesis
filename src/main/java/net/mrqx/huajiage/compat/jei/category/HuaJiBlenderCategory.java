package net.mrqx.huajiage.compat.jei.category;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.compat.jei.HuajiAgeJeiPlugin;
import net.mrqx.huajiage.recipe.HuaJiBlenderRecipe;
import net.mrqx.huajiage.registy.HuaJiBlocks;
import org.jetbrains.annotations.NotNull;

public class HuaJiBlenderCategory extends AbstractRecipeCategory<HuaJiBlenderRecipe> {
    private final IDrawableStatic background;
    private final IDrawableAnimated animatedFlame;
    private final IDrawableAnimated arrow;
    protected static final ResourceLocation TEXTURE = HuaJiAgeMod.prefix("textures/jei/gui_huaji_blender.png");

    public HuaJiBlenderCategory(IGuiHelper guiHelper) {
        super(
                HuajiAgeJeiPlugin.BLENDER_JEI_TYPE,
                HuaJiBlocks.HUAJI_BLENDER.get().getName(),
                guiHelper.createDrawableItemLike(HuaJiBlocks.HUAJI_BLENDER.get()),
                106,
                55
        );
        background = guiHelper.createDrawable(TEXTURE, 0, 0, 106, 55);
        IDrawableStatic staticFlame = guiHelper.createDrawable(TEXTURE, 37, 156, 2, 18);
        animatedFlame = guiHelper.createAnimatedDrawable(staticFlame, 200, IDrawableAnimated.StartDirection.TOP, true);
        arrow = guiHelper.drawableBuilder(TEXTURE, 0, 156, 30, 17).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull HuaJiBlenderRecipe recipe, @NotNull IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 18, 19).setSlotName("blender_input").addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 72, 19).setSlotName("blender_output").addItemStack(recipe.getResultItem());
    }

    @Override
    public void draw(@NotNull HuaJiBlenderRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView, @NotNull GuiGraphics guiGraphics, double mouseX, double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        background.draw(guiGraphics, 0, 0);
        animatedFlame.draw(guiGraphics, 13, 18);
        arrow.draw(guiGraphics, 38, 18);
    }
}
