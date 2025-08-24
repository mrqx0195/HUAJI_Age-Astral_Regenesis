package net.mrqx.huajiage.event.client;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.capability.stand.StandDataCapabilityProvider;
import net.mrqx.huajiage.config.HuaJiClientConfig;
import net.mrqx.huajiage.item.ItemDisc;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.stand.AbstractStand;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber
public class StandExtraRenderer {
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void renderTick(RenderGuiOverlayEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player != null) {
            LocalPlayer player = minecraft.player;
            player.getCapability(StandDataCapabilityProvider.STAND_DATA).ifPresent(data -> {
                AbstractStand stand = AbstractStand.getStand(data.getStand());
                if (stand != null) {
                    if (Minecraft.renderNames()) {
                        if (minecraft.screen == null) {
                            GuiGraphics guiGraphics = event.getGuiGraphics();
                            Window window = minecraft.getWindow();
                            int x = (int) (HuaJiClientConfig.STAND_HUD_X.get() * window.getGuiScaledWidth());
                            int y = (int) (HuaJiClientConfig.STAND_HUD_Y.get() * window.getGuiScaledHeight());
                            ItemStack stack = HuaJiItems.DISC.get().getDefaultInstance();
                            stack.getOrCreateTag().putString(ItemDisc.DISC_STAND_KEY, data.getStand().toString());
                            stack.getOrCreateTag().putInt(ItemDisc.DISC_STAND_LEVEL_KEY, 0);
                            guiGraphics.pose().pushPose();
                            guiGraphics.pose().translate(x + 4, y - 6, 0);
                            guiGraphics.pose().scale(1.5F, 1.5F, 1.5F);
                            guiGraphics.renderItem(stack, 0, 0);
                            guiGraphics.pose().popPose();

                            guiGraphics.drawString(minecraft.font, Component.translatable("stand.huajiage.name", Component.translatable(stand.getDescriptionId())).withStyle(ChatFormatting.BOLD),
                                    8 + x, 2 + 16 + y, 0xFFFFFF, true);
                            guiGraphics.drawString(minecraft.font, Component.translatable("stand.huajiage.level", data.getLevel()).withStyle(ChatFormatting.BOLD),
                                    8 + x, 20 + 16 + y, 0xFFFFFF, true);
                            guiGraphics.drawString(minecraft.font, Component.translatable("stand.huajiage.state", Component.translatable("stand.huajiage.state." + data.getState())).withStyle(ChatFormatting.BOLD),
                                    8 + x, 30 + 16 + y, 0xFFFFFF, true);
                            guiGraphics.drawString(minecraft.font, Component.translatable("stand.huajiage.mp", data.getEnergy(), data.getMaxEnergy()).withStyle(ChatFormatting.BOLD),
                                    8 + x, 40 + 16 + y, stand.skillEnergyDemand(player, data) <= data.getEnergy() ? 0x00FFFC : 0xFFFFFF, true);
                        }
                    }
                }
            });
        }
    }
}
