package net.mrqx.huajiage.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.mrqx.huajiage.block.blockentity.HuaJiBlenderBlockEntity;
import net.mrqx.huajiage.registy.HuaJiBlocks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class BlockHuaJiBlender extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public BlockHuaJiBlender(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, Boolean.FALSE));
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull InteractionResult use(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit) {
        if (pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            this.openContainer(pLevel, pPos, pPlayer);
            return InteractionResult.CONSUME;
        }
    }

    protected void openContainer(Level pLevel, BlockPos pPos, Player pPlayer) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity instanceof HuaJiBlenderBlockEntity huajiBlenderBlockEntity) {
            pPlayer.openMenu(huajiBlenderBlockEntity);
            pPlayer.awardStat(Stats.INTERACT_WITH_FURNACE);
        }

    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public void setPlacedBy(@NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        if (pStack.hasCustomHoverName()) {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            if (blockentity instanceof HuaJiBlenderBlockEntity huajiBlenderBlockEntity) {
                huajiBlenderBlockEntity.setCustomName(pStack.getHoverName());
            }
        }

    }

    @Override
    @SuppressWarnings("deprecation")
    public void onRemove(BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.is(pNewState.getBlock())) {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            if (blockentity instanceof HuaJiBlenderBlockEntity huajiBlenderBlockEntity) {
                if (pLevel instanceof ServerLevel) {
                    Containers.dropContents(pLevel, pPos, huajiBlenderBlockEntity);
                    huajiBlenderBlockEntity.getRecipesToAwardAndPopExperience((ServerLevel) pLevel, Vec3.atCenterOf(pPos));
                }

                pLevel.updateNeighbourForOutputSignal(pPos, this);
            }

            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean hasAnalogOutputSignal(@NotNull BlockState pState) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public int getAnalogOutputSignal(@NotNull BlockState pBlockState, Level pLevel, @NotNull BlockPos pPos) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(pLevel.getBlockEntity(pPos));
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull RenderShape getRenderShape(@NotNull BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, LIT);
    }

    @Override
    @Nullable
    @SuppressWarnings("unchecked")
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level pLevel, @NotNull BlockState pState, @NotNull BlockEntityType<T> pBlockEntityType) {
        return createFurnaceTicker(pLevel, pBlockEntityType, (BlockEntityType<? extends HuaJiBlenderBlockEntity>) HuaJiBlocks.HUAJI_BLENDER_BLOCK_ENTITY.get());
    }

    @Nullable
    protected static <T extends BlockEntity> BlockEntityTicker<T> createFurnaceTicker(Level pLevel, BlockEntityType<T> pServerType, BlockEntityType<? extends HuaJiBlenderBlockEntity> pClientType) {
        return pLevel.isClientSide ? null : createTickerHelper(pServerType, pClientType, HuaJiBlenderBlockEntity::serverTick);
    }

    @Override
    public @org.jetbrains.annotations.Nullable BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return new HuaJiBlenderBlockEntity(pPos, pState);
    }
}
