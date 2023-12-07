package gay.shoroa.bolt.mixins.client.render;

import gay.shoroa.bolt.access.AEntityRenderer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer implements AEntityRenderer {
    @Shadow protected abstract void loadShader(ResourceLocation p_175069_0_);

    @Override
    public void loadShaderLoc(ResourceLocation loc) {
        loadShader(loc);
    }
}
