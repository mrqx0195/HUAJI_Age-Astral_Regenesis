package net.mrqx.huajiage.registy;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.item.*;
import net.mrqx.huajiage.item.equipment.*;
import net.mrqx.huajiage.item.equipment.armor.ItemFiftyFiftyHelmet;
import net.mrqx.huajiage.item.equipment.armor.ItemHuajiArmor;
import net.mrqx.huajiage.item.equipment.armor.ItemOrgaArmor;

public class HuaJiItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HuaJiAgeMod.MODID);

    /**
     * Materials
     */
    public static final RegistryObject<Item> HUAJI = ITEMS.register("huaji", BaseItem::new);
    public static final RegistryObject<Item> ANTI_HUAJI = ITEMS.register("anti_huaji", BaseItem::new);
    public static final RegistryObject<Item> HUAJI_FRAGMENT = ITEMS.register("huaji_fragment", BaseItem::new);
    public static final RegistryObject<Item> HUAJI_INGOT = ITEMS.register("huaji_ingot", BaseItem::new);
    public static final RegistryObject<Item> REDSTONE_DRUSE = ITEMS.register("redstone_druse", BaseItem::new);
    public static final RegistryObject<Item> NEUTRON_STAR_FRAGMENT = ITEMS.register("neutron_star_fragment", BaseItem::new);
    public static final RegistryObject<Item> FLASH_FLOUR = ITEMS.register("flash_flour", BaseItem::new);
    public static final RegistryObject<Item> ETHER_POINT = ITEMS.register("ether_point", BaseItem::new);
    public static final RegistryObject<Item> ETHER_CIRCUMFLUX_BOARD = ITEMS.register("ether_circumflux_board", BaseItem::new);
    public static final RegistryObject<Item> HOPE_ELEMENT = ITEMS.register("hope_element", BaseItem::new);
    public static final RegistryObject<Item> HOPE_FLOWER = ITEMS.register("hope_flower", BaseItem::new);
    public static final RegistryObject<Item> WAVE_CRYSTAL = ITEMS.register("wave_crystal", BaseItem::new);

    /**
     * Stars
     */
    public static final RegistryObject<Item> HUAJI_STAR = ITEMS.register("huaji_star", () -> new BaseItem(new Item.Properties().rarity(Rarity.UNCOMMON).fireResistant()));
    public static final RegistryObject<Item> AIRSPACE_STAR = ITEMS.register("airspace_star", () -> new BaseItem(new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> INFINITE_UNIVERSE_STAR = ITEMS.register("infinite_universe_star", () -> new BaseItem(new Item.Properties().rarity(Rarity.EPIC).fireResistant()));
    public static final RegistryObject<Item> HUAJI_STAR_POLY = ITEMS.register("huaji_star_poly", () -> new BaseItem(new Item.Properties().rarity(Rarity.EPIC).fireResistant()));

    /**
     * Foods
     */
    public static final RegistryObject<Item> EGG_RICE = ITEMS.register("egg_rice", () -> new BaseItem(new Item.Properties().rarity(Rarity.RARE)
            .food(new FoodProperties.Builder().alwaysEat()
                    .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 2400), 1)
                    .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2400, 2), 1)
                    .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2400, 2), 1)
                    .effect(() -> new MobEffectInstance(MobEffects.JUMP, 2400, 2), 1)
                    .nutrition(15).saturationMod(1).build())));

    public static final RegistryObject<Item> ULTIMATE_EGG_RICE = ITEMS.register("ultimate_egg_rice", () -> new BaseItem(new Item.Properties().rarity(Rarity.EPIC)
            .food(new FoodProperties.Builder().alwaysEat().fast()
                    .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 12000), 1)
                    .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 12000, 5), 1)
                    .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 12000, 5), 1)
                    .effect(() -> new MobEffectInstance(MobEffects.JUMP, 12000, 3), 1)
                    .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 12000, 3), 1)
                    .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 12000, 3), 1)
                    .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 12000, 3), 1)
                    .nutrition(22).saturationMod(1).build()).fireResistant()));

    public static final RegistryObject<Item> RAW_GLUTEN = ITEMS.register("raw_gluten", () -> new BaseItem(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F)
                    .nutrition(3).saturationMod(0.3F).build())));

    public static final RegistryObject<Item> BAKED_GLUTEN = ITEMS.register("baked_gluten", () -> new BaseItem(new Item.Properties().rarity(Rarity.RARE)
            .food(new FoodProperties.Builder()
                    .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1800, 0), 1)
                    .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 1800, 2), 1)
                    .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1800, 1), 1)
                    .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1800, 2), 1)
                    .effect(() -> new MobEffectInstance(MobEffects.JUMP, 1800, 2), 1)
                    .nutrition(5).saturationMod(2).build())));

    public static final RegistryObject<Item> DIO_BREAD = ITEMS.register("dio_bread", () -> new BaseItem(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(5).saturationMod(4.8F).build())));

    public static final RegistryObject<Item> REO_CHERRY = ITEMS.register("reo_cherry", () -> new BaseItem(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(2).saturationMod(1).build())));

    /**
     * Weapons
     */
    public static final RegistryObject<Item> HUAJI_SWORD = ITEMS.register("huaji_sword", () -> new ItemHuaJiSword(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> HUAJI_STAR_SWORD = ITEMS.register("huaji_star_sword", () -> new ItemHuajiStarSword(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> HUAJI_LATIAO_SWORD = ITEMS.register("huaji_latiao_sword", () -> new ItemHuajiLaTiaoSword(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> EXGLUTENBUR = ITEMS.register("exglutenbur", () -> new ItemExglutenbur(new Item.Properties().rarity(Rarity.EPIC).fireResistant().durability(5400)));
    public static final RegistryObject<Item> HERO_BOW = ITEMS.register("hero_bow", () -> new ItemHeroBow(new Item.Properties().rarity(Rarity.EPIC).fireResistant().durability(384)));
    // TODO
    public static final RegistryObject<Item> MULTI_KNIFE = ITEMS.register("multi_knife", () -> new BaseItem(new Item.Properties().rarity(Rarity.UNCOMMON)));
    // TODO
    public static final RegistryObject<Item> MULTI_KNIFE_SHINY = ITEMS.register("multi_knife_shiny", () -> new BaseItem(new Item.Properties().rarity(Rarity.RARE)));

    public static final RegistryObject<Item> HUAJI_HELMET = ITEMS.register("huaji_helmet", () -> new ItemHuajiArmor.ItemHuajiArmorHelmet(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> HUAJI_CHESTPLATE = ITEMS.register("huaji_chestplate", () -> new ItemHuajiArmor.ItemHuajiArmorChestplate(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> HUAJI_LEGGINGS = ITEMS.register("huaji_leggings", () -> new ItemHuajiArmor.ItemHuajiArmorLeggings(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> HUAJI_BOOTS = ITEMS.register("huaji_boots", () -> new ItemHuajiArmor.ItemHuajiArmorBoots(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> ORGA_HELMET = ITEMS.register("orga_helmet", () -> new ItemOrgaArmor.ItemOrgaArmorHelmet(new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> ORGA_CHESTPLATE = ITEMS.register("orga_chestplate", () -> new ItemOrgaArmor.ItemOrgaArmorChestplate(new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> ORGA_LEGGINGS = ITEMS.register("orga_leggings", () -> new ItemOrgaArmor.ItemOrgaArmorLeggings(new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> ORGA_BOOTS = ITEMS.register("orga_boots", () -> new ItemOrgaArmor.ItemOrgaArmorBoots(new Item.Properties().rarity(Rarity.RARE).fireResistant()));

    public static final RegistryObject<Item> FIFTY_FIFTY_HELMET = ITEMS.register("50_50_helmet", () -> new ItemFiftyFiftyHelmet(new Item.Properties().rarity(Rarity.EPIC).fireResistant()));
    public static final RegistryObject<Item> LORD_CORE = ITEMS.register("lord_core", () -> new ItemLordCore(new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> LORD_KEY = ITEMS.register("lord_key", () -> new ItemLordKey(new Item.Properties().rarity(Rarity.EPIC).fireResistant()));

    // TODO
    public static final RegistryObject<Item> SECOND_FOIL = ITEMS.register("second_foil", () -> new BaseItem(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> INFINITE_CHARM = ITEMS.register("infinite_charm", () -> new ItemInfiniteCharm(new Item.Properties().rarity(Rarity.UNCOMMON)));

    /**
     * Stands
     */
    public static final RegistryObject<Item> DISC = ITEMS.register("disc", () -> new ItemDisc(new Item.Properties()));
    public static final RegistryObject<Item> SINGULARITY = ITEMS.register("singularity", () -> new ItemSingularity(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> ROAD_ROLLER = ITEMS.register("road_roller", () -> new ItemRoadRoller(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> ARROW_STAND = ITEMS.register("arrow_stand", () -> new ItemArrowStand(new Item.Properties().rarity(Rarity.RARE)));
    // TODO
    public static final RegistryObject<Item> ARROW_REQUIEM = ITEMS.register("arrow_requiem", () -> new BaseItem(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> TAROT = ITEMS.register("tarot", () -> new ItemTarot(new Item.Properties()));

    /**
     * Block Items
     */
    public static final RegistryObject<Item> HUAJI_ORE = ITEMS.register("ore_huaji", () -> new BaseBlockItem(HuaJiBlocks.HUAJI_ORE.get()));
    public static final RegistryObject<Item> HUAJI_STAR_BLOCK = ITEMS.register("huaji_star_block", () -> new BaseBlockItem(HuaJiBlocks.HUAJI_STAR_BLOCK.get(), new Item.Properties().rarity(Rarity.UNCOMMON).fireResistant()));
    public static final RegistryObject<Item> AIRSPACE_STAR_BLOCK = ITEMS.register("airspace_star_block", () -> new BaseBlockItem(HuaJiBlocks.AIRSPACE_STAR_BLOCK.get(), new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> HUAJI_BOMB = ITEMS.register("huaji_bomb", () -> new BaseBlockItem(HuaJiBlocks.HUAJI_BOMB.get(), new Item.Properties()));
    public static final RegistryObject<Item> HUAJI_ANTIMATTER_BOMB = ITEMS.register("huaji_antimatter_bomb", () -> new BaseBlockItem(HuaJiBlocks.HUAJI_ANTIMATTER_BOMB.get(), new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> HUAJI_BLENDER = ITEMS.register("huaji_blender", () -> new BaseBlockItem(HuaJiBlocks.HUAJI_BLENDER.get(), new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> HUAJI_POLYFURNACE = ITEMS.register("huaji_polyfurnace", () -> new BaseBlockItem(HuaJiBlocks.HUAJI_POLYFURNACE.get(), new Item.Properties().rarity(Rarity.EPIC).fireResistant()));
}
