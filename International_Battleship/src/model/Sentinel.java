package model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import tools.Coord;

@objid ("6b6d0859-e740-493d-98e4-4636c5ba6350")
public class Sentinel extends AbstractBoat {
    final int size = BoatName.Sentinel.getValue();

	@objid ("62b7f0e5-4f57-4a8c-bc19-9993b91246d0")
    public Sentinel(Coord coord, int id) {
		super(BoatName.Sentinel, coord, id);
    }

    public int getSize () {
    	return this.size;
    }

}
