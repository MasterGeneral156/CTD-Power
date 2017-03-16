package com.themastergeneral.ctdpower.items;

import com.themastergeneral.ctdpower.blocks.BlockCoalGenerator;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemWrench extends BasicItem {

	public ItemWrench(String name) 
	{
		super(name);
		this.setCreativeTab(CreativeTabs.TOOLS);
        this.setNoRepair();
        this.maxStackSize = 1;
	}
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		if(stack != null && player.isSneaking())
		{
			if(world.getBlockState(pos) instanceof BlockCoalGenerator)
			{
				world.spawnEntityInWorld(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack((Block) world.getBlockState(pos), 1)));
				world.setBlockToAir(pos);
				return EnumActionResult.PASS;
			}
		}
        return EnumActionResult.PASS;
    }
}
