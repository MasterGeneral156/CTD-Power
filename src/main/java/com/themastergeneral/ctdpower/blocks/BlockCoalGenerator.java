package com.themastergeneral.ctdpower.blocks;

import javax.annotation.Nullable;

import com.themastergeneral.ctdpower.CTDPower;
import com.themastergeneral.ctdpower.handlers.GUIHandler;
import com.themastergeneral.ctdpower.te.machines.generator.CoalGenerator;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCoalGenerator extends BlockContainer {

	public BlockCoalGenerator(String unlocalizedName) {
		super(Material.ROCK);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(CreativeTabs.MISC);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new CoalGenerator();
	}
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if(!worldIn.isRemote) {
			playerIn.openGui(CTDPower.instance, GUIHandler.COAL_GENERATOR, worldIn, pos.getX(), pos.getY(), pos.getZ());
			System.out.println(GUIHandler.COAL_GENERATOR);
			System.out.println(worldIn);
			System.out.println(pos.getX());
			System.out.println(pos.getY());
			System.out.println(pos.getZ());
		}
		return true;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState blockstate) {
		CoalGenerator te = (CoalGenerator) world.getTileEntity(pos);
	    InventoryHelper.dropInventoryItems(world, pos, te);
	    super.breakBlock(world, pos, blockstate);
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
	    if (stack.hasDisplayName()) {
	        ((CoalGenerator) worldIn.getTileEntity(pos)).setCustomName(stack.getDisplayName());
	    }
	}
	
	public int getRenderType() {
		return 3;
	}

}
