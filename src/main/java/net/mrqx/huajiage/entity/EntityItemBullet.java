package net.mrqx.huajiage.entity;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.PlayMessages;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.utils.HuaJiDamageSources;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class EntityItemBullet extends Fireball {
    private static final EntityDataAccessor<Float> DAMAGE = SynchedEntityData
            .defineId(EntityItemBullet.class, EntityDataSerializers.FLOAT);

    private static final EntityDataAccessor<Boolean> HUGE = SynchedEntityData
            .defineId(EntityItemBullet.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Long> DELAY = SynchedEntityData
            .defineId(EntityItemBullet.class, EntityDataSerializers.LONG);

    public final long randomSeed;
    public final Consumer<EntityItemBullet> onShoot;
    private boolean hasExploded = false;

    public EntityItemBullet(EntityType<? extends Fireball> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.randomSeed = pLevel.random.nextLong();
        this.onShoot = bullet -> {
        };
    }

    public EntityItemBullet(LivingEntity pShooter, double pOffsetX, double pOffsetY, double pOffsetZ, Level pLevel) {
        super(HuaJiAgeMod.RegistryEvents.itemBulletEntityType, pShooter, pOffsetX, pOffsetY, pOffsetZ, pLevel);
        this.randomSeed = pLevel.random.nextLong();
        this.onShoot = bullet -> {
        };
    }

    public EntityItemBullet(LivingEntity pShooter, double pOffsetX, double pOffsetY, double pOffsetZ, Level pLevel, Consumer<EntityItemBullet> onShoot) {
        super(HuaJiAgeMod.RegistryEvents.itemBulletEntityType, pShooter, pOffsetX, pOffsetY, pOffsetZ, pLevel);
        this.randomSeed = pLevel.random.nextLong();
        this.onShoot = onShoot;
    }

    @SuppressWarnings("unused")
    public static EntityItemBullet createInstance(PlayMessages.SpawnEntity packet, Level worldIn) {
        return new EntityItemBullet(HuaJiAgeMod.RegistryEvents.itemBulletEntityType, worldIn);
    }

    public static EntityItemBullet create(EntityType<? extends Fireball> pEntityType, Level pLevel) {
        return new EntityItemBullet(pEntityType, pLevel);
    }

    @Override
    @Nullable
    public ItemStack getPickedResult(HitResult target) {
        return null;
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putFloat("damage", this.getDamage());
        pCompound.putBoolean("huge", this.isSplashHuge());
        pCompound.putLong("delay", this.getDelay());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DAMAGE, 5F);
        this.entityData.define(HUGE, false);
        this.entityData.define(DELAY, 0L);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setDamage(pCompound.getFloat("damage"));
        this.setSplashHuge(pCompound.getBoolean("huge"));
        this.setDelay(pCompound.getLong("delay"));
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }

    @Override
    protected float getInertia() {
        return 1;
    }

    @Override
    public void tick() {
        this.setDelay(this.getDelay() - 1);
        double r1 = (this.level().random.nextDouble() - 0.5) * 0.2;
        double r2 = (this.level().random.nextDouble() - 0.5) * 0.2;
        double r3 = (this.level().random.nextDouble() - 0.5) * 0.2;
        if (r1 > 0.05) {
            this.level().addParticle(ParticleTypes.ELECTRIC_SPARK, this.getX() + r1, this.getEyeY() + r2, this.getZ() + r3, r1, r2, r3);
        }
        if (this.isSplashHuge()) {
            double r11 = (this.level().random.nextDouble() - 0.5) * 0.2;
            double r12 = (this.level().random.nextDouble() - 0.5) * 0.2;
            double r13 = (this.level().random.nextDouble() - 0.5) * 0.2;
            if (r11 > 0.05) {
                this.level().addParticle(ParticleTypes.HAPPY_VILLAGER, this.getX() + r11, this.getEyeY() + r12, this.getZ() + r13, r11, r12, r13);
            }
        }
        if (this.getDelay() <= 0) {
            if (this.getDelay() == 0) {
                this.onShoot.accept(this);
            }
            super.tick();
            if (this.getDelay() < -300) {
                this.discard();
            }
        }
    }

    @Override
    protected @NotNull ParticleOptions getTrailParticle() {
        return ParticleTypes.ELECTRIC_SPARK;
    }

    @Override
    public boolean hurt(@NotNull DamageSource pSource, float pAmount) {
        return false;
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult pResult) {
        super.onHitBlock(pResult);
        if (!hasExploded) {
            explode();
            this.setDelay(-300);
        }
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if (!(pResult.getEntity() instanceof EntityItemBullet) && !pResult.getEntity().equals(this.getOwner())) {
            if (!hasExploded) {
                explode();
                this.setDelay(-300);
            }
        }
    }

    protected void explode() {
        this.hasExploded = true;
        Explosion explosion = new ExplosionHuaJi(this.level(), this.getOwner(), HuaJiDamageSources.emeraldSplash(this.level(), this, this.getOwner(), this.position()),
                null, getX(), getY(), getZ(), 1.9F, Explosion.BlockInteraction.KEEP, this.getDamage(), 0, false);

        explosion.explode();
        explosion.finalizeExplosion(true);
    }

    public float getDamage() {
        return this.entityData.get(DAMAGE);
    }

    public void setDamage(float damage) {
        this.entityData.set(DAMAGE, damage);
    }

    public boolean isSplashHuge() {
        return this.entityData.get(HUGE);
    }

    public void setSplashHuge(boolean isHuge) {
        this.entityData.set(HUGE, isHuge);
    }

    public long getDelay() {
        return this.entityData.get(DELAY);
    }

    public void setDelay(long delay) {
        this.entityData.set(DELAY, delay);
    }
}
