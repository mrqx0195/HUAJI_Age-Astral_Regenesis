package net.mrqx.huajiage.block.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class HuaJiBlenderFuelSlot extends Slot {
    private final HuaJiBlenderMenu menu;

    public HuaJiBlenderFuelSlot(HuaJiBlenderMenu pFurnaceMenu, Container pFurnaceContainer, int pSlot, int pXPosition, int pYPosition) {
        super(pFurnaceContainer, pSlot, pXPosition, pYPosition);
        this.menu = pFurnaceMenu;
    }

    @Override
    public boolean mayPlace(ItemStack pStack) {
        return this.menu.isFuel(pStack);
    }
}
