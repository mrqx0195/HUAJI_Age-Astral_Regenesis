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

public class ItemTarot extends BaseItem {
    public static final String TAROT_STAND_KEY = HuaJiAgeMod.MODID + "." + "tarotStand";
    public static final String TAROT_STAND_LEVEL_KEY = HuaJiAgeMod.MODID + "." + "tarotStandLevel";

    public ItemTarot() {
        super(new Item.Properties());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        ResourceLocation resourceLocation = getStandResourceLocation(stack);
        if (resourceLocation != null && !"huajiage:null".equals(resourceLocation.toString())) {
            tooltip.add(Component.translatable("item.huajiage.tooltips.stand",
                            Component.translatable("stand." + resourceLocation.getNamespace() + "." + resourceLocation.getPath()))
                    .withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.translatable("stand.huajiage.level", ItemTagHelper.getInt(stack, TAROT_STAND_LEVEL_KEY, 0))
                    .withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        pPlayer.startUsingItem(pUsedHand);
        if (!pPlayer.level().isClientSide) {
            Stand stand = getStand(itemStack);
            if (stand != null) {
                pPlayer.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
                    Stand stand1 = Stand.getStand(data.getStand());
                    if (stand1 == null) {
                        data.setStand(stand);
                        data.setLevel(ItemTagHelper.getInt(itemStack, TAROT_STAND_LEVEL_KEY, 0));
                        data.setMaxEnergy(stand.getMaxEnergy(pPlayer, data));
                        ItemTagHelper.removeEntry(itemStack, TAROT_STAND_KEY);
                        ItemTagHelper.removeEntry(itemStack, TAROT_STAND_LEVEL_KEY);
                        pPlayer.swing(pUsedHand, true);
                    } else {
                        pPlayer.sendSystemMessage(Component.translatable("message.huajiage.tarot.stand.failed").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
                    }
                });
            } else {
                pPlayer.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
                    Stand stand1 = Stand.getStand(data.getStand());
                    if (stand1 != null) {
                        ItemTagHelper.setString(itemStack, TAROT_STAND_KEY, data.getStand().toString());
                        ItemTagHelper.setInt(itemStack, TAROT_STAND_LEVEL_KEY, data.getLevel());
                        data.setStand((ResourceLocation) null);
                        data.setLevel(0);
                        pPlayer.swing(pUsedHand, true);
                        pPlayer.sendSystemMessage(Component.translatable("message.huajiage.tarot.stand.store").withStyle(ChatFormatting.GOLD, ChatFormatting.ITALIC));
                    }
                });
            }
        }
        return InteractionResultHolder.success(itemStack);
    }

    @Nullable
    public static Stand getStand(ItemStack stack) {
        return HuaJiStands.REGISTRY.get().getValue(ResourceLocation.tryParse(ItemTagHelper.getString(stack, TAROT_STAND_KEY, "huajiage:null")));
    }

    @Nullable
    public static ResourceLocation getStandResourceLocation(ItemStack stack) {
        return ResourceLocation.tryParse(ItemTagHelper.getString(stack, TAROT_STAND_KEY, "huajiage:null"));
    }
}
