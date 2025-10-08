package net.mrqx.huajiage.event.handler;

import com.google.common.collect.ImmutableList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.capability.stand.StandDataCapabilityProvider;
import net.mrqx.huajiage.client.event.OrgaClientHandler;
import net.mrqx.huajiage.data.HuaJiDamageTypes;
import net.mrqx.huajiage.item.equipment.armor.ItemOrgaArmor;
import net.mrqx.huajiage.item.stand.ItemOrgaRequiem;
import net.mrqx.huajiage.registy.HuaJiEffects;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.registy.HuaJiSoundEvents;
import net.mrqx.huajiage.stand.Stand;
import net.mrqx.huajiage.stand.StandOrgaRequiem;
import net.mrqx.huajiage.utils.HuaJiDamageSources;
import net.mrqx.huajiage.utils.HuaJiSoundPlayer;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

/**
 * 来自五个类的石山……我已经在尽力优化了
 *
 * @see OrgaClientHandler
 */
@Mod.EventBusSubscriber
public class OrgaEventHandler {
    private static final String PLAYER_UUID = HuaJiAgeMod.MODID + "." + "orgaPlayerUuid";
    private static final String REQUIEM = HuaJiAgeMod.MODID + "." + "orgaRequiem";
    public static final int HOPE_FLOWER_TIME = 136 * 20;

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onLivingTickEvent(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.level().isClientSide) {
            return;
        }
        handleOrgaEntityUpdate(entity);
        handleRequiemTargetUpdate(entity);
        handleRequiemStateUpdate(entity);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onLivingDeathEvent(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.level().isClientSide) {
            return;
        }
        handleOrgaEntityDeath(event, entity);
        handleStandOrgaDeath(event, entity);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onLivingHurtEvent(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.level().isClientSide) {
            return;
        }
        Entity attacker = event.getSource().getEntity();
        handleOrgaEntityHurt(event, entity, attacker);
        handleStandOrgaHurt(event, entity, attacker);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onLivingAttackEvent(LivingAttackEvent event) {
        Entity entity = event.getSource().getEntity();
        LivingEntity target = event.getEntity();
        if (target.level().isClientSide) {
            return;
        }
        if (entity instanceof LivingEntity living && living.hasEffect(HuaJiEffects.REQUIEM.get()) && target instanceof LivingEntity) {
            HuaJiSoundPlayer.playMovingSoundToClient(target, HuaJiSoundEvents.ORGA_REQUIEM_HIT.get());
            target.hurt(HuaJiDamageSources.simpleNullSource(living.level(), HuaJiDamageTypes.REQUIEM), 5);
            target.getPersistentData().putInt(REQUIEM, 60);
            target.getPersistentData().putString(PLAYER_UUID, living.getStringUUID());
        }
    }

    private static void handleOrgaEntityUpdate(LivingEntity living) {
        MobEffectInstance effectHopeFlower = living.getEffect(HuaJiEffects.HOPE_FLOWER.get());
        if (effectHopeFlower != null) {
            int hopeDuration = effectHopeFlower.getDuration();
            if (ItemOrgaRequiem.hasValidOrgaRequiem(living)) {
                living.removeEffect(HuaJiEffects.HOPE_FLOWER.get());
                living.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
            }
            if (hopeDuration == HOPE_FLOWER_TIME) {
                living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, hopeDuration, 5));
                living.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, hopeDuration, 5));
                living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, hopeDuration, 5));
                HuaJiSoundPlayer.playMovingSoundToClient(living, HuaJiSoundEvents.ORGA_FLOWER.get(), 5);
            }
            if (hopeDuration == 844) {
                living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 844, 9));
                living.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 844, 9));
                living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 844, 9));
                living.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 844));
                living.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 844));
            }
            if (hopeDuration < 20 && !living.getCapability(StandDataCapabilityProvider.STAND_DATA).map(data -> Stand.getStand(data.getStand()) instanceof StandOrgaRequiem).orElse(false)) {
                living.hurt(HuaJiDamageSources.simpleNullSource(living.level(), HuaJiDamageTypes.HOPE_FLOWER), Integer.MAX_VALUE);
            }
        }
        MobEffectInstance effectRequiem = living.getEffect(HuaJiEffects.REQUIEM.get());
        if (effectRequiem != null) {
            boolean hasOrga = ItemOrgaArmor.hasAllOrgaArmor(living);
            if (hasOrga) {
                boolean hasRequiemItem = ItemOrgaRequiem.hasValidOrgaRequiem(living);
                boolean hasStandPotion = living.hasEffect(HuaJiEffects.STAND_POWER.get());
                if (!hasRequiemItem && !hasStandPotion) {
                    living.removeEffect(HuaJiEffects.REQUIEM.get());
                }
            }
            int requiemDuration = effectRequiem.getDuration();
            if (requiemDuration == 30 * 20 - 1) {
                living.sendSystemMessage(Component.translatable("message.huajiage.orga_requiem.bgm.1"));
                living.sendSystemMessage(Component.translatable("message.huajiage.orga_requiem.bgm.2"));
            }
        }
    }

    private static void handleRequiemTargetUpdate(LivingEntity target) {
        String playerUuid = target.getPersistentData().getString(PLAYER_UUID);
        if (!playerUuid.isEmpty()) {
            Player player = target.level().getPlayerByUUID(UUID.fromString(playerUuid));
            if (player != null) {
                int requiemTime = target.getPersistentData().getInt(REQUIEM);
                if (requiemTime <= 0) {
                    return;
                }
                target.getPersistentData().putInt(REQUIEM, requiemTime - 1);
                if (target.tickCount % 5 == 0) {
                    target.hurt(HuaJiDamageSources.simpleNullSource(player.level(), HuaJiDamageTypes.REQUIEM), player.getMaxHealth() / 2);
                }
            }
        }
        MobEffectInstance effectOrgaTarget = target.getEffect(HuaJiEffects.ORGA_TARGET.get());
        if (effectOrgaTarget != null) {
            int targetDuration = effectOrgaTarget.getDuration();
            if (targetDuration == 30) {
                Player nearestPlayer = target.level().getNearestPlayer(target, 100);
                if (nearestPlayer != null && target != nearestPlayer) {
                    if (!nearestPlayer.getInventory().contains(new ItemStack(HuaJiItems.ORGA_REQUIEM.get()))) {
                        nearestPlayer.sendSystemMessage(Component.translatable("message.huajiage.orga.shot"));
                    }
                    target.hurt(HuaJiDamageSources.simple(nearestPlayer, HuaJiDamageTypes.ORGA_SHOT), 30);
                } else {
                    target.hurt(HuaJiDamageSources.simpleNullSource(target.level(), HuaJiDamageTypes.ORGA_SHOT), 30);
                }
            }
        }
    }

    private static void handleRequiemStateUpdate(LivingEntity target) {
        if (target.hasEffect(HuaJiEffects.REQUIEM_TARGET.get())) {
            if (!target.hasEffect(HuaJiEffects.HOPE_FLOWER.get())
                    && !target.hasEffect(HuaJiEffects.REQUIEM.get())) {
                String playerUuid = target.getPersistentData().getString(PLAYER_UUID);
                if (!playerUuid.isEmpty()) {
                    Player player = target.level().getPlayerByUUID(UUID.fromString(playerUuid));
                    if (target.tickCount % (target instanceof Player ? 10 : 5) == 0) {
                        if (player != null) {
                            target.hurt(HuaJiDamageSources.simple(player, HuaJiDamageTypes.REQUIEM), 12 + player.getMaxHealth());
                        } else {
                            target.hurt(HuaJiDamageSources.simpleNullSource(target.level(), HuaJiDamageTypes.REQUIEM), 32);
                        }
                    }
                }
            }
        }
    }

    private static void handleOrgaEntityDeath(LivingDeathEvent event, LivingEntity living) {
        if (ItemOrgaArmor.hasAllOrgaArmor(living)) {
            if (!living.hasEffect(HuaJiEffects.HOPE_FLOWER.get()) && !living.hasEffect(HuaJiEffects.REQUIEM.get())) {
                if (ItemOrgaRequiem.hasValidOrgaRequiem(living)) {
                    living.addEffect(new MobEffectInstance(HuaJiEffects.REQUIEM.get(), 30 * 20));
                } else {
                    int hopeDuration = HOPE_FLOWER_TIME + 100;
                    living.addEffect(new MobEffectInstance(HuaJiEffects.HOPE_FLOWER.get(), hopeDuration));
                }
            }
            MobEffectInstance effect = living.getEffect(HuaJiEffects.HOPE_FLOWER.get());
            if (effect == null || effect.getDuration() >= 20) {
                event.setCanceled(true);
                living.setHealth(1);
            }
        } else {
            MobEffectInstance effect = living.getEffect(HuaJiEffects.HOPE_FLOWER.get());
            if (effect != null && effect.getDuration() >= 20) {
                event.setCanceled(true);
                living.setHealth(1);
            }
        }
    }

    private static void handleStandOrgaDeath(LivingDeathEvent event, LivingEntity entity) {
        entity.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
            Stand stand = Stand.getStand(data.getStand());
            if (stand instanceof StandOrgaRequiem && data.isTriggered()) {
                event.setCanceled(true);
                entity.setHealth(1f);
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 4));
                entity.addEffect(new MobEffectInstance(MobEffects.JUMP, 100, 2));
                HuaJiSoundPlayer.playMovingSoundToClient(entity, HuaJiSoundEvents.ORGA_REQUIEM_PROTECT.get());
            }
        });
    }

    private static void handleOrgaEntityHurt(LivingHurtEvent event, LivingEntity living, @Nullable Entity attacker) {
        if (living.hasEffect(HuaJiEffects.HOPE_FLOWER.get()) && attacker instanceof LivingEntity livingAttacker && attacker != living) {
            if (ItemOrgaRequiem.hasValidOrgaRequiem(living)) {
                attacker.hurt(HuaJiDamageSources.simple(living, HuaJiDamageTypes.REQUIEM), event.getAmount() * 2);
            }
            if (living.getHealth() < 2) {
                if (!livingAttacker.hasEffect(HuaJiEffects.ORGA_TARGET.get())) {
                    if (!ItemOrgaRequiem.hasValidOrgaRequiem(living)) {
                        HuaJiSoundPlayer.playMovingSoundToClient(living, HuaJiSoundEvents.ORGA_SHOT.get());
                    }
                    livingAttacker.addEffect(new MobEffectInstance(HuaJiEffects.ORGA_TARGET.get(), 100));
                }
            }
        }
    }

    private static void handleStandOrgaHurt(LivingHurtEvent event, LivingEntity entity, @Nullable Entity attacker) {
        entity.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
            Stand stand = Stand.getStand(data.getStand());
            if (stand instanceof StandOrgaRequiem && data.isTriggered()) {
                if (attacker instanceof LivingEntity living && attacker != entity) {
                    attacker.hurt(HuaJiDamageSources.simple(entity, HuaJiDamageTypes.REQUIEM), event.getAmount() * 2);
                    if (entity.getHealth() < 2) {
                        if (!living.hasEffect(HuaJiEffects.REQUIEM_TARGET.get())) {
                            living.addEffect(new MobEffectInstance(HuaJiEffects.REQUIEM_TARGET.get(), 100));
                        }
                    }
                }
            }
        });
    }

    private static final List<Integer> SING_LIST = ImmutableList.of(104, 244, 368, 474, 544, 776, 862, 942, 962, 1010, 1096, 1226, 1364, 1460, 1470, 1520, 1566, 1620, 1700, 1876, 1956, 2008, 2226, 2260, 2280, 2520);

    @SubscribeEvent
    public static void onOrgaSinging(LivingEvent.LivingTickEvent event) {
        LivingEntity living = event.getEntity();
        if (living.level().isClientSide) {
            return;
        }
        MobEffectInstance effect = living.getEffect(HuaJiEffects.HOPE_FLOWER.get());
        if (effect != null) {
            int duration = HOPE_FLOWER_TIME - effect.getDuration();
            if (SING_LIST.contains(duration)) {
                int i = SING_LIST.indexOf(duration) + 1;
                living.sendSystemMessage(Component.translatable("message.huajiage.orga.sing." + String.format("%02d", i)));
            }
            if (duration >= 1876 && duration <= 1876 + 17) {
                living.sendSystemMessage(Component.translatable("message.huajiage.orga.sing.p" + String.format("%02d", duration - 1876)));
            }
        }
    }
}
