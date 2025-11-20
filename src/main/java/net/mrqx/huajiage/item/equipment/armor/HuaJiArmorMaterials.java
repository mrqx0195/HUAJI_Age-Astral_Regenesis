package net.mrqx.huajiage.item.equipment.armor;

import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.registy.HuaJiSoundEvents;

import java.util.EnumMap;
import java.util.function.Supplier;

public enum HuaJiArmorMaterials implements ArmorMaterial {
    /**
     * @see ItemHuajiArmor
     */
    HUAJI("huaji", 20, Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 2);
        map.put(ArmorItem.Type.LEGGINGS, 5);
        map.put(ArmorItem.Type.CHESTPLATE, 6);
        map.put(ArmorItem.Type.HELMET, 2);
    }), 16, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.1F, () -> Ingredient.of(HuaJiItems.HUAJI.get(), HuaJiItems.ANTI_HUAJI.get())),
    /**
     * @see ItemOrgaArmor
     */
    ORGA("orga", 19, Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 5);
        map.put(ArmorItem.Type.LEGGINGS, 5);
        map.put(ArmorItem.Type.CHESTPLATE, 5);
        map.put(ArmorItem.Type.HELMET, 5);
    }), 44, SoundEvents.ARMOR_EQUIP_DIAMOND, 3, 0.25F, () -> Ingredient.of(HuaJiItems.HOPE_ELEMENT.get())),
    /**
     * @see ItemFiftyFiftyHelmet
     */
    FIFTY_FIFTY("fifty_fifty", 5050 / 11D, Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 10);
        map.put(ArmorItem.Type.LEGGINGS, 10);
        map.put(ArmorItem.Type.CHESTPLATE, 10);
        map.put(ArmorItem.Type.HELMET, 10);
    }), 16, HuaJiSoundEvents.ARMOR_EQUIP_LORD_LU.get(), 2.5F, 1.0F, () -> Ingredient.of(HuaJiItems.NEUTRON_STAR_FRAGMENT.get()));

    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 13);
        map.put(ArmorItem.Type.LEGGINGS, 15);
        map.put(ArmorItem.Type.CHESTPLATE, 16);
        map.put(ArmorItem.Type.HELMET, 11);
    });
    private final String name;
    private final double durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    HuaJiArmorMaterials(String name, double durabilityMultiplier,
                        EnumMap<ArmorItem.Type, Integer> protectionFunctionForType, int enchantmentValue,
                        SoundEvent sound, float toughness, float knockbackResistance,
                        Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionFunctionForType = protectionFunctionForType;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type pType) {
        return (int) (HEALTH_FUNCTION_FOR_TYPE.get(pType) * this.durabilityMultiplier);
    }

    @Override
    public int getDefenseForType(ArmorItem.Type pType) {
        return this.protectionFunctionForType.get(pType);
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.sound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
