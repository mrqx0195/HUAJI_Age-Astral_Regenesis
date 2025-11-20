package net.mrqx.huajiage.entity;

import com.google.common.collect.Sets;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import net.minecraft.world.level.EntityBasedExplosionDamageCalculator;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

public class ExplosionHuaJi extends Explosion {
    private static final ExplosionDamageCalculator EXPLOSION_DAMAGE_CALCULATOR = new ExplosionDamageCalculator();
    private final Level level;
    private final double x;
    private final double y;
    private final double z;
    @Nullable
    private final Entity source;
    private final float radius;
    private final double baseDamage;
    private final double knockback;
    private final ExplosionDamageCalculator damageCalculator;
    private final boolean damageDecrease;
    private final Consumer<Entity> onHitEntity;

    public ExplosionHuaJi(Level pLevel, @Nullable Entity pSource,
                          @Nullable DamageSource pDamageSource, @Nullable ExplosionDamageCalculator pDamageCalculator,
                          double pToBlowX, double pToBlowY, double pToBlowZ, float pRadius,
                          BlockInteraction pBlockInteraction, double baseDamage, double knockback, boolean damageDecrease, Consumer<Entity> onHitEntity) {
        super(pLevel, pSource, pDamageSource, pDamageCalculator, pToBlowX, pToBlowY, pToBlowZ, pRadius, false, pBlockInteraction);
        this.level = pLevel;
        this.source = pSource;
        this.radius = pRadius;
        this.x = pToBlowX;
        this.y = pToBlowY;
        this.z = pToBlowZ;
        this.baseDamage = baseDamage;
        this.knockback = knockback;
        this.damageDecrease = damageDecrease;
        this.damageCalculator = pDamageCalculator == null ? (pSource == null ? EXPLOSION_DAMAGE_CALCULATOR : new EntityBasedExplosionDamageCalculator(pSource)) : pDamageCalculator;
        this.onHitEntity = onHitEntity;
    }

    public ExplosionHuaJi(Level pLevel, @Nullable Entity pSource,
                          @Nullable DamageSource pDamageSource, @Nullable ExplosionDamageCalculator pDamageCalculator,
                          double pToBlowX, double pToBlowY, double pToBlowZ, float pRadius,
                          BlockInteraction pBlockInteraction, double baseDamage, double knockback, boolean damageDecrease) {
        this(pLevel, pSource, pDamageSource, pDamageCalculator, pToBlowX, pToBlowY, pToBlowZ, pRadius, pBlockInteraction, baseDamage, knockback, damageDecrease, entity -> {
        });
    }

    @Override
    public void explode() {
        this.level.gameEvent(this.source, GameEvent.EXPLODE, new Vec3(this.x, this.y, this.z));
        Set<BlockPos> set = Sets.newHashSet();
        for (int j = 0; j < 16; ++j) {
            for (int k = 0; k < 16; ++k) {
                for (int l = 0; l < 16; ++l) {
                    if (j == 0 || j == 15 || k == 0 || k == 15 || l == 0 || l == 15) {
                        double d0 = (float) j / 15.0F * 2.0F - 1.0F;
                        double d1 = (float) k / 15.0F * 2.0F - 1.0F;
                        double d2 = (float) l / 15.0F * 2.0F - 1.0F;
                        double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                        d0 /= d3;
                        d1 /= d3;
                        d2 /= d3;
                        float f = this.radius * (0.7F + this.level.random.nextFloat() * 0.6F);
                        double d4 = this.x;
                        double d6 = this.y;
                        double d8 = this.z;

                        for (; f > 0; f -= 0.225F) {
                            BlockPos blockpos = BlockPos.containing(d4, d6, d8);
                            BlockState blockstate = this.level.getBlockState(blockpos);
                            FluidState fluidstate = this.level.getFluidState(blockpos);
                            if (!this.level.isInWorldBounds(blockpos)) {
                                break;
                            }

                            Optional<Float> optional = this.damageCalculator.getBlockExplosionResistance(this, this.level, blockpos, blockstate, fluidstate);
                            if (optional.isPresent()) {
                                f -= (optional.get() + 0.3F) * 0.3F;
                            }

                            if (f > 0.0F && this.damageCalculator.shouldBlockExplode(this, this.level, blockpos, blockstate, f)) {
                                set.add(blockpos);
                            }

                            d4 += d0 * (double) 0.3F;
                            d6 += d1 * (double) 0.3F;
                            d8 += d2 * (double) 0.3F;
                        }
                    }
                }
            }
        }

        this.getToBlow().addAll(set);
        float f2 = this.radius * 2.0F;
        int k1 = Mth.floor(this.x - (double) f2 - 1.0D);
        int l1 = Mth.floor(this.x + (double) f2 + 1.0D);
        int i2 = Mth.floor(this.y - (double) f2 - 1.0D);
        int i1 = Mth.floor(this.y + (double) f2 + 1.0D);
        int j2 = Mth.floor(this.z - (double) f2 - 1.0D);
        int j1 = Mth.floor(this.z + (double) f2 + 1.0D);
        List<Entity> list = this.level.getEntities(this.source, new AABB(k1, i2, j2, l1, i1, j1));
        net.minecraftforge.event.ForgeEventFactory.onExplosionDetonate(this.level, this, list, f2);
        Vec3 vec3 = new Vec3(this.x, this.y, this.z);

        for (Entity entity : list) {
            double d12 = Math.sqrt(entity.distanceToSqr(vec3)) / (double) f2;
            if (d12 <= 1.0D) {
                double d5 = entity.getX() - this.x;
                double d7 = (entity instanceof PrimedTnt ? entity.getY() : entity.getEyeY()) - this.y;
                double d9 = entity.getZ() - this.z;
                double d13 = Math.sqrt(d5 * d5 + d7 * d7 + d9 * d9);
                if (d13 != 0) {
                    d5 /= d13;
                    d7 /= d13;
                    d9 /= d13;

                    if (!(entity instanceof ItemEntity || entity instanceof ExperienceOrb)) {
                        double d10 = Math.min(1 - d12 + 0.2, 1);
                        entity.hurt(this.getDamageSource(), damageDecrease ? (float) ((d10 * d10 + d10) / 2 * baseDamage) : (float) baseDamage);
                    }

                    double d11;
                    if (entity instanceof LivingEntity livingentity) {
                        d11 = ProtectionEnchantment.getExplosionKnockbackAfterDampener(livingentity, knockback);
                    } else {
                        d11 = knockback;
                    }

                    d5 *= d11;
                    d7 *= d11;
                    d9 *= d11;
                    Vec3 vec31 = new Vec3(d5, d7, d9);
                    entity.setDeltaMovement(entity.getDeltaMovement().add(vec31));
                    if (entity instanceof Player player) {
                        if (!player.isSpectator() && !player.getAbilities().flying) {
                            this.getHitPlayers().put(player, vec31);
                        }
                    }
                    this.onHitEntity.accept(entity);
                }
            }
        }
    }

    @Override
    public void finalizeExplosion(boolean pSpawnParticles) {
        if (pSpawnParticles && this.level instanceof ServerLevel serverLevel) {
            if (this.radius >= 2.0F) {
                serverLevel.sendParticles(ParticleTypes.EXPLOSION_EMITTER, this.x, this.y, this.z, 0, 1, 0, 0, 1);
            } else {
                serverLevel.sendParticles(ParticleTypes.EXPLOSION, this.x, this.y, this.z, 0, 1, 0, 0, 1);
            }
        }
        if (this.level.isClientSide) {
            this.level.playLocalSound(this.x, this.y, this.z, SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 4.0F, (1.0F + (this.level.random.nextFloat() - this.level.random.nextFloat()) * 0.2F) * 0.7F, false);
        }
    }
}
