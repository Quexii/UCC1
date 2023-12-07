package gay.shoroa.bolt.client.ui.modmenu.comp;

import com.google.common.base.Suppliers;
import gay.shoroa.bolt.client.nvg.NVGWrapper;
import gay.shoroa.bolt.client.nvg.UI;
import gay.shoroa.bolt.client.ui.comp.Comp;
import gay.shoroa.bolt.client.ui.comp.Page;
import gay.shoroa.bolt.client.ui.modmenu.ScreenModMenu;
import gay.shoroa.bolt.client.ui.theme.Colors;
import gay.shoroa.bolt.client.util.ColorUtil;
import lombok.NonNull;
import me.surge.animation.Animation;
import me.surge.animation.Easing;

import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class IconPageButton extends Comp {
    private String icon;
    private Page page;
    private Predicate<IconPageButton> sup;
    private Consumer<IconPageButton> onClick;
    private Animation selected = new Animation(200F, false, Easing.EXPO_OUT);
    private Animation hovered = new Animation(150F, false, Easing.EXPO_OUT);
    public IconPageButton(String icon, Page page, @NonNull float x, @NonNull float y, @NonNull float size, Predicate<IconPageButton> sup, Consumer<IconPageButton> onClick) {
        super(x, y, size, size);
        this.icon = icon;
        this.page = page;
        this.sup = sup;
        this.onClick = onClick;
    }

    @Override
    public void render(UI ui, float mx, float my, float delta) {
        selected.setState(sup.test(this));
        hovered.setState(hovered());

        Color textColor = ColorUtil.interpolate(Colors.NEUTRAL(0), Colors.PRIMARY(4), selected.getLinearFactor());
        Color bgColor = ColorUtil.interpolate(ColorUtil.TRANSPARENT, Colors.NEUTRAL(0), selected.getLinearFactor()+hovered.getLinearFactor());

        float inverseSelect = (float) (1f-selected.getAnimationFactor());
        float revFactor = (float) (inverseSelect-0.2*hovered.getAnimationFactor()*inverseSelect);

        NVGWrapper.save();
        NVGWrapper.scissor(x(),y(),w(),h());
        ui.rrect(x()+6-(w()-3)*revFactor,y()+6,w()-12,h()-12,12,Colors.NEUTRAL(0));
//        ui.rrect(x()+6-revFactor*5,y()+6-revFactor*5,w()-12+revFactor*10,h()-12+revFactor*10,12,bgColor);
//        ui.text(x()+w()/2f, y()+h()/2f, w()/1.5f, "icon", icon, textColor, UI.Alignment.CENTER_MIDDLE);
        ui.text(x()+w()/2f, y()+h()/2f, w()/1.4f, "icon", icon, textColor, UI.Alignment.CENTER_MIDDLE);
        NVGWrapper.restore();

        if(page != null) {
            NVGWrapper.globalAlpha((float) (selected.getLinearFactor() * ScreenModMenu.ui_alpha));
            page.render(ui, mx, my, delta);
            NVGWrapper.globalAlpha(ScreenModMenu.ui_alpha);
        }
    }

    @Override
    public void click(float mx, float my, byte btn) {
        if(hovered() && btn == 0)
            onClick.accept(this);

        if(page != null) page.click(mx, my, btn);
    }
}
