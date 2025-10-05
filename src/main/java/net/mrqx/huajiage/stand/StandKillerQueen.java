package net.mrqx.huajiage.stand;

import com.mega.endinglib.util.time.TimeStopUtils;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.capability.stand.IStandData;
import net.mrqx.huajiage.capability.stand.StandDataCapabilityProvider;
import net.mrqx.huajiage.client.HuaJiLayers;
import net.mrqx.huajiage.client.model.stand.ModelKillerQueen;
import net.mrqx.huajiage.client.model.stand.ModelKillerQueenPunch;
import net.mrqx.huajiage.client.model.stand.ModelStandBase;
import net.mrqx.huajiage.data.HuaJiDamageTypes;
import net.mrqx.huajiage.entity.EntityRoadRoller;
import net.mrqx.huajiage.entity.EntitySheerHeartAttack;
import net.mrqx.huajiage.item.ItemKillerQueenTrigger;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.registy.HuaJiSoundEvents;
import net.mrqx.huajiage.utils.HuaJiDamageSources;
import net.mrqx.huajiage.utils.HuaJiMathHelper;
import net.mrqx.huajiage.utils.HuaJiSoundPlayer;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Mod.EventBusSubscriber
public class StandKillerQueen extends Stand {
    public static final BiConsumer<LivingEntity, IStandData> KILLER_QUEEN_TICK = (living, data) -> {
        Stand stand = Stand.getStand(data.getStand());
        if (stand != null && !living.level().isClientSide && STATE_PUNCH.equals(data.getState())) {
            living.level().getEntitiesOfClass(Entity.class, living.getBoundingBox().inflate(stand.getDistance(living, data) / 50)).forEach(entity -> {
                if (HuaJiMathHelper.getDegreeXZ(living.getLookAngle(), HuaJiMathHelper.getVectorEntityEye(living, entity)) > 45) {
                    return;
                }
                Vec3 back = HuaJiMathHelper.getVectorEntityEye(living, entity);
                DamageSource damageSource = HuaJiDamageSources.simple(living, HuaJiDamageTypes.STAND_HIT);

                if (entity instanceof EnderDragonPart enderDragonPart) {
                    enderDragonPart.parentMob.hurt(enderDragonPart.parentMob.head, damageSource, stand.getDamage(living, data));
                } else if (entity instanceof LivingEntity target && !target.equals(living)) {
                    boolean isTimeStopping = TimeStopUtils.isTimeStop && TimeStopUtils.andSameDimension(living.level());
                    if (living.level().getGameTime() % 4 == 0 || isTimeStopping) {
                        target.invulnerableTime = 0;
                        target.hurt(damageSource, stand.getDamage(living, data) / 2);
                        target.invulnerableTime = 0;
                        if (!isTimeStopping) {
                            living.level().levelEvent(2001, target.blockPosition().offset(0, (int) (target.getEyePosition(0).y - target.position().y), 0), Block.getId(Blocks.OBSIDIAN.defaultBlockState()));
                        }
                        if (HuaJiMathHelper.getVectorEntityEye(living, target).length() < stand.getDistance(living, data) / 25) {
                            target.setDeltaMovement(back);
                        }
                    }
                } else if (!(entity instanceof ItemEntity || entity instanceof ExperienceOrb)) {
                    if (entity instanceof EntityRoadRoller roadRoller) {
                        roadRoller.hurt(damageSource, stand.getDamage(living, data) / 2);
                    } else {
                        entity.setDeltaMovement(back.scale(stand.getDamage(living, data) / 10));
                    }
                }
            });
        }
    };

    public static final BiConsumer<LivingEntity, IStandData> KILLER_QUEEN_DO_SKILL = (living, data) -> {
        if (living instanceof Player player) {
            EntitySheerHeartAttack sheerHeartAttack = new EntitySheerHeartAttack(living.level());
            sheerHeartAttack.tame(player);
            sheerHeartAttack.setPos(living.getEyePosition());
            sheerHeartAttack.setDeltaMovement(living.getLookAngle().scale(0.5));
            living.level().addFreshEntity(sheerHeartAttack);
            HuaJiSoundPlayer.playMovingSoundToClient(living, HuaJiSoundEvents.STAND_KILLER_QUEEN_TRIGGER.get(), 2);
        } else if (living instanceof OwnableEntity ownable && ownable.getOwner() instanceof Player player) {
            EntitySheerHeartAttack sheerHeartAttack = new EntitySheerHeartAttack(living.level());
            sheerHeartAttack.tame(player);
            sheerHeartAttack.setPos(living.getEyePosition());
            sheerHeartAttack.setDeltaMovement(living.getLookAngle().scale(0.5));
            living.level().addFreshEntity(sheerHeartAttack);
            HuaJiSoundPlayer.playMovingSoundToClient(living, HuaJiSoundEvents.STAND_KILLER_QUEEN_TRIGGER.get(), 2);
        }
    };

    public StandKillerQueen() {
        super(KILLER_QUEEN_TICK, KILLER_QUEEN_DO_SKILL);
    }

    @Override
    public void onTriggered(LivingEntity livingEntity, IStandData data) {
        super.onTriggered(livingEntity, data);
        HuaJiSoundPlayer.playMovingSoundToClient(livingEntity, livingEntity.level().getRandom().nextBoolean() ? HuaJiSoundEvents.STAND_KILLER_QUEEN_SHOW_1.get() : HuaJiSoundEvents.STAND_KILLER_QUEEN_SHOW_2.get());
    }

    @Override
    public int skillEnergyDemand(LivingEntity livingEntity, IStandData data) {
        return data.getLevel() > 0 ? 60000 : -1;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public float getDamage(LivingEntity livingEntity, IStandData data) {
        return 8 * (1 + (float) data.getLevel() / 2);
    }

    @Override
    public float getSpeed(LivingEntity livingEntity, IStandData data) {
        return 0.6F;
    }

    @Override
    public int getDuration(LivingEntity livingEntity, IStandData data) {
        return 300;
    }

    @Override
    public float getDistance(LivingEntity livingEntity, IStandData data) {
        return 100;
    }

    @Override
    public int chargePerTick(LivingEntity livingEntity, IStandData data) {
        return data.isTriggered() && data.getState().equals(STATE_DEFAULT) ? 104 : 80;
    }

    @Override
    public List<String> getStates() {
        return List.of(STATE_DEFAULT, STATE_PUNCH);
    }

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent event) {
        Player player = event.getEntity();
        if (event instanceof PlayerInteractEvent.LeftClickBlock || event instanceof PlayerInteractEvent.RightClickBlock) {
            player.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
                Stand stand = Stand.getStand(data.getStand());
                if (stand instanceof StandKillerQueen && data.isTriggered()) {
                    Inventory inventory = player.getInventory();
                    if (!inventory.hasAnyMatching(itemStack -> itemStack.is(HuaJiItems.KILLER_QUEEN_TRIGGER.get()))) {
                        ItemStack itemStack = HuaJiItems.KILLER_QUEEN_TRIGGER.get().getDefaultInstance();
                        ItemKillerQueenTrigger.addTrigger(itemStack, new ItemKillerQueenTrigger.Trigger().setTarget(event.getPos()), data.getLevel() + 1);
                        player.getInventory().add(itemStack);
                    } else {
                        for (int j = 0; j < inventory.getContainerSize(); ++j) {
                            ItemStack itemStack = inventory.getItem(j);
                            if (itemStack.is(HuaJiItems.KILLER_QUEEN_TRIGGER.get())) {
                                ItemKillerQueenTrigger.addTrigger(itemStack, new ItemKillerQueenTrigger.Trigger().setTarget(event.getPos()), data.getLevel() + 1);
                                break;
                            }
                        }
                    }
                }
            });
        }
    }

    @SubscribeEvent
    public static void onLivingAttackEvent(LivingAttackEvent event) {
        Entity entity = event.getSource().getEntity();
        if (entity != null) {
            entity.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
                Stand stand = Stand.getStand(data.getStand());
                if (stand instanceof StandKillerQueen && data.isTriggered()) {
                    if (entity instanceof LivingEntity living) {
                        if (living instanceof Player player) {
                            Inventory inventory = player.getInventory();
                            if (!inventory.hasAnyMatching(itemStack -> itemStack.is(HuaJiItems.KILLER_QUEEN_TRIGGER.get()))) {
                                ItemStack itemStack = HuaJiItems.KILLER_QUEEN_TRIGGER.get().getDefaultInstance();
                                ItemKillerQueenTrigger.addTrigger(itemStack, new ItemKillerQueenTrigger.Trigger().setTarget(event.getEntity().getUUID()), data.getLevel() + 1);
                                player.getInventory().add(itemStack);
                            } else {
                                for (int j = 0; j < inventory.getContainerSize(); ++j) {
                                    ItemStack itemStack = inventory.getItem(j);
                                    if (itemStack.is(HuaJiItems.KILLER_QUEEN_TRIGGER.get())) {
                                        ItemKillerQueenTrigger.addTrigger(itemStack, new ItemKillerQueenTrigger.Trigger().setTarget(event.getEntity().getUUID()), data.getLevel() + 1);
                                        break;
                                    }
                                }
                            }
                        } else {
                            for (ItemStack itemStack : living.getAllSlots()) {
                                if (itemStack.is(HuaJiItems.KILLER_QUEEN_TRIGGER.get())) {
                                    ItemKillerQueenTrigger.addTrigger(itemStack, new ItemKillerQueenTrigger.Trigger().setTarget(event.getEntity().getUUID()), data.getLevel() + 1);
                                    break;
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static final ModelLayerLocation DEFAULT_LAYER = HuaJiLayers.create("killer_queen", STATE_DEFAULT);

    @OnlyIn(Dist.CLIENT)
    public static final ModelLayerLocation PUNCH_LAYER = HuaJiLayers.create("killer_queen", STATE_PUNCH);

    @OnlyIn(Dist.CLIENT)
    public static final Map<String, ModelLayerLocation> MODEL_LAYER_MAP = Map.of(
            STATE_DEFAULT, DEFAULT_LAYER,
            STATE_PUNCH, PUNCH_LAYER
    );

    @OnlyIn(Dist.CLIENT)
    public static final Map<String, ResourceLocation> TEXTURE_MAP = Map.of(
            STATE_DEFAULT, HuaJiAgeMod.prefix("textures/entity/stand/killer_queen_default.png"),
            STATE_PUNCH, HuaJiAgeMod.prefix("textures/entity/stand/killer_queen_punch.png")
    );

    @OnlyIn(Dist.CLIENT)
    public static final Map<String, List<Double>> TRANSLATION_MAP = Map.of(
            STATE_DEFAULT, List.of(0.9, -0.1, -0.8),
            STATE_PUNCH, List.of(0.0, 0.0, -0.9)
    );

    @OnlyIn(Dist.CLIENT)
    public static final Map<ModelLayerLocation, Supplier<LayerDefinition>> MODEL_MAP = Map.of(
            DEFAULT_LAYER, ModelKillerQueen::createBodyLayer,
            PUNCH_LAYER, ModelKillerQueenPunch::createBodyLayer
    );

    @OnlyIn(Dist.CLIENT)
    public static final Map<ModelLayerLocation, Function<ModelPart, ModelStandBase>> MODEL_FUNCTION_MAP = Map.of(
            DEFAULT_LAYER, ModelKillerQueen::new,
            PUNCH_LAYER, ModelKillerQueenPunch::new
    );

    @Override
    @OnlyIn(Dist.CLIENT)
    public Map<String, ModelLayerLocation> getModelLocations() {
        return MODEL_LAYER_MAP;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public Map<String, ResourceLocation> getModelTextures() {
        return TEXTURE_MAP;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public Map<String, List<Double>> getModelTranslations() {
        return TRANSLATION_MAP;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public Map<ModelLayerLocation, Supplier<LayerDefinition>> getModels() {
        return MODEL_MAP;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public Map<ModelLayerLocation, Function<ModelPart, ModelStandBase>> getModelFunction() {
        return MODEL_FUNCTION_MAP;
    }

    @Override
    public boolean shouldRenderHand(LivingEntity livingEntity, IStandData data) {
        return !(data.isTriggered() && data.getState().equals(STATE_PUNCH));
    }
}
