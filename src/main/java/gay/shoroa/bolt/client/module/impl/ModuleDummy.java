package gay.shoroa.bolt.client.module.impl;

import gay.shoroa.bolt.client.module.Category;
import gay.shoroa.bolt.client.module.HudModule;
import lombok.NonNull;

import java.awt.*;

public class ModuleDummy extends HudModule {
    public ModuleDummy() {
        super("Dummy", Category.HUD);
    }

    @Override
    public void draw(boolean nvg) {
        if(nvg) {
            ui.rect(x(),y(),width(),height(), Color.RED);
        }
    }

    @Override
    public void dummy() {
        ui.rect(x(),y(),width(),height(), Color.BLUE);
    }

    @Override
    public float width() {
        return 180;
    }

    @Override
    public float height() {
        return 30;
    }
}
