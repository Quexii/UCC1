package gay.shoroa.bolt.client.ui.modmenu;

import gay.shoroa.bolt.access.AEntityRenderer;
import gay.shoroa.bolt.client.nvg.NVGHelper;
import gay.shoroa.bolt.client.nvg.NVGWrapper;
import gay.shoroa.bolt.client.nvg.UI;
import gay.shoroa.bolt.client.ui.Screen;
import gay.shoroa.bolt.client.ui.comp.*;
import gay.shoroa.bolt.client.ui.modmenu.comp.Window;
import gay.shoroa.bolt.client.ui.theme.Colors;
import gay.shoroa.bolt.client.util.IconHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.EXTPackedDepthStencil;

import java.util.ArrayList;

public class ScreenModMenu {

    public static float ui_alpha = 0;
    private boolean ui_state = false;
    private Window window;
    public void open() {
        float winW = 854;
        float winH = 480;
        window = new Window(Display.getWidth()/2f-winW/2f,Display.getHeight()/2f-winH/2f,winW,winH);
        ui_state = true;
    }

    public void render(UI ui, float mx, float my, float delta) {
        if(ui_state) ui_alpha += delta*8;
        else ui_alpha -= delta*8;
        ui_alpha = Math.max(0, Math.min(ui_alpha,1));

        int width = Display.getWidth();
        int height = Display.getHeight();

        NVGWrapper.globalAlpha(ui_alpha);
        NVGWrapper.translate(width/2f, height/2f);
        NVGWrapper.scale(0.75f+ui_alpha/4f,0.75f+ui_alpha/4f);
        NVGWrapper.translate(-width/2f, -height/2f);

        if(window != null)
            window.render(ui, mx, my, delta);
    }

    public void click(float mx, float my, byte btn) {
        if(window != null)
            window.click(mx, my, btn);
    }

    public void close() {
        ui_state = false;
    }
}
