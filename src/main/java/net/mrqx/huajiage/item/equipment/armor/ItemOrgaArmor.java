package net.mrqx.huajiage.item.equipment.armor;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.mrqx.huajiage.client.HuaJiLayers;
import net.mrqx.huajiage.client.model.HuaJiArmorModel;
import net.mrqx.huajiage.registy.HuaJiItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public abstract class ItemOrgaArmor extends ArmorItem {
    public ItemOrgaArmor(Type pType) {
        super(HuaJiArmorMaterials.ORGA, pType, new Item.Properties().rarity(Rarity.RARE).fireResistant());
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return "huajiage:textures/models/armor/orga.png";
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                EntityModelSet models = Minecraft.getInstance().getEntityModels();
                ModelPart root = models.bakeLayer(HuaJiLayers.ORGA_HAIR);
                return new HuaJiArmorModel(root);
            }
        });
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.huajiage.orga.tooltips.1").withStyle(ChatFormatting.AQUA, ChatFormatting.BOLD));
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            tooltip.add(Component.translatable("message.huajiage.prefix", Component.translatable(HuaJiItems.ORGA_HELMET.get().getDescriptionId())
                    .withStyle(player.getInventory().armor.get(3).is(HuaJiItems.ORGA_HELMET.get()) ? ChatFormatting.YELLOW : ChatFormatting.GRAY)));
            tooltip.add(Component.translatable("message.huajiage.prefix", Component.translatable(HuaJiItems.ORGA_CHESTPLATE.get().getDescriptionId())
                    .withStyle(player.getInventory().armor.get(2).is(HuaJiItems.ORGA_CHESTPLATE.get()) ? ChatFormatting.YELLOW : ChatFormatting.GRAY)));
            tooltip.add(Component.translatable("message.huajiage.prefix", Component.translatable(HuaJiItems.ORGA_LEGGINGS.get().getDescriptionId())
                    .withStyle(player.getInventory().armor.get(1).is(HuaJiItems.ORGA_LEGGINGS.get()) ? ChatFormatting.YELLOW : ChatFormatting.GRAY)));
            tooltip.add(Component.translatable("message.huajiage.prefix", Component.translatable(HuaJiItems.ORGA_BOOTS.get().getDescriptionId())
                    .withStyle(player.getInventory().armor.get(0).is(HuaJiItems.ORGA_BOOTS.get()) ? ChatFormatting.YELLOW : ChatFormatting.GRAY)));
        }
    }

    public static boolean hasAllOrgaArmor(LivingEntity livingEntity) {
        return livingEntity.getItemBySlot(EquipmentSlot.FEET).is(HuaJiItems.ORGA_BOOTS.get())
                && livingEntity.getItemBySlot(EquipmentSlot.LEGS).is(HuaJiItems.ORGA_LEGGINGS.get())
                && livingEntity.getItemBySlot(EquipmentSlot.CHEST).is(HuaJiItems.ORGA_CHESTPLATE.get())
                && livingEntity.getItemBySlot(EquipmentSlot.HEAD).is(HuaJiItems.ORGA_HELMET.get());
    }

    public static class ItemOrgaArmorHelmet extends ItemOrgaArmor {
        public ItemOrgaArmorHelmet() {
            super(Type.HELMET);
        }
    }

    public static class ItemOrgaArmorChestplate extends ItemOrgaArmor {
        public ItemOrgaArmorChestplate() {
            super(Type.CHESTPLATE);
        }
    }

    public static class ItemOrgaArmorLeggings extends ItemOrgaArmor {
        public ItemOrgaArmorLeggings() {
            super(Type.LEGGINGS);
        }
    }

    public static class ItemOrgaArmorBoots extends ItemOrgaArmor {
        public ItemOrgaArmorBoots() {
            super(Type.BOOTS);
        }
    }
}
