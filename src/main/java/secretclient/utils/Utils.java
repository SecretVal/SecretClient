package secretclient.utils;

import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ButtonWidget.PressAction;
import net.minecraft.text.Text;

public class Utils {
    public static ButtonWidget createButton(int x, int y, int width, int height, Text text, PressAction pressAction) {
        return ButtonWidget.builder(text, pressAction).size(width, height).position(x, y).build();
    }
}
