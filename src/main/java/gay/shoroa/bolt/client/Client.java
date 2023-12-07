package gay.shoroa.bolt.client;

import gay.shoroa.bolt.client.event.Event;
import gay.shoroa.bolt.client.event.impl.EventRender2D;
import gay.shoroa.bolt.client.event.impl.EventStart;
import gay.shoroa.bolt.client.event.impl.EventStop;
import gay.shoroa.bolt.client.module.hud.HudModule;
import gay.shoroa.bolt.client.module.ModuleManager;
import gay.shoroa.bolt.client.module.impl.*;
import gay.shoroa.bolt.client.module.impl.keystrokes.ModuleKeystrokes;
import gay.shoroa.bolt.client.nvg.NVGHelper;
import gay.shoroa.bolt.client.nvg.UI;
import gay.shoroa.bolt.client.ui.modmenu.ScreenEditMods;
import gay.shoroa.bolt.client.ui.modmenu.ScreenHolder;
import gay.shoroa.bolt.client.ui.modmenu.ScreenModMenu;
import gay.shoroa.bolt.client.util.CPSUtil;
import io.github.nevalackin.radbus.Listen;
import io.github.nevalackin.radbus.PubSub;
import lombok.Getter;
import lombok.experimental.Accessors;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.legacyfabric.fabric.impl.client.keybinding.KeyBindingRegistryImpl;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.shader.Framebuffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import tv.twitch.broadcast.FrameBuffer;


@Accessors(chain = false, fluent = true)
public class Client implements ClientModInitializer {
    private static Client instance;
    @Getter private PubSub<Event> bus;
    @Getter private static final Logger logger = LogManager.getLogger("BOLT");
    @Getter private ModuleManager moduleManager;
    @Getter private KeyBinding key_clickGui = new KeyBinding("key.bolt.clickgui",Keyboard.KEY_RSHIFT,"key.bolt.category");
    @Getter private ScreenModMenu modMenu;
    private final Minecraft mc = Minecraft.getMinecraft();
    @Override
    public void onInitializeClient() {
        instance = this;
        KeyBindingRegistryImpl.registerKeyBinding(key_clickGui);

        bus = PubSub.newInstance(System.err::println);
        bus.subscribe(this);
        bus.subscribe(CPSUtil.get());
    }

    public static Client get() {
        return instance;
    }

    @Listen
    public void onStart(EventStart e) {
        //enable depth stencil on mc FBO

        logger.info("Starting Client");

        logger.debug("Initializing NanoVG UI");
        UI.init();
        logger.debug("Loading Fonts");
        for (String s : new String[]{"black.otf", "bold.otf", "extrabold.otf", "icon.ttf", "regular.otf", "thin.otf", "special.ttf"})
            NVGHelper.initFont(s.split("\\.")[0],s);
        logger.debug("Loading Images");
        for (String s : new String[]{"logo.png", "logo-16.png", "logo-32.png", "logo-64.png", "logo-128.png", "logo-256.png"})
            NVGHelper.initImage(s.split("\\.")[0],s);

        logger.debug("Initializing ModuleManager");
        moduleManager = new ModuleManager();
        logger.debug("Adding Modules");
        moduleManager.add(ModuleFPS.class,new ModuleFPS());
        moduleManager.add(ModuleToggleSprint.class,new ModuleToggleSprint());
        moduleManager.add(ModuleCPS.class,new ModuleCPS());
        moduleManager.add(ModuleReachDisplay.class,new ModuleReachDisplay());
        moduleManager.add(ModuleFullbright.class,new ModuleFullbright());
        moduleManager.add(ModuleKeystrokes.class,new ModuleKeystrokes());

        if(!FabricLoader.getInstance().isModLoaded("optifabric")) { //optifine not preesnt
            //load stuff such as zoom
        }
        moduleManager.finish();

        modMenu = new ScreenModMenu();
    };
    @Listen
    public void onStop(EventStop e) {
        logger.info("Stopping Client");
    };


    @Listen
    public void onRender(EventRender2D e) {
        if(key_clickGui.isPressed())
            mc.displayGuiScreen(new ScreenHolder());
        if(!(mc.currentScreen instanceof ScreenEditMods))
            for (HudModule m : moduleManager.hudmodules()) {
                if(m.x()<0)m.x(0);
                if(m.y()<0)m.y(0);
                if(m.x()+m.width()>Display.getWidth())m.x(Display.getWidth()-m.width());
                if(m.y()+m.height()>Display.getHeight())m.y(Display.getHeight()-m.height());

                if(m.enabled()) {
                    UI.get().render(ui -> m.draw(true), Display.getWidth(),Display.getHeight(),1);
                    m.draw(false);
                }
            }
        UI.get().render(ui -> modMenu.render(ui, Mouse.getX(), Display.getHeight()-Mouse.getY(), 1f / Minecraft.getDebugFPS()),
                Display.getWidth(),Display.getHeight(),1);
    }
}
