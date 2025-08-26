package net.mrqx.huajiage.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrqx.huajiage.entity.EntityHeroArrow;

@OnlyIn(Dist.CLIENT)
public class RenderHeroArrow extends ThrownItemRenderer<EntityHeroArrow> {
    public RenderHeroArrow(EntityRendererProvider.Context pContext) {
        super(pContext, 1, true);
    }
}
