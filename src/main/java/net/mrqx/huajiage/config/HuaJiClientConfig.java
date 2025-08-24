package net.mrqx.huajiage.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class HuaJiClientConfig {
    public static final ForgeConfigSpec CLIENT_CONFIG;
    public static final ForgeConfigSpec.DoubleValue STAND_HUD_X;
    public static final ForgeConfigSpec.DoubleValue STAND_HUD_Y;

    static {
        ForgeConfigSpec.Builder clientBuilder = new ForgeConfigSpec.Builder();
        clientBuilder.comment("HUAJI Age: Astral Regenesis client settings");

        clientBuilder.push("Render settings");
        STAND_HUD_X = clientBuilder
                .comment("Set the x pos of stand`s HUD display. (default: 0.0)")
                .defineInRange("stand_gui_x", 0.0, 0.0, 1.0);
        STAND_HUD_Y = clientBuilder
                .comment("Set the y pos of stand`s HUD display. (default: 0.64)")
                .defineInRange("stand_gui_y", 0.64, 0.0, 1.0);
        clientBuilder.pop();

        CLIENT_CONFIG = clientBuilder.build();
    }
}
