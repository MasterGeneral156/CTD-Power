package com.themastergeneral.ctdpower.te.cables;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cofh.api.energy.IEnergyConnection;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CableBase extends BlockContainer
{
	public static final AxisAlignedBB
	CENTER = new AxisAlignedBB(6D / 16D, 6D / 16D, 6D / 16D, 10D / 16D, 10D / 16D, 10D / 16D),
	CENTER_UP = new AxisAlignedBB(6D / 16D, 10D / 16D, 6D / 16D, 10D / 16D, 1D, 10D / 16D),
	CENTER_DOWN = new AxisAlignedBB(6D / 16D, 6D / 16D, 6D / 16D, 10D / 16D, 0D, 10D / 16D),
	CENTER_EAST = new AxisAlignedBB(6D / 16D, 6D / 16D, 6D / 16D, 1D, 10D / 16D, 10D / 16D),
	CENTER_WEST = new AxisAlignedBB(0D, 6D / 16D, 6D / 16D, 6D / 16D, 10D / 16D, 10D / 16D),
	CENTER_SOUTH = new AxisAlignedBB(6D / 16D, 6D / 16D, 6D / 16D, 10D / 16D, 10D / 16D, 1D),
	CENTER_NORTH = new AxisAlignedBB(6D / 16D, 6D / 16D, 0D, 10D / 16D, 10D / 16D, 6D / 16D);
	private Double Capacity;
	private Integer tier;
	
	public CableBase(String name, Double cap, Integer tier)
	{
		super(Material.IRON);
		setSoundType(SoundType.METAL);
		setUnlocalizedName(name);
		setLightOpacity(255);
        useNeighborBrightness = true;
        setCreativeTab(CreativeTabs.MISC);
        setHardness(3.0F);
        setHarvestLevel("pickaxe", 0);
        setResistance(5.0F);
        this.Capacity = cap;
        this.tier = tier;
	}
	
	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1)
	{
		return new TileCable(Capacity, tier);
	}
	
	@Override
    public boolean isOpaqueCube(IBlockState p_isOpaqueCube_1_)
    {
    	return false;
    }
    
    @Override
    public boolean isVisuallyOpaque()
    {
    	return false;
    }
    
    @Override
    public boolean isFullyOpaque(IBlockState p_isFullyOpaque_1_)
    {
    	return false;
    }
    
    @Override
    public boolean isFullCube(IBlockState p_isFullCube_1_)
    {
    	return false;
    }
    
    @Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess w, BlockPos p)
	{
		Map<EnumFacing, Boolean> conns = new HashMap<EnumFacing, Boolean>();
		
		for(EnumFacing f : EnumFacing.VALUES)
		{
			conns.put(f, false);
			
			TileEntity te = w.getTileEntity(p.offset(f));
			
			if(te != null && TileCable.class.isAssignableFrom(te.getClass())) conns.put(f, true);
			else if(te instanceof IEnergyConnection) conns.put(f, true);
		}
		
		double nx = 5D / 16D, ny = 5D / 16D, nz = 5D / 16D, xx = 11D / 16D, xy = 11D / 16D, xz = 11D / 16D;
		
		if(conns.get(EnumFacing.SOUTH)) xz = 1D;
		if(conns.get(EnumFacing.NORTH)) nz = 0D;
		if(conns.get(EnumFacing.EAST)) xx = 1D;
		if(conns.get(EnumFacing.WEST)) nx = 0D;
		if(conns.get(EnumFacing.UP)) xy = 1D;
		if(conns.get(EnumFacing.DOWN)) ny = 0D;
		
		return new AxisAlignedBB(nx, ny, nz, xx, xy, xz);
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState s, World w, BlockPos p, AxisAlignedBB box, List<AxisAlignedBB> l, Entity ent)
	{
		addCollisionBoxToList(p, box, l, CENTER);
		
		if(w.getBlockState(p.offset(EnumFacing.EAST)).getBlock() == this) addCollisionBoxToList(p, box, l, CENTER_EAST);
		if(w.getBlockState(p.offset(EnumFacing.WEST)).getBlock() == this) addCollisionBoxToList(p, box, l, CENTER_WEST);
		if(w.getBlockState(p.offset(EnumFacing.SOUTH)).getBlock() == this) addCollisionBoxToList(p, box, l, CENTER_SOUTH);
		if(w.getBlockState(p.offset(EnumFacing.NORTH)).getBlock() == this) addCollisionBoxToList(p, box, l, CENTER_NORTH);
		if(w.getBlockState(p.offset(EnumFacing.UP)).getBlock() == this) addCollisionBoxToList(p, box, l, CENTER_UP);
		if(w.getBlockState(p.offset(EnumFacing.DOWN)).getBlock() == this) addCollisionBoxToList(p, box, l, CENTER_DOWN);
	}
	
	@Override
	public int getLightOpacity(IBlockState p_getLightOpacity_1_)
	{
		return 0;
	}
	
	@Override
	public int getLightOpacity(IBlockState p_getLightOpacity_1_, IBlockAccess p_getLightOpacity_2_, BlockPos p_getLightOpacity_3_)
	{
		return 0;
	}
}
