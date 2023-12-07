package gay.shoroa.bolt.client.ui.modmenu.comp.pages;

import gay.shoroa.bolt.client.Client;
import gay.shoroa.bolt.client.module.Module;
import gay.shoroa.bolt.client.nvg.UI;
import gay.shoroa.bolt.client.ui.comp.Page;
import gay.shoroa.bolt.client.ui.comp.ScrollList;
import gay.shoroa.bolt.client.ui.modmenu.comp.ModuleButton;
import lombok.NonNull;

import java.util.ArrayList;

public class PageModule extends Page {
    private ScrollList sc;
    public PageModule(@NonNull float x, @NonNull float y, @NonNull float w, @NonNull float h) {
        super(x, y, w, h);
        ArrayList<ModuleButton> mods = new ArrayList<>();
        for (Module m : Client.get().moduleManager().modules()) {
            mods.add(new ModuleButton(m, x()+10, y()+40, 200, 70));
        }

        sc = new ScrollList(mods,3,x()+10,y()+10,w()-10,h()-10);
    }

    @Override
    public void render(UI ui, float mx, float my, float delta) {
        sc.render(ui, mx, my, delta);
    }

    @Override
    public void click(float mx, float my, byte btn) {
        sc.click(mx, my, btn);
    }
}
