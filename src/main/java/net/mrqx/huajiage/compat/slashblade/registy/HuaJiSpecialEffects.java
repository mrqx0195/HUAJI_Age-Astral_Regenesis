package net.mrqx.huajiage.compat.slashblade.registy;

import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.compat.slashblade.specialeffect.SwornKinship;
import net.mrqx.huajiage.compat.slashblade.specialeffect.WavingEdge;

public class HuaJiSpecialEffects {
    public static final DeferredRegister<SpecialEffect> SPECIAL_EFFECT = DeferredRegister.create(SpecialEffect.REGISTRY_KEY,
            HuaJiAgeMod.MODID);

    public static final RegistryObject<SpecialEffect> WAVING_EDGE = SPECIAL_EFFECT.register("waving_edge",
            WavingEdge::new);
    public static final RegistryObject<SpecialEffect> SWORN_KINSHIP = SPECIAL_EFFECT.register("sworn_kinship",
            SwornKinship::new);
}
