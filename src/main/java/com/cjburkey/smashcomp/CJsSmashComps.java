package com.cjburkey.smashcomp;

import com.cjburkey.smashcomp.block.BlockSmashingComputer;
import com.cjburkey.smashcomp.block.BlockSmashingComputerEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CJsSmashComps implements ModInitializer {

	// Blocks
	public static final Identifier SMASHING_COMP_IDENT = new Identifier("cjs-smash-comp", "smashing_comp");
	public static final Identifier SMASHING_COMP_ENT_IDENT = new Identifier("cjs-smash-comp", "smashing_comp_ent");
	public static final Block SMASHING_COMP = new BlockSmashingComputer();
	public static BlockEntityType<BlockSmashingComputerEntity> SMASHING_COMP_ENT;

	// Get the logger for this mod
	public static final Logger LOGGER = LogManager.getLogger("cjs-smash-comp");

	// On plugin initialize (pre-resource init)
	@Override
	public void onInitialize() {
		// Initialize blocks
		LOGGER.info("Initializing blocks");
		Registry.register(Registry.BLOCK, SMASHING_COMP_IDENT, SMASHING_COMP);
		Registry.register(Registry.ITEM, SMASHING_COMP_IDENT, new BlockItem(SMASHING_COMP, new FabricItemSettings().group(ItemGroup.MISC)));
		SMASHING_COMP_ENT = Registry.register(Registry.BLOCK_ENTITY_TYPE, SMASHING_COMP_ENT_IDENT, FabricBlockEntityTypeBuilder.create(BlockSmashingComputerEntity::new, SMASHING_COMP).build(null));
	}
}
