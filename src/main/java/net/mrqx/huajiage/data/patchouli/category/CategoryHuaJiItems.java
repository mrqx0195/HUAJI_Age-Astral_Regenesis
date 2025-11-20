package net.mrqx.huajiage.data.patchouli.category;

import com.reimnop.pgen.builder.PGenCategoryBuilder;
import com.reimnop.pgen.builder.PGenLangBuilder;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.data.patchouli.page.HuaJiBlenderPageBuilder;
import net.mrqx.huajiage.data.patchouli.page.SimpleProcessingRecipePageBuilder;
import net.mrqx.huajiage.registy.HuaJiItems;

public class CategoryHuaJiItems {
    public static final String ID = "huaji_items";

    public static void enUsCategoryBuilder(PGenCategoryBuilder builder) {
        builder.withSortnum(1);
    }

    public static void zhCnCategoryBuilder(PGenCategoryBuilder builder) {
        builder.withSortnum(1);
    }

    public static void enUsEntryBuilder(PGenLangBuilder builder) {
        builder.addEntry(ID + "/" + "huaji", "HUAJI", HuaJiItems.HUAJI.getId(),
                HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0).withPriority(true)
                        .addTextPage("$(l:https://zh.moegirl.org.cn/滑稽)HUAJI$()—one of the fundamental sources of the world, a crystallization of Kichiku elements. This substance is distributed among all things in the universe, albeit in very low concentrations, making direct extraction difficult. Its high-concentration crystals are only found deep in the earth's crust.", pageBuilder -> {
                        })
                        .addSpotlightPage(itemBuilder -> itemBuilder.addItem(HuaJiItems.HUAJI_FRAGMENT.getId()).addItem(HuaJiItems.HUAJI_ORE.getId()),
                                pageBuilder -> pageBuilder.withText("Stone fragments rich in HUAJI, accumulated deep underground. Due to the high boiling point characteristic of HUAJI in high concentrations, it can be directly heated to extract HUAJI. The latest \"Tieba Geology\" studies suggest that these HUAJI crystal ore deposits seem to have been formed from HUAJI-rich seawater and groundwater through hundreds of millions of years of subterranean deposition and transformation."))
                        .addSmeltingPage(HuaJiAgeMod.prefix("huaji_from_smelting_huaji_fragment"),
                                itemBuilder -> itemBuilder.withRecipe2(HuaJiAgeMod.prefix("huaji_from_smelting_ore_huaji")))
                        .addSpotlightPage(itemBuilder -> itemBuilder.addItem(HuaJiItems.HUAJI.getId()),
                                pageBuilder -> pageBuilder.withText("The form HUAJI naturally takes when crystallized at room temperature. Its peculiar shape has been an inexhaustible source of artistic inspiration for thousands of years."))
        );

        builder.addEntry(ID + "/" + "huaji_bomb", "HUAJI Bomb", HuaJiItems.HUAJI_BOMB.getId(),
                HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                        .addTextPage("Incorporating HUAJI elements into TNT results in a greater explosive yield, but also makes it unstable—for convenience, we generally refer to it as the $(item)HUAJI Bomb$().", pageBuilder -> {
                        })
                        .addSpotlightPage(itemBuilder -> itemBuilder.addItem(HuaJiItems.HUAJI_BOMB.getId()).addItem(HuaJiItems.HUAJI_ANTIMATTER_BOMB.getId()),
                                pageBuilder -> pageBuilder.withText("The HUAJI Bomb also has an improved version: the HUAJI Miniature Antimatter Bomb, capable of annihilating everything within an area."))
                        .addCraftingPage(HuaJiAgeMod.prefix("huaji_bomb"),
                                itemBuilder -> itemBuilder.withRecipe2(HuaJiAgeMod.prefix("huaji_antimatter_bomb")))
        );

        builder.addEntry(ID + "/" + "jin_ke_la", "Jin Ke La", HuaJiItems.JIN_KE_LA.getId(),
                HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                        .addTextPage("As a common fertilizer, Bone Meal is widely used in Minecraft's agriculture and animal husbandry... but what happens if you infuse it with the power of HUAJI?$(br2)" +
                                "$(o)\"With Jin Ke La mixed in the fertilizer, one bag does the work of two!\"$()$(br)" +
                                "$(o)\"With Jin Ke La mixed in the fertilizer, wheat yield reaches 1800 per mu!\"$()", pageBuilder -> {
                        })
                        .addCraftingPage(HuaJiAgeMod.prefix("jin_ke_la"), pageBuilder -> pageBuilder.withText("$(o)\"The energy in every grain of Jin Ke La is like an atomic bomb.\" — It is said this line once appeared in a Jin Ke La advertisement.$()"))
                        .addTextPage("If you attempt to use Jin Ke La on a HUAJI Bomb, it will cause an even larger explosion than the original... Perhaps this is the true meaning behind that advertising slogan?", pageBuilder -> pageBuilder.withAdvancement(HuaJiAgeMod.prefix("jin_ke_la_bomb")))
        );
    }

    public static void zhCnEntryBuilder(PGenLangBuilder builder) {
        builder.addEntry(ID + "/" + "huaji", "滑稽", HuaJiItems.HUAJI.getId(),
                HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0).withPriority(true)
                        .addTextPage("$(l:https://zh.moegirl.org.cn/滑稽)滑稽$()者——世界之本源之一，鬼畜要素的一种结晶。该物质在宇宙万物间均有分布，不过，仅是以极低的浓度存在，难以直接提取，其高浓度的结晶尽在地壳深处才含有。", pageBuilder -> {
                        })
                        .addSpotlightPage(itemBuilder -> itemBuilder.addItem(HuaJiItems.HUAJI_FRAGMENT.getId()).addItem(HuaJiItems.HUAJI_ORE.getId()),
                                pageBuilder -> pageBuilder.withText("在地下深处积蓄的富含滑稽的石片，由于滑稽在高浓度下呈现的高沸点特性，可直接加热以提取滑稽。最新的贴吧地质学表明，这些滑稽的结晶矿似乎是由富含滑稽的海水以及地下水在经过上亿年的地底沉积转化过来的。"))
                        .addSmeltingPage(HuaJiAgeMod.prefix("huaji_from_smelting_huaji_fragment"),
                                itemBuilder -> itemBuilder.withRecipe2(HuaJiAgeMod.prefix("huaji_from_smelting_ore_huaji")))
                        .addSpotlightPage(itemBuilder -> itemBuilder.addItem(HuaJiItems.HUAJI.getId()),
                                pageBuilder -> pageBuilder.withText("滑稽在常温中自然结晶呈现的形态，其奇特的形态成为了千百年来艺术家不竭的艺术源泉。"))
        );

        builder.addEntry(ID + "/" + "huaji_bomb", "滑稽炸弹", HuaJiItems.HUAJI_BOMB.getId(),
                HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                        .addTextPage("将滑稽元素掺入TNT中，会使得TNT具有更大的威力，但会变得不稳定——方便起见，我们一般将其称为 $(item)“滑稽炸弹” $()。", pageBuilder -> {
                        })
                        .addSpotlightPage(itemBuilder -> itemBuilder.addItem(HuaJiItems.HUAJI_BOMB.getId()).addItem(HuaJiItems.HUAJI_ANTIMATTER_BOMB.getId()),
                                pageBuilder -> pageBuilder.withText("滑稽炸弹还有一种改进型：滑稽微型反物质炸弹，能够直接湮灭一块区域内的一切。"))
                        .addCraftingPage(HuaJiAgeMod.prefix("huaji_bomb"),
                                itemBuilder -> itemBuilder.withRecipe2(HuaJiAgeMod.prefix("huaji_antimatter_bomb")))
        );

        builder.addEntry(ID + "/" + "jin_ke_la", "金坷垃", HuaJiItems.JIN_KE_LA.getId(),
                HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                        .addTextPage("作为一种常见的肥料，骨粉被广泛用于Minecraft的农牧业中……但，如果在其基础上加入滑稽的力量，会发生什么呢？$(br2)" +
                                "$(o)“肥料掺了金坷垃 一袋能顶两袋撒！”$()$(br)" +
                                "$(o)“肥料掺了金坷垃 小麦亩产一千八！”$()", pageBuilder -> {
                        })
                        .addCraftingPage(HuaJiAgeMod.prefix("jin_ke_la"), pageBuilder -> pageBuilder.withText("$(o)“每一粒金坷垃的能量，就像一颗原子弹。”——据说金坷垃的广告中曾有过这句话$()"))
                        .addTextPage("如果尝试对滑稽炸弹使用金坷垃，将会引发一场比原来更大的爆炸……或许这就是那句广告词的含义？", pageBuilder -> pageBuilder.withAdvancement(HuaJiAgeMod.prefix("jin_ke_la_bomb")))
        );
    }

    public static class CategoryMaterials {
        public static final String ID = "huaji_materials";

        public static void enUsCategoryBuilder(PGenCategoryBuilder builder) {
            builder.withSortnum(0).withParent(HuaJiAgeMod.prefix(CategoryHuaJiItems.ID));
        }

        public static void zhCnCategoryBuilder(PGenCategoryBuilder builder) {
            builder.withSortnum(0).withParent(HuaJiAgeMod.prefix(CategoryHuaJiItems.ID));
        }

        public static void enUsEntryBuilder(PGenLangBuilder builder) {
            builder.addEntry(ID + "/" + "huaji_blender", "Tumbling HUAJI Power Blender", HuaJiItems.HUAJI_BLENDER.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                            .addTextPage("HUAJI particles have significant application value; however, the elemental form is highly inert and has little direct use. Its special properties only truly manifest after being blended with other materials.", pageBuilder -> {
                            })
                            .addCraftingPage(HuaJiAgeMod.prefix("huaji_blender"),
                                    pageBuilder -> pageBuilder.withTitle("HUAJI Perfusion")
                                            .withText("Leveraging the power of the Nether Star, this instrument can blend HUAJI with other materials, thereby activating the Kichiku elements within and transforming them into various HUAJI materials."))
                            .addPage(new HuaJiBlenderPageBuilder(entryBuilder.modId, HuaJiAgeMod.prefix("huaji_blender/huaji_ingot"))
                                    .withRecipe2(HuaJiAgeMod.prefix("huaji_blender/neutron_star_fragment")).build())
                            .addPage(new HuaJiBlenderPageBuilder(entryBuilder.modId, HuaJiAgeMod.prefix("huaji_blender/redstone_druse"))
                                    .withRecipe2(HuaJiAgeMod.prefix("huaji_blender/flash_flour")).build())
                            .addPage(new HuaJiBlenderPageBuilder(entryBuilder.modId, HuaJiAgeMod.prefix("huaji_blender/hope_element"))
                                    .withRecipe2(HuaJiAgeMod.prefix("huaji_blender/wave_crystal")).build())
                            .addPage(new HuaJiBlenderPageBuilder(entryBuilder.modId, HuaJiAgeMod.prefix("huaji_blender/huaji"))
                                    .withRecipe2(HuaJiAgeMod.prefix("huaji_blender/anti_huaji")).build())
            );
            builder.addEntry(ID + "/" + "the_shining_star", "The Shining Star", HuaJiItems.AIRSPACE_STAR.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                            .addTextPage("Research using the \"I Reckon\" method has shown that the energy contained within the $(item)Nether Star$() can fuse well with HUAJI power, leading to the creation of a whole new class of materials—the $(item)HUAJI Stars$().", pageBuilder -> {
                            })
                            .addSpotlightPage(itemBuilder -> itemBuilder.addItem(HuaJiItems.HUAJI_STAR.getId()).addItem(HuaJiItems.AIRSPACE_STAR.getId()),
                                    pageBuilder -> pageBuilder.withText("\"No matter the era, it is ordinary humans who shatter the stars.\"$(br)" +
                                            "\"The stars?\" \"It's nothing, just a thing of the past.\"$(br)" +
                                            "(From afar, a cry is heard: Stella!!!)"))
                            .addCraftingPage(HuaJiAgeMod.prefix("huaji_star"),
                                    pageBuilder -> pageBuilder.withRecipe2(HuaJiAgeMod.prefix("airspace_star")))
                            .addCraftingPage(HuaJiAgeMod.prefix("huaji_star_block_packed"),
                                    pageBuilder -> pageBuilder.withRecipe2(HuaJiAgeMod.prefix("airspace_star_block_packed")))
            );
        }

        public static void zhCnEntryBuilder(PGenLangBuilder builder) {
            builder.addEntry(ID + "/" + "huaji_blender", "滚筒式滑稽之力混合仪", HuaJiItems.HUAJI_BLENDER.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                            .addTextPage("滑稽粒子具有重要的应用价值，不过其单质具有很强的惰性，直接应用价值不高，只在与其他材料混合之后，其特殊之处才能真正体现出来。", pageBuilder -> {
                            })
                            .addCraftingPage(HuaJiAgeMod.prefix("huaji_blender"),
                                    pageBuilder -> pageBuilder.withTitle("滑稽灌注")
                                            .withText("借助下界之星的力量，该仪器可将滑稽与其他材料混合，进而激发出其中的鬼畜要素，从而转化为各种滑稽素材。"))
                            .addPage(new HuaJiBlenderPageBuilder(entryBuilder.modId, HuaJiAgeMod.prefix("huaji_blender/huaji_ingot"))
                                    .withRecipe2(HuaJiAgeMod.prefix("huaji_blender/neutron_star_fragment")).build())
                            .addPage(new HuaJiBlenderPageBuilder(entryBuilder.modId, HuaJiAgeMod.prefix("huaji_blender/redstone_druse"))
                                    .withRecipe2(HuaJiAgeMod.prefix("huaji_blender/flash_flour")).build())
                            .addPage(new HuaJiBlenderPageBuilder(entryBuilder.modId, HuaJiAgeMod.prefix("huaji_blender/hope_element"))
                                    .withRecipe2(HuaJiAgeMod.prefix("huaji_blender/wave_crystal")).build())
                            .addPage(new HuaJiBlenderPageBuilder(entryBuilder.modId, HuaJiAgeMod.prefix("huaji_blender/huaji"))
                                    .withRecipe2(HuaJiAgeMod.prefix("huaji_blender/anti_huaji")).build())
            );
            builder.addEntry(ID + "/" + "the_shining_star", "闪耀的星辰", HuaJiItems.AIRSPACE_STAR.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                            .addTextPage("经“俺寻思”法研究表明，$(item)下界之星$()中所蕴含的能量能够很好地与滑稽之力融合，进而创造出一类全新的材料——$(item)滑稽之星$()。", pageBuilder -> {
                            })
                            .addSpotlightPage(itemBuilder -> itemBuilder.addItem(HuaJiItems.HUAJI_STAR.getId()).addItem(HuaJiItems.AIRSPACE_STAR.getId()),
                                    pageBuilder -> pageBuilder.withText("“无论何时，击碎星辰的都是普通的人类啊。”$(br)" +
                                            "“星辰？”“没事，只是件往事罢了。”$(br)" +
                                            "（远远地，传来一声：Stella！！！）"))
                            .addCraftingPage(HuaJiAgeMod.prefix("huaji_star"),
                                    pageBuilder -> pageBuilder.withRecipe2(HuaJiAgeMod.prefix("airspace_star")))
                            .addCraftingPage(HuaJiAgeMod.prefix("huaji_star_block_packed"),
                                    pageBuilder -> pageBuilder.withRecipe2(HuaJiAgeMod.prefix("airspace_star_block_packed")))
            );
        }
    }

    public static class CategoryFoods {
        public static final String ID = "huaji_foods";

        public static void enUsCategoryBuilder(PGenCategoryBuilder builder) {
            builder.withSortnum(1).withParent(HuaJiAgeMod.prefix(CategoryHuaJiItems.ID));
        }

        public static void zhCnCategoryBuilder(PGenCategoryBuilder builder) {
            builder.withSortnum(1).withParent(HuaJiAgeMod.prefix(CategoryHuaJiItems.ID));
        }

        public static void enUsEntryBuilder(PGenLangBuilder builder) {
            builder.addEntry(ID + "/" + "treasure_of_so_fragrant", "The Treasure of \"So Fragrant\"", HuaJiItems.EGG_RICE.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                            .addTextPage("Legend says the title $(l:codex_the_king_of_so_fragrant)\"King of 'So Fragrant'\"$() originates from an ordinary food—egg fried rice. He comprehended the truth of \"So Fragrant\" upon tasting a bowl of egg fried rice, eventually becoming the legendary figure he is today.", pageBuilder -> {
                            })
                            .addCraftingPage(HuaJiAgeMod.prefix("egg_rice"),
                                    pageBuilder -> pageBuilder.withText("A long-lost delicacy, said to be made from a recipe written by the King of \"So Fragrant\" himself, capable of making all who eat it praise it from the bottom of their hearts: \"So Fragrant!\". Provides various buffs after consumption."))
                            .addCraftingPage(HuaJiAgeMod.prefix("ultimate_egg_rice"),
                                    pageBuilder -> pageBuilder.withText("Egg fried rice blessed by the King of \"So Fragrant\" himself, brought into the world by the $(l:the_shining_star)HUAJI Star$(). Provides more and stronger buffs after consumption."))
            );
            builder.addEntry(ID + "/" + "baked_gluten", "Baked Gluten", HuaJiItems.BAKED_GLUTEN.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                            .addTextPage("A delicacy created by \"Baked Gluten Bro\" $(thing)Chun · Jian · Laji · Cide$(). Due to its low cost and bold flavor, it has been widely praised among the people.$(br)" +
                                    "After Baked Gluten became popular, $(thing)Chun · Jian · Laji · Cide$() invited the $(thing)Sword Saint of Waves$() to sing a duet with him titled \"My Baked Gluten, Melts Your Heart,\" deeply etching Baked Gluten into the heart of every Kichiku enthusiast.", pageBuilder -> {
                            })
                            .addPage(new SimpleProcessingRecipePageBuilder("stonecutting", entryBuilder.modId,
                                    HuaJiAgeMod.prefix("raw_gluten_from_flash_flour_stonecutting")).build())
                            .addPage(new SimpleProcessingRecipePageBuilder("smoking", entryBuilder.modId,
                                    HuaJiAgeMod.prefix("baked_gluten_from_smoking")).build())
                            .addPage(new SimpleProcessingRecipePageBuilder("campfire", entryBuilder.modId,
                                    HuaJiAgeMod.prefix("baked_gluten_from_campfire_cooking")).build())
            );
            builder.addEntry(ID + "/" + "dio_bread", "Dio's Bread", HuaJiItems.DIO_BREAD.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                            .addTextPage("A commemorative bread released by GIOGIO's Bakery to mark the 30th anniversary of the death of their conglomerate chairman's father. Now jointly produced by the Araki Manor.", pageBuilder -> {
                            })
                            .addCraftingPage(HuaJiAgeMod.prefix("dio_bread"), pageBuilder -> {
                            })
                            .addTextPage("$(o)Note: This bread contains a peculiar power. Upon consumption, it will briefly stop time for the entire dimension... except for the consumer. Furthermore, the bread maintains an indestructible state, similar to time stop, even when not in contact with living beings.$()$(br)" +
                                    "$(o)It has been confirmed that this property persists even if the bread is pulverized and mixed with other foods. The Kichiku All-Star Alliance has contacted the $(l:https://en.wikipedia.org/wiki/SCP_Foundation)SCP Foundation$() to handle this incident.$()", pageBuilder -> {
                            })
            );
        }

        public static void zhCnEntryBuilder(PGenLangBuilder builder) {
            builder.addEntry(ID + "/" + "treasure_of_so_fragrant", "真香财宝", HuaJiItems.EGG_RICE.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                            .addTextPage("传说，$(l:codex_the_king_of_so_fragrant)真香之王$()的称号来自一种普普通通的食物——蛋炒饭。他在品尝一碗蛋炒饭时领悟了“真香”的道理，进而成为了如今的传奇人物。", pageBuilder -> {
                            })
                            .addCraftingPage(HuaJiAgeMod.prefix("egg_rice"),
                                    pageBuilder -> pageBuilder.withText("一种早已失传的美食，据说其食谱由真香之王亲自书写，能让所有食客发自内心地赞美：“真香！”。食用后能够提供多种增益效果。"))
                            .addCraftingPage(HuaJiAgeMod.prefix("ultimate_egg_rice"),
                                    pageBuilder -> pageBuilder.withText("因$(l:the_shining_star)滑稽之星$()而现世的、由真香之王亲自祝福的蛋炒饭。食用后能够提供更多、更强的增益效果。"))
            );
            builder.addEntry(ID + "/" + "baked_gluten", "烤面筋", HuaJiItems.BAKED_GLUTEN.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                            .addTextPage("由“面筋哥”$(thing)淳·简·拉基·茨德$()所创造的一种美食，由于造价低廉、口味强劲，在民间广受好评。$(br)" +
                                    "而在烤面筋走红之后，$(thing)淳·简·拉基·茨德$()邀请$(thing)波澜剑圣$()，与他合唱了一首《我的烤面筋，融化你的心》，深深地将烤面筋刻在了每一位鬼畜人的心里。", pageBuilder -> {
                            })
                            .addPage(new SimpleProcessingRecipePageBuilder("stonecutting", entryBuilder.modId,
                                    HuaJiAgeMod.prefix("raw_gluten_from_flash_flour_stonecutting")).build())
                            .addPage(new SimpleProcessingRecipePageBuilder("smoking", entryBuilder.modId,
                                    HuaJiAgeMod.prefix("baked_gluten_from_smoking")).build())
                            .addPage(new SimpleProcessingRecipePageBuilder("campfire", entryBuilder.modId,
                                    HuaJiAgeMod.prefix("baked_gluten_from_campfire_cooking")).build())
            );
            builder.addEntry(ID + "/" + "dio_bread", "Dio的面包", HuaJiItems.DIO_BREAD.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                            .addTextPage("GIOGIO甜品屋推出的纪念款面包，以纪念其集团董事长父亲逝世30周年，现由荒木庄联合出品。", pageBuilder -> {
                            })
                            .addCraftingPage(HuaJiAgeMod.prefix("dio_bread"), pageBuilder -> {
                            })
                            .addTextPage("$(o)注：该面包中存在一种特殊的力量，食用后将会短暂暂停整个维度的时间……除了食用者以外。并且，该面包在不与生命体接触时也会保持一种类似时间停止的不毁状态。$()$(br)" +
                                    "$(o)目前已确认，即便将面包粉碎、与其他食物混合，该特性仍然存在。鬼畜全明星联盟已联系$(l:https://zh.moegirl.org.cn/SCP基金会)SCP基金会$()以处理此事件。$()", pageBuilder -> {
                            })
            );
        }
    }

    public static class CategoryWeaponsAndEquipment {
        public static final String ID = "weapons_and_equipment";

        public static void enUsCategoryBuilder(PGenCategoryBuilder builder) {
            builder.withSortnum(2).withParent(HuaJiAgeMod.prefix(CategoryHuaJiItems.ID));
        }

        public static void zhCnCategoryBuilder(PGenCategoryBuilder builder) {
            builder.withSortnum(2).withParent(HuaJiAgeMod.prefix(CategoryHuaJiItems.ID));
        }

        public static void enUsEntryBuilder(PGenLangBuilder builder) {
            builder.addEntry(ID + "/" + "huaji_equipment", "HUAJI Equipment", HuaJiItems.HUAJI_HELMET.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                            .addTextPage("Due to their similar color, HUAJI and gold ingots have good compatibility. $(l:huaji_blender)HUAJI Ingots$(), obtained by mixing the two, can be used to craft HUAJI Armor with performance rivaling diamond armor. Although its defense is slightly weaker than diamond armor, each piece of HUAJI Armor provides a continuous buff effect while worn.", pageBuilder -> {
                            })
                            .addCraftingPage(HuaJiAgeMod.prefix("huaji_helmet"), pageBuilder ->
                                    pageBuilder.withRecipe2(HuaJiAgeMod.prefix("huaji_chestplate")))
                            .addCraftingPage(HuaJiAgeMod.prefix("huaji_leggings"), pageBuilder ->
                                    pageBuilder.withRecipe2(HuaJiAgeMod.prefix("huaji_boots")))
                            .addCraftingPage(HuaJiAgeMod.prefix("huaji_sword"), pageBuilder ->
                                    pageBuilder.withText("However, the HUAJI Sword, also made from HUAJI Ingots, does not provide additional effects... Perhaps this sword isn't \"HUAJI\" enough?"))
                            .addTextPage("$(o)SlashBlade: Resharped Crossover Item$()$(br)" +
                                    "$(o)You need this mod to view the related content below.$()", pageBuilder -> pageBuilder.withFlag("!mod:slashblade"))
                            .addTextPage("As a basic material, HUAJI Ingots can also be forged into a Rodai SlashBlade. Furthermore, it can be crafted into the stronger \"-HuaJi Blade-\".", pageBuilder -> pageBuilder.withFlag("mod:slashblade"))
                            .addCraftingPage(HuaJiAgeMod.prefix("slashblade/rodai_huaji"), pageBuilder ->
                                    pageBuilder.withRecipe2(HuaJiAgeMod.prefix("slashblade/huaji_blade")).withFlag("mod:slashblade"))
            );
            builder.addEntry(ID + "/" + "black_premium_suit", "B l a c k  P r e m i u m  S u i t", HuaJiItems.ORGA_HELMET.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                            .addTextPage("The signature attire of Flower of Hope · Orga. Although it is an upgraded version of HUAJI Armor, it does not provide persistent buff effects... Perhaps its true power only manifests at the $(o)brink of life and death$()?", pageBuilder -> {
                            })
                            .addCraftingPage(HuaJiAgeMod.prefix("orga_helmet"), pageBuilder ->
                                    pageBuilder.withRecipe2(HuaJiAgeMod.prefix("orga_chestplate")))
                            .addCraftingPage(HuaJiAgeMod.prefix("orga_leggings"), pageBuilder ->
                                    pageBuilder.withRecipe2(HuaJiAgeMod.prefix("orga_boots")))
                            .addTextPage("$(o)When you die while wearing Orga's attire, the power of the Flower of Hope will reject death— subsequently, the Flower of Hope will materialize the scene of Orga's assassination. You will not die until this scene concludes.$()$(br)" +
                                    "$(o)However, once the scene ends, you will die forcibly due to worldline convergence... Perhaps there is $(l)something$() with the power to reverse this?$()", pageBuilder -> pageBuilder.withAdvancement(HuaJiAgeMod.prefix("hope_flower")))
            );
            builder.addEntry(ID + "/" + "50_50_helmet", "The \"50-50\" Helmet", HuaJiItems.FIFTY_FIFTY_HELMET.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                            .addTextPage("The legendary Noble Phantasm of $(l:codex_lord_lu)Lord.Lu$(), capable of providing strong defense... but its true power seems to still be sealed.", pageBuilder -> {
                            })
                            .addPage(new SimpleProcessingRecipePageBuilder("smithing", entryBuilder.modId,
                                    HuaJiAgeMod.prefix("50_50_helmet_smithing")).build())
                            .addSpotlightPage(itemBuilder -> itemBuilder.addItem(HuaJiItems.LORD_CORE.getId()),
                                    pageBuilder -> pageBuilder.withText("An object resembling a frying pan, containing the power of the Airspace Star. It can awaken a part of Lord.Lu's power. Hold right-click while equipped with the Fifty-Fifty Helmet to use it and activate the helmet's $(thing)Release Mode$().$(br)" +
                                            "Press $(l)$(6)[$(k:huajiage.change_mode)]$() to toggle the Fifty-Fifty Helmet's mode. In Release Mode, the helmet continuously provides multiple buff effects."))
                            .addSpotlightPage(itemBuilder -> itemBuilder.addItem(HuaJiItems.LORD_KEY.getId()),
                                    pageBuilder -> pageBuilder.withText("A key that unleashes Lord.Lu's true supreme power. After use, the Fifty-Fifty Helmet provides stronger buffs, grants permanent creative flight, and reflects half of the damage you receive back to the attacker."))
                            .addCraftingPage(HuaJiAgeMod.prefix("lord_core"), pageBuilder ->
                                    pageBuilder.withRecipe2(HuaJiAgeMod.prefix("lord_key")))
            );
            builder.addEntry(ID + "/" + "exglutenbur", "EX · Glutenbur", HuaJiItems.EXGLUTENBUR.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                            .addTextPage("A Noble Phantasm said to be held by $(thing)\"The Gluten Brother\"$(). Hold $(l)$(6)[$(k:sneak)+$(k:use)]$() while holding it to change its flavor:$(br)" +
                                    "Aromatic: Base attack damage increased and grants permanent buff effects. Attacks consume extra durability but restore hunger and inflict Hunger on the target.$(br)" +
                                    "Spicy: Base attack damage increased. Attacks ignite the target and other nearby creatures, dealing extra fire damage.$(br)" +
                                    "Lime!: Base attack damage increased. Continuously receive the Wither effect, but attacks inflict additional negative status effects and have a chance to deal a large amount of special damage to the target.", pageBuilder -> {
                            })
                            .addCraftingPage(HuaJiAgeMod.prefix("exglutenbur"), pageBuilder ->
                                    // 或许需要一种更好的翻译方法？ / Perhaps a better translation method is needed?
                                    pageBuilder.withText("哎香香的口味，你吃过没，辣辣的感觉，你尝过没，网爆红人的歌声，你听过没，真正的烤面筋，可带劲了，让你吃到真正的实惠，不一样的滋味，烤面筋烤面筋，我的烤面筋，让你吃到每天都开心，烤面筋烤面筋，我的烤面筋，让你吃到每个清晨黄昏都精神~$(br)" +
                                            "— Allegedly an early song made for gluten by \"The Gluten Brother,\" also the original version of \"My Baked Gluten, Melts Your Heart\"."))
            );
            builder.addEntry(ID + "/" + "wave_slashblade", "Wave Blade -Raging Billows-", HuaJiItems.WAVE_CRYSTAL.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                            .addTextPage("$(o)SlashBlade: Resharped Crossover Item$()$(br)" +
                                    "$(o)You need this mod to view this item's related content$()", pageBuilder -> pageBuilder.withFlag("!mod:slashblade"))
                            .addTextPage("The legendary katana used by the $(thing)Sword Saint of Waves$(). It can flow through enemy hordes like water and overwhelm foes like a tsunami.$(br)" +
                                    "Due to the deep friendship between the $(thing)Sword Saint of Waves$() and $(thing)\"The Gluten Brother\"$(), special effects trigger when their weapons meet.", pageBuilder -> pageBuilder.withFlag("mod:slashblade"))
                            .addCraftingPage(HuaJiAgeMod.prefix("slashblade/wave_yamato"), pageBuilder -> pageBuilder.withFlag("mod:slashblade"))
                            .addTextPage("SA：$(l)Wave Iaido$()$(br)" +
                                            "Step back, then unleash a special Rapid Slash. Each slash during this releases a phantom blade. Does not step back if executed perfectly.$(br)" +
                                            "$(br)" +
                                            "SE：$(br)" +
                                            "$(l)Waving Edge$(): When in water/rain, gain multiple buffs. Swinging the blade generates an additional phantom blade.$(br)" +
                                            "$(l)Sworn Kinship$(): When holding EX·Glutenbur in the off-hand, sword swings will apply the attack effects of EX·Glutenbur. Can be used consecutively without recovery frames after using Wave Iaido.",
                                    pageBuilder -> pageBuilder.withFlag("mod:slashblade"))
            );
            builder.addEntry(ID + "/" + "huaji_sword_pro", "HUAJI Sword Pro", HuaJiItems.HUAJI_STAR_SWORD.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                            .addTextPage("The HUAJI Sword can be modified and enhanced.", pageBuilder -> {
                            })
                            .addCraftingPage(HuaJiAgeMod.prefix("huaji_star_sword"), pageBuilder ->
                                    pageBuilder.withText("Stellar Sword: The result of combining the HUAJI Sword with the HUAJI Star. Hold $(l)$(6)[$(k:sneak)+$(k:use)]$() while holding it to change form. In its active state, damage is greatly increased and deals extra void damage, but inflicts the Hunger effect on the user."))
                            .addTextPage("$(o)Latiao Craft 2 Crossover Item$()$(br)" +
                                    "$(o)You need this mod to view the related content below.$()", pageBuilder -> pageBuilder.withFlag("!mod:ltc2"))
                            .addCraftingPage(HuaJiAgeMod.prefix("huaji_latiao_sword"), pageBuilder ->
                                    pageBuilder.withText("A sword cast from spicy strips. Hold $(l)$(6)[$(k:sneak)+$(k:use)]$() while holding it to change form. When inactive, consumes hunger to repair durability. When active, attacks deal extra lava damage, and pressing $(l)$(6)[$(k:use)]$() while holding releases 「Soaring Flames」, causing area damage and moving you upward.").withFlag("mod:ltc2"))
            );
            builder.addEntry(ID + "/" + "hero_bow", "Hero's Bow", HuaJiItems.HERO_BOW.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                            .addTextPage("A weapon not belonging to a Kichiku star, but still a powerful Noble Phantasm.$(br)" +
                                    "$(l)Robustness (EX)$(): Grants multiple buff effects to the wielder while held.$(br)" +
                                    "$(l)Clairvoyance (A)$(): Arrows shot from the Hero's Bow receive extra enhancements.$(br)" +
                                    "$(l)Bow and Arrow Construction (A)$(): The Hero's Bow does not consume arrows when shooting.", pageBuilder -> {
                            })
                            .addCraftingPage(HuaJiAgeMod.prefix("hero_bow"), pageBuilder ->
                                    pageBuilder.withText("Hold $(l)$(6)[$(k:sneak)+$(k:use)]$() while holding it to change form. While in the $(e)True Name Release$() form, shot arrows cause large area, high damage—but the shooter will also suffer severe backlash.$(br2)" +
                                            "$(o)Stella！！！——$()"))
            );
        }

        public static void zhCnEntryBuilder(PGenLangBuilder builder) {
            builder.addEntry(ID + "/" + "huaji_equipment", "滑稽装备", HuaJiItems.HUAJI_HELMET.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                            .addTextPage("由于颜色相近，滑稽和金锭具有很好的相性。将两者混合得到的$(l:huaji_blender)滑稽锭$()能够用于制作性能不输于钻石护甲的滑稽护甲。尽管防御力略弱于钻石盔甲，但在穿戴时，每件滑稽护甲能够各持续提供一种增益效果。", pageBuilder -> {
                            })
                            .addCraftingPage(HuaJiAgeMod.prefix("huaji_helmet"), pageBuilder ->
                                    pageBuilder.withRecipe2(HuaJiAgeMod.prefix("huaji_chestplate")))
                            .addCraftingPage(HuaJiAgeMod.prefix("huaji_leggings"), pageBuilder ->
                                    pageBuilder.withRecipe2(HuaJiAgeMod.prefix("huaji_boots")))
                            .addCraftingPage(HuaJiAgeMod.prefix("huaji_sword"), pageBuilder ->
                                    pageBuilder.withText("然而，作为同由滑稽锭制作的装备，滑稽剑并不能提供额外的效果……或许是因为这把剑不够滑稽？"))
                            .addTextPage("$(o)拔刀剑：重锋（SlashBlade:Resharped）联动物品$()$(br)" +
                                    "$(o)你需要安装此mod以查看以下相关内容$()", pageBuilder -> pageBuilder.withFlag("!mod:slashblade"))
                            .addTextPage("作为一种基础材料，滑稽锭也可以用于打造露台拔刀剑，而在此基础上，可以将其打造成更强的「滑稽大刀」。", pageBuilder -> pageBuilder.withFlag("mod:slashblade"))
                            .addCraftingPage(HuaJiAgeMod.prefix("slashblade/rodai_huaji"), pageBuilder ->
                                    pageBuilder.withRecipe2(HuaJiAgeMod.prefix("slashblade/huaji_blade")).withFlag("mod:slashblade"))
            );
            builder.addEntry(ID + "/" + "black_premium_suit", "黑  色  高  级  西  装", HuaJiItems.ORGA_HELMET.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                            .addTextPage("$(thing)希望之花·奥尔加$()的标志性装束。虽然是滑稽盔甲的升级版，但却不能提供常驻增益效果……或许，它的真正力量只会在$(o)生死边缘$()体现？", pageBuilder -> {
                            })
                            .addCraftingPage(HuaJiAgeMod.prefix("orga_helmet"), pageBuilder ->
                                    pageBuilder.withRecipe2(HuaJiAgeMod.prefix("orga_chestplate")))
                            .addCraftingPage(HuaJiAgeMod.prefix("orga_leggings"), pageBuilder ->
                                    pageBuilder.withRecipe2(HuaJiAgeMod.prefix("orga_boots")))
                            .addTextPage("$(o)当你身着奥尔加的衣物死亡时，希望之花的力量将会拒绝死亡——随后，希望之花会将当年奥尔加遇刺的剧情具现出来，在这段剧情结束前你将不会死亡。$()$(br)" +
                                    "$(o)然而，一旦剧情结束，你就会因时间线收束而强制死亡……或许，有$(l)什么东西$()的力量，可以逆转这一切？$()", pageBuilder -> pageBuilder.withAdvancement(HuaJiAgeMod.prefix("hope_flower")))
            );
            builder.addEntry(ID + "/" + "50_50_helmet", "五五开头盔", HuaJiItems.FIFTY_FIFTY_HELMET.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                            .addTextPage("传说中$(l:codex_lord_lu)Lord.Lu$()的宝具，能够提供很强的防御力……但其真正的力量似乎还在封印之中。", pageBuilder -> {
                            })
                            .addPage(new SimpleProcessingRecipePageBuilder("smithing", entryBuilder.modId,
                                    HuaJiAgeMod.prefix("50_50_helmet_smithing")).build())
                            .addSpotlightPage(itemBuilder -> itemBuilder.addItem(HuaJiItems.LORD_CORE.getId()),
                                    pageBuilder -> pageBuilder.withText("形似平底锅的物体，蕴含着空域之星的力量，能够唤醒一部分Lord.Lu的力量，装备五五开头盔时长按右键即可使用、激活五五开头盔的$(thing)解放模式$()。$(br)" +
                                            "按下$(l)$(6)[$(k:huajiage.change_mode)]$()即可切换五五开头盔的模式，在解放模式下，五五开头盔将持续提供多种增益效果。"))
                            .addSpotlightPage(itemBuilder -> itemBuilder.addItem(HuaJiItems.LORD_KEY.getId()),
                                    pageBuilder -> pageBuilder.withText("一把钥匙，解放Lord.Lu真正的无上之力。使用后，五五开头盔将能提供更强的增益，还能为你提供常驻飞行能力，并将你受到的一半伤害返还给攻击者。"))
                            .addCraftingPage(HuaJiAgeMod.prefix("lord_core"), pageBuilder ->
                                    pageBuilder.withRecipe2(HuaJiAgeMod.prefix("lord_key")))
            );
            builder.addEntry(ID + "/" + "exglutenbur", "EX·面筋棒", HuaJiItems.EXGLUTENBUR.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                            .addTextPage("传说由$(thing)“面筋哥”$()持有的神器，手持时按下$(l)$(6)[$(k:sneak)+$(k:use)]$()可以切换其口味：$(br)" +
                                    "飘香：基础攻击伤害提升并获得常驻的增益效果，攻击额外消耗一定耐久，但会恢复饥饿值并使目标获得饥饿效果；$(br)" +
                                    "热辣：基础攻击伤害提升，攻击将点燃目标及其周围的其他生物，额外造成火焰伤害；$(br)" +
                                    "石灰！：基础攻击伤害提升，持续获得凋零效果，但攻击会额外附加负面效果，且有概率对目标额外造成大量特殊伤害。", pageBuilder -> {
                            })
                            .addCraftingPage(HuaJiAgeMod.prefix("exglutenbur"), pageBuilder ->
                                    pageBuilder.withText("哎香香的口味，你吃过没，辣辣的感觉，你尝过没，网爆红人的歌声，你听过没，真正的烤面筋，可带劲了，让你吃到真正的实惠，不一样的滋味，烤面筋烤面筋，我的烤面筋，让你吃到每天都开心，烤面筋烤面筋，我的烤面筋，让你吃到每个清晨黄昏都精神~$(br)" +
                                            "——据说是“面筋哥”早期为面筋所做的曲目，也是《我的烤面筋，融化你的心》的原曲"))
            );
            builder.addEntry(ID + "/" + "wave_slashblade", "澜刀「波澜怒涛」", HuaJiItems.WAVE_CRYSTAL.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                            .addTextPage("$(o)拔刀剑：重锋（SlashBlade:Resharped）联动物品$()$(br)" +
                                    "$(o)你需要安装此mod以查看该物品相关内容$()", pageBuilder -> pageBuilder.withFlag("!mod:slashblade"))
                            .addTextPage("传说中由$(thing)波澜剑圣$()所使用的武士刀，能够像水流一样在敌群中穿梭，也能像海啸一般淹没敌人。$(br)" +
                                    "由于$(thing)波澜剑圣$()与$(thing)“面筋哥”$()有着深厚的友情，两人的武器相遇时将会触发特殊的效果。", pageBuilder -> pageBuilder.withFlag("mod:slashblade"))
                            .addCraftingPage(HuaJiAgeMod.prefix("slashblade/wave_yamato"), pageBuilder -> pageBuilder.withFlag("mod:slashblade"))
                            .addTextPage("SA：$(l)波澜居合$()$(br)" +
                                            "向后退一步，然后发动一次特殊的疾走居合，期间每一刀附带一道幻影刃。完美释放时不会后退一步。$(br)" +
                                            "$(br)" +
                                            "SE：$(br)" +
                                            "$(l)掀起波澜$()：在水中/雨中时，获得多种增益效果，挥刀时额外产生一道幻影刃。$(br)" +
                                            "$(l)义结筋澜$()：副手持有EX·面筋棒时，挥刀命中敌人将会附带EX·面筋棒的攻击效果。使用波澜居合时，可以无后摇连续发动。",
                                    pageBuilder -> pageBuilder.withFlag("mod:slashblade"))
            );
            builder.addEntry(ID + "/" + "huaji_sword_pro", "滑稽剑 Pro", HuaJiItems.HUAJI_STAR_SWORD.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                            .addTextPage("在滑稽剑的基础上加以改造，可以使其变得更强。", pageBuilder -> {
                            })
                            .addCraftingPage(HuaJiAgeMod.prefix("huaji_star_sword"), pageBuilder ->
                                    pageBuilder.withText("滑稽剑与滑稽之星结合的成果。手持时按下$(l)$(6)[$(k:sneak)+$(k:use)]$()可以切换形态。激活状态下伤害大幅提升且额外造成虚空伤害，但会使自己陷入饥饿效果。"))
                            .addTextPage("$(o)辣条工艺2（Latiao Craft 2）联动物品$()$(br)" +
                                    "$(o)你需要安装此mod以查看以下相关内容$()", pageBuilder -> pageBuilder.withFlag("!mod:ltc2"))
                            .addCraftingPage(HuaJiAgeMod.prefix("huaji_latiao_sword"), pageBuilder ->
                                    pageBuilder.withText("由辣条铸成的剑，手持时按下$(l)$(6)[$(k:sneak)+$(k:use)]$()可以切换形态。未激活时会消耗饥饿值修复耐久，激活时攻击能够额外造成熔岩伤害，且按下手持时按下$(l)$(6)[$(k:use)]$()能够释放「烈焰腾空」，造成范围伤害并向上移动。").withFlag("mod:ltc2"))
            );
            builder.addEntry(ID + "/" + "hero_bow", "大英雄之弓", HuaJiItems.HERO_BOW.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                            .addTextPage("不属于鬼畜明星的武器，但仍是一把强力的宝具。$(br)" +
                                    "$(l)健硕（EX）$()：手持时大英雄之弓获得多种增益效果。$(br)" +
                                    "$(l)千里眼（A）$()：由大英雄之弓射出的箭矢将会获得额外的强化。$(br)" +
                                    "$(l)弓矢作成（A）$()：大英雄之弓射击时将不会消耗箭矢。", pageBuilder -> {
                            })
                            .addCraftingPage(HuaJiAgeMod.prefix("hero_bow"), pageBuilder ->
                                    pageBuilder.withText("手持时按下$(l)$(6)[$(k:sneak)+$(k:use)]$()可以切换形态。处于$(e)$(l)真名解放$()形态时，射出的箭矢将会造成大范围高额伤害——但射击者也将会受到强烈的反噬。$(br2)" +
                                            "$(o)Stella！！！——$()"))
            );
        }
    }
}
