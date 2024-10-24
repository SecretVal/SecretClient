package secretclient.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ButtonWidget.PressAction;
import net.minecraft.text.Text;

import secretclient.screens.SecretOptionsScreen;
import secretclient.utils.Utils;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin extends Screen {
    protected GameMenuScreenMixin(Text text) {
        super(text);
    }

    @Inject(at = @At("HEAD"), method = "initWidgets()V")
    private void initWidgets(CallbackInfo info) {
        this.addDrawableChild(Utils.createButton(10, 10, 80, 20, Text.of("SecretOptions"), new PressAction() {
            @Override
            public void onPress(ButtonWidget button) {
                MinecraftClient client = MinecraftClient.getInstance();
                client.setScreen(new SecretOptionsScreen(client.currentScreen));
            }
        }));
    }
}
