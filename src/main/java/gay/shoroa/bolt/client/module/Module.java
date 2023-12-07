package gay.shoroa.bolt.client.module;

import gay.shoroa.bolt.client.Client;
import gay.shoroa.bolt.client.setting.Setting;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;

@RequiredArgsConstructor
@Accessors(fluent = true, chain = false)
public class Module {
    @Getter @Setter
    private boolean enabled;
    @Getter @NonNull
    private String name;
    @Getter @NonNull
    private Category category;
    @Getter
    private ArrayList<Setting> settings = new ArrayList<Setting>();
    protected Minecraft mc = Minecraft.getMinecraft();
    public void toggle() {
        enabled(!enabled());
        if(enabled()) Client.get().bus().subscribe(this);
        else Client.get().bus().unsubscribe(this);
        onState(enabled());
    }
    public void onState(boolean state) {}
}
