package secretclient.modules.VisualModules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import secretclient.modules.Module;

public class NightVisionModule extends Module {
    @Override
    public String getName() {
        return "Night Vision";
    }

    @Override
    public void onEnable(MinecraftClient client) {
        client.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, -1, 255));
    }

    @Override
    public void onDisable(MinecraftClient client) {
        client.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
    }

    @Override
    public void onTick(MinecraftClient client) {
        if (!client.player.hasStatusEffect(StatusEffects.NIGHT_VISION)) {
            this.onEnable(client);
        }
    }
}
