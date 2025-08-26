package net.mrqx.huajiage.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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

public class EntityFivePower extends Fireball {
    private static final EntityDataAccessor<Float> DAMAGE = SynchedEntityData
            .defineId(EntityFivePower.class, EntityDataSerializers.FLOAT);

    private static final EntityDataAccessor<Boolean> IS_DE = SynchedEntityData
            .defineId(EntityFivePower.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Long> DELAY = SynchedEntityData
            .defineId(EntityFivePower.class, EntityDataSerializers.LONG);

    public final long randomSeed;
    private boolean hasExploded = false;
    public boolean timeStopFix = false;

    public EntityFivePower(EntityType<? extends Fireball> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.randomSeed = pLevel.random.nextLong();
    }

    public EntityFivePower(LivingEntity pShooter, double pOffsetX, double pOffsetY, double pOffsetZ, Level pLevel) {
        super(HuaJiAgeMod.RegistryEvents.fivePowerEntityType, pShooter, pOffsetX, pOffsetY, pOffsetZ, pLevel);
        this.randomSeed = pLevel.random.nextLong();
    }

    @SuppressWarnings("unused")
    public static EntityFivePower createInstance(PlayMessages.SpawnEntity packet, Level worldIn) {
        return new EntityFivePower(HuaJiAgeMod.RegistryEvents.fivePowerEntityType, worldIn);
    }

    public static EntityFivePower create(EntityType<? extends Fireball> pEntityType, Level pLevel) {
        return new EntityFivePower(pEntityType, pLevel);
    }

    @Override
    public ItemStack getItemRaw() {
        return Items.AIR.getDefaultInstance();
    }

    @Override
    @Nullable
    public ItemStack getPickedResult(HitResult target) {
        return Items.AIR.getDefaultInstance();
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putFloat("damage", this.getDamage());
        pCompound.putBoolean("is_de", this.isDe());
        pCompound.putLong("delay", this.getDelay());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DAMAGE, 15F);
        this.entityData.define(IS_DE, false);
        this.entityData.define(DELAY, 0L);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setDamage(pCompound.getFloat("damage"));
        this.setIsDe(pCompound.getBoolean("is_de"));
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
        if (this.getDelay() <= 0) {
            super.tick();
            if (this.getDelay() < -300) {
                this.discard();
            }
        }
    }

    @Override
    public boolean hurt(@NotNull DamageSource pSource, float pAmount) {
        return false;
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult pResult) {
        super.onHitBlock(pResult);
        if (!hasExploded && !this.level().isClientSide) {
            explode();
            this.setDelay(-300);
        }
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if (!(pResult.getEntity() instanceof EntityFivePower) && !pResult.getEntity().equals(this.getOwner()) && !this.level().isClientSide) {
            if (!hasExploded) {
                explode();
                this.setDelay(-300);
            }
        }
    }

    protected void explode() {
        this.hasExploded = true;
        Explosion explosion = new ExplosionHuaJi(this.level(), this.getOwner(), HuaJiDamageSources.five(this.level(), this, this.getOwner(), this.position()),
                null, getX(), getY(), getZ(), 1.9F, Explosion.BlockInteraction.KEEP, this.getDamage(), 0, false, entity -> {
            if (this.isDe() && entity instanceof LivingEntity living) {
                living.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60));
                living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 2));
                living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 2));
                living.addEffect(new MobEffectInstance(MobEffects.GLOWING, 60));
            } else if (!this.isDe()) {
                entity.setSecondsOnFire(3);
                entity.hurt(HuaJiDamageSources.five(this.level(), this, this.getOwner(), this.position()), 15);
                if (!timeStopFix) {
                    for (int i = 0; i < 3; i++) {
                        LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, entity.level());
                        bolt.setPos(entity.position());
                        bolt.setVisualOnly(true);
                        entity.level().addFreshEntity(bolt);
                    }
                }
                if (this.getOwner() instanceof LivingEntity living) {
                    living.heal(2);
                }
            }
        });

        explosion.explode();
        explosion.finalizeExplosion(true);
    }

    public float getDamage() {
        return this.entityData.get(DAMAGE);
    }

    public void setDamage(float damage) {
        this.entityData.set(DAMAGE, damage);
    }

    public boolean isDe() {
        return this.entityData.get(IS_DE);
    }

    public void setIsDe(boolean isDe) {
        this.entityData.set(IS_DE, isDe);
    }

    public long getDelay() {
        return this.entityData.get(DELAY);
    }

    public void setDelay(long delay) {
        this.entityData.set(DELAY, delay);
    }
}
