package net.mrqx.huajiage.item.equipment;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.init.HuaJiTiers;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.utils.HuaJiMathHelper;
import net.mrqx.huajiage.utils.HuaJiSoundPlayer;
import net.mrqx.huajiage.utils.ItemTagHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class ItemHuajiLaTiaoSword extends SwordItem {

    public static final String HUAJI_LATIAO_SWORD_MODE_KEY = "huaji_latiao_sword_mode";

    public ItemHuajiLaTiaoSword() {
        super(HuaJiTiers.HUAJI_LATIAO.get(), 12, -1, new Item.Properties().rarity(Rarity.RARE).durability(3998));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable(this.getDescriptionId() + ".tooltips.1",
                Minecraft.getInstance().options.keyShift.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.YELLOW, ChatFormatting.ITALIC),
                Minecraft.getInstance().options.keyUse.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.YELLOW, ChatFormatting.ITALIC)
        ).withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD));
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
                HuaJiSoundPlayer.playMovingSoundToClient(player, SoundEvents.PARROT_EAT);
            }
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (Mode.getMode(stack).equals(Mode.ON)) {
            target.hurt(target.level().damageSources().lava(), 10);
            target.setSecondsOnFire(8);
            HuaJiSoundPlayer.playMovingSoundToClient(target, SoundEvents.BLAZE_SHOOT);
            HuaJiSoundPlayer.playMovingSoundToClient(target, SoundEvents.GENERIC_BURN, 1, 0.1F);
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
                    HuaJiSoundPlayer.playMovingSoundToClient(player, SoundEvents.BLAZE_SHOOT);
                }
            } else if (Mode.getMode(itemStack).equals(Mode.ON) && player instanceof ServerPlayer serverPlayer) {
                if (serverPlayer.getFoodData().getFoodLevel() < 20) {
                    serverPlayer.getFoodData().setFoodLevel(serverPlayer.getFoodData().getFoodLevel() + 2);
                    itemStack.hurtAndBreak(10, serverPlayer, living -> living.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                }
                List<LivingEntity> entities = serverPlayer.level().getEntitiesOfClass(LivingEntity.class, serverPlayer.getBoundingBox().inflate(2));
                for (LivingEntity entity : entities) {
                    if (entity != serverPlayer) {
                        Vec3 vec = HuaJiMathHelper.getVectorEntity(serverPlayer, entity);
                        if (vec.length() != 0) {
                            entity.hurt(entity.level().damageSources().lava(), (float) (20f / vec.length()));
                            entity.setDeltaMovement(entity.getDeltaMovement().add(-vec.x / vec.length(), -vec.y / vec.length(), -vec.z / vec.length()));
                        }
                    }
                }
                serverPlayer.setDeltaMovement(serverPlayer.getDeltaMovement().x, 0.6, serverPlayer.getDeltaMovement().z);
                serverPlayer.fallDistance = 0;
                player.getCooldowns().addCooldown(HuaJiItems.HUAJI_LATIAO_SWORD.get(), 10);
                MinecraftServer server = serverPlayer.getServer();
                if (server != null) {
                    server.getPlayerList().getPlayers().forEach(serverPlayer1 -> serverPlayer1.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer)));
                }
                if (serverPlayer.level() instanceof ServerLevel level) {
                    Vec3 targetCoordinates = serverPlayer.position();
                    for (int d = 0; d < 360; d += 15) {
                        level.sendParticles(ParticleTypes.FLAME,
                                targetCoordinates.x + 0.5 * Math.sin(d), targetCoordinates.y + 0.1, targetCoordinates.z + 0.5 * Math.cos(d),
                                0, 0.5 * Math.sin(d), 0, 0.5 * Math.cos(d), 1);
                    }
                }
            }
        }
    }

    public enum Mode {
        /**
         * Active Mode
         */
        ON,
        /**
         * Inactive Mode
         */
        OFF;

        public static Mode getMode(ItemStack itemStack) {
            if (ItemTagHelper.getBoolean(itemStack, HUAJI_LATIAO_SWORD_MODE_KEY, false)) {
                return ON;
            }
            return OFF;
        }

        @CanIgnoreReturnValue
        public static Mode changeMode(ItemStack itemStack) {
            if (getMode(itemStack) == Mode.OFF) {
                ItemTagHelper.setBoolean(itemStack, HUAJI_LATIAO_SWORD_MODE_KEY, true);
                return ON;
            }
            ItemTagHelper.setBoolean(itemStack, HUAJI_LATIAO_SWORD_MODE_KEY, false);
            return OFF;
        }
    }
}
