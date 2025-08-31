package net.mrqx.huajiage.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class HuaJiCanFlyEvent extends PlayerEvent {
    private final ServerPlayer player;
    private boolean canFly = false;

    public HuaJiCanFlyEvent(ServerPlayer player) {
        super(player);
        this.player = player;
    }

    @Override
    public ServerPlayer getEntity() {
        return player;
    }

    public boolean canFly() {
        return this.canFly;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }
}
