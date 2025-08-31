package net.mrqx.huajiage.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.registy.HuaJiSoundEvents;
import net.mrqx.huajiage.utils.HuaJiMathHelper;
import net.mrqx.huajiage.utils.HuaJiSoundPlayer;
import org.jetbrains.annotations.Nullable;

public class EntitySheerHeartAttack extends TamableAnimal {
    private static final EntityDataAccessor<Boolean> TRIGGER = SynchedEntityData
            .defineId(EntitySheerHeartAttack.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Long> LIFE = SynchedEntityData
            .defineId(EntitySheerHeartAttack.class, EntityDataSerializers.LONG);

    public EntitySheerHeartAttack(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    public EntitySheerHeartAttack(Level level) {
        super(HuaJiAgeMod.RegistryEvents.sheerHeartAttackEntityType, level);
    }

    @SuppressWarnings("unused")
    public static EntitySheerHeartAttack createInstance(PlayMessages.SpawnEntity packet, Level worldIn) {
        return new EntitySheerHeartAttack(HuaJiAgeMod.RegistryEvents.sheerHeartAttackEntityType, worldIn);
    }

    public static EntitySheerHeartAttack create(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        return new EntitySheerHeartAttack(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 15);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.5D, false));
        this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.0d, 20, 5, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.4D));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 0.4D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Mob.class, 10.0F));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Mob.class, true));
        this.targetSelector.addGoal(4, (new HurtByTargetGoal(this)).setAlertOthers());
    }

    @Override
    public boolean wantsToAttack(LivingEntity target, LivingEntity owner) {
        return !(target instanceof OwnableEntity ownable && owner.equals(ownable.getOwner())) && super.wantsToAttack(target, owner);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (player.equals(this.getOwner())) {
            player.heal(player.getMaxHealth() / 2);
            HuaJiSoundPlayer.playMovingSoundToClient(player, SoundEvents.ARMOR_EQUIP_DIAMOND);
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 300, 1));
            this.discard();
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    @Override
    public void tick() {
        this.setLife(this.getLife() - 1);
        super.tick();
        LivingEntity target = this.getTarget();
        if (target != null && HuaJiMathHelper.getDistance(this.position(), target.position()) < 8) {
            if (!isTriggered()) {
                HuaJiSoundPlayer.playMovingSoundToClient(this, HuaJiSoundEvents.STAND_KILLER_QUEEN_TRIGGER.get(), 2);
                setTrigger(true);
            }
            if (this.tickCount % 20 == 0) {
                Vec3 vec = HuaJiMathHelper.getVectorEntityEye(this, target);
                this.setDeltaMovement(vec.scale(0.8));
            }
        } else {
            setTrigger(false);
        }

        if (this.getLife() < 0) {
            this.explode();
            this.discard();
        }
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        boolean hurtTarget = super.doHurtTarget(entity);
        if (hurtTarget) {
            this.explode();
        }
        return hurtTarget;
    }

    protected void explode() {
        Explosion explosion = new ExplosionHuaJi(this.level(), this.getOwner(), this.level().damageSources().explosion(this.getOwner(), this),
                null, getX(), getY(), getZ(), 5, Explosion.BlockInteraction.KEEP, this.getAttributeValue(Attributes.ATTACK_DAMAGE),
                0, true);

        explosion.explode();
        explosion.finalizeExplosion(true);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        return false;
    }

    @Override
    protected void actuallyHurt(DamageSource damageSource, float damageAmount) {
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("trigger", this.isTriggered());
        pCompound.putLong("life", this.getLife());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TRIGGER, false);
        this.entityData.define(LIFE, 400L);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setTrigger(pCompound.getBoolean("trigger"));
        this.setLife(pCompound.getLong("life"));
    }

    public boolean isTriggered() {
        return this.entityData.get(TRIGGER);
    }

    public void setTrigger(boolean isHuge) {
        this.entityData.set(TRIGGER, isHuge);
    }

    public long getLife() {
        return this.entityData.get(LIFE);
    }

    public void setLife(long delay) {
        this.entityData.set(LIFE, delay);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return null;
    }
}
