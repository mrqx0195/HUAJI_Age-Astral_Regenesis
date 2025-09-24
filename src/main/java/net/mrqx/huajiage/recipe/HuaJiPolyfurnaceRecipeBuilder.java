package net.mrqx.huajiage.recipe;

import com.google.gson.JsonObject;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.registy.HuaJiRecipes;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class HuaJiPolyfurnaceRecipeBuilder implements RecipeBuilder {
    private final Ingredient ingredient;
    private final float experience;
    private final int processTime;
    private final int point;
    @Nullable
    private String group;
    private final HuaJiPolyfurnaceRecipeSerializer serializer;

    public HuaJiPolyfurnaceRecipeBuilder(Ingredient ingredient, float experience, int processTime, int point) {
        this.ingredient = ingredient;
        this.experience = experience;
        this.processTime = processTime;
        this.point = point;
        this.serializer = HuaJiRecipes.HUAJI_POLYFURNACE_RECIPE_SERIALIZER.get();
    }

    public static HuaJiPolyfurnaceRecipeBuilder create(Ingredient ingredient, float experience, int processTime, int point) {
        return new HuaJiPolyfurnaceRecipeBuilder(ingredient, experience, processTime, point);
    }

    @Override
    public HuaJiPolyfurnaceRecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
        return this;
    }

    @Override
    public HuaJiPolyfurnaceRecipeBuilder group(@Nullable String pGroupName) {
        this.group = pGroupName;
        return this;
    }

    @Override
    public Item getResult() {
        return HuaJiItems.INFINITE_UNIVERSE_STAR.get();
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        this.save(pFinishedRecipeConsumer, getDefaultRecipeId(this.ingredient.getItems()[0].getItem()));
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        pFinishedRecipeConsumer.accept(new HuaJiPolyfurnaceRecipeBuilder.Result(pRecipeId, this.group == null ? "" : this.group, this.ingredient, this.experience, this.processTime, this.point, this.serializer));
    }

    @SuppressWarnings("deprecation")
    protected static ResourceLocation getDefaultRecipeId(ItemLike pItemLike) {
        return BuiltInRegistries.ITEM.getKey(pItemLike.asItem()).withPrefix("huaji_polyfurnace/");
    }

    record Result(ResourceLocation id, String group, Ingredient ingredient, float experience, int processTime,
                  int point, HuaJiPolyfurnaceRecipeSerializer serializer) implements FinishedRecipe {

        @Override
            public void serializeRecipeData(JsonObject pJson) {
                if (!this.group.isEmpty()) {
                    pJson.addProperty("group", this.group);
                }
                pJson.add("ingredient", this.ingredient.toJson());
                pJson.addProperty("experience", this.experience);
                pJson.addProperty("processTime", this.processTime);
                pJson.addProperty("point", this.point);
            }

            @Override
            public RecipeSerializer<?> getType() {
                return this.serializer;
            }

            @Override
            public ResourceLocation getId() {
                return this.id;
            }

            @Override
            @Nullable
            public JsonObject serializeAdvancement() {
                return null;
            }

            @Override
            @Nullable
            public ResourceLocation getAdvancementId() {
                return null;
            }
        }
}
