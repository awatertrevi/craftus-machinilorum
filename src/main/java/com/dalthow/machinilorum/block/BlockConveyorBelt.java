package com.dalthow.machinilorum.block;

import com.dalthow.machinilorum.base.Main;
import com.dalthow.machinilorum.tile.TileEntityConveyorBelt;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Craftus Machinilorum
 *
 * 
 * @author Dalthow Game Studios 
 * @class BlockConveyorBelt.java
 * 
 **/

public class BlockConveyorBelt extends Block implements ITileEntityProvider
{
	// Constructor that adds data to the block.
	
	public BlockConveyorBelt()
	{
		super(Material.rock);
		
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
		setCreativeTab(Main.tabMachinilorumMachines);
		setHardness(2.5F);
		setHarvestLevel("pickaxe", 1);
	}
	
	
	// Determines the textures displayed on the blocks based on the side and meta data also gets ignored by the server.
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return side == 1 ? Blocks.furnace.getBlockTextureFromSide(side) : side == 0 ? Blocks.furnace.getBlockTextureFromSide(side) : Blocks.furnace.getBlockTextureFromSide(side);
	}
	
	
	// If a redstone wire is next to this block it connects.
	
	@Override
	public boolean canConnectRedstone(IBlockAccess access, int xPos, int yPos, int zPos, int par1)
	{
	    return true;
	}
	
	
	// Creates a TileEntity when you place it down.
	
	public TileEntity createNewTileEntity(World world, int par1) 
	{
		TileEntityConveyorBelt tile = new TileEntityConveyorBelt(); 
        
		return tile;
	}
	
	
	// Makes you not see trough the world because the block isn't full.
	
	@Override
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	
	// Makes the block render as a custom block.
	
	@Override
    public int getRenderType()
    {
        return -1;
    }

	@Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	
	// Makes something happen when you click the block.
	
	public boolean onBlockActivated(World world, int xPos, int yPos, int zPos, EntityPlayer player, int par1, float par2, float par3, float par4) 
    {  
		// Rotating the block if its clicked with an empty hand.
    	
    	if(player.getHeldItem() == null) 
        { 
			if(world.getBlockMetadata(xPos, yPos, zPos) < 5)
			{
				world.setBlockMetadataWithNotify(xPos, yPos, zPos, (world.getBlockMetadata(xPos, yPos, zPos) + 1), 1);
			}
			
			else
			{
				world.setBlockMetadataWithNotify(xPos, yPos, zPos, 0, 1);
			}
			
			return true;
        }
		
		return false;
    }
}
