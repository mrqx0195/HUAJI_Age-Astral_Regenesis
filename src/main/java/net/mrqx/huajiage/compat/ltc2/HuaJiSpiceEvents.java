package net.mrqx.huajiage.compat.ltc2;

import com.doggystudio.chirencqr.ltc.server.spiceevent.SpiceEventRegistry;
import net.mrqx.huajiage.compat.ltc2.spiceevent.TimeStopSpiceEvent;

public class HuaJiSpiceEvents {
    public static void init() {
        SpiceEventRegistry.register(new TimeStopSpiceEvent());
    }
}
