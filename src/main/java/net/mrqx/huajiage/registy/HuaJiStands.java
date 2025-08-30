package net.mrqx.huajiage.registy;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.stand.*;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class HuaJiStands {
    public static final DeferredRegister<Stand> STANDS = DeferredRegister.create(Stand.REGISTRY_KEY, HuaJiAgeMod.MODID);
    public static final Supplier<IForgeRegistry<Stand>> REGISTRY = STANDS.makeRegistry(() -> new RegistryBuilder<Stand>().hasTags());

    public static final RegistryObject<Stand> HIEROPHANT_GREEN = STANDS
            .register("hierophant_green", StandHierophantGreen::new);
    public static final RegistryObject<Stand> THE_WORLD = STANDS
            .register("the_world", StandTheWorld::new);
    public static final RegistryObject<Stand> STAR_PLATINUM = STANDS
            .register("star_platinum", StandStarPlatinum::new);
    public static final RegistryObject<Stand> ORGA_REQUIEM = STANDS
            .register("orga_requiem", StandOrgaRequiem::new);
}
