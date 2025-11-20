package net.mrqx.huajiage.compat.patchouli.processor;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.recipe.HuaJiBlenderRecipe;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

@SuppressWarnings("unused")
public class RecipeBlenderProcessor implements IComponentProcessor {
    @SuppressWarnings("NotNullFieldNotInitialized")
    private HuaJiBlenderRecipe recipe;

    @Override
    public void setup(Level level, IVariableProvider iVariableProvider) {
        String recipeId = iVariableProvider.get("recipe").asString();
        RecipeManager manager = level.getRecipeManager();
        if (manager.byKey(ResourceLocation.parse(recipeId)).orElseThrow(() -> new IllegalArgumentException("Invalid recipe id:" + recipeId)) instanceof HuaJiBlenderRecipe huaJiBlenderRecipe) {
            recipe = huaJiBlenderRecipe;
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
            default -> {
                HuaJiAgeMod.LOGGER.error("Error while processing HuaJiBlenderRecipe:", new IllegalArgumentException("Invalid recipe key:" + key));
                return IVariable.empty();
            }
        }
    }
}
