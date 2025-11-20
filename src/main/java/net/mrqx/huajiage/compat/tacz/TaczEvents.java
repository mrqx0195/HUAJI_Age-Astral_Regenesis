package net.mrqx.huajiage.compat.tacz;

import com.mega.endinglib.util.time.TimeStopUtils;
import com.tacz.guns.api.item.IGun;
import com.tacz.guns.api.item.gun.AbstractGunItem;
import com.tacz.guns.entity.shooter.ShooterDataHolder;
import com.tacz.guns.item.ModernKineticGunScriptAPI;
import com.tacz.guns.resource.modifier.AttachmentPropertyManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.mrqx.huajiage.config.HuaJiCommonConfig;
import net.mrqx.huajiage.event.StandPunchEvent;
import net.mrqx.huajiage.item.equipment.armor.ItemFiftyFiftyHelmet;
import net.mrqx.huajiage.item.equipment.armor.ItemLordLuWing;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.registy.HuaJiSoundEvents;
import net.mrqx.huajiage.utils.HuaJiSoundPlayer;

import java.util.List;

public class TaczEvents {
    public static void register() {
        MinecraftForge.EVENT_BUS.addListener(TaczEvents::onAnvilUpdateEvent);
        MinecraftForge.EVENT_BUS.addListener(TaczEvents::onStandPunchEvent);
    }

    @SubscribeEvent
    public static void onAnvilUpdateEvent(AnvilUpdateEvent event) {
        if (!event.getOutput().isEmpty()) {
            return;
        }
        ItemStack base = event.getLeft();
        ItemStack material = event.getRight();
        if (base.isEmpty() || material.isEmpty()) {
            return;
        }

        if (base.is(HuaJiItems.LORD_LU_WING.get()) && material.getItem() instanceof IGun gun) {
            if (HuaJiCommonConfig.LORD_LU_WING_ALL_GUN.get() || gun.getGunId(material).equals(ResourceLocation.fromNamespaceAndPath("tacz", "sks_tactical"))) {
                ItemStack output = base.copy();

                ItemLordLuWing.addGunToWing(output, material);

                event.setMaterialCost(1);
                event.setCost(30);
                event.setOutput(output);
            }
        }
    }

    @SubscribeEvent
    public static void onStandPunchEvent(StandPunchEvent event) {
        LivingEntity living = event.getEntity();
        tryWingShoot(living);
    }

    @SuppressWarnings("ConstantValue")
    public static void tryWingShoot(LivingEntity living) {
        ItemStack wing = living.getItemBySlot(EquipmentSlot.CHEST);
        if (wing.is(HuaJiItems.LORD_LU_WING.get())) {
            double chance = ItemFiftyFiftyHelmet.isFiftyFiftyLord(living) ? 1 : ItemFiftyFiftyHelmet.isFiftyFiftyActive(living) ? 2 / 3.0 : 1 / 3.0;
            List<ItemStack> guns = ItemLordLuWing.getGunsFromWing(wing);
            for (ItemStack gun : guns) {
                if (gun.getItem() instanceof AbstractGunItem && living.level().random.nextDouble() < chance && ItemLordLuWing.gunCounterUpdate(gun)) {
                    ShooterDataHolder dataHolder = new ShooterDataHolder();
                    ModernKineticGunScriptAPI api = new ModernKineticGunScriptAPI();
                    api.setItemStack(gun);
                    api.setShooter(living);
                    api.setDataHolder(dataHolder);
                    api.setPitchSupplier(living::getXRot);
                    api.setYawSupplier(living::getYHeadRot);
                    AttachmentPropertyManager.postChangeEvent(living, gun);
                    if (api instanceof IHuaJiGunScriptApi api1) {
                        api1.huajiAge$shootOnceUnchecked();
                    }
                }
            }
            boolean isTimeStopping = TimeStopUtils.isTimeStop && TimeStopUtils.andSameDimension(living.level());
            if (ItemLordLuWing.soundCounterUpdate(wing, isTimeStopping)) {
                HuaJiSoundPlayer.playMovingSoundToClient(living, HuaJiSoundEvents.LBWNB.get(), 0.75F);
            } else {
                HuaJiSoundPlayer.playMovingSoundToClient(living, HuaJiSoundEvents.LBWNB.get(), 0);
            }
        }
    }
}
