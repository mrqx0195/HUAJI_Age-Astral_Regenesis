package net.mrqx.huajiage.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class EffectFive extends MobEffect {
    public EffectFive() {
        super(MobEffectCategory.BENEFICIAL, 0xFFFF00);
        this.addAttributeModifier(Attributes.ARMOR, "1bbd1dc0-3ea5-43e3-9555-5f086f6e60d7", 5, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, "afdf6495-bbd7-4b19-bb3f-8f271a34ccea", 5, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
