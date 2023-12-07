package gay.shoroa.bolt.client.ui.comp;

import gay.shoroa.bolt.client.nvg.NVGWrapper;
import gay.shoroa.bolt.client.nvg.UI;
import gay.shoroa.bolt.client.ui.theme.Colors;
import gay.shoroa.bolt.client.util.ScrollHelper;
import lombok.NonNull;

import java.util.ArrayList;

public class ScrollList extends Comp {
    private ArrayList<? extends Comp> comps;
    private ScrollHelper sh = new ScrollHelper();
    private int columns;
    public ScrollList(ArrayList<? extends Comp> comps, int columns, @NonNull float x, @NonNull float y, @NonNull float w, @NonNull float h) {
        super(x, y, w, h);
        this.comps = comps;
        this.columns = columns;
    }

    @Override
    public void render(UI ui, float mx, float my, float delta) {
        float elementsHeight = -10;
        int columnCount = columns;
        for (Comp c : comps) {
            if(columnCount == columns) {
                elementsHeight += c.h() + 10;
                columnCount = 0;
            }
            columnCount++;
        }
        sh.setElementsHeight(elementsHeight);
        sh.setFlag(hovered());
        sh.setReversed(true);
        sh.setMaxScroll(h());
        sh.setSpeed(100);
        sh.setStep(45);

        float sc = sh.getScroll();

        NVGWrapper.save();
        NVGWrapper.intersectScissor(x(),y(),w(),h());
        float i = 0;
        columnCount = 0;
        float compWidth = w()/columns-10;
        for (Comp c : comps) {
            c.x(x()+(compWidth+10)*columnCount);
            c.y(y()-sc+i);
            c.w(compWidth);

            columnCount++;
            c.render(ui, mx, my, delta);
            if(columnCount == columns){
                columnCount = 0;
                i += c.h()+10;
            }
        }
        NVGWrapper.restore();

        float scf = (h()/elementsHeight);
        float scs = (sc/elementsHeight)*h();
        if(scf < 1) {
            ui.rrect(x() + w() - 4, y(), 4, h(), 1.5f, Colors.NEUTRAL(3));
            ui.rrect(x() + w() - 4, y() + scs, 4, scf * h(), 1.5f, Colors.MID(3));
        }
    }

    @Override
    public void click(float mx, float my, byte btn) {
        for (Comp c : comps) {
            float hoverX = hovered() ? mx : -1;
            float hoverY = hovered() ? my : -1;
            c.click(hoverX,hoverY,btn);
        }
    }
}
