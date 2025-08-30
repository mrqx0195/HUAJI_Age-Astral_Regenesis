package net.mrqx.huajiage.mixin;

import com.mega.endinglib.util.time.TimeStopUtils;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.registries.tags.ITagManager;
import net.mrqx.huajiage.capability.stand.StandDataCapabilityProvider;
import net.mrqx.huajiage.data.tag.stand.StandTags;
import net.mrqx.huajiage.registy.HuaJiStands;
import net.mrqx.huajiage.stand.Stand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TimeStopUtils.class)
public abstract class MixinTimeStopUtils {
    @Inject(method = "canMove(Lnet/minecraft/world/entity/Entity;)Z", at = @At("HEAD"), remap = false, cancellable = true)
    private static void injectCanMove(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        entity.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
            Stand stand = Stand.getStand(data.getStand());
            ITagManager<Stand> tags = HuaJiStands.REGISTRY.get().tags();
            if (tags != null && tags.getTag(StandTags.TIME_STOP).contains(stand)) {
                cir.setReturnValue(true);
            }
        });
    }
}
