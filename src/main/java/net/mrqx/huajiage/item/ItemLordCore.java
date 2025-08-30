package net.mrqx.huajiage.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.mrqx.huajiage.item.equipment.armor.ItemFiftyFiftyHelmet;
import net.mrqx.huajiage.utils.HuaJiSoundPlayer;

public class ItemLordCore extends BaseItem {
    public ItemLordCore() {
        super(new Item.Properties().rarity(Rarity.RARE).fireResistant());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        if (ItemFiftyFiftyHelmet.hasFiftyFiftyHelmet(pPlayer)) {
            pPlayer.startUsingItem(pUsedHand);
            return InteractionResultHolder.consume(itemStack);
        } else {
            return InteractionResultHolder.pass(itemStack);
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        super.finishUsingItem(pStack, pLevel, pLivingEntity);
        if (pLivingEntity instanceof Player player && ItemFiftyFiftyHelmet.hasFiftyFiftyHelmet(player)) {
            ItemFiftyFiftyHelmet.setFiftyFiftyActive(player, true);
            HuaJiSoundPlayer.playMovingSoundToClient(player, SoundEvents.ANVIL_LAND, player.getSoundSource());
            HuaJiSoundPlayer.playMovingSoundToClient(player, SoundEvents.UI_TOAST_CHALLENGE_COMPLETE, player.getSoundSource());
            if (pLevel.isClientSide) {
                Minecraft.getInstance().gameRenderer.displayItemActivation(pStack.copy());
            } else if (player.getServer() != null) {
                player.getServer().getPlayerList().broadcastSystemMessage(Component.translatable("message.huajiage.50_50_helmet.active", player.getDisplayName())
                        .withStyle(ChatFormatting.YELLOW, ChatFormatting.BOLD), false);
                if (!player.isCreative()) {
                    pStack.shrink(1);
                }
            }
        }
        return pStack;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 29;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }
}
