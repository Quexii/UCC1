package gay.shoroa.bolt.client.module.hud;

import gay.shoroa.bolt.client.event.impl.EventRender2D;
import gay.shoroa.bolt.client.module.Category;
import gay.shoroa.bolt.client.module.Module;
import gay.shoroa.bolt.client.nvg.UI;
import gay.shoroa.bolt.client.ui.modmenu.ScreenEditMods;
import io.github.nevalackin.radbus.Listen;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

@Accessors(fluent = true, chain = false)
public abstract class HudModule extends Module {
    @Getter @Setter
    private float x,y,width,height;
    protected UI ui = UI.get();
    public HudModule(@NonNull String name, @NonNull Category category) {
        super(name, category);
    }
    public abstract void draw(boolean nvg);
    public abstract void dummy();

    public boolean hovered() {
        return Mouse.getX() >= x() && Display.getHeight()-Mouse.getY() >= y() && Mouse.getX() <= x() + width() && Display.getHeight()-Mouse.getY() <= y() + height();
    }

    public boolean isDummy() {
        return mc.currentScreen instanceof ScreenEditMods;
    }
}
