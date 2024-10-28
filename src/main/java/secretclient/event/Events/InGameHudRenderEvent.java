package secretclient.event.Events;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import secretclient.event.Event;

public class InGameHudRenderEvent extends Event {
    public DrawContext context;
    public RenderTickCounter tickCounter;

    public InGameHudRenderEvent(DrawContext context, RenderTickCounter tickCounter) {
        this.context = context;
        this.tickCounter = tickCounter;
    }
}
