package gay.shoroa.bolt.mixins.client;

import gay.shoroa.bolt.client.Client;
import gay.shoroa.bolt.client.event.impl.EventKey;
import gay.shoroa.bolt.client.event.impl.EventMouseClick;
import gay.shoroa.bolt.client.event.impl.EventStart;
import gay.shoroa.bolt.client.event.impl.EventStop;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiIngame;<init>(Lnet/minecraft/client/Minecraft;)V"))
    public void inject$initGuiIngame(CallbackInfo ci) {
        Client.get().bus().publish(new EventStart());
    }

    @Inject(method = "shutdown", at =@At("HEAD"))
    public void inject$shutdown(CallbackInfo ci) {
        Client.get().bus().publish(new EventStop());
    }

    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding;onTick(I)V", ordinal = 0))
    public void inject$runTickMouseInput(CallbackInfo ci) {
        Client.get().bus().publish(new EventMouseClick(Mouse.getEventButton()));
    }
    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding;onTick(I)V", ordinal = 1))
    public void inject$runTickKeyInput(CallbackInfo ci) {
        Client.get().bus().publish(new EventKey(Keyboard.getEventKey()));
    }
}
