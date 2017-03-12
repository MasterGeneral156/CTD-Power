package com.themastergeneral.ctdpower.blocks;

import com.themastergeneral.ctdpower.items.CableItemBlock;
import com.themastergeneral.ctdpower.te.cables.CableBase;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks 
{
	public static final Block cable1 = new CableBase("wire_1", 32D, 1);
	public static final Block cable2 = new CableBase("wire_2", 128D, 2);
	public static final Block cable3 = new CableBase("wire_3", 512D, 3);
	public static final BlockCoalGenerator coal_generator = new BlockCoalGenerator("coal_generator");
	public static void initialize()
	{
		GameRegistry.registerBlock(cable1, CableItemBlock.class, "wire_1");
		GameRegistry.registerBlock(cable2, CableItemBlock.class, "wire_2");
		GameRegistry.registerBlock(cable3, CableItemBlock.class, "wire_3");
		GameRegistry.registerBlock(coal_generator, "coal_generator");
	}
}
