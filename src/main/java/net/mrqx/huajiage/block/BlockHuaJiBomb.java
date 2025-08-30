package net.mrqx.huajiage.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BlockHuaJiBomb extends TntBlock {
    public BlockHuaJiBomb(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void wasExploded(Level pLevel, BlockPos pPos, Explosion pExplosion) {
        explode(pLevel, pPos, null, new ArrayList<>());
    }

    @Override
    public void onCaughtFire(BlockState state, Level world, BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {
        explode(world, pos, igniter, new ArrayList<>());
    }

    public void explode(Level pLevel, BlockPos pPos, @Nullable LivingEntity pEntity, List<BlockPos> posList) {
        if (!pLevel.isClientSide) {
            posList.add(pPos);
            pLevel.explode(pEntity, pPos.getX(), pPos.getY(), pPos.getZ(), 6.0F, Level.ExplosionInteraction.TNT);
        }
    }
}
