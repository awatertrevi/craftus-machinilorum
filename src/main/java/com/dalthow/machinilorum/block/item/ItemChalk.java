package com.dalthow.machinilorum.block.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Craftus Machinilorum
 *
 * 
 * @Author Dalthow Game Studios 
 * @Class ItemChalk.java
 * 
 **/

public class ItemChalk extends ItemBlock 
{
	// Declaration of all the sub blocks.
	
	final String[] subBlocks = new String[] {"raw", "bricks"};

	
	// Constructor that adds data to the item.
	
	public ItemChalk(Block block) 
	{
		super(block);
		
		setHasSubtypes(true);
	}
	
	
	// Returns the names of the sub blocks.

	public String getUnlocalizedName(ItemStack itemStack) 
	{
		int i = itemStack.getItemDamage();
		
		if (i < 0 || i >= subBlocks.length) 
		{
			i = 0;
		}

		return super.getUnlocalizedName() + "." + subBlocks[i];
	}

	
	// Returns the meta data from the sub blocks.
	
	public int getMetadata(int meta) 
	{
		return meta;
	}
}
