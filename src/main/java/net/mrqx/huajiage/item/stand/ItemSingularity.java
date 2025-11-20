package net.mrqx.huajiage.item.stand;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.capability.stand.StandDataCapabilityProvider;
import net.mrqx.huajiage.item.BaseItem;
import net.mrqx.huajiage.registy.HuaJiSoundEvents;
import net.mrqx.huajiage.stand.Stand;
import net.mrqx.huajiage.utils.HuaJiSoundPlayer;
import net.mrqx.huajiage.utils.ItemUtils;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

@Mod.EventBusSubscriber
public class ItemSingularity extends BaseItem {
    public static final String SINGULARITY_COUNT = HuaJiAgeMod.MODID + "." + "singularityCount";
    public static final int SINGULARITY_TIME = 20 * 30;

    public ItemSingularity() {
        super(new Item.Properties().rarity(Rarity.EPIC).fireResistant().stacksTo(1));
    }

    private static int tick = 0;
    private static int curColor = 0;
    private static final String[] SINGULARITY_COLORS = {"§c", "§6", "§e", "§a", "§9", "§b",};

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        int index = 1;
        while (true) {
            String key = this.getDescriptionId() + ".tooltips." + index;
            String translated = Component.translatable(key).getString();
            if (!translated.toLowerCase(Locale.ENGLISH).equals(key)) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < translated.length(); i++) {
                    sb.append(SINGULARITY_COLORS[(curColor + i) % SINGULARITY_COLORS.length]);
                    sb.append(translated.charAt(i));
                }
                tooltip.add(Component.literal(sb.toString()));
                index++;
            } else {
                return;
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        pPlayer.startUsingItem(pUsedHand);
        AtomicBoolean flag = new AtomicBoolean(false);
        pPlayer.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
            Stand stand = Stand.getStand(data.getStand());
            if (stand != null && stand.getMaxLevel() >= data.getLevel() + 1) {
                CompoundTag persistentData = pPlayer.getPersistentData();
                if (!persistentData.contains(SINGULARITY_COUNT, Tag.TAG_INT) || persistentData.getInt(SINGULARITY_COUNT) <= 0) {
                    persistentData.putInt(SINGULARITY_COUNT, SINGULARITY_TIME);
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, SINGULARITY_TIME));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.DARKNESS, SINGULARITY_TIME));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.WITHER, SINGULARITY_TIME, 9));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.POISON, SINGULARITY_TIME, 9));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, SINGULARITY_TIME, 5));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.HUNGER, SINGULARITY_TIME, 9));
                    HuaJiSoundPlayer.playMovingSoundToClient(pPlayer, HuaJiSoundEvents.CHARGE.get());
                    HuaJiSoundPlayer.playMovingSoundToClient(pPlayer, HuaJiSoundEvents.ENERGY_HIT.get());
                    HuaJiSoundPlayer.playMovingSoundToClient(pPlayer, HuaJiSoundEvents.NOISE_FURNACE.get());
                    pPlayer.sendSystemMessage(Component.translatable("message.huajiage.singularity").withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD));
                    flag.set(true);
                }
            }
        });
        return ItemUtils.swingAndShrinkItem(pPlayer, pUsedHand, itemStack, flag);
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onClientTickEvent(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }
        if (++tick >= 2) {
            tick = 0;
            if (--curColor < 0) {
                curColor = SINGULARITY_COLORS.length - 1;
            }
        }
    }
}
