package gay.shoroa.bolt.client.ui.modmenu.comp;

import gay.shoroa.bolt.client.Client;
import gay.shoroa.bolt.client.module.Module;
import gay.shoroa.bolt.client.nvg.NVGHelper;
import gay.shoroa.bolt.client.nvg.NVGWrapper;
import gay.shoroa.bolt.client.nvg.UI;
import gay.shoroa.bolt.client.ui.comp.*;
import gay.shoroa.bolt.client.ui.modmenu.ScreenEditMods;
import gay.shoroa.bolt.client.ui.modmenu.comp.pages.PageModule;
import gay.shoroa.bolt.client.ui.theme.Colors;
import gay.shoroa.bolt.client.util.ColorUtil;
import gay.shoroa.bolt.client.util.IconHelper;
import net.minecraft.client.Minecraft;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NVGPaint;
import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.injection.selectors.ISelectorContext;

import java.awt.*;
import java.util.ArrayList;

import static gay.shoroa.bolt.client.nvg.NVGHelper.nvgcolor;
import static gay.shoroa.bolt.client.nvg.NVGWrapper.linearGradient;

public class Window extends Comp {
    private ArrayList<Comp> comps = new ArrayList<>();
    private IconPageButton currentPageButton = null;
    public Window(float x, float y, float w, float h) {
        super(x, y, w, h);
        comps.clear();
        comps.add(new IconPageButton(IconHelper.box, new PageModule(x()+50, y()+50, w()-50, h()-50), x(), y()+70, 50, i -> i == currentPageButton, i -> currentPageButton = i));
        comps.add(new IconPageButton(IconHelper.cogs, null, x(), y()+70+51, 50, i -> i == currentPageButton, i -> currentPageButton = i));
        comps.add(new IconPageButton(IconHelper.brush, null, x(), y()+70+51*2, 50, i -> i == currentPageButton, i -> currentPageButton = i));
        comps.add(new IconPageButton(IconHelper.pen, null, x(), y()+h()-50, 50, i -> i == currentPageButton, i -> mc.displayGuiScreen(new ScreenEditMods())));
        currentPageButton = (IconPageButton) comps.get(0);
    }

    @Override
    public void render(UI ui, float mx, float my, float delta) {
        ui.dropShadow(x(),y(),w(),h(),20,70, ColorUtil.withAlpha(Color.BLACK,0.6f));
        drawMenuGradient();
        ui.text(x()+30, y()+30, 40, "special", IconHelper.Client.bolt, Colors.NEUTRAL(0), UI.Alignment.CENTER_MIDDLE);
        ui.text(x()+74,y()+26.5f, 30, "regular", "Bolt", Colors.NEUTRAL(0), UI.Alignment.LEFT_MIDDLE);

        for (Comp c : comps) {
            c.render(ui, mx, my, delta);
        }
    }

    @Override
    public void click(float mx, float my, byte btn) {
        for (Comp c : comps) {
            c.click(mx, my, btn);
        }
    }

    private void drawMenuGradient() {
        NVGColor nc1 = nvgcolor(Colors.PRIMARY(3));
        NVGColor nc2 = nvgcolor(Colors.SECONDARY(3));
        NVGColor nc3 = nvgcolor(Colors.NEUTRAL(0));
        NVGColor nc4 = nvgcolor(ColorUtil.TRANSPARENT);
        NVGPaint paint = NVGPaint.calloc();

        linearGradient(x()+w()/4f,y(),x()+w()-w()/4f,y()+h(), nc1, nc2, paint);

        NVGHelper.path(() -> {
            NVGWrapper.rrect(x(),y(),w(),h(),14);
            NVGWrapper.rrect(x()+50,y()+50,w()-51,h()-52,12,0,12,0);
            NVGWrapper.pathWinding(NanoVG.NVG_HOLE);
            NVGWrapper.fillPaint(paint);
        }, 1);
        NVGHelper.path(() -> {
            NVGWrapper.rrect(x()+50,y()+50,w()-50,h()-49,12,0,12,0);
            NVGWrapper.fillColor(nc3);
        }, 1);

        nc1.free();
        nc2.free();
        nc3.free();
        nc4.free();
        paint.free();
    }
}
