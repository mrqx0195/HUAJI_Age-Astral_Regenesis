package net.mrqx.huajiage.item.stand;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.capability.stand.StandDataCapabilityProvider;
import net.mrqx.huajiage.item.BaseItem;
import net.mrqx.huajiage.registy.HuaJiEffects;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.stand.Stand;
import net.mrqx.huajiage.stand.StandOrgaRequiem;
import net.mrqx.huajiage.utils.ItemTagHelper;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class ItemOrgaRequiem extends BaseItem {
    public static final String ORGA_REQUIEM_OWNER_KEY = HuaJiAgeMod.MODID + "." + "orgaRequiemOwner";

    public static boolean hasValidOrgaRequiem(LivingEntity living) {
        if (living.getCapability(StandDataCapabilityProvider.STAND_DATA)
                .map(data -> Stand.getStand(data.getStand()) instanceof StandOrgaRequiem).orElse(false)) {
            return true;
        }
        if (living instanceof Player player) {
            return player.getInventory().hasAnyMatching(itemStack ->
                    itemStack.is(HuaJiItems.ORGA_REQUIEM.get())
                            && ItemTagHelper.getString(itemStack, ORGA_REQUIEM_OWNER_KEY, "").equals(player.getStringUUID()));
        } else {
            for (ItemStack itemStack : living.getAllSlots()) {
                if (itemStack.is(HuaJiItems.ORGA_REQUIEM.get())
                        && ItemTagHelper.getString(itemStack, ORGA_REQUIEM_OWNER_KEY, "").equals(living.getStringUUID())) {
                    return true;
                }
            }
        }
        return false;
    }

    public ItemOrgaRequiem() {
        super(new Item.Properties().rarity(Rarity.EPIC).fireResistant().stacksTo(1));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        if (!ItemTagHelper.getString(stack, ORGA_REQUIEM_OWNER_KEY, "").isEmpty()) {
            Component component = null;
            if (Minecraft.getInstance().getConnection() != null) {
                PlayerInfo playerInfo = Minecraft.getInstance().getConnection()
                        .getPlayerInfo(UUID.fromString(ItemTagHelper.getString(stack, ORGA_REQUIEM_OWNER_KEY, "")));
                if (playerInfo != null) {
                    component = playerInfo.getTabListDisplayName();
                    if (component == null) {
                        component = Component.literal(playerInfo.getProfile().getName());
                    }
                }
            } else if (Minecraft.getInstance().player != null
                    && UUID.fromString(ItemTagHelper.getString(stack, ORGA_REQUIEM_OWNER_KEY, "")).equals(Minecraft.getInstance().player.getUUID())) {
                component = Minecraft.getInstance().player.getDisplayName();
            }
            if (component != null) {
                tooltip.add(Component.translatable(this.getDescriptionId() + ".tooltips.1", component).withStyle(ChatFormatting.YELLOW));
            }
        }
        List<Component> shiftTooltips = new ArrayList<>();
        int index = 1;
        while (true) {
            String key = this.getDescriptionId() + ".tooltips.shift." + index;
            String translated = Component.translatable(key).getString();
            if (!translated.toLowerCase(Locale.ENGLISH).equals(key)) {
                shiftTooltips.add(Component.translatable(key).withStyle(ChatFormatting.GOLD));
                index++;
            } else {
                break;
            }
        }
        if (!shiftTooltips.isEmpty()) {
            tooltip.add(Component.translatable("item.huajiage.orga_requiem.tooltips.2",
                            Component.translatable("item.huajiage.tooltips.shift.1",
                                            Minecraft.getInstance().options.keyShift.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.YELLOW, ChatFormatting.ITALIC))
                                    .withStyle(ChatFormatting.GRAY))
                    .withStyle(ChatFormatting.GOLD));
            if (Screen.hasShiftDown()) {
                tooltip.addAll(shiftTooltips);
            }
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (ItemTagHelper.getString(stack, ORGA_REQUIEM_OWNER_KEY, "").isEmpty()) {
            ItemTagHelper.setString(stack, ORGA_REQUIEM_OWNER_KEY, entity.getStringUUID());
            entity.sendSystemMessage(Component.translatable("message.huajiage.orga.awake.2"));
        } else if (entity instanceof LivingEntity living) {
            if (!ItemTagHelper.getString(stack, ORGA_REQUIEM_OWNER_KEY, "").equals(entity.getStringUUID())) {
                if (living.tickCount % 60 == 0 && !living.hasEffect(HuaJiEffects.HOPE_FLOWER.get()) && !living.hasEffect(HuaJiEffects.REQUIEM.get())) {
                    living.addEffect(new MobEffectInstance(HuaJiEffects.REQUIEM_TARGET.get(), 60, 0, false, false));
                }
            } else if (living.hasEffect(HuaJiEffects.HOPE_FLOWER.get())) {
                living.removeEffect(HuaJiEffects.HOPE_FLOWER.get());
            }
        }
    }
}
