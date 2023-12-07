package gay.shoroa.bolt.client.module.impl;

import gay.shoroa.bolt.client.event.impl.EventUpdate;
import gay.shoroa.bolt.client.module.Category;
import gay.shoroa.bolt.client.module.Module;
import io.github.nevalackin.radbus.Listen;
import io.github.nevalackin.radbus.Listener;

public class ModuleFullbright extends Module {
    float old = 0;
    public ModuleFullbright() {
        super("Fullbright", Category.MISC);
    }

    @Override
    public void onState(boolean state) {
        if(state) {
            old = mc.gameSettings.gammaSetting;
        } else {
            mc.gameSettings.gammaSetting = old;
        }
        super.onState(state);
    }

    @Listen
    public void updateListener(EventUpdate e) {
        mc.gameSettings.gammaSetting = 10000;
    }
}