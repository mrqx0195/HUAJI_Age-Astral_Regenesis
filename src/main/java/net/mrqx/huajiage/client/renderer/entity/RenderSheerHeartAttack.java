package net.mrqx.huajiage.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.client.HuaJiLayers;
import net.mrqx.huajiage.client.model.entity.ModelSheerHeartAttack;
import net.mrqx.huajiage.entity.EntitySheerHeartAttack;

public class RenderSheerHeartAttack extends MobRenderer<EntitySheerHeartAttack, ModelSheerHeartAttack<EntitySheerHeartAttack>> {
    private static final ResourceLocation TEXTURE = HuaJiAgeMod.prefix("textures/entity/entity_sheer_heart_attack.png");

    public RenderSheerHeartAttack(EntityRendererProvider.Context context) {
        super(context, new ModelSheerHeartAttack<>(context.bakeLayer(HuaJiLayers.SHEER_HEART_ATTACK)), 0.6F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntitySheerHeartAttack entity) {
        return TEXTURE;
    }
}
