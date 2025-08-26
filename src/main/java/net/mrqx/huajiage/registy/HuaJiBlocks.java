package net.mrqx.huajiage.registy;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.block.BlockHuaJiAntimatterBomb;
import net.mrqx.huajiage.block.BlockHuaJiBlender;
import net.mrqx.huajiage.block.BlockHuaJiBomb;
import net.mrqx.huajiage.block.BlockHuaJiPolyfurnace;
import net.mrqx.huajiage.block.blockentity.HuaJiBlenderBlockEntity;
import net.mrqx.huajiage.block.blockentity.HuaJiPolyfurnaceBlockEntity;

public class HuaJiBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, HuaJiAgeMod.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, HuaJiAgeMod.MODID);

    public static final RegistryObject<Block> HUAJI_ORE = BLOCKS.register("ore_huaji", () -> new DropExperienceBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(23.3F, 23.3F)));
    public static final RegistryObject<Block> HUAJI_STAR_BLOCK = BLOCKS.register("huaji_star_block", () -> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.DIAMOND).instrument(NoteBlockInstrument.BELL).requiresCorrectToolForDrops().strength(2.8F, 6.66F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> AIRSPACE_STAR_BLOCK = BLOCKS.register("airspace_star_block", () -> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BELL).requiresCorrectToolForDrops().strength(6.6F, 66.6F).sound(SoundType.METAL)));

    /**
     * Boooooooooom!
     */
    public static final RegistryObject<Block> HUAJI_BOMB = BLOCKS.register("huaji_bomb", () -> new BlockHuaJiBomb(BlockBehaviour.Properties.of()
            .mapColor(MapColor.FIRE).instabreak().sound(SoundType.GRASS).ignitedByLava().isRedstoneConductor((blockState, blockGetter, blockPos) -> false)));
    public static final RegistryObject<Block> HUAJI_ANTIMATTER_BOMB = BLOCKS.register("huaji_antimatter_bomb", () -> new BlockHuaJiAntimatterBomb(BlockBehaviour.Properties.of()
            .mapColor(MapColor.METAL).instabreak().sound(SoundType.GRASS).ignitedByLava().isRedstoneConductor((blockState, blockGetter, blockPos) -> false)));

    /**
     * Machines
     */
    public static final RegistryObject<Block> HUAJI_BLENDER = BLOCKS.register("huaji_blender", () -> new BlockHuaJiBlender(BlockBehaviour.Properties.of()
            .mapColor(MapColor.METAL).requiresCorrectToolForDrops().strength(2, 10).sound(SoundType.STONE)
            .lightLevel(blockState -> blockState.getValue(BlockHuaJiBlender.LIT) ? 15 : 8)
            .isRedstoneConductor((blockState, blockGetter, blockPos) -> false))
    );
    public static final RegistryObject<Block> HUAJI_POLYFURNACE = BLOCKS.register("huaji_polyfurnace", () -> new BlockHuaJiPolyfurnace(BlockBehaviour.Properties.of()
            .mapColor(MapColor.METAL).requiresCorrectToolForDrops().strength(28, 1437).sound(SoundType.ANVIL)
            .lightLevel(blockState -> blockState.getValue(BlockHuaJiBlender.LIT) ? 15 : 0)
            .isRedstoneConductor((blockState, blockGetter, blockPos) -> false)
            .noOcclusion()
            .isSuffocating((blockState, blockGetter, blockPos) -> false)
            .isViewBlocking((blockState, blockGetter, blockPos) -> false))
    );

    /**
     * Block Entities
     */
    @SuppressWarnings("DataFlowIssue")
    public static final RegistryObject<BlockEntityType<?>> HUAJI_BLENDER_BLOCK_ENTITY = BLOCK_ENTITIES
            .register("huaji_blender", () -> BlockEntityType.Builder.of(HuaJiBlenderBlockEntity::new, HUAJI_BLENDER.get()).build(null));
    @SuppressWarnings("DataFlowIssue")
    public static final RegistryObject<BlockEntityType<?>> HUAJI_POLYFURNACE_BLOCK_ENTITY = BLOCK_ENTITIES
            .register("huaji_polyfurnace", () -> BlockEntityType.Builder.of(HuaJiPolyfurnaceBlockEntity::new, HUAJI_POLYFURNACE.get()).build(null));
}
