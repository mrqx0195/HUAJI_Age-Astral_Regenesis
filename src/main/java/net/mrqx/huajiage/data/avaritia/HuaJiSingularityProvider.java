package net.mrqx.huajiage.data.avaritia;

import committee.nova.mods.avaritia.init.data.provider.base.SingularityProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.mrqx.huajiage.compat.avaritia.HuaJiSingularities;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;

import java.util.concurrent.CompletableFuture;

public class HuaJiSingularityProvider extends SingularityProvider {
    public HuaJiSingularityProvider(DataGenerator generator, CompletableFuture<HolderLookup.Provider> lookupProvider, @MonotonicNonNull ExistingFileHelper existingFileHelper) {
        super(generator, lookupProvider, existingFileHelper);
    }

    @Override
    public void generate(HolderLookup.Provider provider, ExistingFileHelper existingFileHelper) {
        this.addSingularity(HuaJiSingularities.getHuaJiDefaultSingularities());
    }

    @Override
    public String getName() {
        return "HuaJiAge Singularities Data Provider";
    }
}
