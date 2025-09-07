package net.ptera.MostIronGolems.registries;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.ptera.RecordBreaker.MOD_ID;

public class ModBlocks {
    public static Block register(String id, Block block,boolean shouldRegisterItem) {
        if (shouldRegisterItem) {
            Registry.register(Registries.ITEM,Identifier.of(MOD_ID,id),new BlockItem(block, new Item.Settings()));
        }
        return Registry.register(Registries.BLOCK, Identifier.of(MOD_ID,id),block);
    }
    public static Block IRON_GOLEM_MARKER = register("iron_golem_marker",new Block(AbstractBlock.Settings.create().hardness(0.7F)),true);
    public static void init() {}
}
