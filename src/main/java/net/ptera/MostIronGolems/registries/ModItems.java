package net.ptera.MostIronGolems.registries;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.ptera.MostIronGolems.items.IronWand;

import static net.ptera.RecordBreaker.MOD_ID;

public class ModItems {
    public static Item register(Identifier id,Item item) {
        return Registry.register(Registries.ITEM,id,item);
    }
    public static Item IRON_WAND = register(Identifier.of(MOD_ID,"iron_wand"),new IronWand(new Item.Settings()));
    public static void init() {}
}
