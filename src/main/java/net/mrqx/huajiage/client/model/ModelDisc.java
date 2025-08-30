package net.mrqx.huajiage.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.mrqx.huajiage.item.stand.ItemDisc;

import javax.annotation.Nullable;
import java.util.List;

/**
 * This class is based on the original work from Apotheosis by Shadows-of-Fire.
 * <p>
 * Original source: <a href="https://github.com/Shadows-of-Fire/Apotheosis/blob/1.20/src/main/java/dev/shadowsoffire/apotheosis/adventure/client/GemModel.java">Shadows-of-Fire/Apotheosis/.../GemModel.java</a>
 * <p>
 * License: <a href="https://github.com/Shadows-of-Fire/Apotheosis/blob/1.20/LICENSE">MIT License</a>
 *
 * @author Shadows-of-Fire
 */
@SuppressWarnings("deprecation")
public class ModelDisc implements BakedModel {
    private final BakedModel original;
    private final ItemOverrides overrides;

    public ModelDisc(BakedModel original) {
        this.original = original;
        this.overrides = new ItemOverrides() {
            @Override
            public BakedModel resolve(BakedModel original, ItemStack stack, @Nullable ClientLevel world, @Nullable LivingEntity entity, int seed) {
                if (stack.hasTag()) {
                    ResourceLocation resourceLocation = getModelFromItemStack(stack);
                    ModelManager manager = Minecraft.getInstance().getModelManager();
                    BakedModel missing = manager.getModel(ModelBakery.MISSING_MODEL_LOCATION);
                    BakedModel model;
                    if (resourceLocation != null) {
                        model = manager.getModel(resourceLocation);
                        return model == missing ? original : model;
                    }
                }
                return original;
            }
        };
    }

    public static ResourceLocation getModelResourceLocation(ResourceLocation resourceLocation) {
        return new ResourceLocation(resourceLocation.getNamespace(), "item/disc/disc_" + resourceLocation.getPath());
    }

    @Nullable
    public static ResourceLocation getModelFromItemStack(ItemStack stack) {
        ResourceLocation resourceLocation = ItemDisc.getStandResourceLocation(stack);
        if (resourceLocation != null) {
            return new ResourceLocation(resourceLocation.getNamespace(), "item/disc/disc_" + resourceLocation.getPath());
        } else {
            return null;
        }
    }

    @Override
    public ItemOverrides getOverrides() {
        return this.overrides;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState pState, @Nullable Direction pDirection, RandomSource pRandom) {
        return this.original.getQuads(pState, pDirection, pRandom);
    }

    @Override
    public boolean useAmbientOcclusion() {
        return this.original.useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return this.original.isGui3d();
    }

    @Override
    public boolean usesBlockLight() {
        return this.original.usesBlockLight();
    }

    @Override
    public boolean isCustomRenderer() {
        return this.original.isCustomRenderer();
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return this.original.getParticleIcon();
    }

    @Override
    public ItemTransforms getTransforms() {
        return this.original.getTransforms();
    }
}
