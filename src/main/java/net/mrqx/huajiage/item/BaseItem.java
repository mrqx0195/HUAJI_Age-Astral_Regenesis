package net.mrqx.huajiage.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BaseItem extends Item {
    public BaseItem() {
        this(new Item.Properties().rarity(Rarity.COMMON));
    }

    public BaseItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        int index = 1;
        while (true) {
            String key = this.getDescriptionId() + ".tooltips." + index;
            String translated = Component.translatable(key).getString();
            if (!translated.toLowerCase(Locale.ENGLISH).equals(key)) {
                tooltip.add(Component.translatable(key).withStyle(ChatFormatting.GRAY));
                index++;
            } else {
                break;
            }
        }
        List<Component> shiftTooltips = new ArrayList<>();
        index = 1;
        while (true) {
            String key = this.getDescriptionId() + ".tooltips.shift." + index;
            String translated = Component.translatable(key).getString();
            if (!translated.toLowerCase(Locale.ENGLISH).equals(key)) {
                shiftTooltips.add(Component.translatable(key).withStyle(ChatFormatting.GRAY));
                index++;
            } else {
                break;
            }
        }
        if (!shiftTooltips.isEmpty()) {
            tooltip.add(Component.translatable("item.huajiage.tooltips.shift.1",
                            Minecraft.getInstance().options.keyShift.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.YELLOW, ChatFormatting.ITALIC))
                    .withStyle(ChatFormatting.AQUA));
            if (Screen.hasShiftDown()) {
                tooltip.addAll(shiftTooltips);
            }
        }
    }
}
