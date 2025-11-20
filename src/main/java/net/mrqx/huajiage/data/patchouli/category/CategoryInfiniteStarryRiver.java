package net.mrqx.huajiage.data.patchouli.category;

import com.reimnop.pgen.builder.PGenCategoryBuilder;
import com.reimnop.pgen.builder.PGenLangBuilder;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.data.patchouli.page.HuaJiPolyFurnacePageBuilder;
import net.mrqx.huajiage.registy.HuaJiItems;

public class CategoryInfiniteStarryRiver {
    public static final String ID = "the_infinite_starry_river";

    public static void enUsCategoryBuilder(PGenCategoryBuilder builder) {
        builder.withSortnum(3);
    }

    public static void zhCnCategoryBuilder(PGenCategoryBuilder builder) {
        builder.withSortnum(3);
    }

    public static void enUsEntryBuilder(PGenLangBuilder builder) {
        builder.addEntry(ID + "/" + "infinite_universe_star", "Infinite Universe Star", HuaJiItems.INFINITE_UNIVERSE_STAR.getId(),
                HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0).withPriority(true)
                        .addTextPage("\"If each HUAJI Star can be considered a single star, what would countless HUAJI Stars polymerize into?\"$()$(br)" +
                                "$(br)" +
                                "— No one knows which Senpai first posed this question, but for this query of his, the $(item)HUAJI Ultimate Polyfurnace$() might just give us the answer.", pageBuilder -> {
                        })
                        .addCraftingPage(HuaJiAgeMod.prefix("huaji_polyfurnace"),
                                pageBuilder -> pageBuilder.withText("By throwing a massive amount of $(item)HUAJI Stars$() into the HUAJI Ultimate Polyfurnace and using HUAJI as fuel for polymerization, one can obtain the final result—the $(item)Infinite Universe Star$().$()$(br)" +
                                        "$()$(br)" +
                                        "Using the Infinite Universe Star, one can draw more Kichiku power from the previous universe, thereby crafting even stronger HUAJI items."))
                        .addPage(new HuaJiPolyFurnacePageBuilder(entryBuilder.modId, HuaJiAgeMod.prefix("huaji_polyfurnace/huaji_star"))
                                .withRecipe2(HuaJiAgeMod.prefix("huaji_polyfurnace/huaji_star_block")).build())
                        .addPage(new HuaJiPolyFurnacePageBuilder(entryBuilder.modId, HuaJiAgeMod.prefix("huaji_polyfurnace/airspace_star"))
                                .withRecipe2(HuaJiAgeMod.prefix("huaji_polyfurnace/airspace_star_block")).build())
        );
        builder.addEntry(ID + "/" + "infinite_charm", "Infinite Durability Armor Charm", HuaJiItems.INFINITE_CHARM.getId(),
                HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                        .addTextPage("A charm made from the $(item)Infinite Universe Star$(), capable of rapidly repairing items in your equipment slots and hands—simple, crude, and powerful.", pageBuilder -> {
                        })
                        .addCraftingPage(HuaJiAgeMod.prefix("infinite_charm"),
                                pageBuilder -> pageBuilder.withText("$(o)If you equip a full set of the Black Premium Suit, its appearance will change... but it's utterly useless.$()"))
        );
        builder.addEntry(ID + "/" + "singularity", "Singularity", HuaJiItems.SINGULARITY.getId(),
                HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                        .addTextPage("$(o)According to the theory of $(0)$(l)Dark Priest Snake$(), the succession of universes is a cycle. Time accelerates infinitely towards a Singularity, the universe collapses into a point at that instant, and a new universe forms after another Big Bang. (The difference is that all surviving beings from the previous world return to the new world, while beings who died during the acceleration are replaced by not-quite-identical counterparts from different spacetimes.) This is 「Heaven」.$()$(br)" +
                                "$()$(br)" +
                                "The power of the Infinite Universe Star is immense, but still insufficient to accelerate the entire universe—but, if it's just accelerating yourself, it is enough.", pageBuilder -> {
                        })
                        .addCraftingPage(HuaJiAgeMod.prefix("singularity"),
                                pageBuilder -> pageBuilder.withText("Hold a Stand that is not at its maximum level, then use the Singularity while holding it to activate its power: For a period of time thereafter, you will continuously suffer high damage and multiple negative status effects. If you can find a way to endure through this period, your Stand will evolve to the next level.$()$(br)" +
                                        "$()$(br)" +
                                        "Evolved Stands gain the ability to use 「Stand Skills」, activated by pressing [$(k:huajiage.stand_skill)]. Different Stands have different skills; some can even achieve 「Time Stop」."))
        );
        builder.addEntry(ID + "/" + "arrow_requiem", "Arrow of Requiem", HuaJiItems.ARROW_REQUIEM.getId(),
                HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                        .addTextPage("According to apocryphal accounts, during the final battle of the previous universe, $(4)$(l)The Flower of Hope · Orga$() was struck by an arrow that flew out of a spacetime rift as he was dying, subsequently obtaining the power to turn the tide of battle.$()$(br)" +
                                "$()$(br)" +
                                "Regardless of the records, the power contained within this arrow, crafted from the Infinite Universe Star, can indeed resonate with Orga's power—when the \"Flower of Hope\" begins its chant, use this arrow on yourself to transform the power of the Flower of Hope into a special item. This is the $(4)$(l)「Unstoppable Orga Requiem」$().", pageBuilder -> {
                        })
                        .addCraftingPage(HuaJiAgeMod.prefix("arrow_requiem"), pageBuilder -> {
                        })
                        .addSpotlightPage(itemBuilder -> itemBuilder.addItem(HuaJiItems.ORGA_REQUIEM.getId()),
                                pageBuilder -> pageBuilder.withText("$(6)Ability I - Unstoppable Orga$(): Using the Requiem's power on himself, Orga can never reach the truth of death.$()$(br)" +
                                        "$(6)Ability II - Assassin Decreed by Causality$(): Any enemy who attempts to attack Orga will suffer double the damage they dealt in return.$()$(br)" +
                                        "$(6)Ability III - Requiem$(): Approaching the critical point of death, Orga activates the Requiem's power, continuously Muda-punching the opponent with a Stand (invisible even to Stand users)."))
                        .addTextPage("If you use the Requiem Arrow on yourself again, the $(4)$(l)「Unstoppable Orga Requiem」$() will transform into a Stand. At this point, some methods that disable items will no longer affect the $(4)$(l)「Unstoppable Orga Requiem」$().", pageBuilder -> {
                        })
        );
    }

    public static void zhCnEntryBuilder(PGenLangBuilder builder) {
        builder.addEntry(ID + "/" + "infinite_universe_star", "无尽之星", HuaJiItems.INFINITE_UNIVERSE_STAR.getId(),
                HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0).withPriority(true)
                        .addTextPage("“如果每一颗滑稽之星都可以看作一枚星辰的话，无数枚滑稽之星聚合在一起，会变成什么？”$()$(br)" +
                                "$(br)" +
                                "——没人知道最早提出这个问题的先辈是谁，但对于他提出的这个问题，$(item)滑稽终极熔炼炉$()或许能告诉我们答案。", pageBuilder -> {
                        })
                        .addCraftingPage(HuaJiAgeMod.prefix("huaji_polyfurnace"),
                                pageBuilder -> pageBuilder.withText("将海量的$(item)滑稽之星$()投入滑稽终极熔炼炉，再以滑稽作为燃料进行聚合，就能得到最后的结果——$(item)无尽之星$()。$()$(br)" +
                                        "$()$(br)" +
                                        "利用无尽之星，可以从上个宇宙中获取更多的鬼畜之力，进而打造更强的滑稽物品。"))
                        .addPage(new HuaJiPolyFurnacePageBuilder(entryBuilder.modId, HuaJiAgeMod.prefix("huaji_polyfurnace/huaji_star"))
                                .withRecipe2(HuaJiAgeMod.prefix("huaji_polyfurnace/huaji_star_block")).build())
                        .addPage(new HuaJiPolyFurnacePageBuilder(entryBuilder.modId, HuaJiAgeMod.prefix("huaji_polyfurnace/airspace_star"))
                                .withRecipe2(HuaJiAgeMod.prefix("huaji_polyfurnace/airspace_star_block")).build())
        );
        builder.addEntry(ID + "/" + "infinite_charm", "无限耐久盔甲护身符", HuaJiItems.INFINITE_CHARM.getId(),
                HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                        .addTextPage("由$(item)无尽之星$()制成的护身符，能够快速修复你装备栏及双手中的物品——简单，粗暴，且强大。", pageBuilder -> {
                        })
                        .addCraftingPage(HuaJiAgeMod.prefix("infinite_charm"),
                                pageBuilder -> pageBuilder.withText("$(o)如果你装备了全套黑色高级西装，它的外观会发生变化……然而并没有什么卵用。$()"))
        );
        builder.addEntry(ID + "/" + "singularity", "特异点", HuaJiItems.SINGULARITY.getId(),
                HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                        .addTextPage("$(o)根据Dark蛇神父的理论，宇宙的更迭是一个环，时间无限的加速抵达了特异点，宇宙在这一瞬间坍塌成了一个点，再次大爆炸形成了新的宇宙，（不同的是上个世界所有活下来的生物全部会回到新世界，在加速中死亡的生物会被不完全相同的异时空同位体代替），这就是「天堂」。$()$(br)" +
                                "$()$(br)" +
                                "无尽之星的力量十分强大，但仍不足以加速整个宇宙——但，如果只是加速你自己的话，足以。", pageBuilder -> {
                        })
                        .addCraftingPage(HuaJiAgeMod.prefix("singularity"),
                                pageBuilder -> pageBuilder.withText("持有一个未满级的替身，然后手持使用特异点，即可激发其中的力量：接下来的一段时间里，你将会持续受到高额的伤害与多种负面效果。如果你能想办法挺过这一段时间的话，你的替身就会进化到下一个等级。$()$(br)" +
                                        "$()$(br)" +
                                        "进化后的替身将能够使用「替身技能」，积攒足够的精神力后按下[$(k:huajiage.stand_skill)]即可使用。不同的替身具有不同的技能，有些替身甚至可以做到「停止时间」。"))
        );
        builder.addEntry(ID + "/" + "arrow_requiem", "虫箭", HuaJiItems.ARROW_REQUIEM.getId(),
                HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                        .addTextPage("据野史记载，在上个宇宙的最终之战中，$(4)$(l)希望之花·奥尔加$()在濒死中被时空裂痕中飞出的一支箭击中，进而获得了扭转战局的力量。$()$(br)" +
                                "$()$(br)" +
                                "无论记载如何，这支由无尽之星制成的箭所蕴含的力量，确实可以与奥尔加的力量产生共鸣——当$(thing)「希望之花」$()开始吟唱时，对自己使用这只箭，即可将希望之花的力量转化为一个特殊的物品，此即$(4)$(l)「停不下来的奥尔加镇魂曲」$()。", pageBuilder -> {
                        })
                        .addCraftingPage(HuaJiAgeMod.prefix("arrow_requiem"), pageBuilder -> {
                        })
                        .addSpotlightPage(itemBuilder -> itemBuilder.addItem(HuaJiItems.ORGA_REQUIEM.getId()),
                                pageBuilder -> pageBuilder.withText("$(6)能力I-停不下来的奥尔加$()：以镇魂曲的能力作用于自身，奥尔加永远达不到死亡的真实。$()$(br)" +
                                        "$(6)能力II-因果法则注定的暗杀者$()：凡尝试攻击奥尔加的敌人，必将承受其造成伤害的双倍奉还。$()$(br)" +
                                        "$(6)能力III-镇魂曲$()：逼近死亡的临界点，奥尔加将激活镇魂曲的能力，以（替身使者也看不见的）替身连续木大对方。"))
                        .addTextPage("如果再次对自己使用虫箭，$(4)$(l)「停不下来的奥尔加镇魂曲」$()将会转化为替身的形式，此时一些针对物品的禁用手段将不再会影响到$(4)$(l)「停不下来的奥尔加镇魂曲」$()。", pageBuilder -> {
                        })
        );
    }
}
