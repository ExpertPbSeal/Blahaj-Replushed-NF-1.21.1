package hibi.blahaj;

import hibi.blahaj.block.*;
import net.neoforged.api.distmarker.*;
import net.neoforged.bus.api.*;
import net.neoforged.fml.common.*;
import net.neoforged.fml.event.lifecycle.*;

@EventBusSubscriber(modid = Blahaj.MOD_ID, value = Dist.CLIENT)
public class BlahajClient {

	@SubscribeEvent
	public static void onInitializeClient(FMLClientSetupEvent event) {
		event.enqueueWork(BlahajBlocks::registerClient);
	}

}
