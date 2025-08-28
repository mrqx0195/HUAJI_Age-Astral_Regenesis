package net.mrqx.huajiage.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.mrqx.huajiage.capability.stand.StandDataCapabilityProvider;
import net.mrqx.huajiage.registy.HuaJiStands;
import net.mrqx.huajiage.stand.AbstractStand;
import net.mrqx.huajiage.utils.ItemTagHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemDisc extends BaseItem {
    public static final String DISC_STAND_KEY = "huajiage.discStand";
    public static final String DISC_STAND_LEVEL_KEY = "huajiage.discStandLevel";

    public ItemDisc(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack pStack) {
        ResourceLocation resourceLocation = getStandResourceLocation(pStack);
        if (resourceLocation != null) {
            return Component.translatable("item.huajiage.disc", Component.translatable("stand." + resourceLocation.getNamespace() + "." + resourceLocation.getPath()));
        }
        return Component.translatable("item.huajiage.disc", Component.translatable("stand.huajiage.null"));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        ResourceLocation resourceLocation = getStandResourceLocation(stack);
        if (resourceLocation != null) {
            tooltip.add(Component.translatable("item.huajiage.tooltips.stand",
                            Component.translatable("stand." + resourceLocation.getNamespace() + "." + resourceLocation.getPath()))
                    .withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.translatable("stand.huajiage.level", ItemTagHelper.getInt(stack, DISC_STAND_LEVEL_KEY, 0))
                    .withStyle(ChatFormatting.GRAY));
        } else {
            tooltip.add(Component.translatable("item.huajiage.disc.tooltips.craft").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        }
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        pPlayer.startUsingItem(pUsedHand);
        AbstractStand stand = getStand(itemStack);
        if (stand != null) {
            pPlayer.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
                data.setStand(stand);
                data.setMaxEnergy(stand.getMaxEnergy(pPlayer, data));
                data.setLevel(ItemTagHelper.getInt(itemStack, DISC_STAND_LEVEL_KEY, 0));
            });
            if (!pPlayer.isCreative()) {
                itemStack.shrink(1);
            }
            pPlayer.swing(pUsedHand, true);
            return InteractionResultHolder.success(itemStack);
        }
        return InteractionResultHolder.consume(itemStack);
    }

    @Nullable
    public static AbstractStand getStand(ItemStack stack) {
        return HuaJiStands.REGISTRY.get().getValue(ResourceLocation.tryParse(ItemTagHelper.getString(stack, DISC_STAND_KEY, "huajiage:null")));
    }

    @Nullable
    public static ResourceLocation getStandResourceLocation(ItemStack stack) {
        return ResourceLocation.tryParse(ItemTagHelper.getString(stack, DISC_STAND_KEY, "huajiage:null"));
    }
}
