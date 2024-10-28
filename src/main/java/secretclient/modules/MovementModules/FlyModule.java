package secretclient.modules.MovementModules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;
import secretclient.modules.Module;

public class FlyModule extends Module {
    int counter = 60;
    float FLY_SPEED = 0.25f;

    @Override
    public String getName() {
        return "Flying";
    }

    @Override
    public void onEnable(MinecraftClient client) {
        client.player.getAbilities().allowFlying = true;
        client.player.getAbilities().setFlySpeed(FLY_SPEED);
    }

    @Override
    public void onDisable(MinecraftClient client) {
        client.player.getAbilities().flying = false;
        client.player.getAbilities().allowFlying = false;
    }

    @Override
    public void onTick(MinecraftClient client) {
        if (counter == 0) {
            Vec3d vel = client.player.getVelocity();
            client.player.setVelocity(new Vec3d(vel.x, -0.04, vel.z));
            counter = 40;
            return;
        }
        if (!client.player.getAbilities().allowFlying || client.player.getAbilities().getFlySpeed() != FLY_SPEED) {
            this.onEnable(client);
        }
        counter--;
    }

}
