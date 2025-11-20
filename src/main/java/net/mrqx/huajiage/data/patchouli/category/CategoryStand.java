package net.mrqx.huajiage.data.patchouli.category;

import com.reimnop.pgen.builder.PGenCategoryBuilder;
import com.reimnop.pgen.builder.PGenLangBuilder;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.registy.HuaJiItems;

public class CategoryStand {
    public static final String ID = "stand";

    public static void enUsCategoryBuilder(PGenCategoryBuilder builder) {
        builder.withSortnum(2);
    }

    public static void zhCnCategoryBuilder(PGenCategoryBuilder builder) {
        builder.withSortnum(2);
    }

    public static void enUsEntryBuilder(PGenLangBuilder builder) {
        builder.addEntry(ID + "/" + "overview", "Overview", HuaJiItems.DISC.getId(),
                HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0).withPriority(true)
                        .addTextPage("$(l)$(o)Stand POWER!!!$()$(br)" +
                                "A $(l)$(thing)Stand$() is a special ability that can be awakened by using the $(item)Stand Arrow$() or by obtaining a $(item)Disc$() from chests.", pageBuilder -> {
                        })
                        .addSpotlightPage(itemBuilder -> itemBuilder.addItem(HuaJiItems.DISC.getId()),
                                pageBuilder -> pageBuilder.withText("There are various types of Stands. The Stand obtained via the Stand Arrow is random, while the Stand obtained via a Disc is the same as the one written in the Disc's description. If you already have a Stand, you cannot obtain another one by the above methods."))
                        .addCraftingPage(HuaJiAgeMod.prefix("arrow_stand"), pageBuilder -> {
                        })
                        .addTextPage("After obtaining a Stand, several pieces of Stand-related information will be displayed on the left side of your field of view:$()$(br)" +
                                "$(br)" +
                                "$(l)Stand Name$(): The name of your current Stand.$()$(br)" +
                                "$(l)Level$(): The current level of your Stand. Shows as \"Complete\" at max level.$()$(br)" +
                                "$(l)State$(): The current active state of your Stand.$()$(br)" +
                                "$(l)Mental Power (MP)$(): Current Mental Power and maximum Mental Power.$()$(br)" +
                                "$(br)" +
                                "Here, $(l)$(thing)Mental Power$() is an important resource required for Stands. Summoning a Stand and using Stand skills both consume a certain amount of MP.$()$(br)" +
                                "After being summoned, a Stand can only exist for a limited time. Beyond this time, it will consume additional hunger to maintain its presence.$()$(br)", pageBuilder -> {
                        })
        );
        builder.addEntry(ID + "/" + "tarot", "Tarot Card of Fate", HuaJiItems.TAROT.getId(),
                HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                        .addTextPage("Sometimes, you might want to change your Stand. In this case, you can use the $(item)Tarot Card of Fate$() to unload your current Stand into the card, allowing you to acquire a new one.$()$(br)" +
                                "Of course, you can also reload the Stand from the Tarot card back to yourself when you don't have a Stand.", pageBuilder -> {
                        })
                        .addCraftingPage(HuaJiAgeMod.prefix("tarot"), pageBuilder -> {
                        })
        );
    }

    public static void zhCnEntryBuilder(PGenLangBuilder builder) {
        builder.addEntry(ID + "/" + "overview", "概述", HuaJiItems.DISC.getId(),
                HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0).withPriority(true)
                        .addTextPage("$(l)$(o)Stand POWER!!!$()$(br)" +
                                "$(l)$(thing)替身$()是一种特殊的能力，可以通过使用$(item)替身之箭$()或在宝箱中得到的$(item)Disc$()觉醒。", pageBuilder -> {
                        })
                        .addSpotlightPage(itemBuilder -> itemBuilder.addItem(HuaJiItems.DISC.getId()),
                                pageBuilder -> pageBuilder.withText("替身分为多种，通过替身之箭觉醒获得的替身是随机的，而通过Disc获得的替身与Disc的描述中所写的替身相同。已有替身时，将无法使用以上方式获得替身。"))
                        .addCraftingPage(HuaJiAgeMod.prefix("arrow_stand"), pageBuilder -> {
                        })
                        .addTextPage("获得替身后，在你视野的左侧将会展示几项与替身相关的信息：$()$(br)" +
                                "$(br)" +
                                "$(l)替身名$()：当前持有替身的名称$()$(br)" +
                                "$(l)等级$()：当前持有替身的等级，满级时会显示为“完成”$()$(br)" +
                                "$(l)状态$()：替身当前的活动状态$()$(br)" +
                                "$(l)精神力$()：目前的精神力与精神力上限$()$(br)" +
                                "$(br)" +
                                "其中，$(l)$(thing)精神力$()是替身所需的一种重要资源。召唤替身、使用替身技能时，均会消耗一定量的精神力。$()$(br)" +
                                "替身被召唤后只能存在一段时间，超出时间后将会额外消耗饥饿值来维持替身的存在。$()$(br)", pageBuilder -> {
                        })
        );
        builder.addEntry(ID + "/" + "tarot", "命运的塔罗牌", HuaJiItems.TAROT.getId(),
                HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0)
                        .addTextPage("有时，你或许会需要更换替身——此时，你可以使用$(item)命运的塔罗牌$()来将自己已有的替身卸载至塔罗牌中，以便获取新的替身。$()$(br)" +
                                "当然，你也可以在没有替身的情况下重新将塔罗牌中的替身装载至自身。", pageBuilder -> {
                        })
                        .addCraftingPage(HuaJiAgeMod.prefix("tarot"), pageBuilder -> {
                        })
        );
    }
}
