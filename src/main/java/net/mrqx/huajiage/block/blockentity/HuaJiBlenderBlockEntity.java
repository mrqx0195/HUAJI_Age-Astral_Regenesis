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
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.mrqx.huajiage.block.BlockHuaJiBlender;
import net.mrqx.huajiage.block.inventory.HuaJiBlenderMenu;
import net.mrqx.huajiage.recipe.HuaJiBlenderRecipe;
import net.mrqx.huajiage.registy.HuaJiBlocks;
import net.mrqx.huajiage.registy.HuaJiMenus;
import net.mrqx.huajiage.registy.HuaJiRecipes;
import net.mrqx.huajiage.utils.HuaJiUtils;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

public class HuaJiBlenderBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer, RecipeHolder, StackedContentsCompatible {
    protected static final int SLOT_INPUT = HuaJiBlenderMenu.INPUT_SLOT_INDEX;
    protected static final int SLOT_FUEL = HuaJiBlenderMenu.FUEL_SLOT_INDEX;
    protected static final int SLOT_RESULT = HuaJiBlenderMenu.OUTPUT_SLOT_INDEX;
    private static final int[] SLOTS_FOR_UP = new int[]{0};
    private static final int[] SLOTS_FOR_DOWN = new int[]{2, 1};
    private static final int[] SLOTS_FOR_SIDES = new int[]{1};
    public static final int DATA_LIT_TIME = 0;
    public static final int DATA_LIT_DURATION = 1;
    public static final int DATA_COOKING_PROGRESS = 2;
    public static final int DATA_COOKING_TOTAL_TIME = 3;
    public static final int NUM_DATA_VALUES = 4;
    public static final int BURN_TIME_STANDARD = 200;
    public static final String HUAJI_BLENDER_TIME_PREFIX = "huaji_blender/time_";
    protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
    public int litTime;
    public int litDuration;
    public int processingProgress;
    public int processingTotalTime;
    protected final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int p_58431_) {
            return switch (p_58431_) {
                case DATA_LIT_TIME -> HuaJiBlenderBlockEntity.this.litTime;
                case DATA_LIT_DURATION -> HuaJiBlenderBlockEntity.this.litDuration;
                case DATA_COOKING_PROGRESS -> HuaJiBlenderBlockEntity.this.processingProgress;
                case DATA_COOKING_TOTAL_TIME -> HuaJiBlenderBlockEntity.this.processingTotalTime;
                default -> 0;
            };
        }

        @Override
        public void set(int p_58433_, int p_58434_) {
            switch (p_58433_) {
                case DATA_LIT_TIME:
                    HuaJiBlenderBlockEntity.this.litTime = p_58434_;
                    break;
                case DATA_LIT_DURATION:
                    HuaJiBlenderBlockEntity.this.litDuration = p_58434_;
                    break;
                case DATA_COOKING_PROGRESS:
                    HuaJiBlenderBlockEntity.this.processingProgress = p_58434_;
                    break;
                case DATA_COOKING_TOTAL_TIME:
                    HuaJiBlenderBlockEntity.this.processingTotalTime = p_58434_;
            }

        }

        @Override
        public int getCount() {
            return NUM_DATA_VALUES;
        }
    };
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
    private final RecipeManager.CachedCheck<Container, HuaJiBlenderRecipe> quickCheck;

    public HuaJiBlenderBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(HuaJiBlocks.HUAJI_BLENDER_BLOCK_ENTITY.get(), pPos, pBlockState);
        this.quickCheck = RecipeManager.createCheck(HuaJiRecipes.HUAJI_BLENDER_RECIPE_TYPE.get());
    }

    private boolean isLit() {
        return this.litTime > 0;
    }

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(pTag, this.items);
        this.litTime = pTag.getInt("BurnTime");
        this.processingProgress = pTag.getInt("ProcessTime");
        this.processingTotalTime = pTag.getInt("ProcessTimeTotal");
        this.litDuration = this.getBurnDuration(this.items.get(SLOT_FUEL));
        CompoundTag compoundtag = pTag.getCompound("RecipesUsed");

        for (String s : compoundtag.getAllKeys()) {
            this.recipesUsed.put(new ResourceLocation(s), compoundtag.getInt(s));
        }

    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt("BurnTime", this.litTime);
        pTag.putInt("ProcessTime", this.processingProgress);
        pTag.putInt("ProcessTimeTotal", this.processingTotalTime);
        ContainerHelper.saveAllItems(pTag, this.items);
        CompoundTag compoundtag = new CompoundTag();
        this.recipesUsed.forEach((resourceLocation, integer) -> compoundtag.putInt(resourceLocation.toString(), integer));
        pTag.put("RecipesUsed", compoundtag);
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable("container.huaji_blender");
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pInventory) {
        return new HuaJiBlenderMenu(HuaJiMenus.HUAJI_BLENDER.get(), pContainerId, pInventory, this, this.dataAccess);
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, HuaJiBlenderBlockEntity pBlockEntity) {
        boolean flag = pBlockEntity.processingProgress > 0;
        boolean hasChanged = false;
        ItemStack fuel = pBlockEntity.items.get(SLOT_FUEL);
        boolean hasInput = !pBlockEntity.items.get(SLOT_INPUT).isEmpty();
        boolean hasFuel = !fuel.isEmpty();
        boolean shouldWork = pBlockEntity.isLit() || hasFuel && hasInput;

        if (shouldWork) {
            HuaJiBlenderRecipe recipe;
            if (hasInput) {
                recipe = pBlockEntity.quickCheck.getRecipeFor(pBlockEntity, pLevel).orElse(null);
            } else {
                recipe = null;
            }

            int i = pBlockEntity.getMaxStackSize();
            if (!pBlockEntity.isLit() && pBlockEntity.canBurn(pLevel.registryAccess(), recipe, pBlockEntity.items, i)) {
                pBlockEntity.litTime = pBlockEntity.getBurnDuration(fuel);
                pBlockEntity.litDuration = pBlockEntity.litTime;
                if (pBlockEntity.isLit()) {
                    hasChanged = true;
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

            if (pBlockEntity.canBurn(pLevel.registryAccess(), recipe, pBlockEntity.items, i)) {
                if (pBlockEntity.isLit()) {
                    ++pBlockEntity.processingProgress;
                    --pBlockEntity.litTime;
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

        if (flag != (pBlockEntity.processingProgress > 0) || hasChanged) {
            hasChanged = true;
            pState = pState.setValue(BlockHuaJiBlender.LIT, pBlockEntity.processingProgress > 0);
            pLevel.setBlock(pPos, pState, 3);
        }

        if (hasChanged) {
            setChanged(pLevel, pPos, pState);
        }
    }

    private boolean canBurn(RegistryAccess pRegistryAccess, @Nullable HuaJiBlenderRecipe pRecipe, NonNullList<ItemStack> pInventory, int pMaxStackSize) {
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

    private boolean burn(RegistryAccess pRegistryAccess, @Nullable HuaJiBlenderRecipe pRecipe, NonNullList<ItemStack> pInventory, int pMaxStackSize) {
        if (pRecipe != null && this.canBurn(pRegistryAccess, pRecipe, pInventory, pMaxStackSize)) {
            ItemStack input = pInventory.get(SLOT_INPUT);
            ItemStack result = pRecipe.assemble(this, pRegistryAccess);
            ItemStack output = pInventory.get(SLOT_RESULT);
            if (output.isEmpty()) {
                pInventory.set(SLOT_RESULT, result.copy());
            } else if (output.is(result.getItem())) {
                output.grow(result.getCount());
            }
            input.shrink(1);
            return true;
        } else {
            return false;
        }
    }

    protected int getBurnDuration(ItemStack pFuel) {
        if (pFuel.isEmpty()) {
            return 0;
        } else {
            return HuaJiUtils.getTagFuel(pFuel, HUAJI_BLENDER_TIME_PREFIX, 19);
        }
    }

    private static int getTotalProcessTime(Level pLevel, HuaJiBlenderBlockEntity pBlockEntity) {
        return pBlockEntity.quickCheck.getRecipeFor(pBlockEntity, pLevel).map(HuaJiBlenderRecipe::getProcessTime).orElse(BURN_TIME_STANDARD);
    }

    public static boolean isFuel(ItemStack pStack) {
        return HuaJiUtils.isTagFuel(pStack, HUAJI_BLENDER_TIME_PREFIX, 19);
    }

    @Override
    public int @NotNull [] getSlotsForFace(@NotNull Direction pSide) {
        if (pSide == Direction.DOWN) {
            return SLOTS_FOR_DOWN;
        } else {
            return pSide == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int pIndex, @NotNull ItemStack pItemStack, @Nullable Direction pDirection) {
        return this.canPlaceItem(pIndex, pItemStack);
    }

    @Override
    public boolean canTakeItemThroughFace(int pIndex, @NotNull ItemStack pStack, @NotNull Direction pDirection) {
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
    public @NotNull ItemStack getItem(int pIndex) {
        return this.items.get(pIndex);
    }

    @Override
    public @NotNull ItemStack removeItem(int pIndex, int pCount) {
        return ContainerHelper.removeItem(this.items, pIndex, pCount);
    }

    @Override
    public @NotNull ItemStack removeItemNoUpdate(int pIndex) {
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
            if (this.level != null) {
                this.processingTotalTime = getTotalProcessTime(this.level, this);
            }
            this.processingProgress = 0;
            this.setChanged();
        }

    }

    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        return Container.stillValidBlockEntity(this, pPlayer);
    }

    @Override
    public boolean canPlaceItem(int pIndex, @NotNull ItemStack pStack) {
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
    public HuaJiBlenderRecipe getRecipeUsed() {
        return null;
    }

    @Override
    public void awardUsedRecipes(@NotNull Player pPlayer, @NotNull List<ItemStack> pItems) {
    }

    public void awardUsedRecipesAndPopExperience(ServerPlayer pPlayer) {
        List<HuaJiBlenderRecipe> list = this.getRecipesToAwardAndPopExperience(pPlayer.serverLevel(), pPlayer.position());
        pPlayer.awardRecipes(list.stream().map(huaJiBlenderRecipe -> (Recipe<?>) huaJiBlenderRecipe).collect(Collectors.toSet()));

        for (HuaJiBlenderRecipe recipe : list) {
            if (recipe != null) {
                pPlayer.triggerRecipeCrafted(recipe, this.items);
            }
        }

        this.recipesUsed.clear();
    }

    public List<HuaJiBlenderRecipe> getRecipesToAwardAndPopExperience(ServerLevel pLevel, Vec3 pPopVec) {
        List<HuaJiBlenderRecipe> list = Lists.newArrayList();

        for (Object2IntMap.Entry<ResourceLocation> entry : this.recipesUsed.object2IntEntrySet()) {
            pLevel.getRecipeManager().byKey(entry.getKey()).ifPresent((recipe) -> {
                if (recipe instanceof HuaJiBlenderRecipe huaJiBlenderRecipe) {
                    list.add(huaJiBlenderRecipe);
                    createExperience(pLevel, pPopVec, entry.getIntValue(), huaJiBlenderRecipe.getExperience());
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
    public void fillStackedContents(@NotNull StackedContents pHelper) {
        for (ItemStack itemstack : this.items) {
            pHelper.accountStack(itemstack);
        }

    }

    LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction facing) {
        if (capability == ForgeCapabilities.ITEM_HANDLER && facing != null && !this.remove) {
            return switch (facing) {
                case UP -> handlers[0].cast();
                case DOWN -> handlers[1].cast();
                default -> handlers[2].cast();
            };
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
