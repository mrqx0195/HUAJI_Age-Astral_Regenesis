package net.mrqx.huajiage.data.advancement;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.item.stand.ItemDisc;
import net.mrqx.huajiage.registy.HuaJiEffects;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.registy.HuaJiStands;
import net.mrqx.huajiage.utils.AdvancementHelper;
import net.mrqx.huajiage.utils.ItemTagHelper;

import java.util.function.Consumer;

@SuppressWarnings("DataFlowIssue")
public class HuaJiAdvancementGenerator implements ForgeAdvancementProvider.AdvancementGenerator {
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {
        Advancement root = Advancement.Builder.advancement()
                .display(HuaJiItems.HUAJI.get(),
                        Component.translatable("advancement.huajiage.root.title"),
                        Component.translatable("advancement.huajiage.root.desc"),
                        HuaJiAgeMod.prefix("textures/block/look.png"),
                        FrameType.TASK, false, false, false)
                .addCriterion("tick", PlayerTrigger.TriggerInstance.tick())
                .save(saver, HuaJiAgeMod.prefix("root"), existingFileHelper);

        Advancement ore = Advancement.Builder.advancement().parent(root)
                .display(HuaJiItems.HUAJI_FRAGMENT.get(),
                        Component.translatable("advancement.huajiage.ore.title"),
                        Component.translatable("advancement.huajiage.ore.desc"),
                        null,
                        FrameType.TASK, true, true, false)
                .requirements(RequirementsStrategy.OR)
                .addCriterion("get_" + HuaJiItems.HUAJI_FRAGMENT.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.HUAJI_FRAGMENT.get()))
                .addCriterion("get_" + HuaJiItems.HUAJI_ORE.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.HUAJI_ORE.get()))
                .save(saver, HuaJiAgeMod.prefix("ore"), existingFileHelper);

        Advancement huaji = Advancement.Builder.advancement().parent(ore)
                .display(HuaJiItems.HUAJI.get(),
                        Component.translatable("advancement.huajiage.huaji.title"),
                        Component.translatable("advancement.huajiage.huaji.desc"),
                        null,
                        FrameType.TASK, true, true, false)
                .addCriterion("get_" + HuaJiItems.HUAJI.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.HUAJI.get()))
                .save(saver, HuaJiAgeMod.prefix("huaji"), existingFileHelper);

        Advancement jinKeLa = Advancement.Builder.advancement().parent(huaji)
                .display(HuaJiItems.JIN_KE_LA.get(),
                        Component.translatable("advancement.huajiage.jin_ke_la.title"),
                        Component.translatable("advancement.huajiage.jin_ke_la.desc"),
                        null,
                        FrameType.TASK, true, true, false)
                .addCriterion("get_" + HuaJiItems.JIN_KE_LA.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.JIN_KE_LA.get()))
                .save(saver, HuaJiAgeMod.prefix("jin_ke_la"), existingFileHelper);

        Advancement.Builder.advancement().parent(jinKeLa)
                .display(HuaJiItems.HUAJI_BOMB.get(),
                        Component.translatable("advancement.huajiage.jin_ke_la_bomb.title"),
                        Component.translatable("advancement.huajiage.jin_ke_la_bomb.desc"),
                        null,
                        FrameType.CHALLENGE, true, true, true)
                .addCriterion("custom", new ImpossibleTrigger.TriggerInstance())
                .save(saver, HuaJiAgeMod.prefix("jin_ke_la_bomb"), existingFileHelper);

        Advancement huajiBlender = Advancement.Builder.advancement().parent(huaji)
                .display(HuaJiItems.HUAJI_BLENDER.get(),
                        Component.translatable("advancement.huajiage.huaji_blender.title"),
                        Component.translatable("advancement.huajiage.huaji_blender.desc"),
                        null,
                        FrameType.TASK, true, true, false)
                .addCriterion("get_" + HuaJiItems.HUAJI_BLENDER.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.HUAJI_BLENDER.get()))
                .save(saver, HuaJiAgeMod.prefix("huaji_blender"), existingFileHelper);

        Advancement huajiPolyfurnace = Advancement.Builder.advancement().parent(huajiBlender)
                .display(HuaJiItems.HUAJI_POLYFURNACE.get(),
                        Component.translatable("advancement.huajiage.huaji_polyfurnace.title"),
                        Component.translatable("advancement.huajiage.huaji_polyfurnace.desc"),
                        null,
                        FrameType.TASK, true, true, false)
                .addCriterion("get_" + HuaJiItems.HUAJI_POLYFURNACE.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.HUAJI_POLYFURNACE.get()))
                .save(saver, HuaJiAgeMod.prefix("huaji_polyfurnace"), existingFileHelper);


        foodAdvancements(saver, existingFileHelper, huaji);
        starAdvancements(saver, existingFileHelper, huaji);
        armorAdvancements(saver, existingFileHelper, huajiBlender);
        weaponAdvancements(saver, existingFileHelper, huajiBlender);
        standAdvancements(saver, existingFileHelper, huajiPolyfurnace);
        orgaAdvancements(saver, existingFileHelper, root);
    }

    private static void foodAdvancements(Consumer<Advancement> saver, ExistingFileHelper existingFileHelper, Advancement huaji) {
        Advancement eggRice = Advancement.Builder.advancement().parent(huaji)
                .display(HuaJiItems.EGG_RICE.get(),
                        Component.translatable("advancement.huajiage.egg_rice.title"),
                        Component.translatable("advancement.huajiage.egg_rice.desc"),
                        null,
                        FrameType.TASK, true, true, false)
                .addCriterion("get_" + HuaJiItems.EGG_RICE.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.EGG_RICE.get()))
                .save(saver, HuaJiAgeMod.prefix("egg_rice"), existingFileHelper);

        Advancement.Builder.advancement().parent(eggRice)
                .display(HuaJiItems.ULTIMATE_EGG_RICE.get(),
                        Component.translatable("advancement.huajiage.ultimate_egg_rice.title"),
                        Component.translatable("advancement.huajiage.ultimate_egg_rice.desc"),
                        null,
                        FrameType.CHALLENGE, true, true, false)
                .addCriterion("get_" + HuaJiItems.ULTIMATE_EGG_RICE.getId().getPath(), ConsumeItemTrigger.TriggerInstance.usedItem(HuaJiItems.ULTIMATE_EGG_RICE.get()))
                .save(saver, HuaJiAgeMod.prefix("ultimate_egg_rice"), existingFileHelper);

        Advancement.Builder.advancement().parent(huaji)
                .display(HuaJiItems.REO_CHERRY.get(),
                        Component.translatable("advancement.huajiage.reo_cherry.title"),
                        Component.translatable("advancement.huajiage.reo_cherry.desc"),
                        null,
                        FrameType.TASK, true, true, false)
                .addCriterion("get_" + HuaJiItems.REO_CHERRY.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.REO_CHERRY.get()))
                .save(saver, HuaJiAgeMod.prefix("reo_cherry"), existingFileHelper);

        Advancement.Builder.advancement().parent(huaji)
                .display(HuaJiItems.DIO_BREAD.get(),
                        Component.translatable("advancement.huajiage.dio_bread.title"),
                        Component.translatable("advancement.huajiage.dio_bread.desc"),
                        null,
                        FrameType.GOAL, true, true, false)
                .addCriterion("get_" + HuaJiItems.DIO_BREAD.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.DIO_BREAD.get()))
                .save(saver, HuaJiAgeMod.prefix("dio_bread"), existingFileHelper);
    }

    private static void starAdvancements(Consumer<Advancement> saver, ExistingFileHelper existingFileHelper, Advancement huaji) {
        Advancement huajiStar = Advancement.Builder.advancement().parent(huaji)
                .display(HuaJiItems.HUAJI_STAR.get(),
                        Component.translatable("advancement.huajiage.huaji_star.title"),
                        Component.translatable("advancement.huajiage.huaji_star.desc"),
                        null,
                        FrameType.TASK, true, true, false)
                .addCriterion("get_" + HuaJiItems.HUAJI_STAR.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.HUAJI_STAR.get()))
                .save(saver, HuaJiAgeMod.prefix("huaji_star"), existingFileHelper);

        Advancement airspaceStar = Advancement.Builder.advancement().parent(huajiStar)
                .display(HuaJiItems.AIRSPACE_STAR.get(),
                        Component.translatable("advancement.huajiage.airspace_star.title"),
                        Component.translatable("advancement.huajiage.airspace_star.desc"),
                        null,
                        FrameType.GOAL, true, true, false)
                .addCriterion("get_" + HuaJiItems.AIRSPACE_STAR.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.AIRSPACE_STAR.get()))
                .save(saver, HuaJiAgeMod.prefix("airspace_star"), existingFileHelper);

        Advancement.Builder.advancement().parent(airspaceStar)
                .display(HuaJiItems.INFINITE_UNIVERSE_STAR.get(),
                        Component.translatable("advancement.huajiage.infinite_universe_star.title"),
                        Component.translatable("advancement.huajiage.infinite_universe_star.desc"),
                        null,
                        FrameType.CHALLENGE, true, true, true)
                .addCriterion("get_" + HuaJiItems.INFINITE_UNIVERSE_STAR.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.INFINITE_UNIVERSE_STAR.get()))
                .save(saver, HuaJiAgeMod.prefix("infinite_universe_star"), existingFileHelper);
    }

    private static void armorAdvancements(Consumer<Advancement> saver, ExistingFileHelper existingFileHelper, Advancement huajiBlender) {
        Advancement huajiArmor = Advancement.Builder.advancement().parent(huajiBlender)
                .display(HuaJiItems.HUAJI_HELMET.get(),
                        Component.translatable("advancement.huajiage.huaji_armor.title"),
                        Component.translatable("advancement.huajiage.huaji_armor.desc"),
                        null,
                        FrameType.TASK, true, true, false)
                .addCriterion("get_" + HuaJiItems.HUAJI_HELMET.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.HUAJI_HELMET.get()))
                .addCriterion("get_" + HuaJiItems.HUAJI_CHESTPLATE.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.HUAJI_CHESTPLATE.get()))
                .addCriterion("get_" + HuaJiItems.HUAJI_LEGGINGS.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.HUAJI_LEGGINGS.get()))
                .addCriterion("get_" + HuaJiItems.HUAJI_BOOTS.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.HUAJI_BOOTS.get()))
                .save(saver, HuaJiAgeMod.prefix("huaji_armor"), existingFileHelper);

        Advancement.Builder.advancement().parent(huajiArmor)
                .display(HuaJiItems.ORGA_HELMET.get(),
                        Component.translatable("advancement.huajiage.orga_armor.title"),
                        Component.translatable("advancement.huajiage.orga_armor.desc"),
                        null,
                        FrameType.TASK, true, true, false)
                .addCriterion("get_" + HuaJiItems.ORGA_HELMET.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.ORGA_HELMET.get()))
                .addCriterion("get_" + HuaJiItems.ORGA_CHESTPLATE.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.ORGA_CHESTPLATE.get()))
                .addCriterion("get_" + HuaJiItems.ORGA_LEGGINGS.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.ORGA_LEGGINGS.get()))
                .addCriterion("get_" + HuaJiItems.ORGA_BOOTS.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.ORGA_BOOTS.get()))
                .save(saver, HuaJiAgeMod.prefix("orga_armor"), existingFileHelper);

        Advancement fiftyFiftyHelmet = Advancement.Builder.advancement().parent(huajiArmor)
                .display(HuaJiItems.FIFTY_FIFTY_HELMET.get(),
                        Component.translatable("advancement.huajiage.50_50_helmet.title"),
                        Component.translatable("advancement.huajiage.50_50_helmet.desc"),
                        null,
                        FrameType.GOAL, true, true, false)
                .addCriterion("get_" + HuaJiItems.FIFTY_FIFTY_HELMET.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.FIFTY_FIFTY_HELMET.get()))
                .save(saver, HuaJiAgeMod.prefix("50_50_helmet"), existingFileHelper);

        Advancement lordCore = Advancement.Builder.advancement().parent(fiftyFiftyHelmet)
                .display(HuaJiItems.LORD_CORE.get(),
                        Component.translatable("advancement.huajiage.lord_core.title"),
                        Component.translatable("advancement.huajiage.lord_core.desc"),
                        null,
                        FrameType.CHALLENGE, true, true, true)
                .addCriterion("custom", new ImpossibleTrigger.TriggerInstance())
                .save(saver, AdvancementHelper.LORD_CORE, existingFileHelper);

        Advancement.Builder.advancement().parent(lordCore)
                .display(HuaJiItems.LORD_KEY.get(),
                        Component.translatable("advancement.huajiage.lord_key.title"),
                        Component.translatable("advancement.huajiage.lord_key.desc"),
                        null,
                        FrameType.CHALLENGE, true, true, true)
                .addCriterion("custom", new ImpossibleTrigger.TriggerInstance())
                .save(saver, AdvancementHelper.LORD_KEY, existingFileHelper);
    }

    private static void weaponAdvancements(Consumer<Advancement> saver, ExistingFileHelper existingFileHelper, Advancement huajiBlender) {
        Advancement bakedGluten = Advancement.Builder.advancement().parent(huajiBlender)
                .display(HuaJiItems.BAKED_GLUTEN.get(),
                        Component.translatable("advancement.huajiage.baked_gluten.title"),
                        Component.translatable("advancement.huajiage.baked_gluten.desc"),
                        null,
                        FrameType.TASK, true, true, false)
                .addCriterion("get_" + HuaJiItems.BAKED_GLUTEN.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.BAKED_GLUTEN.get()))
                .save(saver, HuaJiAgeMod.prefix("baked_gluten"), existingFileHelper);

        Advancement.Builder.advancement().parent(bakedGluten)
                .display(HuaJiItems.EXGLUTENBUR.get(),
                        Component.translatable("advancement.huajiage.exglutenbur.title"),
                        Component.translatable("advancement.huajiage.exglutenbur.desc"),
                        null,
                        FrameType.GOAL, true, true, false)
                .addCriterion("get_" + HuaJiItems.EXGLUTENBUR.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.EXGLUTENBUR.get()))
                .save(saver, HuaJiAgeMod.prefix("exglutenbur"), existingFileHelper);

        Advancement huajiSword = Advancement.Builder.advancement().parent(huajiBlender)
                .display(HuaJiItems.HUAJI_SWORD.get(),
                        Component.translatable("advancement.huajiage.huaji_sword.title"),
                        Component.translatable("advancement.huajiage.huaji_sword.desc"),
                        null,
                        FrameType.TASK, true, true, false)
                .addCriterion("get_" + HuaJiItems.HUAJI_SWORD.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.HUAJI_SWORD.get()))
                .save(saver, HuaJiAgeMod.prefix("huaji_sword"), existingFileHelper);

        Advancement.Builder.advancement().parent(huajiSword)
                .display(HuaJiItems.HUAJI_STAR_SWORD.get(),
                        Component.translatable("advancement.huajiage.huaji_star_sword.title"),
                        Component.translatable("advancement.huajiage.huaji_star_sword.desc"),
                        null,
                        FrameType.GOAL, true, true, false)
                .addCriterion("get_" + HuaJiItems.HUAJI_STAR_SWORD.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.HUAJI_STAR_SWORD.get()))
                .save(saver, HuaJiAgeMod.prefix("huaji_star_sword"), existingFileHelper);

        Advancement.Builder.advancement().parent(huajiSword)
                .display(HuaJiItems.HUAJI_LATIAO_SWORD.get(),
                        Component.translatable("advancement.huajiage.huaji_latiao_sword.title"),
                        Component.translatable("advancement.huajiage.huaji_latiao_sword.desc"),
                        null,
                        FrameType.GOAL, true, true, true)
                .addCriterion("get_" + HuaJiItems.HUAJI_LATIAO_SWORD.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.HUAJI_LATIAO_SWORD.get()))
                .save(saver, HuaJiAgeMod.prefix("huaji_latiao_sword"), existingFileHelper);

        Advancement heroBow = Advancement.Builder.advancement().parent(huajiBlender)
                .display(HuaJiItems.HERO_BOW.get(),
                        Component.translatable("advancement.huajiage.hero_bow.title"),
                        Component.translatable("advancement.huajiage.hero_bow.desc"),
                        null,
                        FrameType.TASK, true, true, false)
                .addCriterion("get_" + HuaJiItems.HERO_BOW.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.HERO_BOW.get()))
                .save(saver, HuaJiAgeMod.prefix("hero_bow"), existingFileHelper);

        Advancement.Builder.advancement().parent(heroBow)
                .display(HuaJiItems.HERO_BOW.get(),
                        Component.translatable("advancement.huajiage.stella.title"),
                        Component.translatable("advancement.huajiage.stella.desc"),
                        null,
                        FrameType.TASK, true, true, false)
                .addCriterion("custom", new ImpossibleTrigger.TriggerInstance())
                .save(saver, AdvancementHelper.STELLA, existingFileHelper);

        Advancement multiKnife = Advancement.Builder.advancement().parent(huajiBlender)
                .display(HuaJiItems.MULTI_KNIFE.get(),
                        Component.translatable("advancement.huajiage.multi_knife.title"),
                        Component.translatable("advancement.huajiage.multi_knife.desc"),
                        null,
                        FrameType.TASK, true, true, false)
                .addCriterion("get_" + HuaJiItems.MULTI_KNIFE.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.MULTI_KNIFE.get()))
                .save(saver, HuaJiAgeMod.prefix("multi_knife"), existingFileHelper);

        Advancement.Builder.advancement().parent(multiKnife)
                .display(HuaJiItems.MULTI_KNIFE_SHINY.get(),
                        Component.translatable("advancement.huajiage.multi_knife_shiny.title"),
                        Component.translatable("advancement.huajiage.multi_knife_shiny.desc"),
                        null,
                        FrameType.TASK, true, true, false)
                .addCriterion("get_" + HuaJiItems.MULTI_KNIFE_SHINY.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.MULTI_KNIFE_SHINY.get()))
                .save(saver, HuaJiAgeMod.prefix("multi_knife_shiny"), existingFileHelper);
    }

    private static void standAdvancements(Consumer<Advancement> saver, ExistingFileHelper existingFileHelper, Advancement huajiPolyfurnace) {
        Advancement stand = Advancement.Builder.advancement().parent(huajiPolyfurnace)
                .display(HuaJiItems.DISC.get(),
                        Component.translatable("advancement.huajiage.stand.title"),
                        Component.translatable("advancement.huajiage.stand.desc"),
                        null,
                        FrameType.CHALLENGE, true, true, false)
                .addCriterion("custom", new ImpossibleTrigger.TriggerInstance())
                .save(saver, AdvancementHelper.STAND, existingFileHelper);

        Advancement.Builder.advancement().parent(stand)
                .display(HuaJiItems.TAROT.get(),
                        Component.translatable("advancement.huajiage.tarot.title"),
                        Component.translatable("advancement.huajiage.tarot.desc"),
                        null,
                        FrameType.TASK, true, true, false)
                .addCriterion("get_" + HuaJiItems.TAROT.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.TAROT.get()))
                .save(saver, HuaJiAgeMod.prefix("tarot"), existingFileHelper);

        Advancement.Builder.advancement().parent(huajiPolyfurnace)
                .display(HuaJiItems.SINGULARITY.get(),
                        Component.translatable("advancement.huajiage.singularity.title"),
                        Component.translatable("advancement.huajiage.singularity.desc"),
                        null,
                        FrameType.CHALLENGE, true, true, false)
                .addCriterion("custom", new ImpossibleTrigger.TriggerInstance())
                .save(saver, AdvancementHelper.SINGULARITY, existingFileHelper);
    }

    private static void orgaAdvancements(Consumer<Advancement> saver, ExistingFileHelper existingFileHelper, Advancement root) {
        Advancement hopeFlower = Advancement.Builder.advancement().parent(root)
                .display(HuaJiItems.HOPE_FLOWER.get(),
                        Component.translatable("advancement.huajiage.hope_flower.title"),
                        Component.translatable("advancement.huajiage.hope_flower.desc"),
                        null,
                        FrameType.GOAL, true, true, true)
                .addCriterion("has_effect", EffectsChangedTrigger.TriggerInstance.hasEffects(MobEffectsPredicate.effects().and(HuaJiEffects.HOPE_FLOWER.get())))
                .save(saver, HuaJiAgeMod.prefix("hope_flower"), existingFileHelper);

        Advancement arrowRequiem = Advancement.Builder.advancement().parent(hopeFlower)
                .display(HuaJiItems.ARROW_REQUIEM.get(),
                        Component.translatable("advancement.huajiage.arrow_requiem.title"),
                        Component.translatable("advancement.huajiage.arrow_requiem.desc"),
                        null,
                        FrameType.GOAL, true, true, true)
                .addCriterion("custom", new ImpossibleTrigger.TriggerInstance())
                .save(saver, AdvancementHelper.ARROW_REQUIEM, existingFileHelper);

        Advancement orgaRequiem = Advancement.Builder.advancement().parent(arrowRequiem)
                .display(HuaJiItems.ORGA_REQUIEM.get(),
                        Component.translatable("advancement.huajiage.orga_requiem.title"),
                        Component.translatable("advancement.huajiage.orga_requiem.desc"),
                        null,
                        FrameType.CHALLENGE, true, true, true)
                .addCriterion("get_" + HuaJiItems.ORGA_REQUIEM.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(HuaJiItems.ORGA_REQUIEM.get()))
                .save(saver, HuaJiAgeMod.prefix("orga_requiem"), existingFileHelper);

        ItemStack orgaStand = HuaJiItems.DISC.get().getDefaultInstance();
        ItemTagHelper.setString(orgaStand, ItemDisc.DISC_STAND_KEY, HuaJiStands.ORGA_REQUIEM.getKey().location().toString());
        ItemTagHelper.setInt(orgaStand, ItemDisc.DISC_STAND_LEVEL_KEY, 0);
        Advancement.Builder.advancement().parent(orgaRequiem)
                .display(orgaStand,
                        Component.translatable("advancement.huajiage.orga_stand.title"),
                        Component.translatable("advancement.huajiage.orga_stand.desc"),
                        null,
                        FrameType.CHALLENGE, true, true, true)
                .addCriterion("custom", new ImpossibleTrigger.TriggerInstance())
                .save(saver, AdvancementHelper.ORGA_STAND, existingFileHelper);
    }
}
