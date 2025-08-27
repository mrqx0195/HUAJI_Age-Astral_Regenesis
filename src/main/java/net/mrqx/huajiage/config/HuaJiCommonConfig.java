package net.mrqx.huajiage.config;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;
import com.google.common.util.concurrent.AtomicDouble;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber
public class HuaJiCommonConfig {
    public static final ForgeConfigSpec COMMON_CONFIG;

    public static final ForgeConfigSpec.IntValue POLYFURNACE_TOTAL_POINT;
    public static final ForgeConfigSpec.IntValue POLYFURNACE_MAX_ENERGY;
    public static final ForgeConfigSpec.IntValue POLYFURNACE_MAX_FE;
    public static final ForgeConfigSpec.IntValue POLYFURNACE_FE_CONVERT;

    public static final ForgeConfigSpec.DoubleValue ARROW_STAND_CHANCE;
    public static final ForgeConfigSpec.ConfigValue<List<? extends List<?>>> ARROW_STAND_LIST;

    static {
        ForgeConfigSpec.Builder commonBuilder = new ForgeConfigSpec.Builder();
        commonBuilder.comment("HUAJI Age: Astral Regenesis common settings");

        commonBuilder.push("Machine settings");
        POLYFURNACE_TOTAL_POINT = commonBuilder
                .comment("Set the total point requirement of HUAJI Ultimate Polyfurnace. (default: 2008)")
                .defineInRange("polyfurnace_total_point", 2008, Integer.MIN_VALUE, Integer.MAX_VALUE);
        POLYFURNACE_MAX_ENERGY = commonBuilder
                .comment("Set the max Golden Spirit of HUAJI Ultimate Polyfurnace. (default: 5000)")
                .defineInRange("polyfurnace_max_energy", 5000, Integer.MIN_VALUE, Integer.MAX_VALUE);
        POLYFURNACE_MAX_FE = commonBuilder
                .comment("Set the max Energy (RF) of HUAJI Ultimate Polyfurnace. (default: 37000000)")
                .defineInRange("polyfurnace_max_fe", 37000000, Integer.MIN_VALUE, Integer.MAX_VALUE);
        POLYFURNACE_FE_CONVERT = commonBuilder
                .comment("How many FE to convert to 1 Golden Spirit in HUAJI Ultimate Polyfurnace. (default: 100)")
                .defineInRange("polyfurnace_fe_convert", 100, Integer.MIN_VALUE, Integer.MAX_VALUE);
        commonBuilder.pop();

        commonBuilder.push("Stand settings");
        ARROW_STAND_CHANCE = commonBuilder
                .comment("Set the chance of gaining stand from (default: 0.7)")
                .defineInRange("arrow_stand_chance", 0.7, 0, 1);
        ARROW_STAND_LIST = commonBuilder
                .comment("Set the weight for Stands from.")
                .defineListAllowEmpty(List.of("arrow_stand_weight_list"),
                        List.of(
                                List.of("huajiage:hierophant_green", 1.0),
                                List.of("huajiage:the_world", 0.8)
                        ),
                        it -> it instanceof List<?> list && list.size() == 2
                                && list.get(0) instanceof String
                                && list.get(1) instanceof Double
                                && (Double) (list.get(1)) >= 0.0);
        commonBuilder.pop();

        COMMON_CONFIG = commonBuilder.build();
    }


    public static final RangeMap<Double, String> ARROW_STAND_RANGE_MAP = TreeRangeMap.create();
    public static double arrowStandTotalRange;

    @SubscribeEvent
    public static void onServerStartingEvent(ServerStartingEvent event) {
        ARROW_STAND_RANGE_MAP.clear();
        Map<String, Double> unawakenedSoulMap = new HashMap<>(16);
        ARROW_STAND_LIST.get().forEach(chance -> unawakenedSoulMap.put((String) (chance.get(0)), (Double) (chance.get(1))));
        List<Map.Entry<String, Double>> sortedList = new ArrayList<>(unawakenedSoulMap.entrySet());
        sortedList.sort(Map.Entry.comparingByValue());
        AtomicDouble total = new AtomicDouble(0);
        sortedList.forEach(entry -> ARROW_STAND_RANGE_MAP.put(Range.closedOpen(total.getAndAdd(entry.getValue()), total.get() + entry.getValue()), entry.getKey()));
        arrowStandTotalRange = total.get();
    }
}
