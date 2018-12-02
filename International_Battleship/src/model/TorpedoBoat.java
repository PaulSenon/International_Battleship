package model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import tools.Coord;

@objid ("07eff5db-2976-4547-8f92-df67c63dfc1f")
public class TorpedoBoat extends AbstractBoat {
	final int size = 2;

    @objid ("626c2269-3ed4-48f9-9a35-95e47f65c6db")
    public TorpedoBoat(Coord coord, int id) {
    	super(BoatName.TorpedoBoat, coord, id);
    }

    public int getSize() {
    	return this.size;
    }

}
