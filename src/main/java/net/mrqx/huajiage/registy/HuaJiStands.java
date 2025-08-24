package net.mrqx.huajiage.registy;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.stand.AbstractStand;
import net.mrqx.huajiage.stand.StandHierophantGreen;
import net.mrqx.huajiage.stand.StandTheWorld;

import java.util.function.Supplier;

public class HuaJiStands {
    public static final DeferredRegister<AbstractStand> STANDS = DeferredRegister.create(AbstractStand.REGISTRY_KEY, HuaJiAgeMod.MODID);
    public static final Supplier<IForgeRegistry<AbstractStand>> REGISTRY = STANDS.makeRegistry(RegistryBuilder::new);

    public static final RegistryObject<AbstractStand> HIEROPHANT_GREEN = STANDS
            .register("hierophant_green", StandHierophantGreen::new);
    public static final RegistryObject<AbstractStand> THE_WORLD = STANDS
            .register("the_world", StandTheWorld::new);
}
