package gay.shoroa.bolt.mixins.client.entity;

import gay.shoroa.bolt.client.Client;
import gay.shoroa.bolt.client.event.impl.EventUpdate;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP {
    @Inject(method = "onUpdate", at = @At("HEAD"))
    public void inject$onUpdate(CallbackInfo ci) {
        Client.get().bus().publish(new EventUpdate());
    }
}
