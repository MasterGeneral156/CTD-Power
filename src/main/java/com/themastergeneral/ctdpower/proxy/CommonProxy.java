package com.themastergeneral.ctdpower.proxy;

import com.themastergeneral.ctdpower.blocks.ModBlocks;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy 
{
	public void preInit(FMLPreInitializationEvent e) 
	{
		ModBlocks.initialize();
	}

	public void init(FMLInitializationEvent e) 
	{

	}
	public void postInit(FMLPostInitializationEvent e) 
	{

	}
}
