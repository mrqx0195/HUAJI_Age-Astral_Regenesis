package net.mrqx.huajiage.item.stand;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.capability.stand.StandDataCapabilityProvider;
import net.mrqx.huajiage.item.BaseItem;
import net.mrqx.huajiage.registy.HuaJiStands;
import net.mrqx.huajiage.stand.Stand;
import net.mrqx.huajiage.utils.ItemTagHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemDisc extends BaseItem {
    public static final String DISC_STAND_KEY = HuaJiAgeMod.MODID + "." + "discStand";
    public static final String DISC_STAND_LEVEL_KEY = HuaJiAgeMod.MODID + "." + "discStandLevel";

    public ItemDisc() {
        super(new Item.Properties());
    }

    @Override
    public Component getName(ItemStack pStack) {
        ResourceLocation resourceLocation = getStandResourceLocation(pStack);
        if (resourceLocation != null) {
            return Component.translatable("item.huajiage.disc", Component.translatable("stand." + resourceLocation.getNamespace() + "." + resourceLocation.getPath()));
        }
        return Component.translatable("item.huajiage.disc", Component.translatable("stand.huajiage.null"));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        ResourceLocation resourceLocation = getStandResourceLocation(stack);
        Stand stand = getStand(stack);
        if (resourceLocation != null && stand != null) {
            tooltip.add(Component.translatable("item.huajiage.tooltips.stand",
                            Component.translatable("stand." + resourceLocation.getNamespace() + "." + resourceLocation.getPath()))
                    .withStyle(ChatFormatting.GRAY));
            int standLevel = ItemTagHelper.getInt(stack, DISC_STAND_LEVEL_KEY, 0);
            tooltip.add(Component.translatable("stand.huajiage.level",
                            standLevel >= stand.getMaxLevel() ? Component.translatable("stand.huajiage.level.max") : standLevel)
                    .withStyle(ChatFormatting.GRAY));
        } else {
            tooltip.add(Component.translatable("item.huajiage.disc.tooltips.craft").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        pPlayer.startUsingItem(pUsedHand);
        Stand stand = getStand(itemStack);
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
    public static Stand getStand(ItemStack stack) {
        return HuaJiStands.REGISTRY.get().getValue(ResourceLocation.tryParse(ItemTagHelper.getString(stack, DISC_STAND_KEY, "huajiage:null")));
    }

    @Nullable
    public static ResourceLocation getStandResourceLocation(ItemStack stack) {
        return ResourceLocation.tryParse(ItemTagHelper.getString(stack, DISC_STAND_KEY, "huajiage:null"));
    }
}
