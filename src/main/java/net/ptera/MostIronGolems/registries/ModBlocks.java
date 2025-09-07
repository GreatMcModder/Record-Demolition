package net.ptera.MostIronGolems.registries;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.ptera.RecordBreaker.MOD_ID;

public class ModBlocks {
    public static Block register(String id, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(MOD_ID,id),block);
    }
    public static Block IRON_GOLEM_MARKER = register("iron_golem_marker",new Block(AbstractBlock.Settings.create().hardness(0.7F)));
    public static void init() {}
}
