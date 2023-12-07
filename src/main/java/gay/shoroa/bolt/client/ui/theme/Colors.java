package gay.shoroa.bolt.client.ui.theme;

import java.awt.*;
import java.util.HashMap;

public class Colors {
    private static Color[] primaryColors = {new Color(0xe8c30b),new Color(0xFFD500),new Color(0xFFE047),new Color(0xFFE76A),new Color(0xFFEF9D),new Color(0xFFFBE5)};
    private static Color[] midColors = {new Color(0xEA7F34),new Color(0xFF8936),new Color(0xFF9952),new Color(0xFFA86B),new Color(0xFFBA89),new Color(0xFFD4B5)};
    private static Color[] secondaryColors = {new Color(0xD84040),new Color(0xF44646),new Color(0xFC5454),new Color(0xFF6C6C),new Color(0xFF9999),new Color(0xFFBFBF)};
    private static Color[] neutralColors = {new Color(0x313131),new Color(0x434343),new Color(0x505050),new Color(0x5F5E5E),new Color(0x8B8B8B),new Color(0xD5D5D5)};

    public static Color PRIMARY(int i) {
        return fromArray(primaryColors,i);
    }
    public static Color MID(int i) {
        return fromArray(midColors,i);
    }
    public static Color SECONDARY(int i) {
        return fromArray(secondaryColors,i);
    }
    public static Color NEUTRAL(int i) {
        return fromArray(neutralColors,i);
    }

    private static Color fromArray(Color[] cArr, int i) {
        if(i > cArr.length-1) return cArr[cArr.length-1];
        if(i < 0) return cArr[0];
        return cArr[i];
    }
}
