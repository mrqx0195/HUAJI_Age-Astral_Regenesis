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
    public static final DeferredRegister<AbstractStand> STANDS = DeferredRegister.create(AbstractStand.REGISTRY_KEY, HuaJiAgeMod.MODID);
    public static final Supplier<IForgeRegistry<AbstractStand>> REGISTRY = STANDS.makeRegistry(RegistryBuilder::new);

    public static final RegistryObject<AbstractStand> HIEROPHANT_GREEN = STANDS
            .register("hierophant_green", StandHierophantGreen::new);
    public static final RegistryObject<AbstractStand> THE_WORLD = STANDS
            .register("the_world", StandTheWorld::new);
    public static final RegistryObject<AbstractStand> STAR_PLATINUM = STANDS
            .register("star_platinum", StandStarPlatinum::new);
    public static final RegistryObject<AbstractStand> ORGA_REQUIEM = STANDS
            .register("orga_requiem", StandOrgaRequiem::new);
}
