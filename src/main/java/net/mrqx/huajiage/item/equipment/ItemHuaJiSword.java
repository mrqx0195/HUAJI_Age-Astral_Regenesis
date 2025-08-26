package net.mrqx.huajiage.item.equipment;

import net.minecraft.world.item.SwordItem;
import net.mrqx.huajiage.init.HuaJiTiers;

public class ItemHuaJiSword extends SwordItem {
    public ItemHuaJiSword(Properties properties) {
        super(HuaJiTiers.HUAJI.get(), 8, -2.4F, properties);
    }
}
