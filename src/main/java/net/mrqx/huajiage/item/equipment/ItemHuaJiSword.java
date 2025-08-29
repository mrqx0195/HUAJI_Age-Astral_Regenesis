package net.mrqx.huajiage.item.equipment;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.mrqx.huajiage.init.HuaJiTiers;

public class ItemHuaJiSword extends SwordItem {
    public ItemHuaJiSword() {
        super(HuaJiTiers.HUAJI.get(), 8, -2.4F, new Item.Properties().rarity(Rarity.UNCOMMON).durability(1200));
    }
}
