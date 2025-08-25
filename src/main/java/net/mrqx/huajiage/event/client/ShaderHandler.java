package net.mrqx.huajiage.event.client;

import com.google.gson.JsonSyntaxException;
import com.mega.endinglib.util.time.TimeContext;
import com.mega.endinglib.util.time.TimeStopUtils;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.client.renderer.shader.HuaJiShaderManager;
import net.mrqx.huajiage.compat.HuaJiCompat;
import net.mrqx.huajiage.registy.HuaJiSoundEvents;
import net.mrqx.huajiage.utils.HuajiSoundPlayer;
import net.mrqx.huajiage.utils.QuadConsumer;
import org.apache.commons.lang3.tuple.MutablePair;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber
public class ShaderHandler {
    public static final HashMap<ResourceLocation, Map.Entry<Long, Long>> TIME_STOP_EFFECT_TICK = new HashMap<>();
    public static final HashMap<ResourceLocation, Integer> TIME_STOPER = new HashMap<>();
    public static final ResourceLocation TIME_STOP_SHADER = HuaJiAgeMod.prefix("time_stop_shader");
    private static int time = 0;
    private static int lastRenderTime = 0;

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onRenderLevelStageEvent(RenderLevelStageEvent event) {
        if (event.getStage().equals(RenderLevelStageEvent.Stage.AFTER_LEVEL)) {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.level != null && minecraft.player != null && Minecraft.renderNames() && minecraft.screen == null && !HuaJiCompat.getInstance().hasFantasyEnding) {
                TIME_STOP_EFFECT_TICK.forEach((level, entry) -> {
                    if (minecraft.level.dimension().location().equals(level)) {
                        long endTime = entry.getValue();
                        if (time == 0) {
                            time = (int) (TimeContext.Client.currentSeconds() * 20);
                        }
                        int timePassed = (int) (TimeContext.Client.currentSeconds() * 20 - time);
                        if (lastRenderTime == timePassed) {
                            return;
                        }

                        if (timePassed == 10) {
                            addShader(new ResourceLocation("shaders/post/invert.json"));
                        } else if (timePassed == 30) {
                            addShader(new ResourceLocation("shaders/post/desaturate.json"));
                        }

                        if (endTime - timePassed == 20) {
                            minecraft.getSoundManager().play(HuajiSoundPlayer.getMovingSound(minecraft.player, HuaJiSoundEvents.THE_WORLD_RE.get(), SoundSource.PLAYERS, 1));
                        } else if (endTime - timePassed == 10) {
                            addShader(new ResourceLocation("shaders/post/pencil.json"));
                        } else if (endTime - timePassed <= 3) {
                            HuaJiShaderManager.removeShader(TIME_STOP_SHADER);
                        }

                        lastRenderTime = timePassed;
                    }
                });
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onComputeFovModifierEvent(ComputeFovModifierEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.level != null && minecraft.player != null && Minecraft.renderNames() && minecraft.screen == null) {
            TIME_STOP_EFFECT_TICK.forEach((level, entry) -> {
                if (minecraft.level.dimension().location().equals(level)) {
                    if (TimeStopUtils.isTimeStop && TimeStopUtils.canMove(minecraft.player) && TimeStopUtils.andSameDimension(minecraft.level)) {
                        if (TIME_STOPER.get(level).equals(minecraft.player.getId())) {
                            float timePassed = TimeContext.Client.currentSeconds() * 20 - time;
                            if (timePassed < 30) {
                                event.setNewFovModifier(event.getNewFovModifier() + 0.3F);
                            } else {
                                event.setNewFovModifier(event.getNewFovModifier() + 0.1F);
                            }
                        }
                    }
                }
            });
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onRenderGuiOverlayEvent(RenderGuiOverlayEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (event.getOverlay().id().equals(VanillaGuiOverlay.VIGNETTE.id()) && minecraft.level != null && minecraft.player != null && Minecraft.renderNames() && minecraft.screen == null) {
            List<ResourceLocation> removeList = new ArrayList<>();
            TIME_STOP_EFFECT_TICK.forEach((level, startTick) -> {
                if (minecraft.level.dimension().location().equals(level)) {
                    if (TimeStopUtils.isTimeStop && TimeStopUtils.canMove(minecraft.player) && TimeStopUtils.andSameDimension(minecraft.level)) {
                        if (TIME_STOPER.get(level).equals(minecraft.player.getId())) {
                            GuiGraphics guiGraphics = event.getGuiGraphics();
                            Window window = minecraft.getWindow();
                            renderView(guiGraphics, HuaJiAgeMod.prefix("textures/misc/time_stop_view.png"));
                            renderElement(HuaJiAgeMod.prefix("textures/misc/gear_1.png"), 0, 0, 512, 512, 0.25f, 0.25f, false);
                            renderElement(HuaJiAgeMod.prefix("textures/misc/gear_2.png"), window.getGuiScaledWidth(), window.getGuiScaledHeight(), 512, 512, 0.25f, 0.25f, true);
                        }
                    } else {
                        removeList.add(level);
                    }
                }
            });
            removeList.forEach(TIME_STOP_EFFECT_TICK::remove);
        }
    }

    public static void addShader(ResourceLocation resourceLocation) {
        PostChain postChain = loadEffect(resourceLocation);
        if (postChain != null) {
            HuaJiShaderManager.removeShader(TIME_STOP_SHADER);
            HuaJiShaderManager.postChains.put(TIME_STOP_SHADER, postChain);
        }
    }

    @Nullable
    public static PostChain loadEffect(ResourceLocation resourceLocation) {
        PostChain postEffect = null;
        Minecraft minecraft = Minecraft.getInstance();
        try {
            postEffect = new PostChain(minecraft.getTextureManager(), minecraft.getResourceManager(), minecraft.getMainRenderTarget(), resourceLocation);
            postEffect.resize(minecraft.getWindow().getWidth(), minecraft.getWindow().getHeight());
        } catch (IOException ioexception) {
            HuaJiAgeMod.LOGGER.warn("Failed to load shader: {}", resourceLocation, ioexception);
        } catch (JsonSyntaxException jsonsyntaxexception) {
            HuaJiAgeMod.LOGGER.warn("Failed to parse shader: {}", resourceLocation, jsonsyntaxexception);
        }
        return postEffect;
    }

    public static void renderView(GuiGraphics guiGraphics, ResourceLocation texture) {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.blendFuncSeparate(
                GlStateManager.SourceFactor.ZERO,
                GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR,
                GlStateManager.SourceFactor.ONE,
                GlStateManager.DestFactor.ZERO
        );
        RenderSystem.setShaderColor(0.25F, 0.25F, 0.25F, 1.0F);

        int width = guiGraphics.guiWidth();
        int height = guiGraphics.guiHeight();

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, texture);

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder buffer = tesselator.getBuilder();
        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);

        buffer.vertex(0.0D, height, -90.0D).uv(0.0F, 1.0F).endVertex();
        buffer.vertex(width, height, -90.0D).uv(1.0F, 1.0F).endVertex();
        buffer.vertex(width, 0.0D, -90.0D).uv(1.0F, 0.0F).endVertex();
        buffer.vertex(0.0D, 0.0D, -90.0D).uv(0.0F, 0.0F).endVertex();

        tesselator.end();

        RenderSystem.depthMask(true);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
    }

    public static void renderElement(ResourceLocation texture, int x, int y, float width, float heigh, float xScale, float yScale, boolean reverse) {
        RenderSystem.disableDepthTest();
        RenderSystem.blendFuncSeparate(
                GlStateManager.SourceFactor.ZERO,
                GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR,
                GlStateManager.SourceFactor.ONE,
                GlStateManager.DestFactor.ZERO
        );
        RenderSystem.setShaderColor(0.3F, 0.3F, 0.3F, 1.0F);

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, texture);

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder buffer = tesselator.getBuilder();
        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        if (reverse) {
            x -= (int) (width * xScale);
            y -= (int) (heigh * yScale);
        }

        buffer.vertex(x, y + heigh * yScale, 0).uv(0.0F, 1.0F).endVertex();
        buffer.vertex(x + width * xScale, y + heigh * yScale, 0).uv(1.0F, 1.0F).endVertex();
        buffer.vertex(x + width * xScale, y, 0).uv(1.0F, 0.0F).endVertex();
        buffer.vertex(x, y, 0).uv(0.0F, 0.0F).endVertex();

        tesselator.end();

        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.defaultBlendFunc();
    }

    public static QuadConsumer<ResourceLocation, Long, Long, Integer> setClientTimeStopEffect() {
        return (level, tick, time, id) -> {
            TIME_STOP_EFFECT_TICK.put(level, new MutablePair<>(tick, time));
            TIME_STOPER.put(level, id);
            ShaderHandler.time = (int) (TimeContext.Client.currentSeconds() * 20);
        };
    }
}
