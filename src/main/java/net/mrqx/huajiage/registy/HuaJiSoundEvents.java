package net.mrqx.huajiage.registy;

import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrqx.huajiage.HuaJiAgeMod;

public class HuaJiSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, HuaJiAgeMod.MODID);

    public static final RegistryObject<SoundEvent> EXGLUTENBUR_1 = registerSound("exglutenbur_flavor1");
    public static final RegistryObject<SoundEvent> EXGLUTENBUR_2 = registerSound("exglutenbur_flavor2");
    public static final RegistryObject<SoundEvent> EXGLUTENBUR_3 = registerSound("exglutenbur_flavor3");
    public static final RegistryObject<SoundEvent> EXGLUTENBUR_HIT = registerSound("exglutenbur_hit");
    public static final RegistryObject<SoundEvent> STELLA = registerSound("stella");

    private static RegistryObject<SoundEvent> registerSound(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(HuaJiAgeMod.prefix(name)));
    }
}
