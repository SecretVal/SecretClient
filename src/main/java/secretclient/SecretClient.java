package secretclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import secretclient.Hud.Hud;
import secretclient.KeyBinds.KeyBindManager;
import secretclient.KeyBinds.ModuleKeybinds;
import secretclient.event.EventManager;
import secretclient.event.Events.TickEvent;
import secretclient.modules.ModuleManager;
import secretclient.modules.MovementModules.FlyModule;
import secretclient.modules.MovementModules.NofallModule;
import secretclient.modules.VisualModules.NightVisionModule;

public class SecretClient implements ClientModInitializer {
    public static final String MOD_ID = "secretclient";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static ModuleManager moduleManager = new ModuleManager();
    public static EventManager eventManager = new EventManager();
    public static KeyBindManager keyBindManager = new KeyBindManager();
    public static Hud hud = new Hud();

    public static FlyModule flyModule = new FlyModule();
    public static NightVisionModule nightVisionModule = new NightVisionModule();
    public static NofallModule nofallModule = new NofallModule();
    public static Boolean XrayEnabled = false;

    @Override
    public void onInitializeClient() {
        moduleManager.register(flyModule);
        moduleManager.register(nightVisionModule);
        moduleManager.register(nofallModule);

        new ModuleKeybinds().register();

        eventManager.register(hud);

        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            eventManager.post(new TickEvent(client));
            moduleManager.onStartTick(new TickEvent(client));
            keyBindManager.onTickEvent(new TickEvent(client));
        });
    }
}
