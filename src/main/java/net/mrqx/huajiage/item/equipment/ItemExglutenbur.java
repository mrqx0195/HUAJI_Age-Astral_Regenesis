package net.mrqx.huajiage.item.equipment;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.init.HuaJiTiers;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.registy.HuaJiSoundEvents;
import net.mrqx.huajiage.utils.HuaJiDamageSources;
import net.mrqx.huajiage.utils.HuaJiMathHelper;
import net.mrqx.huajiage.utils.ItemTagHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class ItemExglutenbur extends SwordItem {
    public ItemExglutenbur(Properties pProperties) {
        super(HuaJiTiers.EXGLUTENBUR.get(), 3, -2.4F, pProperties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        tooltip.add(Component.translatable(this.getDescriptionId() + ".tooltips.1",
                Minecraft.getInstance().options.keyShift.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.YELLOW, ChatFormatting.ITALIC),
                Minecraft.getInstance().options.keyUse.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.YELLOW, ChatFormatting.ITALIC)
        ).withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD));
        switch (Flavor.getFlavor(stack)) {
            case FRAGRANT -> tooltip.add(Component.translatable(this.getDescriptionId() + ".tooltips.2",
                    Component.translatable(this.getDescriptionId() + ".tooltips.flavor.1").withStyle(ChatFormatting.GOLD)));
            case SPICY -> tooltip.add(Component.translatable(this.getDescriptionId() + ".tooltips.2",
                    Component.translatable(this.getDescriptionId() + ".tooltips.flavor.2").withStyle(ChatFormatting.RED)));
            case LIME -> tooltip.add(Component.translatable(this.getDescriptionId() + ".tooltips.2",
                    Component.translatable(this.getDescriptionId() + ".tooltips.flavor.3").withStyle(ChatFormatting.GRAY, ChatFormatting.BOLD)));
            default -> tooltip.add(Component.translatable(this.getDescriptionId() + ".tooltips.2",
                    Component.translatable(this.getDescriptionId() + ".tooltips.flavor.0")));
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
        if (slot == EquipmentSlot.MAINHAND) {
            multimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier",
                    Flavor.getFlavor(stack).equals(Flavor.FRAGRANT) ? -2.4 + 0.7F : -2.4, AttributeModifier.Operation.ADDITION));
            switch (Flavor.getFlavor(stack)) {
                case FRAGRANT ->
                        multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier",
                                15 + getDamage(), AttributeModifier.Operation.ADDITION));
                case SPICY ->
                        multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier",
                                1 + getDamage(), AttributeModifier.Operation.ADDITION));
                case LIME ->
                        multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier",
                                10 + getDamage(), AttributeModifier.Operation.ADDITION));
                default ->
                        multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier",
                                getDamage(), AttributeModifier.Operation.ADDITION));
            }
        } else {
            return super.getAttributeModifiers(slot, stack);
        }
        return multimap;
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack pStack, @NotNull LivingEntity pTarget, @NotNull LivingEntity pAttacker) {
        switch (Flavor.getFlavor(pStack)) {
            case FRAGRANT -> {
                pTarget.addEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 3));
                if (pAttacker instanceof Player player) {
                    FoodData foodData = player.getFoodData();
                    foodData.setFoodLevel(foodData.getFoodLevel() + 1);
                    foodData.setSaturation(foodData.getSaturationLevel() + 10);
                }
                pTarget.setDeltaMovement(pTarget.getDeltaMovement().x, 1, pTarget.getDeltaMovement().z);
                pStack.hurtAndBreak(8, pAttacker, living -> living.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            }
            case SPICY -> {
                pTarget.setSecondsOnFire(5);
                pTarget.playSound(SoundEvents.FIREWORK_ROCKET_LARGE_BLAST, 1f, 1f);
                List<LivingEntity> entities = pTarget.level().getEntitiesOfClass(LivingEntity.class, pTarget.getBoundingBox().inflate(2));
                for (LivingEntity entity : entities) {
                    if (!entity.equals(pAttacker) && !entity.equals(pTarget)) {
                        entity.hurt(entity.level().damageSources().inFire(), 5f);
                        entity.setSecondsOnFire(3);
                    }
                }
                pTarget.level().levelEvent(2007, pTarget.blockPosition(), 0xFF4500);
                pStack.hurtAndBreak(1, pAttacker, living -> living.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            }
            case LIME -> {
                pTarget.playSound(SoundEvents.STONE_BREAK, 1f, 1f);
                pTarget.playSound(SoundEvents.FIRE_EXTINGUISH, 1f, 1f);
                pTarget.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 2));
                pTarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 9));
                pTarget.level().levelEvent(2001, pTarget.blockPosition(), Block.getId(Blocks.OBSIDIAN.defaultBlockState()));
                boolean flag2 = false;
                if (pAttacker instanceof Player player) {
                    flag2 = (player.getAttackStrengthScale(0.5F) > 0.9F)
                            && player.fallDistance > 0.0F && !player.onGround()
                            && !player.onClimbable() && !player.isInWater()
                            && !player.hasEffect(MobEffects.BLINDNESS) && !player.isPassenger()
                            && pTarget instanceof LivingEntity;
                    flag2 = flag2 && !player.isSprinting();
                    net.minecraftforge.event.entity.player.CriticalHitEvent hitResult = net.minecraftforge.common.ForgeHooks.getCriticalHit(player, pTarget, flag2, flag2 ? 1.5F : 1.0F);
                    flag2 = hitResult != null;
                }
                if (flag2 || pTarget.level().getRandom().nextDouble() < 0.3d) {
                    pAttacker.heal(10f);
                    pStack.hurtAndBreak(3 * 29, pAttacker, living -> living.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                    pTarget.hurt(HuaJiDamageSources.keDaiJinLa(pTarget.level(), pAttacker, null, pAttacker.position()), 50f);
                    pTarget.playSound(HuaJiSoundEvents.EXGLUTENBUR_HIT.get(), 1f, 1f);
                    pAttacker.sendSystemMessage(Component.translatable("message.huajiage.exglutenbur.kdjl").withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.BOLD));
                    List<LivingEntity> entities = pTarget.level().getEntitiesOfClass(LivingEntity.class, pTarget.getBoundingBox().inflate(5));
                    for (LivingEntity entity : entities) {
                        if (entity != pAttacker && entity != pTarget) {
                            Vec3 vec = HuaJiMathHelper.getVectorEntity(pTarget, entity);
                            if (vec.length() != 0) {
                                entity.hurt(HuaJiDamageSources.keDaiJinLa(pTarget.level(), pAttacker, null, pAttacker.position()), (float) (20f / vec.length()));
                                entity.setDeltaMovement(entity.getDeltaMovement().add(-vec.x / vec.length(), -vec.y / vec.length(), -vec.z / vec.length()));
                            }
                        }
                    }
                }
                pAttacker.heal(1);
                pStack.hurtAndBreak(12, pAttacker, living -> living.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            }
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pEntity instanceof LivingEntity living && living.getMainHandItem().equals(pStack)) {
            switch (Flavor.getFlavor(pStack)) {
                case FRAGRANT -> {
                    if (!pLevel.isClientSide) {
                        living.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 60, 0));
                        living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 60, 2));
                        living.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 2));
                    }
                }
                case LIME -> {
                    if (!pLevel.isClientSide) {
                        living.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 1));
                        living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60, 4));
                    }
                }
            }
        }
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent event) {
        if (!event.getEntity().getMainHandItem().is(HuaJiItems.EXGLUTENBUR.get())
                || !event.getEntity().isShiftKeyDown()
                || event.getEntity().getCooldowns().isOnCooldown(HuaJiItems.EXGLUTENBUR.get())) {
            return;
        }
        Player player = event.getEntity();
        if (event instanceof PlayerInteractEvent.RightClickItem
                || event instanceof PlayerInteractEvent.RightClickBlock
                || event instanceof PlayerInteractEvent.EntityInteractSpecific) {
            Flavor.nextFlavor(event.getEntity().getMainHandItem());
            player.getCooldowns().addCooldown(HuaJiItems.EXGLUTENBUR.get(), 10);
            if (!player.level().isClientSide) {
                switch (Flavor.getFlavor(event.getEntity().getMainHandItem())) {
                    case FRAGRANT -> {
                        player.level().playSound(player, player.blockPosition(), HuaJiSoundEvents.EXGLUTENBUR_1.get(), SoundSource.PLAYERS);
                        player.sendSystemMessage(Component.translatable("message.huajiage.exglutenbur.flavor.1").withStyle(ChatFormatting.GOLD));
                    }
                    case SPICY -> {
                        player.level().playSound(player, player.blockPosition(), HuaJiSoundEvents.EXGLUTENBUR_2.get(), SoundSource.PLAYERS);
                        player.sendSystemMessage(Component.translatable("message.huajiage.exglutenbur.flavor.2").withStyle(ChatFormatting.RED));
                    }
                    case LIME -> {
                        player.level().playSound(player, player.blockPosition(), HuaJiSoundEvents.EXGLUTENBUR_3.get(), SoundSource.PLAYERS);
                        player.sendSystemMessage(Component.translatable("message.huajiage.exglutenbur.flavor.3").withStyle(ChatFormatting.GRAY, ChatFormatting.BOLD));
                    }
                    default -> {
                    }
                }
            }
        }
    }

    public enum Flavor {
        BASE, FRAGRANT, SPICY, LIME;

        public static Flavor getFlavor(ItemStack itemStack) {
            switch (ItemTagHelper.getInt(itemStack, "exglutenbur_flavor", 0)) {
                case 1 -> {
                    return FRAGRANT;
                }
                case 2 -> {
                    return SPICY;
                }
                case 3 -> {
                    return LIME;
                }
                default -> {
                    return BASE;
                }
            }
        }

        public static Flavor nextFlavor(ItemStack itemStack) {
            switch (getFlavor(itemStack)) {
                case FRAGRANT -> {
                    ItemTagHelper.setInt(itemStack, "exglutenbur_flavor", 2);
                    return FRAGRANT;
                }
                case SPICY -> {
                    ItemTagHelper.setInt(itemStack, "exglutenbur_flavor", 3);
                    return SPICY;
                }
                case LIME -> {
                    ItemTagHelper.setInt(itemStack, "exglutenbur_flavor", 0);
                    return LIME;
                }
                default -> {
                    ItemTagHelper.setInt(itemStack, "exglutenbur_flavor", 1);
                    return BASE;
                }
            }
        }
    }
}
