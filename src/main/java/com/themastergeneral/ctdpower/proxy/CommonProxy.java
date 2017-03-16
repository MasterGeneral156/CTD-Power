package com.themastergeneral.ctdpower.proxy;

import java.io.File;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.themastergeneral.ctdpower.CTDPower;
import com.themastergeneral.ctdpower.blocks.ModBlocks;
import com.themastergeneral.ctdpower.handlers.CraftingHandler;
import com.themastergeneral.ctdpower.handlers.GUIHandler;
import com.themastergeneral.ctdpower.items.ModItems;
import com.themastergeneral.ctdpower.te.machines.generator.CoalGenerator;
import com.themastergeneral.ctdpower.config.Config;

public class CommonProxy 
{
	// Config instance
    public static Configuration config;
	public void preInit(FMLPreInitializationEvent e) 
	{
	    File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "CTD/CTDPower.cfg"));
        Config.readConfig();
		ModBlocks.initialize();
		ModItems.init();
		CraftingHandler.addRecipes();
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
		if (config.hasChanged()) 
		{
            config.save();
        }
	}
	public void registerGuiHandler() 
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(CTDPower.instance, new GUIHandler());
	}
	public void registerItemRenderer(Item item, int meta, String id)
    {
    	 
    }
}
