package mod.schnappdragon.funnyfoxmod.core.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import mod.schnappdragon.funnyfoxmod.core.tags.FunnyFoxModItemTags;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.FoxHeldItemLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.Fox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoxHeldItemLayer.class)
public class FoxHoldInstrumentMixin {
    @Inject(
            method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/animal/Fox;FFFFFF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/Fox;getItemBySlot(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;")
    )
    private void funnyfoxmod_foxHoldInstrument(PoseStack p_117007_, MultiBufferSource p_117008_, int p_117009_, Fox p_117010_, float p_117011_, float p_117012_, float p_117013_, float p_117014_, float p_117015_, float p_117016_, CallbackInfo ci) {
        if (p_117010_.getItemBySlot(EquipmentSlot.MAINHAND).is(FunnyFoxModItemTags.FOX_CAN_PLAY)) {
            if (!p_117010_.isSleeping()) {
                p_117007_.mulPose(Vector3f.XP.rotationDegrees(90.0F));
                p_117007_.mulPose(Vector3f.YP.rotationDegrees(90.0F));
            } else {
                p_117007_.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
            }
        }
    }
}
