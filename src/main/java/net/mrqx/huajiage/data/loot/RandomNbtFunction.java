package net.mrqx.huajiage.data.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.mrqx.huajiage.HuaJiAgeMod;

public class RandomNbtFunction extends LootItemConditionalFunction {
    @SuppressWarnings("DataFlowIssue")
    public static LootItemFunctionType TYPE = null;
    private final ListTag tags;
    private final int number;

    protected RandomNbtFunction(LootItemCondition[] predicates, ListTag tags, int number) {
        super(predicates);
        this.tags = tags;
        this.number = number;
    }

    @Override
    protected ItemStack run(ItemStack stack, LootContext context) {
        CompoundTag compoundTag = stack.getOrCreateTag();
        int count = tags.size();
        int number = context.getRandom().nextInt(count);
        CompoundTag tag;
        if (number < count) {
            tag = tags.getCompound(number);
        } else {
            tag = tags.getCompound(count - 1);
        }
        compoundTag.merge(tag);
        stack.setTag(compoundTag);
        return stack;
    }

    public static void register() {
        TYPE = Registry.register(BuiltInRegistries.LOOT_FUNCTION_TYPE,
                new ResourceLocation(HuaJiAgeMod.MODID, "random_nbt_function"),
                new LootItemFunctionType(new Serializer()));
    }

    @Override
    public LootItemFunctionType getType() {
        return TYPE;
    }

    public static LootItemConditionalFunction.Builder<?> setTags(ListTag tags, int number) {
        return simpleBuilder((conditions) -> new RandomNbtFunction(conditions, tags, number));
    }

    public static final class Serializer extends LootItemConditionalFunction.Serializer<RandomNbtFunction> {
        @Override
        public void serialize(JsonObject object, RandomNbtFunction functionObject, JsonSerializationContext context) {
            int count = functionObject.tags.size();
            for (int i = 0; i < count; i++) {
                CompoundTag tag = functionObject.tags.getCompound(i);
                object.addProperty("tag" + (i + 1), tag.toString());
            }
            object.addProperty("number", functionObject.number);
        }

        @Override
        public RandomNbtFunction deserialize(JsonObject object, JsonDeserializationContext context, LootItemCondition[] conditions) {
            try {
                ListTag list = new ListTag();
                int size = GsonHelper.getAsInt(object, "number");
                if (size < 1) {
                    size = 1;
                }
                for (int i = 0; i < size; i++) {
                    CompoundTag nbtTag = TagParser.parseTag(GsonHelper.getAsString(object, "tag" + (i + 1)));
                    if (nbtTag.contains("StandId")) {
                        String id = nbtTag.getString("StandId");
                        if (id.contains("-")) {
                            nbtTag.putString("StandId", id.replace("-", ":"));
                        }
                    }
                    list.add(nbtTag);
                }
                return new RandomNbtFunction(conditions, list, size);
            } catch (Exception e) {
                throw new JsonSyntaxException(e);
            }
        }
    }
}
