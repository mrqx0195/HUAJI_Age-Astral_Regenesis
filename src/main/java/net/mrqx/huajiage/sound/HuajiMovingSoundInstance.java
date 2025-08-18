package net.mrqx.huajiage.sound;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HuajiMovingSoundInstance extends AbstractTickableSoundInstance {
    protected final Entity entity;

    public HuajiMovingSoundInstance(SoundEvent soundEvent, SoundSource soundSource, Entity entity) {
        super(soundEvent, soundSource, entity.level().random);
        this.entity = entity;
    }

    public HuajiMovingSoundInstance setVolume(float volume) {
        this.volume = volume;
        return this;
    }

    public HuajiMovingSoundInstance setLoop() {
        this.looping = true;
        return this;
    }

    @Override
    public void tick() {
        this.x = (float) entity.getX();
        this.y = (float) entity.getY();
        this.z = (float) entity.getZ();
        if (!entity.isAlive()) {
            this.stop();
        }
    }
}
