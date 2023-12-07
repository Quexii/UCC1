package gay.shoroa.bolt.mixins.client.entity;

import gay.shoroa.bolt.client.Client;
import gay.shoroa.bolt.client.event.impl.EventEntityHurt;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase {
    @Inject(method = "handleStatusUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;playSound(Ljava/lang/String;FF)V", ordinal = 0, shift = At.Shift.BEFORE))
    public void injectHandleHealthUpdateB1(CallbackInfo ci) {
        Client.get().bus().publish(new EventEntityHurt((EntityLivingBase) (Object) this));
    }
}
