package gay.shoroa.bolt.client.util;

import gay.shoroa.bolt.client.event.impl.EventKey;
import gay.shoroa.bolt.client.event.impl.EventMouseClick;
import gay.shoroa.bolt.client.event.impl.EventUpdate;
import io.github.nevalackin.radbus.Listen;
import io.github.nevalackin.radbus.Listener;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;

public final class CPSUtil {
    private final static CPSUtil instance = new CPSUtil();
    private CPSUtil(){}
    public static CPSUtil get() {
        return instance;
    }
    private ArrayList<Long> rmbArray = new ArrayList<>();
    private ArrayList<Long> lmbArray = new ArrayList<>();
    private int leftBindCode = 0;
    private int rightBindCode = 0;

    @Listen
    public void updateListener(EventUpdate e) {
        this.rightBindCode = Minecraft.getMinecraft().gameSettings.keyBindUseItem.getKeyCode();
        this.leftBindCode = Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode();
    };
    @Listen
    public void mouseClickListener(EventMouseClick e) {
        handleClicks(e.button-100);
    }
    @Listen
    public void keyPressListener(EventKey e) {
        handleClicks(e.keycode);
    }

    private void handleClicks(int code) {
        if(code == leftBindCode) lmbArray.add(System.currentTimeMillis());
        if(code == rightBindCode) rmbArray.add(System.currentTimeMillis());
    }

    private int getCPS(ArrayList<Long> list) {
        list.removeIf(t -> System.currentTimeMillis() - t > 1000);
        return list.size();
    }

    public int getLeftCPS() {
        return getCPS(lmbArray);
    }
    public int getRightCPS() {
        return getCPS(rmbArray);
    }
}