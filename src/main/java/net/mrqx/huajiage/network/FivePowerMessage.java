package net.mrqx.huajiage.network;

import com.mega.endinglib.util.time.TimeStopUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.mrqx.huajiage.compat.HuaJiCompat;
import net.mrqx.huajiage.entity.EntityFivePower;
import net.mrqx.huajiage.registy.HuaJiEffects;
import net.mrqx.huajiage.registy.HuaJiItems;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class FivePowerMessage {
    public FivePowerMessage() {
    }

    @SuppressWarnings("InstantiationOfUtilityClass")
    public static FivePowerMessage decode(FriendlyByteBuf buf) {
        return new FivePowerMessage();
    }

    @SuppressWarnings("EmptyMethod")
    public static void encode(FivePowerMessage msg, FriendlyByteBuf buf) {
    }

    public static void handle(FivePowerMessage fivePowerMessage, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer sender = ctx.get().getSender();
            if (sender != null) {
                if (!sender.getCooldowns().isOnCooldown(HuaJiItems.FIFTY_FIFTY_HELMET.get())
                        && sender.hasEffect(HuaJiEffects.FIVE.get())) {
                    EntityFivePower fivePower = new EntityFivePower(sender, 0, 0, 0, sender.level());
                    fivePower.setIsDe(sender.level().random.nextBoolean());
                    fivePower.shootFromRotation(sender, sender.getXRot(), sender.getYRot(), 0, 1F, 0f);
                    fivePower.setPos(sender.getEyePosition());
                    if (TimeStopUtils.isTimeStop && TimeStopUtils.andSameDimension(sender.level())) {
                        fivePower.timeStopFix = true;
                    }
                    sender.level().addFreshEntity(fivePower);
                }
                HuaJiCompat.Factories.tryLordLuWingShoot(sender);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
