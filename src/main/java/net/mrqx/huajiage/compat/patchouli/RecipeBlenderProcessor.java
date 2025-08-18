package net.mrqx.huajiage.compat.patchouli;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.mrqx.huajiage.recipe.HuaJiBlenderRecipe;
import org.jetbrains.annotations.NotNull;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

@SuppressWarnings("all")
public class RecipeBlenderProcessor implements IComponentProcessor {
    private HuaJiBlenderRecipe recipe;

    @Override
    public void setup(Level level, IVariableProvider iVariableProvider) {
        String recipeId = iVariableProvider.get("recipe").asString();
        RecipeManager manager = level.getRecipeManager();
        if (manager.byKey(new ResourceLocation(recipeId)).orElseThrow(() -> new IllegalArgumentException("Invalid recipe id:" + recipeId)) instanceof HuaJiBlenderRecipe huaJiBlenderRecipe) {
            recipe = huaJiBlenderRecipe;
        } else {
            throw new IllegalArgumentException("Invalid recipe id:" + recipeId);
        }
    }

    @Override
    public @NotNull IVariable process(Level level, String key) {
        switch (key) {
            case "item_in" -> {
                ItemStack[] stacks = recipe.getIngredients().get(0).getItems();
                ItemStack stack = stacks.length == 0 ? ItemStack.EMPTY : stacks[0];
                return IVariable.from(stack);
            }
            case "item_out" -> {
                return IVariable.from(recipe.getResultItem(level.registryAccess()));
            }
            case "icount" -> {
                return IVariable.wrap(recipe.getResultItem(level.registryAccess()).getCount());
            }
            case "iname" -> {
                return IVariable.wrap(recipe.getResultItem(level.registryAccess()).getHoverName().getString());
            }
        }
        throw new IllegalArgumentException("Invalid recipe key:" + key);
    }
}
