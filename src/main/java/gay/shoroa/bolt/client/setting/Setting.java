package gay.shoroa.bolt.client.setting;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.function.Predicate;

@RequiredArgsConstructor
@Accessors(chain = true, fluent = true)
@Getter
@Setter
public class Setting<T> {
    private T value;
    private float min, max, step;
    @NonNull
    private String name;
    private boolean visible;

    public Setting<T> visible (Predicate<Setting<T>> visibleIf) {
        visible = visibleIf.test(this);
        return this;
    }

    public Setting<Float> constrains (float min, float max, float step) {
        this.min = min;
        this.max = max;
        this.step = step;
        return (Setting<Float>) this;
    }
}
