package net.mrqx.huajiage.utils;

import net.minecraft.world.item.ItemStack;
import net.mrqx.huajiage.HuaJiAgeMod;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class HuaJiUtils {
    public static int getTagFuel(ItemStack itemStack, String tagPrefix, int index) {
        AtomicInteger burnDuration = new AtomicInteger(0);
        itemStack.getTags().forEach(itemTagKey -> {
            if (itemTagKey.location().getNamespace().equals(HuaJiAgeMod.MODID)) {
                String path = itemTagKey.location().getPath();
                if (path.startsWith(tagPrefix)) {
                    try {
                        int time = Integer.parseInt(path.substring(index));
                        burnDuration.set(time);
                    } catch (NumberFormatException e) {
                        HuaJiAgeMod.LOGGER.error("BAD TAG {} on item {}", itemTagKey, itemStack.getDisplayName().getString(), e);
                    }
                }
            }
        });
        return burnDuration.get();
    }

    public static boolean isTagFuel(ItemStack itemStack, String tagPrefix, int index) {
        AtomicBoolean flag = new AtomicBoolean(false);
        itemStack.getTags().forEach(itemTagKey -> {
            if (itemTagKey.location().getNamespace().equals(HuaJiAgeMod.MODID)) {
                String path = itemTagKey.location().getPath();
                if (path.startsWith(tagPrefix)) {
                    try {
                        Integer.parseInt(path.substring(index));
                        flag.set(true);
                    } catch (NumberFormatException e) {
                        HuaJiAgeMod.LOGGER.error("BAD TAG {} on item {}", itemTagKey, itemStack.getDisplayName().getString(), e);
                    }
                }
            }
        });
        return flag.get();
    }

    public static boolean isInRectangularArea(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
        return (mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize);
    }
}
