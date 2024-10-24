package secretclient;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import secretclient.hacks.FlyHack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecretClient implements ClientModInitializer {
    public static final String MOD_ID = "secretclient";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static Boolean FlyingEnabled = false;
    public static Boolean NightVisionEnabled = false;

    @Override
    public void onInitializeClient() {
        ClientTickEvents.START_CLIENT_TICK.register(new FlyHack());
    }
}
