package net.mrqx.huajiage.recipe;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.mrqx.huajiage.registy.HuaJiRecipes;

public record HuaJiBlenderRecipe(RecipeType<?> type, ResourceLocation id, String group, Ingredient ingredient,
                                 ItemStack result, float experience, int processTime) implements Recipe<Container> {

    public HuaJiBlenderRecipe(ResourceLocation pId, String pGroup, Ingredient pIngredient, ItemStack pResult, float pExperience, int processTime) {
        this(HuaJiRecipes.HUAJI_BLENDER_RECIPE_TYPE.get(), pId, pGroup, pIngredient, pResult, pExperience, processTime);
    }

    @Override
    public boolean matches(Container pInv, Level pLevel) {
        return this.ingredient.test(pInv.getItem(0));
    }

    @Override
    public ItemStack assemble(Container pContainer, RegistryAccess pRegistryAccess) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(this.ingredient);
        return nonnulllist;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return this.result;
    }

    public ItemStack getResultItem() {
        return this.result;
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return HuaJiRecipes.HUAJI_BLENDER_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return this.type;
    }
}
