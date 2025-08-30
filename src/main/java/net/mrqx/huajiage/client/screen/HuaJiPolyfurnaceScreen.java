package net.mrqx.huajiage.client.screen;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrqx.huajiage.block.blockentity.HuaJiPolyfurnaceBlockEntity;
import net.mrqx.huajiage.block.inventory.HuaJiPolyfurnaceMenu;
import net.mrqx.huajiage.utils.HuaJiUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class HuaJiPolyfurnaceScreen extends AbstractContainerScreen<HuaJiPolyfurnaceMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("huajiage", "textures/gui/container/gui_huaji_polyfurnace.png");

    public final int COOK_BAR_XPOS = 62;
    public final int COOK_BAR_YPOS = 33;
    public final int COOK_BAR_ICON_U = 0;
    public final int COOK_BAR_ICON_V = 158;
    public final int COOK_BAR_WIDTH = 87;
    public final int COOK_BAR_HEIGHT = 1;

    public final int POOL_XPOS = 147;
    public final int POOL_YPOS = 19;
    public final int POOL_ICON_U = 192;
    public final int POOL_ICON_V = 2;
    public final int POOL_WIDTH = 28;
    public final int POOL_HEIGHT = 19;

    public final int ENERGY_XPOS = 27;
    public final int ENERGY_YPOS = 4;
    public final int ENERGY_ICON_U = 246;
    public final int ENERGY_ICON_V = 0;
    public final int ENERGY_WIDTH = 3;
    public final int ENERGY_HEIGHT = 62;

    public final int FLAME_XPOS = 10;
    public final int FLAME_YPOS = 6;
    public final int FLAME_ICON_U = 224;
    public final int FLAME_ICON_V = 0;
    public final int FLAME_WIDTH = 16;
    public final int FLAME_HEIGHT = 58;

    public HuaJiPolyfurnaceScreen(HuaJiPolyfurnaceMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = 192;
        this.imageHeight = 156;
        this.inventoryLabelX = 152;
        this.inventoryLabelY = 58;
    }

    @Override
    protected void renderLabels(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        Font fontRenderer = Minecraft.getInstance().font;
        pGuiGraphics.drawString(this.font, this.title, (imageWidth - fontRenderer.width(this.title)) / 2, this.titleLabelY, 4210752, false);
        pGuiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 4210752, false);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);

        if (this.hoveredSlot == null || !this.hoveredSlot.hasItem()) {
            List<Component> pTooltipLines = new ArrayList<>();
            if (HuaJiUtils.isInRectangularArea(leftPos + FLAME_XPOS, topPos + FLAME_YPOS, FLAME_WIDTH, FLAME_HEIGHT, pMouseX, pMouseY)) {
                BigDecimal energy = BigDecimal.valueOf((double) this.menu.getEnergy() / 1000);
                pTooltipLines.add(Component.translatable("gui.huajiage.polyfurnace.fuel").withStyle(ChatFormatting.GOLD));
                pTooltipLines.add(Component.literal(energy + (this.menu.getEnergy() > 1000 ? "k" : "")).withStyle(ChatFormatting.AQUA));
            }
            if (HuaJiUtils.isInRectangularArea(leftPos + POOL_XPOS, topPos + POOL_YPOS, POOL_WIDTH, POOL_HEIGHT, pMouseX, pMouseY)) {
                pTooltipLines.add(Component.translatable("gui.huajiage.polyfurnace.pool"));
                pTooltipLines.add(Component.literal(this.menu.getPool() + "/" + HuaJiPolyfurnaceBlockEntity.TOTAL_POINT).withStyle(ChatFormatting.YELLOW, ChatFormatting.BOLD));
            }

            if (HuaJiUtils.isInRectangularArea(leftPos + ENERGY_XPOS, topPos + ENERGY_YPOS, ENERGY_WIDTH, ENERGY_HEIGHT, pMouseX, pMouseY)) {
                if (this.menu.blockEntity != null) {
                    pTooltipLines.add(Component.translatable("gui.huajiage.polyfurnace.energy"));
                    pTooltipLines.add(Component.literal(this.menu.blockEntity.feEnergy + "/" + HuaJiPolyfurnaceBlockEntity.FE_CAPACITY).withStyle(ChatFormatting.YELLOW, ChatFormatting.BOLD));
                }
            }

            pGuiGraphics.renderComponentTooltip(this.font, pTooltipLines, pMouseX, pMouseY);
        }
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        pGuiGraphics.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);
        double cookProgress = this.menu.getProgress();
        pGuiGraphics.blit(TEXTURE,
                leftPos + COOK_BAR_XPOS, topPos + COOK_BAR_YPOS,
                COOK_BAR_ICON_U, COOK_BAR_ICON_V,
                (int) (COOK_BAR_WIDTH * cookProgress), COOK_BAR_HEIGHT);

        double burnRemaining = this.menu.getFuel();
        int burnOffsetY = (int) ((1.0 - burnRemaining) * FLAME_HEIGHT);
        pGuiGraphics.blit(TEXTURE,
                leftPos + FLAME_XPOS, topPos + FLAME_YPOS + burnOffsetY,
                FLAME_ICON_U, FLAME_ICON_V + burnOffsetY,
                FLAME_WIDTH, FLAME_HEIGHT - burnOffsetY);

        double pool = (double) this.menu.getPool() / HuaJiPolyfurnaceBlockEntity.TOTAL_POINT;
        int poolOffsetY = (int) ((1.0 - pool) * POOL_HEIGHT);
        pGuiGraphics.blit(TEXTURE,
                leftPos + POOL_XPOS, topPos + POOL_YPOS + poolOffsetY,
                POOL_ICON_U, POOL_ICON_V + poolOffsetY,
                POOL_WIDTH, POOL_HEIGHT - poolOffsetY);

        if (this.menu.blockEntity != null) {
            double energy = (double) this.menu.blockEntity.feEnergy / HuaJiPolyfurnaceBlockEntity.FE_CAPACITY;
            int energyOffsetY = (int) ((1.0 - energy) * ENERGY_HEIGHT);
            pGuiGraphics.blit(TEXTURE,
                    leftPos + ENERGY_XPOS, topPos + ENERGY_YPOS + energyOffsetY,
                    ENERGY_ICON_U, ENERGY_ICON_V + energyOffsetY,
                    ENERGY_WIDTH, ENERGY_HEIGHT - energyOffsetY);
        }
    }
}
