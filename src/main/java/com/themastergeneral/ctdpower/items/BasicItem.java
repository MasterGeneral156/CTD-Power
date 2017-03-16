package com.themastergeneral.ctdpower.items;

import com.themastergeneral.ctdpower.CTDPower;
import com.themastergeneral.ctdpower.client.render.item.ItemModelProvider;

import net.minecraft.item.Item;

public class BasicItem extends Item implements ItemModelProvider
{
	protected String name;
	public BasicItem(String name) 
	{
		this.name = name;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
	}
	public void registerItemModel(Item item) 
	{
		CTDPower.proxy.registerItemRenderer(this, 0, name);
	}
}
