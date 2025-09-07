package net.ptera.MostIronGolems.registries;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.ptera.MostIronGolems.entity.IronBuilderEntityRenderer;
import net.ptera.MostIronGolems.entity.IronBuilderModel;

import static net.ptera.RecordBreaker.MOD_ID;

public class ModModelLayers {
    public static EntityModelLayer IRON_BUILDER = new EntityModelLayer(Identifier.of(MOD_ID,"iron_giant"),"iron_man");
    public static void init() {
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.IRON_BUILDER, IronBuilderModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.IRON_BUILDER, IronBuilderEntityRenderer::new);
    }
}
