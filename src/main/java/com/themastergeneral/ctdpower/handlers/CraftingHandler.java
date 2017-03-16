package com.themastergeneral.ctdpower.handlers;

import com.themastergeneral.ctdpower.blocks.ModBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CraftingHandler 
{
	public static void addRecipes() 
	{
		//Cables
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.cable1, 8), "GGG", "RIR", "GGG", 'R', Items.REDSTONE, 'I', "ingotGold", 'G', "blockGlass"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.cable2, 8), "GGG", "RIR", "GGG", 'R', Items.REDSTONE, 'I', "gemDiamond", 'G', "glowstone"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.cable3, 8), "GGG", "RIR", "GGG", 'R', Items.REDSTONE, 'I', "gemEmerald", 'G', Blocks.SEA_LANTERN));
		
		//Generators
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.coal_generator, 1), "CRC", "RFR", "CRC", 'R', Items.REDSTONE, 'F', Blocks.FURNACE, 'C', Items.COAL));
	}
}
