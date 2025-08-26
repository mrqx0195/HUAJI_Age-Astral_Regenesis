package net.mrqx.huajiage;

import com.google.common.base.CaseFormat;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.mrqx.huajiage.client.renderer.entity.RenderFivePower;
import net.mrqx.huajiage.client.renderer.entity.RenderHeroArrow;
import net.mrqx.huajiage.client.renderer.entity.RenderItemBullet;
import net.mrqx.huajiage.client.renderer.entity.RenderRoadRoller;
import net.mrqx.huajiage.config.HuaJiClientConfig;
import net.mrqx.huajiage.config.HuaJiCommonConfig;
import net.mrqx.huajiage.entity.EntityFivePower;
import net.mrqx.huajiage.entity.EntityHeroArrow;
import net.mrqx.huajiage.entity.EntityItemBullet;
import net.mrqx.huajiage.entity.EntityRoadRoller;
import net.mrqx.huajiage.network.NetworkManager;
import net.mrqx.huajiage.registy.*;
import org.slf4j.Logger;

@Mod(HuaJiAgeMod.MODID)
public class HuaJiAgeMod {
    public static final String MODID = "huajiage";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation prefix(String s) {
        return new ResourceLocation(MODID, s);
    }

    public HuaJiAgeMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        HuaJiItems.ITEMS.register(modEventBus);
        HuaJiBlocks.BLOCKS.register(modEventBus);
        HuaJiBlocks.BLOCK_ENTITIES.register(modEventBus);
        HuaJiCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        HuaJiEffects.MOB_EFFECTS.register(modEventBus);
        HuaJiMenus.CONTAINER_TYPES.register(modEventBus);
        HuaJiRecipes.RECIPE_TYPES.register(modEventBus);
        HuaJiRecipes.RECIPE_SERIALIZER.register(modEventBus);
        HuaJiSoundEvents.SOUND_EVENTS.register(modEventBus);
        HuaJiStands.STANDS.register(modEventBus);
        NetworkManager.register();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, HuaJiCommonConfig.COMMON_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, HuaJiClientConfig.CLIENT_CONFIG);
    }

    @Mod.EventBusSubscriber(
            bus = Mod.EventBusSubscriber.Bus.MOD
    )
    public static class RegistryEvents {
        public static final ResourceLocation ENTITY_HERO_ARROW_RESOURCE_LOCATION = prefix(classToString(EntityHeroArrow.class));
        public static final ResourceLocation ENTITY_ITEM_BULLET_RESOURCE_LOCATION = prefix(classToString(EntityItemBullet.class));
        public static final ResourceLocation ENTITY_ROAD_ROLLER_RESOURCE_LOCATION = prefix(classToString(EntityRoadRoller.class));
        public static final ResourceLocation ENTITY_ENTITY_FIVE_POWER_RESOURCE_LOCATION = prefix(classToString(EntityFivePower.class));
        @SuppressWarnings("NotNullFieldNotInitialized")
        public static EntityType<EntityHeroArrow> heroArrowEntityType;
        @SuppressWarnings("NotNullFieldNotInitialized")
        public static EntityType<EntityItemBullet> itemBulletEntityType;
        @SuppressWarnings("NotNullFieldNotInitialized")
        public static EntityType<EntityRoadRoller> roadRollerEntityType;
        @SuppressWarnings("NotNullFieldNotInitialized")
        public static EntityType<EntityFivePower> fivePowerEntityType;

        @SubscribeEvent
        public static void register(RegisterEvent event) {
            event.register(ForgeRegistries.Keys.ENTITY_TYPES, entityTypeRegisterHelper -> {
                heroArrowEntityType = EntityType.Builder
                        .of(EntityHeroArrow::create, MobCategory.MISC).sized(0.5F, 0.5F)
                        .setTrackingRange(8).setUpdateInterval(3)
                        .setCustomClientFactory(EntityHeroArrow::createInstance)
                        .build(ENTITY_HERO_ARROW_RESOURCE_LOCATION.toString());
                entityTypeRegisterHelper.register(ENTITY_HERO_ARROW_RESOURCE_LOCATION, heroArrowEntityType);

                itemBulletEntityType = EntityType.Builder
                        .of(EntityItemBullet::create, MobCategory.MISC).sized(0.2F, 0.2F)
                        .setTrackingRange(8).setUpdateInterval(3)
                        .setCustomClientFactory(EntityItemBullet::createInstance)
                        .build(ENTITY_ITEM_BULLET_RESOURCE_LOCATION.toString());
                entityTypeRegisterHelper.register(ENTITY_ITEM_BULLET_RESOURCE_LOCATION, itemBulletEntityType);

                roadRollerEntityType = EntityType.Builder
                        .of(EntityRoadRoller::create, MobCategory.MISC).sized(2, 2)
                        .setTrackingRange(8).setUpdateInterval(3)
                        .setCustomClientFactory(EntityRoadRoller::createInstance)
                        .build(ENTITY_ROAD_ROLLER_RESOURCE_LOCATION.toString());
                entityTypeRegisterHelper.register(ENTITY_ROAD_ROLLER_RESOURCE_LOCATION, roadRollerEntityType);

                fivePowerEntityType = EntityType.Builder
                        .of(EntityFivePower::create, MobCategory.MISC).sized(0.7F, 0.7F)
                        .setTrackingRange(8).setUpdateInterval(3)
                        .setCustomClientFactory(EntityFivePower::createInstance)
                        .build(ENTITY_ENTITY_FIVE_POWER_RESOURCE_LOCATION.toString());
                entityTypeRegisterHelper.register(ENTITY_ENTITY_FIVE_POWER_RESOURCE_LOCATION, fivePowerEntityType);
            });
        }

        @SubscribeEvent
        public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(heroArrowEntityType, RenderHeroArrow::new);
            event.registerEntityRenderer(itemBulletEntityType, RenderItemBullet::new);
            event.registerEntityRenderer(roadRollerEntityType, RenderRoadRoller::new);
            event.registerEntityRenderer(fivePowerEntityType, RenderFivePower::new);
        }

        private static String classToString(Class<? extends Entity> entityClass) {
            return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entityClass.getSimpleName()).replace("entity_", "");
        }

    }
}
