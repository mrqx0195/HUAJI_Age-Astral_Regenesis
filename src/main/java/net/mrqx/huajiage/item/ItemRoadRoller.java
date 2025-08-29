package net.mrqx.huajiage.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.mrqx.huajiage.entity.EntityRoadRoller;
import net.mrqx.huajiage.registy.HuaJiSoundEvents;
import net.mrqx.huajiage.utils.HuaJiSoundPlayer;
import org.jetbrains.annotations.NotNull;

public class ItemRoadRoller extends BaseItem {
    public ItemRoadRoller() {
        super(new Item.Properties().rarity(Rarity.RARE));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        pPlayer.startUsingItem(pUsedHand);
        if (!pLevel.isClientSide) {
            EntityRoadRoller road = new EntityRoadRoller(pPlayer, pLevel);
            Vec3 v1 = pPlayer.getLookAngle();
            road.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 1F, 1F, 0f);
            float fn = (float) Math.sqrt(v1.x * v1.x + v1.y * v1.y + v1.z * v1.z);
            road.setPos(road.position().add(v1.x / fn, 0.1f + v1.y / fn, v1.z / fn));
            road.setYRot(-pPlayer.getYRot());
            road.setXRot(pPlayer.getXRot());
            road.setDamage(10f);
            road.setLife(512);
            road.setItem(itemStack);
            if (!pPlayer.isCreative()) {
                itemStack.shrink(1);
            }
            HuaJiSoundPlayer.playMovingSoundToClient(pPlayer, HuaJiSoundEvents.ROAD_ROLLER.get(), pPlayer.getSoundSource(), 2F);
            pLevel.addFreshEntity(road);
        }
        pPlayer.swing(pUsedHand, true);
        return InteractionResultHolder.success(itemStack);
    }
}
