package net.mrqx.huajiage.compat;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.mrqx.huajiage.compat.curios.CuriosInfiniteCharm;
import net.mrqx.huajiage.item.ItemInfiniteCharm;
import org.apache.logging.log4j.util.LoaderUtil;

public class HuaJiCompat {
    public static final TagKey<Item> LATIAO_TAG = ItemTags.create(new ResourceLocation("ltc2", "latiao"));
    public static final TagKey<Item> CHARM_ITEM_TAG = ItemTags.create(new ResourceLocation("curios", "charm"));
    private static final HuaJiCompat INSTANCE = new HuaJiCompat();

    public static HuaJiCompat getInstance() {
        return INSTANCE;
    }

    public final boolean hasJEI;
    public final boolean hasPatchouli;
    public final boolean hasCurios;
    public final boolean hasFantasyEnding;

    private HuaJiCompat() {
        this.hasJEI = LoaderUtil.isClassAvailable("mezz.jei.api.JeiPlugin");
        this.hasPatchouli = LoaderUtil.isClassAvailable("vazkii.patchouli.api.IComponentProcessor");
        this.hasCurios = LoaderUtil.isClassAvailable("top.theillusivec4.curios.api.type.capability.ICurioItem");
        this.hasFantasyEnding = LoaderUtil.isClassAvailable("com.mega.uom.ModSource");
    }

    public static Item makeInfiniteCharmCurios() {
        if (HuaJiCompat.getInstance().hasCurios) {
            return new CuriosInfiniteCharm();
        }
        return new ItemInfiniteCharm();
    }
}
