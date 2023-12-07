package gay.shoroa.bolt.client.ui.comp;

import gay.shoroa.bolt.client.nvg.UI;
import lombok.NonNull;

public abstract class Page extends Comp {
    public Page(@NonNull float x, @NonNull float y, @NonNull float w, @NonNull float h) {
        super(x, y, w, h);
    }
}
