package net.mrqx.huajiage.data.slashblade;

import mods.flammpfeil.slashblade.client.renderer.CarryType;
import mods.flammpfeil.slashblade.item.SwordType;
import mods.flammpfeil.slashblade.registry.SlashArtsRegistry;
import mods.flammpfeil.slashblade.registry.slashblade.EnchantmentDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.PropertiesDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.RenderDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.SlashBladeDefinition;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.compat.slashblade.registy.HuaJiSlashArts;
import net.mrqx.huajiage.compat.slashblade.registy.HuaJiSpecialEffects;
import net.mrqx.huajiage.registy.HuaJiCreativeTabs;

import java.util.List;

public class HuaJiSlashBladeDefinitions {
    public static final ResourceKey<SlashBladeDefinition> RODAI_HUAJI = register("rodai_huaji");
    public static final ResourceKey<SlashBladeDefinition> HUAJI_BLADE = register("huaji_blade");
    public static final ResourceKey<SlashBladeDefinition> WAVE = register("wave");

    public static void registerAll(BootstapContext<SlashBladeDefinition> bootstrap) {
        bootstrap.register(RODAI_HUAJI, new SlashBladeDefinition(HuaJiAgeMod.prefix("rodai_huaji"),
                RenderDefinition.Builder.newInstance().textureName(HuaJiAgeMod.prefix("model/slashblade/rodai_huaji.png")).build(),
                PropertiesDefinition.Builder.newInstance().baseAttackModifier(8).maxDamage(1200).build(),
                List.of(), HuaJiCreativeTabs.HUAJI_GROUP.getId()
        ));

        bootstrap.register(HUAJI_BLADE, new SlashBladeDefinition(HuaJiAgeMod.prefix("huaji_blade"),
                RenderDefinition.Builder.newInstance()
                        .textureName(HuaJiAgeMod.prefix("model/slashblade/huaji_blade/huaji_blade.png"))
                        .modelName(HuaJiAgeMod.prefix("model/slashblade/huaji_blade/huaji_blade.obj"))
                        .standbyRenderType(CarryType.NINJA).build(),
                PropertiesDefinition.Builder.newInstance()
                        .baseAttackModifier(10)
                        .maxDamage(60)
                        .defaultSwordType(List.of(SwordType.BEWITCHED))
                        .slashArtsType(SlashArtsRegistry.WAVE_EDGE.getId())
                        .build(),
                List.of(new EnchantmentDefinition(EnchantmentHelper.getEnchantmentId(Enchantments.KNOCKBACK), 2),
                        new EnchantmentDefinition(EnchantmentHelper.getEnchantmentId(Enchantments.BANE_OF_ARTHROPODS), 2),
                        new EnchantmentDefinition(EnchantmentHelper.getEnchantmentId(Enchantments.UNBREAKING), 3),
                        new EnchantmentDefinition(EnchantmentHelper.getEnchantmentId(Enchantments.FIRE_ASPECT), 2),
                        new EnchantmentDefinition(EnchantmentHelper.getEnchantmentId(Enchantments.MOB_LOOTING), 3),
                        new EnchantmentDefinition(EnchantmentHelper.getEnchantmentId(Enchantments.SMITE), 4))
                , HuaJiCreativeTabs.HUAJI_GROUP.getId()
        ));

        bootstrap.register(WAVE, new SlashBladeDefinition(HuaJiAgeMod.prefix("wave"),
                RenderDefinition.Builder.newInstance().effectColor(0x00c2ff)
                        .textureName(HuaJiAgeMod.prefix("model/slashblade/wave/wave.png"))
                        .modelName(HuaJiAgeMod.prefix("model/slashblade/wave/wave.obj"))
                        .standbyRenderType(CarryType.NINJA).build(),
                PropertiesDefinition.Builder.newInstance()
                        .baseAttackModifier(14)
                        .maxDamage(92)
                        .defaultSwordType(List.of(SwordType.BEWITCHED))
                        .slashArtsType(HuaJiSlashArts.WAVE_SLASH.getId())
                        .addSpecialEffect(HuaJiSpecialEffects.WAVING_EDGE.getId())
                        .addSpecialEffect(HuaJiSpecialEffects.SWORN_KINSHIP.getId())
                        .build(),
                List.of(new EnchantmentDefinition(EnchantmentHelper.getEnchantmentId(Enchantments.UNBREAKING), 4),
                        new EnchantmentDefinition(EnchantmentHelper.getEnchantmentId(Enchantments.KNOCKBACK), 2),
                        new EnchantmentDefinition(EnchantmentHelper.getEnchantmentId(Enchantments.MOB_LOOTING), 3),
                        new EnchantmentDefinition(EnchantmentHelper.getEnchantmentId(Enchantments.POWER_ARROWS), 2),
                        new EnchantmentDefinition(EnchantmentHelper.getEnchantmentId(Enchantments.SWEEPING_EDGE), 2),
                        new EnchantmentDefinition(EnchantmentHelper.getEnchantmentId(Enchantments.FIRE_PROTECTION), 1)
                ), HuaJiCreativeTabs.HUAJI_GROUP.getId()
        ));
    }

    private static ResourceKey<SlashBladeDefinition> register(String id) {
        return ResourceKey.create(SlashBladeDefinition.REGISTRY_KEY, HuaJiAgeMod.prefix(id));
    }
}
