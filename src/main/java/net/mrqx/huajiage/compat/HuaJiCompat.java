package net.mrqx.huajiage.compat;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.mrqx.huajiage.compat.curios.CuriosItemFactory;
import net.mrqx.huajiage.compat.ltc2.LaTiaoCraftCompat;
import net.mrqx.huajiage.compat.slashblade.SlashBladeCompat;
import net.mrqx.huajiage.compat.tacz.LordLuWingGunsRenderer;
import net.mrqx.huajiage.compat.tacz.TaczEvents;
import net.mrqx.huajiage.item.ItemInfiniteCharm;
import org.apache.logging.log4j.util.LoaderUtil;

public class HuaJiCompat {
    public static final TagKey<Item> LATIAO_TAG = ItemTags.create(ResourceLocation.fromNamespaceAndPath("ltc2", "latiao"));
    public static final TagKey<Item> CHARM_ITEM_TAG = ItemTags.create(ResourceLocation.fromNamespaceAndPath("curios", "charm"));
    private static final HuaJiCompat INSTANCE = new HuaJiCompat();

    public static HuaJiCompat getInstance() {
        return INSTANCE;
    }

    public final boolean hasJEI;
    public final boolean hasPatchouli;
    public final boolean hasCurios;
    public final boolean hasFantasyEnding;
    public final boolean hasSlashBlade;
    public final boolean hasTetra;
    public final boolean hasLaTiaoCraft;
    public final boolean hasApothicAttributes;
    public final boolean hasTacz;

    private HuaJiCompat() {
        this.hasJEI = LoaderUtil.isClassAvailable("mezz.jei.api.JeiPlugin");
        this.hasPatchouli = LoaderUtil.isClassAvailable("vazkii.patchouli.api.IComponentProcessor");
        this.hasCurios = LoaderUtil.isClassAvailable("top.theillusivec4.curios.api.type.capability.ICurioItem");
        this.hasFantasyEnding = LoaderUtil.isClassAvailable("com.mega.uom.ModSource");
        this.hasSlashBlade = LoaderUtil.isClassAvailable("mods.flammpfeil.slashblade.SlashBlade");
        this.hasTetra = LoaderUtil.isClassAvailable("se.mickelus.tetra.TetraMod");
        this.hasLaTiaoCraft = LoaderUtil.isClassAvailable("com.doggystudio.chirencqr.ltc.server.LatiaoCraft");
        this.hasApothicAttributes = LoaderUtil.isClassAvailable("dev.shadowsoffire.attributeslib.AttributesLib");
        this.hasTacz = LoaderUtil.isClassAvailable("com.tacz.guns.GunMod");
    }

    public static void register(IEventBus modEventBus) {
        if (HuaJiCompat.getInstance().hasSlashBlade) {
            SlashBladeCompat.register(modEventBus);
        }
        if (HuaJiCompat.getInstance().hasTacz) {
            TaczEvents.register();
        }
    }

    public static void commonSetup() {
        if (HuaJiCompat.getInstance().hasLaTiaoCraft) {
            LaTiaoCraftCompat.init();
        }
    }

    public static class Factories {
        public static Item makeInfiniteCharmCurios() {
            if (HuaJiCompat.getInstance().hasCurios) {
                return CuriosItemFactory.makeInfiniteCharm();
            }
            return new ItemInfiniteCharm();
        }

        @OnlyIn(Dist.CLIENT)
        public static <T extends LivingEntity> void renderLordLuWingGuns(PoseStack pPoseStack, MultiBufferSource pBuffer, T pLivingEntity, ItemStack wing) {
            if (HuaJiCompat.getInstance().hasTacz) {
                LordLuWingGunsRenderer.renderLordLuWingGuns(pPoseStack, pBuffer, pLivingEntity, wing);
            }
        }

        public static void tryLordLuWingShoot(LivingEntity living) {
            TaczEvents.tryWingShoot(living);
        }
    }
}
