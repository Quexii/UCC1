package gay.shoroa.bolt.client.ui.comp;

import gay.shoroa.bolt.client.nvg.UI;
import gay.shoroa.bolt.client.util.HoverUtil;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import java.awt.*;

public class IconButton extends Button {
    public IconButton(String text, ButtonStyle style, Color color, float x, float y, Runnable onClick) {
        super(text, style, color, x, y, onClick);
        font = "icon";
        w(UI.get().textWidth(text, font, 20) + 20);
        h(w());
    }
}
