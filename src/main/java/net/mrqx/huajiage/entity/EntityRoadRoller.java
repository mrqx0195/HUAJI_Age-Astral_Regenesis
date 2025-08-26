package net.mrqx.huajiage.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.PlayMessages;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.registy.HuaJiSoundEvents;
import net.mrqx.huajiage.utils.HuaJiDamageSources;
import net.mrqx.huajiage.utils.HuajiSoundPlayer;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class EntityRoadRoller extends ThrowableItemProjectile {
    private static final EntityDataAccessor<Float> DAMAGE = SynchedEntityData
            .defineId(EntityRoadRoller.class, EntityDataSerializers.FLOAT);

    private static final EntityDataAccessor<Long> LIFE = SynchedEntityData
            .defineId(EntityRoadRoller.class, EntityDataSerializers.LONG);

    private boolean hasExploded = false;
    private int hitTime = 0;

    public EntityRoadRoller(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public EntityRoadRoller(LivingEntity shooter, Level level) {
        super(HuaJiAgeMod.RegistryEvents.roadRollerEntityType, shooter, level);
    }

    @SuppressWarnings("unused")
    public static EntityRoadRoller createInstance(PlayMessages.SpawnEntity packet, Level worldIn) {
        return new EntityRoadRoller(HuaJiAgeMod.RegistryEvents.roadRollerEntityType, worldIn);
    }

    public static EntityRoadRoller create(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        return new EntityRoadRoller(pEntityType, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return HuaJiItems.ROAD_ROLLER.get();
    }

    @Override
    @Nullable
    public ItemStack getPickedResult(HitResult target) {
        return HuaJiItems.ROAD_ROLLER.get().getDefaultInstance();
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putFloat("damage", this.getDamage());
        pCompound.putLong("life", this.getLife());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DAMAGE, 5F);
        this.entityData.define(LIFE, 0L);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setDamage(pCompound.getFloat("damage"));
        this.setLife(pCompound.getLong("life"));
    }

    @Override
    public void tick() {
        this.setLife(this.getLife() - 1);
        super.tick();
        if (this.getLife() < -300) {
            this.discard();
        }
    }

    @Override
    public boolean hurt(@NotNull DamageSource pSource, float pAmount) {
        this.setDamage(this.getDamage() + pAmount);
        if (this.level() instanceof ServerLevel serverLevel) {
            if (hitTime == 0) {
                HuajiSoundPlayer.playMovingSoundToClient(this, HuaJiSoundEvents.DIO_FLAG.get(), this.getSoundSource(), 2F);
            } else if (hitTime % 4 == 0) {
                HuajiSoundPlayer.playMovingSoundToClient(this, HuaJiSoundEvents.DIO_HIT.get(), this.getSoundSource(), 0.75F);
            }
            hitTime++;
            serverLevel.sendParticles(ParticleTypes.LAVA, getX(), getY(), getZ(), 5, 1, 1, 1, 1);
        }
        return false;
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult pResult) {
        super.onHitBlock(pResult);
        if (!hasExploded) {
            explode();
            this.setLife(1);
        }
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if (!(pResult.getEntity() instanceof EntityItemBullet) && !pResult.getEntity().equals(this.getOwner())) {
            if (!hasExploded) {
                explode();
                this.setLife(1);
            }
        }
    }

    protected void explode() {
        this.hasExploded = true;
        Explosion explosion = new ExplosionHuaJi(this.level(), this.getOwner(), HuaJiDamageSources.standHit(this.level(), this, this.getOwner(), this.position()),
                null, getX(), getY(), getZ(), 4F, Explosion.BlockInteraction.KEEP, this.getDamage(), 0, false);

        explosion.explode();
        explosion.finalizeExplosion(true);
    }

    public float getDamage() {
        return this.entityData.get(DAMAGE);
    }

    public void setDamage(float damage) {
        this.entityData.set(DAMAGE, damage);
    }

    public long getLife() {
        return this.entityData.get(LIFE);
    }

    public void setLife(long life) {
        this.entityData.set(LIFE, life);
    }
}
