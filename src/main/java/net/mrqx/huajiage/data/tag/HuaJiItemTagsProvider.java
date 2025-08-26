package net.mrqx.huajiage.data.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.block.blockentity.HuaJiBlenderBlockEntity;
import net.mrqx.huajiage.block.blockentity.HuaJiPolyfurnaceBlockEntity;
import net.mrqx.huajiage.registy.HuaJiItems;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class HuaJiItemTagsProvider extends ItemTagsProvider {
    public HuaJiItemTagsProvider(PackOutput pGenerator, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> provider,
                                 ExistingFileHelper existingFileHelper) {
        super(pGenerator, lookupProvider, provider, HuaJiAgeMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        tag(ItemTags.create(HuaJiAgeMod.prefix(HuaJiBlenderBlockEntity.HUAJI_BLENDER_TIME_PREFIX + "100"))).add(HuaJiItems.HUAJI.get());
        tag(ItemTags.create(HuaJiAgeMod.prefix(HuaJiPolyfurnaceBlockEntity.HUAJI_POLYFURNACE_TIME_PREFIX + "100"))).add(HuaJiItems.HUAJI.get());
        tag(ItemTags.SWORDS).add(HuaJiItems.HUAJI_SWORD.get());
        tag(ItemTags.SWORDS).add(HuaJiItems.HUAJI_STAR_SWORD.get());
        tag(ItemTags.SWORDS).add(HuaJiItems.HUAJI_LATIAO_SWORD.get());
        tag(ItemTags.SWORDS).add(HuaJiItems.EXGLUTENBUR.get());
    }
}
