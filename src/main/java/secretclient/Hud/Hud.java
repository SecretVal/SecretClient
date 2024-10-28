package secretclient.Hud;

import java.util.List;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import secretclient.SecretClient;
import secretclient.event.EventHandler;
import secretclient.event.Events.InGameHudRenderEvent;
import secretclient.modules.Module;

public class Hud {
    @EventHandler
    public void render(InGameHudRenderEvent e) {
        MinecraftClient client = MinecraftClient.getInstance();
        TextRenderer textRenderer = client.textRenderer;
        int basicX = 5;
        int basicY = 5;
        List<Module> activeModules = SecretClient.moduleManager.getActiveModules();
        for (int i = 0; i < activeModules.size(); i++) {
            Module m = activeModules.get(i);
            e.context.drawTextWithShadow(textRenderer, m.getName(), basicX, basicY + i * 10, -1);
        }
    }
}
