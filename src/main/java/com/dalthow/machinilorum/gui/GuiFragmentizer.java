package com.dalthow.machinilorum.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.dalthow.machinilorum.base.Reference;
import com.dalthow.machinilorum.container.ContainerFragmentizer;
import com.dalthow.machinilorum.tile.TileEntityFragmentizer;

/**
* Craftus Machinilorum
*
* 
* @author Dalthow Game Studios 
* @class GuiFragmentizer.java
* 
**/

public class GuiFragmentizer extends GuiContainer
{	
	// Declaration of the TileEntity.
	
	private TileEntityFragmentizer tile;
	
	
	// Declaration of the elements used in the gui.
	
	private GuiButton close;
	
	private final ResourceLocation background = new ResourceLocation(Reference.modId + ":" + "textures/gui/fragmentizer.png");
	
	
	// Constructor that adds data to the gui.
	
	public GuiFragmentizer(InventoryPlayer inventoryPlayer, TileEntityFragmentizer tile) 
	{
		super(new ContainerFragmentizer(inventoryPlayer, tile));
		
		this.tile = tile;
		
		xSize = 256;
		ySize = 166;
	}
	
	
	// Everything in front of the slots.
	
	@Override	
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		fontRendererObj.drawString(I18n.format("container.fragmentizer", new Object[0]), xSize / 2 - fontRendererObj.getStringWidth(I18n.format("container.fragmentizer", new Object[0])), 6, 4210752);
		fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, ySize - 96 + 2, 4210752);
		
		buttonList.clear();
		
		close = new GuiButton(0, width / 2 + 51, height / 2 - 70, 71, 20, I18n.format("Close", new Object[0]));
		
		buttonList.add(close);	
	}
	
	
	// Everything behind the slots.
		
	@Override
	protected void drawGuiContainerBackgroundLayer(float xPos, int yPos, int zPos) 
	{
		GL11.glColor4f(1F, 1F, 1F, 1F);

		Minecraft.getMinecraft().getTextureManager().bindTexture(background);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		int var1 = tile.getCookProgressScaled(24);
		drawTexturedModalRect(guiLeft + 79, guiTop + 36, 0, 182, 1 + var1, 12);
		
		if(tile.isFragmenting())
	    {
			int var2 = tile.getBurnTimeRemainingScaled(12);
	    	drawTexturedModalRect(guiLeft + 57, guiTop + 36 + 12 - var2, 0, 178 - var2, 14, var2 + 2);
	    }	
	}
	
	
	// Executes something after a button press.
	
	protected void actionPerformed(GuiButton guiButton)
    {
		if(guiButton.id == 0)
        {
        	mc.thePlayer.closeScreen();
        }
    }
}
