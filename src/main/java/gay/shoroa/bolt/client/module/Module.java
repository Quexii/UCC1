package gay.shoroa.bolt.client.module;

import gay.shoroa.bolt.client.Client;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true, chain = false)
public class Module {
    @Getter @Setter
    private boolean enabled;
    @Getter @NonNull
    private String name;
    @Getter @NonNull
    private Category category;
    public void toggle() {
        enabled(!enabled());
        if(enabled()) Client.get().bus().subscribe(this);
        else Client.get().bus().unsubscribe(this);
        onState(enabled());
    }
    public void onState(boolean state) {}
}
