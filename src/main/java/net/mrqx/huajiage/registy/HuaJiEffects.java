package net.mrqx.huajiage.registy;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.effect.*;

public class HuaJiEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, HuaJiAgeMod.MODID);

    public static final RegistryObject<MobEffect> FIVE = MOB_EFFECTS.register("five", EffectFive::new);
    public static final RegistryObject<MobEffect> STAND_POWER = MOB_EFFECTS.register("stand_power", EffectStandPower::new);
    public static final RegistryObject<MobEffect> HOPE_FLOWER = MOB_EFFECTS.register("hope_flower", EffectHopeFlower::new);
    public static final RegistryObject<MobEffect> ORGA_TARGET = MOB_EFFECTS.register("orga_target", EffectOrgaTarget::new);
    public static final RegistryObject<MobEffect> REQUIEM = MOB_EFFECTS.register("requiem", EffectRequiem::new);
    public static final RegistryObject<MobEffect> REQUIEM_TARGET = MOB_EFFECTS.register("requiem_target", EffectRequiemTarget::new);
}
