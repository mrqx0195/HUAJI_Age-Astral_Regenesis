package net.mrqx.huajiage.event;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.mrqx.huajiage.network.HuaJiKeyMessage;

import java.util.EnumSet;

public class KeyInputEvent extends PlayerEvent {
    public final EnumSet<HuaJiKeyMessage.Keys> oldCommand;
    public final EnumSet<HuaJiKeyMessage.Keys> currentCommand;

    public KeyInputEvent(Player player, EnumSet<HuaJiKeyMessage.Keys> oldCommand, EnumSet<HuaJiKeyMessage.Keys> currentCommand) {
        super(player);
        this.oldCommand = oldCommand;
        this.currentCommand = currentCommand;
    }
}
