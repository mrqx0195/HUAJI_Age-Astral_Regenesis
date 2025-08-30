package net.mrqx.huajiage.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class HuaJiPolyfurnaceRecipeSerializer implements RecipeSerializer<HuaJiPolyfurnaceRecipe> {
    private final int defaultProcessTime;
    private final int defaultPoint;
    private final CookieBaker<HuaJiPolyfurnaceRecipe> factory;

    public HuaJiPolyfurnaceRecipeSerializer(CookieBaker<HuaJiPolyfurnaceRecipe> pFactory, int pDefaultProcessTime, int defaultPoint) {
        this.factory = pFactory;
        this.defaultProcessTime = pDefaultProcessTime;
        this.defaultPoint = defaultPoint;
    }

    public HuaJiPolyfurnaceRecipeSerializer() {
        this(HuaJiPolyfurnaceRecipe::new, 100, 1);
    }

    @Override
    public HuaJiPolyfurnaceRecipe fromJson(ResourceLocation pRecipeId, JsonObject pJson) {
        String s = GsonHelper.getAsString(pJson, "group", "");
        JsonElement jsonelement = GsonHelper.isArrayNode(pJson, "ingredient") ? GsonHelper.getAsJsonArray(pJson, "ingredient") : GsonHelper.getAsJsonObject(pJson, "ingredient");
        Ingredient ingredient = Ingredient.fromJson(jsonelement, false);
        float f = GsonHelper.getAsFloat(pJson, "experience", 0.0F);
        int i = GsonHelper.getAsInt(pJson, "processTime", this.defaultProcessTime);
        int p = GsonHelper.getAsInt(pJson, "point", this.defaultPoint);
        return this.factory.create(pRecipeId, s, ingredient, f, i, p);
    }

    @Override
    public HuaJiPolyfurnaceRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
        String s = pBuffer.readUtf();
        Ingredient ingredient = Ingredient.fromNetwork(pBuffer);
        float f = pBuffer.readFloat();
        int i = pBuffer.readVarInt();
        int p = pBuffer.readVarInt();
        return this.factory.create(pRecipeId, s, ingredient, f, i, p);
    }

    @Override
    public void toNetwork(FriendlyByteBuf pBuffer, HuaJiPolyfurnaceRecipe pRecipe) {
        pBuffer.writeUtf(pRecipe.group);
        pRecipe.ingredient.toNetwork(pBuffer);
        pBuffer.writeFloat(pRecipe.experience);
        pBuffer.writeVarInt(pRecipe.processTime);
        pBuffer.writeVarInt(pRecipe.processTime);
    }

    public interface CookieBaker<T extends HuaJiPolyfurnaceRecipe> {
        T create(ResourceLocation pId, String pGroup, Ingredient pIngredient, float pExperience, int processTime, int point);
    }
}
