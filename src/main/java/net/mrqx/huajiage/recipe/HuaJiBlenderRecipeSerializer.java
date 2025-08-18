package net.mrqx.huajiage.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class HuaJiBlenderRecipeSerializer implements RecipeSerializer<HuaJiBlenderRecipe> {
    private final int defaultProcessTime;
    private final CookieBaker<HuaJiBlenderRecipe> factory;

    public HuaJiBlenderRecipeSerializer(CookieBaker<HuaJiBlenderRecipe> pFactory, int pDefaultProcessTime) {
        this.factory = pFactory;
        this.defaultProcessTime = pDefaultProcessTime;
    }

    public HuaJiBlenderRecipeSerializer() {
        this(HuaJiBlenderRecipe::new, 100);
    }

    @Override
    public @NotNull HuaJiBlenderRecipe fromJson(@NotNull ResourceLocation pRecipeId, @NotNull JsonObject pJson) {
        String s = GsonHelper.getAsString(pJson, "group", "");
        JsonElement jsonelement = GsonHelper.isArrayNode(pJson, "ingredient") ? GsonHelper.getAsJsonArray(pJson, "ingredient") : GsonHelper.getAsJsonObject(pJson, "ingredient");
        Ingredient ingredient = Ingredient.fromJson(jsonelement, false);
        if (!pJson.has("result")) {
            throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
        }
        ItemStack itemstack;
        if (pJson.get("result").isJsonObject()) {
            itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pJson, "result"));
        } else {
            String s1 = GsonHelper.getAsString(pJson, "result");
            ResourceLocation resourcelocation = new ResourceLocation(s1);
            itemstack = new ItemStack(ForgeRegistries.ITEMS.getDelegate(resourcelocation).orElseThrow(() -> new IllegalStateException("Item: " + s1 + " does not exist")));
        }
        float f = GsonHelper.getAsFloat(pJson, "experience", 0.0F);
        int i = GsonHelper.getAsInt(pJson, "processTime", this.defaultProcessTime);
        return this.factory.create(pRecipeId, s, ingredient, itemstack, f, i);
    }

    @Override
    public HuaJiBlenderRecipe fromNetwork(@NotNull ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
        String s = pBuffer.readUtf();
        Ingredient ingredient = Ingredient.fromNetwork(pBuffer);
        ItemStack itemstack = pBuffer.readItem();
        float f = pBuffer.readFloat();
        int i = pBuffer.readVarInt();
        return this.factory.create(pRecipeId, s, ingredient, itemstack, f, i);
    }

    @Override
    public void toNetwork(FriendlyByteBuf pBuffer, HuaJiBlenderRecipe pRecipe) {
        pBuffer.writeUtf(pRecipe.group);
        pRecipe.ingredient.toNetwork(pBuffer);
        pBuffer.writeItem(pRecipe.result);
        pBuffer.writeFloat(pRecipe.experience);
        pBuffer.writeVarInt(pRecipe.processTime);
    }

    public interface CookieBaker<T extends HuaJiBlenderRecipe> {
        T create(ResourceLocation pId, String pGroup, Ingredient pIngredient, ItemStack pResult, float pExperience, int processTime);
    }
}
