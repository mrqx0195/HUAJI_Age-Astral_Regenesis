package net.mrqx.huajiage.item.equipment;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.init.HuaJiTiers;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.utils.HuajiSoundPlayer;
import net.mrqx.huajiage.utils.ItemTagHelper;

@Mod.EventBusSubscriber
public class ItemHuajiLaTiaoSword extends SwordItem {
    public ItemHuajiLaTiaoSword(Properties properties) {
        super(HuaJiTiers.HUAJI_LATIAO.get(), 12, -1, properties);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
        if (slot == EquipmentSlot.MAINHAND) {
            multimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier",
                    Mode.getMode(stack).equals(Mode.ON) ? -2.4 : -1, AttributeModifier.Operation.ADDITION));
            if (Mode.getMode(stack) == Mode.ON) {
                multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier",
                        6 + getDamage(), AttributeModifier.Operation.ADDITION));
            } else {
                multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier",
                        getDamage(), AttributeModifier.Operation.ADDITION));
            }
        } else {
            return super.getAttributeModifiers(slot, stack);
        }
        return multimap;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        if (entity instanceof LivingEntity living && living.getMainHandItem().equals(stack) && Mode.getMode(stack) == Mode.ON) {
            living.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 60, 0, false, false));
            living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 60, 1, false, false));
            living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60, 1, false, false));
        } else if (entity instanceof Player player && player.getMainHandItem().equals(stack)) {
            if (player.getFoodData().getFoodLevel() > 0 && stack.isDamaged()) {
                player.getFoodData().addExhaustion(4);
                stack.setDamageValue(Math.max(stack.getDamageValue() - 1, 0));
                HuajiSoundPlayer.playMovingSoundToClient(player, SoundEvents.PARROT_EAT, player.getSoundSource());
            }
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (Mode.getMode(stack).equals(Mode.ON)) {
            target.hurt(target.level().damageSources().lava(), 10);
            target.setSecondsOnFire(8);
            HuajiSoundPlayer.playMovingSoundToClient(target, SoundEvents.BLAZE_SHOOT, target.getSoundSource());
            HuajiSoundPlayer.playMovingSoundToClient(target, SoundEvents.GENERIC_BURN, target.getSoundSource(), 1, 0.1F);
        } else {
            target.addEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 2));
            stack.hurtAndBreak(1, attacker, living -> living.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent event) {
        if (!event.getEntity().getMainHandItem().is(HuaJiItems.HUAJI_LATIAO_SWORD.get())
                || event.getEntity().getCooldowns().isOnCooldown(HuaJiItems.HUAJI_LATIAO_SWORD.get())) {
            return;
        }
        Player player = event.getEntity();
        if (event instanceof PlayerInteractEvent.RightClickItem
                || event instanceof PlayerInteractEvent.RightClickBlock
                || event instanceof PlayerInteractEvent.EntityInteractSpecific) {
            ItemStack itemStack = player.getMainHandItem();
            if (player.isShiftKeyDown()) {
                Mode.changeMode(itemStack);
                player.getCooldowns().addCooldown(HuaJiItems.HUAJI_LATIAO_SWORD.get(), 10);
                if (Mode.getMode(itemStack).equals(Mode.ON)) {
                    HuajiSoundPlayer.playMovingSoundToClient(player, SoundEvents.BLAZE_SHOOT, player.getSoundSource());
                }
            } else if (Mode.getMode(itemStack).equals(Mode.ON) && player instanceof ServerPlayer serverPlayer) {
                if (serverPlayer.getFoodData().getFoodLevel() < 20) {
                    serverPlayer.getFoodData().setFoodLevel(serverPlayer.getFoodData().getFoodLevel() + 2);
                    itemStack.hurtAndBreak(20, serverPlayer, living -> living.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                }
                serverPlayer.setDeltaMovement(serverPlayer.getDeltaMovement().add(0, 0.6, 0));
                serverPlayer.fallDistance = 0;
                player.getCooldowns().addCooldown(HuaJiItems.HUAJI_LATIAO_SWORD.get(), 10);
                MinecraftServer server = serverPlayer.getServer();
                if (server != null) {
                    server.getPlayerList().getPlayers().forEach(serverPlayer1 -> serverPlayer1.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer)));
                }
                if (serverPlayer.level() instanceof ServerLevel level) {
                    Vec3 targetCoordinates = serverPlayer.position();
                    for (int i = 0; i < 10; ++i) {
                        for (int d = 0; d < 360; d += 15) {
                            level.sendParticles(ParticleTypes.FLAME,
                                    targetCoordinates.x + 0.5 * Math.sin(d), targetCoordinates.y + 0.1, targetCoordinates.z + 0.5 * Math.cos(d),
                                    0, 0.5 * Math.sin(d), 0, 0.5 * Math.cos(d), 1);
                        }
                    }
                }
            }
        }
    }

    public enum Mode {
        ON, OFF;

        public static Mode getMode(ItemStack itemStack) {
            if (ItemTagHelper.getBoolean(itemStack, "huaji_latiao_sword_mode", false)) {
                return ON;
            }
            return OFF;
        }

        public static Mode changeMode(ItemStack itemStack) {
            if (getMode(itemStack) == Mode.OFF) {
                ItemTagHelper.setBoolean(itemStack, "huaji_latiao_sword_mode", true);
                return ON;
            }
            ItemTagHelper.setBoolean(itemStack, "huaji_latiao_sword_mode", false);
            return OFF;
        }
    }
}
