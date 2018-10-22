package Model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("07eff5db-2976-4547-8f92-df67c63dfc1f")
public class TorpedoBoat extends AbstractBoat {
	final int size = 2;
	
    @objid ("626c2269-3ed4-48f9-9a35-95e47f65c6db")
    public TorpedoBoat() {
    }

    @objid ("98961ac2-9fa5-485d-a769-f844cd66e86b")
    @Override
    public void shoot() {
        // TODO Auto-generated method stub
    }

    @objid ("30c44c71-c872-44ba-b56c-220f6f026b5f")
    @Override
    public void move() {
        // TODO Auto-generated method stub
    }

    @objid ("c0ff792b-88e9-4386-b087-6b29b9d43703")
    @Override
    public void hourlyRotation() {
        // TODO Auto-generated method stub
    }

    @objid ("5346be0a-7f59-46ed-9968-2ded9dc6d0ff")
    @Override
    public void antiHourlyRotation() {
        // TODO Auto-generated method stub
    }
    
    public int getSize() {
    	return this.size;
    }

}
