package net.mrqx.huajiage.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.mrqx.huajiage.item.equipment.armor.ItemFiftyFiftyHelmet;
import org.jetbrains.annotations.NotNull;

public class ItemLordKey extends BaseItem {
    public ItemLordKey(Properties properties) {
        super((properties));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        if (ItemFiftyFiftyHelmet.isFiftyFiftyActive(pPlayer)) {
            pPlayer.startUsingItem(pUsedHand);
            return InteractionResultHolder.consume(itemStack);
        } else {
            return InteractionResultHolder.pass(itemStack);
        }
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull LivingEntity pLivingEntity) {
        super.finishUsingItem(pStack, pLevel, pLivingEntity);
        if (pLivingEntity instanceof Player player && ItemFiftyFiftyHelmet.isFiftyFiftyActive(player)) {
            ItemFiftyFiftyHelmet.setFiftyFiftyLord(player, true);
            player.playSound(SoundEvents.ARMOR_EQUIP_NETHERITE, 1f, 1f);
            player.playSound(SoundEvents.GLASS_BREAK, 1f, 1f);
            if (pLevel.isClientSide) {
                Minecraft.getInstance().gameRenderer.displayItemActivation(pStack.copy());
            } else if (player.getServer() != null) {
                for (int i = 0; i < 3; i++) {
                    LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, pLevel);
                    lightningBolt.setPos(player.position());
                    pLevel.addFreshEntity(lightningBolt);
                }
                player.getServer().getPlayerList().broadcastSystemMessage(Component.translatable("message.huajiage.50_50_helmet.lord", player.getDisplayName())
                        .withStyle(ChatFormatting.GRAY, ChatFormatting.BOLD), false);
            }
            pStack.shrink(1);
        }
        return pStack;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack pStack) {
        return 29;
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack pStack) {
        return UseAnim.BOW;
    }
}
