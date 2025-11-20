package net.mrqx.huajiage.item.equipment.armor;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.client.HuaJiLayers;
import net.mrqx.huajiage.client.model.armor.HuaJiArmorModel;
import net.mrqx.huajiage.config.HuaJiCommonConfig;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.utils.ItemTagHelper;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public class ItemLordLuWing extends ArmorItem {
    public static final String LORD_LU_WING_GUNS_KEY = HuaJiAgeMod.MODID + "." + "lordLuWingGuns";
    public static final String LORD_LU_WING_GUN_COUNTER_KEY = HuaJiAgeMod.MODID + "." + "lordLuWingGunCounter";
    public static final String LORD_LU_WING_SOUND_COUNTER_KEY = HuaJiAgeMod.MODID + "." + "lordLuWingSoundCounter";
    public static final MutableComponent REMOVE_FLAG = Component.literal("huajiage.lordLuWing.removeFlag");

    public ItemLordLuWing() {
        super(HuaJiArmorMaterials.FIFTY_FIFTY, Type.CHESTPLATE, new Item.Properties().rarity(Rarity.EPIC).fireResistant());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(Component.translatable(this.getDescriptionId() + ".tooltips.1").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable(this.getDescriptionId() + ".tooltips.2",
                        Component.translatable(HuaJiItems.FIFTY_FIFTY_HELMET.get().getDescriptionId()))
                .withStyle(ChatFormatting.GRAY));
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.empty());
            tooltip.add(Component.translatable(this.getDescriptionId() + ".tooltips.shift.1",
                            Component.translatable(!HuaJiCommonConfig.LORD_LU_WING_ALL_GUN.get() ? "tacz.gun.sks_tactical.name" : "item.huajiage.tooltips.any_gun"))
                    .withStyle(ChatFormatting.WHITE));
            tooltip.add(Component.translatable(this.getDescriptionId() + ".tooltips.shift.2").withStyle(ChatFormatting.WHITE));

            tooltip.add(Component.empty());
            tooltip.add(Component.translatable(this.getDescriptionId() + ".tooltips.shift.3",
                            Component.translatable(HuaJiItems.FIFTY_FIFTY_HELMET.get().getDescriptionId()))
                    .withStyle(ChatFormatting.WHITE));

            int i = 0;
            for (ItemStack gun : ItemLordLuWing.getGunsFromWing(stack)) {
                if (!gun.isEmpty()) {
                    i++;
                }
            }
            if (i > 0) {
                if (level != null) {
                    int index = Math.toIntExact(level.getGameTime() / 20 % i + 1);
                    tooltip.add(Component.empty());
                    tooltip.add(Component.translatable(this.getDescriptionId() + ".tooltips.shift.4", index, i)
                            .withStyle(ChatFormatting.GOLD));
                    tooltip.add(Component.empty());
                    tooltip.add(ItemLordLuWing.getGunsFromWing(stack).get(index - 1).getHoverName());
                    tooltip.add(Component.empty());
                    tooltip.add(REMOVE_FLAG);
                }
            }
        } else {
            tooltip.add(Component.translatable("item.huajiage.tooltips.shift.1",
                            Minecraft.getInstance().options.keyShift.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.YELLOW, ChatFormatting.ITALIC))
                    .withStyle(ChatFormatting.AQUA));
        }
    }

    @OnlyIn(Dist.CLIENT)
    public Optional<TooltipComponent> getItemComponent(ItemStack stack) {
        if (Screen.hasShiftDown()) {
            int i = 0;
            List<ItemStack> guns = ItemLordLuWing.getGunsFromWing(stack);
            for (ItemStack gun : guns) {
                if (!gun.isEmpty()) {
                    i++;
                }
            }
            if (i > 0) {
                ClientLevel level = Minecraft.getInstance().level;
                if (level != null) {
                    int index = Math.toIntExact(level.getGameTime() / 20 % i + 1);
                    return guns.get(index - 1).getTooltipImage();
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
        if (slot == EquipmentSlot.CHEST) {
            multimap.putAll(super.getAttributeModifiers(slot, stack));
            int i = 0;
            for (ItemStack gun : ItemLordLuWing.getGunsFromWing(stack)) {
                if (!gun.isEmpty()) {
                    i++;
                }
            }
            multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                    UUID.fromString("0098379c-ef9c-471f-8c91-00e4416173bc"),
                    "Lord Lu Wing modifier", i * 0.5, AttributeModifier.Operation.MULTIPLY_BASE));
        }
        return multimap;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        if (entity instanceof LivingEntity living && living.getItemBySlot(EquipmentSlot.CHEST).equals(stack)) {
            if (!living.getItemBySlot(EquipmentSlot.HEAD).is(HuaJiItems.FIFTY_FIFTY_HELMET.get()) || living.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
                Block.popResource(level, entity.getOnPos(), living.getItemBySlot(EquipmentSlot.CHEST).copyAndClear());
            }
        }
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return "huajiage:textures/entity/lord_lu_wing.png";
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                EntityModelSet models = Minecraft.getInstance().getEntityModels();
                ModelPart root = models.bakeLayer(HuaJiLayers.LORD_LU_WING);
                return new HuaJiArmorModel(root) {
                    @Override
                    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
                    }
                };
            }
        });
    }

    public static void addGunToWing(ItemStack wing, ItemStack gun) {
        ListTag listTag = ItemTagHelper.getList(wing, LORD_LU_WING_GUNS_KEY, Tag.TAG_COMPOUND, false);
        listTag.add(gun.serializeNBT());
        if (listTag.size() > 8) {
            listTag.remove(0);
        }
        ItemTagHelper.setList(wing, LORD_LU_WING_GUNS_KEY, listTag);
    }

    public static List<ItemStack> getGunsFromWing(ItemStack wing) {
        ListTag listTag = ItemTagHelper.getList(wing, LORD_LU_WING_GUNS_KEY, Tag.TAG_COMPOUND, false);
        List<ItemStack> list = new ArrayList<>();
        listTag.forEach(tag -> {
            if (tag instanceof CompoundTag compoundTag) {
                list.add(ItemStack.of(compoundTag));
            }
        });
        while (list.size() < 8) {
            list.add(Items.AIR.getDefaultInstance());
        }
        return list;
    }

    public static boolean soundCounterUpdate(ItemStack wing, boolean isTimeStopping) {
        long counter = ItemTagHelper.getLong(wing, LORD_LU_WING_SOUND_COUNTER_KEY, 0);
        boolean flag = false;
        counter += isTimeStopping ? 1 : 5;
        if (counter >= 13 * 5) {
            flag = true;
            counter = 0;
        }
        ItemTagHelper.setLong(wing, LORD_LU_WING_SOUND_COUNTER_KEY, counter);
        return flag;
    }

    public static boolean gunCounterUpdate(ItemStack gun) {
        long counter = ItemTagHelper.getLong(gun, LORD_LU_WING_GUN_COUNTER_KEY, 0);
        boolean flag = true;
        counter += 17;
        if (counter >= 40) {
            flag = false;
            counter -= 40;
        }
        ItemTagHelper.setLong(gun, LORD_LU_WING_GUN_COUNTER_KEY, counter);
        return flag;
    }
}
