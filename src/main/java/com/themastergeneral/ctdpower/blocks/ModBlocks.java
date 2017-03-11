package com.themastergeneral.ctdpower.blocks;

import com.themastergeneral.ctdpower.items.CableItemBlock;
import com.themastergeneral.ctdpower.te.cables.CableBase;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks 
{
	public static final Block cable1 = new CableBase("wire_1", 20D, 1);
	public static void initialize()
	{
		GameRegistry.registerBlock(cable1, CableItemBlock.class, "wire_1");
	}
}
