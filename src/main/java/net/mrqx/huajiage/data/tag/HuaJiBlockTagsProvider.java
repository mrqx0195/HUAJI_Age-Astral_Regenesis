package net.mrqx.huajiage.data.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.registy.HuaJiBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class HuaJiBlockTagsProvider extends BlockTagsProvider {
    public HuaJiBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, HuaJiAgeMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(
                HuaJiBlocks.HUAJI_ORE.get(),
                HuaJiBlocks.HUAJI_STAR_BLOCK.get(),
                HuaJiBlocks.AIRSPACE_STAR_BLOCK.get(),
                HuaJiBlocks.HUAJI_BLENDER.get(),
                HuaJiBlocks.HUAJI_POLYFURNACE.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                HuaJiBlocks.HUAJI_ORE.get(),
                HuaJiBlocks.HUAJI_STAR_BLOCK.get(),
                HuaJiBlocks.AIRSPACE_STAR_BLOCK.get(),
                HuaJiBlocks.HUAJI_BLENDER.get(),
                HuaJiBlocks.HUAJI_POLYFURNACE.get());
        tag(BlockTags.BEACON_BASE_BLOCKS).add(
                HuaJiBlocks.HUAJI_ORE.get(),
                HuaJiBlocks.HUAJI_STAR_BLOCK.get(),
                HuaJiBlocks.AIRSPACE_STAR_BLOCK.get());
    }
}
