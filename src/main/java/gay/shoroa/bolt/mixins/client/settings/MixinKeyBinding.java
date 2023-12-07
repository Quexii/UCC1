package gay.shoroa.bolt.mixins.client.settings;

import gay.shoroa.bolt.access.AKeybinding;
import net.minecraft.client.settings.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(KeyBinding.class)
public class MixinKeyBinding implements AKeybinding {
    @Shadow private boolean pressed;

    @Override
    public void setPressed(boolean value) {
        pressed = value;
    }
}
