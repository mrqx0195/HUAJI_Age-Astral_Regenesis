package net.mrqx.huajiage.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.block.inventory.HuaJiBlenderMenu;
import net.mrqx.huajiage.block.inventory.HuaJiPolyfurnaceMenu;
import net.mrqx.huajiage.client.screen.HuaJiBlenderScreen;
import net.mrqx.huajiage.client.screen.HuaJiPolyfurnaceScreen;
import net.mrqx.huajiage.compat.jei.category.HuaJiBlenderCategory;
import net.mrqx.huajiage.compat.jei.category.HuaJiPolyfurnaceCategory;
import net.mrqx.huajiage.recipe.HuaJiBlenderRecipe;
import net.mrqx.huajiage.recipe.HuaJiPolyfurnaceRecipe;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.registy.HuaJiMenus;
import net.mrqx.huajiage.registy.HuaJiRecipes;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@JeiPlugin
public class HuajiAgeJeiPlugin implements IModPlugin {
    public static final mezz.jei.api.recipe.RecipeType<HuaJiBlenderRecipe> BLENDER_JEI_TYPE = mezz.jei.api.recipe.RecipeType
            .create(HuaJiAgeMod.MODID, "huaji_blender", HuaJiBlenderRecipe.class);
    public static final mezz.jei.api.recipe.RecipeType<HuaJiPolyfurnaceRecipe> POLYFURNACE_JEI_TYPE = mezz.jei.api.recipe.RecipeType
            .create(HuaJiAgeMod.MODID, "huaji_polyfurnace", HuaJiPolyfurnaceRecipe.class);

    private static final Minecraft MINECRAFT = Minecraft.getInstance();

    /**
     * This method is adapted from The-Last-Smith-Resharpened by 0999312.
     * <p>
     * Original source: <a href="https://github.com/0999312/The-Last-Smith-Resharpened/blob/main/src/main/java/cn/mmf/tls/compat/jei/JEICompat.java">0999312/The-Last-Smith-Resharpened/.../JEICompat.java</a>
     * <p>
     * License: <a href="https://github.com/0999312/The-Last-Smith-Resharpened/blob/main/LICENSE">MIT License</a>
     *
     * @author 0999312
     */
    private static <C extends Container, T extends Recipe<C>> List<T> findRecipesByType(RecipeType<T> type) {
        if (MINECRAFT.level != null) {
            return MINECRAFT.level.getRecipeManager().getAllRecipesFor(type);
        }
        return List.of();
    }

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return HuaJiAgeMod.prefix(HuaJiAgeMod.MODID + "_jei_plugin");
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        registration.addRecipes(BLENDER_JEI_TYPE, findRecipesByType(HuaJiRecipes.HUAJI_BLENDER_RECIPE_TYPE.get()));
        registration.addRecipes(POLYFURNACE_JEI_TYPE, findRecipesByType(HuaJiRecipes.HUAJI_POLYFURNACE_RECIPE_TYPE.get()));
    }

    @Override
    public void registerGuiHandlers(@NotNull IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(HuaJiBlenderScreen.class, 77, 31, 25, 12, BLENDER_JEI_TYPE);
        // TODO: HuaJiPolyfurnaceScreen
        registration.addRecipeClickArea(HuaJiPolyfurnaceScreen.class, 62, 26, 86, 15, POLYFURNACE_JEI_TYPE);
    }

    @Override
    public void registerRecipeCatalysts(@NotNull IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(HuaJiItems.HUAJI_BLENDER.get(), BLENDER_JEI_TYPE);
        registration.addRecipeCatalyst(HuaJiItems.HUAJI_POLYFURNACE.get(), POLYFURNACE_JEI_TYPE);
    }

    @Override
    public void registerRecipeTransferHandlers(@NotNull IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(HuaJiBlenderMenu.class, HuaJiMenus.HUAJI_BLENDER.get(), BLENDER_JEI_TYPE,
                HuaJiBlenderMenu.INPUT_SLOT_INDEX, HuaJiBlenderMenu.INPUT_SLOTS_COUNT,
                HuaJiBlenderMenu.FURNACE_SLOTS_COUNT, HuaJiBlenderMenu.VANILLA_SLOT_COUNT);
        registration.addRecipeTransferHandler(HuaJiPolyfurnaceMenu.class, HuaJiMenus.HUAJI_POLYFURNACE.get(), POLYFURNACE_JEI_TYPE,
                HuaJiPolyfurnaceMenu.INPUT_SLOT_INDEX, HuaJiPolyfurnaceMenu.INPUT_SLOTS_COUNT,
                HuaJiPolyfurnaceMenu.FURNACE_SLOTS_COUNT, HuaJiPolyfurnaceMenu.VANILLA_SLOT_COUNT);
    }

    @Override
    public void registerCategories(@NotNull IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new HuaJiBlenderCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new HuaJiPolyfurnaceCategory(registration.getJeiHelpers().getGuiHelper()));
    }
}
