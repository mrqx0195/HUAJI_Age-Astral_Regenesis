package net.mrqx.huajiage.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
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
import net.mrqx.huajiage.utils.HuaJiDamageSources;

import javax.annotation.Nullable;

public class EntityOrgaHairKnife extends ThrowableItemProjectile {
    private static final EntityDataAccessor<Float> DAMAGE = SynchedEntityData
            .defineId(EntityOrgaHairKnife.class, EntityDataSerializers.FLOAT);

    private static final EntityDataAccessor<Long> LIFE = SynchedEntityData
            .defineId(EntityOrgaHairKnife.class, EntityDataSerializers.LONG);

    private static final EntityDataAccessor<Float> ROTATION_RANDOM = SynchedEntityData
            .defineId(EntityOrgaHairKnife.class, EntityDataSerializers.FLOAT);

    private boolean hasExploded = false;

    public EntityOrgaHairKnife(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
        this.setRotationRandom(level.random.nextFloat());
    }

    public EntityOrgaHairKnife(LivingEntity shooter, Level level) {
        super(HuaJiAgeMod.RegistryEvents.orgaHairKnifeEntityType, shooter, level);
        this.setRotationRandom(level.random.nextFloat());
    }

    @SuppressWarnings("unused")
    public static EntityOrgaHairKnife createInstance(PlayMessages.SpawnEntity packet, Level worldIn) {
        return new EntityOrgaHairKnife(HuaJiAgeMod.RegistryEvents.orgaHairKnifeEntityType, worldIn);
    }

    public static EntityOrgaHairKnife create(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        return new EntityOrgaHairKnife(pEntityType, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return HuaJiItems.ORGA_HAIR_KNIFE.get();
    }

    @Override
    @Nullable
    public ItemStack getPickedResult(HitResult target) {
        return this.getItem();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putFloat("damage", this.getDamage());
        pCompound.putLong("life", this.getLife());
        pCompound.putFloat("rotation_random", this.getRotationRandom());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DAMAGE, 5F);
        this.entityData.define(LIFE, 200L);
        this.entityData.define(ROTATION_RANDOM, 0F);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setDamage(pCompound.getFloat("damage"));
        this.setLife(pCompound.getLong("life"));
        this.setRotationRandom(pCompound.getFloat("rotation_random"));
    }

    @Override
    public void tick() {
        this.setLife(this.getLife() - 1);
        super.tick();
        double r1 = (this.level().random.nextDouble() - 0.5) * 0.2;
        double r2 = (this.level().random.nextDouble() - 0.5) * 0.2;
        double r3 = (this.level().random.nextDouble() - 0.5) * 0.2;
        if (r1 > 0.05) {
            this.level().addParticle(ParticleTypes.FIREWORK, this.getX() + r1, this.getEyeY() + r2, this.getZ() + r3, r1, r2, r3);
        }
        if (this.getLife() < 0) {
            this.discard();
        }
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        return false;
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        if (!hasExploded) {
            explode();
            this.setLife(1);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
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
        Explosion explosion = new ExplosionHuaJi(this.level(), this.getOwner(), HuaJiDamageSources.hopeFlower(this.level(), this, this.getOwner(), this.position()),
                null, getX(), getY(), getZ(), 1.5F, Explosion.BlockInteraction.KEEP, this.getDamage(), 0, false);

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

    public float getRotationRandom() {
        return this.entityData.get(ROTATION_RANDOM);
    }

    public void setRotationRandom(float rot) {
        this.entityData.set(ROTATION_RANDOM, rot);
    }
}
