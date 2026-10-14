package hibi.blahaj.block;

import hibi.blahaj.*;
import net.minecraft.*;
import net.minecraft.core.component.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class CuddlyItem extends BlockItem {

	private final Component subtitle;

	public CuddlyItem(Block block, Item.Properties settings, String subtitle) {
		super(block, settings);
		this.subtitle = subtitle == null ? null : Component.translatable(subtitle).withStyle(ChatFormatting.GRAY);
	}

	@Override
	public void onCraftedBy(ItemStack stack, Level world, Player player) {
		super.onCraftedBy(stack, world, player);

		if (player != null) { // compensate for auto-crafter mods
			stack.set(BlahajDataComponentTypes.OWNER, player.getName());
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag type) {
		super.appendHoverText(stack, context, tooltip, type);

		if (this.subtitle != null) {
			tooltip.add(this.subtitle);
		}

		@Nullable Component ownerName = stack.get(BlahajDataComponentTypes.OWNER);
		if (ownerName != null) {
			@Nullable Component customName = stack.get(DataComponents.CUSTOM_NAME);
			if (customName == null) {
				tooltip.add(Component.translatable("tooltip.blahaj.owner.craft", ownerName).withStyle(ChatFormatting.GRAY));
			} else {
				tooltip.add(Component.translatable("tooltip.blahaj.owner.rename", customName, ownerName).withStyle(ChatFormatting.GRAY));
			}
		}
	}

	public static final ResourceLocation MINING_SPEED_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(Blahaj.MOD_ID, "base_attack_damage");

	public static ItemAttributeModifiers createAttributeModifiers() {
		return ItemAttributeModifiers.builder()
			.add(Attributes.BLOCK_BREAK_SPEED, new AttributeModifier(MINING_SPEED_MODIFIER_ID, -3.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.MAINHAND)
			.add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, -2.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.MAINHAND)
			.build();
	}


}
