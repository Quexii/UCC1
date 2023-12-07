package gay.shoroa.bolt.client.module.impl;

import gay.shoroa.bolt.client.module.Category;
import gay.shoroa.bolt.client.module.hud.TextModule;
import lombok.NonNull;
import net.minecraft.client.Minecraft;

public class ModuleFPS extends TextModule {
    public ModuleFPS() {
        super("FPS", Category.HUD);
    }

    @Override
    public String getText() {
        return "FPS: " + Minecraft.getDebugFPS();
    }

    @Override
    public String getDummyText() {
        return "FPS: 999";
    }
}
