package net.mrqx.huajiage.data.avaritia;

import com.google.gson.JsonObject;
import committee.nova.mods.avaritia.Const;
import committee.nova.mods.avaritia.core.singularity.Singularity;
import committee.nova.mods.avaritia.init.data.provider.ModSingularityProvider;
import committee.nova.mods.avaritia.util.SingularityUtils;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.mrqx.huajiage.compat.avaritia.HuaJiSingularities;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

/**
 * @see ModSingularityProvider
 */
public class HuaJiSingularityProvider implements DataProvider {
    private final PackOutput.PathProvider pathProvider;
    private final Map<ResourceLocation, Singularity> singularities = new TreeMap<>();

    public HuaJiSingularityProvider(PackOutput packOutput) {
        this.pathProvider = packOutput.createPathProvider(PackOutput.Target.DATA_PACK, "singularities");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        List<CompletableFuture<?>> list = new ArrayList<>();
        this.singularities.clear();
        this.collectDefaultSingularities();

        for (Map.Entry<ResourceLocation, Singularity> entry : this.singularities.entrySet()) {
            ResourceLocation id = entry.getKey();
            Singularity singularity = entry.getValue();
            JsonObject json = SingularityUtils.writeToJson(singularity);
            Path path = this.pathProvider.json(id);
            list.add(DataProvider.saveStable(output, json, path));
        }

        Const.LOGGER.info("Generated {} singularity data files", this.singularities.size());
        return CompletableFuture.allOf(list.toArray(CompletableFuture[]::new));
    }

    private void collectDefaultSingularities() {
        for (Singularity singularity : HuaJiSingularities.getHuaJiDefaultSingularities()) {
            this.singularities.put(singularity.getId(), singularity);
        }

        Const.LOGGER.debug("Collected {} default singularities for data generation", this.singularities.size());
    }

    @Override
    public String getName() {
        return "HuaJiAge Singularities Data Provider";
    }
}
