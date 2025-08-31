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
        this.addSound(HuaJiSoundEvents.ENERGY_HIT.get());
        this.addSound(HuaJiSoundEvents.CHARGE.get());
        this.addSound(HuaJiSoundEvents.WAVE1.get());
        this.addSound(HuaJiSoundEvents.NOISE_FURNACE.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.EXGLUTENBUR_1.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.EXGLUTENBUR_2.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.EXGLUTENBUR_3.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.EXGLUTENBUR_HIT.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.ORGA_SHOT.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.ORGA_FLOWER.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.ORGA_REQUIEM.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.ORGA_REQUIEM_1.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.ORGA_REQUIEM_2.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.ORGA_REQUIEM_3.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.ORGA_REQUIEM_GOLD.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.ORGA_REQUIEM_PROTECT.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.ORGA_REQUIEM_HIT.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.ORGA_RIDER.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STELLA.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.THE_WORLD.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.THE_WORLD_1.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.THE_WORLD_2.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.THE_WORLD_3.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.THE_WORLD_RE.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.ROAD_ROLLER.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.DIO_FLAG.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.DIO_HIT.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_THE_WORLD_HIT_1.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_THE_WORLD_HIT_2.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_STAR_PLATINUM_1.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_STAR_PLATINUM_2.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_STAR_PLATINUM_3.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_STAR_PLATINUM_4.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_STAR_PLATINUM_5.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAR_PLATINUM_THE_WORLD_1.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_STAR_PLATINUM_REPEAT_1.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_HIEROPHANT_GREEN_SHOOT_1.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_HIEROPHANT_GREEN_SHOOT_2.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_HIEROPHANT_GREEN_EMERALD_SPLASH.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_KILLER_QUEEN_TRIGGER.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_KILLER_QUEEN_SHOW_1.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_KILLER_QUEEN_SHOW_2.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_CRAZY_DIAMOND_1.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_CRAZY_DIAMOND_2.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_CRAZY_DIAMOND_3.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_CRAZY_DIAMOND_4.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_CRAZY_DIAMOND_REPAIR_1.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_CRAZY_DIAMOND_REPAIR_2.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_HERMIT_PURPLE_1.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_HERMIT_PURPLE_2.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_HERMIT_PURPLE_WAVE.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_HERMIT_PURPLE_CAMERA_BROKEN.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_WHITE_SNAKE_1.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_WHITE_SNAKE_2.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_WHITE_SNAKE_3.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_WHITE_SNAKE_HIT_1.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_WHITE_SNAKE_HIT_2.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_WHITE_SNAKE_HIT_3.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_WHITE_SNAKE_MAKE_DISC_1.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.STAND_WHITE_SNAKE_MAKE_DISC_2.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.SHEER_HEART_ATTACK.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.BIKE_RING_1.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.REO_CHERRY.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.WAVE_OVERDRIVE_1.get());
        this.addSoundWithSubTitle(HuaJiSoundEvents.WAVE_OVERDRIVE_RUN.get());
    }

    protected void addSound(SoundEvent soundEvent) {
        this.add(soundEvent, SoundDefinition.definition()
                .with(SoundDefinition.Sound.sound(soundEvent.getLocation(), SoundDefinition.SoundType.SOUND).preload(true))
        );
    }

    protected void addSoundWithSubTitle(SoundEvent soundEvent) {
        this.add(soundEvent, SoundDefinition.definition()
                .subtitle("subtitles." + soundEvent.getLocation().toString().replace(':', '.'))
                .with(SoundDefinition.Sound.sound(soundEvent.getLocation(), SoundDefinition.SoundType.SOUND).preload(true))
        );
    }
}
