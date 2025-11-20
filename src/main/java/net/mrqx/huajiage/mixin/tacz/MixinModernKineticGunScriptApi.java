package net.mrqx.huajiage.mixin.tacz;

import com.mega.endinglib.util.annotation.ModDependsMixin;
import com.tacz.guns.api.entity.IGunOperator;
import com.tacz.guns.api.event.common.GunFireEvent;
import com.tacz.guns.api.item.gun.AbstractGunItem;
import com.tacz.guns.api.item.gun.FireMode;
import com.tacz.guns.config.common.AmmoConfig;
import com.tacz.guns.entity.EntityKineticBullet;
import com.tacz.guns.entity.shooter.ShooterDataHolder;
import com.tacz.guns.item.ModernKineticGunScriptAPI;
import com.tacz.guns.network.NetworkHandler;
import com.tacz.guns.network.message.event.ServerMessageGunFire;
import com.tacz.guns.resource.index.CommonGunIndex;
import com.tacz.guns.resource.modifier.AttachmentCacheProperty;
import com.tacz.guns.resource.modifier.custom.AmmoSpeedModifier;
import com.tacz.guns.resource.modifier.custom.InaccuracyModifier;
import com.tacz.guns.resource.modifier.custom.SilenceModifier;
import com.tacz.guns.resource.pojo.data.gun.BulletData;
import com.tacz.guns.resource.pojo.data.gun.GunData;
import com.tacz.guns.resource.pojo.data.gun.GunHeatData;
import com.tacz.guns.resource.pojo.data.gun.InaccuracyType;
import com.tacz.guns.sound.SoundManager;
import com.tacz.guns.util.CycleTaskHelper;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.LogicalSide;
import net.mrqx.huajiage.compat.tacz.IHuaJiGunScriptApi;
import org.joml.Vector3f;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@SuppressWarnings({"AlibabaLowerCamelCaseVariableNaming", "AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc"})
@Mixin(ModernKineticGunScriptAPI.class)
@ModDependsMixin("tacz")
public abstract class MixinModernKineticGunScriptApi implements IHuaJiGunScriptApi {
    @Shadow(remap = false)
    private LivingEntity shooter;
    @Shadow(remap = false)
    private ShooterDataHolder dataHolder;
    @Shadow(remap = false)
    private ItemStack itemStack;
    @Shadow(remap = false)
    private AbstractGunItem abstractGunItem;
    @Shadow(remap = false)
    private CommonGunIndex gunIndex;
    @Shadow(remap = false)
    private ResourceLocation gunId;
    @Shadow(remap = false)
    private ResourceLocation gunDisplayId;
    @Shadow(remap = false)
    private Supplier<Float> pitchSupplier;
    @Shadow(remap = false)
    private Supplier<Float> yawSupplier;

    @Shadow(remap = false)
    protected abstract LuaFunction checkFunction(LuaValue luaValue);

    @Shadow(remap = false)
    public abstract void handleShootHeat();

    @Shadow(remap = false)
    public abstract float getHeatAmount();

    @Shadow(remap = false)
    public abstract boolean hasHeatData();

    @SuppressWarnings("unchecked")
    @Override
    public void huajiAge$shootOnceUnchecked() {
        GunData gunData = this.gunIndex.getGunData();
        BulletData bulletData = this.gunIndex.getBulletData();
        IGunOperator gunOperator = IGunOperator.fromLivingEntity(this.shooter);
        AttachmentCacheProperty cacheProperty = gunOperator.getCacheProperty();
        if (cacheProperty != null) {
            float heatInaccuracy = 1.0F;
            if (this.hasHeatData()) {
                GunHeatData heatData = this.gunIndex.getGunData().getHeatData();
                float heatPercentage = 0;
                if (heatData != null) {
                    heatPercentage = this.getHeatAmount() / heatData.getHeatMax();
                }
                if (heatData != null) {
                    heatInaccuracy *= Mth.lerp(heatPercentage, heatData.getMinInaccuracy(), heatData.getMaxInaccuracy());
                }
            }

            InaccuracyType inaccuracyType = InaccuracyType.getInaccuracyType(this.shooter);
            float inaccuracy = Math.max(0.0F, ((Map<InaccuracyType, Float>) cacheProperty.getCache(InaccuracyModifier.ID)).get(inaccuracyType) * heatInaccuracy);
            Pair<Integer, Boolean> silence = cacheProperty.getCache(SilenceModifier.ID);
            int soundDistance = silence.first();
            boolean useSilenceSound = silence.right();
            float speed = cacheProperty.getCache(AmmoSpeedModifier.ID);
            speed = (float) ((double) speed * AmmoConfig.GLOBAL_BULLET_SPEED_MODIFIER.get());
            float processedSpeed = Mth.clamp(speed / 20.0F, 0.0F, Float.MAX_VALUE);
            int bulletAmount = Math.max(bulletData.getBulletAmount(), 1);
            FireMode fireMode = this.abstractGunItem.getFireMode(this.itemStack);
            int cycles = fireMode == FireMode.BURST ? gunData.getBurstData().getCount() : 1;
            long period = fireMode == FireMode.BURST ? gunData.getBurstShootInterval() : 1L;
            CycleTaskHelper.addCycleTask(() -> {
                if (this.shooter.isDeadOrDying()) {
                    return false;
                } else {
                    boolean fire = !MinecraftForge.EVENT_BUS.post(new GunFireEvent(this.shooter, this.itemStack, LogicalSide.SERVER));
                    if (fire) {
                        NetworkHandler.sendToTrackingEntity(new ServerMessageGunFire(this.shooter.getId(), this.itemStack), this.shooter);

                        if (this.gunIndex.getGunData().hasHeatData()) {
                            Optional.ofNullable(this.gunIndex.getScript())
                                    .map((script) -> this.checkFunction(script.get("handle_shoot_heat")))
                                    .ifPresentOrElse((func) -> func.call(CoerceJavaToLua.coerce(this)), this::handleShootHeat);
                        }

                        float pitch = this.pitchSupplier != null ? this.pitchSupplier.get() : this.shooter.getXRot();
                        float yaw = this.yawSupplier != null ? this.yawSupplier.get() : this.shooter.getYRot();
                        Level world = this.shooter.level();
                        ResourceLocation ammoId = gunData.getAmmoId();

                        for (int i = 0; i < bulletAmount; ++i) {
                            boolean isTracer = bulletData.hasTracerAmmo() && gunOperator.nextBulletIsTracer(bulletData.getTracerCountInterval());
                            EntityKineticBullet bullet = new EntityKineticBullet(world, this.shooter, this.itemStack, ammoId, this.gunId, this.gunDisplayId, isTracer, gunData, bulletData);
                            this.abstractGunItem.doBulletSpread(this.dataHolder, this.itemStack, this.shooter, bullet, i, processedSpeed, inaccuracy, pitch, yaw);
                            bullet.setFirstPersonRenderOffset(new Vector3f());
                            world.addFreshEntity(bullet);
                        }

                        if (soundDistance > 0) {
                            String soundId = useSilenceSound ? SoundManager.SILENCE_3P_SOUND : SoundManager.SHOOT_3P_SOUND;
                            SoundManager.sendSoundToNearby(this.shooter, soundDistance, this.gunId, this.gunDisplayId, soundId, 0.8F, 0.9F + this.shooter.getRandom().nextFloat() * 0.125F);
                        }
                    }

                    return true;
                }
            }, period, cycles);
        }
    }
}
