package secretclient.KeyBinds;

import java.util.ArrayList;

import net.minecraft.client.MinecraftClient;
import secretclient.SecretClient;
import secretclient.KeyBinds.KeyBind.OnPress;
import secretclient.modules.Module;

public class ModuleKeybinds {
    public void register() {
        ArrayList<Module> modules = SecretClient.moduleManager.modules;

        for (Module m : modules) {
            SecretClient.keyBindManager.register(
                    new KeyBind(String.format("keys.secretclient.%s", m.getName()), -1, new OnPress() {
                        @Override
                        public void onPress(MinecraftClient client) {
                            SecretClient.moduleManager.toggleModule(m);
                        }
                    }, "secretclient.config.keys"));
        }
    };
}
