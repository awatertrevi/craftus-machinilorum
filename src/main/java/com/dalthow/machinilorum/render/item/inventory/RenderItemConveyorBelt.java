package com.dalthow.machinilorum.render.item.inventory;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.dalthow.machinilorum.base.Reference;

/**
 * Craftus Machinilorum
 *
 * 
 * @author Dalthow Game Studios 
 * @class RenderItemConveyorBelt.java
 * 
 **/

@SideOnly(Side.CLIENT)
public class RenderItemConveyorBelt implements IItemRenderer
{
	// Declaration of the model files.
	
    private IModelCustom model;

	private final ResourceLocation texture = new ResourceLocation(Reference.modId, "textures/models/conveyor belt off.png");
	 
	
	// Constructor that obtains the model.
    
    public RenderItemConveyorBelt()
    {
    	model = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.modId, "models/conveyor belt.obj"));
    }

    
    // Render settings.
    
    public boolean handleRenderType(ItemStack itemStack, ItemRenderType itemRenderType)
    {
        return true;
    }

    public boolean shouldUseRenderHelper(ItemRenderType itemRenderType, ItemStack itemStack, ItemRendererHelper itemRendererHelper)
    {
        return true;
    }

    
    // All the different cases where it can be rendered in.
    
    public void renderItem(ItemRenderType itemRenderType, ItemStack itemStack, Object... data)
    {
        switch (itemRenderType)
        {
            case ENTITY:
            {
                render(-0.5F, 0.0F, 0.5F);
                
                return;
            }
            
            case EQUIPPED:
            {
                render(0.0F, 0.0F, 1.0F);
                
                return;
            }
            
            case EQUIPPED_FIRST_PERSON:
            {
                render(0.0F, 0.0F, 1.0F);
               
                return;
            }
            
            case INVENTORY:
            {
                render(0.0F, 0F, 1.0F);
                
                return;
            }
            
            default:
            {
            	return;
            }
        }
    }

    
    // The actual render method.
    
    private void render(float xPos, float yPos, float zPos)
    {
        GL11.glPushMatrix();

        GL11.glScalef(0.5F, 0.5F, 0.5F);
        GL11.glTranslatef(xPos + 1F, yPos - 0.15F, zPos);
        GL11.glRotatef(90, 0, 1, 0);
        
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);

      	model.renderAll();

        GL11.glPopMatrix();
    }
}
