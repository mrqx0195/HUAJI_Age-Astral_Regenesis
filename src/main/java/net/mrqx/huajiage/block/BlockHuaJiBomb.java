package net.mrqx.huajiage.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.utils.AdvancementHelper;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BlockHuaJiBomb extends TntBlock {
    protected boolean isBig = false;

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

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!itemstack.is(HuaJiItems.JIN_KE_LA.get())) {
            isBig = false;
            return super.use(state, level, pos, player, hand, hit);
        } else {
            isBig = true;
            AdvancementHelper.grantCriterion(player, HuaJiAgeMod.prefix("jin_ke_la_bomb"));
            onCaughtFire(state, level, pos, hit.getDirection(), player);
            isBig = false;
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
            Item item = itemstack.getItem();
            if (!player.isCreative()) {
                itemstack.hurtAndBreak(1, player, entity -> entity.broadcastBreakEvent(hand));
            }

            player.awardStat(Stats.ITEM_USED.get(item));
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
    }

    public void explode(Level pLevel, BlockPos pPos, @Nullable LivingEntity pEntity, List<BlockPos> posList) {
        if (!pLevel.isClientSide) {
            posList.add(pPos);
            pLevel.explode(pEntity, pPos.getX(), pPos.getY(), pPos.getZ(), isBig ? 12 : 6, Level.ExplosionInteraction.TNT);
        }
    }
}
