package secretclient.modules.MovementModules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import secretclient.modules.Module;

public class NofallModule extends Module {

    @Override
    public String getName() {
        return "NoFall";
    }

    @Override
    public void onTick(MinecraftClient client) {
        client.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true));
    }

    @Override
    public void onEnable(MinecraftClient client) {
    }

    @Override
    public void onDisable(MinecraftClient client) {
    }
}
