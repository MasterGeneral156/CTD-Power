package com.themastergeneral.ctdpower.items;

import com.themastergeneral.ctdpower.client.render.item.ItemModelProvider;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems 
{
	public static ItemWrench ctd_wrench;
	public static void init()
	{
		ctd_wrench = register(new ItemWrench("ctd_wrench"));
	}
	public static <T extends Item> T register(T item) 
	{
		GameRegistry.register(item);
		if(item instanceof ItemModelProvider) 
		{
			((ItemModelProvider)item).registerItemModel(item);
		}
		return item;
	}
}
