package net.mrqx.huajiage.block.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class HuaJiBlenderFuelSlot extends Slot {
    private final HuaJiBlenderMenu menu;

    public HuaJiBlenderFuelSlot(HuaJiBlenderMenu pFurnaceMenu, Container pFurnaceContainer, int pSlot, int xPosition, int yPosition) {
        super(pFurnaceContainer, pSlot, xPosition, yPosition);
        this.menu = pFurnaceMenu;
    }

    @Override
    public boolean mayPlace(ItemStack pStack) {
        return this.menu.isFuel(pStack);
    }
}
