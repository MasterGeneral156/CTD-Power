package com.themastergeneral.ctdpower.proxy;

import com.themastergeneral.ctdpower.CTDPower;
import com.themastergeneral.ctdpower.blocks.ModBlocks;
import com.themastergeneral.ctdpower.handlers.GUIHandler;
import com.themastergeneral.ctdpower.te.machines.generator.CoalGenerator;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy 
{
	public void preInit(FMLPreInitializationEvent e) 
	{
		ModBlocks.initialize();
	}

	public void init(FMLInitializationEvent e) 
	{

	}
	public void registerTileEntities() 
	{
		GameRegistry.registerTileEntity(CoalGenerator.class, "coal_generator");
	}
	public void postInit(FMLPostInitializationEvent e) 
	{

	}
	public void registerGuiHandler() 
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(CTDPower.instance, new GUIHandler());
	}
}
