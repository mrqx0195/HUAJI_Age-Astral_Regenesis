package net.mrqx.huajiage.compat.slashblade.registy;

import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.event.client.UserPoseOverrider;
import mods.flammpfeil.slashblade.event.handler.FallHandler;
import mods.flammpfeil.slashblade.registry.combo.ComboState;
import mods.flammpfeil.slashblade.slasharts.WaveEdge;
import mods.flammpfeil.slashblade.util.AdvancementHelper;
import mods.flammpfeil.slashblade.util.AttackManager;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.compat.slashblade.specialeffect.SwornKinship;

@SuppressWarnings("unused")
public class HuaJiComboStates {
    public static final DeferredRegister<ComboState> COMBO_STATE = DeferredRegister.create(ComboState.REGISTRY_KEY,
            HuaJiAgeMod.MODID);

    public static final RegistryObject<ComboState> WAVE_SLASH_PRE = COMBO_STATE.register("wave_slash_pre",
            ComboState.Builder.newInstance().startAndEnd(1900, 1923).priority(80)
                    .next(entity -> HuaJiAgeMod.prefix("wave_slash_pre"))
                    .nextOfTimeout(entity -> HuaJiAgeMod.prefix("wave_slash"))
                    .addTickAction((e) -> {
                        long elapsed = ComboState.getElapsed(e);

                        if (elapsed == 0) {
                            e.playSound(SoundEvents.TRIDENT_THROW, 0.80F, 0.625F + 0.1f * e.getRandom().nextFloat());
                            AdvancementHelper.grantCriterion(e, AdvancementHelper.ADVANCEMENT_JUDGEMENT_CUT);
                        }

                        if (elapsed <= 3) {
                            e.moveRelative(-0.3f, new Vec3(0, 0, 1));
                            Vec3 vec = e.getDeltaMovement();
                            {
                                double d0 = vec.x;
                                double d1 = vec.z;

                                while (d0 != 0 && e.level().noCollision(e,
                                        e.getBoundingBox().move(d0, -e.maxUpStep(), 0))) {
                                    if (d0 < 0.05D && d0 >= -0.05D) {
                                        d0 = 0;
                                    } else if (d0 > 0) {
                                        d0 -= 0.05D;
                                    } else {
                                        d0 += 0.05D;
                                    }
                                }

                                while (d1 != 0 && e.level().noCollision(e,
                                        e.getBoundingBox().move(0, -e.maxUpStep(), d1))) {
                                    if (d1 < 0.05D && d1 >= -0.05D) {
                                        d1 = 0;
                                    } else if (d1 > 0) {
                                        d1 -= 0.05D;
                                    } else {
                                        d1 += 0.05D;
                                    }
                                }

                                while (d0 != 0 && d1 != 0 && e.level().noCollision(e,
                                        e.getBoundingBox().move(d0, -e.maxUpStep(), d1))) {
                                    if (d0 < 0.05D && d0 >= -0.05D) {
                                        d0 = 0;
                                    } else if (d0 > 0) {
                                        d0 -= 0.05D;
                                    } else {
                                        d0 += 0.05D;
                                    }

                                    if (d1 < 0.05D && d1 >= -0.05D) {
                                        d1 = 0;
                                    } else if (d1 > 0) {
                                        d1 -= 0.05D;
                                    } else {
                                        d1 += 0.05D;
                                    }
                                }

                                vec = new Vec3(d0, vec.y, d1);
                            }

                            e.move(MoverType.SELF, vec);
                        }
                        e.setDeltaMovement(e.getDeltaMovement().multiply(0, 1, 0));
                    }).addTickAction(FallHandler::fallDecrease)
                    .addTickAction(UserPoseOverrider::resetRot)::build);

    public static final RegistryObject<ComboState> WAVE_SLASH = COMBO_STATE.register("wave_slash",
            ComboState.Builder.newInstance().startAndEnd(2000, 2019).priority(90)
                    .next(living -> AttackManager.isPowered(living)
                            && SwornKinship.isEffective(living)
                            ? HuaJiAgeMod.prefix("wave_slash_quick")
                            : HuaJiAgeMod.prefix("wave_slash"))
                    .nextOfTimeout(entity -> HuaJiAgeMod.prefix("wave_slash_end"))
                    .addHoldAction((living) -> {
                        int elapsed = living.getTicksUsingItem();

                        if (elapsed < 2) {
                            return;
                        }

                        AttributeModifier rapidSlashReach = new AttributeModifier("RapidSlashReach", -3,
                                AttributeModifier.Operation.ADDITION);
                        AttributeInstance attributeInstance = living.getAttribute(ForgeMod.ENTITY_REACH.get());
                        if (attributeInstance != null) {
                            attributeInstance.addTransientModifier(rapidSlashReach);
                        }
                        AttackManager.areaAttack(living, livingEntity -> {
                        }, 0.44f, true, false, true);
                        if (attributeInstance != null) {
                            attributeInstance.removeModifier(rapidSlashReach);
                        }
                    }).addTickAction(living -> {
                        long elapsed = ComboState.getElapsed(living);

                        if (elapsed == 0) {
                            living.level().playSound(null, living.getX(), living.getY(), living.getZ(),
                                    SoundEvents.ARMOR_EQUIP_IRON, SoundSource.PLAYERS, 1.0F, 1.0F);
                        }

                        if (elapsed <= 3 && living.onGround()) {
                            living.moveRelative(living.isInWater() ? 1.25f : 0.8f, new Vec3(0, 0, 1));
                        }

                        Vec3 centerOffset = AttackManager.genRushOffset(living);
                        if (2 <= elapsed && elapsed < 6) {
                            float roll = -45 + 90 * living.getRandom().nextFloat();

                            if (elapsed % 2 == 0) {
                                roll += 180;
                            }

                            boolean critical = AttackManager.isPowered(living);

                            AttackManager.doSlash(living, roll, centerOffset, false, critical, 0.4f);
                            WaveEdge.doSlash(living, roll, 100, Vec3.ZERO, critical, 0.2F, 2f, 2f, 1);
                        }

                        if (elapsed == 7) {
                            AttackManager.doSlash(living, -30, centerOffset, false, true, 0.4f);
                            WaveEdge.doSlash(living, -30, 100, Vec3.ZERO, true, 0.2F, 2f, 2f, 4);
                        }

                        if (7 <= elapsed && elapsed <= 10) {
                            UserPoseOverrider.setRot(living, 90, true);
                        }
                        if (10 < elapsed) {
                            UserPoseOverrider.setRot(living, 0, false);
                        }
                    }).addHitEffect(StunManager::setStun)
                    .releaseAction(ComboState::releaseActionQuickCharge)::build);

    public static final RegistryObject<ComboState> WAVE_SLASH_QUICK = COMBO_STATE.register("wave_slash_quick",
            ComboState.Builder.newInstance().startAndEnd(2000, 2001).priority(70)
                    .next(entity -> HuaJiAgeMod.prefix("wave_slash_quick"))
                    .nextOfTimeout(entity -> HuaJiAgeMod.prefix("wave_slash"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(0, AttackManager::playQuickSheathSoundAction).build())
                    .releaseAction(ComboState::releaseActionQuickCharge)::build);

    public static final RegistryObject<ComboState> WAVE_SLASH_END = COMBO_STATE.register("wave_slash_end",
            ComboState.Builder.newInstance().startAndEnd(2019, 2054).priority(70)
                    .next(entity -> SlashBlade.prefix("none"))
                    .nextOfTimeout(entity -> HuaJiAgeMod.prefix("wave_slash_end2"))
                    .releaseAction(ComboState::releaseActionQuickCharge)::build);

    public static final RegistryObject<ComboState> WAVE_SLASH_END2 = COMBO_STATE.register("wave_slash_end2",
            ComboState.Builder.newInstance().startAndEnd(2054, 2073).priority(70)
                    .next(entity -> SlashBlade.prefix("none"))
                    .nextOfTimeout(entity -> SlashBlade.prefix("none"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(0, AttackManager::playQuickSheathSoundAction).build())
                    .releaseAction(ComboState::releaseActionQuickCharge)::build);
}
