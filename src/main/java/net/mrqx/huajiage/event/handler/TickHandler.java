package net.mrqx.huajiage.event.handler;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import net.mrqx.huajiage.capability.stand.IStandData;
import net.mrqx.huajiage.capability.stand.StandDataCapabilityProvider;
import net.mrqx.huajiage.item.equipment.armor.ItemFiftyFiftyHelmet;
import net.mrqx.huajiage.network.NetworkManager;
import net.mrqx.huajiage.network.StandSyncMessage;
import net.mrqx.huajiage.stand.AbstractStand;

@Mod.EventBusSubscriber
public class TickHandler {
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

    @SubscribeEvent
    public static void onLivingTickEvent(LivingEvent.LivingTickEvent event) {
        event.getEntity().getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
            AbstractStand stand = AbstractStand.getStand(data.getStand());
            data.getScheduler().onTick(event.getEntity());
            if (stand != null) {
                stand.tick(event.getEntity(), data);
            }
            if (!event.getEntity().level().isClientSide) {
                StandSyncMessage message = new StandSyncMessage();
                message.data = IStandData.serializeNBT(data);
                message.entityId = event.getEntity().getId();
                event.getEntity().level().players().forEach(player -> {
                    if (player instanceof ServerPlayer serverPlayer) {
                        NetworkManager.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer), message);
                    }
                });
            }
        });
    }
}
