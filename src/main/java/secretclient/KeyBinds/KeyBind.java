package secretclient.KeyBinds;

import net.minecraft.client.MinecraftClient;

public class KeyBind {
    int Key;
    OnPress onPress;
    String Name;
    String Category;

    public KeyBind(String Name, int Key, OnPress onPress, String Category) {
        this.Name = Name;
        this.Key = Key;
        this.onPress = onPress;
        this.Category = Category;
    }

    public interface OnPress {
        void onPress(MinecraftClient client);
    }
}
