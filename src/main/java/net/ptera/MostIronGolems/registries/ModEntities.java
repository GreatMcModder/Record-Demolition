package net.ptera.MostIronGolems.registries;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.ptera.MostIronGolems.entity.IronBuilderEntity;

import static net.ptera.RecordBreaker.MOD_ID;

public class ModEntities {
    public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder) {
        return Registry.register(Registries.ENTITY_TYPE, Identifier.of(MOD_ID,id),builder.build(id));
    }
    public static final EntityType<IronBuilderEntity> IRON_BUILDER = register("iron_builder",EntityType.Builder.create(IronBuilderEntity::new,SpawnGroup.MISC));
    public static void init() {
        FabricDefaultAttributeRegistry.register(IRON_BUILDER,IronBuilderEntity.registerAttributes());
    }
}
