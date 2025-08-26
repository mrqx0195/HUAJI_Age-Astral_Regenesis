package net.mrqx.huajiage.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.client.layer.LayerLordLu;
import net.mrqx.huajiage.client.layer.LayerStand;
import net.mrqx.huajiage.client.model.ModelDisc;
import net.mrqx.huajiage.client.model.ModelFiftyFiftyHelmet;
import net.mrqx.huajiage.client.model.ModelLordLu;
import net.mrqx.huajiage.client.model.ModelOrgaArmor;
import net.mrqx.huajiage.client.screen.HuaJiBlenderScreen;
import net.mrqx.huajiage.client.screen.HuaJiPolyfurnaceScreen;
import net.mrqx.huajiage.item.equipment.ItemExglutenbur;
import net.mrqx.huajiage.item.equipment.ItemHeroBow;
import net.mrqx.huajiage.item.equipment.ItemHuajiLaTiaoSword;
import net.mrqx.huajiage.item.equipment.ItemHuajiStarSword;
import net.mrqx.huajiage.item.equipment.armor.ItemOrgaArmor;
import net.mrqx.huajiage.registy.HuaJiItems;
import net.mrqx.huajiage.registy.HuaJiMenus;
import net.mrqx.huajiage.registy.HuaJiStands;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class ClientHandler {
    @SubscribeEvent
    public static void screenRegistry(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(HuaJiMenus.HUAJI_BLENDER.get(), HuaJiBlenderScreen::new);
            MenuScreens.register(HuaJiMenus.HUAJI_POLYFURNACE.get(), HuaJiPolyfurnaceScreen::new);

            ItemProperties.register(HuaJiItems.EXGLUTENBUR.get(), HuaJiAgeMod.prefix("exglutenbur_flavor"),
                    (stack, level, living, id) -> ItemExglutenbur.Flavor.getFlavor(stack).ordinal());

            ItemProperties.register(HuaJiItems.HERO_BOW.get(), HuaJiAgeMod.prefix("pull"),
                    (stack, level, living, id) -> living != null && living.getMainHandItem().is(HuaJiItems.HERO_BOW.get())
                            && stack.is(HuaJiItems.HERO_BOW.get()) ? (float) (stack.getUseDuration() - living.getUseItemRemainingTicks()) / 20.0F : 0);
            ItemProperties.register(HuaJiItems.HERO_BOW.get(), HuaJiAgeMod.prefix("pulling"),
                    (stack, level, living, id) -> living != null && living.getMainHandItem().is(HuaJiItems.HERO_BOW.get())
                            && stack.is(HuaJiItems.HERO_BOW.get()) && living.isUsingItem() ? 1 : 0);
            ItemProperties.register(HuaJiItems.HERO_BOW.get(), HuaJiAgeMod.prefix("burst"),
                    (stack, level, living, id) -> living != null && living.getMainHandItem().is(HuaJiItems.HERO_BOW.get())
                            && stack.is(HuaJiItems.HERO_BOW.get()) && ItemHeroBow.Mode.getMode(stack).equals(ItemHeroBow.Mode.ON)
                            && (float) (stack.getUseDuration() - living.getUseItemRemainingTicks()) / 20.0F >= 1 ? 1 : 0);

            ItemProperties.register(HuaJiItems.HUAJI_STAR_SWORD.get(), HuaJiAgeMod.prefix("burst"),
                    (stack, level, living, id) -> ItemHuajiStarSword.Mode.getMode(stack).equals(ItemHuajiStarSword.Mode.ON) ? 1 : 0);
            ItemProperties.register(HuaJiItems.HUAJI_LATIAO_SWORD.get(), HuaJiAgeMod.prefix("burst"),
                    (stack, level, living, id) -> ItemHuajiLaTiaoSword.Mode.getMode(stack).equals(ItemHuajiLaTiaoSword.Mode.ON) ? 1 : 0);

            ItemProperties.register(HuaJiItems.INFINITE_CHARM.get(), HuaJiAgeMod.prefix("orga"),
                    (stack, level, living, id) -> living != null && ItemOrgaArmor.hasAllOrgaArmor(living) ? 1 : 0);
        });
    }

    @SubscribeEvent
    public static void registerKeyMapping(RegisterKeyMappingsEvent event) {
        event.register(HuaJiKeyMappings.KEY_CHANGE_MODE);
        event.register(HuaJiKeyMappings.KEY_CHANGE_STAND_MODE);
        event.register(HuaJiKeyMappings.KEY_TRIGGER_STAND);
        event.register(HuaJiKeyMappings.KEY_STAND_SKILL);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(HuaJiLayers.ORGA_HAIR, () -> LayerDefinition.create(ModelOrgaArmor.createBodyLayer(LayerDefinitions.OUTER_ARMOR_DEFORMATION), 64, 64));
        event.registerLayerDefinition(HuaJiLayers.FIFTY_FIFTY_HELMET, () -> LayerDefinition.create(ModelFiftyFiftyHelmet.createBodyLayer(LayerDefinitions.OUTER_ARMOR_DEFORMATION), 64, 64));
        event.registerLayerDefinition(HuaJiLayers.LORD_LU, ModelLordLu::createBodyLayer);
        HuaJiStands.REGISTRY.get().forEach(stand -> stand.getModels().forEach(event::registerLayerDefinition));
    }

    @SubscribeEvent
    public static void addLayers(EntityRenderersEvent.AddLayers event) {
        addPlayerLayer(event, "default");
        addPlayerLayer(event, "slim");

        Minecraft mc = Minecraft.getInstance();
        for (EntityType<?> type : ForgeRegistries.ENTITY_TYPES) {
            addEntityLayer(event, mc.getEntityRenderDispatcher().renderers.get(type));
        }
    }

    @SubscribeEvent
    public static void onModifyBakingResult(ModelEvent.RegisterAdditional event) {
        HuaJiStands.REGISTRY.get().getKeys().forEach(resourceLocation -> event.register(ModelDisc.getModelResourceLocation(resourceLocation)));
    }

    @SubscribeEvent
    public static void onModifyBakingResult(ModelEvent.ModifyBakingResult event) {
        event.getModels().computeIfPresent(new ModelResourceLocation(HuaJiAgeMod.prefix("disc"), "inventory"),
                (resourceLocation, bakedModel) -> new ModelDisc(bakedModel));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void addPlayerLayer(EntityRenderersEvent.AddLayers event, String skin) {
        EntityRenderer<? extends Player> renderer = event.getSkin(skin);
        if (renderer instanceof LivingEntityRenderer livingRenderer) {
            livingRenderer.addLayer(new LayerLordLu(livingRenderer, event.getEntityModels()));
            livingRenderer.addLayer(new LayerStand(livingRenderer, event.getEntityModels()));
        }
    }


    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void addEntityLayer(EntityRenderersEvent.AddLayers event, EntityRenderer<?> renderer) {
        if (renderer instanceof LivingEntityRenderer livingRenderer) {
            livingRenderer.addLayer(new LayerLordLu(livingRenderer, event.getEntityModels()));
            livingRenderer.addLayer(new LayerStand(livingRenderer, event.getEntityModels()));
        }
    }
}
