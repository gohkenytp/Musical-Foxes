package mod.schnappdragon.musicalfoxes.core;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(MusicalFoxes.MODID)
public class MusicalFoxes {
    public static final String MODID = "musicalfoxes";

    public MusicalFoxes() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}