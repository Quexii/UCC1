package gay.shoroa.bolt.client.ui.comp;

import gay.shoroa.bolt.client.nvg.NVGWrapper;
import gay.shoroa.bolt.client.nvg.UI;
import gay.shoroa.bolt.client.ui.theme.Colors;
import gay.shoroa.bolt.client.util.ColorUtil;
import lombok.Setter;
import me.surge.animation.Animation;
import me.surge.animation.Easing;

import java.awt.*;

public class Button extends Comp {
    private ButtonStyle style;
    private Color color;
    private String text;
    private Runnable onClick;
    private Animation hover = new Animation(() -> 200F,  false, () -> Easing.LINEAR);
    private Animation tooltipAnim = new Animation(() -> 100F,  false, () -> Easing.LINEAR);
    protected String font = "regular";
    private String tooltip = "";
    private Direction tooltipDirection = Direction.RIGHT;
    private long hoverTimer = 0, lastTime = 0;
    public Button(String text, ButtonStyle style, Color color, float x, float y, Runnable onClick) {
        super(x, y, 0, 0);
        w(UI.get().textWidth(text, font, 20) + 20*2);
        h(UI.get().textHeight(font, 20) + 10*2);
        this.text = text;
        this.style = style;
        this.color = color;
        this.onClick = onClick;
    }

    @Override
    public void render(UI ui, float mx, float my, float delta) {
        if(hovered()) {
            hoverTimer = Math.min(500, System.currentTimeMillis() - lastTime);
        } else {
            lastTime = System.currentTimeMillis();
            hoverTimer = 0L;
        }

        hover.setState(hovered());
        tooltipAnim.setState(hoverTimer >= 500);

        Color textColor = color;
        Color fillColor = ColorUtil.interpolate(color, Colors.NEUTRAL(0), hover.getAnimationFactor()/5f);
        switch (style) {
            case FILLED: textColor = Colors.NEUTRAL(0);
                break;
            case OUTLINE: fillColor = ColorUtil.interpolate(Colors.NEUTRAL(0), color, 0.05f + hover.getAnimationFactor()/10f);
                break;
            case TEXT: fillColor = ColorUtil.interpolate(Colors.NEUTRAL(0), color, 0.25f - hover.getAnimationFactor()/10f);
                break;
        }

        if(style.equals(ButtonStyle.OUTLINE)) {
            ui.rrect(x(),y(),w(),h(),h()/2f, textColor);
            ui.rrect(x()+2,y()+2,w()-4,h()-4,h()/2f-2, fillColor);
        } else {
            ui.rrect(x(),y(),w(),h(),h()/2f, fillColor);
        }
        ui.text(x()+w()/2f, y()+h()/2f+1, font.equals("icon") ? 24 : 20,font,text, textColor, UI.Alignment.CENTER_MIDDLE);

        if(!tooltip.isEmpty()) {
            Color tooltipTXT = ColorUtil.withAlpha(color, 1-(float) tooltipAnim.getAnimationFactor());
            Color tooltipBG = ColorUtil.withAlpha(Colors.NEUTRAL(0), 1-(float) tooltipAnim.getAnimationFactor());
            float tooltipWidth = ui.textWidth(tooltip,"regular",16) + 20;
            float tooltipHeight = ui.textHeight(tooltip,16) + 30;
            switch (tooltipDirection) {
                case TOP:
                    ui.dropShadow(x()+w()/2f-tooltipWidth/2f, y()-tooltipHeight-10, tooltipWidth, tooltipHeight, 10, 20,ColorUtil.withAlpha(tooltipTXT, 0.2f));
                    ui.rrect(x()+w()/2f-tooltipWidth/2f, y()-tooltipHeight-10, tooltipWidth, tooltipHeight, 10, tooltipBG);
                    ui.text(x()+w()/2f, y()-tooltipHeight/2f-10+1, 16, "regular", tooltip, tooltipTXT, UI.Alignment.CENTER_MIDDLE);
                    break;
                case LEFT:
                    ui.dropShadow(x()-tooltipWidth-10, y()+h()/2f-tooltipHeight/2f, tooltipWidth, tooltipHeight, 10, 20,ColorUtil.withAlpha(tooltipTXT, 0.2f));
                    ui.rrect(x()-tooltipWidth-10, y()+h()/2f-tooltipHeight/2f, tooltipWidth, tooltipHeight, 10, tooltipBG);
                    ui.text(x()-tooltipWidth/2f-10, y()+h()/2f+1, 16, "regular", tooltip, tooltipTXT, UI.Alignment.CENTER_MIDDLE);
                    break;
                case RIGHT:
                    ui.dropShadow(x()+w()+10, y()+h()/2f-tooltipHeight/2f, tooltipWidth, tooltipHeight, 10, 20,ColorUtil.withAlpha(tooltipTXT, 0.2f));
                    ui.rrect(x()+w()+10, y()+h()/2f-tooltipHeight/2f, tooltipWidth, tooltipHeight, 10, tooltipBG);
                    ui.text(x()+w()+tooltipWidth/2f+10, y()+h()/2f+1, 16, "regular", tooltip, tooltipTXT, UI.Alignment.CENTER_MIDDLE);
                    break;
                case BOTTOM:
                    ui.dropShadow(x()+w()/2f-tooltipWidth/2f, y()+h()+10, tooltipWidth, tooltipHeight, 10, 20,ColorUtil.withAlpha(tooltipTXT, 0.2f));
                    ui.rrect(x()+w()/2f-tooltipWidth/2f, y()+h()+10, tooltipWidth, tooltipHeight, 10, tooltipBG);
                    ui.text(x()+w()/2f, y()+h()+tooltipHeight/2f+10+1, 16, "regular", tooltip, tooltipTXT, UI.Alignment.CENTER_MIDDLE);
                    break;
            }
        }
    }

    @Override
    public void click(float mx, float my, byte btn) {
        if(hovered() && btn == 0) onClick.run();
    }

    public Button tooltip(String text, Direction dir) {
        this.tooltip = text;
        this.tooltipDirection = dir;
        return this;
    }
}
