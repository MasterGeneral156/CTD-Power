package com.themastergeneral.ctdpower.proxy;

import java.util.ArrayList;
import java.util.List;

import com.themastergeneral.ctdpower.blocks.ModBlocks;
import com.themastergeneral.ctdpower.client.render.tile.RenderCable;
import com.themastergeneral.ctdpower.te.cables.TileCable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
    @Override
    public void init(FMLInitializationEvent e) 
    {
        super.init(e);
        List<Item> items = new ArrayList<Item>();
        items.add(Item.getItemFromBlock(ModBlocks.cable1));
        items.add(Item.getItemFromBlock(ModBlocks.cable2));
        items.add(Item.getItemFromBlock(ModBlocks.cable3));
        for(int i = 0; i < items.size(); ++i)
		{
			Item item = items.get(i);
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getUnlocalizedName().substring(5), "inventory"));
		}
        ClientRegistry.bindTileEntitySpecialRenderer(TileCable.class, new RenderCable());
    }
    public void registerItemRenderer(Item item, int meta, String id)
    {
    	 ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation("CTDPower:" + id, "inventory"));
    }
}