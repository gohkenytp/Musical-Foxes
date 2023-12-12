package mod.schnappdragon.musicalfoxes.core.mixin;

import mod.schnappdragon.musicalfoxes.core.tags.MusicalFoxesItemTags;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.InstrumentItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(Fox.class)
public abstract class FoxReplaceDeathSoundMixin {
    @Inject(
            method = "getDeathSound",
            at = @At("HEAD"),
            cancellable = true)
    private void musicalfoxes_foxReplaceAmbientSound(CallbackInfoReturnable<SoundEvent> cir) {
        ItemStack itemstack = ((Fox) (Object) this).getItemBySlot(EquipmentSlot.MAINHAND);

        if (itemstack.is(MusicalFoxesItemTags.FOX_CAN_PLAY) && itemstack.getItem() instanceof InstrumentItem instrumentItem) {
            Optional<? extends Holder<Instrument>> optionalInstrumentHolder = instrumentItem.getInstrument(itemstack);
            optionalInstrumentHolder.ifPresent(instrumentHolder -> cir.setReturnValue(instrumentHolder.value().soundEvent().value()));
        }
    }
}
