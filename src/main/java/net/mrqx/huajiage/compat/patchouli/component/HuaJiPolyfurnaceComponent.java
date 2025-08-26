package net.mrqx.huajiage.compat.patchouli.component;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.mrqx.huajiage.HuaJiAgeMod;
import vazkii.patchouli.api.IComponentRenderContext;
import vazkii.patchouli.api.ICustomComponent;
import vazkii.patchouli.api.IVariable;

import java.util.function.UnaryOperator;

public class HuaJiPolyfurnaceComponent implements ICustomComponent {
    private transient int x, y;
    private static final ResourceLocation TEXTURE = HuaJiAgeMod.prefix("textures/patchouli/gui_huaji_polyfurnace.png");


    @Override
    public void build(int componentX, int componentY, int pageNum) {
        this.x = componentX;
        this.y = componentY;
    }

    @Override
    public void render(GuiGraphics graphics, IComponentRenderContext context, float pTicks, int mouseX, int mouseY) {
        graphics.blit(TEXTURE, x, y, 0, 0, 109, 56);
        float animationValueA = 0;
        float animationValueB = 0;
        if (Minecraft.getInstance().level != null) {
            animationValueA = (float) (Minecraft.getInstance().level.getGameTime() % 200) / 200;
            animationValueB = (float) (Minecraft.getInstance().level.getGameTime() % 500) / 500;
        }

        graphics.blit(TEXTURE, x + 7, Math.round(y + 8 + 31 * animationValueA),
                233, Math.round(5 + 31 * animationValueA),
                6, Math.round(31 * (1 - animationValueA)));

        graphics.blit(TEXTURE, x + 39, y + 27,
                2, 62,
                Math.round(37 * animationValueA), 1);

        graphics.blit(TEXTURE, x + 77, Math.round(y + 13 + 19 * (1 - animationValueB)),
                202, Math.round(6 + 19 * (1 - animationValueB)),
                23, Math.round(19 * animationValueB));
    }

    @Override
    public void onVariablesAvailable(UnaryOperator<IVariable> lookup) {
    }
}
