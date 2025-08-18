package net.mrqx.huajiage.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.PlayMessages;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.init.HuaJiTickets;
import net.mrqx.huajiage.utils.HuaJiDamageSources;
import org.jetbrains.annotations.NotNull;

public class EntityHeroArrow extends AbstractArrow implements ItemSupplier {
    private boolean hasExploded = false;

    public EntityHeroArrow(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public EntityHeroArrow(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
    }

    @SuppressWarnings("unused")
    public static EntityHeroArrow createInstance(PlayMessages.SpawnEntity packet, Level worldIn) {
        return new EntityHeroArrow(HuaJiAgeMod.RegistryEvents.heroArrowEntityType, worldIn);
    }

    public static EntityHeroArrow create(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        return new EntityHeroArrow(pEntityType, pLevel);
    }

    @Override
    protected @NotNull ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull ItemStack getItem() {
        return ItemStack.EMPTY;
    }

    @Override
    protected void onHit(@NotNull HitResult pResult) {
        super.onHit(pResult);
        if (!hasExploded && !this.level().isClientSide) {
            this.explode();
            hasExploded = true;
        }
    }

    protected void explode() {
        Explosion explosion = new ExplosionHeroArrow(this.level(), this, HuaJiDamageSources.stella(this.level(), this, this.getOwner(), this.position()),
                null, getX(), getY(), getZ(), 50, Explosion.BlockInteraction.KEEP, getBaseDamage(), getKnockback());

        explosion.explode();
        explosion.finalizeExplosion(true);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.broadcastEntityEvent(this, (byte) 0);
            serverLevel.getChunkSource().addRegionTicket(HuaJiTickets.HERO_TICKET, new ChunkPos(this.blockPosition()), 2, Unit.INSTANCE, true);
            double r = (Math.random() - 0.5) * 0.5;
            serverLevel.sendParticles(ParticleTypes.LAVA, getX() + r, getY(), getZ() + r, 5, 0, 0, 0, 0.01);
            serverLevel.sendParticles(ParticleTypes.FIREWORK, getX(), getY(), getZ(), 0, 0, 0, 0, 0);
        }
        if (this.inGroundTime > 2) {
            this.discard();
        }
    }
}
