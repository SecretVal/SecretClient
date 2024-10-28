package secretclient.modules;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.MinecraftClient;
import secretclient.event.EventHandler;
import secretclient.event.Events.TickEvent;

public class ModuleManager {
    public ArrayList<Module> modules = new ArrayList<Module>();
    private MinecraftClient client = MinecraftClient.getInstance();

    public void register(Module m) {
        modules.add(m);
    }

    public void toggleModule(Module m) {
        if (modules.get(modules.indexOf(m)).enabled) {
            disableModule(m);
        } else {
            enableModule(m);
        }
    }

    public Boolean isEnabled(Module m) {
        return modules.get(modules.indexOf(m)).enabled;
    }

    public List<Module> getActiveModules() {
        List<Module> activeModules = new ArrayList<>();
        for (Module m : this.modules) {
            if (m.enabled) {
                activeModules.add(m);
            }
        }
        return activeModules;
    }

    @EventHandler
    public void onStartTick(TickEvent e) {
        for (Module m : this.getActiveModules()) {
            if (e.client.player != null)
                m.onTick(e.client);
        }
    }

    private void enableModule(Module m) {
        modules.get(modules.indexOf(m)).enabled = true;
        m.onEnable(client);
    }

    private void disableModule(Module m) {
        modules.get(modules.indexOf(m)).enabled = false;
        m.onDisable(client);
    }
}
