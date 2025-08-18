package net.mrqx.huajiage.event;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class ChangeModeEvent extends PlayerEvent {
    public final boolean shift;

    public ChangeModeEvent(Player player, boolean shift) {
        super(player);
        this.shift = shift;
    }
}
