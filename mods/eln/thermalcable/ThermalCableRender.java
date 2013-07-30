package mods.eln.thermalcable;

import java.io.DataInputStream;
import java.io.IOException;

import org.lwjgl.opengl.GL11;

import mods.eln.Eln;
import mods.eln.cable.CableRender;
import mods.eln.cable.CableRenderDescriptor;
import mods.eln.item.MeterItemArmor;
import mods.eln.misc.Direction;
import mods.eln.misc.LRDU;
import mods.eln.misc.Utils;
import mods.eln.node.NodeBase;
import mods.eln.node.SixNodeDescriptor;
import mods.eln.node.SixNodeElementRender;
import mods.eln.node.SixNodeEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;


public class ThermalCableRender extends SixNodeElementRender{

	ThermalCableDescriptor cableDesciptor;

	public ThermalCableRender(SixNodeEntity tileEntity, Direction side,
			SixNodeDescriptor descriptor) {
		super(tileEntity, side, descriptor);
		this.cableDesciptor = (ThermalCableDescriptor) descriptor;
		// TODO Auto-generated constructor stub
	}

	double temperature = 0;
	int color = 0;
	
	public boolean drawCableAuto() {
		
		return false;
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		Minecraft.getMinecraft().mcProfiler.startSection("TCable");
		
		//ItemStack i = Minecraft.getMinecraft().thePlayer.inventory.armorInventory[3];
		
	//	GL11.glDisable(GL11.GL_TEXTURE_2D);

/*
		if(i != null && i.getItem()  == Eln.thermoMeterHelmet)
		{		
			double factor = temperature  *MeterItemArmor.getBlockRenderColorFactor(i);
			GL11.glColor4d(factor, 1.0-factor,0.0, 1.0);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_TEXTURE_2D);	
		}
		else*/
		{
			Utils.setGlColorFromDye(color);
		}
		

		
		Utils.bindTexture(cableDesciptor.render.cableTexture);
		glListCall();
		
		
		//GL11.glEnable(GL11.GL_LIGHTING);		
		//GL11.glEnable(GL11.GL_TEXTURE_2D);		
		
		Minecraft.getMinecraft().mcProfiler.endSection();				
	}

	@Override
	public void glListDraw() {
		CableRender.drawCable(cableDesciptor.render, connectedSide,CableRender.connectionType(this, side));
		CableRender.drawNode(cableDesciptor.render, connectedSide,CableRender.connectionType(this, side));

	}
	@Override
	public boolean glListEnable() {
		return true;	
	}
	
	@Override
	public void publishUnserialize(DataInputStream stream) {
		// TODO Auto-generated method stub
		super.publishUnserialize(stream);
		try {
			

			Byte b;
			b = stream.readByte();
			
			color = (b>>4) & 0xF;
			temperature = stream.readShort() /NodeBase.networkSerializeTFactor;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public CableRenderDescriptor getCableRender(LRDU lrdu) {
		return cableDesciptor.render;
	}
	
	@Override
	public int getCableDry(LRDU lrdu) {
		// TODO Auto-generated method stub
		return color;
	}
}
