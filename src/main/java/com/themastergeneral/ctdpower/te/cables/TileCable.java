package com.themastergeneral.ctdpower.te.cables;

import net.minecraft.nbt.NBTTagCompound;

import com.themastergeneral.ctdpower.CTDPower;

public class TileCable extends TileCableAbstract
{
	public double capacityAdded;
	public int tier;
	
	public TileCable(double cap, int tier) 
	{ 
		this.tier = tier; 
		capacityAdded = cap; 
		System.out.println(CTDPower.MODID.toLowerCase() + ":blocks/wire_" + tier);
	}
	
	@Override
	public double getCapacityAddedToNet()
	{
		return capacityAdded;
	}
	
	@Override
	public void writeCustomNBT(NBTTagCompound nbt)
	{
		super.writeCustomNBT(nbt);
		nbt.setDouble("CapacityAdded", capacityAdded);
		nbt.setInteger("CapTier", tier);
	}
	
	@Override
	public void readCustomNBT(NBTTagCompound nbt)
	{
		super.readCustomNBT(nbt);
		capacityAdded = nbt.getDouble("CapacityAdded");
		tier = nbt.getInteger("CapTier");
	}
	
	@Override
	public String getResourceConnection()
	{
		return CTDPower.MODID.toLowerCase() + ":blocks/wire_" + tier;
	}
}
