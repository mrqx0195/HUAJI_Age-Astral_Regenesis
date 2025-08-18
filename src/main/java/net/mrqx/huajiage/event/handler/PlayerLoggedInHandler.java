package net.mrqx.huajiage.event.handler;

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class PlayerLoggedInHandler {
    private static boolean notFirst = false;

    @SubscribeEvent
    public static void onPlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
        boolean missingPatchouli = !ModList.get().isLoaded("patchouli");
        if (notFirst) {
            return;
        }
        if (missingPatchouli) {
            event.getEntity().sendSystemMessage(Objects.requireNonNull(Component.Serializer.fromJsonLenient(Component.translatable("message.huajiage.patchouli_missing").getString())));
        }

        notFirst = true;
    }
}
