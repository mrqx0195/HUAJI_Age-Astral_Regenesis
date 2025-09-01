package net.mrqx.huajiage.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.PartEntity;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.capability.stand.StandDataCapabilityProvider;
import net.mrqx.huajiage.entity.ExplosionHuaJi;
import net.mrqx.huajiage.stand.Stand;
import net.mrqx.huajiage.stand.StandKillerQueen;
import net.mrqx.huajiage.utils.HuaJiDamageSources;
import net.mrqx.huajiage.utils.ItemTagHelper;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class ItemKillerQueenTrigger extends BaseItem {
    public static final String TRIGGER_LIST_KEY = HuaJiAgeMod.MODID + "." + "killerQueenTrigger" + "." + "list";
    public static final String TRIGGER_ENTITY_UUID_KEY = HuaJiAgeMod.MODID + "." + "killerQueenTrigger" + "." + "entityUuid";
    public static final String TRIGGER_BLOCK_POS_KEY = HuaJiAgeMod.MODID + "." + "killerQueenTrigger" + "." + "blockPos";

    public ItemKillerQueenTrigger() {
        super(new Item.Properties().rarity(Rarity.COMMON).stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        List<Trigger> triggers = getTriggers(stack);
        triggers.forEach(trigger -> {
            if (trigger.targetUuid != null) {
                tooltip.add(Component.translatable("item.huajiage.killer_queen_trigger.tooltips.entity", trigger.targetUuid).withStyle(ChatFormatting.AQUA));
            }
            if (trigger.targetBlock != null) {
                tooltip.add(Component.translatable("item.huajiage.killer_queen_trigger.tooltips.block",
                                Component.translatable("chat.coordinates", trigger.targetBlock.getX(), trigger.targetBlock.getY(), trigger.targetBlock.getZ()).withStyle(ChatFormatting.GREEN))
                        .withStyle(ChatFormatting.AQUA));
            }
        });
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);
        AtomicBoolean flag = new AtomicBoolean(false);
        player.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
            Stand stand = Stand.getStand(data.getStand());
            if (stand instanceof StandKillerQueen) {
                int cost = stand.skillEnergyDemand(player, data) / 10;
                if (data.getEnergy() >= cost) {
                    player.startUsingItem(usedHand);
                    if (level instanceof ServerLevel serverLevel) {
                        List<Trigger> triggers = getTriggers(itemStack);
                        triggers.forEach(trigger -> {
                            Vec3 targetPos = trigger.getTargetPos(serverLevel);
                            if (targetPos != null) {
                                explode(level, player, targetPos, 20 + data.getLevel() * 40, data.getLevel());
                                flag.set(true);
                            }
                        });
                        if (!flag.get()) {
                            player.sendSystemMessage(Component.translatable("message.huajiage.killer_queen_trigger.failed.not_found").withStyle(ChatFormatting.GRAY));
                        } else {
                            data.setEnergy(data.getEnergy() - cost);
                        }
                    }
                } else {
                    player.sendSystemMessage(Component.translatable("message.huajiage.killer_queen_trigger.failed.mp_lack").withStyle(ChatFormatting.GRAY));
                }
            } else {
                player.sendSystemMessage(Component.translatable("message.huajiage.killer_queen_trigger.failed.stand").withStyle(ChatFormatting.GRAY));
            }
        });
        player.swing(usedHand, true);
        if (flag.get()) {
            player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
            if (!player.getAbilities().instabuild) {
                itemStack.shrink(1);
            }
            return InteractionResultHolder.success(itemStack);
        }
        return InteractionResultHolder.consume(itemStack);
    }

    public static List<Trigger> getTriggers(ItemStack itemStack) {
        List<Trigger> triggerList = new ArrayList<>();
        ListTag list = ItemTagHelper.getList(itemStack, TRIGGER_LIST_KEY, ListTag.TAG_COMPOUND, false);
        list.forEach(tag -> {
            if (tag instanceof CompoundTag compoundTag) {
                if (compoundTag.hasUUID(TRIGGER_ENTITY_UUID_KEY)) {
                    triggerList.add(new Trigger().setTarget(compoundTag.getUUID(TRIGGER_ENTITY_UUID_KEY)));
                } else if (compoundTag.contains(TRIGGER_BLOCK_POS_KEY, Tag.TAG_INT_ARRAY)) {
                    int[] posArray = compoundTag.getIntArray(TRIGGER_BLOCK_POS_KEY);
                    triggerList.add(new Trigger().setTarget(new BlockPos(posArray[0], posArray[1], posArray[2])));
                }
            }
        });
        return triggerList;
    }

    public static void setTriggers(ItemStack itemStack, List<Trigger> triggerList) {
        ListTag list = new ListTag();
        triggerList.forEach(trigger -> {
            CompoundTag compoundTag = new CompoundTag();
            if (trigger.targetUuid != null) {
                compoundTag.putUUID(TRIGGER_ENTITY_UUID_KEY, trigger.targetUuid);
            }
            if (trigger.targetBlock != null) {
                compoundTag.putIntArray(TRIGGER_BLOCK_POS_KEY, List.of(trigger.targetBlock.getX(), trigger.targetBlock.getY(), trigger.targetBlock.getZ()));
            }
            if (!compoundTag.isEmpty()) {
                list.add(compoundTag);
            }
        });
        ItemTagHelper.setList(itemStack, TRIGGER_LIST_KEY, list);
    }

    public static void addTrigger(ItemStack itemStack, Trigger trigger, int max) {
        List<Trigger> triggers = getTriggers(itemStack);
        triggers.add(trigger);
        while (triggers.size() > max) {
            triggers.remove(0);
        }
        setTriggers(itemStack, triggers);
    }

    public static void explode(Level level, Entity entity, Vec3 targetPos, double damage, int standLevel) {
        DamageSource damageSource = HuaJiDamageSources.standHit(level, entity, entity, entity.position());
        Explosion explosion = new ExplosionHuaJi(level, entity, damageSource, null, targetPos.x, targetPos.y, targetPos.z,
                3 + standLevel, Explosion.BlockInteraction.KEEP, damage, 0, true, target -> {
            if (standLevel > 0 && target.position().distanceTo(targetPos) <= 2 && target.level().random.nextDouble() < 0.1) {
                killerQueenDeath(target, damageSource);
            }
        });

        explosion.explode();
        explosion.finalizeExplosion(true);
    }

    public static void killerQueenDeath(Entity target, DamageSource damageSource) {
        if (target instanceof LivingEntity living) {
            living.setHealth(1);
            living.invulnerableTime = 0;
            living.hurt(damageSource, living.getMaxHealth());
            living.invulnerableTime = 0;
        } else if (target instanceof PartEntity<?> part) {
            killerQueenDeath(part.getParent(), damageSource);
        } else {
            target.kill();
        }
    }

    public static class Trigger {
        @Nullable
        private UUID targetUuid = null;
        @Nullable
        private BlockPos targetBlock = null;

        public Trigger() {
        }

        public Trigger setTarget(UUID entity) {
            this.targetUuid = entity;
            targetBlock = null;
            return this;
        }

        public Trigger setTarget(BlockPos blockPos) {
            this.targetBlock = blockPos;
            targetUuid = null;
            return this;
        }

        public @Nullable Vec3 getTargetPos(ServerLevel level) {
            if (targetUuid != null) {
                Entity entity = level.getEntity(targetUuid);
                if (entity != null) {
                    return entity.position();
                }
            } else if (targetBlock != null) {
                return targetBlock.getCenter();
            }
            return null;
        }
    }
}
