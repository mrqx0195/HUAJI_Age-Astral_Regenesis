package net.mrqx.huajiage.mixin.avaritia;

import committee.nova.mods.avaritia.common.item.singularity.Singularity;
import committee.nova.mods.avaritia.init.registry.ModSingularities;
import net.mrqx.huajiage.compat.avaritia.HuaJiSingularities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(ModSingularities.class)
public abstract class MixinModSingularities {
    @Inject(method = "getDefaults()Ljava/util/List;", at = @At("RETURN"), cancellable = true, remap = false)
    private static void injectGetDefaults(CallbackInfoReturnable<List<Singularity>> cir) {
        List<Singularity> singularities = new ArrayList<>(cir.getReturnValue());
        singularities.addAll(HuaJiSingularities.getHuaJiDefaultSingularities());
        cir.setReturnValue(singularities);
    }
}
