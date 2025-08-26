package net.mrqx.huajiage.compat.patchouli.component;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.mrqx.huajiage.HuaJiAgeMod;
import vazkii.patchouli.api.IComponentRenderContext;
import vazkii.patchouli.api.ICustomComponent;
import vazkii.patchouli.api.IVariable;

import java.util.function.UnaryOperator;

@SuppressWarnings("unused")
public class HuaJiBlenderComponent implements ICustomComponent {
    private transient int x, y;
    private static final ResourceLocation TEXTURE = HuaJiAgeMod.prefix("textures/patchouli/gui_huaji_blender.png");


    @Override
    public void build(int componentX, int componentY, int pageNum) {
        this.x = componentX;
        this.y = componentY;
    }

    @Override
    public void render(GuiGraphics graphics, IComponentRenderContext context, float pTicks, int mouseX, int mouseY) {
        graphics.blit(TEXTURE, x, y, 0, 0, 106, 55);
        float animationValue = 0;
        if (Minecraft.getInstance().level != null) {
            animationValue = (float) (Minecraft.getInstance().level.getGameTime() % 200) / 200;
        }
        graphics.blit(TEXTURE, x + 13, Math.round(y + 18 * (1 + animationValue)),
                37, Math.round(156 + 18 * animationValue),
                2, Math.round(18 * (1 - animationValue)));

        graphics.blit(TEXTURE, x + 38, y + 18,
                0, 156,
                Math.round(30 * animationValue), 17);
    }

    @Override
    public void onVariablesAvailable(UnaryOperator<IVariable> lookup) {
    }
}
