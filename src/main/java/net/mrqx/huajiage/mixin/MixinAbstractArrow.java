package net.mrqx.huajiage.mixin;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;
import net.mrqx.huajiage.entity.EntityHeroArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractArrow.class)
public abstract class MixinAbstractArrow {
    @SuppressWarnings("all")
    @Redirect(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
    private void injectAddParticle(Level instance, ParticleOptions pParticleData, double pX, double pY, double pZ, double xSpeed, double ySpeed, double zSpeed) {
        if (!this.getClass().equals(EntityHeroArrow.class)) {
            instance.addParticle(pParticleData, pX, pY, pZ, xSpeed, ySpeed, zSpeed);
        }
    }
}
