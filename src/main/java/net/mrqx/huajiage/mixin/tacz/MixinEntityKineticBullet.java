package net.mrqx.huajiage.mixin.tacz;

import com.tacz.guns.entity.EntityKineticBullet;
import net.minecraft.network.FriendlyByteBuf;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
@Mixin(EntityKineticBullet.class)
public abstract class MixinEntityKineticBullet {
    @Shadow(remap = false)
    public abstract Vector3f getFirstPersonRenderOffset();

    @Shadow(remap = false)
    public abstract void setFirstPersonRenderOffset(Vector3f originRenderOffset);

    @Inject(method = "writeSpawnData(Lnet/minecraft/network/FriendlyByteBuf;)V", at = @At("TAIL"), remap = false)
    private void injectWriteSpawnData(FriendlyByteBuf buffer, CallbackInfo ci) {
        Vector3f vector3f = getFirstPersonRenderOffset();
        buffer.writeVector3f(vector3f == null ? new Vector3f(Float.MAX_VALUE) : vector3f);
    }

    @Inject(method = "readSpawnData(Lnet/minecraft/network/FriendlyByteBuf;)V", at = @At("TAIL"), remap = false)
    private void injectReadSpawnData(FriendlyByteBuf buffer, CallbackInfo ci) {
        Vector3f vector3f = buffer.readVector3f();
        setFirstPersonRenderOffset(vector3f.equals(new Vector3f(Float.MAX_VALUE)) ? null : vector3f);
    }
}
