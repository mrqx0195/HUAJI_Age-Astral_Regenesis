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
import net.mrqx.huajiage.registy.HuaJiRecipes;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class HuaJiBlenderRecipeBuilder implements RecipeBuilder {
    private final Item result;
    private final Ingredient ingredient;
    private final float experience;
    private final int processTime;
    @Nullable
    private String group;
    private final @NotNull HuaJiBlenderRecipeSerializer serializer;

    public HuaJiBlenderRecipeBuilder(ItemLike itemLike, Ingredient ingredient, float experience, int processTime) {
        this.result = itemLike.asItem();
        this.ingredient = ingredient;
        this.experience = experience;
        this.processTime = processTime;
        this.serializer = HuaJiRecipes.HUAJI_BLENDER_RECIPE_SERIALIZER.get();
    }

    public static HuaJiBlenderRecipeBuilder create(ItemLike itemLike, Ingredient ingredient, float experience, int processTime) {
        return new HuaJiBlenderRecipeBuilder(itemLike, ingredient, experience, processTime);
    }

    @Override
    public @NotNull HuaJiBlenderRecipeBuilder unlockedBy(@NotNull String pCriterionName, @NotNull CriterionTriggerInstance pCriterionTrigger) {
        return this;
    }

    @Override
    public @NotNull HuaJiBlenderRecipeBuilder group(@Nullable String pGroupName) {
        this.group = pGroupName;
        return this;
    }

    @Override
    public @NotNull Item getResult() {
        return this.result;
    }

    @Override
    public void save(@NotNull Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        this.save(pFinishedRecipeConsumer, getDefaultRecipeId(this.getResult()));
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, @NotNull ResourceLocation pRecipeId) {
        pFinishedRecipeConsumer.accept(new HuaJiBlenderRecipeBuilder.Result(pRecipeId, this.group == null ? "" : this.group, this.ingredient, this.result, this.experience, this.processTime, this.serializer));
    }

    @SuppressWarnings("deprecation")
    protected static ResourceLocation getDefaultRecipeId(ItemLike pItemLike) {
        return BuiltInRegistries.ITEM.getKey(pItemLike.asItem()).withPrefix("huaji_blender/");
    }

    static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final String group;
        private final Ingredient ingredient;
        private final Item result;
        private final float experience;
        private final int processTime;
        private final HuaJiBlenderRecipeSerializer serializer;

        public Result(ResourceLocation resourceLocation, String group, Ingredient ingredient, Item result, float experience, int processTime, @NotNull HuaJiBlenderRecipeSerializer serializer) {
            this.id = resourceLocation;
            this.group = group;
            this.ingredient = ingredient;
            this.result = result;
            this.experience = experience;
            this.processTime = processTime;
            this.serializer = serializer;
        }

        @Override
        @SuppressWarnings("deprecation")
        public void serializeRecipeData(@NotNull JsonObject pJson) {
            if (!this.group.isEmpty()) {
                pJson.addProperty("group", this.group);
            }
            pJson.add("ingredient", this.ingredient.toJson());
            pJson.addProperty("result", BuiltInRegistries.ITEM.getKey(this.result).toString());
            pJson.addProperty("experience", this.experience);
            pJson.addProperty("processTime", this.processTime);
        }

        @Override
        public @NotNull RecipeSerializer<?> getType() {
            return this.serializer;
        }

        @Override
        public @NotNull ResourceLocation getId() {
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
