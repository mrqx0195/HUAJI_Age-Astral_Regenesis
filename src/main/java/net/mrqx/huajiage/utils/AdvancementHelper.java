package net.mrqx.huajiage.utils;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.mrqx.huajiage.HuaJiAgeMod;

/**
 * This class is based on the original work from SlashBlade_Resharped by 0999312.
 * <p>
 * Original source: <a href="https://github.com/0999312/SlashBlade_Resharped/blob/master/src/main/java/mods/flammpfeil/slashblade/util/AdvancementHelper.java">0999312/SlashBlade_Resharped/.../AdvancementHelper.java</a>
 * <p>
 * License: <a href="https://github.com/0999312/SlashBlade_Resharped/blob/master/LICENSE">MIT License</a>
 *
 * @author 0999312
 */
public class AdvancementHelper {
    public static final ResourceLocation LORD_CORE = HuaJiAgeMod.prefix("lord_core");
    public static final ResourceLocation LORD_KEY = HuaJiAgeMod.prefix("lord_key");
    public static final ResourceLocation STELLA = HuaJiAgeMod.prefix("stella");
    public static final ResourceLocation STAND = HuaJiAgeMod.prefix("stand");
    public static final ResourceLocation SINGULARITY = HuaJiAgeMod.prefix("singularity");
    public static final ResourceLocation ARROW_REQUIEM = HuaJiAgeMod.prefix("arrow_requiem");
    public static final ResourceLocation ORGA_STAND = HuaJiAgeMod.prefix("orga_stand");

    public static void grantCriterion(LivingEntity entity, ResourceLocation resourcelocation) {
        if (entity instanceof ServerPlayer serverPlayer) {
            grantCriterion(serverPlayer, resourcelocation);
        }
    }

    public static void grantCriterion(ServerPlayer player, ResourceLocation resourcelocation) {
        MinecraftServer server = player.getServer();
        Advancement adv = null;
        if (server != null) {
            adv = server.getAdvancements().getAdvancement(resourcelocation);
        }
        if (adv == null) {
            return;
        }

        AdvancementProgress advancementprogress = player.getAdvancements().getOrStartProgress(adv);
        if (advancementprogress.isDone()) {
            return;
        }

        for (String s : advancementprogress.getRemainingCriteria()) {
            player.getAdvancements().award(adv, s);
        }
    }
}
