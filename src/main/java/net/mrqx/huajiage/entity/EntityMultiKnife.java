package net.mrqx.huajiage.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;
import net.mrqx.huajiage.HuaJiAgeMod;

public class EntityMultiKnife extends AbstractArrow {
    private static final EntityDataAccessor<Long> LIFE = SynchedEntityData
            .defineId(EntityMultiKnife.class, EntityDataSerializers.LONG);

    private static final EntityDataAccessor<Boolean> SHINY = SynchedEntityData
            .defineId(EntityMultiKnife.class, EntityDataSerializers.BOOLEAN);

    public EntityMultiKnife(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public EntityMultiKnife(LivingEntity shooter, Level level) {
        super(HuaJiAgeMod.RegistryEvents.multiKnifeEntityType, shooter, level);
    }

    @SuppressWarnings("unused")
    public static EntityMultiKnife createInstance(PlayMessages.SpawnEntity packet, Level worldIn) {
        return new EntityMultiKnife(HuaJiAgeMod.RegistryEvents.multiKnifeEntityType, worldIn);
    }

    public static EntityMultiKnife create(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        return new EntityMultiKnife(pEntityType, pLevel);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putLong("life", this.getLife());
        pCompound.putBoolean("shiny", this.isShiny());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(LIFE, 200L);
        this.entityData.define(SHINY, false);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setLife(pCompound.getLong("life"));
        this.setShiny(pCompound.getBoolean("shiny"));
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    protected ItemStack getPickupItem() {
        return Items.AIR.getDefaultInstance();
    }

    @Override
    public void tick() {
        this.setLife(this.getLife() - 1);
        Vec3 motion = this.getDeltaMovement().normalize().scale(2);
        super.tick();
        if (this.getLife() < 0) {
            this.discard();
        } else if (!this.inGround) {
            this.setDeltaMovement(motion);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        result.getEntity().invulnerableTime = 0;
        super.onHitEntity(result);
        result.getEntity().invulnerableTime = 0;
        if (this.isShiny()) {
            result.getEntity().setSecondsOnFire(5);
        }
    }

    public long getLife() {
        return this.entityData.get(LIFE);
    }

    public void setLife(long life) {
        this.entityData.set(LIFE, life);
    }

    public boolean isShiny() {
        return this.entityData.get(SHINY);
    }

    public void setShiny(boolean isHuge) {
        this.entityData.set(SHINY, isHuge);
    }
}
