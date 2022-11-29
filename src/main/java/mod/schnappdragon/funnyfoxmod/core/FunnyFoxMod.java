package mod.schnappdragon.funnyfoxmod.core;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(FunnyFoxMod.MODID)
public class FunnyFoxMod {
    public static final String MODID = "funnyfoxmod";

    public FunnyFoxMod() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}