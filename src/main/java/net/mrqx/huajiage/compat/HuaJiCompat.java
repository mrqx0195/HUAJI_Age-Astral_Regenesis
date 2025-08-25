package net.mrqx.huajiage.compat;

import org.apache.logging.log4j.util.LoaderUtil;

public class HuaJiCompat {
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
