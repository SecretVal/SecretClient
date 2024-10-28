package secretclient.event.Events;

import net.minecraft.client.MinecraftClient;
import secretclient.event.Event;

public class TickEvent extends Event {
    public MinecraftClient client;

    public TickEvent(MinecraftClient client) {
        this.client = client;
    }
}
