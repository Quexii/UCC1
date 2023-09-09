package gay.shoroa.bolt.client.module;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    private LinkedHashMap<Class<? extends Module>,Module> moduleMap = new LinkedHashMap<>();

    public void add(Class<? extends Module> klass, Module module) {
        moduleMap.put(klass, module);
    }

    public List<Module> modules() {
        return new ArrayList<>(moduleMap.values());
    }
    public List<HudModule> hudmodules() {
        ArrayList<HudModule> a = new ArrayList<>();
        moduleMap.values().stream().filter(m -> m instanceof HudModule).forEach(m -> a.add((HudModule) m));
        return a;
    }

    public <T extends Module> T get(Class<? extends Module> klass) {
        return (T) moduleMap.get(klass);
    }
}
