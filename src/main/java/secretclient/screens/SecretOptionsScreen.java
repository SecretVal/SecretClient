package secretclient.screens;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ButtonWidget.PressAction;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potions;
import net.minecraft.text.Text;
import secretclient.SecretClient;
import secretclient.utils.Utils;

public class SecretOptionsScreen extends Screen {
    private final Screen parent;

    public SecretOptionsScreen(Screen parent) {
        super(Text.of("SecretClient Options"));
        this.parent = parent;
    }

    protected void init() {
        this.addDrawableChild(Utils.createButton(0, 0, 80, 20, Text.of("Back"), new PressAction() {

            @Override
            public void onPress(ButtonWidget button) {
                MinecraftClient.getInstance().setScreen(parent);
            }

        }));
        this.addDrawableChild(Utils.createButton(0, 25, 80, 20, Text.of(getFlyingString()), new PressAction() {

            @Override
            public void onPress(ButtonWidget button) {
                SecretClient.FlyingEnabled = !SecretClient.FlyingEnabled;
                if (SecretClient.FlyingEnabled) {
                    client.player.sendMessage(Text.of("[SecretClient]: Flying Enabled"));
                } else {
                    client.player.sendMessage(Text.of("[SecretClient]: Flying Disabled"));
                }
                button.setMessage(Text.of(getFlyingString()));
            }

        }));
        this.addDrawableChild(Utils.createButton(0, 50, 80, 20, Text.of(getNightVisionText()), new PressAction() {

            @Override
            public void onPress(ButtonWidget button) {
                SecretClient.NightVisionEnabled = !SecretClient.NightVisionEnabled;
                if (SecretClient.NightVisionEnabled) {
                    client.player.sendMessage(Text.of("[SecretClient]: Night Vision Enabled"));
                    client.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, -1, 255));
                } else {
                    client.player.sendMessage(Text.of("[SecretClient]: Night Vision Disabled"));
                    client.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
                }
                button.setMessage(Text.of(getNightVisionText()));
            }

        }));
    }

    String getFlyingString() {
        if (SecretClient.FlyingEnabled) {
            return "Flying [ON]";
        } else {
            return "Flying [OFF]";
        }
    }

    String getNightVisionText() {
        if (SecretClient.NightVisionEnabled) {
            return "NightVision [ON]";
        } else {
            return "NightVision [OFF]";
        }
    }
}
