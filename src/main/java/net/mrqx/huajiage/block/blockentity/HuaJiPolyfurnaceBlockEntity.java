package net.mrqx.huajiage.block.blockentity;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.minecraftforge.network.NetworkHooks;
import net.mrqx.huajiage.block.BlockHuaJiPolyfurnace;
import net.mrqx.huajiage.block.inventory.HuaJiPolyfurnaceMenu;
import net.mrqx.huajiage.config.HuaJiCommonConfig;
import net.mrqx.huajiage.recipe.HuaJiPolyfurnaceRecipe;
import net.mrqx.huajiage.registy.HuaJiBlocks;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.registy.HuaJiMenus;
import net.mrqx.huajiage.registy.HuaJiRecipes;
import net.mrqx.huajiage.utils.HuaJiUtils;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HuaJiPolyfurnaceBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer, RecipeHolder, StackedContentsCompatible {
    protected static final int SLOT_INPUT = HuaJiPolyfurnaceMenu.INPUT_SLOT_INDEX;
    protected static final int SLOT_FUEL = HuaJiPolyfurnaceMenu.FUEL_SLOT_INDEX;
    protected static final int SLOT_RESULT = HuaJiPolyfurnaceMenu.OUTPUT_SLOT_INDEX;
    private static final int[] SLOTS_FOR_UP = new int[]{0};
    private static final int[] SLOTS_FOR_DOWN = new int[]{2, 1};
    private static final int[] SLOTS_FOR_SIDES = new int[]{1};
    public static final int DATA_LIT_TIME = 0;
    public static final int DATA_PROCESSING_PROGRESS = 1;
    public static final int DATA_PROCESSING_TOTAL_TIME = 2;
    public static final int DATA_POLYFURNACE_POOL = 3;
    public static final int DATA_FE_ENERGY = 4;
    public static final int NUM_DATA_VALUES = 5;
    public static final int BURN_TIME_STANDARD = 200;

    public static final int TOTAL_POINT = HuaJiCommonConfig.POLYFURNACE_TOTAL_POINT.get();
    public static final int MAX_ENERGY = HuaJiCommonConfig.POLYFURNACE_MAX_ENERGY.get();
    public static final int FE_CAPACITY = HuaJiCommonConfig.POLYFURNACE_MAX_FE.get();
    public static final int FE_CONVERT = HuaJiCommonConfig.POLYFURNACE_FE_CONVERT.get();

    public static final String HUAJI_POLYFURNACE_TIME_PREFIX = "huaji_polyfurnace/time_";
    protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
    public int energy;
    public int processingProgress;
    public int processingTotalTime;
    public int polyfurnacePool;
    public int feEnergy;
    protected final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int p_58431_) {
            return switch (p_58431_) {
                case DATA_LIT_TIME -> HuaJiPolyfurnaceBlockEntity.this.energy;
                case DATA_PROCESSING_PROGRESS -> HuaJiPolyfurnaceBlockEntity.this.processingProgress;
                case DATA_PROCESSING_TOTAL_TIME -> HuaJiPolyfurnaceBlockEntity.this.processingTotalTime;
                case DATA_POLYFURNACE_POOL -> HuaJiPolyfurnaceBlockEntity.this.polyfurnacePool;
                case DATA_FE_ENERGY -> HuaJiPolyfurnaceBlockEntity.this.feEnergy;
                default -> 0;
            };
        }

        @Override
        public void set(int p_58433_, int p_58434_) {
            switch (p_58433_) {
                case DATA_LIT_TIME:
                    HuaJiPolyfurnaceBlockEntity.this.energy = p_58434_;
                    break;
                case DATA_PROCESSING_PROGRESS:
                    HuaJiPolyfurnaceBlockEntity.this.processingProgress = p_58434_;
                    break;
                case DATA_PROCESSING_TOTAL_TIME:
                    HuaJiPolyfurnaceBlockEntity.this.processingTotalTime = p_58434_;
                    break;
                case DATA_POLYFURNACE_POOL:
                    HuaJiPolyfurnaceBlockEntity.this.polyfurnacePool = p_58434_;
                    break;
                case DATA_FE_ENERGY:
                    HuaJiPolyfurnaceBlockEntity.this.feEnergy = p_58434_;
                    break;
            }

        }

        @Override
        public int getCount() {
            return NUM_DATA_VALUES;
        }
    };
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
    private final RecipeManager.CachedCheck<Container, HuaJiPolyfurnaceRecipe> quickCheck;

    public HuaJiPolyfurnaceBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(HuaJiBlocks.HUAJI_POLYFURNACE_BLOCK_ENTITY.get(), pPos, pBlockState);
        this.quickCheck = RecipeManager.createCheck(HuaJiRecipes.HUAJI_POLYFURNACE_RECIPE_TYPE.get());
    }

    private boolean hasEnergy() {
        return this.energy > 0;
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(pTag, this.items);
        this.energy = pTag.getInt("BurnTime");
        this.processingProgress = pTag.getInt("ProcessTime");
        this.processingTotalTime = pTag.getInt("ProcessTimeTotal");
        this.polyfurnacePool = pTag.getInt("PolyfurnacePool");
        this.feEnergy = pTag.getInt("FEEnergy");
        CompoundTag compoundtag = pTag.getCompound("RecipesUsed");
        for (String s : compoundtag.getAllKeys()) {
            this.recipesUsed.put(new ResourceLocation(s), compoundtag.getInt(s));
        }
        if (pTag.contains("Energy")) {
            this.energyHandler.ifPresent(energyStorage -> energyStorage.deserializeNBT(pTag.get("Energy")));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt("BurnTime", this.energy);
        pTag.putInt("ProcessTime", this.processingProgress);
        pTag.putInt("ProcessTimeTotal", this.processingTotalTime);
        pTag.putInt("PolyfurnacePool", this.polyfurnacePool);
        pTag.putInt("FEEnergy", this.feEnergy);
        ContainerHelper.saveAllItems(pTag, this.items);
        CompoundTag compoundtag = new CompoundTag();
        this.recipesUsed.forEach((resourceLocation, integer) -> compoundtag.putInt(resourceLocation.toString(), integer));
        pTag.put("RecipesUsed", compoundtag);
        this.energyHandler.ifPresent(energyStorage -> pTag.put("Energy", energyStorage.serializeNBT()));
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.huaji_polyfurnace");
    }

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return new HuaJiPolyfurnaceMenu(HuaJiMenus.HUAJI_POLYFURNACE.get(), pContainerId, pInventory, this, this.dataAccess, Objects.requireNonNull(this.level), this.worldPosition);
    }

    public void openMenu(Player pPlayer) {
        if (pPlayer instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openScreen(serverPlayer, this, this.worldPosition);
        }
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, HuaJiPolyfurnaceBlockEntity pBlockEntity) {
        boolean flag = pBlockEntity.processingProgress > 0;
        boolean hasChanged = false;
        ItemStack fuel = pBlockEntity.items.get(SLOT_FUEL);
        boolean hasInput = !pBlockEntity.items.get(SLOT_INPUT).isEmpty();
        boolean hasFuel = !fuel.isEmpty();
        boolean shouldWork = pBlockEntity.hasEnergy() || hasFuel && hasInput;

        if (pBlockEntity.energy < MAX_ENERGY) {
            if (pBlockEntity.energy + pBlockEntity.getFuelEnergy(fuel) < MAX_ENERGY) {
                pBlockEntity.energy += pBlockEntity.getFuelEnergy(fuel);
                if (pBlockEntity.hasEnergy()) {
                    hasChanged = !shouldWork;
                    if (fuel.hasCraftingRemainingItem()) {
                        pBlockEntity.items.set(SLOT_FUEL, fuel.getCraftingRemainingItem());
                    } else if (hasFuel) {
                        fuel.shrink(1);
                        if (fuel.isEmpty()) {
                            pBlockEntity.items.set(SLOT_FUEL, fuel.getCraftingRemainingItem());
                        }
                    }
                }
            }

            pBlockEntity.energyHandler.ifPresent(energyStorage -> {
                int extract = energyStorage.extractEnergy(energyStorage.getMaxEnergyStored(), true);
                if (extract >= FE_CONVERT) {
                    int addEnergy = Math.min(extract / FE_CONVERT, MAX_ENERGY - pBlockEntity.energy);
                    if (addEnergy > 0) {
                        energyStorage.extractEnergy(addEnergy * FE_CONVERT, false);
                        pBlockEntity.energy += addEnergy;
                    }
                }
                pBlockEntity.feEnergy = energyStorage.getEnergyStored();
            });
        }

        if (shouldWork) {
            HuaJiPolyfurnaceRecipe recipe;
            if (hasInput) {
                recipe = pBlockEntity.quickCheck.getRecipeFor(pBlockEntity, pLevel).orElse(null);
            } else {
                recipe = null;
            }

            int i = pBlockEntity.getMaxStackSize();

            if (pBlockEntity.canBurn(pLevel.registryAccess(), recipe, pBlockEntity.items, i)) {
                if (pBlockEntity.hasEnergy()) {
                    ++pBlockEntity.processingProgress;
                    --pBlockEntity.energy;
                    if (pBlockEntity.processingProgress == pBlockEntity.processingTotalTime) {
                        pBlockEntity.processingProgress = 0;
                        pBlockEntity.processingTotalTime = getTotalProcessTime(pLevel, pBlockEntity);
                        if (pBlockEntity.burn(pLevel.registryAccess(), recipe, pBlockEntity.items, i)) {
                            pBlockEntity.setRecipeUsed(recipe);
                        }
                        flag = false;
                    } else {
                        hasChanged = true;
                    }
                }
            } else {
                pBlockEntity.processingProgress = 0;
                hasChanged = true;
            }
        }


        while (pBlockEntity.polyfurnacePool > TOTAL_POINT) {
            ItemStack result = HuaJiItems.INFINITE_UNIVERSE_STAR.get().getDefaultInstance();
            ItemStack output = pBlockEntity.getItem(SLOT_RESULT);
            if (output.isEmpty()) {
                pBlockEntity.setItem(SLOT_RESULT, result.copy());
            } else if (output.is(result.getItem())) {
                output.grow(result.getCount());
            }
            pBlockEntity.polyfurnacePool -= TOTAL_POINT;
        }

        if (flag != (pBlockEntity.processingProgress > 0) || hasChanged) {
            hasChanged = true;
            pState = pState.setValue(BlockHuaJiPolyfurnace.LIT, pBlockEntity.processingProgress > 0);
            pLevel.setBlock(pPos, pState, 3);
        }

        if (hasChanged) {
            setChanged(pLevel, pPos, pState);
        }
    }

    private boolean canBurn(RegistryAccess pRegistryAccess, @Nullable HuaJiPolyfurnaceRecipe pRecipe, NonNullList<ItemStack> pInventory, int pMaxStackSize) {
        if (!pInventory.get(SLOT_INPUT).isEmpty() && pRecipe != null) {
            ItemStack input = pRecipe.assemble(this, pRegistryAccess);
            if (input.isEmpty()) {
                return false;
            } else {
                ItemStack output = pInventory.get(SLOT_RESULT);
                if (output.isEmpty()) {
                    return true;
                } else if (!ItemStack.isSameItem(output, input)) {
                    return false;
                } else if (output.getCount() + input.getCount() <= pMaxStackSize && output.getCount() + input.getCount() <= output.getMaxStackSize()) {
                    return true;
                } else {
                    return output.getCount() + input.getCount() <= input.getMaxStackSize();
                }
            }
        } else {
            return false;
        }
    }

    private boolean burn(RegistryAccess pRegistryAccess, @Nullable HuaJiPolyfurnaceRecipe pRecipe, NonNullList<ItemStack> pInventory, int pMaxStackSize) {
        if (pRecipe != null && this.canBurn(pRegistryAccess, pRecipe, pInventory, pMaxStackSize)) {
            ItemStack input = pInventory.get(SLOT_INPUT);
            input.shrink(1);
            this.polyfurnacePool += pRecipe.point();
            while (this.polyfurnacePool > TOTAL_POINT) {
                ItemStack result = pRecipe.assemble(this, pRegistryAccess);
                ItemStack output = pInventory.get(SLOT_RESULT);
                if (output.isEmpty()) {
                    pInventory.set(SLOT_RESULT, result.copy());
                } else if (output.is(result.getItem())) {
                    output.grow(result.getCount());
                }
                this.polyfurnacePool -= TOTAL_POINT;
            }
            return true;
        } else {
            return false;
        }
    }

    public int getFuelEnergy(ItemStack pFuel) {
        if (pFuel.isEmpty()) {
            return 0;
        } else {
            return HuaJiUtils.getTagFuel(pFuel, HUAJI_POLYFURNACE_TIME_PREFIX, 23);
        }
    }

    public static int getTotalProcessTime(Level pLevel, HuaJiPolyfurnaceBlockEntity pBlockEntity) {
        return pBlockEntity.quickCheck.getRecipeFor(pBlockEntity, pLevel).map(HuaJiPolyfurnaceRecipe::processTime).orElse(BURN_TIME_STANDARD);
    }

    public static boolean isFuel(ItemStack pStack) {
        return HuaJiUtils.isTagFuel(pStack, HUAJI_POLYFURNACE_TIME_PREFIX, 23);
    }

    @Override
    public int[] getSlotsForFace(Direction pSide) {
        if (pSide == Direction.DOWN) {
            return SLOTS_FOR_DOWN;
        } else {
            return pSide == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int pIndex, ItemStack pItemStack, @Nullable Direction pDirection) {
        return this.canPlaceItem(pIndex, pItemStack);
    }

    @Override
    public boolean canTakeItemThroughFace(int pIndex, ItemStack pStack, Direction pDirection) {
        return true;
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack getItem(int pIndex) {
        return this.items.get(pIndex);
    }

    @Override
    public ItemStack removeItem(int pIndex, int pCount) {
        return ContainerHelper.removeItem(this.items, pIndex, pCount);
    }

    @Override
    public ItemStack removeItemNoUpdate(int pIndex) {
        return ContainerHelper.takeItem(this.items, pIndex);
    }

    @Override
    public void setItem(int pIndex, ItemStack pStack) {
        ItemStack itemstack = this.items.get(pIndex);
        boolean flag = !pStack.isEmpty() && ItemStack.isSameItemSameTags(itemstack, pStack);
        this.items.set(pIndex, pStack);
        if (pStack.getCount() > this.getMaxStackSize()) {
            pStack.setCount(this.getMaxStackSize());
        }

        if (pIndex == 0 && !flag) {
            this.processingTotalTime = getTotalProcessTime(Objects.requireNonNull(this.level), this);
            this.processingProgress = 0;
            this.setChanged();
        }

    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return Container.stillValidBlockEntity(this, pPlayer);
    }

    @Override
    public boolean canPlaceItem(int pIndex, ItemStack pStack) {
        if (pIndex == SLOT_RESULT) {
            return false;
        } else if (pIndex != SLOT_FUEL) {
            return true;
        } else {
            ItemStack itemstack = this.items.get(1);
            return isFuel(itemstack);
        }
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    @Override
    public void setRecipeUsed(@org.jetbrains.annotations.Nullable Recipe<?> pRecipe) {
        if (pRecipe != null) {
            ResourceLocation resourcelocation = pRecipe.getId();
            this.recipesUsed.addTo(resourcelocation, 1);
        }
    }

    @Override
    @Nullable
    public HuaJiPolyfurnaceRecipe getRecipeUsed() {
        return null;
    }

    @Override
    public void awardUsedRecipes(Player pPlayer, List<ItemStack> pItems) {
    }

    public void awardUsedRecipesAndPopExperience(ServerPlayer pPlayer) {
        List<HuaJiPolyfurnaceRecipe> list = this.getRecipesToAwardAndPopExperience(pPlayer.serverLevel(), pPlayer.position());
        pPlayer.awardRecipes(list.stream().map(huaJiPolyfurnaceRecipe -> (Recipe<?>) huaJiPolyfurnaceRecipe).collect(Collectors.toSet()));

        for (HuaJiPolyfurnaceRecipe recipe : list) {
            if (recipe != null) {
                pPlayer.triggerRecipeCrafted(recipe, this.items);
            }
        }

        this.recipesUsed.clear();
    }

    public List<HuaJiPolyfurnaceRecipe> getRecipesToAwardAndPopExperience(ServerLevel pLevel, Vec3 pPopVec) {
        List<HuaJiPolyfurnaceRecipe> list = Lists.newArrayList();

        for (Object2IntMap.Entry<ResourceLocation> entry : this.recipesUsed.object2IntEntrySet()) {
            pLevel.getRecipeManager().byKey(entry.getKey()).ifPresent((recipe) -> {
                if (recipe instanceof HuaJiPolyfurnaceRecipe huaJiPolyfurnaceRecipe) {
                    list.add(huaJiPolyfurnaceRecipe);
                    createExperience(pLevel, pPopVec, entry.getIntValue(), huaJiPolyfurnaceRecipe.experience());
                }
            });
        }

        return list;
    }

    private static void createExperience(ServerLevel pLevel, Vec3 pPopVec, int pRecipeIndex, float pExperience) {
        int i = Mth.floor((float) pRecipeIndex * pExperience);
        float f = Mth.frac((float) pRecipeIndex * pExperience);
        if (f > 0.0F && Math.random() < (double) f) {
            ++i;
        }

        ExperienceOrb.award(pLevel, pPopVec, i);
    }

    @Override
    public void fillStackedContents(StackedContents pHelper) {
        for (ItemStack itemstack : this.items) {
            pHelper.accountStack(itemstack);
        }

    }

    private LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

    public final LazyOptional<EnergyStorage> energyHandler = LazyOptional.of(() -> new EnergyStorage(FE_CAPACITY) {
        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            setChanged();
            return super.extractEnergy(maxExtract, simulate);
        }

        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            setChanged();
            return super.receiveEnergy(maxReceive, simulate);
        }
    });

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (capability == ForgeCapabilities.ITEM_HANDLER && facing != null && !this.remove) {
            return switch (facing) {
                case UP -> handlers[0].cast();
                case DOWN -> handlers[1].cast();
                default -> handlers[2].cast();
            };
        }
        if (capability == ForgeCapabilities.ENERGY) {
            return energyHandler.cast();
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        for (LazyOptional<? extends IItemHandler> handler : handlers) {
            handler.invalidate();
        }
    }

    @Override
    public void reviveCaps() {
        super.reviveCaps();
        this.handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
    }
}
