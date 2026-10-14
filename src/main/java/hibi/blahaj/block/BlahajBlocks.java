package hibi.blahaj.block;

import net.minecraft.client.renderer.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.event.*;
import net.neoforged.neoforge.registries.*;

import java.util.*;

import static hibi.blahaj.Blahaj.*;

public class BlahajBlocks {

	public static final ResourceLocation GRAY_SHARK_ID = ResourceLocation.fromNamespaceAndPath(MOD_ID, "gray_shark");
	public static final ResourceLocation BLAHAJ_ID = ResourceLocation.fromNamespaceAndPath(MOD_ID, "blue_shark");
	public static final ResourceLocation BLAVINGAD_ID = ResourceLocation.fromNamespaceAndPath(MOD_ID, "blue_whale");
	public static final ResourceLocation BREAD_ID = ResourceLocation.fromNamespaceAndPath(MOD_ID, "bread");
	public static final ResourceLocation BROWN_BEAR_ID = ResourceLocation.fromNamespaceAndPath(MOD_ID, "brown_bear");

	/** NeoForge freezes the built-in registries before mod constructors run, so all entries are registered through DeferredRegisters. */
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MOD_ID);
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MOD_ID);

	public static Block GRAY_SHARK_BLOCK;
	public static Item GRAY_SHARK_ITEM;
	public static Block BLAHAJ_BLOCK;
	public static Item BLAHAJ_ITEM;
	public static Block BLAVINGAD_BLOCK;
	public static Item BLAVINGAD_ITEM;
	public static Block BREAD_BLOCK;
	public static Item BREAD_ITEM;
	public static Block BROWN_BEAR_BLOCK;
	public static Item BROWN_BEAR_ITEM;

	public static final List<String> PRIDE_NAMES = List.of(
		"ace", "agender", "aro", "aroace", "bi", "demiboy", "demigirl",
		"demi_r", "demi_s", "enby", "gay", "genderfluid", "genderqueer", "greyrose",
		"grey_r", "grey_s", "intersex", "lesbian", "pan", "poly", "pride", "trans");

	public static List<Block> PRIDE_BLOCKS = new ArrayList<>();
	public static List<Item> PRIDE_ITEMS = new ArrayList<>();

	public static void register(IEventBus modEventBus) {
		// The values are only created (and the fields filled in) once NeoForge fires its RegisterEvent.
		// NeoForge registers data component types, then blocks, then items (see GameData#getRegistrationOrder),
		// so the block fields are guaranteed to be set by the time the item suppliers run.
		BLOCKS.register(GRAY_SHARK_ID.getPath(), () -> GRAY_SHARK_BLOCK = new CuddlyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_WOOL)));
		BLOCKS.register(BLAHAJ_ID.getPath(), () -> BLAHAJ_BLOCK = new CuddlyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CYAN_WOOL)));
		BLOCKS.register(BLAVINGAD_ID.getPath(), () -> BLAVINGAD_BLOCK = new CuddlyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_WOOL)));
		BLOCKS.register(BREAD_ID.getPath(), () -> BREAD_BLOCK = new CuddlyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_WOOL)));
		BLOCKS.register(BROWN_BEAR_ID.getPath(), () -> BROWN_BEAR_BLOCK = new CuddlyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_WOOL)));

		ITEMS.register(GRAY_SHARK_ID.getPath(), () -> GRAY_SHARK_ITEM = new CuddlyItem(GRAY_SHARK_BLOCK, new Item.Properties().stacksTo(1).attributes(CuddlyItem.createAttributeModifiers()), "block.blahaj.gray_shark.tooltip"));
		ITEMS.register(BLAHAJ_ID.getPath(), () -> BLAHAJ_ITEM = new CuddlyItem(BLAHAJ_BLOCK, new Item.Properties().stacksTo(1).attributes(CuddlyItem.createAttributeModifiers()), "block.blahaj.blue_shark.tooltip"));
		ITEMS.register(BLAVINGAD_ID.getPath(), () -> BLAVINGAD_ITEM = new CuddlyItem(BLAVINGAD_BLOCK, new Item.Properties().stacksTo(1).attributes(CuddlyItem.createAttributeModifiers()), "block.blahaj.blue_whale.tooltip"));
		ITEMS.register(BREAD_ID.getPath(), () -> BREAD_ITEM = new CuddlyItem(BREAD_BLOCK, new Item.Properties().stacksTo(1).attributes(CuddlyItem.createAttributeModifiers()), null));
		ITEMS.register(BROWN_BEAR_ID.getPath(), () -> BROWN_BEAR_ITEM = new CuddlyItem(BROWN_BEAR_BLOCK, new Item.Properties().stacksTo(1).attributes(CuddlyItem.createAttributeModifiers()), "block.blahaj.brown_bear.tooltip"));

		for (String name : PRIDE_NAMES) {
			ResourceLocation id = ResourceLocation.fromNamespaceAndPath(MOD_ID, name + "_shark");
			DeferredBlock<Block> block = BLOCKS.register(id.getPath(), () -> {
				Block prideBlock = new CuddlyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL));
				PRIDE_BLOCKS.add(prideBlock);
				return prideBlock;
			});
			ITEMS.register(id.getPath(), () -> {
				Item prideItem = new CuddlyItem(block.get(), new Item.Properties().stacksTo(1).attributes(CuddlyItem.createAttributeModifiers()), "block.blahaj.blue_shark.tooltip");
				PRIDE_ITEMS.add(prideItem);
				return prideItem;
			});
		}

		BLOCKS.register(modEventBus);
		ITEMS.register(modEventBus);

		modEventBus.addListener((BuildCreativeModeTabContentsEvent event) -> {
			if (event.getTabKey() != CreativeModeTabs.TOOLS_AND_UTILITIES) return;

			event.accept(new ItemStack(GRAY_SHARK_ITEM));
			event.accept(new ItemStack(BLAHAJ_ITEM));
			event.accept(new ItemStack(BLAVINGAD_ITEM));
			event.accept(new ItemStack(BREAD_ITEM));
			event.accept(new ItemStack(BROWN_BEAR_ITEM));

			for (Item item : PRIDE_ITEMS) {
				event.accept(new ItemStack(item));
			}
		});
	}

	public static void registerClient() {
		ItemBlockRenderTypes.setRenderLayer(GRAY_SHARK_BLOCK, RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BLAHAJ_BLOCK, RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BLAVINGAD_BLOCK, RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BREAD_BLOCK, RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BROWN_BEAR_BLOCK, RenderType.cutout());
		for (Block block : PRIDE_BLOCKS) {
			ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutout());
		}
	}

}
