package gay.shoroa.bolt.client.ui.comp;

import gay.shoroa.bolt.client.nvg.UI;
import gay.shoroa.bolt.client.util.HoverUtil;
import lombok.*;
import lombok.experimental.Accessors;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

@Getter
@Setter
@RequiredArgsConstructor
@Accessors(fluent = true,chain = false)
public abstract class Comp {
    @NonNull private float x,y,w,h;
    protected Minecraft mc = Minecraft.getMinecraft();
    public abstract void render(UI ui, float mx, float my, float delta);
    public abstract void click(float mx, float my, byte btn);

    public boolean hovered() {
        return HoverUtil.comp(this);
    }

    public boolean hovered(float x, float y, float w, float h) {
        return HoverUtil.rect(x,y,w,h);
    }
}
