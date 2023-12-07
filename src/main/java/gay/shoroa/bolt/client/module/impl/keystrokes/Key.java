package gay.shoroa.bolt.client.module.impl.keystrokes;

import gay.shoroa.bolt.client.nvg.NVGHelper;
import gay.shoroa.bolt.client.nvg.NVGWrapper;
import gay.shoroa.bolt.client.nvg.UI;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.nanovg.NVGColor;

import java.awt.*;
import java.util.ArrayList;

@RequiredArgsConstructor
public class Key {
    @NonNull private KeyBinding key;
    @NonNull float x,y,w,h;
    private ArrayList<Circle> cirlcles = new ArrayList<>();
    public Key(@NonNull KeyBinding key,@NonNull float x,@NonNull float y,@NonNull float size) {
        this(key,x,y,size,size);
    }

    public void render(UI ui) {
        cirlcles.removeIf(c -> cirlcles.size()>200||c.f>=w*2);
        if(key.isPressed()) cirlcles.add(new Circle());

        String str = key.getKeyCode() < 0 ? Mouse.getButtonName(key.getKeyCode()+100) : Keyboard.getKeyName(key.getKeyCode());
        String s = "";
        if(str.startsWith("BUTTON")) {
            int btn = 49-(int)str.toCharArray()[str.length()-1];
            if(btn==0) s = "L";
            else if(btn==1) s = "R";
            else if(btn==2) s = "M";
            else s = btn+"";
            str = s+"MB";
        }

        ui.rect(x,y,w,h,new Color(0x77000000, true));
        ui.text(x+w/2f,y+h/2f,20, "regular", str, Color.WHITE, UI.Alignment.CENTER_MIDDLE);

        for (Circle c : cirlcles) {
            c.render(ui);
        }
    }

    class Circle {
        float f = 0f;
        void render(UI ui) {
            NVGWrapper.save();
            NVGWrapper.scissor(x,y,w,h);
            if(f <= w*2) {
                f += (1f / Minecraft.getDebugFPS())*w*2;
            }
            float alpha = 1f-f/w/2;

            NVGColor c = NVGHelper.nvgcolor(new Color(1f,1f,1f,Math.min(Math.max(alpha/1.6f, 0), 1)));
            NVGHelper.path(() -> {
                NVGWrapper.circle(x+w/2f,y+h/2f,f);
                NVGWrapper.fillColor(c);
            }, 1);
            c.free();
            NVGWrapper.restore();
        }
    }
}
