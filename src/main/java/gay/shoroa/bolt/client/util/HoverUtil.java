package gay.shoroa.bolt.client.util;

import gay.shoroa.bolt.client.ui.comp.Comp;
import gay.shoroa.bolt.client.ui.comp.IconButton;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class HoverUtil {
    private static int mx = Mouse.getX();
    private static int my = Display.getHeight()-Mouse.getY();
    public static boolean rect(float x, float y, float width, float height) {
        mx = Mouse.getX();
        my = Display.getHeight()-Mouse.getY();
        return mx >= x && my >= y && mx <= x + width && my <= y + height;
    }
    public static boolean circle(float x, float y, float radius) {
        mx = Mouse.getX();
        my = Display.getHeight()-Mouse.getY();
        return Math.sqrt((x-mx)*(x-mx)+(y-my)*(y-my)) <= radius;
    }

    public static boolean comp(Comp c) {
        if(c.w() == c.h()) return circle(c.x()+ c.w()/2f, c.y() + c.h()/2f, c.w()/2f);
        else return rect(c.x(),c.y(),c.w(),c.h());
    }
}
