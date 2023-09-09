package gay.shoroa.bolt.mixins.lwjgl;

import gay.shoroa.bolt.funcprov.Lwjgl2FunctionProvider;
import org.lwjgl.system.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(targets = "org.lwjgl.nanovg.NanoVGGLConfig")
public abstract class MixinNanoVGGLConfig {
    @Inject(method = "getFunctionProvider", at = @At("HEAD"),
            cancellable = true, remap = false)
    private static void getFunctionProvider(String className, CallbackInfoReturnable<FunctionProvider> cir) {
       cir.setReturnValue(new Lwjgl2FunctionProvider());
    }
}