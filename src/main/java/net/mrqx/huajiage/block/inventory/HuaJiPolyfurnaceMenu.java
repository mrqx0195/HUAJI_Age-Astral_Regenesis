package net.mrqx.huajiage.block.inventory;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.mrqx.huajiage.block.blockentity.HuaJiPolyfurnaceBlockEntity;
import net.mrqx.huajiage.registy.HuaJiMenus;
import net.mrqx.huajiage.registy.HuaJiRecipes;
import net.mrqx.huajiage.utils.HuaJiUtils;
import org.jetbrains.annotations.Nullable;

public class HuaJiPolyfurnaceMenu extends AbstractContainerMenu {
    protected final Level level;
    protected final Container container;
    protected final ContainerData data;
    @Nullable
    public final HuaJiPolyfurnaceBlockEntity blockEntity;

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

    public HuaJiPolyfurnaceMenu(int pContainerId, Inventory pPlayerInventory, @Nullable Level level, @Nullable BlockPos worldPosition) {
        this(HuaJiMenus.HUAJI_POLYFURNACE.get(), pContainerId, pPlayerInventory, new SimpleContainer(3), new SimpleContainerData(5), level, worldPosition);
    }

    public HuaJiPolyfurnaceMenu(MenuType<?> pMenuType, int pContainerId, Inventory pPlayerInventory, Container pContainer, ContainerData pData, @Nullable Level level, @Nullable BlockPos worldPosition) {
        super(pMenuType, pContainerId);
        this.container = pContainer;
        this.data = pData;
        this.level = pPlayerInventory.player.level();

        this.addSlot(new Slot(this.container, INPUT_SLOT_INDEX, 44, 25));
        this.addSlot(new HuaJiPolyfurnaceFuelSlot(this, this.container, FUEL_SLOT_INDEX, 44, 48));
        this.addSlot(new HuaJiResultSlot(pPlayerInventory.player, this.container, OUTPUT_SLOT_INDEX, 151, 25));

        final int hotbarXpos = 16;
        final int hotbarYpos = 129;
        for (int x = 0; x < HOTBAR_SLOT_COUNT; x++) {
            this.addSlot(new Slot(pPlayerInventory, x, hotbarXpos + 18 * x, hotbarYpos));
        }

        final int playerInventoryXpos = 16;
        final int playerInventoryYpos = 71;
        for (int i = 0; i < PLAYER_INVENTORY_ROW_COUNT; i++) {
            for (int j = 0; j < PLAYER_INVENTORY_COLUMN_COUNT; j++) {
                int slotId = HOTBAR_SLOT_COUNT + i * PLAYER_INVENTORY_COLUMN_COUNT + j;
                int posX = playerInventoryXpos + j * 18;
                int posY = playerInventoryYpos + i * 18;
                this.addSlot(new Slot(pPlayerInventory, slotId, posX, posY));
            }
        }

        this.addDataSlots(this.data);

        if (level != null && worldPosition != null && level.getBlockEntity(worldPosition) instanceof HuaJiPolyfurnaceBlockEntity huaJiPolyfurnaceBlockEntity) {
            blockEntity = huaJiPolyfurnaceBlockEntity;
        } else {
            blockEntity = null;
        }
    }

    public Container getContainer() {
        return this.container;
    }

    public double getProgress() {
        int i = this.data.get(HuaJiPolyfurnaceBlockEntity.DATA_PROCESSING_PROGRESS);
        int j = this.data.get(HuaJiPolyfurnaceBlockEntity.DATA_PROCESSING_TOTAL_TIME);
        return j != 0 && i != 0 ? (double) i / j : 0;
    }

    public int getEnergy() {
        return this.data.get(HuaJiPolyfurnaceBlockEntity.DATA_LIT_TIME);
    }

    public int getPool() {
        return this.data.get(HuaJiPolyfurnaceBlockEntity.DATA_POLYFURNACE_POOL);
    }

    public double getFuel() {
        return (double) this.data.get(HuaJiPolyfurnaceBlockEntity.DATA_LIT_TIME) / HuaJiPolyfurnaceBlockEntity.MAX_ENERGY;
    }

    protected boolean isFuel(ItemStack pStack) {
        return HuaJiUtils.isTagFuel(pStack, HuaJiPolyfurnaceBlockEntity.HUAJI_POLYFURNACE_TIME_PREFIX, 23);
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
        return this.level.getRecipeManager().getRecipeFor(HuaJiRecipes.HUAJI_POLYFURNACE_RECIPE_TYPE.get(), new SimpleContainer(pStack), this.level).isPresent();
    }
}
