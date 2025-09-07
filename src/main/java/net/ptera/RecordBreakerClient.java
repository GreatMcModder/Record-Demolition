package net.ptera;

import net.fabricmc.api.ClientModInitializer;
import net.ptera.MostIronGolems.registries.ModModelLayers;

public class RecordBreakerClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModModelLayers.init();
    }
}
