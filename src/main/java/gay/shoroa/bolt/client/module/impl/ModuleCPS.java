package gay.shoroa.bolt.client.module.impl;

import gay.shoroa.bolt.client.module.Category;
import gay.shoroa.bolt.client.module.hud.TextModule;
import gay.shoroa.bolt.client.util.CPSUtil;
import net.minecraft.client.Minecraft;

public class ModuleCPS extends TextModule {
    public ModuleCPS() {
        super("CPS", Category.HUD);
    }

    @Override
    public String getText() {
        return String.format("[%d:%d]", CPSUtil.get().getLeftCPS(),CPSUtil.get().getRightCPS());
    }

    @Override
    public String getDummyText() {
        return "[0:0]";
    }
}
