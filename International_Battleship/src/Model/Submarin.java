package Model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import tools.Coord;

@objid ("6e97e275-d4cc-4ebf-b561-c227b2cf71b7")
public class Submarin extends AbstractBoat {
    final int size = 3;

    @objid ("8258161e-781b-456d-b755-55f8df14bb0f")
    public Submarin(Coord coord) {
    	super(BoatName.SUBMARIN, coord);
    }


    @objid ("43d21173-1bc4-4697-b15b-e68ebb16a070")
    public void shoot() {
    }

    @objid ("fc227ee8-9e1b-432c-95c1-dea5ed968186")
    public void move() {
    }

    @objid ("61aa8350-5b14-44f6-bb96-9db7182264d2")
    public void hourlyRotation() {
    }

    @objid ("42800ab6-df71-49e1-b549-e5be4dd190f5")
    public void antiHourlyRotation() {
    }

    public int getSize () {
    	return this.size;
    }

}
