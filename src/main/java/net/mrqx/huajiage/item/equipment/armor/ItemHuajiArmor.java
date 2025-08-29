package net.mrqx.huajiage.item.equipment.armor;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public abstract class ItemHuajiArmor extends ArmorItem {
    public ItemHuajiArmor(Type pType) {
        super(HuaJiArmorMaterials.HUAJI, pType, new Item.Properties().rarity(Rarity.UNCOMMON));
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, @Nullable String type) {
        return String.format(java.util.Locale.ROOT, "huajiage:textures/models/armor/huaji_layer_%d%s.png", (slot == EquipmentSlot.LEGS ? 2 : 1), type == null ? "" : String.format(java.util.Locale.ROOT, "_%s", type));
    }

    public static class ItemHuajiArmorHelmet extends ItemHuajiArmor {
        public ItemHuajiArmorHelmet() {
            super(Type.HELMET);
        }
    }

    public static class ItemHuajiArmorChestplate extends ItemHuajiArmor {
        public ItemHuajiArmorChestplate() {
            super(Type.CHESTPLATE);
        }

        @Override
        public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
            if (player.getInventory().armor.get(2).equals(stack)) {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 0, false, false));
            }
        }
    }

    public static class ItemHuajiArmorLeggings extends ItemHuajiArmor {
        public ItemHuajiArmorLeggings() {
            super(Type.LEGGINGS);
        }

        @Override
        public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
            if (player.getInventory().armor.get(1).equals(stack)) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 0, false, false));
            }
        }
    }

    public static class ItemHuajiArmorBoots extends ItemHuajiArmor {
        public ItemHuajiArmorBoots() {
            super(Type.BOOTS);
        }

        @Override
        public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
            if (player.getInventory().armor.get(0).equals(stack)) {
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 200, 0, false, false));
            }
        }
    }
}
