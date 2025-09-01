package net.mrqx.huajiage.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.block.blockentity.HuaJiPolyfurnaceBlockEntity;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.utils.ItemTagHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemPolyStar extends BaseItem {
    public static final String POLY_POINT_KEY = HuaJiAgeMod.MODID + "." + "polyStarPoint";

    public ItemPolyStar() {
        super(new Item.Properties().rarity(Rarity.EPIC).fireResistant());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable(this.getDescriptionId() + ".tooltips.1", HuaJiItems.HUAJI_POLYFURNACE.get().getDescription(),
                Minecraft.getInstance().options.keyShift.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.YELLOW, ChatFormatting.ITALIC),
                Minecraft.getInstance().options.keyUse.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.YELLOW, ChatFormatting.ITALIC)).withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("item.huajiage.huaji_star_poly.tooltips.point", ItemTagHelper.getInt(stack, POLY_POINT_KEY, 0)));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        InteractionResult interactionresult = super.useOn(context);
        BlockEntity blockEntity = context.getLevel().getBlockEntity(context.getClickedPos());
        ItemStack itemStack = context.getItemInHand();
        if (blockEntity instanceof HuaJiPolyfurnaceBlockEntity polyfurnaceBlockEntity && itemStack.is(HuaJiItems.HUAJI_STAR_POLY.get())) {
            polyfurnaceBlockEntity.polyfurnacePool += ItemTagHelper.getInt(itemStack, POLY_POINT_KEY, 0);
            polyfurnaceBlockEntity.setChanged();
            itemStack.shrink(1);
            if (context.getPlayer() != null) {
                context.getPlayer().swing(context.getHand());
            }
        }
        return interactionresult;
    }
}
