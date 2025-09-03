package net.mrqx.huajiage.item.stand;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.mrqx.huajiage.capability.stand.StandDataCapabilityProvider;
import net.mrqx.huajiage.item.BaseItem;
import net.mrqx.huajiage.item.equipment.armor.ItemOrgaArmor;
import net.mrqx.huajiage.registy.HuaJiEffects;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.registy.HuaJiSoundEvents;
import net.mrqx.huajiage.registy.HuaJiStands;
import net.mrqx.huajiage.stand.Stand;
import net.mrqx.huajiage.utils.AdvancementHelper;
import net.mrqx.huajiage.utils.HuaJiSoundPlayer;

public class ItemArrowRequiem extends BaseItem {
    public ItemArrowRequiem() {
        super(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        pPlayer.startUsingItem(pUsedHand);
        if (!pPlayer.level().isClientSide && ItemOrgaArmor.hasAllOrgaArmor(pPlayer)) {
            pPlayer.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
                if (pPlayer.getInventory().hasAnyMatching(stack -> stack.is(HuaJiItems.ORGA_REQUIEM.get()))) {
                    Stand stand = Stand.getStand(data.getStand());
                    if (stand == null) {
                        Stand stand2 = HuaJiStands.ORGA_REQUIEM.get();
                        data.setStand(stand2);
                        data.setLevel(0);
                        data.setMaxEnergy(stand2.getMaxEnergy(pPlayer, data));
                        AdvancementHelper.grantCriterion(pPlayer, AdvancementHelper.ORGA_STAND);
                        pPlayer.sendSystemMessage(Component.translatable("message.huajiage.stand.gain", Component.translatable(stand2.getDescriptionId())).withStyle(ChatFormatting.AQUA, ChatFormatting.BOLD));
                        pPlayer.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
                        if (!pPlayer.getAbilities().instabuild) {
                            itemStack.shrink(1);
                        }
                    } else {
                        pPlayer.sendSystemMessage(Component.translatable("message.huajiage.tarot.stand.failed").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
                    }
                } else if (pPlayer.hasEffect(HuaJiEffects.HOPE_FLOWER.get())) {
                    pPlayer.removeEffect(HuaJiEffects.HOPE_FLOWER.get());
                    HuaJiSoundPlayer.playMovingSoundToClient(pPlayer, SoundEvents.EMPTY);
                    data.getScheduler().schedule("orgaRequiemMusic", 1, (living, manager, gameTime) -> HuaJiSoundPlayer.playMovingSoundToClient(pPlayer, HuaJiSoundEvents.ORGA_REQUIEM_1.get()));
                    pPlayer.sendSystemMessage(Component.translatable("message.huajiage.orga.awake.1"));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 6));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 100));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 7));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 6));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 2));
                    AdvancementHelper.grantCriterion(pPlayer, AdvancementHelper.ARROW_REQUIEM);
                    data.getScheduler().schedule("orgaRequiem", 100, (living, manager, gameTime) -> pPlayer.getInventory().add(HuaJiItems.ORGA_REQUIEM.get().getDefaultInstance()));
                    for (ItemStack stack : pPlayer.getArmorSlots()) {
                        if (stack.getItem() instanceof ItemOrgaArmor) {
                            stack.hurtAndBreak(Integer.MAX_VALUE, pPlayer, player -> player.broadcastBreakEvent(EquipmentSlot.CHEST));
                        }
                    }
                    pPlayer.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
                    if (!pPlayer.getAbilities().instabuild) {
                        itemStack.shrink(1);
                    }
                }
            });
        }
        return InteractionResultHolder.consume(itemStack);
    }
}
