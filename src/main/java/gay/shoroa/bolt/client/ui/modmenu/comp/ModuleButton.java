package gay.shoroa.bolt.client.ui.modmenu.comp;

import gay.shoroa.bolt.client.module.Module;
import gay.shoroa.bolt.client.nvg.UI;
import gay.shoroa.bolt.client.ui.comp.Comp;
import gay.shoroa.bolt.client.ui.theme.Colors;
import gay.shoroa.bolt.client.util.ColorUtil;
import gay.shoroa.bolt.client.util.HoverUtil;
import gay.shoroa.bolt.client.util.IconHelper;
import lombok.NonNull;
import me.surge.animation.Animation;
import me.surge.animation.Easing;

import java.awt.*;

public class ModuleButton extends Comp {
    private Module module;
    private Animation toggleAnimation = new Animation(() -> 200F, false, () -> Easing.LINEAR);
    public ModuleButton(Module module, @NonNull float x, @NonNull float y, @NonNull float w, @NonNull float h) {
        super(x, y, w, h);
        this.module = module;
    }

    @Override
    public void render(UI ui, float mx, float my, float delta) {
        toggleAnimation.setState(module.enabled());

        Color prim = Colors.MID(4);
        Color bg = Colors.NEUTRAL(1);
        Color toggleBG = ColorUtil.interpolate(bg,prim, toggleAnimation.getAnimationFactor());

        ui.rrect(x(),y(),w(),h(),10, bg);
        ui.text(x()+12, y()+h()/2f+1, 20, "regular", module.name(), prim, UI.Alignment.LEFT_MIDDLE);
        //temp icon
        ui.text(x()+w()-35+1,y()+h()-35,32,"special", IconHelper.Client.bolt, toggleBG, UI.Alignment.CENTER_MIDDLE);
        ui.text(x()+w()-35+1,y()+h()-35,32,"special", IconHelper.Client.bolt_outline, prim, UI.Alignment.CENTER_MIDDLE);
    }

    @Override
    public void click(float mx, float my, byte btn) {
        if(btn == 0 && hovered())
            module.toggle();
    }
}
