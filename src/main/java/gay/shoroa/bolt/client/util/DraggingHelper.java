package gay.shoroa.bolt.client.util;

import java.util.function.Consumer;

public class DraggingHelper {
    private boolean dragging;
    private float dragX = 0, dragY = 0;

    public void handlePress(float mx, float my, float dragX, float dragY) {
        this.dragX = dragX-mx;
        this.dragY = dragY-my;
    }

    public void setPosition(float mx, float my, Consumer<Float> setX, Consumer<Float> setY) {
        setX.accept(mx+dragX);
        setY.accept(my+dragY);
    }
}