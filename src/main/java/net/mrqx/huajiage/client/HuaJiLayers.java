package net.mrqx.huajiage.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrqx.huajiage.HuaJiAgeMod;

@OnlyIn(Dist.CLIENT)
public class HuaJiLayers {
    public static final ModelLayerLocation ORGA_HAIR = create("orga_hair", "");
    public static final ModelLayerLocation FIFTY_FIFTY_HELMET = create("fifty_fifty_helmet", "");
    public static final ModelLayerLocation LORD_LU_WING = create("lord_lu_wing", "");
    public static final ModelLayerLocation LORD_LU = create("lord_lu", "");
    public static final ModelLayerLocation SHEER_HEART_ATTACK = create("sheer_heart_attack", "");
    public static final ModelLayerLocation MULTI_KNIFE = create("multi_knife", "");
    public static final ModelLayerLocation MULTI_KNIFE_SHINY = create("multi_knife_shiny", "");

    public static ModelLayerLocation create(String pPath, String pLayer) {
        return new ModelLayerLocation(HuaJiAgeMod.prefix(pPath), pLayer);
    }
}
