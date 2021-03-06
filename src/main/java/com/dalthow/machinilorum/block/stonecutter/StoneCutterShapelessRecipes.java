package com.dalthow.machinilorum.block.stonecutter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

/**
 * Craftus Machinilorum
 *
 * 
 * @author Dalthow Game Studios 
 * @class StoneCutterShapelessRecipes.java
 * 
 **/

public class StoneCutterShapelessRecipes implements IRecipe
{
	// Declaration of the crafting output.

    private final ItemStack recipeOutput;
    
    
    // Declaring the items that can now be crafted.
    
    public final List<?> recipeItems;
  
    
    // Constructor that sets the local variables based on its parameters.
    
    public StoneCutterShapelessRecipes(ItemStack recipeOutput, List<?> recipeItems)
    {
        this.recipeOutput = recipeOutput;
        this.recipeItems = recipeItems;
    }
    
    
    /**
     * getRecipeOutput Returns the output of a recipe.
     * 
     * @return {ItemStack}
     */
    public ItemStack getRecipeOutput()
    {
        return recipeOutput;
    }

   
    /**
     * matches Used to check if a recipe matches current crafting inventory.
     * 
     * @param {InventoryCrafting} inventoryCrafting The crafting recipe that has been used.
     * @param {World} world							The world object.			
     * 
     * @return {ItemStack}
     */
    public boolean matches(InventoryCrafting inventoryCrafting, World world)
    {
        ArrayList<?> var1 = new ArrayList<Object>(recipeItems);

        for(int i = 0; i < 5; ++i)
        {
            for(int j = 0; j < 5; ++j)
            {
                ItemStack var2 = inventoryCrafting.getStackInRowAndColumn(j, i);

                if(var2 != null)
                {
                    boolean flag = false;
                    
                    Iterator<?> var3 = var1.iterator();

                    while(var3.hasNext())
                    {
                        ItemStack var4 = (ItemStack)var3.next();

                        if(var2.getItem() == var4.getItem() && (var4.getItemDamage() == 32767 || var2.getItemDamage() == var4.getItemDamage()))
                        {
                            flag = true;
                            var1.remove(var4);
                            
                            break;
                        }
                    }

                    if(!flag)
                    {
                        return false;
                    }
                }
            }
        }

        return var1.isEmpty();
    }

    
    /**
     * getCraftingResult Returns an ItemStack that is the result of this recipe.
     * 
     * @param {InventoryCrafting} inventoryCrafting The crafting recipe that has been used.
     * 
     * @return {ItemStack}
     */
    public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting)
    {
        return recipeOutput.copy();
    }

    
    /**
     * getRecipeSize Gets the size of a recipe.
     * 
     * @return {int}
     */
    public int getRecipeSize()
    {
        return recipeItems.size();
    }
}