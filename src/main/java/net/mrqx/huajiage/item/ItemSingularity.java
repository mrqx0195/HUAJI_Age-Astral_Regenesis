package net.mrqx.huajiage.item;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.capability.stand.StandDataCapabilityProvider;
import net.mrqx.huajiage.stand.AbstractStand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

@Mod.EventBusSubscriber
public class ItemSingularity extends BaseItem {
    public static final String SINGULARITY_COUNT = "huajiage.singularityCount";

    public ItemSingularity(Properties properties) {
        super(properties);
    }

    private static int tick = 0;
    private static int curColor = 0;
    private static final String[] SINGULARITY_COLORS = {"§c", "§6", "§e", "§a", "§9", "§b",};

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
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
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        pPlayer.startUsingItem(pUsedHand);
        AtomicBoolean flag = new AtomicBoolean(false);
        pPlayer.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
            AbstractStand stand = AbstractStand.getStand(data.getStand());
            if (stand != null && stand.getMaxLevel() >= data.getLevel() + 1) {
                CompoundTag persistentData = pPlayer.getPersistentData();
                if (!persistentData.contains(SINGULARITY_COUNT, Tag.TAG_INT) || persistentData.getInt(SINGULARITY_COUNT) <= 0) {
                    persistentData.putInt(SINGULARITY_COUNT, 200);
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.WITHER, 200, 9));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 9));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 5));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.HUNGER, 200, 9));
                    pPlayer.sendSystemMessage(Component.translatable("message.huajiage.singularity").withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD));
                    flag.set(true);
                }
            }
        });
        if (flag.get()) {
            itemStack.shrink(1);
        }
        pPlayer.swing(pUsedHand, true);
        return InteractionResultHolder.success(itemStack);
    }

    @SubscribeEvent
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
