package hibi.blahaj;

import net.minecraft.core.component.*;
import net.minecraft.core.registries.*;
import net.minecraft.network.chat.*;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.registries.*;

@SuppressWarnings("null")
public class BlahajDataComponentTypes {

	public static final DeferredRegister.DataComponents DATA_COMPONENT_TYPES = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Blahaj.MOD_ID);

	public static DataComponentType<Component> OWNER;

	static {
		DATA_COMPONENT_TYPES.register("owner", () -> OWNER = DataComponentType.<Component>builder().persistent(ComponentSerialization.FLAT_CODEC).networkSynchronized(ComponentSerialization.STREAM_CODEC).cacheEncoding().build());
	}

	public static void register(IEventBus modEventBus) {
		DATA_COMPONENT_TYPES.register(modEventBus);
	}

}
