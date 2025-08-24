package net.mrqx.huajiage.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;
import net.mrqx.huajiage.network.HuaJiSoundMessage;
import net.mrqx.huajiage.network.NetworkManager;
import net.mrqx.huajiage.sound.HuajiMovingSoundInstance;
import org.jetbrains.annotations.Nullable;

public class HuajiSoundPlayer {
    @Nullable
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

    public static void playMovingSoundToClient(Entity target, SoundEvent sound, SoundSource category, float volume) {
        target.level().players().forEach(player -> {
            if (player instanceof ServerPlayer serverPlayer) {
                HuaJiSoundMessage message = new HuaJiSoundMessage();
                message.soundEvent = sound.getLocation();
                message.source = category;
                message.entityId = target.getId();
                message.volume = volume;
                NetworkManager.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer), message);
            }
        });
    }

    @OnlyIn(Dist.CLIENT)
    public static QuadConsumer<ResourceLocation, SoundSource, Integer, Float> playMovingSoundClient() {
        return (sound, category, target, volume) -> {
            if (Minecraft.getInstance().level != null) {
                Entity entity = Minecraft.getInstance().level.getEntity(target);
                if (entity != null) {
                    SoundEvent soundEvent = ForgeRegistries.SOUND_EVENTS.getValue(sound);
                    if (soundEvent != null) {
                        playClient(new HuajiMovingSoundInstance(soundEvent, category, entity).setVolume(volume));
                    }
                }
            }
        };
    }

    public static HuajiMovingSoundInstance getMovingSound(Entity target, SoundEvent sound, SoundSource category, float volume) {
        HuajiMovingSoundInstance soundInstance = new HuajiMovingSoundInstance(sound, category, target);
        return soundInstance.setVolume(volume);
    }

    @OnlyIn(Dist.CLIENT)
    public static void playClient(Level world, double x, double y, double z, SoundEvent sound, SoundSource category, float volume, float pitch) {
        world.playSound(null, x, y, z, sound, category, volume, pitch);
    }

    @FunctionalInterface
    public interface QuadConsumer<T, U, V, W> {
        void accept(T var1, U var2, V var3, W var4);

        default QuadConsumer<T, U, V, W> andThen(QuadConsumer<? super T, ? super U, ? super V, ? super W> after) {
            return (t, u, v, w) -> {
                this.accept(t, u, v, w);
                after.accept(t, u, v, w);
            };
        }
    }
}
