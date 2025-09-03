package net.mrqx.huajiage.event.handler;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.data.loot.RandomNbtFunction;
import net.mrqx.huajiage.item.stand.ItemDisc;
import net.mrqx.huajiage.registy.HuaJiItems;

import java.util.List;

@Mod.EventBusSubscriber
public class LootHandler {
    private static final ListTag STAND_LIST = new ListTag();

    @SubscribeEvent
    public static void onLootTableLoadEvent(LootTableLoadEvent event) {
        if (event.getName().equals(BuiltInLootTables.END_CITY_TREASURE)) {
            event.getTable().addPool(LootPool.lootPool().setRolls(UniformGenerator.between(0, 2))
                    .add(LootItem.lootTableItem(HuaJiItems.HUAJI.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 10))))
                    .add(LootItem.lootTableItem(HuaJiItems.HUAJI_STAR.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
                    .add(LootItem.lootTableItem(HuaJiItems.AIRSPACE_STAR.get()).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
                    .add(LootItem.lootTableItem(HuaJiItems.DISC.get()).setWeight(3).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                            .apply(RandomNbtFunction.setTags(STAND_LIST, STAND_LIST.size())))
                    .build());
        } else if (event.getName().equals(BuiltInLootTables.DESERT_PYRAMID)) {
            event.getTable().addPool(LootPool.lootPool().setRolls(UniformGenerator.between(0, 2))
                    .add(LootItem.lootTableItem(HuaJiItems.HUAJI.get()).setWeight(30).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .add(LootItem.lootTableItem(HuaJiItems.BAKED_GLUTEN.get()).setWeight(10).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .add(LootItem.lootTableItem(HuaJiItems.DISC.get()).setWeight(6).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                            .apply(RandomNbtFunction.setTags(STAND_LIST, STAND_LIST.size())))
                    .build());
        } else if (event.getName().equals(BuiltInLootTables.JUNGLE_TEMPLE)) {
            event.getTable().addPool(LootPool.lootPool().setRolls(UniformGenerator.between(0, 2))
                    .add(LootItem.lootTableItem(HuaJiItems.HUAJI.get()).setWeight(10).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    .add(LootItem.lootTableItem(Items.EMERALD).setWeight(30).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                    .add(LootItem.lootTableItem(HuaJiItems.DISC.get()).setWeight(6).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                            .apply(RandomNbtFunction.setTags(STAND_LIST, STAND_LIST.size())))
                    .build());
        } else if (event.getName().equals(BuiltInLootTables.NETHER_BRIDGE)) {
            event.getTable().addPool(LootPool.lootPool().setRolls(UniformGenerator.between(0, 3))
                    .add(LootItem.lootTableItem(HuaJiItems.HUAJI.get()).setWeight(30).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                    .build());
        }
    }

    static {
        List.of("hierophant_green", "the_world", "star_platinum", "killer_queen").forEach(s -> {
            CompoundTag tag = new CompoundTag();
            tag.putString(ItemDisc.DISC_STAND_KEY, HuaJiAgeMod.prefix(s).toString());
            tag.putInt(ItemDisc.DISC_STAND_LEVEL_KEY, 0);
            STAND_LIST.add(tag);
        });
    }
}
