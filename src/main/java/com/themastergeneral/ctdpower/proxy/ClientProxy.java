package com.themastergeneral.ctdpower.proxy;

import com.themastergeneral.ctdpower.client.render.tile.RenderCable;
import com.themastergeneral.ctdpower.te.cables.TileCable;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
    public void preInit(FMLPreInitializationEvent e) 
    {
        super.preInit(e);
    }
    public void init(FMLInitializationEvent e) 
    {
        super.init(e);
        ClientRegistry.bindTileEntitySpecialRenderer(TileCable.class, new RenderCable());
    }
    public void postInit(FMLPostInitializationEvent e) 
    {
        super.postInit(e);
    }
}