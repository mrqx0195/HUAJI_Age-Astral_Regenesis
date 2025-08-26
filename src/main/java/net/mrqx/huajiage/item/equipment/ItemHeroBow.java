package net.mrqx.huajiage.item.equipment;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.entity.EntityHeroArrow;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.registy.HuaJiSoundEvents;
import net.mrqx.huajiage.utils.HuaJiDamageSources;
import net.mrqx.huajiage.utils.HuajiSoundPlayer;
import net.mrqx.huajiage.utils.ItemTagHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Mod.EventBusSubscriber
public class ItemHeroBow extends BowItem {
    public ItemHeroBow(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull String getDescriptionId(@NotNull ItemStack pStack) {
        if (Mode.getMode(pStack).equals(Mode.ON)) {
            return super.getDescriptionId(pStack) + ".on";
        }
        return super.getDescriptionId(pStack);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        int index = 1;
        while (true) {
            String key = this.getDescriptionId() + ".tooltips." + index;
            String translated = Component.translatable(key).getString();
            if (!translated.toLowerCase(Locale.ENGLISH).equals(key)) {
                tooltip.add(Component.translatable(key).withStyle(index == 1 ? ChatFormatting.DARK_RED : ChatFormatting.GOLD));
                index++;
            } else {
                break;
            }
        }
        tooltip.add(Component.translatable("item.huajiage.tooltips.shift.1",
                        Minecraft.getInstance().options.keyShift.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.YELLOW, ChatFormatting.ITALIC))
                .withStyle(ChatFormatting.DARK_RED));
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("message.huajiage.prefix", Component.translatable(this.getDescriptionId() + ".tooltips.shift.1")
                    .withStyle(ChatFormatting.YELLOW)));
            tooltip.add(Component.translatable("message.huajiage.prefix", Mode.getMode(stack).equals(Mode.ON)
                    ? Component.translatable(this.getDescriptionId() + ".tooltips.shift.on.1").withStyle(ChatFormatting.YELLOW, ChatFormatting.BOLD)
                    : Component.translatable(this.getDescriptionId() + ".tooltips.shift.2",
                            Minecraft.getInstance().options.keyShift.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.YELLOW, ChatFormatting.ITALIC),
                            Minecraft.getInstance().options.keyAttack.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.YELLOW, ChatFormatting.ITALIC))
                    .withStyle(ChatFormatting.AQUA)));
        }
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, Player pPlayer, @NotNull InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        pPlayer.startUsingItem(pHand);
        return InteractionResultHolder.consume(itemstack);
    }

    @Override
    public void releaseUsing(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof Player player) {
            ItemStack itemstack = player.getProjectile(pStack);

            int i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(pStack, pLevel, player, this.getUseDuration(pStack) - pTimeLeft, true);
            if (i < 0) {
                return;
            }

            if (itemstack.isEmpty()) {
                itemstack = Items.ARROW.getDefaultInstance();
            }

            float f = getPowerForTime(i);
            if (f >= 0.1D) {
                if (!pLevel.isClientSide) {
                    ArrowItem arrowitem = (ArrowItem) (itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                    AbstractArrow abstractarrow = arrowitem.createArrow(pLevel, itemstack, player);
                    boolean isOn = Mode.getMode(pStack).equals(Mode.ON) && f >= 1.0F;

                    if (isOn) {
                        abstractarrow = new EntityHeroArrow(HuaJiAgeMod.RegistryEvents.heroArrowEntityType, pEntityLiving, pLevel);
                        abstractarrow.setPierceLevel((byte) 127);
                    }

                    abstractarrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, f * (isOn ? 10 : 6), 1);
                    if (f >= 1.0F) {
                        abstractarrow.setCritArrow(true);
                    }

                    int j = pStack.getEnchantmentLevel(Enchantments.POWER_ARROWS);
                    if (j > 0) {
                        abstractarrow.setBaseDamage(abstractarrow.getBaseDamage() + j * 0.5D + 0.5D);
                    }

                    int k = pStack.getEnchantmentLevel(Enchantments.PUNCH_ARROWS);
                    if (k > 0) {
                        abstractarrow.setKnockback(k);
                    }

                    if (pStack.getEnchantmentLevel(Enchantments.FLAMING_ARROWS) > 0) {
                        abstractarrow.setSecondsOnFire(100);
                    }

                    pStack.hurtAndBreak(1, player, (player1) -> player1.broadcastBreakEvent(player.getUsedItemHand()));

                    abstractarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;

                    if (player.hasEffect(MobEffects.DAMAGE_BOOST)) {
                        int l = Objects.requireNonNull(player.getEffect(MobEffects.DAMAGE_BOOST)).getAmplifier();
                        abstractarrow.setBaseDamage(abstractarrow.getBaseDamage() + l * 2D);
                    }

                    pLevel.addFreshEntity(abstractarrow);

                    if (isOn) {
                        player.hurt(HuaJiDamageSources.stella(pLevel, null, player, player.position()), player.getMaxHealth() * 5);
                        HuajiSoundPlayer.playMovingSoundToClient(player, HuaJiSoundEvents.STELLA.get(), player.getSoundSource());
                    }
                }

                HuajiSoundPlayer.playMovingSoundToClient(player, SoundEvents.ARROW_SHOOT, player.getSoundSource(), 1, 1.0F / (pLevel.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                player.awardStat(Stats.ITEM_USED.get(this));
            }
        }
    }

    @SubscribeEvent
    public static void onLeftClick(PlayerInteractEvent event) {
        if (!event.getEntity().getMainHandItem().is(HuaJiItems.HERO_BOW.get())
                || !event.getEntity().isShiftKeyDown()
                || event.getEntity().getCooldowns().isOnCooldown(HuaJiItems.HERO_BOW.get())) {
            return;
        }
        Player player = event.getEntity();
        if (event instanceof PlayerInteractEvent.LeftClickBlock
                || event instanceof PlayerInteractEvent.LeftClickEmpty) {
            Mode.changeMode(player.getMainHandItem());
            player.getCooldowns().addCooldown(HuaJiItems.HERO_BOW.get(), 10);
            if (Mode.getMode(player.getMainHandItem()).equals(Mode.ON)) {
                player.sendSystemMessage(Component.translatable("message.huajiage.stella_warning").withStyle(ChatFormatting.YELLOW));
                HuajiSoundPlayer.playMovingSoundToClient(player, SoundEvents.TOTEM_USE, player.getSoundSource());
            }
        }
    }

    public enum Mode {
        ON, OFF;

        public static Mode getMode(ItemStack itemStack) {
            if (ItemTagHelper.getBoolean(itemStack, "hero_bow_mode", false)) {
                return ON;
            }
            return OFF;
        }

        public static Mode changeMode(ItemStack itemStack) {
            if (getMode(itemStack) == Mode.OFF) {
                ItemTagHelper.setBoolean(itemStack, "hero_bow_mode", true);
                return ON;
            }
            ItemTagHelper.setBoolean(itemStack, "hero_bow_mode", false);
            return OFF;
        }
    }
}
