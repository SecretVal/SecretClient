package secretclient.modules;

import net.minecraft.client.MinecraftClient;

public abstract class Module {
    public Boolean enabled = false;

    public abstract String getName();

    public abstract void onEnable(MinecraftClient client);

    public abstract void onDisable(MinecraftClient client);

    public abstract void onTick(MinecraftClient client);
}
