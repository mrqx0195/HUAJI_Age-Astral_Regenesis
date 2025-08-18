package net.mrqx.huajiage.data.model;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.registy.HuaJiItems;

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


        basicItem(HuaJiItems.EGG_RICE.get());
        basicItem(HuaJiItems.ULTIMATE_EGG_RICE.get());
        basicItem(HuaJiItems.RAW_GLUTEN.get());
        basicItem(HuaJiItems.BAKED_GLUTEN.get());


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

    }
}
