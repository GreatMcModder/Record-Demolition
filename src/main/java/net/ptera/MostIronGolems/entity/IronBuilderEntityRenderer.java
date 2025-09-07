package net.ptera.MostIronGolems.entity;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.ptera.MostIronGolems.registries.ModModelLayers;

import static net.ptera.RecordBreaker.MOD_ID;

public class IronBuilderEntityRenderer extends MobEntityRenderer<IronBuilderEntity,IronBuilderModel> {
    public IronBuilderEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new IronBuilderModel(context.getPart(ModModelLayers.IRON_BUILDER)),0.5F);
    }

    @Override
    public Identifier getTexture(IronBuilderEntity entity) {
        return Identifier.of(MOD_ID, "textures/entity/iron_builder.png");
    }
}
