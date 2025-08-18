package net.mrqx.huajiage.event.handler;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.item.equipment.armor.ItemFiftyFiftyHelmet;

@Mod.EventBusSubscriber
public class PlayerTickHandler {
    @SubscribeEvent
    public static void onPlayerTickEvent(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (player instanceof ServerPlayer serverPlayer) {
            CompoundTag persistentData = serverPlayer.getPersistentData();
            if (ItemFiftyFiftyHelmet.isFiftyFiftyLord(serverPlayer)) {
                serverPlayer.getAbilities().mayfly = true;
                serverPlayer.onUpdateAbilities();
                persistentData.putBoolean(ItemFiftyFiftyHelmet.FIFTY_FIFTY_LORD_FLY_KEY, true);
            } else if (persistentData.getBoolean(ItemFiftyFiftyHelmet.FIFTY_FIFTY_LORD_FLY_KEY)) {
                Abilities abilities = new Abilities();
                serverPlayer.gameMode.getGameModeForPlayer().updatePlayerAbilities(abilities);
                if (!abilities.mayfly) {
                    serverPlayer.getAbilities().mayfly = false;
                    serverPlayer.getAbilities().flying = false;
                    serverPlayer.onUpdateAbilities();
                }
                persistentData.putBoolean(ItemFiftyFiftyHelmet.FIFTY_FIFTY_LORD_FLY_KEY, false);
            }
        }
    }
}
