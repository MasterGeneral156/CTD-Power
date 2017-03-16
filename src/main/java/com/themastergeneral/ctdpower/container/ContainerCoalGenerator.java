package com.themastergeneral.ctdpower.container;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerCoalGenerator extends Container
{
    private final IInventory te;
    private int currentRF;
    private int maxRF;
    private int cooldown;
    private int increasePerTick;

    public ContainerCoalGenerator(InventoryPlayer playerInv, IInventory furnaceInventory)
    {
        this.te = furnaceInventory;
        this.addSlotToContainer(new Slot(te, 0, 8, 20));
		
		//Player Inventory
		for (int y = 0; y < 3; ++y) 
		{
			for (int x = 0; x < 9; ++x) 
			{
				this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 86 + y * 18));
			}
		}
		//Player Hotbar	
		for (int x = 0; x < 9; ++x) 
		{
			this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 144));
		}
    }

    public void addListener(IContainerListener listener)
    {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.te);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); ++i)
        {
            IContainerListener icontainerlistener = (IContainerListener)this.listeners.get(i);

            if (this.currentRF != this.te.getField(0))
            {
                //icontainerlistener.sendProgressBarUpdate(this, 2, this.te.getField(0));
                this.currentRF = this.te.getField(0);
            }

            if (this.maxRF != this.te.getField(1))
            {
                //icontainerlistener.sendProgressBarUpdate(this, 0, this.te.getField(1));
            	this.maxRF = this.te.getField(1);
            }

            if (this.cooldown != this.te.getField(2))
            {
                //icontainerlistener.sendProgressBarUpdate(this, 1, this.te.getField(2));
            	this.cooldown = this.te.getField(2);
            }

            if (this.increasePerTick != this.te.getField(3))
            {
                //icontainerlistener.sendProgressBarUpdate(this, 3, this.te.getField(3));
            	this.increasePerTick = this.te.getField(3);
            }
        }
        /*
        this.currentRF = this.te.getField(0);
        this.maxRF = this.te.getField(1);
        this.cooldown = this.te.getField(2);
        this.increasePerTick = this.te.getField(3);*/
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        this.te.setField(id, data);
    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.te.isUseableByPlayer(playerIn);
    }

    /**
     * Take a stack from the specified inventory slot.
     */
    @Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) 
	{
		ItemStack previous = null;
		Slot slot = (Slot) this.inventorySlots.get(fromSlot);

		if (slot != null && slot.getHasStack()) 
		{
			ItemStack current = slot.getStack();
			previous = current.copy();
			if (fromSlot < 1) 
			{
				// From TE Inventory to Player Inventory
				if (!this.mergeItemStack(current, 1, this.inventorySlots.size(), true))
				{
					return null;
				}
			}
			else
			{
				// From Player Inventory to TE Inventory
				if (!this.mergeItemStack(current, 0, 1, false))
					return null;
			}
			if (current.stackSize == 0)
		            slot.putStack((ItemStack) null);
			else
				slot.onSlotChanged();
		}
		return previous;
	}
}