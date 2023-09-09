package gay.shoroa.bolt.client.nvg;

import gay.shoroa.bolt.client.util.BufferUtil;
import gay.shoroa.bolt.client.util.LinkedStorage;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.nanovg.NVGColor;

import java.awt.*;
import java.io.IOException;
import java.nio.ByteBuffer;

public class NVGHelper {
    public static int DRAW_STROKE = 0;
    public static int DRAW_FILL = 1;
    public static NVGColor nvgcolor(Color color) {
        return NVGColor.calloc().r(color.getRed()/255f).g(color.getGreen()/255f).b(color.getBlue()/255f).a(color.getAlpha()/255f);
    }

    public static int[] int_to_rgba(int color) {
        int[] rgba = new int[4];
        rgba[0] = color >> 24 & 0xFF;
        rgba[1] = color >> 16 & 0xFF;
        rgba[2] = color >> 8 & 0xFF;
        rgba[3] = color & 0xFF;
        return rgba;
    }

    public static void path(Runnable r, int mode) {
        NVGWrapper.beginPath();
        r.run();
        if(mode == 0) NVGWrapper.stroke();
        if(mode == 1) NVGWrapper.fill();
        NVGWrapper.closePath();
    }

    public static void path(float x, float y, Runnable r, int mode) {
        NVGWrapper.beginPath();
        NVGWrapper.moveTo(x,y);
        r.run();
        if(mode == 0) NVGWrapper.stroke();
        if(mode == 1) NVGWrapper.fill();
        NVGWrapper.closePath();
    }

    public static void initFont(String fontName, String fileName) {
        try {
            ByteBuffer data = BufferUtil.get().getResourceBytes(new ResourceLocation("bolt","fonts/"+fileName), 1024);
            LinkedStorage.put(data);
            NVGWrapper.createFontMem(fontName, data, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void initImage(String imgName, String fileName) {
        try {
            ByteBuffer data = BufferUtil.get().getResourceBytes(new ResourceLocation("bolt","img/"+fileName), 1024);
            LinkedStorage.put(data);
            int img = NVGWrapper.createImageMem(0, data);
            UI.imagesmap.put(imgName,img);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
