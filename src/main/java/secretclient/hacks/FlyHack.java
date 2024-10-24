package secretclient.hacks;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.StartTick;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import secretclient.SecretClient;

public class FlyHack implements StartTick {

    int counter = 79;

    @Override
    public void onStartTick(MinecraftClient client) {
        if (SecretClient.FlyingEnabled) {
            ClientPlayerEntity p = client.player;
            if (client.player != null) {
                if (counter == 0) {
                    p.setVelocity(0, -0.5, 0);
                    counter = 79;
                    return;
                }
                if (client.options.jumpKey.isPressed()) {
                    p.setVelocity(0, 0.25, 0);
                } else {
                    p.setVelocity(p.getVelocity().getX(), 0, p.getVelocity().getZ());
                }
                p.setMovementSpeed(100);
                counter--;
            }
        }
    }

}
