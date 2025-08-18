package net.mrqx.huajiage.data.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.registy.HuaJiBlocks;

public class HuaJiBlockStateGenerator extends BlockStateProvider {
    private static final ImmutableList<Block> SIMPLE_BLOCK_LIST = ImmutableList.of(
            HuaJiBlocks.HUAJI_ORE.get(),
            HuaJiBlocks.HUAJI_STAR_BLOCK.get(),
            HuaJiBlocks.AIRSPACE_STAR_BLOCK.get(),
            HuaJiBlocks.HUAJI_BOMB.get(),
            HuaJiBlocks.HUAJI_ANTIMATTER_BOMB.get()
    );

    public HuaJiBlockStateGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, HuaJiAgeMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        SIMPLE_BLOCK_LIST.forEach(block -> simpleBlockWithItem(block, cubeAll(block)));
    }
}
