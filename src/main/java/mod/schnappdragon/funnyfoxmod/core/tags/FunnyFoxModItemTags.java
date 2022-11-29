package mod.schnappdragon.funnyfoxmod.core.tags;

import mod.schnappdragon.funnyfoxmod.core.FunnyFoxMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class FunnyFoxModItemTags {
    public static final TagKey<Item> FOX_CAN_PLAY = makeTag("fox_can_play");

    private static TagKey<Item> makeTag(String id) {
        return ItemTags.create(new ResourceLocation(FunnyFoxMod.MODID, id));
    }
}