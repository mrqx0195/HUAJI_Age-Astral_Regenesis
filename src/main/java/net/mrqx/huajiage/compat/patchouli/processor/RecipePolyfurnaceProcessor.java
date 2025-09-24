package net.mrqx.huajiage.compat.patchouli.processor;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.recipe.HuaJiPolyfurnaceRecipe;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

@SuppressWarnings("unused")
public class RecipePolyfurnaceProcessor implements IComponentProcessor {
    @SuppressWarnings("NotNullFieldNotInitialized")
    private HuaJiPolyfurnaceRecipe recipe;

    @Override
    public void setup(Level level, IVariableProvider iVariableProvider) {
        String recipeId = iVariableProvider.get("recipe").asString();
        RecipeManager manager = level.getRecipeManager();
        if (manager.byKey(new ResourceLocation(recipeId)).orElseThrow(() -> new IllegalArgumentException("Invalid recipe id:" + recipeId)) instanceof HuaJiPolyfurnaceRecipe huaJiPolyfurnaceRecipe) {
            recipe = huaJiPolyfurnaceRecipe;
        } else {
            throw new IllegalArgumentException("Invalid recipe id:" + recipeId);
        }
    }

    @Override
    public IVariable process(Level level, String key) {
        switch (key) {
            case "item_in" -> {
                return IVariable.from(recipe.getIngredients().get(0).getItems()[0]);
            }
            case "item_out" -> {
                return IVariable.from(recipe.getResultItem(level.registryAccess()));
            }
            case "icount" -> {
                return IVariable.wrap(recipe.getResultItem(level.registryAccess()).getCount());
            }
            case "ipool" -> {
                return IVariable.wrap(Component.translatable("gui.jei.category.huajiage.polyfurnace.pool", recipe.point()).getString());
            }
        }
        HuaJiAgeMod.LOGGER.error("Error while processing HuaJiPolyfurnaceRecipe:", new IllegalArgumentException("Invalid recipe key:" + key));
        return IVariable.empty();
    }
}
