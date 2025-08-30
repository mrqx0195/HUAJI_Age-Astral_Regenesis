package net.mrqx.huajiage.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;

public class BaseBlockItem extends BlockItem {
    public BaseBlockItem(Block pBlock) {
        this(pBlock, new Item.Properties().rarity(Rarity.COMMON));
    }

    public BaseBlockItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        int index = 1;
        while (true) {
            String key = this.getDescriptionId() + ".tooltips." + index;
            String translated = Component.translatable(key).getString();
            if (!translated.toLowerCase(Locale.ENGLISH).equals(key)) {
                tooltip.add(Component.translatable(key));
                index++;
            } else {
                return;
            }
        }
    }
}
