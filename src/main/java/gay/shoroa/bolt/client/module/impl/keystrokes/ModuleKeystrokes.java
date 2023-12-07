package gay.shoroa.bolt.client.module.impl.keystrokes;

import gay.shoroa.bolt.client.event.impl.EventKey;
import gay.shoroa.bolt.client.event.impl.EventMouseClick;
import gay.shoroa.bolt.client.module.Category;
import gay.shoroa.bolt.client.module.hud.HudModule;
import gay.shoroa.bolt.client.nvg.NVGWrapper;
import io.github.nevalackin.radbus.Listen;
import lombok.NonNull;

import java.util.ArrayList;

public class ModuleKeystrokes extends HudModule {
    public ModuleKeystrokes() {
        super("Keystrokes", Category.HUD);
    }

    private ArrayList<Key> keys = new ArrayList<>();

    @Override
    public void onState(boolean state) {
        if(state) {
            populateKeys();
        } else {
            keys.clear();
        }
        super.onState(state);
    }

    @Override
    public void draw(boolean nvg) {
        if(nvg) {
            NVGWrapper.translate(x(), y());
            for (Key k : keys) {
                k.render(ui);
            }
            NVGWrapper.translate(-x(), -y());
        }
    }

    @Override
    public void dummy() {
        draw(true);
    }

    private void populateKeys() {
        float keySize = 40;
        float gap = 4;
        keys.add(new Key(mc.gameSettings.keyBindForward, keySize + gap,0,keySize));
        keys.add(new Key(mc.gameSettings.keyBindLeft, 0,keySize + gap,keySize));
        keys.add(new Key(mc.gameSettings.keyBindBack, keySize + gap,keySize + gap,keySize));
        keys.add(new Key(mc.gameSettings.keyBindRight, keySize + gap + keySize + gap,keySize + gap,keySize));
        keys.add(new Key(mc.gameSettings.keyBindJump, 0,keySize + gap + keySize + gap, width(),keySize));
        keys.add(new Key(mc.gameSettings.keyBindAttack, 0,(keySize + gap)*3,width()/2f-gap/2f,keySize));
        keys.add(new Key(mc.gameSettings.keyBindUseItem, width()/2f+gap/2f,(keySize + gap)*3,width()/2f-gap/2f,keySize));
    }

    @Override
    public float width() {
        return 128;
    }

    @Override
    public float height() {
        return 172;
    }
}
