package gay.shoroa.bolt.client.ui;

import gay.shoroa.bolt.client.nvg.UI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public abstract class Screen extends GuiScreen {
    public abstract void open();
    public abstract void render(UI ui, float mx, float my, float delta);
    public abstract void click(float mx, float my, byte btn);
    public abstract void close();

    @Override
    public void initGui() {
        width = Display.getWidth();
        height = Display.getHeight();
        open();
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float ticks) {
        UI.get().render(ui -> render(ui,Mouse.getX(),height-Mouse.getY(), 1f/Minecraft.getDebugFPS()), width,height,1);
        super.drawScreen(mouseX, mouseY, ticks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int btn) {
        click(Mouse.getX(),height-Mouse.getY(), (byte)btn);
        super.mouseClicked(mouseX, mouseY, btn);
    }

    @Override
    public void onResize(Minecraft p_175273_0_, int mcIn, int w) {
        width = Display.getWidth();
        height = Display.getHeight();
        super.onResize(p_175273_0_, mcIn, w);
    }

    @Override
    public void setWorldAndResolution(Minecraft p_146280_0_, int mc, int width) {
        width = Display.getWidth();
        height = Display.getHeight();
        super.setWorldAndResolution(p_146280_0_, mc, width);
    }

    @Override
    public void onGuiClosed() {
        close();
        super.onGuiClosed();
    }
}
