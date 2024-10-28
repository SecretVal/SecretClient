package secretclient.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import secretclient.SecretClient;
import secretclient.event.Events.InGameHudRenderEvent;

@Mixin(InGameHud.class)
class InGameHudMixin {
    @Inject(at = @At("HEAD"), method = "render")
    public void render(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        SecretClient.eventManager.post(new InGameHudRenderEvent(context, tickCounter));
    }
}
