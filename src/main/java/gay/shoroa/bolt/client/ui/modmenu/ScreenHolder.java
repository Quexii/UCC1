package gay.shoroa.bolt.client.ui.modmenu;

import gay.shoroa.bolt.access.AEntityRenderer;
import gay.shoroa.bolt.client.Client;
import gay.shoroa.bolt.client.nvg.UI;
import gay.shoroa.bolt.client.ui.Screen;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class ScreenHolder extends Screen {
    @Override
    public void open() {
        ((AEntityRenderer) Minecraft.getMinecraft().entityRenderer).loadShaderLoc(new ResourceLocation("shaders/post/blur.json"));
        Client.get().modMenu().open();
    }

    @Override
    public void render(UI ui, float mx, float my, float delta) {

    }

    @Override
    public void click(float mx, float my, byte btn) {
        Client.get().modMenu().click(mx, my, btn);
    }

    @Override
    public void close() {
        Minecraft.getMinecraft().entityRenderer.stopUseShader();
        Client.get().modMenu().close();
    }
}
