package net.mrqx.huajiage.init;

import net.minecraft.server.level.TicketType;
import net.minecraft.util.Unit;

public class HuaJiTickets {
    public static final TicketType<Unit> HERO_TICKET = TicketType.create("hero_arrow", (a, b) -> 0, 5);
}
