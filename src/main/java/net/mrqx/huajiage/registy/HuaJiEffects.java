package net.mrqx.huajiage.registy;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.effect.EffectFive;
import net.mrqx.huajiage.effect.EffectStandPower;

public class HuaJiEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, HuaJiAgeMod.MODID);

    public static final RegistryObject<MobEffect> FIVE = MOB_EFFECTS.register("five", EffectFive::new);
    public static final RegistryObject<MobEffect> STAND_POWER = MOB_EFFECTS.register("stand_power", EffectStandPower::new);
}
