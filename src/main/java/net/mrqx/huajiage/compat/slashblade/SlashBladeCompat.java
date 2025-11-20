package net.mrqx.huajiage.compat.slashblade;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.mrqx.huajiage.compat.slashblade.registy.HuaJiComboStates;
import net.mrqx.huajiage.compat.slashblade.registy.HuaJiSlashArts;
import net.mrqx.huajiage.compat.slashblade.registy.HuaJiSpecialEffects;
import net.mrqx.huajiage.compat.slashblade.specialeffect.SwornKinship;
import net.mrqx.huajiage.compat.slashblade.specialeffect.WavingEdge;

public class SlashBladeCompat {
    public static void register(IEventBus modEventBus) {
        HuaJiComboStates.COMBO_STATE.register(modEventBus);
        HuaJiSlashArts.SLASH_ARTS.register(modEventBus);
        HuaJiSpecialEffects.SPECIAL_EFFECT.register(modEventBus);

        MinecraftForge.EVENT_BUS.addListener(WavingEdge::onUpdateEvent);
        MinecraftForge.EVENT_BUS.addListener(WavingEdge::onDoSlashEvent);
        MinecraftForge.EVENT_BUS.addListener(SwornKinship::onUpdateEvent);
        MinecraftForge.EVENT_BUS.addListener(SwornKinship::onHitEvent);
    }
}
