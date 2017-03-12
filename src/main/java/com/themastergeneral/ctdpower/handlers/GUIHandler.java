package com.themastergeneral.ctdpower.handlers;

import com.themastergeneral.ctdpower.client.gui.GUICoalGenerator;
import com.themastergeneral.ctdpower.container.ContainerCoalGenerator;
import com.themastergeneral.ctdpower.te.machines.generator.CoalGenerator;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GUIHandler implements IGuiHandler {
	
	public static final int COAL_GENERATOR = 0;

	@Override
	public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == COAL_GENERATOR) {
			return new ContainerCoalGenerator(player.inventory, (CoalGenerator) world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}

	@Override
	public GuiScreen getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == COAL_GENERATOR) {
			return new GUICoalGenerator(player.inventory, (CoalGenerator) world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}

}
