package hibi.blahaj;

import hibi.blahaj.block.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.npc.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.trading.*;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.entries.*;
import net.neoforged.bus.api.*;
import net.neoforged.fml.common.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.event.*;
import net.neoforged.neoforge.event.village.*;

@SuppressWarnings("null")
@Mod(Blahaj.MOD_ID)
public class Blahaj {

	public static final String MOD_ID = "blahaj";

	public Blahaj(IEventBus modEventBus) {
		BlahajDataComponentTypes.register(modEventBus);
		BlahajBlocks.register(modEventBus);
		registerLootTables();
		registerTrades();
	}

	private static void registerLootTables() {
		NeoForge.EVENT_BUS.addListener((LootTableLoadEvent event) -> {
			ResourceKey<LootTable> key = event.getKey();
			if (key.equals(BuiltInLootTables.STRONGHOLD_CROSSING) || key.equals(BuiltInLootTables.STRONGHOLD_CORRIDOR)) {
				LootPool.Builder pb = LootPool.lootPool()
					.add(LootItem.lootTableItem(BlahajBlocks.GRAY_SHARK_ITEM).setWeight(5))
					.add(LootItem.lootTableItem(Items.AIR).setWeight(100));
				event.getTable().addPool(pb.build());
			} else if (key.equals(BuiltInLootTables.VILLAGE_PLAINS_HOUSE)) {
				LootPool.Builder pb = LootPool.lootPool()
					.add(LootItem.lootTableItem(BlahajBlocks.GRAY_SHARK_ITEM))
					.add(LootItem.lootTableItem(Items.AIR).setWeight(43));
				event.getTable().addPool(pb.build());
			} else if (key.equals(BuiltInLootTables.VILLAGE_TAIGA_HOUSE) || key.equals(BuiltInLootTables.VILLAGE_SNOWY_HOUSE)) {
				LootPool.Builder pb = LootPool.lootPool()
					.add(LootItem.lootTableItem(BlahajBlocks.GRAY_SHARK_ITEM).setWeight(5))
					.add(LootItem.lootTableItem(Items.AIR).setWeight(54));
				event.getTable().addPool(pb.build());
			} else if (key.equals(BuiltInLootTables.FLETCHER_GIFT)
				|| key.equals(BuiltInLootTables.BUTCHER_GIFT)
				|| key.equals(BuiltInLootTables.LEATHERWORKER_GIFT)) {

				LootPool.Builder pb = LootPool.lootPool()
					.add(LootItem.lootTableItem(BlahajBlocks.BROWN_BEAR_ITEM).setWeight(5))
					.add(LootItem.lootTableItem(Items.AIR).setWeight(25));
				event.getTable().addPool(pb.build());
			}
		});
	}

	private static void registerTrades() {
		NeoForge.EVENT_BUS.addListener((VillagerTradesEvent event) -> {
			if (event.getType() == VillagerProfession.SHEPHERD) {
				event.getTrades().get(5).add((entity, random) -> new MerchantOffer(new ItemCost(Items.EMERALD, 15), new ItemStack(BlahajBlocks.GRAY_SHARK_ITEM), 2, 30, 0.1f));
			}
		});
	}

}
