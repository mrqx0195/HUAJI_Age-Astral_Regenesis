package net.mrqx.huajiage.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.mrqx.huajiage.item.equipment.armor.ItemOrgaArmor;
import net.mrqx.huajiage.utils.ItemTagHelper;

public class ItemInfiniteCharm extends BaseItem {
    public ItemInfiniteCharm(Properties properties) {
        super(properties);
    }

    @Override
    public String getDescriptionId(ItemStack stack) {
        return super.getDescriptionId(stack) + (ItemTagHelper.getBoolean(stack, "orga", false) ? ".orga" : "");
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        tick(stack, entity);
    }

    protected static void tick(ItemStack stack, Entity entity) {
        if (entity instanceof LivingEntity living) {
            for (EquipmentSlot equipmentslot : EquipmentSlot.values()) {
                ItemStack itemstack = living.getItemBySlot(equipmentslot);
                if (!itemstack.isEmpty() && itemstack.isDamaged()) {
                    itemstack.setDamageValue(0);
                }
            }
            ItemTagHelper.setBoolean(stack, "orga", ItemOrgaArmor.hasAllOrgaArmor(living));
        }
    }
}
