package net.mrqx.huajiage.data.sound;

import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.registy.HuaJiSoundEvents;

public class HuaJiSoundDefinitions extends SoundDefinitionsProvider {
    public HuaJiSoundDefinitions(PackOutput output, ExistingFileHelper helper) {
        super(output, HuaJiAgeMod.MODID, helper);
    }

    @Override
    public void registerSounds() {
        HuaJiSoundEvents.SOUND_EVENTS.getEntries().forEach(soundEvent -> this.addSoundWithSubTitle(soundEvent.get()));
    }

    protected void addSoundWithSubTitle(SoundEvent soundEvent) {
        this.add(soundEvent, SoundDefinition.definition()
                .subtitle("subtitles." + soundEvent.getLocation().toString().replace(':', '.'))
                .with(SoundDefinition.Sound.sound(soundEvent.getLocation(), SoundDefinition.SoundType.SOUND))
        );
    }
}
