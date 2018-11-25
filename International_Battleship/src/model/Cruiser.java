package model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import tools.Coord;

@objid ("8381580b-805e-4d6a-adea-46b5df3bdced")
public class Cruiser extends AbstractBoat {
    final int size = 4;

    @objid ("6bb6d6ef-55bb-484d-bb77-2d5b9fed816d")
    public Cruiser(Coord coord) {
    	super(BoatName.Cruiser, coord);
    }

    public int getSize( ) {
    	return this.size;
    }
}
