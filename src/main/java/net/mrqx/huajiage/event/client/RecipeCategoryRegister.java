package net.mrqx.huajiage.event.client;

import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RegisterRecipeBookCategoriesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.registy.HuaJiRecipes;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RecipeCategoryRegister {
    @SubscribeEvent
    public static void onRegisterRecipeBookCategoriesEvent(RegisterRecipeBookCategoriesEvent event) {
        event.registerRecipeCategoryFinder(HuaJiRecipes.HUAJI_BLENDER_RECIPE_TYPE.get(), recipe -> RecipeBookCategories.create("huaji_blender", new ItemStack(HuaJiItems.HUAJI_BLENDER.get())));
    }
}
