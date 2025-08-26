package net.mrqx.huajiage.compat;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.apache.logging.log4j.util.LoaderUtil;

public class HuaJiCompat {
    public static final TagKey<Item> LATIAO_TAG = ItemTags.create(new ResourceLocation("ltc2" + "latiao"));
    private static final HuaJiCompat INSTANCE = new HuaJiCompat();

    public static HuaJiCompat getInstance() {
        return INSTANCE;
    }

    public final boolean hasJEI;
    public final boolean hasPatchouli;
    public final boolean hasFantasyEnding;

    private HuaJiCompat() {
        this.hasJEI = LoaderUtil.isClassAvailable("mezz.jei.api.JeiPlugin");
        this.hasPatchouli = LoaderUtil.isClassAvailable("vazkii.patchouli.api.IComponentProcessor");
        this.hasFantasyEnding = LoaderUtil.isClassAvailable("com.mega.uom.ModSource");
    }
}
