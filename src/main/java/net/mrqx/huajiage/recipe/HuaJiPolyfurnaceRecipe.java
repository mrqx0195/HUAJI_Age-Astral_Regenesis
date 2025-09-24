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
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.registy.HuaJiRecipes;

public record HuaJiPolyfurnaceRecipe(RecipeType<?> type, ResourceLocation id, String group, Ingredient ingredient,
                                     float experience, int processTime, int point) implements Recipe<Container> {

    public HuaJiPolyfurnaceRecipe(ResourceLocation pId, String pGroup, Ingredient pIngredient, float pExperience, int processTime, int point) {
        this(HuaJiRecipes.HUAJI_POLYFURNACE_RECIPE_TYPE.get(), pId, pGroup, pIngredient, pExperience, processTime, point);
    }

    @Override
    public boolean matches(Container pInv, Level pLevel) {
        return this.ingredient.test(pInv.getItem(0));
    }

    @Override
    public ItemStack assemble(Container pContainer, RegistryAccess pRegistryAccess) {
        return HuaJiItems.INFINITE_UNIVERSE_STAR.get().getDefaultInstance();
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
        return HuaJiItems.INFINITE_UNIVERSE_STAR.get().getDefaultInstance();
    }

    public ItemStack getResultItem() {
        return HuaJiItems.INFINITE_UNIVERSE_STAR.get().getDefaultInstance();
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
