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
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.mrqx.huajiage.client.HuaJiLayers;
import net.mrqx.huajiage.client.model.HuaJiArmorModel;
import net.mrqx.huajiage.registy.HuaJiItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public abstract class ItemOrgaArmor extends ArmorItem {
    public ItemOrgaArmor(Type pType, Properties pProperties) {
        super(HuaJiArmorMaterials.ORGA, pType, pProperties);
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return "huajiage:textures/models/armor/orga.png";
    }

    @Override
    public void initializeClient(@NotNull Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                EntityModelSet models = Minecraft.getInstance().getEntityModels();
                ModelPart root = models.bakeLayer(HuaJiLayers.ORGA_HAIR);
                return new HuaJiArmorModel(root);
            }
        });
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
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

    public static boolean hasAllOrgaArmor(Player player) {
        return player.getInventory().armor.get(0).is(HuaJiItems.ORGA_BOOTS.get())
                && player.getInventory().armor.get(1).is(HuaJiItems.ORGA_LEGGINGS.get())
                && player.getInventory().armor.get(2).is(HuaJiItems.ORGA_CHESTPLATE.get())
                && player.getInventory().armor.get(3).is(HuaJiItems.ORGA_HELMET.get());
    }

    public static class ItemOrgaArmorHelmet extends ItemOrgaArmor {
        public ItemOrgaArmorHelmet(Properties pProperties) {
            super(Type.HELMET, pProperties);
        }
    }

    public static class ItemOrgaArmorChestplate extends ItemOrgaArmor {
        public ItemOrgaArmorChestplate(Properties pProperties) {
            super(Type.CHESTPLATE, pProperties);
        }
    }

    public static class ItemOrgaArmorLeggings extends ItemOrgaArmor {
        public ItemOrgaArmorLeggings(Properties pProperties) {
            super(Type.LEGGINGS, pProperties);
        }
    }

    public static class ItemOrgaArmorBoots extends ItemOrgaArmor {
        public ItemOrgaArmorBoots(Properties pProperties) {
            super(Type.BOOTS, pProperties);
        }
    }
}
