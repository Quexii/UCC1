package gay.shoroa.bolt.mixins.client.gui;

import gay.shoroa.bolt.client.Client;
import gay.shoroa.bolt.client.event.impl.EventRender2D;
import net.minecraft.client.gui.GuiIngame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngame.class)
public class MixinGuiIngame {
    @Inject(method = "renderGameOverlay", at = @At("TAIL"))
    public void inject$renderGameOverlay(CallbackInfo ci) {
        Client.get().bus().publish(new EventRender2D());
    }
}
