package net.mrqx.huajiage.compat.slashblade.registy;

import mods.flammpfeil.slashblade.slasharts.SlashArts;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.mrqx.huajiage.HuaJiAgeMod;

public class HuaJiSlashArts {
    public static final DeferredRegister<SlashArts> SLASH_ARTS = DeferredRegister.create(SlashArts.REGISTRY_KEY,
            HuaJiAgeMod.MODID);

    public static final RegistryObject<SlashArts> WAVE_SLASH = SLASH_ARTS.register("wave_slash",
            () -> new SlashArts(livingEntity -> HuaJiComboStates.WAVE_SLASH_PRE.getId())
                    .setComboStateJust(livingEntity -> HuaJiComboStates.WAVE_SLASH.getId()));
}
