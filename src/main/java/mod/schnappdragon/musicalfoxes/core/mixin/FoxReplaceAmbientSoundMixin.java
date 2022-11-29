package mod.schnappdragon.musicalfoxes.core.mixin;

import mod.schnappdragon.musicalfoxes.core.tags.MusicalFoxesItemTags;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.InstrumentItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Optional;

@Mixin(Fox.class)
public abstract class FoxReplaceAmbientSoundMixin {
    @Inject(
            method = "playAmbientSound",
            at = @At("HEAD"),
            cancellable = true)
    private void musicalfoxes_foxReplaceAmbientSound(CallbackInfo ci) {
        Fox fox = (Fox) (Object) this;
        ItemStack itemstack = fox.getItemBySlot(EquipmentSlot.MAINHAND);
        SoundEvent soundevent = null;

        if (itemstack.is(MusicalFoxesItemTags.FOX_CAN_PLAY) && itemstack.getItem() instanceof InstrumentItem instrumentItem) {
            Optional<Holder<Instrument>> optionalInstrumentHolder = instrumentItem.getInstrument(itemstack);

            if (optionalInstrumentHolder.isPresent()) {
                soundevent = optionalInstrumentHolder.get().get().soundEvent();
            }
        }

        if (soundevent != null) {
            if (fox.isSleeping()) {
                fox.playSound(soundevent, 0.15F, fox.getVoicePitch());
            } else {
                if (!fox.level.isDay() && fox.getRandom().nextFloat() < 0.1F) {
                    List<Player> list = fox.level.getEntitiesOfClass(Player.class, fox.getBoundingBox().inflate(16.0D, 16.0D, 16.0D), EntitySelector.NO_SPECTATORS);
                    if (list.isEmpty()) {
                        fox.playSound(soundevent, 2.0F, fox.getVoicePitch());
                    }
                } else {
                    fox.playSound(soundevent, 1.0F, fox.getVoicePitch());
                }
            }
        }

        ci.cancel();
    }
}
