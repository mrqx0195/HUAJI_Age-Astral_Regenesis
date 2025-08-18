package net.mrqx.huajiage.utils;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.mrqx.huajiage.data.HuaJiDamageTypes;

import javax.annotation.Nullable;

public class HuaJiDamageSources {
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
}
