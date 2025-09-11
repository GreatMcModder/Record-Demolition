package net.ptera;

import net.fabricmc.api.ModInitializer;

import net.ptera.MostIronGolems.registries.ModBlocks;
import net.ptera.MostIronGolems.registries.ModEntities;
import net.ptera.MostIronGolems.registries.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecordBreaker implements ModInitializer {

	public static final String MOD_ID = "record-breaker";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModEntities.init();
		ModBlocks.init();
		ModItems.init();
		LOGGER.info("Breaking the 3 Biggest World Records");
	}
}