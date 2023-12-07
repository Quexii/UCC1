package gay.shoroa.bolt.client.module;

import gay.shoroa.bolt.client.module.hud.HudModule;
import gay.shoroa.bolt.client.setting.Exclude;
import gay.shoroa.bolt.client.setting.Setting;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

public class ModuleManager {
    private LinkedHashMap<Class<? extends Module>,Module> moduleMap = new LinkedHashMap<>();

    public void add(Class<? extends Module> klass, Module module) {
        moduleMap.put(klass, module);
    }

    public void finish() {
        for (Module m : modules()) {
            for (Field f : m.getClass().getDeclaredFields()) {
                 if(Setting.class.isAssignableFrom(f.getType())) {
                     f.setAccessible(true);
                     try {
                         Setting<?> set = (Setting<?>) f.get(m);
                         if(!set.getClass().isAnnotationPresent(Exclude.class)) {
                             m.settings().add(set);
                         }
                     } catch (IllegalAccessException e) {
                         throw new RuntimeException(e);
                     }
                 }
            }
        }
        moduleMap.values().stream().sorted(Comparator.comparingInt(a -> a.name().toLowerCase().charAt(0)));
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
