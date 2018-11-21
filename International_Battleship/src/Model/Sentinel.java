package Model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import tools.Coord;

@objid ("6b6d0859-e740-493d-98e4-4636c5ba6350")
public class Sentinel extends AbstractBoat {
    final int size = 1;

	@objid ("62b7f0e5-4f57-4a8c-bc19-9993b91246d0")
    public Sentinel(Coord coord) {
		super(BoatName.Sentinel, coord);
    }

    @objid ("e3a0f19d-f496-4b18-8b34-c7953806c406")
    @Override
    public void move() {
        // TODO Auto-generated method stub
    }

    @objid ("90d46db7-7bd1-4864-9e37-5bed7c4b3c76")
    @Override
    public void hourlyRotation() {
        // TODO Auto-generated method stub
    }

    @objid ("81499637-eb3b-401e-8916-4e434b256ce6")
    @Override
    public void antiHourlyRotation() {
        // TODO Auto-generated method stub
    }

    public int getSize () {
    	return this.size;
    }

}
