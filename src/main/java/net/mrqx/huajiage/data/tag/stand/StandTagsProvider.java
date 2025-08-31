package net.mrqx.huajiage.data.tag.stand;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.mrqx.huajiage.registy.HuaJiStands;
import net.mrqx.huajiage.stand.Stand;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class StandTagsProvider extends IntrinsicHolderTagsProvider<Stand> {
    public StandTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, Stand.REGISTRY_KEY, provider, stand -> HuaJiStands.REGISTRY.get().getResourceKey(stand).orElseThrow(), modId, existingFileHelper);
    }

    @Override
    @SuppressWarnings("DataFlowIssue")
    protected void addTags(HolderLookup.Provider provider) {
        tag(StandTags.TIME_STOP).add(HuaJiStands.THE_WORLD.getKey(), HuaJiStands.STAR_PLATINUM.getKey());
        tag(StandTags.CLOSE_RANGE_POWER).add(HuaJiStands.STAR_PLATINUM.getKey(), HuaJiStands.ORGA_REQUIEM.getKey(), HuaJiStands.KILLER_QUEEN.getKey());
        tag(StandTags.LONG_DISTANCE_OPERATION).add(HuaJiStands.HIEROPHANT_GREEN.getKey());
        tag(StandTags.RANGE_IRRELEVANT).add(HuaJiStands.THE_WORLD.getKey(), HuaJiStands.STAR_PLATINUM.getKey());
        tag(StandTags.AUTOMATIC).add(HuaJiStands.KILLER_QUEEN.getKey());
        tag(StandTags.REQUIEM).add(HuaJiStands.ORGA_REQUIEM.getKey());
    }
}
