package net.mrqx.huajiage.compat.slashblade.specialeffect;

import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.mrqx.huajiage.compat.slashblade.registy.HuaJiSpecialEffects;

public class WavingEdge extends SpecialEffect {
    public WavingEdge() {
        super(30, true, true);
    }

    @SubscribeEvent
    public static void onUpdateEvent(SlashBladeEvent.UpdateEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(HuaJiSpecialEffects.WAVING_EDGE.getId())) {
            if (!(event.getEntity() instanceof Player player)) {
                return;
            }

            if (!event.isSelected()) {
                return;
            }

            int level = player.experienceLevel;

            if (SpecialEffect.isEffective(HuaJiSpecialEffects.WAVING_EDGE.get(), level) && player.isInWaterRainOrBubble()) {
                player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 100, 2));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 2));
            }
        }
    }

    @SubscribeEvent
    public static void onDoSlashEvent(SlashBladeEvent.DoSlashEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(HuaJiSpecialEffects.WAVING_EDGE.getId())) {
            if (!(event.getUser() instanceof Player player)) {
                return;
            }
            int level = player.experienceLevel;
            if (SpecialEffect.isEffective(HuaJiSpecialEffects.WAVING_EDGE.get(), level)) {
                spawnParticle(player);
            }
        }
    }

    private static void spawnParticle(Entity entity) {
        if (!(entity.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        RandomSource random = entity.level().getRandom();
        for (int i = 0; i < 30; ++i) {
            double xDist = (random.nextFloat() * 4.0F - 2.0F);
            double yDist = (random.nextFloat() * 4.0F - 2.0F);
            double zDist = (random.nextFloat() * 4.0F - 2.0F);
            double x = entity.getX() + ((random.nextDouble() * 2 - 1) * entity.getBbWidth() - random.nextGaussian() * 0.02 * 10.0) * 2.0;
            double y = entity.getY() + ((random.nextDouble() * 2 - 1) * entity.getBbHeight() - random.nextGaussian() * 0.02 * 10.0) * 2.0;
            double z = entity.getZ() + ((random.nextDouble() * 2 - 1) * entity.getBbWidth() - random.nextGaussian() * 0.02 * 10.0) * 2.0;
            serverLevel.sendParticles((ParticleOptions) ParticleTypes.FALLING_WATER, x, y, z, 0, xDist, yDist + 0.2D, zDist, 1);
        }
    }
}
