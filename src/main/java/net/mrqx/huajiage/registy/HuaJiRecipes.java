package net.mrqx.huajiage.registy;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.recipe.HuaJiBlenderRecipe;
import net.mrqx.huajiage.recipe.HuaJiBlenderRecipeSerializer;
import net.mrqx.huajiage.recipe.HuaJiPolyfurnaceRecipe;
import net.mrqx.huajiage.recipe.HuaJiPolyfurnaceRecipeSerializer;

public class HuaJiRecipes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister
            .create(ForgeRegistries.RECIPE_TYPES, HuaJiAgeMod.MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister
            .create(ForgeRegistries.RECIPE_SERIALIZERS, HuaJiAgeMod.MODID);


    public static final RegistryObject<RecipeType<HuaJiBlenderRecipe>> HUAJI_BLENDER_RECIPE_TYPE = RECIPE_TYPES
            .register("huaji_blender", () -> recipeType("huaji_blender"));

    public static final RegistryObject<HuaJiBlenderRecipeSerializer> HUAJI_BLENDER_RECIPE_SERIALIZER = RECIPE_SERIALIZER
            .register("huaji_blender", HuaJiBlenderRecipeSerializer::new);
    
    public static final RegistryObject<RecipeType<HuaJiPolyfurnaceRecipe>> HUAJI_POLYFURNACE_RECIPE_TYPE = RECIPE_TYPES
            .register("huaji_polyfurnace", () -> recipeType("huaji_polyfurnace"));

    public static final RegistryObject<HuaJiPolyfurnaceRecipeSerializer> HUAJI_POLYFURNACE_RECIPE_SERIALIZER = RECIPE_SERIALIZER
            .register("huaji_polyfurnace", HuaJiPolyfurnaceRecipeSerializer::new);


    private static <T extends Recipe<?>> RecipeType<T> recipeType(String name) {
        return new RecipeType<>() {
            @Override
            public String toString() {
                return new ResourceLocation(HuaJiAgeMod.MODID, name).toString();
            }
        };
    }
}
