package com.themastergeneral.ctdpower;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.themastergeneral.ctdpower.proxy.CommonProxy;

@Mod(modid = CTDPower.MODID, name = CTDPower.MODNAME, version = CTDPower.VERSION, acceptedMinecraftVersions = CTDPower.acceptedMinecraftVersions, updateJSON = CTDPower.updateJSON)
public class CTDPower 
{
	public static final String MODID = "ctdpower";
    public static final String MODNAME = "CTD Power";
    public static final String VERSION = "1.0.0";
    public static final String acceptedMinecraftVersions = "1.10.2";
	public static final String updateJSON = "https://raw.githubusercontent.com/MasterGeneral156/Version/master/CTD-Power.json";
	
	@Instance
    public static CTDPower instance = new CTDPower();
	
	@SidedProxy(clientSide="com.themastergeneral.ctdpower.proxy.ClientProxy", serverSide="com.themastergeneral.ctdpower.proxy.ServerProxy")
    public static CommonProxy proxy;
	
	@EventHandler
    public void preInit(FMLPreInitializationEvent e) 
    {
    	proxy.preInit(e);
    }
    @EventHandler
    public void init(FMLInitializationEvent e) 
    {
    	proxy.init(e);
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) 
    {
    	proxy.postInit(e);
    }
}
