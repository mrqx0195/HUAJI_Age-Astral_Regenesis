package net.mrqx.huajiage.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrqx.huajiage.sound.HuajiMovingSoundInstance;

public class HuajiSoundPlayer {
    private static SoundInstance currentMusic;

    @OnlyIn(Dist.CLIENT)
    public static void playMusic(SoundEvent sound) {
        if (Minecraft.getInstance().player != null) {
            HuajiSoundPlayer.currentMusic = SimpleSoundInstance.forRecord(sound, Minecraft.getInstance().player.position());
            Minecraft.getInstance().getSoundManager().play(currentMusic);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void stopMusic(SoundEvent sound) {
        if (Minecraft.getInstance().player != null) {
            HuajiSoundPlayer.currentMusic = SimpleSoundInstance.forRecord(sound, Minecraft.getInstance().player.position());
            Minecraft.getInstance().getSoundManager().stop(currentMusic);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void playClient(SoundInstance sound) {
        Minecraft.getInstance().getSoundManager().play(sound);

    }

    @OnlyIn(Dist.CLIENT)
    public static void playMovingSoundClient(Entity target, SoundEvent sound, SoundSource category, float volume) {
        playClient(new HuajiMovingSoundInstance(sound, category, target).setVolume(volume));
    }

    public static HuajiMovingSoundInstance getMovingSound(Entity target, SoundEvent sound, SoundSource category, float volume) {
        HuajiMovingSoundInstance soundInstance = new HuajiMovingSoundInstance(sound, category, target);
        return soundInstance.setVolume(volume);
    }

    @OnlyIn(Dist.CLIENT)
    public static void playClient(Level world, double x, double y, double z, SoundEvent sound, SoundSource category, float volume, float pitch) {
        world.playSound(null, x, y, z, sound, category, volume, pitch);
    }
}
