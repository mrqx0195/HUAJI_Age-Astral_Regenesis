package net.mrqx.huajiage.item.equipment.armor;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.network.chat.Component;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.client.HuaJiKeyMappings;
import net.mrqx.huajiage.client.HuaJiLayers;
import net.mrqx.huajiage.client.model.armor.ModelFiftyFiftyHelmet;
import net.mrqx.huajiage.event.HuaJiCanFlyEvent;
import net.mrqx.huajiage.event.KeyInputEvent;
import net.mrqx.huajiage.network.HuaJiKeyMessage;
import net.mrqx.huajiage.registy.HuaJiEffects;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.utils.HuaJiDamageSources;
import net.mrqx.huajiage.utils.ItemTagHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

@Mod.EventBusSubscriber
public class ItemFiftyFiftyHelmet extends ArmorItem {
    public static final String FIFTY_FIFTY_ACTIVE_KEY = HuaJiAgeMod.MODID + "." + "fiftyFiftyActive";
    public static final String FIFTY_FIFTY_LORD_KEY = HuaJiAgeMod.MODID + "." + "fiftyFiftyLord";
    public static final String FIFTY_FIFTY_MODE_KEY = "50_50_mode";

    public static boolean isFiftyFiftyActive(LivingEntity livingEntity) {
        return hasFiftyFiftyHelmet(livingEntity) && ItemTagHelper.getBoolean(livingEntity.getItemBySlot(EquipmentSlot.HEAD), ItemFiftyFiftyHelmet.FIFTY_FIFTY_ACTIVE_KEY, false);
    }

    public static void setFiftyFiftyActive(LivingEntity livingEntity, boolean isActive) {
        ItemTagHelper.setBoolean(livingEntity.getItemBySlot(EquipmentSlot.HEAD), ItemFiftyFiftyHelmet.FIFTY_FIFTY_ACTIVE_KEY, isActive);
    }

    public static boolean isFiftyFiftyLord(LivingEntity livingEntity) {
        return hasFiftyFiftyHelmet(livingEntity) && ItemTagHelper.getBoolean(livingEntity.getItemBySlot(EquipmentSlot.HEAD), ItemFiftyFiftyHelmet.FIFTY_FIFTY_LORD_KEY, false);
    }

    public static void setFiftyFiftyLord(LivingEntity livingEntity, boolean isLord) {
        ItemTagHelper.setBoolean(livingEntity.getItemBySlot(EquipmentSlot.HEAD), ItemFiftyFiftyHelmet.FIFTY_FIFTY_LORD_KEY, isLord);
    }

    public static boolean hasFiftyFiftyHelmet(LivingEntity livingEntity) {
        return livingEntity.getItemBySlot(EquipmentSlot.HEAD).is(HuaJiItems.FIFTY_FIFTY_HELMET.get());
    }

    public ItemFiftyFiftyHelmet() {
        super(HuaJiArmorMaterials.FIFTY_FIFTY, Type.HELMET, new Item.Properties().rarity(Rarity.EPIC).fireResistant());
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return "huajiage:textures/models/armor/50_50_helmet.png";
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable(this.getDescriptionId() + ".tooltips.1").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("item.huajiage.tooltips.shift.1",
                        Minecraft.getInstance().options.keyShift.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.YELLOW, ChatFormatting.ITALIC))
                .withStyle(ChatFormatting.DARK_RED));
        if (Screen.hasShiftDown()) {
            if (Minecraft.getInstance().player != null) {
                if (ItemTagHelper.getBoolean(stack, ItemFiftyFiftyHelmet.FIFTY_FIFTY_LORD_KEY, false)) {
                    tooltip.add(Component.translatable("message.huajiage.prefix", Component.translatable(this.getDescriptionId() + ".tooltips.shift.3")
                            .withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.BOLD)));
                } else if (ItemTagHelper.getBoolean(stack, ItemFiftyFiftyHelmet.FIFTY_FIFTY_ACTIVE_KEY, false)) {
                    tooltip.add(Component.translatable("message.huajiage.prefix", Component.translatable(this.getDescriptionId() + ".tooltips.shift.2",
                                    Component.translatable(HuaJiItems.LORD_KEY.get().getDescriptionId()).withStyle(ChatFormatting.DARK_GREEN))
                            .withStyle(ChatFormatting.DARK_RED)));
                } else {
                    tooltip.add(Component.translatable("message.huajiage.prefix", Component.translatable(this.getDescriptionId() + ".tooltips.shift.1",
                                    Component.translatable(HuaJiItems.LORD_CORE.get().getDescriptionId()).withStyle(ChatFormatting.GREEN))
                            .withStyle(ChatFormatting.RED)));
                }
            }
        }
        tooltip.add(Component.translatable(this.getDescriptionId() + ".tooltips.2",
                HuaJiKeyMappings.KEY_CHANGE_MODE.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.YELLOW, ChatFormatting.ITALIC)
        ).withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable(this.getDescriptionId() + ".tooltips.mode", Mode.getMode(stack).equals(Mode.ON)
                ? Component.translatable(this.getDescriptionId() + ".tooltips.mode.on").withStyle(ChatFormatting.GOLD)
                : Component.translatable(this.getDescriptionId() + ".tooltips.mode.off").withStyle(ChatFormatting.GRAY)
        ).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
        if (!pLevel.isClientSide
                && pEntity instanceof Player player
                && player.getInventory().armor.get(EquipmentSlot.HEAD.getIndex()).equals(pStack)
                && Mode.getMode(pStack).equals(Mode.ON)) {
            if (ItemFiftyFiftyHelmet.isFiftyFiftyActive(player)) {
                boolean isLord = ItemFiftyFiftyHelmet.isFiftyFiftyLord(player);
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 300, 0, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 50, 0, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 50, 0, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 50, isLord ? 5 : 2, false, false));
                if (isLord) {
                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 50, 5, false, false));
                    player.addEffect(new MobEffectInstance(HuaJiEffects.FIVE.get(), 50, 0, false, false));
                }
            } else {
                Mode.changeMode(pStack);
            }
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        if (Mode.getMode(stack).equals(Mode.ON)) {
            Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
            if (slot == EquipmentSlot.HEAD) {
                multimap.putAll(super.getAttributeModifiers(slot, stack));
                multimap.put(Attributes.MAX_HEALTH, new AttributeModifier(
                        UUID.fromString("ebb91868-6aed-11e9-a923-1681be663d3e"),
                        "Fifty Fifty Helmet modifier", 5, AttributeModifier.Operation.MULTIPLY_BASE));
                multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                        UUID.fromString("05fd4064-6aee-11e9-a923-1681be663d3e"),
                        "Fifty Fifty Helmet modifier", 5, AttributeModifier.Operation.MULTIPLY_BASE));
                multimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(
                        UUID.fromString("1cb6e486-6aee-11e9-a923-1681be663d3e"),
                        "Fifty Fifty Helmet modifier", 5, AttributeModifier.Operation.MULTIPLY_BASE));
                multimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(
                        UUID.fromString("1cb6e710-6aee-11e9-a923-1681be663d3e"),
                        "Fifty Fifty Helmet modifier", 5, AttributeModifier.Operation.MULTIPLY_BASE));
            }
            return multimap;
        } else {
            return super.getAttributeModifiers(slot, stack);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                EntityModelSet models = Minecraft.getInstance().getEntityModels();
                ModelPart root = models.bakeLayer(HuaJiLayers.FIFTY_FIFTY_HELMET);
                return new ModelFiftyFiftyHelmet(root, itemStack);
            }
        });
    }

    @SubscribeEvent
    public static void onChangeModeEvent(KeyInputEvent event) {
        if (!event.oldCommand.contains(HuaJiKeyMessage.Keys.CHANGE_MODE) && event.currentCommand.contains(HuaJiKeyMessage.Keys.CHANGE_MODE)) {
            if (isFiftyFiftyActive(event.getEntity())) {
                ItemStack itemStack = event.getEntity().getItemBySlot(EquipmentSlot.HEAD);
                Mode.changeMode(itemStack);
                if (Mode.getMode(itemStack).equals(Mode.ON)) {
                    event.getEntity().sendSystemMessage(Component.translatable("message.huajiage.50_50_helmet.open", event.getEntity().getDisplayName())
                            .withStyle(ChatFormatting.YELLOW, ChatFormatting.BOLD));
                }
            } else {
                event.getEntity().sendSystemMessage(Component.translatable("message.huajiage.50_50_helmet.failed", event.getEntity().getDisplayName())
                        .withStyle(ChatFormatting.DARK_RED));
            }
        }
    }

    @SubscribeEvent
    public static void onLivingHurtEvent(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player && isFiftyFiftyLord(player)) {
            if (event.getSource().getEntity() != null) {
                event.getSource().getEntity().hurt(HuaJiDamageSources.five(event.getSource().getEntity().level(), event.getSource()), event.getAmount() * 0.5F);
            }
            event.setAmount(event.getAmount() * 0.5F);
        }
    }

    @SubscribeEvent
    public static void onHuaJiCanFlyEvent(HuaJiCanFlyEvent event) {
        if (ItemFiftyFiftyHelmet.isFiftyFiftyLord(event.getEntity())) {
            event.setCanFly(true);
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
            if (ItemTagHelper.getBoolean(itemStack, FIFTY_FIFTY_MODE_KEY, false)) {
                return ON;
            }
            return OFF;
        }

        @CanIgnoreReturnValue
        public static Mode changeMode(ItemStack itemStack) {
            if (getMode(itemStack) == Mode.OFF) {
                ItemTagHelper.setBoolean(itemStack, FIFTY_FIFTY_MODE_KEY, true);
                return ON;
            }
            ItemTagHelper.setBoolean(itemStack, FIFTY_FIFTY_MODE_KEY, false);
            return OFF;
        }
    }
}
