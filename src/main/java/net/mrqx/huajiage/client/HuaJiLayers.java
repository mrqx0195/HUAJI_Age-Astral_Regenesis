package net.mrqx.huajiage.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrqx.huajiage.HuaJiAgeMod;

@OnlyIn(Dist.CLIENT)
public class HuaJiLayers {
    public static final ModelLayerLocation ORGA_HAIR = create("orga_hair", "");
    public static final ModelLayerLocation FIFTY_FIFTY_HELMET = create("fifty_fifty_helmet", "");
    public static final ModelLayerLocation LORD_LU = create("lord_lu", "");

    public static ModelLayerLocation create(String pPath, String pLayer) {
        return new ModelLayerLocation(new ResourceLocation(HuaJiAgeMod.MODID, pPath), pLayer);
    }
}
