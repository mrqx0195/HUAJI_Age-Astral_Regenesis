package net.mrqx.huajiage.compat.jei.category;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.block.blockentity.HuaJiPolyfurnaceBlockEntity;
import net.mrqx.huajiage.compat.jei.DrawableComponent;
import net.mrqx.huajiage.compat.jei.HuajiAgeJeiPlugin;
import net.mrqx.huajiage.recipe.HuaJiPolyfurnaceRecipe;
import net.mrqx.huajiage.registy.HuaJiBlocks;

public class HuaJiPolyfurnaceCategory extends AbstractRecipeCategory<HuaJiPolyfurnaceRecipe> {
    private final IDrawableStatic background;
    private final IDrawableAnimated animatedFlame;
    private final IDrawableAnimated arrow;
    private final IDrawableAnimated pool;
    private final DrawableComponent poolText;
    private final DrawableComponent pointText;
    protected static final ResourceLocation TEXTURE = HuaJiAgeMod.prefix("textures/jei/gui_huaji_polyfurnace.png");

    @SuppressWarnings("DataFlowIssue")
    public HuaJiPolyfurnaceCategory(IGuiHelper guiHelper) {
        super(
                HuajiAgeJeiPlugin.POLYFURNACE_JEI_TYPE,
                HuaJiBlocks.HUAJI_POLYFURNACE.get().getName(),
                guiHelper.createDrawableItemLike(HuaJiBlocks.HUAJI_POLYFURNACE.get()),
                109,
                56
        );
        background = guiHelper.createDrawable(TEXTURE, 0, 0, 109, 56);
        IDrawableStatic staticFlame = guiHelper.createDrawable(TEXTURE, 233, 5, 6, 31);
        animatedFlame = guiHelper.createAnimatedDrawable(staticFlame, 200, IDrawableAnimated.StartDirection.TOP, true);
        arrow = guiHelper.drawableBuilder(TEXTURE, 2, 62, 37, 1).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
        pool = guiHelper.drawableBuilder(TEXTURE, 202, 6, 23, 19).buildAnimated(500, IDrawableAnimated.StartDirection.BOTTOM, false);
        poolText = new DrawableComponent(Component.translatable("gui.jei.category.huajiage.polyfurnace.pool"), ChatFormatting.AQUA.getColor());
        pointText = new DrawableComponent(Component.translatable("gui.jei.category.huajiage.polyfurnace.full"), ChatFormatting.GRAY.getColor());
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, HuaJiPolyfurnaceRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 18, 19).setSlotName("polyfurnace_input").addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 78, 19).setSlotName("polyfurnace_output").addItemStack(recipe.getResultItem());
    }

    @Override
    public void draw(HuaJiPolyfurnaceRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        background.draw(guiGraphics, 0, 0);
        animatedFlame.draw(guiGraphics, 7, 8);
        arrow.draw(guiGraphics, 39, 27);
        pool.draw(guiGraphics, 77, 13);
        poolText.draw(guiGraphics, (109 - poolText.getWidth()) / 2, 41, Component.translatable("gui.jei.category.huajiage.polyfurnace.pool", recipe.point()));
        pointText.draw(guiGraphics, 101 - pointText.getWidth(), 3, Component.translatable("gui.jei.category.huajiage.polyfurnace.full", HuaJiPolyfurnaceBlockEntity.TOTAL_POINT));
    }
}
