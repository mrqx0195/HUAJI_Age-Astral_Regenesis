package net.mrqx.huajiage.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.BulkSectionAccess;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.mrqx.huajiage.data.worldgen.HuaJiConfiguredFeatures;
import net.mrqx.huajiage.registy.HuaJiBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.BitSet;
import java.util.Iterator;

@Mixin(OreFeature.class)
public abstract class MixinOreFeature {
    @Inject(method = "doPlace(Lnet/minecraft/world/level/WorldGenLevel;Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/level/levelgen/feature/configurations/OreConfiguration;DDDDDDIIIII)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/chunk/LevelChunkSection;setBlockState(IIILnet/minecraft/world/level/block/state/BlockState;Z)Lnet/minecraft/world/level/block/state/BlockState;",
                    shift = At.Shift.AFTER
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    @SuppressWarnings("all")
    private void injectDoPlace(WorldGenLevel pLevel, RandomSource pRandom, OreConfiguration pConfig,
                               double pMinX, double pMaxX, double pMinZ, double pMaxZ, double pMinY, double pMaxY,
                               int pX, int pY, int pZ, int pWidth, int pHeight, CallbackInfoReturnable<Boolean> cir,
                               int i, BitSet bitset, BlockPos.MutableBlockPos blockpos$mutableblockpos, int j, double[] adouble, BulkSectionAccess bulksectionaccess,
                               int j4, double d9, double d11, double d13, double d15, int k4, int l, int i1, int j1, int k1, int l1, int i2, double d5, int j2, double d6,
                               int k2, double d7, int l2, LevelChunkSection levelchunksection, int i3, int j3, int k3, BlockState blockstate, Iterator<?> var57,
                               OreConfiguration.TargetBlockState oreconfiguration$targetblockstate) {
        if (oreconfiguration$targetblockstate.state.getBlock().equals(HuaJiBlocks.HUAJI_ORE.get())
                && pConfig.size == HuaJiConfiguredFeatures.HUAJI_ORE_CONFIGURATION.size
                && pConfig.discardChanceOnAirExposure == HuaJiConfiguredFeatures.HUAJI_ORE_CONFIGURATION.discardChanceOnAirExposure
                && pRandom.nextDouble() < 0.5) {
            levelchunksection.setBlockState(i3, j3, k3, Blocks.GLOWSTONE.defaultBlockState(), false);
        }
    }
}
