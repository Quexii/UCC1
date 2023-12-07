package gay.shoroa.bolt.client.module.impl;

import gay.shoroa.bolt.access.AKeybinding;
import gay.shoroa.bolt.client.module.Category;
import gay.shoroa.bolt.client.module.hud.TextModule;
import lombok.NonNull;

public class ModuleToggleSprint extends TextModule {
    private boolean state = false;
    public ModuleToggleSprint() {
        super("ToggleSprint", Category.PLAYER);
    }

    @Override
    public String getText() {
        if(enabled()) {
            if (mc.gameSettings.keyBindSprint.isPressed()) state = !state;
            if (state) ((AKeybinding) mc.gameSettings.keyBindSprint).setPressed(true);
        }
        if(state) {
            return "Sprinting : Toggled";
        } else {
            return  "";
        }
    }

    @Override
    public String getDummyText() {
        return "Sprinting: Toggled";
    }
}
