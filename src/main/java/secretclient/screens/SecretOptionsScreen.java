package secretclient.screens;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ButtonWidget.PressAction;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import secretclient.SecretClient;
import secretclient.modules.Module;
import secretclient.modules.ModuleManager;
import secretclient.utils.Utils;

public class SecretOptionsScreen extends Screen {
    private final Screen parent;
    private int tpDist = 5;
    private ButtonWidget TeleportButton;

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
        TeleportButton = Utils.createButton(0, 25, 40, 20, Text.of("TP " + tpDist), new PressAction() {
            @Override
            public void onPress(ButtonWidget button) {
                Direction dir = client.player.getFacing();
                Vec3d cPos = client.player.getPos();
                Vec3d newPos = cPos.add(new Vec3d(tpDist, tpDist, tpDist)
                        .multiply(new Vec3d(dir.getVector().getX(), dir.getVector().getY(), dir.getVector().getZ())));
                for (int i = 0; i < 8; i++) {
                    client.player.networkHandler
                            .sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(client.player.getX(),
                                    client.player.getY(), client.player.getZ(), client.player.isOnGround()));
                }
                client.player.setPosition(newPos);
            }

        });
        this.addDrawableChild(TeleportButton);
        this.addDrawableChild(Utils.createButton(45, 25, 20, 20, Text.of("+"), new PressAction() {
            @Override
            public void onPress(ButtonWidget button) {
                tpDist++;
                TeleportButton.setMessage(Text.of("TP " + tpDist));
            }
        }));
        this.addDrawableChild(Utils.createButton(70, 25, 20, 20, Text.of("-"), new PressAction() {
            @Override
            public void onPress(ButtonWidget button) {
                tpDist -= tpDist > 0 ? 1 : 0;
                TeleportButton.setMessage(Text.of("TP " + tpDist));
            }
        }));

        this.addDrawableChild(Utils.createButton(0, 50, 80, 20, Text.of(getXrayString()), new PressAction() {
            @Override
            public void onPress(ButtonWidget button) {
                SecretClient.XrayEnabled = !SecretClient.XrayEnabled;
                String str = getXrayString();
                client.player.sendMessage(Text.of("[SecretClient]: " + str));
                button.setMessage(Text.of(str));
            }
        }));

        int basicWidth = 80;
        int basicHeight = 20;
        int basicY = 75;
        int basicX = 0;
        for (int i = 0; i < SecretClient.moduleManager.modules.size(); i++) {
            Module m = SecretClient.moduleManager.modules.get(i);
            String s = m.getName() + " " + (m.enabled ? "[ON]" : "[OFF]");
            this.addDrawableChild(
                    Utils.createButton(basicX, basicY + i * 25, basicWidth, basicHeight,
                            Text.of(s), new PressAction() {
                                @Override
                                public void onPress(ButtonWidget button) {
                                    SecretClient.moduleManager.toggleModule(m);
                                    String s = m.getName() + " " + (m.enabled ? "[ON]" : "[OFF]");
                                    client.player.sendMessage(Text.of(s));
                                    button.setMessage(Text.of(s));
                                }
                            }));
        }
        // this.addDrawableChild(Utils.createButton(0, 25, 80, 20,
        // Text.of(getFlyingString()), new PressAction() {
        //
        // @Override
        // public void onPress(ButtonWidget button) {
        // SecretClient.moduleManager.toggleModule(SecretClient.flyModule);
        // String str = getFlyingString();
        // client.player.sendMessage(Text.of("[SecretClient]: " + str));
        // button.setMessage(Text.of(getFlyingString()));
        // }
        //
        // }));
        // this.addDrawableChild(Utils.createButton(0, 50, 80, 20,
        // Text.of(getNightVisionText()), new PressAction() {
        //
        // @Override
        // public void onPress(ButtonWidget button) {
        // SecretClient.NightVisionEnabled = !SecretClient.NightVisionEnabled;
        // if (SecretClient.NightVisionEnabled) {
        // client.player.sendMessage(Text.of("[SecretClient]: Night Vision Enabled"));
        // client.player.addStatusEffect(new
        // StatusEffectInstance(StatusEffects.NIGHT_VISION, -1, 255));
        // } else {
        // client.player.sendMessage(Text.of("[SecretClient]: Night Vision Disabled"));
        // client.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
        // }
        // button.setMessage(Text.of(getNightVisionText()));
        // }
        //
        // }));

    }

    String getXrayString() {
        if (SecretClient.XrayEnabled) {
            return "Xray [ON]";
        } else {
            return "Xray [OFF]";
        }
    }
}
