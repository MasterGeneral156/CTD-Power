package com.themastergeneral.ctdpower.network.energy.cable;

import java.util.HashSet;
import java.util.Set;

import com.themastergeneral.ctdpower.te.cables.TileCable;
import com.themastergeneral.ctdpower.te.cables.TileCableAbstract;

public class CableNetwork
{
	public final Set<TileCableAbstract> wires = new HashSet<TileCableAbstract>();
	public final Set<TileCableAbstract> wiresTicked = new HashSet<TileCableAbstract>();
	
	public double energy, capacity;
	public int updateRate = 0;
	
	public void setNet(CableNetwork net)
	{
		if(net != null)
		{
			net.capacity += capacity;
			net.energy += energy;
		}
		
		for(TileCableAbstract w : wires.toArray(new TileCableAbstract[0]))
		{
			w.network = net;
			if(net != null) net.wires.add(w);
		}
		wires.clear();
		wiresTicked.clear();
		updateCapacity();
	}
	
	public void markTicked(TileCableAbstract wire)
	{
		if(wires.contains(wire)) wiresTicked.add(wire);
		if(wiresTicked.size() == wires.size())
		{
			wiresTicked.clear();
			masterTick();
		}
	}
	
	public void masterTick()
	{
		if(updateRate++ >= 80)
		{
			updateRate = 0;
			updateCapacity();
		}
	}
	
	public void updateCapacity()
	{
		capacity = 0D;
		for(TileCableAbstract w : wires.toArray(new TileCableAbstract[0]))
			capacity += w.getCapacityAddedToNet();
		for(TileCableAbstract w : wires.toArray(new TileCableAbstract[0]))
		{
			if(w.internal > 0D)
			{
				energy += w.internal;
				if(energy > capacity)
				{
					w.internal += energy - capacity;
					energy = capacity;
				}
			}
		}
	}
	
	public void splitEnergyTo1Wire(TileCableAbstract tileCableAbstract)
	{
		double eq = getEqualAmtFor1Wire();
		tileCableAbstract.internal += eq;
		energy -= eq;
	}
	
	public double getEqualAmtFor1Wire()
	{
		if(wires.size() == 0) return 0D;
		return energy / wires.size();
	}
	
	public void disconnect(TileCableAbstract wire)
	{
		wires.remove(wire);
		wiresTicked.remove(wire);
		wire.network = null;
		die();
	}
	
	public void connect(TileCableAbstract wire)
	{
		wires.add(wire);
		wire.network = this;
	}

	public void merge(CableNetwork network)
	{
		network.setNet(this);
	}
	
	public void die()
	{
		setNet(null);
	}
}
