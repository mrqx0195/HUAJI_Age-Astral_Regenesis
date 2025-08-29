package net.mrqx.huajiage.utils;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.mrqx.huajiage.data.HuaJiDamageTypes;

import javax.annotation.Nullable;

public class HuaJiDamageSources {
    public static DamageSource simple(Entity entity, ResourceKey<DamageType> damageType) {
        return new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(damageType),
                entity, entity, entity.position());
    }

    public static DamageSource simpleNullSource(Level level, ResourceKey<DamageType> damageType) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(damageType));
    }

    public static DamageSource antimatter(Level level, @Nullable Entity pDirectEntity, @Nullable Entity pCausingEntity, @Nullable Vec3 pDamageSourcePosition) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(HuaJiDamageTypes.ANTIMATTER),
                pDirectEntity, pCausingEntity, pDamageSourcePosition);
    }

    public static DamageSource keDaiJinLa(Level level, @Nullable Entity pDirectEntity, @Nullable Entity pCausingEntity, @Nullable Vec3 pDamageSourcePosition) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(HuaJiDamageTypes.KE_DAI_JIN_LA),
                pDirectEntity, pCausingEntity, pDamageSourcePosition);
    }

    public static DamageSource stella(Level level, @Nullable Entity pDirectEntity, @Nullable Entity pCausingEntity, @Nullable Vec3 pDamageSourcePosition) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(HuaJiDamageTypes.STELLA),
                pDirectEntity, pCausingEntity, pDamageSourcePosition);
    }

    public static DamageSource five(Level level, DamageSource damageSource) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(HuaJiDamageTypes.FIVE),
                damageSource.getDirectEntity(), damageSource.getEntity(), damageSource.getSourcePosition());
    }

    public static DamageSource five(Level level, @Nullable Entity pDirectEntity, @Nullable Entity pCausingEntity, @Nullable Vec3 pDamageSourcePosition) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(HuaJiDamageTypes.FIVE),
                pDirectEntity, pCausingEntity, pDamageSourcePosition);
    }

    public static DamageSource emeraldSplash(Level level, @Nullable Entity pDirectEntity, @Nullable Entity pCausingEntity, @Nullable Vec3 pDamageSourcePosition) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(HuaJiDamageTypes.EMERALD_SPLASH),
                pDirectEntity, pCausingEntity, pDamageSourcePosition);
    }

    public static DamageSource standHit(Level level, @Nullable Entity pDirectEntity, @Nullable Entity pCausingEntity, @Nullable Vec3 pDamageSourcePosition) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(HuaJiDamageTypes.STAND_HIT),
                pDirectEntity, pCausingEntity, pDamageSourcePosition);
    }

    public static DamageSource singularity(Level level, @Nullable Entity entity) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(HuaJiDamageTypes.SINGULARITY),
                entity, entity, entity != null ? entity.position() : null);
    }

    public static DamageSource hopeFlower(Level level, @Nullable Entity pDirectEntity, @Nullable Entity pCausingEntity, @Nullable Vec3 pDamageSourcePosition) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(HuaJiDamageTypes.HOPE_FLOWER),
                pDirectEntity, pCausingEntity, pDamageSourcePosition);
    }

    public static DamageSource orgaShot(Level level, @Nullable Entity pDirectEntity, @Nullable Entity pCausingEntity, @Nullable Vec3 pDamageSourcePosition) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(HuaJiDamageTypes.ORGA_SHOT),
                pDirectEntity, pCausingEntity, pDamageSourcePosition);
    }

    public static DamageSource requiem(Level level, @Nullable Entity pDirectEntity, @Nullable Entity pCausingEntity, @Nullable Vec3 pDamageSourcePosition) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(HuaJiDamageTypes.REQUIEM),
                pDirectEntity, pCausingEntity, pDamageSourcePosition);
    }
}
