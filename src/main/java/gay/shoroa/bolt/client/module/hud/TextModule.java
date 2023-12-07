package gay.shoroa.bolt.client.module.hud;

import gay.shoroa.bolt.client.module.Category;
import gay.shoroa.bolt.client.nvg.NVGWrapper;
import gay.shoroa.bolt.client.nvg.UI;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.awt.*;

public class TextModule extends HudModule {
    @Getter @Setter
    private String text, dummyText;
    public TextModule(@NonNull String name, @NonNull Category category) {
        super(name, category);
    }

    @Override
    public void draw(boolean nvg) {
        if(getText().isEmpty()) return;
        if(nvg) {
            ui.rect(x(), y(), width(), height(), new Color(0x61000000, true));
            ui.text(x() + width() / 2f, y() + height() / 2f + 1, 20, "regular", getText(), Color.WHITE, UI.Alignment.CENTER_MIDDLE);
        }
    }

    @Override
    public void dummy() {
        ui.rect(x(),y(),width(),height(),new Color(0x61000000, true));
        ui.text(x()+width()/2f,y()+height()/2f+1, 20, "regular", getDummyText(), Color.WHITE, UI.Alignment.CENTER_MIDDLE);
    }

    @Override
    public float width() {
        return UI.get().textWidth(isDummy() ? getDummyText():getText(),"regular",20)+20;
    }

    @Override
    public float height() {
        return UI.get().textHeight("regular",20)+10;
    }
}
