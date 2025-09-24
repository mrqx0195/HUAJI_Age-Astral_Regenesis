package net.mrqx.huajiage.compat.jei;

import com.mojang.blaze3d.systems.RenderSystem;
import mezz.jei.api.gui.drawable.IDrawable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public record DrawableComponent(Component text, int width, int height, int color) implements IDrawable {
    public DrawableComponent(Component text, int color) {
        this(text, Minecraft.getInstance().font.width(text), Minecraft.getInstance().font.wordWrapHeight(text, Minecraft.getInstance().font.width(text)), color);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void draw(GuiGraphics guiGraphics, int xOffset, int yOffset) {
        Font fontRenderer = Minecraft.getInstance().font;
        int textCenterX = xOffset + (width / 2);
        int textCenterY = yOffset + (height / 2) - 3;
        int stringCenter = fontRenderer.width(text) / 2;
        guiGraphics.drawString(fontRenderer, text, textCenterX - stringCenter, textCenterY, color);
        RenderSystem.setShaderColor(1, 1, 1, 1);
    }

    public void draw(GuiGraphics guiGraphics, int xOffset, int yOffset, Component newText) {
        Font fontRenderer = Minecraft.getInstance().font;
        int textCenterX = xOffset + (fontRenderer.width(newText) / 2);
        int textCenterY = yOffset + (fontRenderer.wordWrapHeight(newText, fontRenderer.width(newText)) / 2) - 3;
        int stringCenter = fontRenderer.width(newText) / 2;
        guiGraphics.drawString(fontRenderer, newText, textCenterX - stringCenter, textCenterY, color);
        RenderSystem.setShaderColor(1, 1, 1, 1);
    }
}
