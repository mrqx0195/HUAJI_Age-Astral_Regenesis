package net.mrqx.huajiage.client.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrqx.huajiage.block.inventory.HuaJiBlenderMenu;

@OnlyIn(Dist.CLIENT)
public class HuaJiBlenderScreen extends AbstractContainerScreen<HuaJiBlenderMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("huajiage", "textures/gui/container/gui_huaji_blender.png");

    public final int COOK_BAR_XPOS = 76;
    public final int COOK_BAR_YPOS = 29;
    public final int COOK_BAR_ICON_U = 0;
    public final int COOK_BAR_ICON_V = 156;
    public final int COOK_BAR_WIDTH = 30;
    public final int COOK_BAR_HEIGHT = 17;

    public final int FLAME_XPOS = 12;
    public final int FLAME_YPOS = 29;
    public final int FLAME_ICON_U = 37;
    public final int FLAME_ICON_V = 156;
    public final int FLAME_WIDTH = 2;
    public final int FLAME_HEIGHT = 18;

    public HuaJiBlenderScreen(HuaJiBlenderMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = 176;
        this.imageHeight = 156;
        this.inventoryLabelX = 5;
        this.inventoryLabelY = 62;
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        pGuiGraphics.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);
        double cookProgress = menu.getProgress();
        pGuiGraphics.blit(TEXTURE,
                leftPos + COOK_BAR_XPOS, topPos + COOK_BAR_YPOS,
                COOK_BAR_ICON_U, COOK_BAR_ICON_V,
                (int) (COOK_BAR_WIDTH * cookProgress), COOK_BAR_HEIGHT);

        double burnRemaining = menu.getFuel();
        int yOffset = (int) ((1.0 - burnRemaining) * FLAME_HEIGHT);
        pGuiGraphics.blit(TEXTURE,
                leftPos + FLAME_XPOS, topPos + FLAME_YPOS + yOffset,
                FLAME_ICON_U, FLAME_ICON_V + yOffset,
                FLAME_WIDTH, FLAME_HEIGHT - yOffset);
    }
}
