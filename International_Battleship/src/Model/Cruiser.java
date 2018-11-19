package Model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import tools.Coord;

@objid ("8381580b-805e-4d6a-adea-46b5df3bdced")
public class Cruiser extends AbstractBoat {
    final int size = 4;

    @objid ("6bb6d6ef-55bb-484d-bb77-2d5b9fed816d")
    public Cruiser(Coord coord) {
    	super(BoatName.Cruiser, coord);
    }

    @objid ("4873fad4-fba9-407a-8784-4384d06ec67e")
    @Override
    public void move() {
        // TODO Auto-generated method stub
    }

    @objid ("df462c13-7058-490a-8e61-5eef54851c5c")
    @Override
    public void hourlyRotation() {
        // TODO Auto-generated method stub
    }

    @objid ("cc5603b7-ddd9-4d16-802b-d8e00a5ead3d")
    @Override
    public void antiHourlyRotation() {
        // TODO Auto-generated method stub
    }

    public int getSize( ) {
    	return this.size;
    }
}
