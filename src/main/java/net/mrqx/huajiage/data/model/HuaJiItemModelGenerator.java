package net.mrqx.huajiage.data.model;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.registy.HuaJiItems;

import java.util.Objects;

public class HuaJiItemModelGenerator extends ItemModelProvider {
    public HuaJiItemModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, HuaJiAgeMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(HuaJiItems.HUAJI.get());
        basicItem(HuaJiItems.ANTI_HUAJI.get());
        basicItem(HuaJiItems.HUAJI_FRAGMENT.get());
        basicItem(HuaJiItems.HUAJI_INGOT.get());
        basicItem(HuaJiItems.REDSTONE_DRUSE.get());
        basicItem(HuaJiItems.NEUTRON_STAR_FRAGMENT.get());
        basicItem(HuaJiItems.FLASH_FLOUR.get());
        basicItem(HuaJiItems.ETHER_POINT.get());
        basicItem(HuaJiItems.ETHER_CIRCUMFLUX_BOARD.get());
        basicItem(HuaJiItems.HOPE_ELEMENT.get());
        basicItem(HuaJiItems.HOPE_FLOWER.get());
        basicItem(HuaJiItems.WAVE_CRYSTAL.get());


        basicItem(HuaJiItems.HUAJI_STAR.get());
        basicItem(HuaJiItems.AIRSPACE_STAR.get());
        basicItem(HuaJiItems.INFINITE_UNIVERSE_STAR.get());
        basicItem(HuaJiItems.HUAJI_STAR_POLY.get());


        basicItem(HuaJiItems.EGG_RICE.get());
        basicItem(HuaJiItems.ULTIMATE_EGG_RICE.get());
        basicItem(HuaJiItems.RAW_GLUTEN.get());
        basicItem(HuaJiItems.BAKED_GLUTEN.get());
        basicItem(HuaJiItems.DIO_BREAD.get());
        basicItem(HuaJiItems.REO_CHERRY.get());

        swordItem(HuaJiItems.HUAJI_SWORD.get());
        swordItem(HuaJiItems.MULTI_KNIFE.get());
        swordItem(HuaJiItems.MULTI_KNIFE_SHINY.get());

        basicItem(HuaJiItems.HUAJI_HELMET.get());
        basicItem(HuaJiItems.HUAJI_CHESTPLATE.get());
        basicItem(HuaJiItems.HUAJI_LEGGINGS.get());
        basicItem(HuaJiItems.HUAJI_BOOTS.get());

        basicItem(HuaJiItems.ORGA_HELMET.get());
        basicItem(HuaJiItems.ORGA_CHESTPLATE.get());
        basicItem(HuaJiItems.ORGA_LEGGINGS.get());
        basicItem(HuaJiItems.ORGA_BOOTS.get());

        basicItem(HuaJiItems.FIFTY_FIFTY_HELMET.get());
        basicItem(HuaJiItems.LORD_KEY.get());

        basicItem(HuaJiItems.SECOND_FOIL.get());
        basicItem(HuaJiItems.ORGA_HAIR_KNIFE.get());

        basicItem(HuaJiItems.DISC.get());
        basicItem(HuaJiItems.SINGULARITY.get());
        basicItem(HuaJiItems.ARROW_STAND.get());
        basicItem(HuaJiItems.ARROW_REQUIEM.get());
        basicItem(HuaJiItems.ORGA_REQUIEM.get());
        basicItem(HuaJiItems.TAROT.get());
    }

    public ItemModelBuilder swordItem(Item item) {
        return swordItem(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)));
    }

    public ItemModelBuilder swordItem(ResourceLocation item) {
        return getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                .texture("layer0", new ResourceLocation(item.getNamespace(), "item/" + item.getPath()));
    }
}
