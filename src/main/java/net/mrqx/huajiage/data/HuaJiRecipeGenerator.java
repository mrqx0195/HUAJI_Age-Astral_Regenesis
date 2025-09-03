package net.mrqx.huajiage.data;

import com.doggystudio.chirencqr.ltc.server.registry.LTCItems;
import com.google.common.collect.ImmutableList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.recipe.HuaJiBlenderRecipeBuilder;
import net.mrqx.huajiage.recipe.HuaJiPolyfurnaceRecipeBuilder;
import net.mrqx.huajiage.registy.HuaJiItems;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings("SameParameterValue")
public class HuaJiRecipeGenerator extends RecipeProvider {
    public HuaJiRecipeGenerator(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        vanillaRecipes(pWriter);
        blenderRecipes(pWriter);
        polyfurnaceRecipes(pWriter);
    }

    private static void blenderRecipes(Consumer<FinishedRecipe> pWriter) {
        HuaJiBlenderRecipeBuilder.create(HuaJiItems.HUAJI.get(), Ingredient.of(HuaJiItems.ANTI_HUAJI.get()), 1, 100).save(pWriter);
        HuaJiBlenderRecipeBuilder.create(HuaJiItems.ANTI_HUAJI.get(), Ingredient.of(HuaJiItems.HUAJI.get()), 1, 100).save(pWriter);
        HuaJiBlenderRecipeBuilder.create(HuaJiItems.HUAJI_INGOT.get(), Ingredient.of(Tags.Items.STORAGE_BLOCKS_GOLD), 1, 100).save(pWriter);
        HuaJiBlenderRecipeBuilder.create(HuaJiItems.NEUTRON_STAR_FRAGMENT.get(), Ingredient.of(HuaJiItems.HUAJI_STAR.get()), 1, 100).save(pWriter);
        HuaJiBlenderRecipeBuilder.create(HuaJiItems.REDSTONE_DRUSE.get(), Ingredient.of(Tags.Items.STORAGE_BLOCKS_REDSTONE), 1, 100).save(pWriter);
        HuaJiBlenderRecipeBuilder.create(HuaJiItems.FLASH_FLOUR.get(), Ingredient.of(Items.HAY_BLOCK), 1, 100).save(pWriter);
        HuaJiBlenderRecipeBuilder.create(HuaJiItems.HOPE_ELEMENT.get(), Ingredient.of(Items.CHORUS_FLOWER), 1, 100).save(pWriter);
        HuaJiBlenderRecipeBuilder.create(HuaJiItems.WAVE_CRYSTAL.get(), Ingredient.of(Items.SEA_LANTERN), 1, 100).save(pWriter);
    }

    private static void polyfurnaceRecipes(Consumer<FinishedRecipe> pWriter) {
        HuaJiPolyfurnaceRecipeBuilder.create(Ingredient.of(HuaJiItems.HUAJI_STAR.get()), 10, 200, 1).save(pWriter);
        HuaJiPolyfurnaceRecipeBuilder.create(Ingredient.of(HuaJiItems.HUAJI_STAR_BLOCK.get()), 90, 200, 9).save(pWriter);
        HuaJiPolyfurnaceRecipeBuilder.create(Ingredient.of(HuaJiItems.AIRSPACE_STAR.get()), 730, 200, 73).save(pWriter);
        HuaJiPolyfurnaceRecipeBuilder.create(Ingredient.of(HuaJiItems.AIRSPACE_STAR_BLOCK.get()), 6570, 200, 657).save(pWriter);
    }

    private static void vanillaRecipes(Consumer<FinishedRecipe> pWriter) {
        oreSmelting(pWriter, ImmutableList.of(HuaJiItems.HUAJI_FRAGMENT.get()), RecipeCategory.MISC, HuaJiItems.HUAJI.get(), 1.0F, 200, "huaji");
        oreBlasting(pWriter, ImmutableList.of(HuaJiItems.HUAJI_FRAGMENT.get()), RecipeCategory.MISC, HuaJiItems.HUAJI.get(), 1.0F, 200, "huaji");
        oreSmelting(pWriter, ImmutableList.of(HuaJiItems.HUAJI_ORE.get()), RecipeCategory.MISC, HuaJiItems.HUAJI.get(), 1.0F, 200, "huaji");
        oreBlasting(pWriter, ImmutableList.of(HuaJiItems.HUAJI_ORE.get()), RecipeCategory.MISC, HuaJiItems.HUAJI.get(), 1.0F, 200, "huaji");

        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, HuaJiItems.HUAJI_STAR.get(), RecipeCategory.MISC, HuaJiItems.HUAJI_STAR_BLOCK.get());
        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, HuaJiItems.AIRSPACE_STAR.get(), RecipeCategory.MISC, HuaJiItems.AIRSPACE_STAR_BLOCK.get());

        stonecutterResultFromBase(pWriter, RecipeCategory.FOOD, HuaJiItems.RAW_GLUTEN.get(), HuaJiItems.FLASH_FLOUR.get(), 2);
        simpleCookingRecipe(pWriter, "smoking", RecipeSerializer.SMOKING_RECIPE, 2333, HuaJiItems.RAW_GLUTEN.get(), HuaJiItems.BAKED_GLUTEN.get(), 0.35F);
        simpleCookingRecipe(pWriter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, 2333, HuaJiItems.RAW_GLUTEN.get(), HuaJiItems.BAKED_GLUTEN.get(), 0.35F);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HuaJiItems.HUAJI_STAR.get())
                .pattern(" J ")
                .pattern("JSJ")
                .pattern(" J ")
                .define('S', Items.NETHER_STAR)
                .define('J', HuaJiItems.HUAJI.get())
                .unlockedBy("has_huaji", has(HuaJiItems.HUAJI.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HuaJiItems.AIRSPACE_STAR.get())
                .pattern("JJJ")
                .pattern("JSJ")
                .pattern("JJJ")
                .define('S', HuaJiItems.HUAJI_STAR.get())
                .define('J', HuaJiItems.HUAJI_STAR_BLOCK.get())
                .unlockedBy("has_huaji_star", has(HuaJiItems.HUAJI_STAR.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, HuaJiItems.EGG_RICE.get())
                .pattern("HBM")
                .pattern("JRJ")
                .pattern("EOE")
                .define('H', Items.COOKED_CHICKEN)
                .define('B', Items.COOKED_BEEF)
                .define('M', Items.COOKED_MUTTON)
                .define('R', Items.COOKED_RABBIT)
                .define('J', HuaJiItems.HUAJI.get())
                .define('E', Items.EGG)
                .define('O', Items.BOWL)
                .unlockedBy("has_huaji", has(HuaJiItems.HUAJI.get())).save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HuaJiItems.ULTIMATE_EGG_RICE.get())
                .requires(HuaJiItems.EGG_RICE.get())
                .requires(HuaJiItems.HUAJI_STAR.get())
                .requires(HuaJiItems.BAKED_GLUTEN.get())
                .requires(HuaJiItems.WAVE_CRYSTAL.get())
                .requires(HuaJiItems.HOPE_FLOWER.get())
                .requires(Items.DIAMOND_BLOCK)
                .unlockedBy("has_huaji", has(HuaJiItems.HUAJI.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, HuaJiItems.HUAJI_BOMB.get())
                .pattern("PPP")
                .pattern("PJP")
                .pattern("PPP")
                .define('P', Items.GUNPOWDER)
                .define('J', HuaJiItems.HUAJI.get())
                .unlockedBy("has_huaji", has(HuaJiItems.HUAJI.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, HuaJiItems.HUAJI_ANTIMATTER_BOMB.get())
                .pattern("PPP")
                .pattern("PJP")
                .pattern("PPP")
                .define('P', HuaJiItems.ANTI_HUAJI.get())
                .define('J', HuaJiItems.HUAJI_BOMB.get())
                .unlockedBy("has_huaji_bomb", has(HuaJiItems.HUAJI_BOMB.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HuaJiItems.HUAJI_BLENDER.get())
                .pattern("OJO")
                .pattern("JBJ")
                .pattern("OJO")
                .define('O', Items.OBSIDIAN)
                .define('J', HuaJiItems.HUAJI.get())
                .define('B', Items.BEACON)
                .unlockedBy("has_huaji", has(HuaJiItems.HUAJI.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HuaJiItems.ETHER_POINT.get())
                .pattern(" J ")
                .pattern("JSJ")
                .pattern(" J ")
                .define('S', HuaJiItems.HUAJI_STAR.get())
                .define('J', HuaJiItems.REDSTONE_DRUSE.get())
                .unlockedBy("has_huaji_star", has(HuaJiItems.HUAJI_STAR.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HuaJiItems.ETHER_CIRCUMFLUX_BOARD.get())
                .pattern("ENE")
                .pattern(" N ")
                .pattern("ENE")
                .define('E', HuaJiItems.NEUTRON_STAR_FRAGMENT.get())
                .define('N', HuaJiItems.ETHER_POINT.get())
                .unlockedBy("has_ether_point", has(HuaJiItems.ETHER_POINT.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HuaJiItems.HOPE_FLOWER.get())
                .pattern("PPP")
                .pattern("PDP")
                .pattern("PPP")
                .define('P', HuaJiItems.HOPE_ELEMENT.get())
                .define('D', Items.DIAMOND)
                .unlockedBy("has_hope_element", has(HuaJiItems.HOPE_ELEMENT.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HuaJiItems.EXGLUTENBUR.get())
                .pattern("NGN")
                .pattern("NGN")
                .pattern(" B ")
                .define('G', HuaJiItems.BAKED_GLUTEN.get())
                .define('N', HuaJiItems.NEUTRON_STAR_FRAGMENT.get())
                .define('B', Items.BLAZE_ROD)
                .unlockedBy("has_baked_gluten", has(HuaJiItems.BAKED_GLUTEN.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HuaJiItems.HUAJI_HELMET.get())
                .pattern("HHH")
                .pattern("H H")
                .define('H', HuaJiItems.HUAJI_INGOT.get())
                .unlockedBy("has_huaji_ingot", has(HuaJiItems.HUAJI_INGOT.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HuaJiItems.HUAJI_CHESTPLATE.get())
                .pattern("H H")
                .pattern("HDH")
                .pattern("HHH")
                .define('H', HuaJiItems.HUAJI_INGOT.get())
                .define('D', Items.DIAMOND)
                .unlockedBy("has_huaji_ingot", has(HuaJiItems.HUAJI_INGOT.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HuaJiItems.HUAJI_LEGGINGS.get())
                .pattern("HDH")
                .pattern("H H")
                .pattern("H H")
                .define('H', HuaJiItems.HUAJI_INGOT.get())
                .define('D', Items.DIAMOND)
                .unlockedBy("has_huaji_ingot", has(HuaJiItems.HUAJI_INGOT.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HuaJiItems.HUAJI_BOOTS.get())
                .pattern("D D")
                .pattern("H H")
                .pattern("H H")
                .define('H', HuaJiItems.HUAJI_INGOT.get())
                .define('D', Items.DIAMOND)
                .unlockedBy("has_huaji_ingot", has(HuaJiItems.HUAJI_INGOT.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HuaJiItems.ORGA_HELMET.get())
                .pattern("NFN")
                .pattern("FHF")
                .define('H', HuaJiItems.HUAJI_HELMET.get())
                .define('F', HuaJiItems.HOPE_FLOWER.get())
                .define('N', HuaJiItems.NEUTRON_STAR_FRAGMENT.get())
                .unlockedBy("has_hope_flower", has(HuaJiItems.HOPE_FLOWER.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HuaJiItems.ORGA_CHESTPLATE.get())
                .pattern("N N")
                .pattern("FHF")
                .pattern("FFF")
                .define('H', HuaJiItems.HUAJI_HELMET.get())
                .define('F', HuaJiItems.HOPE_FLOWER.get())
                .define('N', HuaJiItems.NEUTRON_STAR_FRAGMENT.get())
                .unlockedBy("has_hope_flower", has(HuaJiItems.HOPE_FLOWER.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HuaJiItems.ORGA_LEGGINGS.get())
                .pattern("NHN")
                .pattern("F F")
                .pattern("F F")
                .define('H', HuaJiItems.HUAJI_HELMET.get())
                .define('F', HuaJiItems.HOPE_FLOWER.get())
                .define('N', HuaJiItems.NEUTRON_STAR_FRAGMENT.get())
                .unlockedBy("has_hope_flower", has(HuaJiItems.HOPE_FLOWER.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HuaJiItems.ORGA_BOOTS.get())
                .pattern("FHF")
                .pattern("N N")
                .define('H', HuaJiItems.HUAJI_HELMET.get())
                .define('F', HuaJiItems.HOPE_FLOWER.get())
                .define('N', HuaJiItems.NEUTRON_STAR_FRAGMENT.get())
                .unlockedBy("has_hope_flower", has(HuaJiItems.HOPE_FLOWER.get())).save(pWriter);

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(HuaJiItems.ETHER_CIRCUMFLUX_BOARD.get()), Ingredient.of(HuaJiItems.HUAJI_HELMET.get()),
                        Ingredient.of(HuaJiItems.NEUTRON_STAR_FRAGMENT.get()), RecipeCategory.COMBAT, HuaJiItems.FIFTY_FIFTY_HELMET.get())
                .unlocks("has_ether_circumflux_board", has(HuaJiItems.ETHER_CIRCUMFLUX_BOARD.get())).save(pWriter, HuaJiAgeMod.prefix(getItemName(HuaJiItems.FIFTY_FIFTY_HELMET.get()) + "_smithing"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HuaJiItems.HERO_BOW.get())
                .pattern(" NE")
                .pattern("NS ")
                .pattern("E  ")
                .define('S', HuaJiItems.AIRSPACE_STAR.get())
                .define('N', HuaJiItems.NEUTRON_STAR_FRAGMENT.get())
                .define('E', HuaJiItems.ETHER_POINT.get())
                .unlockedBy("has_airspace_star", has(HuaJiItems.AIRSPACE_STAR.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HuaJiItems.LORD_CORE.get())
                .pattern(" ES")
                .pattern("EAE")
                .pattern(" E ")
                .define('S', HuaJiItems.HUAJI_STAR.get())
                .define('A', HuaJiItems.AIRSPACE_STAR.get())
                .define('E', HuaJiItems.ETHER_CIRCUMFLUX_BOARD.get())
                .unlockedBy("has_airspace_star", has(HuaJiItems.AIRSPACE_STAR.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HuaJiItems.LORD_KEY.get())
                .pattern("FFF")
                .pattern("NSN")
                .pattern(" N ")
                .define('S', HuaJiItems.INFINITE_UNIVERSE_STAR.get())
                .define('N', HuaJiItems.NEUTRON_STAR_FRAGMENT.get())
                .define('F', HuaJiItems.HOPE_FLOWER.get())
                .unlockedBy("has_airspace_star", has(HuaJiItems.INFINITE_UNIVERSE_STAR.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HuaJiItems.TAROT.get())
                .pattern("OJO")
                .pattern("JBJ")
                .pattern("OJO")
                .define('O', Tags.Items.STORAGE_BLOCKS_LAPIS)
                .define('J', Items.OBSIDIAN)
                .define('B', HuaJiItems.ETHER_POINT.get())
                .unlockedBy("has_ether_point", has(HuaJiItems.ETHER_POINT.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, HuaJiItems.REO_CHERRY.get())
                .pattern("OJO")
                .pattern("JBJ")
                .pattern("OJO")
                .define('O', Tags.Items.GEMS_EMERALD)
                .define('J', HuaJiItems.HUAJI.get())
                .define('B', Items.APPLE)
                .unlockedBy("has_apple", has(Items.APPLE)).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HuaJiItems.INFINITE_CHARM.get())
                .pattern("OJO")
                .pattern("JBJ")
                .pattern("OJO")
                .define('O', HuaJiItems.HOPE_FLOWER.get())
                .define('J', HuaJiItems.NEUTRON_STAR_FRAGMENT.get())
                .define('B', HuaJiItems.INFINITE_UNIVERSE_STAR.get())
                .unlockedBy("has_infinite_universe_star", has(HuaJiItems.INFINITE_UNIVERSE_STAR.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HuaJiItems.SINGULARITY.get())
                .pattern("OAO")
                .pattern("DBC")
                .pattern("OEO")
                .define('O', HuaJiItems.DISC.get())
                .define('A', HuaJiItems.EXGLUTENBUR.get())
                .define('B', HuaJiItems.INFINITE_UNIVERSE_STAR.get())
                .define('C', HuaJiItems.ULTIMATE_EGG_RICE.get())
                .define('D', HuaJiItems.LORD_KEY.get())
                .define('E', HuaJiItems.WAVE_CRYSTAL.get())
                .unlockedBy("has_infinite_universe_star", has(HuaJiItems.INFINITE_UNIVERSE_STAR.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HuaJiItems.HUAJI_POLYFURNACE.get())
                .pattern("OOO")
                .pattern("ABA")
                .pattern("JJJ")
                .define('O', HuaJiItems.ETHER_CIRCUMFLUX_BOARD.get())
                .define('J', HuaJiItems.HUAJI_STAR_BLOCK.get())
                .define('B', HuaJiItems.HUAJI_BLENDER.get())
                .define('A', HuaJiItems.HOPE_FLOWER.get())
                .unlockedBy("has_huaji_blender", has(HuaJiItems.HUAJI_BLENDER.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, HuaJiItems.DIO_BREAD.get())
                .pattern("OAO")
                .pattern("ABA")
                .pattern("OCO")
                .define('O', HuaJiItems.HUAJI_INGOT.get())
                .define('A', Items.CLOCK)
                .define('B', HuaJiItems.ETHER_CIRCUMFLUX_BOARD.get())
                .define('C', Items.BREAD)
                .unlockedBy("has_ether_circumflux_board", has(HuaJiItems.ETHER_CIRCUMFLUX_BOARD.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HuaJiItems.HUAJI_SWORD.get())
                .pattern(" H ")
                .pattern(" H ")
                .pattern(" D ")
                .define('H', HuaJiItems.HUAJI_INGOT.get())
                .define('D', Items.DIAMOND)
                .unlockedBy("has_huaji_ingot", has(HuaJiItems.HUAJI_INGOT.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HuaJiItems.HUAJI_STAR_SWORD.get())
                .pattern(" N ")
                .pattern("NHN")
                .pattern(" D ")
                .define('H', HuaJiItems.HUAJI_SWORD.get())
                .define('N', HuaJiItems.NEUTRON_STAR_FRAGMENT.get())
                .define('D', Items.DIAMOND)
                .unlockedBy("has_huaji_sword", has(HuaJiItems.HUAJI_SWORD.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HuaJiItems.HUAJI_LATIAO_SWORD.get())
                .pattern(" N ")
                .pattern("NHN")
                .pattern(" D ")
                .define('H', LTCItems.HOTAURUM_SWORD.get())
                .define('N', HuaJiItems.NEUTRON_STAR_FRAGMENT.get())
                .define('D', Items.DIAMOND)
                .unlockedBy("has_huaji_sword", has(HuaJiItems.HUAJI_SWORD.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HuaJiItems.ARROW_STAND.get())
                .pattern("NNR")
                .pattern("NT ")
                .pattern("R H")
                .define('T', HuaJiItems.TAROT.get())
                .define('N', HuaJiItems.NEUTRON_STAR_FRAGMENT.get())
                .define('H', HuaJiItems.HOPE_FLOWER.get())
                .define('R', HuaJiItems.REDSTONE_DRUSE.get())
                .unlockedBy("has_tarot", has(HuaJiItems.TAROT.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HuaJiItems.ARROW_REQUIEM.get())
                .pattern("NNR")
                .pattern("NS ")
                .pattern("R A")
                .define('S', HuaJiItems.SINGULARITY.get())
                .define('N', HuaJiItems.HOPE_FLOWER.get())
                .define('R', HuaJiItems.DISC.get())
                .define('A', HuaJiItems.ARROW_STAND.get())
                .unlockedBy("has_arrow_stand", has(HuaJiItems.ARROW_STAND.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HuaJiItems.MULTI_KNIFE.get())
                .pattern(" N ")
                .pattern(" H ")
                .pattern(" D ")
                .define('N', HuaJiItems.NEUTRON_STAR_FRAGMENT.get())
                .define('H', HuaJiItems.HOPE_FLOWER.get())
                .define('D', HuaJiItems.HUAJI_INGOT.get())
                .unlockedBy("has_huaji_ingot", has(HuaJiItems.HUAJI_INGOT.get())).save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, HuaJiItems.MULTI_KNIFE_SHINY.get())
                .pattern("ADA")
                .pattern("LHL")
                .pattern("ADA")
                .define('A', HuaJiItems.BAKED_GLUTEN.get())
                .define('H', HuaJiItems.MULTI_KNIFE.get())
                .define('D', HuaJiItems.HUAJI_INGOT.get())
                .define('L', Items.LAVA_BUCKET)
                .unlockedBy("has_huaji_ingot", has(HuaJiItems.HUAJI_INGOT.get())).save(pWriter);
    }


    protected static void oreSmelting(Consumer<FinishedRecipe> finishedRecipeConsumer, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group) {
        oreCooking(finishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, ingredients, category, result, experience, cookingTime, group, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> finishedRecipeConsumer, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group) {
        oreCooking(finishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, ingredients, category, result, experience, cookingTime, group, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> finishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> cookingSerializer, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group, String recipeName) {
        for (ItemLike itemlike : ingredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), category, result, experience, cookingTime, cookingSerializer).group(group).unlockedBy(getHasName(itemlike), has(itemlike)).save(finishedRecipeConsumer, HuaJiAgeMod.prefix(getItemName(result) + recipeName + "_" + getItemName(itemlike)));
        }
    }

    protected static void nineBlockStorageRecipes(Consumer<FinishedRecipe> finishedRecipeConsumer, RecipeCategory unpackedCategory, ItemLike unpacked, RecipeCategory packedCategory, ItemLike packed) {
        nineBlockStorageRecipes(finishedRecipeConsumer, unpackedCategory, unpacked, packedCategory, packed, getSimpleRecipeName(packed) + "_packed", null, getSimpleRecipeName(unpacked) + "_unpacked", null);
    }

    protected static void nineBlockStorageRecipes(Consumer<FinishedRecipe> finishedRecipeConsumer, RecipeCategory unpackedCategory, ItemLike unpacked, RecipeCategory packedCategory, ItemLike packed, String packedName, @Nullable String packedGroup, String unpackedName, @Nullable String unpackedGroup) {
        ShapelessRecipeBuilder.shapeless(unpackedCategory, unpacked, 9).requires(packed).group(unpackedGroup).unlockedBy(getHasName(packed), has(packed)).save(finishedRecipeConsumer, HuaJiAgeMod.prefix(unpackedName));
        ShapedRecipeBuilder.shaped(packedCategory, packed).define('#', unpacked).pattern("###").pattern("###").pattern("###").group(packedGroup).unlockedBy(getHasName(unpacked), has(unpacked)).save(finishedRecipeConsumer, HuaJiAgeMod.prefix(packedName));
    }

    protected static void stonecutterResultFromBase(Consumer<FinishedRecipe> finishedRecipeConsumer, RecipeCategory category, ItemLike result, ItemLike material, int resultCount) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(material), category, result, resultCount).unlockedBy(getHasName(material), has(material)).save(finishedRecipeConsumer, HuaJiAgeMod.prefix(getConversionRecipeName(result, material) + "_stonecutting"));
    }

    protected static void simpleCookingRecipe(Consumer<FinishedRecipe> finishedRecipeConsumer, String cookingMethod, RecipeSerializer<? extends AbstractCookingRecipe> cookingSerializer, int cookingTime, ItemLike ingredient, ItemLike result, float experience) {
        SimpleCookingRecipeBuilder.generic(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, cookingTime, cookingSerializer).unlockedBy(getHasName(ingredient), has(ingredient)).save(finishedRecipeConsumer, HuaJiAgeMod.prefix(getItemName(result) + "_from_" + cookingMethod));
    }
}
