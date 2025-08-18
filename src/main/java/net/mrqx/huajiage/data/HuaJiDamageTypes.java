package net.mrqx.huajiage.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.mrqx.huajiage.HuaJiAgeMod;

public class HuaJiDamageTypes {
    public static final ResourceKey<DamageType>
            ANTIMATTER = ResourceKey.create(Registries.DAMAGE_TYPE, HuaJiAgeMod.prefix("antimatter")),
            KE_DAI_JIN_LA = ResourceKey.create(Registries.DAMAGE_TYPE, HuaJiAgeMod.prefix("ke_dai_jin_la")),
            STELLA = ResourceKey.create(Registries.DAMAGE_TYPE, HuaJiAgeMod.prefix("stella")),
            FIVE = ResourceKey.create(Registries.DAMAGE_TYPE, HuaJiAgeMod.prefix("five"));

    public static void register(BootstapContext<DamageType> context) {
        registerDamageType(context, ANTIMATTER, 40);
        registerDamageType(context, KE_DAI_JIN_LA, 2);
        registerDamageType(context, STELLA, 2);
        registerDamageType(context, FIVE, 5);
    }

    private static void registerDamageType(BootstapContext<DamageType> context, ResourceKey<DamageType> damageType, float pExhaustion) {
        context.register(damageType, new DamageType(damageType.location().getNamespace() + "." + damageType.location().getPath(), pExhaustion));
    }
}
