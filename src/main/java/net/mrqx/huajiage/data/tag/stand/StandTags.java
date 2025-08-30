package net.mrqx.huajiage.data.tag.stand;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.registy.HuaJiStands;
import net.mrqx.huajiage.stand.Stand;

public final class StandTags {
    public static final TagKey<Stand> TIME_STOP = bind("time_stop");
    public static final TagKey<Stand> CLOSE_RANGE_POWER = bind("close_range_power");
    public static final TagKey<Stand> LONG_DISTANCE_OPERATION = bind("long_distance_operation");
    public static final TagKey<Stand> AUTOMATIC = bind("automatic");
    public static final TagKey<Stand> RANGE_IRRELEVANT = bind("range_irrelevant");

    private static TagKey<Stand> bind(String name) {
        return create(HuaJiAgeMod.prefix(name));
    }

    public static TagKey<Stand> create(final ResourceLocation name) {
        return HuaJiStands.STANDS.createTagKey(name);
    }
}
