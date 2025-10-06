package net.mrqx.huajiage.compat;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.mrqx.huajiage.compat.curios.CuriosItemFactory;
import net.mrqx.huajiage.compat.ltc2.LaTiaoCraftFactory;
import net.mrqx.huajiage.compat.slashblade.SlashBladeFactory;
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
    public final boolean hasSlashBlade;
    public final boolean hasTetra;
    public final boolean hasLaTiaoCraft;

    private HuaJiCompat() {
        this.hasJEI = LoaderUtil.isClassAvailable("mezz.jei.api.JeiPlugin");
        this.hasPatchouli = LoaderUtil.isClassAvailable("vazkii.patchouli.api.IComponentProcessor");
        this.hasCurios = LoaderUtil.isClassAvailable("top.theillusivec4.curios.api.type.capability.ICurioItem");
        this.hasFantasyEnding = LoaderUtil.isClassAvailable("com.mega.uom.ModSource");
        this.hasSlashBlade = LoaderUtil.isClassAvailable("mods.flammpfeil.slashblade.SlashBlade");
        this.hasTetra = LoaderUtil.isClassAvailable("se.mickelus.tetra.TetraMod");
        this.hasLaTiaoCraft = LoaderUtil.isClassAvailable("com.doggystudio.chirencqr.ltc.server.LatiaoCraft");
    }

    public static Item makeInfiniteCharmCurios() {
        if (HuaJiCompat.getInstance().hasCurios) {
            return CuriosItemFactory.makeInfiniteCharm();
        }
        return new ItemInfiniteCharm();
    }

    public static void register(IEventBus modEventBus) {
        if (HuaJiCompat.getInstance().hasSlashBlade) {
            SlashBladeFactory.register(modEventBus);
        }
    }

    public static void commonSetup() {
        if (HuaJiCompat.getInstance().hasLaTiaoCraft) {
            LaTiaoCraftFactory.init();
        }
    }
}
