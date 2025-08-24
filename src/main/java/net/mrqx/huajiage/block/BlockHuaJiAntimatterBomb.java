package net.mrqx.huajiage.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.mrqx.huajiage.utils.HuaJiDamageSources;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockHuaJiAntimatterBomb extends BlockHuaJiBomb {
    protected final int radius = 10;

    public BlockHuaJiAntimatterBomb(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(UNSTABLE, Boolean.TRUE));
    }

    @Override
    public void explode(Level pLevel, BlockPos pPos, @Nullable LivingEntity pEntity, List<BlockPos> posList) {
        if (!pLevel.isClientSide) {
            posList.add(pPos);
            pLevel.removeBlock(pPos, true);
            for (int i = -radius; i <= radius; i++) {
                for (int j = -radius; j <= radius; j++) {
                    for (int k = -radius; k <= radius; k++) {
                        BlockPos blockPos = new BlockPos(pPos.getX() + i, pPos.getY() + j, pPos.getZ() + k);
                        if (!posList.contains(blockPos)) {
                            posList.add(blockPos);
                            if (pLevel.getBlockState(blockPos).getBlock() instanceof BlockHuaJiBomb huajiBombBlock) {
                                huajiBombBlock.explode(pLevel, pPos, pEntity, posList);
                            }
                            if (pLevel.getBlockState(blockPos).getBlock().defaultDestroyTime() >= 0) {
                                pLevel.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 3 | 64);
                            }
                        }
                    }
                }
            }
            pLevel.getEntitiesOfClass(Entity.class, new AABB(pPos.getX() - radius, pPos.getY() - radius, pPos.getZ() - radius, pPos.getX() + radius, pPos.getY() + radius, pPos.getZ() + radius)).forEach(entity -> {
                if (entity instanceof LivingEntity living) {
                    living.hurt(HuaJiDamageSources.antimatter(pLevel, null, null, pPos.getCenter()), 20);
                } else {
                    entity.remove(Entity.RemovalReason.KILLED);
                }
            });
        }
    }
}
