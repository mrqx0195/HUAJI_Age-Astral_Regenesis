package net.mrqx.huajiage.block.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.mrqx.huajiage.block.blockentity.HuaJiBlenderBlockEntity;
import net.mrqx.huajiage.registy.HuaJiMenus;
import net.mrqx.huajiage.registy.HuaJiRecipes;
import net.mrqx.huajiage.utils.HuaJiUtils;

public class HuaJiBlenderMenu extends AbstractContainerMenu {
    protected final Level level;
    protected final Container container;
    protected final ContainerData data;

    public static final int HOTBAR_SLOT_COUNT = 9;
    public static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    public static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    public static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    public static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

    public static final int FUEL_SLOTS_COUNT = 1;
    public static final int INPUT_SLOTS_COUNT = 1;
    public static final int OUTPUT_SLOTS_COUNT = 1;
    public static final int FURNACE_SLOTS_COUNT = FUEL_SLOTS_COUNT + INPUT_SLOTS_COUNT + OUTPUT_SLOTS_COUNT;

    public static final int INPUT_SLOT_INDEX = 0;
    public static final int FUEL_SLOT_INDEX = INPUT_SLOT_INDEX + INPUT_SLOTS_COUNT;
    public static final int OUTPUT_SLOT_INDEX = FUEL_SLOT_INDEX + FUEL_SLOTS_COUNT;

    public HuaJiBlenderMenu(int pContainerId, Inventory pPlayerInventory) {
        this(HuaJiMenus.HUAJI_BLENDER.get(), pContainerId, pPlayerInventory, new SimpleContainer(3), new SimpleContainerData(4));
    }

    public HuaJiBlenderMenu(MenuType<?> pMenuType, int pContainerId, Inventory pPlayerInventory, Container pContainer, ContainerData pData) {
        super(pMenuType, pContainerId);
        this.container = pContainer;
        this.data = pData;
        this.level = pPlayerInventory.player.level();

        this.addSlot(new Slot(this.container, INPUT_SLOT_INDEX, 56, 30));
        this.addSlot(new HuaJiBlenderFuelSlot(this, this.container, FUEL_SLOT_INDEX, 19, 30));
        this.addSlot(new HuaJiResultSlot(pPlayerInventory.player, this.container, OUTPUT_SLOT_INDEX, 110, 30));

        final int hotbarXpos = 8;
        final int hotbarYpos = 132;
        for (int x = 0; x < HOTBAR_SLOT_COUNT; x++) {
            this.addSlot(new Slot(pPlayerInventory, x, hotbarXpos + 18 * x, hotbarYpos));
        }

        final int playerInventoryXpos = 8;
        final int playerInventoryYpos = 74;
        for (int i = 0; i < PLAYER_INVENTORY_ROW_COUNT; i++) {
            for (int j = 0; j < PLAYER_INVENTORY_COLUMN_COUNT; j++) {
                int slotId = HOTBAR_SLOT_COUNT + i * PLAYER_INVENTORY_COLUMN_COUNT + j;
                int posX = playerInventoryXpos + j * 18;
                int posY = playerInventoryYpos + i * 18;
                this.addSlot(new Slot(pPlayerInventory, slotId, posX, posY));
            }
        }

        this.addDataSlots(this.data);
    }

    public double getProgress() {
        int i = this.data.get(HuaJiBlenderBlockEntity.DATA_COOKING_PROGRESS);
        int j = this.data.get(HuaJiBlenderBlockEntity.DATA_COOKING_TOTAL_TIME);
        return j != 0 && i != 0 ? (double) i / j : 0;
    }

    public double getFuel() {
        int i = this.data.get(HuaJiBlenderBlockEntity.DATA_LIT_DURATION);
        if (i == 0) {
            i = 200;
        }

        return (double) this.data.get(HuaJiBlenderBlockEntity.DATA_LIT_TIME) / i;
    }

    protected boolean isFuel(ItemStack pStack) {
        return HuaJiUtils.isTagFuel(pStack, HuaJiBlenderBlockEntity.HUAJI_BLENDER_TIME_PREFIX, 19);
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return this.container.stillValid(pPlayer);
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(pIndex);
        if (slot.hasItem()) {
            ItemStack itemStack1 = slot.getItem();
            itemStack = itemStack1.copy();
            if (pIndex == OUTPUT_SLOT_INDEX) {
                if (!this.moveItemStackTo(itemStack1, FURNACE_SLOTS_COUNT, FURNACE_SLOTS_COUNT + VANILLA_SLOT_COUNT, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(itemStack1, itemStack);
            } else if (pIndex != INPUT_SLOT_INDEX && pIndex != FUEL_SLOT_INDEX) {
                if (this.canProcess(itemStack1)) {
                    if (!this.moveItemStackTo(itemStack1, INPUT_SLOT_INDEX, FUEL_SLOT_INDEX, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isFuel(itemStack1)) {
                    if (!this.moveItemStackTo(itemStack1, FUEL_SLOT_INDEX, OUTPUT_SLOT_INDEX, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (pIndex >= FURNACE_SLOTS_COUNT
                        && pIndex < FURNACE_SLOTS_COUNT + VANILLA_SLOT_COUNT - HOTBAR_SLOT_COUNT) {
                    if (!this.moveItemStackTo(itemStack1, FURNACE_SLOTS_COUNT + VANILLA_SLOT_COUNT - HOTBAR_SLOT_COUNT, FURNACE_SLOTS_COUNT + VANILLA_SLOT_COUNT, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (pIndex >= FURNACE_SLOTS_COUNT + VANILLA_SLOT_COUNT - HOTBAR_SLOT_COUNT
                        && pIndex < FURNACE_SLOTS_COUNT + VANILLA_SLOT_COUNT
                        && !this.moveItemStackTo(itemStack1, FURNACE_SLOTS_COUNT, FURNACE_SLOTS_COUNT + VANILLA_SLOT_COUNT - HOTBAR_SLOT_COUNT, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemStack1, FURNACE_SLOTS_COUNT, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemStack1.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(pPlayer, itemStack1);
        }

        return itemStack;
    }

    protected boolean canProcess(ItemStack pStack) {
        return this.level.getRecipeManager().getRecipeFor(HuaJiRecipes.HUAJI_BLENDER_RECIPE_TYPE.get(), new SimpleContainer(pStack), this.level).isPresent();
    }
}
