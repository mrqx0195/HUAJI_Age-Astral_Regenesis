package net.mrqx.huajiage.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.utils.ItemTagHelper;

public class ItemOrgaRequiem extends BaseItem {
    public static final String ORGA_REQUIEM_OWNER_KEY = HuaJiAgeMod.MODID + "." + "orgaRequiemOwner";

    public static boolean hasValidOrgaRequiem(Player player) {
        return player.getInventory().hasAnyMatching(itemStack ->
                itemStack.is(HuaJiItems.ORGA_REQUIEM.get())
                        && ItemTagHelper.getString(itemStack, ORGA_REQUIEM_OWNER_KEY, "").equals(player.getStringUUID()));
    }

    public static boolean hasOrgaRequiem(Player player) {
        return player.getInventory().hasAnyMatching(itemStack -> itemStack.is(HuaJiItems.ORGA_REQUIEM.get()));
    }

    public ItemOrgaRequiem() {
        super(new Item.Properties().rarity(Rarity.EPIC));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!stack.hasTag() || ItemTagHelper.getString(stack, ORGA_REQUIEM_OWNER_KEY, "").isEmpty()) {
            ItemTagHelper.setString(stack, ORGA_REQUIEM_OWNER_KEY, entity.getStringUUID());
        }
    }
}
