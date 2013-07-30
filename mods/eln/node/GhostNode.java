package mods.eln.node;

import mods.eln.Eln;
import mods.eln.misc.Direction;
import mods.eln.misc.LRDU;
import mods.eln.sim.ElectricalLoad;
import mods.eln.sim.ThermalLoad;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public abstract class GhostNode extends NodeBase{

	@Override
	public short getBlockId() {
		// TODO Auto-generated method stub
		return (short) Eln.ghostBlock.blockID;
	}


	

}
