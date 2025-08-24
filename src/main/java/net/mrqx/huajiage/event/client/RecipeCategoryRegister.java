package net.mrqx.huajiage.event.client;

import net.minecraft.client.RecipeBookCategories;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterRecipeBookCategoriesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.registy.HuaJiRecipes;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RecipeCategoryRegister {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onRegisterRecipeBookCategoriesEvent(RegisterRecipeBookCategoriesEvent event) {
        event.registerRecipeCategoryFinder(HuaJiRecipes.HUAJI_BLENDER_RECIPE_TYPE.get(), recipe -> RecipeBookCategories.create("huaji_blender", HuaJiItems.HUAJI_BLENDER.get().getDefaultInstance()));
        event.registerRecipeCategoryFinder(HuaJiRecipes.HUAJI_POLYFURNACE_RECIPE_TYPE.get(), recipe -> RecipeBookCategories.create("huaji_blender", HuaJiItems.HUAJI_POLYFURNACE.get().getDefaultInstance()));
    }
}
