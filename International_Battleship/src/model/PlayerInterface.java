package model;



import java.util.List;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("e18fc35c-5b26-4137-a3ef-3863e6e3f46a")
public interface PlayerInterface {
	
	public List<BoatInterface> getFleet();
	
	@objid ("b31194db-0d16-49e3-836e-01c937a0eb0b")
    BoatInterface getBoat();

    @objid ("6d661e57-8fae-4018-a1b5-70963b1dc0b3")
    void setBoat(final BoatInterface value);

}