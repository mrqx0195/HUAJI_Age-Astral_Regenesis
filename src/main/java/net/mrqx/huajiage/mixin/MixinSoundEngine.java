package net.mrqx.huajiage.mixin;

import com.mega.endinglib.util.time.TimeStopUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.sounds.SoundEngine;
import net.mrqx.huajiage.client.event.ShaderHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundEngine.class)
public abstract class MixinSoundEngine {
    @Shadow
    protected abstract void tickNonPaused();

    @Inject(method = "tick(Z)V", at = @At("TAIL"))
    private void injectTick(boolean isGamePaused, CallbackInfo ci) {
        Minecraft minecraft = Minecraft.getInstance();
        if (isGamePaused && minecraft.level != null && minecraft.player != null && Minecraft.renderNames() && !(minecraft.screen instanceof PauseScreen)) {
            ShaderHandler.TIME_STOP_EFFECT_TICK.forEach((level, startTick) -> {
                if (minecraft.level.dimension().location().equals(level)) {
                    if (TimeStopUtils.isTimeStop && TimeStopUtils.canMove(minecraft.player) && TimeStopUtils.andSameDimension(minecraft.level)) {
                        if (ShaderHandler.TIME_STOPER.get(level).equals(minecraft.player.getId())) {
                            this.tickNonPaused();
                        }
                    }
                }
            });
        }
    }
}
