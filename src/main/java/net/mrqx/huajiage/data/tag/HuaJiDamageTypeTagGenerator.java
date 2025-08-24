package net.mrqx.huajiage.data.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.data.HuaJiDamageTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class HuaJiDamageTypeTagGenerator extends TagsProvider<DamageType> {
    public HuaJiDamageTypeTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, Registries.DAMAGE_TYPE, pLookupProvider, HuaJiAgeMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        tag(DamageTypeTags.BYPASSES_ARMOR)
                .add(HuaJiDamageTypes.ANTIMATTER)
                .add(HuaJiDamageTypes.KE_DAI_JIN_LA)
                .add(HuaJiDamageTypes.STELLA)
                .add(HuaJiDamageTypes.FIVE);

        tag(DamageTypeTags.BYPASSES_COOLDOWN)
                .add(HuaJiDamageTypes.ANTIMATTER)
                .add(HuaJiDamageTypes.KE_DAI_JIN_LA)
                .add(HuaJiDamageTypes.STELLA)
                .add(HuaJiDamageTypes.FIVE)
                .add(HuaJiDamageTypes.EMERALD_SPLASH)
                .add(HuaJiDamageTypes.STAND_HIT);

        tag(DamageTypeTags.BYPASSES_EFFECTS)
                .add(HuaJiDamageTypes.ANTIMATTER)
                .add(HuaJiDamageTypes.STELLA)
                .add(HuaJiDamageTypes.FIVE);

        tag(DamageTypeTags.BYPASSES_ENCHANTMENTS)
                .add(HuaJiDamageTypes.ANTIMATTER)
                .add(HuaJiDamageTypes.STELLA)
                .add(HuaJiDamageTypes.FIVE);

        tag(DamageTypeTags.BYPASSES_INVULNERABILITY)
                .add(HuaJiDamageTypes.ANTIMATTER)
                .add(HuaJiDamageTypes.FIVE);

        tag(DamageTypeTags.BYPASSES_RESISTANCE)
                .add(HuaJiDamageTypes.ANTIMATTER)
                .add(HuaJiDamageTypes.STELLA)
                .add(HuaJiDamageTypes.FIVE);

        tag(DamageTypeTags.BYPASSES_SHIELD)
                .add(HuaJiDamageTypes.ANTIMATTER)
                .add(HuaJiDamageTypes.KE_DAI_JIN_LA)
                .add(HuaJiDamageTypes.STELLA)
                .add(HuaJiDamageTypes.FIVE);
    }
}
