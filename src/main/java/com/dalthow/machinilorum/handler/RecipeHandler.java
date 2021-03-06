package com.dalthow.machinilorum.handler;

import com.dalthow.machinilorum.base.Main;
import com.dalthow.machinilorum.proxy.CommonProxy;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Craftus Machinilorum
 *
 * 
 * @author Dalthow Game Studios 
 * @class RecipeHandler.java
 * 
 **/

public class RecipeHandler 
{
	// Is called during the initialization of the game to load the modded recipes.
	
	public static void loadRecipes()
	{
		if(Main.recipeObsidian.getBoolean(false) == true)
		{
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.obsidian, 1), Items.lava_bucket, Items.water_bucket);
		}
			
		if(Main.recipeMossCobblestone.getBoolean(false) == true)
		{
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.mossy_cobblestone, 1), Blocks.cobblestone, Blocks.vine);
		}
		
		if(Main.recipeBone.getBoolean(false) == true)
		{
			GameRegistry.addShapelessRecipe(new ItemStack(Items.bone, 2), Items.chicken);
		}
		
		if(Main.recipeIce.getBoolean(false) == true)
		{
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.ice, 1), Items.snowball, Items.water_bucket);
		}
		
		if(Main.recipeSaddle.getBoolean(false) == true)
		{
			GameRegistry.addRecipe(new ItemStack(Items.saddle, 1), new Object[]
			{
				" L ",
				"L L",
				"I I",
				
				'I', Items.iron_ingot, 'L', Items.leather 
			});
		}
		
		if(Main.recipeCobweb.getBoolean(false) == true)
		{
			GameRegistry.addRecipe(new ItemStack(Blocks.web, 1), new Object[]
			{
				"S S",
				" S ",
				"S S",
				
				'S', Items.string
			});
		}
		
		if(Main.recipeFilter.getBoolean(false) == true)
		{
			GameRegistry.addRecipe(new ItemStack(Main.itemFilter, 1), new Object[]
			{
				"P P",
				" W ",
				
				'P', Blocks.planks, 'W', Blocks.wool
			});
		}
		
		if(Main.recipeBomb.getBoolean(false) == true)
		{
			GameRegistry.addRecipe(new ItemStack(Main.itemBomb, 1), new Object[]
			{
				"S",
				"G",
				
				'S', Items.string, 'G', Items.gunpowder
			});
		}
		
		if(Main.recipeStoneCutter.getBoolean(false) == true)
		{
			GameRegistry.addRecipe(new ItemStack(Main.blockStoneCutter, 1), new Object[]
			{
				"CC",
				"CC",
				
				'C', Blocks.cobblestone
			});
		}
		
		if(Main.recipeFragmentizer.getBoolean(false) == true)
		{
			GameRegistry.addRecipe(new ItemStack(Main.blockFragmentizer, 1), new Object[]
			{
				"CCC",
				"CGC",
				"PGP",
				
				'C', Blocks.cobblestone, 'G', Main.blockStoneCutter, 'P', Blocks.piston
			});
		}
		
		if(Main.recipeEggIncubator.getBoolean(false) == true)
		{
			GameRegistry.addRecipe(new ItemStack(Main.blockEggIncubator, 1), new Object[]
			{
				"GGG",
				"CWC",
				"CCC",
				
				'C', Blocks.cobblestone, 'W', Items.wheat, 'G', Blocks.glass
			});
		}
		
		if(Main.recipeWoodCutter.getBoolean(false) == true)
		{
			GameRegistry.addRecipe(new ItemStack(Main.blockWoodCutter, 1), new Object[]
			{
				"FFF",
				"CAC",
				"CRC",
				
				'C', Blocks.cobblestone, 'F', Items.flint, 'R', Items.redstone, 'A', Items.iron_axe
			});
		}
		
		if(Main.recipeMobRadar.getBoolean(false) == true)
		{
			GameRegistry.addRecipe(new ItemStack(Main.blockMobRadar, 1), new Object[]
			{
				" P ",
				"CDC",
				"CRC",
				
				'C', Blocks.cobblestone, 'P', Items.compass, 'R', Items.redstone, 'D', Items.diamond
			});
		}
		
		if(Main.recipeConveyorBelt.getBoolean(false) == true)
		{
			GameRegistry.addRecipe(new ItemStack(Main.blockConveyorBelt, 6), new Object[]
			{
				"CIC",
				
				'C', Blocks.cobblestone, 'I', Items.iron_ingot
			});
		}
		
		if(Main.recipeSprinkler.getBoolean(false) == true)
		{
			GameRegistry.addRecipe(new ItemStack(Main.blockSprinkler, 1), new Object[]
			{
				" G ",
				" IL",
				"CRC",
				
				'C', Blocks.cobblestone, 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'R', Items.redstone, 'L', new ItemStack(Items.dye, 1, 4)
			});
		}
		
		if(Main.recipeChalkboard.getBoolean(false) == true)
		{
			GameRegistry.addRecipe(new ItemStack(Main.blockChalkboard, 1), new Object[]
			{
				"SSS",
				"SCS",
				"SSS",
				
				'S', Items.stick, 'C', Blocks.stained_hardened_clay,
			});
		}
		
		if(Main.recipeShoppingCart.getBoolean(false) == true)
		{
			GameRegistry.addRecipe(new ItemStack(Main.itemShoppingCart, 1), new Object[]
			{
				"S T",
				"SSS",
				"I I",
				
				'I', Items.iron_ingot, 'S', Items.string, 'T', Items.stick
			});
		}
		
		if(Main.recipeChecker.getBoolean(false) == true)
		{
			GameRegistry.addRecipe(new ItemStack(Main.itemChecker, 1), new Object[]
			{
				"RI ",
				"CGC",
				"CRC",
				
				'I', Items.iron_ingot, 'C', Blocks.cobblestone, 'G', Blocks.glass, 'R', Items.redstone
			});
		}
	}
	
	
	// Is called during the initialization of the game to remove vanilla recipes.
	
	public static void removeRecipes()
	{
		CommonProxy.removeRecipe(Item.getItemFromBlock(Blocks.stone_slab));
		
		CommonProxy.removeRecipe(Item.getItemFromBlock(Blocks.sandstone));
		CommonProxy.removeRecipe(Item.getItemFromBlock(Blocks.stonebrick));
		
		CommonProxy.removeRecipe(Item.getItemFromBlock(Blocks.quartz_stairs));
		CommonProxy.removeRecipe(Item.getItemFromBlock(Blocks.stone_brick_stairs));
		CommonProxy.removeRecipe(Item.getItemFromBlock(Blocks.stone_stairs));
		CommonProxy.removeRecipe(Item.getItemFromBlock(Blocks.brick_stairs));
		CommonProxy.removeRecipe(Item.getItemFromBlock(Blocks.sandstone));
		CommonProxy.removeRecipe(Item.getItemFromBlock(Blocks.nether_brick));
		
		CommonProxy.removeRecipe(Item.getItemFromBlock(Blocks.cobblestone_wall));
	}
}
