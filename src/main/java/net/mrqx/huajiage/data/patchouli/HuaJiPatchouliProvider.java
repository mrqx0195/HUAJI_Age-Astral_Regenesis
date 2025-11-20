package net.mrqx.huajiage.data.patchouli;

import com.reimnop.pgen.PGenBookProvider;
import com.reimnop.pgen.builder.PGenLangBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.data.patchouli.category.CategoryCosmicChronicles;
import net.mrqx.huajiage.data.patchouli.category.CategoryHuaJiItems;
import net.mrqx.huajiage.data.patchouli.category.CategoryInfiniteStarryRiver;
import net.mrqx.huajiage.data.patchouli.category.CategoryStand;
import net.mrqx.huajiage.registy.HuaJiCreativeTabs;
import net.mrqx.huajiage.registy.HuaJiItems;

import java.util.concurrent.CompletableFuture;

public class HuaJiPatchouliProvider extends PGenBookProvider {
    public HuaJiPatchouliProvider(CompletableFuture<HolderLookup.Provider> lookupProvider, PackOutput packOutput) {
        super(HuaJiAgeMod.MODID, lookupProvider, packOutput);
    }

    @Override
    protected void generate(HolderLookup.Provider provider) {
        this.addBook("jo_book", "patchouli.huajiage.book.name", "patchouli.huajiage.book.landing", true,
                builder -> builder.withCreativeTab(HuaJiCreativeTabs.HUAJI_GROUP.getId())
                        .withModel(HuaJiAgeMod.prefix("book_huaji"))
                        .withShowProgress(true)
                        .withVersion("1919810")
                        .addLanguage("en_us", HuaJiPatchouliProvider::enUsLangBuilder)
                        .addLanguage("zh_cn", HuaJiPatchouliProvider::zhCnLangBuilder));
    }

    public static void enUsLangBuilder(PGenLangBuilder builder) {
        builder.addCategory(CategoryCosmicChronicles.ID, "Cosmic Chronicles",
                "It all began 1,145,141,919,810,000,000 years ago with the Kichiku War...$(br2)Disclaimer: This section's content is generated via trans-temporal resonance reconstruction technology. Any similarities are likely intentional (lol.",
                ForgeRegistries.ITEMS.getKey(Items.WRITABLE_BOOK), CategoryCosmicChronicles::enUsCategoryBuilder);
        CategoryCosmicChronicles.enUsEntryBuilder(builder);
        builder.addCategory(CategoryCosmicChronicles.CategoryKichikuAllStarCodex.ID, "Kichiku All-Star Codex",
                "Choose Your Hero$(br2)Disclaimer: This section's content is generated via trans-temporal resonance reconstruction technology. Any similarities are likely intentional (lol.",
                ForgeRegistries.ITEMS.getKey(Items.WRITTEN_BOOK), CategoryCosmicChronicles.CategoryKichikuAllStarCodex::enUsCategoryBuilder);
        CategoryCosmicChronicles.CategoryKichikuAllStarCodex.enUsEntryBuilder(builder);


        builder.addCategory(CategoryHuaJiItems.ID, "HUAJI Items",
                "What are you looking at? Get in here!",
                HuaJiItems.HUAJI.getId(), CategoryHuaJiItems::enUsCategoryBuilder);
        CategoryHuaJiItems.enUsEntryBuilder(builder);
        builder.addCategory(CategoryHuaJiItems.CategoryMaterials.ID, "Materials",
                "Aahhh... It's all materials, materials!",
                HuaJiItems.HUAJI_INGOT.getId(), CategoryHuaJiItems.CategoryMaterials::enUsCategoryBuilder);
        CategoryHuaJiItems.CategoryMaterials.enUsEntryBuilder(builder);
        builder.addCategory(CategoryHuaJiItems.CategoryFoods.ID, "Foods",
                "This Steve isn't eating properly. How about we...",
                HuaJiItems.EGG_RICE.getId(), CategoryHuaJiItems.CategoryFoods::enUsCategoryBuilder);
        CategoryHuaJiItems.CategoryFoods.enUsEntryBuilder(builder);
        builder.addCategory(CategoryHuaJiItems.CategoryWeaponsAndEquipment.ID, "Weapons & Equipment",
                "White Emperor Holy Sword, China's number one sword! White Emperor Holy Sword, fly following me!",
                HuaJiItems.HUAJI_STAR_SWORD.getId(), CategoryHuaJiItems.CategoryWeaponsAndEquipment::enUsCategoryBuilder);
        CategoryHuaJiItems.CategoryWeaponsAndEquipment.enUsEntryBuilder(builder);


        builder.addCategory(CategoryStand.ID, "Stands",
                "Stand POWER!!!",
                HuaJiItems.ARROW_STAND.getId(), CategoryStand::enUsCategoryBuilder);
        CategoryStand.enUsEntryBuilder(builder);


        builder.addCategory(CategoryInfiniteStarryRiver.ID, "The Infinite Starry River",
                "It is absolutely not Avaritia mod.",
                HuaJiItems.INFINITE_UNIVERSE_STAR.getId(), CategoryInfiniteStarryRiver::enUsCategoryBuilder);
        CategoryInfiniteStarryRiver.enUsEntryBuilder(builder);
    }

    public static void zhCnLangBuilder(PGenLangBuilder builder) {
        builder.addCategory(CategoryCosmicChronicles.ID, "宇宙往事",
                "这一切都要从114514亿亿亿年前的鬼畜战争说起……$(br2)免责声明：本节内容由超时空脑补技术生成。如有雷同，可能是有意为之（雾",
                ForgeRegistries.ITEMS.getKey(Items.WRITABLE_BOOK), CategoryCosmicChronicles::zhCnCategoryBuilder);
        CategoryCosmicChronicles.zhCnEntryBuilder(builder);
        builder.addCategory(CategoryCosmicChronicles.CategoryKichikuAllStarCodex.ID, "鬼畜全明星图鉴",
                "选择你的英雄$(br2)免责声明：本节内容由超时空脑补技术生成。如有雷同，可能是有意为之（雾",
                ForgeRegistries.ITEMS.getKey(Items.WRITTEN_BOOK), CategoryCosmicChronicles.CategoryKichikuAllStarCodex::zhCnCategoryBuilder);
        CategoryCosmicChronicles.CategoryKichikuAllStarCodex.zhCnEntryBuilder(builder);


        builder.addCategory(CategoryHuaJiItems.ID, "滑稽物品",
                "你瞅啥？还不快进来！",
                HuaJiItems.HUAJI.getId(), CategoryHuaJiItems::zhCnCategoryBuilder);
        CategoryHuaJiItems.zhCnEntryBuilder(builder);
        builder.addCategory(CategoryHuaJiItems.CategoryMaterials.ID, "材料",
                "啊啊啊……都是素材啊，素材啊！",
                HuaJiItems.HUAJI_INGOT.getId(), CategoryHuaJiItems.CategoryMaterials::zhCnCategoryBuilder);
        CategoryHuaJiItems.CategoryMaterials.zhCnEntryBuilder(builder);
        builder.addCategory(CategoryHuaJiItems.CategoryFoods.ID, "食物",
                "这个史蒂夫不好好吃东西，不如我们把它……",
                HuaJiItems.EGG_RICE.getId(), CategoryHuaJiItems.CategoryFoods::zhCnCategoryBuilder);
        CategoryHuaJiItems.CategoryFoods.zhCnEntryBuilder(builder);
        builder.addCategory(CategoryHuaJiItems.CategoryWeaponsAndEquipment.ID, "武器与装备",
                "白帝圣剑，华夏第一剑！白帝圣剑，御剑跟着我！",
                HuaJiItems.HUAJI_STAR_SWORD.getId(), CategoryHuaJiItems.CategoryWeaponsAndEquipment::zhCnCategoryBuilder);
        CategoryHuaJiItems.CategoryWeaponsAndEquipment.zhCnEntryBuilder(builder);


        builder.addCategory(CategoryStand.ID, "替身",
                "Stand POWER!!!",
                HuaJiItems.ARROW_STAND.getId(), CategoryStand::zhCnCategoryBuilder);
        CategoryStand.zhCnEntryBuilder(builder);


        builder.addCategory(CategoryInfiniteStarryRiver.ID, "无尽星河",
                "绝对不是无尽贪婪",
                HuaJiItems.INFINITE_UNIVERSE_STAR.getId(), CategoryInfiniteStarryRiver::zhCnCategoryBuilder);
        CategoryInfiniteStarryRiver.zhCnEntryBuilder(builder);
    }
}
