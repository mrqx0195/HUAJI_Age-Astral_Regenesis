package net.mrqx.huajiage.compat.slashblade.specialeffect;

import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.mrqx.huajiage.compat.slashblade.registy.HuaJiSpecialEffects;
import net.mrqx.huajiage.registy.HuaJiItems;

public class SwornKinship extends SpecialEffect {
    public SwornKinship() {
        super(50, false, false);
    }

    @SubscribeEvent
    public static void onUpdateEvent(SlashBladeEvent.UpdateEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        if (!event.isSelected()) {
            return;
        }
        if (isEffective(player)) {
            ItemStack offhandItem = player.getOffhandItem();
            if (offhandItem.is(HuaJiItems.EXGLUTENBUR.get())) {
                offhandItem.inventoryTick(player.level(), player, EquipmentSlot.OFFHAND.getIndex(), true);
            }
        }
    }

    @SubscribeEvent
    public static void onHitEvent(SlashBladeEvent.HitEvent event) {
        if (!(event.getUser() instanceof Player player)) {
            return;
        }
        if (isEffective(player)) {
            ItemStack offhandItem = player.getOffhandItem();
            if (offhandItem.is(HuaJiItems.EXGLUTENBUR.get())) {
                LivingEntity target = event.getTarget();
                offhandItem.hurtEnemy(target, player);
                offhandItem.getItem().onLeftClickEntity(offhandItem, player, target);
            }
        }
    }

    public static boolean isEffective(LivingEntity living) {
        LazyOptional<ISlashBladeState> capability = living.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE);
        if (capability.isPresent() && capability.orElseThrow(NullPointerException::new).hasSpecialEffect(HuaJiSpecialEffects.SWORN_KINSHIP.getId())) {
            return living.getItemBySlot(EquipmentSlot.OFFHAND).is(HuaJiItems.EXGLUTENBUR.get())
                    && (!(living instanceof Player player) || SpecialEffect.isEffective(HuaJiSpecialEffects.SWORN_KINSHIP.get(), player.experienceLevel));
        }
        return false;
    }
}
