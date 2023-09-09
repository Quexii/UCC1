package gay.shoroa.bolt.client.ui;

import gay.shoroa.bolt.client.Client;
import gay.shoroa.bolt.client.module.HudModule;
import gay.shoroa.bolt.client.nvg.UI;
import gay.shoroa.bolt.client.util.DraggingHelper;

public class ScreenEditMods extends Screen {
    private HudModule selected;
    private final DraggingHelper dragHelper = new DraggingHelper();
    @Override
    public void open() {

    }

    @Override
    public void render(UI ui, float mx, float my, float delta) {
        for (HudModule m : Client.get().moduleManager().hudmodules()) {
            if(m.enabled()) m.dummy();
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
    public void close() {

    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
