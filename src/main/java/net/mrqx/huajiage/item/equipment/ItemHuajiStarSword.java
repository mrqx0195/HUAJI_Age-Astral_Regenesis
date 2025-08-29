package net.mrqx.huajiage.item.equipment;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.init.HuaJiTiers;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.registy.HuaJiSoundEvents;
import net.mrqx.huajiage.utils.HuaJiSoundPlayer;
import net.mrqx.huajiage.utils.ItemTagHelper;

@Mod.EventBusSubscriber
public class ItemHuajiStarSword extends SwordItem {
    public ItemHuajiStarSword() {
        super(HuaJiTiers.HUAJI_STAR.get(), 14, -1.4F, new Item.Properties().rarity(Rarity.RARE).durability(5400));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
        if (slot == EquipmentSlot.MAINHAND) {
            multimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier",
                    Mode.getMode(stack).equals(Mode.ON) ? -1.4 : -2.4, AttributeModifier.Operation.ADDITION));
            if (Mode.getMode(stack) == Mode.ON) {
                multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier",
                        9 + getDamage(), AttributeModifier.Operation.ADDITION));
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
            living.addEffect(new MobEffectInstance(MobEffects.HUNGER, 40, 0, false, false));
            living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 40, 3, false, false));
            living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 3, false, false));
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (Mode.getMode(stack).equals(Mode.ON)) {
            target.hurt(target.level().damageSources().fellOutOfWorld(), 10);
            target.setSecondsOnFire(5);
            HuaJiSoundPlayer.playMovingSoundToClient(target, HuaJiSoundEvents.WAVE1.get(), target.getSoundSource());
            HuaJiSoundPlayer.playMovingSoundToClient(target, HuaJiSoundEvents.ENERGY_HIT.get(), target.getSoundSource(), 1, 0.1F);
        } else {
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 2));
            target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 2));
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent event) {
        if (!event.getEntity().getMainHandItem().is(HuaJiItems.HUAJI_STAR_SWORD.get())
                || !event.getEntity().isShiftKeyDown()
                || event.getEntity().getCooldowns().isOnCooldown(HuaJiItems.HUAJI_STAR_SWORD.get())) {
            return;
        }
        Player player = event.getEntity();
        if (event instanceof PlayerInteractEvent.RightClickItem
                || event instanceof PlayerInteractEvent.RightClickBlock
                || event instanceof PlayerInteractEvent.EntityInteractSpecific) {
            Mode.changeMode(player.getMainHandItem());
            player.getCooldowns().addCooldown(HuaJiItems.HUAJI_STAR_SWORD.get(), 10);
            if (Mode.getMode(player.getMainHandItem()).equals(Mode.ON)) {
                HuaJiSoundPlayer.playMovingSoundToClient(player, HuaJiSoundEvents.CHARGE.get(), player.getSoundSource());
            }
        }
    }

    public enum Mode {
        ON, OFF;

        public static Mode getMode(ItemStack itemStack) {
            if (ItemTagHelper.getBoolean(itemStack, "huaji_star_sword_mode", false)) {
                return ON;
            }
            return OFF;
        }

        public static Mode changeMode(ItemStack itemStack) {
            if (getMode(itemStack) == Mode.OFF) {
                ItemTagHelper.setBoolean(itemStack, "huaji_star_sword_mode", true);
                return ON;
            }
            ItemTagHelper.setBoolean(itemStack, "huaji_star_sword_mode", false);
            return OFF;
        }
    }
}
