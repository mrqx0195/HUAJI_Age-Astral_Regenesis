package net.mrqx.huajiage.compat.apotheosis.attribute;

import dev.shadowsoffire.attributeslib.util.IEntityOwned;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;

public class ApothicAttributesFactory {
    public static void setAbilitiesOwner(Abilities abilities, Player player) {
        ((IEntityOwned) abilities).setOwner(player);
    }
}
