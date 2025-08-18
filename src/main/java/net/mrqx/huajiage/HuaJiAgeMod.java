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
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.mrqx.huajiage.client.renderer.RenderHeroArrow;
import net.mrqx.huajiage.entity.EntityHeroArrow;
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
        NetworkManager.register();
    }

    @Mod.EventBusSubscriber(
            bus = Mod.EventBusSubscriber.Bus.MOD
    )
    public static class RegistryEvents {
        public static final ResourceLocation ENTITY_HERO_ARROW_RESOURCE_LOCATION = new ResourceLocation(MODID, classToString(EntityHeroArrow.class));
        public static EntityType<EntityHeroArrow> heroArrowEntityType;

        @SubscribeEvent
        public static void register(RegisterEvent event) {
            event.register(ForgeRegistries.Keys.ENTITY_TYPES, entityTypeRegisterHelper -> {
                heroArrowEntityType = EntityType.Builder
                        .of(EntityHeroArrow::create, MobCategory.MISC).sized(0.5F, 0.5F)
                        .setTrackingRange(4).setUpdateInterval(20)
                        .setCustomClientFactory(EntityHeroArrow::createInstance)
                        .build(ENTITY_HERO_ARROW_RESOURCE_LOCATION.toString());
                entityTypeRegisterHelper.register(ENTITY_HERO_ARROW_RESOURCE_LOCATION, heroArrowEntityType);
            });
        }

        @SubscribeEvent
        public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(heroArrowEntityType, RenderHeroArrow::new);
        }

        private static String classToString(Class<? extends Entity> entityClass) {
            return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entityClass.getSimpleName()).replace("entity_", "");
        }

    }
}
