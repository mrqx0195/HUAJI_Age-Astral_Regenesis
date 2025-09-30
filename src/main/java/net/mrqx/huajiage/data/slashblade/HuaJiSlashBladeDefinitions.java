package net.mrqx.huajiage.data.slashblade;

import mods.flammpfeil.slashblade.client.renderer.CarryType;
import mods.flammpfeil.slashblade.item.SwordType;
import mods.flammpfeil.slashblade.registry.slashblade.EnchantmentDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.PropertiesDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.RenderDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.SlashBladeDefinition;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.registries.ForgeRegistries;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.compat.slashblade.registy.HuaJiSlashArts;
import net.mrqx.huajiage.compat.slashblade.registy.HuaJiSpecialEffects;
import net.mrqx.huajiage.registy.HuaJiCreativeTabs;

import java.util.List;
import java.util.Objects;

@SuppressWarnings("SameParameterValue")
public class HuaJiSlashBladeDefinitions {
    public static final ResourceKey<SlashBladeDefinition> WAVE = register("wave");

    public static void registerAll(BootstapContext<SlashBladeDefinition> bootstrap) {
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
                List.of(new EnchantmentDefinition(getEnchantmentId(Enchantments.UNBREAKING), 4),
                        new EnchantmentDefinition(getEnchantmentId(Enchantments.KNOCKBACK), 2),
                        new EnchantmentDefinition(getEnchantmentId(Enchantments.MOB_LOOTING), 3),
                        new EnchantmentDefinition(getEnchantmentId(Enchantments.POWER_ARROWS), 2),
                        new EnchantmentDefinition(getEnchantmentId(Enchantments.SWEEPING_EDGE), 2),
                        new EnchantmentDefinition(getEnchantmentId(Enchantments.FIRE_PROTECTION), 1)
                ), HuaJiCreativeTabs.HUAJI_GROUP.getId()
        ));
    }

    private static ResourceLocation getEnchantmentId(Enchantment enchantment) {
        return Objects.requireNonNull(ForgeRegistries.ENCHANTMENTS.getKey(enchantment));
    }

    private static ResourceKey<SlashBladeDefinition> register(String id) {
        return ResourceKey.create(SlashBladeDefinition.REGISTRY_KEY, HuaJiAgeMod.prefix(id));
    }
}
