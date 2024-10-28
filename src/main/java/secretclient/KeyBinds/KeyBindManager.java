package secretclient.KeyBinds;

import java.util.HashMap;
import java.util.Map;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import secretclient.event.EventHandler;
import secretclient.event.Events.TickEvent;

public class KeyBindManager {
    public HashMap<KeyBind, KeyBinding> keybinds = new HashMap<>();

    public void register(KeyBind bind) {
        KeyBinding keyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                bind.Name,
                bind.Key,
                bind.Category));

        keybinds.put(bind, keyBind);
    }

    public void register(KeyBind[] binds) {
        for (KeyBind bind : binds) {
            register(bind);
        }
    }

    @EventHandler
    public void onTickEvent(TickEvent e) {
        for (Map.Entry<KeyBind, KeyBinding> pair : keybinds.entrySet()) {
            KeyBind bind = pair.getKey();
            KeyBinding keyBinding = pair.getValue();

            while (keyBinding.wasPressed()) {
                bind.onPress.onPress(e.client);
            }
        }
    }
}
