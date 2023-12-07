package gay.shoroa.bolt.client.ui.modmenu;

import gay.shoroa.bolt.client.Client;
import gay.shoroa.bolt.client.module.hud.HudModule;
import gay.shoroa.bolt.client.nvg.UI;
import gay.shoroa.bolt.client.ui.Screen;
import gay.shoroa.bolt.client.util.DraggingHelper;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import java.awt.*;

public class ScreenEditMods extends Screen {
    private HudModule selected;
    private final DraggingHelper dragHelper = new DraggingHelper();
    private Robot robot;
    @Override
    public void open() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void render(UI ui, float mx, float my, float delta) {
        for (HudModule m : Client.get().moduleManager().hudmodules()) {
            if(m.x()<0)m.x(0);
            if(m.y()<0)m.y(0);
            if(m.x()+m.width()> Display.getWidth())m.x(Display.getWidth()-m.width());
            if(m.y()+m.height()>Display.getHeight())m.y(Display.getHeight()-m.height());

            if(m.enabled()) {
                m.dummy();
                if(m.hovered()) {
                    ui.rect(m.x(), m.y(), 1, m.height(), Color.WHITE);
                    ui.rect(m.x()+m.width()-1, m.y(), 1, m.height(), Color.WHITE);
                    ui.rect(m.x(), m.y(), m.width(), 1, Color.WHITE);
                    ui.rect(m.x(), m.y()+m.height()-1, m.width(), 1, Color.WHITE);
                }
            }
        }

        if(selected != null)
            dragHelper.setPosition(mx,my,f -> selected.x(f), f -> selected.y(f));
    }

    @Override
    public void click(float mx, float my, byte btn) {
        for (HudModule m : Client.get().moduleManager().hudmodules()) {
            if(m.hovered() && btn == 0) {
                selected = m;
                dragHelper.handlePress(mx,my,m.x(),m.y());
            }
        }
    }

    @Override
    protected void mouseReleased(int p_146286_0_, int mouseX, int mouseY) {
        selected = null;
        super.mouseReleased(p_146286_0_, mouseX, mouseY);
    }

    @Override
    protected void keyTyped(char character, int keycode) {
        if(selected != null) {
            Point mousePos = MouseInfo.getPointerInfo().getLocation();
            switch (keycode) {
                case Keyboard.KEY_LEFT:
                    robot.mouseMove(mousePos.x - 1, mousePos.y);
                    break;
                case Keyboard.KEY_RIGHT:
                    robot.mouseMove(mousePos.x + 1, mousePos.y);
                    break;
                case Keyboard.KEY_UP:
                    robot.mouseMove(mousePos.x, mousePos.y - 1);
                    break;
                case Keyboard.KEY_DOWN:
                    robot.mouseMove(mousePos.x, mousePos.y + 1);
                    break;
            }
        }
        super.keyTyped(character, keycode);
    }

    @Override
    public void close() {

    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
