package com.themastergeneral.ctdpower.te.cables;

import java.util.HashSet;
import java.util.Set;

import com.themastergeneral.ctdpower.CTDPower;
import com.themastergeneral.ctdpower.network.energy.cable.CableNetwork;
import com.themastergeneral.ctdpower.te.CommonTileEntity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import cofh.api.energy.IEnergyReceiver;

public class TileCableAbstract extends CommonTileEntity implements IEnergyReceiver
{
	public CableNetwork network;
	public double internal;
	
	@Override
	public void update()
	{
		if(network == null)
		{
			Set<CableNetwork> nets = new HashSet<CableNetwork>();
			for(EnumFacing f : EnumFacing.VALUES)
			{
				TileEntity t = worldObj.getTileEntity(pos.offset(f));
				if(t instanceof TileCableAbstract)
				{
					TileCableAbstract w = (TileCableAbstract) t;
					if(w.network != null)
						nets.add(w.network);
				}
			}
			
			if(nets.size() > 0)
			{
				CableNetwork netC = null;
				int size = 0;
				
				for(CableNetwork n : nets.toArray(new CableNetwork[0]))
				{
					if(n.wires.size() > size)
					{
						size = n.wires.size();
						netC = n;
					}else if(n.wires.size() == size) netC = n;
				}
				
				network = netC;
			}
			
			if(network == null) network = new CableNetwork();
			
			network.connect(this);
		}else
		{
			network.connect(this);
			network.markTicked(this);
			
			for(EnumFacing f : EnumFacing.VALUES)
			{
				TileEntity t = worldObj.getTileEntity(pos.offset(f));
				if(t instanceof TileCableAbstract)
				{
					TileCableAbstract w = (TileCableAbstract) t;
					if(w.network != null && w.network != network)
					{
						if(w.network.wires.size() > network.wires.size())
							w.network.merge(network);
						else network.merge(w.network);
					}
				}
			}
		}
		
		BlockPos p;
		if(network != null && network.energy > 0) for(EnumFacing f : EnumFacing.VALUES)
		{
			p = getPos().offset(f);
			TileEntity t = worldObj.getTileEntity(p);
			if(t instanceof TileCableAbstract) continue;
			
			if(t instanceof IEnergyReceiver)
			{
				IEnergyReceiver r = (IEnergyReceiver) t;
				
				if(r.canConnectEnergy(f.getOpposite()))
				{
					int sent = r.receiveEnergy(f.getOpposite(), Math.min((int) getCapacityAddedToNet(), (int) network.energy), false);
					network.energy -= sent;
				}
			}
		}
	}
	
	public double getCapacityAddedToNet()
	{
		return 80D;
	}
	
	@Override
	public boolean canConnectEnergy(EnumFacing facing)
	{
		return true;
	}

	@Override
	public int getEnergyStored(EnumFacing facing)
	{
		if(network == null) return 0;
		return (int) network.energy;
	}

	@Override
	public int getMaxEnergyStored(EnumFacing facing)
	{
		if(network == null) return 0;
		return (int) network.capacity;
	}

	@Override
	public int receiveEnergy(EnumFacing facing, int maxReceive, boolean simulate)
	{
		if(network == null) return 0;
		int energyReceived = (int) Math.min(network.capacity - network.energy, maxReceive);
        if(!simulate)
        	network.energy += energyReceived;
        return energyReceived;
	}

	@Override
	public void readCustomNBT(NBTTagCompound nbt)
	{
		internal = nbt.getDouble("EnergyStored");
	}

	@Override
	public void writeCustomNBT(NBTTagCompound nbt)
	{
		nbt.setDouble("EnergyStored", internal);
	}
	
	boolean wasUnloaded = false;
	
	@Override
	public void onChunkUnload()
	{
		super.onChunkUnload();
		wasUnloaded = true;
		
		if(network != null)
		{
			network.splitEnergyTo1Wire(this);
			network.disconnect(this);
		}
	}
	
	@Override
	public void invalidate()
	{
		super.invalidate();
		
		if(!wasUnloaded && network != null)
		{
			CableNetwork network = this.network;
			network.disconnect(this);
			network.energy += internal;
		}
	}
	
	public String getResourceConnection()
	{
		return CTDPower.MODID + ":blocks/wire_1";
	}
}
