package net.mrqx.huajiage.event.handler;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.capability.stand.IStandData;
import net.mrqx.huajiage.capability.stand.StandDataCapabilityProvider;
import net.mrqx.huajiage.data.HuaJiDamageTypes;
import net.mrqx.huajiage.event.HuaJiCanFlyEvent;
import net.mrqx.huajiage.item.stand.ItemSingularity;
import net.mrqx.huajiage.network.NetworkManager;
import net.mrqx.huajiage.network.StandSyncMessage;
import net.mrqx.huajiage.stand.Stand;
import net.mrqx.huajiage.utils.AdvancementHelper;
import net.mrqx.huajiage.utils.HuaJiDamageSources;
import net.mrqx.huajiage.utils.HuaJiSoundPlayer;

@Mod.EventBusSubscriber
public class TickHandler {
    public static final String HUAJI_FLY_KEY = HuaJiAgeMod.MODID + "." + "flying";

    @SubscribeEvent
    public static void onPlayerTickEvent(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (player instanceof ServerPlayer serverPlayer) {
            CompoundTag persistentData = serverPlayer.getPersistentData();
            HuaJiCanFlyEvent canFlyEvent = new HuaJiCanFlyEvent(serverPlayer);
            MinecraftForge.EVENT_BUS.post(canFlyEvent);
            if (canFlyEvent.canFly()) {
                serverPlayer.getAbilities().mayfly = true;
                serverPlayer.onUpdateAbilities();
                persistentData.putBoolean(HUAJI_FLY_KEY, true);
            } else if (persistentData.getBoolean(HUAJI_FLY_KEY)) {
                Abilities abilities = new Abilities();
                serverPlayer.gameMode.getGameModeForPlayer().updatePlayerAbilities(abilities);
                if (!abilities.mayfly) {
                    serverPlayer.getAbilities().mayfly = false;
                    serverPlayer.getAbilities().flying = false;
                    serverPlayer.onUpdateAbilities();
                }
                persistentData.putBoolean(HUAJI_FLY_KEY, false);
            }

            if (persistentData.contains(ItemSingularity.SINGULARITY_COUNT, Tag.TAG_INT)) {
                int count = persistentData.getInt(ItemSingularity.SINGULARITY_COUNT);
                if (count <= 0 && serverPlayer.isAlive()) {
                    persistentData.remove(ItemSingularity.SINGULARITY_COUNT);
                    serverPlayer.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
                        Stand stand = Stand.getStand(data.getStand());
                        if (stand != null && stand.getMaxLevel() >= data.getLevel() + 1) {
                            data.setLevel(Math.min(stand.getMaxLevel(), data.getLevel() + 1));
                            HuaJiSoundPlayer.playMovingSoundToClient(serverPlayer, SoundEvents.UI_TOAST_CHALLENGE_COMPLETE, 2);
                            AdvancementHelper.grantCriterion(serverPlayer, AdvancementHelper.SINGULARITY);
                            if (serverPlayer.level() instanceof ServerLevel level) {
                                Vec3 targetCoordinates = serverPlayer.getEyePosition();
                                for (int d = 0; d < 360; d += 15) {
                                    level.sendParticles(ParticleTypes.TOTEM_OF_UNDYING,
                                            targetCoordinates.x + 0.5 * Math.sin(d), targetCoordinates.y + 0.1, targetCoordinates.z + 0.5 * Math.cos(d),
                                            0, 0.5 * Math.sin(d), 0, 0.5 * Math.cos(d), 1);
                                }
                            }
                        }
                    });
                } else {
                    persistentData.putInt(ItemSingularity.SINGULARITY_COUNT, count - 1);
                    serverPlayer.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data ->
                            serverPlayer.hurt(HuaJiDamageSources.simpleNullSource(serverPlayer.level(), HuaJiDamageTypes.SINGULARITY), data.getLevel()));
                    serverPlayer.hurt(HuaJiDamageSources.simpleNullSource(serverPlayer.level(), HuaJiDamageTypes.SINGULARITY), 1);
                    if (!serverPlayer.isAlive()) {
                        persistentData.remove(ItemSingularity.SINGULARITY_COUNT);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingTickEvent(LivingEvent.LivingTickEvent event) {
        event.getEntity().getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
            Stand stand = Stand.getStand(data.getStand());
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
