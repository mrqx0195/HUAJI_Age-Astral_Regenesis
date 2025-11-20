package net.mrqx.huajiage.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ItemJinKeLa extends BoneMealItem {
    public ItemJinKeLa() {
        super(new Properties().durability(200).rarity(Rarity.UNCOMMON));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        BlockPos blockPos1 = blockPos.relative(context.getClickedFace());
        Player player = context.getPlayer();
        ItemStack itemInHand = context.getItemInHand();
        if (player != null) {
            if (applyBonemeal(itemInHand.copy(), level, blockPos, player)) {
                applyBonemeal(itemInHand.copy(), level, blockPos, player);
                if (!level.isClientSide) {
                    level.levelEvent(1505, blockPos, 0);
                }
                itemInHand.hurtAndBreak(1, player, entity -> entity.broadcastBreakEvent(player.getUsedItemHand()));
                return InteractionResult.sidedSuccess(level.isClientSide);
            } else {
                BlockState blockstate = level.getBlockState(blockPos);
                boolean flag = blockstate.isFaceSturdy(level, blockPos, context.getClickedFace());
                if (flag && growWaterPlant(itemInHand.copy(), level, blockPos1, context.getClickedFace())) {
                    growWaterPlant(itemInHand.copy(), level, blockPos1, context.getClickedFace());
                    if (!level.isClientSide) {
                        level.levelEvent(1505, blockPos1, 0);
                    }
                    itemInHand.hurtAndBreak(1, player, entity -> entity.broadcastBreakEvent(player.getUsedItemHand()));
                    return InteractionResult.sidedSuccess(level.isClientSide);
                }
            }
        }
        return InteractionResult.PASS;
    }
}
