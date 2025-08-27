package net.mrqx.huajiage.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.resources.sounds.TickableSoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;
import net.mrqx.huajiage.network.HuaJiSoundMessage;
import net.mrqx.huajiage.network.NetworkManager;
import net.mrqx.huajiage.sound.HuajiMovingSoundInstance;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HuajiSoundPlayer {
    @Nullable
    private static SoundInstance currentMusic;
    public static final List<TickableSoundInstance> STORAGE_TICKABLE_SOUNDS = new ArrayList<>();

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
        if (sound instanceof TickableSoundInstance tickableSoundInstance) {
            STORAGE_TICKABLE_SOUNDS.add(tickableSoundInstance);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void clientSoundsTick() {
        HuajiSoundPlayer.STORAGE_TICKABLE_SOUNDS.forEach(TickableSoundInstance::tick);
        HuajiSoundPlayer.STORAGE_TICKABLE_SOUNDS.removeIf(TickableSoundInstance::isStopped);
    }

    public static void playMovingSoundToClient(Entity target, SoundEvent sound, SoundSource category) {
        playMovingSoundToClient(target, sound, category, 1, 1);
    }

    public static void playMovingSoundToClient(Entity target, SoundEvent sound, SoundSource category, float volume) {
        playMovingSoundToClient(target, sound, category, volume, 1);
    }

    public static void playMovingSoundToClient(Entity target, SoundEvent sound, SoundSource category, float volume, float pitch) {
        if (!target.level().isClientSide) {
            HuaJiSoundMessage message = new HuaJiSoundMessage();
            message.soundEvent = sound.getLocation();
            message.source = category;
            message.entityId = target.getId();
            message.volume = volume;
            message.pitch = pitch;
            target.level().players().forEach(player -> {
                if (player instanceof ServerPlayer serverPlayer) {
                    NetworkManager.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer), message);
                }
            });
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static PentaConsumer<ResourceLocation, SoundSource, Integer, Float, Float> playMovingSoundClient() {
        return (sound, category, target, volume, pitch) -> {
            if (Minecraft.getInstance().level != null) {
                Entity entity = Minecraft.getInstance().level.getEntity(target);
                if (entity != null) {
                    SoundEvent soundEvent = ForgeRegistries.SOUND_EVENTS.getValue(sound);
                    if (soundEvent != null) {
                        playClient(getMovingSound(entity, soundEvent, category, volume, pitch));
                    }
                }
            }
        };
    }

    public static HuajiMovingSoundInstance getMovingSound(Entity entity, SoundEvent soundEvent, SoundSource category) {
        HuajiMovingSoundInstance soundInstance = new HuajiMovingSoundInstance(soundEvent, category, entity);
        soundInstance.tick();
        return soundInstance;
    }

    public static HuajiMovingSoundInstance getMovingSound(Entity entity, SoundEvent soundEvent, SoundSource category, float volume) {
        return getMovingSound(entity, soundEvent, category).setVolume(volume);
    }

    public static HuajiMovingSoundInstance getMovingSound(Entity entity, SoundEvent soundEvent, SoundSource category, float volume, float pitch) {
        return getMovingSound(entity, soundEvent, category).setVolume(volume).setPitch(pitch);
    }
}
