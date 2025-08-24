package net.mrqx.huajiage.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class HuaJiCommonConfig {
    public static final ForgeConfigSpec COMMON_CONFIG;
    public static final ForgeConfigSpec.IntValue POLYFURNACE_TOTAL_POINT;
    public static final ForgeConfigSpec.IntValue POLYFURNACE_MAX_ENERGY;
    public static final ForgeConfigSpec.IntValue POLYFURNACE_MAX_FE;
    public static final ForgeConfigSpec.IntValue POLYFURNACE_FE_CONVERT;

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

        COMMON_CONFIG = commonBuilder.build();
    }
}
